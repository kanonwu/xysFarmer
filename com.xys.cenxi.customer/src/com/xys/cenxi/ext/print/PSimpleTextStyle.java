package com.xys.cenxi.ext.print;

import org.eclipse.swt.SWT;

/**
 * @author Friederich Kupzog A PTextStyle that is easy to create.
 */
class PSimpleTextStyle extends PTextStyle {
  public PSimpleTextStyle(String fontname, int size, boolean bold) {
    super();
    this.fontName = fontname;
    this.fontSize = size;
    if (bold)
      this.fontStyle = SWT.BOLD;
  }

}