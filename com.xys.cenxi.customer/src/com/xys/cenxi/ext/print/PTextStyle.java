package com.xys.cenxi.ext.print;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * A style for printable objects that that contain text.
 * 
 * @author Friederich Kupzog
 */
class PTextStyle {

  public static final int ALIGN_LEFT = 1;

  public static final int ALIGN_RIGHT = 2;

  public static final int ALIGN_CENTER = 3;

  protected static HashMap fonts = new HashMap();

  public int fontSize;

  public String fontName;

  public int fontStyle;

  public int fontColor;

  public int textAlign;

  protected double marginLeft;

  protected double marginRight;

  protected double marginTop;

  protected double marginBottom;

  public PTextStyle() {
    fontName = "Arial";
    fontStyle = SWT.NORMAL;
    fontSize = 10;
    fontColor = SWT.COLOR_BLACK;
    textAlign = ALIGN_LEFT;

    marginLeft = 0.0;
    marginRight = 0.0;

    marginTop = 0.0;
    marginBottom = 0.0;

  }

  public static void disposeFonts() {
    for (Iterator iter = fonts.values().iterator(); iter.hasNext();) {
      Font element = (Font) iter.next();
      element.dispose();
    }
    fonts.clear();
  }

  public static PTextStyle getDefaultStyle() {
    return new PTextStyle();
  }

  public Font getFont() {
    int height = Math.abs(fontSize * PBox.scalingPercent / 100);
    String key = PBox.device.getDPI().x + "|" + PBox.device.getDPI().y
        + "|" + fontName + "|" + height + "|" + fontStyle;
    Font font = (Font) fonts.get(key);
    if (font != null)
      return font;
    font = new Font(PBox.device, fontName, Math.abs(fontSize
        * PBox.scalingPercent / 100), fontStyle);
    fonts.put(key, font);
    return font;
  }

  public Color getFontColor() {
    return PBox.device.getSystemColor(fontColor);
  }

  /**
   * @return double
   */
  public double getMarginLeft() {
    return marginLeft;
  }

  /**
   * @return double
   */
  public double getMarginRight() {
    return marginRight;
  }

  /**
   * Sets the marginLeft.
   * 
   * @param marginLeft
   *            The marginLeft to set
   */
  public void setMarginLeft(double marginLeft) {
    this.marginLeft = marginLeft;
  }

  /**
   * Sets the marginRight.
   * 
   * @param marginRight
   *            The marginRight to set
   */
  public void setMarginRight(double marginRight) {
    this.marginRight = marginRight;
  }

  /**
   * @return double
   */
  public double getMarginBottom() {
    return marginBottom;
  }

  /**
   * @return double
   */
  public double getMarginTop() {
    return marginTop;
  }

  /**
   * Sets the marginBottom.
   * 
   * @param marginBottom
   *            The marginBottom to set
   */
  public void setMarginBottom(double marginBottom) {
    this.marginBottom = marginBottom;
  }

  /**
   * Sets the marginTop.
   * 
   * @param marginTop
   *            The marginTop to set
   */
  public void setMarginTop(double marginTop) {
    this.marginTop = marginTop;
  }

}