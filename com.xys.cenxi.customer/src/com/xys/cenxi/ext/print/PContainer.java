package com.xys.cenxi.ext.print;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Point;

/**
 * A Container class for PBoxes. Used for PDocument, Headers and Footers.
 * 
 * @author Friederich Kupzog
 */
class PContainer {

  protected ArrayList children;

  protected PDocument doc;

  private int calculatedHeight;

  /**
   * 
   */
  public PContainer(PDocument doc) {
    this.doc = doc;
    children = new ArrayList(100);
    calculatedHeight = -1;
  }

  protected double getPossibleWidth() {
    return (doc.pageWidth - doc.margins[1] - doc.margins[3]);
  }

  protected void addChild(PBox child) {
    children.add(child);
  }

  /**
   * Returns the height of this Container on the first pages it occupies.
   * Usually only used with headers and footers. Does only work if the
   * container is already layouted.
   * 
   * @return int
   */
  protected int getHeight() {
    if (calculatedHeight == -1)
      return 0;
    return calculatedHeight;
  }

  protected void layoutResetTuning() {
    calculatedHeight = -1;
    for (Iterator iter = children.iterator(); iter.hasNext();) {
      PBox element = (PBox) iter.next();
      element.layoutResetTuning();
    }
  }

  /*
   * Gibt die Anzahl der gefullten Seiten zuruck.
   */
  protected int layout(int maxHeightForFirstPage, int maxHeightForOtherPages) {
    int currentPage = 1;
    Point currentOrgin = new Point(0, 0);
    Point spaceLeft = new Point(PBox.pixelX(getPossibleWidth()),
        maxHeightForFirstPage);

    resetElementIndex();
    List currentRow = getNextRow();

    // Schleife uber alle Elemente in "Zeilen"
    while (currentRow != null) {
      boolean pageBreakNeccessary;
      rowHorizontalLayout(currentRow, spaceLeft);

      do {
        pageBreakNeccessary = rowVerticalLayout(currentRow,
            currentOrgin, spaceLeft, currentPage);
        if (currentPage == 1 && calculatedHeight < currentOrgin.y)
          calculatedHeight = currentOrgin.y;
        if (pageBreakNeccessary) {
          currentPage++;
          PPageNumber.pageNumber++;
          currentOrgin = new Point(0, 0);
          spaceLeft = new Point(PBox.pixelX(getPossibleWidth()),
              maxHeightForOtherPages);
        }
      } while (pageBreakNeccessary);

      currentRow = getNextRow();
    }
    return currentPage;

  }

  /*
   * Gibt true zuruck, wenn ein Seitenumbruch notig ist. Wird in diesem Falle
   * von layout() nochmal fur die gleiche Zeile aber die nachste Seite
   * aufgerufen.
   */
  private boolean rowVerticalLayout(List row, Point origin, Point spaceLeft,
      int page) {
    int max = 0;
    boolean pageBreakNeccessary = false;
    boolean allOnNextPage = false;

    origin.x = 0;

    // Hohen verarbeiten
    for (Iterator iter = row.iterator(); iter.hasNext();) {
      PBox element = (PBox) iter.next();
      if (element instanceof PPageBreak)
        break;

      int height = element.layoutHowMuchWouldYouOccupyOf(spaceLeft, page);
      if (!element.layoutWouldYouFinishWithin(spaceLeft, page))
        pageBreakNeccessary = true;
      if (height < 0) {
        allOnNextPage = true;
        max = 0;
        break;
      } else if (height > max)
        max = height;
    }

    if (!allOnNextPage) {
      for (Iterator iter = row.iterator(); iter.hasNext();) {
        PBox element = (PBox) iter.next();
        if (element instanceof PPageBreak) {
          max = spaceLeft.y;
          break;
        }
        element.layoutOccupy(origin, spaceLeft, page);
        if (element.rowAlign)
          element.setForcedHeight(max);
        origin.x += element.getWidth();
      }
      origin.y += max;
      spaceLeft.y -= max;
      origin.x = 0;
      return pageBreakNeccessary;
    }
    return true;
  }

  // Breiten berechnen und setzen
  private void rowHorizontalLayout(List row, Point spaceLeft) {
    int numOfGrabbingElements = 0;
    int widthLeft = spaceLeft.x;

    for (Iterator iter = row.iterator(); iter.hasNext();) {
      PBox element = (PBox) iter.next();
      if (element.grabbing)
        numOfGrabbingElements++;
      else
        widthLeft -= element.getWidth();
    }

    if (widthLeft < 0)
      widthLeft = 0; // bad practice, but easy...
    if (numOfGrabbingElements > 0) {
      int grabWidth = widthLeft / numOfGrabbingElements;

      for (Iterator iter = row.iterator(); iter.hasNext();) {
        PBox element = (PBox) iter.next();
        if (element.grabbing)
          element.grabWidth = grabWidth;
      }
    }

  }

  private int elementIndex;

  private List getNextRow() {
    if (elementIndex == children.size())
      return null;
    int last = elementIndex;
    int save = elementIndex;

    boolean firstRun = true;

    for (; last < children.size(); last++) {
      PBox element = (PBox) children.get(last);
      if (!firstRun && element.below)
        break;
      firstRun = false;
    }
    elementIndex = last;
    return children.subList(save, last);
  }

  private void resetElementIndex() {
    elementIndex = 0;
  }

  public void draw(int page, Point origin) {
    for (Iterator iter = children.iterator(); iter.hasNext();) {
      PBox element = (PBox) iter.next();
      if (element.layoutIsOnPage(page)) {
        element.draw(page, origin);
      }
    }
  }

}