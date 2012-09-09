package com.xys.cenxi.ext.print;

import org.eclipse.swt.graphics.Point;

/**
 * Used inside the KPrint implementation
 * 
 * @author Friederich Kupzog
 */
class PagePoint {

  /**
   * 
   */
  public int page;

  public int x, y;

  public PagePoint() {
    x = 0;
    y = 0;
    page = 0;
  }

  public PagePoint(Point p, int page) {
    x = p.x;
    y = p.y;
    this.page = page;
  }

}