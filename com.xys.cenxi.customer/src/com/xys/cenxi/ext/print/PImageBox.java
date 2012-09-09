package com.xys.cenxi.ext.print;

import java.io.InputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * A printable Image label.
 * 
 * @author Friederich Kupzog For more details
 * @see PDocument
 */
class PImageBox extends PBox {

  protected String imgName;

  protected int imgDPI;

  protected Image image;

  protected Point imgOriginalSize;

  protected Point imgTargetSize;

  /**
   * @param parent
   * @param style
   */
  public PImageBox(PContainer parent, int style) {
    super(parent, style);
  }

  /**
   * Sets the Image. The size of the image will be calculated by using the dpi
   * parameter, in which you can specify which resolution is the "native"
   * image resulotion. If you e.g. specify 600 dpi and print on a 600 dpi
   * printer, the image will not be resized. If you print it on an 300 dpi
   * printer, it will be resized.
   * 
   * @param name
   * @param dpi
   */
  public void setImage(String name, int dpi) {
    this.imgName = name;
    this.imgDPI = dpi;
    image = null;
    imgOriginalSize = null;
    imgTargetSize = null;
  }

  /*
   * overridden from superclass
   */
  protected void layoutResetTuning() {
    super.layoutResetTuning();
    image = null;
    imgOriginalSize = null;
    imgTargetSize = null;
  }

  protected Point calcSize() {
    try {
      Class clazz = new Object().getClass();
      InputStream is = clazz.getResourceAsStream(imgName);
      image = new Image(device, is);

      imgOriginalSize = new Point(0, 0);
      imgOriginalSize.x = image.getImageData().width;
      imgOriginalSize.y = image.getImageData().height;

      imgTargetSize = new Point(0, 0);
      imgTargetSize.x = (int) (imgOriginalSize.x * scalingPercent / 100 * (pixelPerInch.x / (double) imgDPI));
      imgTargetSize.y = (int) (imgOriginalSize.y * scalingPercent / 100 * (pixelPerInch.y / (double) imgDPI));

      sizeCalculatedfor = scalingPercent;

      image.getImageData().transparentPixel = -1;
      image.getImageData().maskData = null;

      return imgTargetSize;

    } catch (Exception e1) {
      System.out.println("could not open ressource " + imgName);
      imgOriginalSize = new Point(10, 10);
      imgTargetSize = new Point(10, 10);
      return imgTargetSize;
    }

  }

  /*
   * overridden from superclass
   */
  protected int layoutHowMuchWouldYouOccupyOf(Point spaceLeft, int page) {
    if (layoutAlreadyFinished())
      return 0;
    if (sizeCalculatedfor != scalingPercent || image == null) {
      calcSize();
    }
    // System.out.println("Size: "+imgTargetSize.y+" Space: "+spaceLeft.y);
    if (imgTargetSize.y > spaceLeft.y)
      return -1;
    return imgTargetSize.y;
  }

  /*
   * overridden from superclass
   */
  protected int getWidth() {
    if (sizeCalculatedfor != scalingPercent || image == null) {
      calcSize();
    }
    return imgTargetSize.x;
  }

  /*
   * overridden from superclass
   */
  protected int getHeight() {
    if (rowAlign)
      return forcedHeight;
    if (sizeCalculatedfor != scalingPercent || image == null) {
      calcSize();
    }
    return imgTargetSize.y;
  }

  /*
   * overridden from superclass
   */
  protected int layoutOccupy(Point origin, Point spaceLeft, int page) {
    if (layoutAlreadyFinished())
      return 0;
    if (sizeCalculatedfor != scalingPercent || image == null) {
      calcSize();
    }
    this.origin = new PagePoint(origin, page);
    return imgTargetSize.y;
  }

  public void draw(int page, Point originOffset) {
    if (layoutIsOnPage(page)) {
      super.draw(page, originOffset);
      if (image != null) {
        gc.drawImage(image, 0, 0, imgOriginalSize.x, imgOriginalSize.y,
            origin.x + originOffset.x, origin.y + originOffset.y,
            imgTargetSize.x, imgTargetSize.y);
      }
    }
  }

}