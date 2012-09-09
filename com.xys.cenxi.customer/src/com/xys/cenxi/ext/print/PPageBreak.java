package com.xys.cenxi.ext.print;

import org.eclipse.swt.graphics.Point;

/**
 * forces a page break at the current position in the tree of printable elements
 * 
 * @author Friederich Kupzog
 */
class PPageBreak extends PBox {

  public PPageBreak(PContainer parent) {
    super(parent);
  }

  /*
   * overridden from superclass
   */
  protected int getWidth() {
    return 0;
  }

  /*
   * overridden from superclass
   */
  protected int layoutHowMuchWouldYouOccupyOf(Point spaceLeft, int page) {
    return -1;
  }

  /*
   * overridden from superclass
   */
  public void draw(int page, Point originOffset) {
  }

}