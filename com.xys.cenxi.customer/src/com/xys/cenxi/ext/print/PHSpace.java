package com.xys.cenxi.ext.print;

/**
 * Horizontal white space
 * 
 * @author Friederich Kupzog
 */
class PHSpace extends PBox {

  private double cm;

  /**
   * Creates a new Space
   */
  public PHSpace(PContainer parent, int style, double cm) {
    super(parent, style);
    this.cm = cm;
    // getBoxStyle().backColor = SWT.COLOR_GREEN;
  }

  /*
   * overridden from superclass
   */
  protected int getWidth() {
    if (grabbing)
      return grabWidth;
    return PBox.pixelX(cm);
  }

  protected int getHeight() {
    if (forcedHeight > 0)
      return forcedHeight;
    // return 2;
    return 0;
  }

}