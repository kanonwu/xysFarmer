package com.xys.cenxi.ext.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * A style for printable objects.
 * 
 * @author Friederich Kupzog
 */
class PStyle {

  public double[] lines;

  public int lineColor;

  public int backColor;

  public PStyle() {
    lines = new double[4];
    lines[0] = 0.0;
    lines[1] = 0.0;
    lines[2] = 0.0;
    lines[3] = 0.0;
    lineColor = SWT.COLOR_BLACK;
    backColor = SWT.COLOR_WHITE;

    // setDebugStyle();
  }

  private void setDebugStyle() {
    lines[0] = 0.01;
    lines[1] = 0.01;
    lines[2] = 0.01;
    lines[3] = 0.01;
    lineColor = SWT.COLOR_GRAY;
  }

  public static PStyle getDefaultStyle() {
    return new PStyle();
  }

  public int getLineWidth(int num) {
    int pixel = 0;
    if (num == 0 || num == 2)
      pixel = PBox.pixelY(lines[num]);
    if (num == 1 || num == 3)
      pixel = PBox.pixelX(lines[num]);
    if (pixel < 0)
      return 0;
    if (pixel == 0)
      return 1;
    return pixel;
  }

  public boolean hasLine(int num) {
    return lines[num] > 0;
  }

  public Color getLineColor() {
    return PBox.device.getSystemColor(lineColor);
  }

  public Color getBackColor() {
    return PBox.device.getSystemColor(backColor);
  }

}