package com.xys.cenxi.ext.print;

import org.eclipse.swt.graphics.Point;

/**
 * A text box that shows the current page number.
 * 
 * @author Friederich Kupzog
 */
class PPageNumber extends PTextBox {
  protected static int pageNumber = 0;

  /**
   * @param parent
   * @param style
   */
  public PPageNumber(PContainer parent, int style) {
    super(parent, style, -1, 0);
    setText("    ");
  }

  public void draw(int page, Point originOffset) {

    if (layoutIsOnPage(page)) {
      super.draw(page, originOffset);
      gc.setFont(textStyle.getFont());
      gc.setForeground(textStyle.getFontColor());
      gc.drawText("" + pageNumber, origin.x + originOffset.x
          + pixelX(textStyle.getMarginLeft()), origin.y
          + originOffset.y, true);
    }
  }

}