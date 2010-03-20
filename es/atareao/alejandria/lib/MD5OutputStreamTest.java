package es.atareao.alejandria.lib;

import java.io.*;
import java.util.*;
import es.atareao.alejandria.lib.MD5;
import es.atareao.alejandria.lib.MD5OutputStream;
import es.atareao.alejandria.lib.NullOutputStream;

/**
 * Copyright (c) 2002 by Timothy W Macinta, All Rights Reserved.<p>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * <p>
 * This class generates a stream of random data and compares the
 * results of the md5 checksum generated by an instance of
 * MD5OutputStream with the md5 checksum by piping the data through
 * the local md5sum binary.
 * <p>
 * See http://www.twmacinta.com/myjava/fast_md5.php for more information
 * on this file.
 *
 * @author Tim Macinta (twm@alum.mit.edu)
 **/

public class MD5OutputStreamTest {

  /**
   * Usage:
   *   java com.twmacinta.util.test.MD5OutputStreamTest [seed | "time" [max_data]]
   **/
  public static void main(String arg[]) {
    try {
      long seed = System.currentTimeMillis();
      if (arg.length > 0) {
	if (!arg[0].equals("time")) {
	  seed = Long.parseLong(arg[0]);
	}
      }

      long max_data = (20L * (1 << 30)); // max 20 gigabytes
      if (arg.length > 1) {
	max_data = Long.parseLong(arg[1]);
      }

      Random ran = new Random(seed);
      while (true) {
	System.out.print("seed:  "+seed+"  \t");
	long data_size = ran.nextLong();
	if (data_size < 0) data_size = -data_size;
	data_size = data_size % (max_data + 1);
	System.out.println("size:  "+data_size);
	runTest(data_size, ran);
	seed = ran.nextLong();
	ran.setSeed(seed);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void runTest(long data_size, Random ran) throws IOException {
    Process proc = Runtime.getRuntime().exec("md5sum");
    MD5OutputStream out1 = new MD5OutputStream(new NullOutputStream());
    OutputStream out2 = new BufferedOutputStream(proc.getOutputStream());
    
    while (data_size > 0) {

      int output_type = ran.nextInt() % 100;

      output_type -= 5;  // 5% chance
      if (output_type < 0) {
	outputSingleByte(ran, out1, out2);
	data_size--;
	continue;
      }

      output_type -= 25;  // 25% chance
      if (output_type < 0) {
	data_size -= outputFullBuffer(ran, out1, out2, data_size);
	continue;
      }

      // the default

      data_size -= outputPartialBuffer(ran, out1, out2, data_size);
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
    out2.flush();
    out2.close();

    String native_sum = new StringTokenizer(in.readLine()).nextToken();
    in.close();
    String java_sum = MD5.asHex(out1.hash());
    if (!native_sum.equals(java_sum)) {
      out1.close();
      System.out.println("ERROR");
      System.out.println("java:   " + java_sum);
      System.out.println("native: " + native_sum);
      System.exit(1);
    }
    out1.close();
  }

  private static void outputSingleByte(Random ran, OutputStream out1, OutputStream out2) throws IOException {
    int b = ran.nextInt() & 0xff;
    out1.write(b);
    out2.write(b);
  }

  private static long outputFullBuffer(Random ran, OutputStream out1, OutputStream out2, long max_bytes) throws IOException {
    int b_len = ran.nextInt();
    if (b_len < 0) b_len = -b_len;
    b_len = b_len % ((1 << 20) / 8);  // 1/8 meg max
    if (b_len > max_bytes) b_len = (int) max_bytes;
    byte[] b = new byte[b_len];
    ran.nextBytes(b);
    out1.write(b);
    out2.write(b);
    return b_len;
  }

  private static long outputPartialBuffer(Random ran, OutputStream out1, OutputStream out2, long max_bytes) throws IOException {
    int b_len = ran.nextInt();
    if (b_len < 0) b_len = -b_len;
    b_len = b_len % ((1 << 20) / 2);  // 1/2 meg max
    if (b_len > max_bytes) b_len = (int) max_bytes;
    if (b_len == 0) return 0;
    byte[] b = new byte[b_len];
    ran.nextBytes(b);

    int off = ran.nextInt();
    if (off < 0) off = -off;
    off = off % b_len;
    if (off == b_len) return 0;
    
    int len = ran.nextInt();
    if (len < 0) len = -len;
    len = len % (b_len - off);

    out1.write(b, off, len);
    out2.write(b, off, len);
    return len;
  }

}
