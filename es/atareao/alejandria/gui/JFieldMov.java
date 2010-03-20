/*
 * JFieldMov
 *
 * File created on 14-mar-2010
 * Copyright (c) 2009 Lorenzo Carbonell
 * email: lorenzo.carbonell.cerezo@gmail.com
 * website: http://www.atareao.es
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.atareao.alejandria.gui;

import javax.swing.SwingConstants;

/**
 *
 * @author atareao
 */
public interface JFieldMov {
    String EDIT_STOP = "stopEditing";
    int PROP_DOWN = 4;
    int PROP_LEFT = 1;
    int PROP_NONE = -1;
    int PROP_RIGHT = 2;
    int PROP_UP = 3;
    int LEFT=SwingConstants.LEFT;
    int RIGHT=SwingConstants.RIGHT;
    int CENTER=SwingConstants.CENTER;
}
