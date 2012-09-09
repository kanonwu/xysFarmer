package com.xys.cenxi.ext.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableModel;

/*******************************************************************************
 * Copyright (C) 2004 by Friederich Kupzog Elektronik & Software All rights
 * reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Friederich Kupzog - initial API and implementation
 * fkmk@kupzog.de www.kupzog.de/fkmk
 ******************************************************************************/

public class PaletteExampleRenderer implements KTableCellRenderer {

  /**
   * 
   */
  public PaletteExampleRenderer() {
  }

  /*
   * overridden from superclass
   */
  public int getOptimalWidth(GC gc, int col, int row, Object content,
      boolean fixed) {
    return 16;
  }

  /*
   * overridden from superclass
   */
  public void drawCell(GC gc, Rectangle rect, int col, int row,
      Object content, boolean focus, boolean fixed, boolean clicked) {
    // Performance test:
    /*
     * gc.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
     * gc.fillRectangle(rect);
     * 
     * int j=1; for (int i = 0; i < 10000000; i++) { j++; }
     */
    Color color = new Color(Display.getDefault(), (RGB) content);
    gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
    rect.height++;
    rect.width++;
    gc.fillRectangle(rect);

    gc.setBackground(color);
    if (!focus) {
      rect.x += 1;
      rect.y += 1;
      rect.height -= 2;
      rect.width -= 2;
    }
    gc.fillRectangle(rect);
    color.dispose();
  }

@Override
public void drawCell(GC arg0, Rectangle arg1, int arg2, int arg3, Object arg4,
		boolean arg5, boolean arg6, boolean arg7, KTableModel arg8) {
	// TODO Auto-generated method stub
	
}

@Override
public int getOptimalWidth(GC arg0, int arg1, int arg2, Object arg3,
		boolean arg4, KTableModel arg5) {
	// TODO Auto-generated method stub
	return 0;
}

}