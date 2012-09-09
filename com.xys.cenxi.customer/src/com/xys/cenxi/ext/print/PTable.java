package com.xys.cenxi.ext.print;

import de.kupzog.ktable.KTableModel;

/**
 * Allows to print KTable objects.
 * 
 * You have to specify a KTableModel and a PTableBoxProvider.
 * 
 * @author Friederich Kupzog
 */
class PTable {

  protected KTableModel model;

  protected PTableBoxProvider boxProvider;

  protected PContainer parent;

  public PTable(PContainer parent) {
    this.parent = parent;
  }

  protected void fillDocument() {
    boolean abgeschnitten = false;
    // Zeilen
    for (int i = 0; i < model.getRowCount(); i++) {
      // System.out.println("Spalte "+i);
      int height = model.getRowHeight(i);
      double width = parent.getPossibleWidth();

      // Spalten
      for (int j = 0; j < model.getColumnCount(); j++) {
        // System.out.println(" Zeile "+j);
        int style = PBox.POS_RIGHT | PBox.ROW_ALIGN;
        if (j == 0)
          style = PBox.POS_BELOW | PBox.ROW_ALIGN;

        PBox box = boxProvider.createBox(parent, style, j, i, model
            .getColumnWidth(j), height,
            (model.getFixedHeaderColumnCount() > j || model
                .getFixedHeaderRowCount() > i), model.getContentAt(j,
                i));
        double boxWidth = Math.max(box.minCm, parent.getPossibleWidth()
            * box.hWeight);
        width -= boxWidth;
        if (width < 0) {
          box.dispose();
          abgeschnitten = true;
          break;
        }
      }
    }
    if (abgeschnitten)
      MsgBox.show("Tabelle ist zu breit fur die Seite\n"
          + "und wird deshalb abgeschnitten.");

  }

  /**
   * @return PTableBoxProvider
   */
  public PTableBoxProvider getBoxProvider() {
    return boxProvider;
  }

  /**
   * @return KTableModel
   */
  public KTableModel getModel() {
    return model;
  }

  /**
   * Sets the boxProvider.
   * 
   * @param boxProvider
   *            The boxProvider to set
   */
  public void setBoxProvider(PTableBoxProvider boxProvider) {
    this.boxProvider = boxProvider;
    if (this.boxProvider != null && this.model != null) {
      fillDocument();
    }
  }

  /**
   * Sets the model.
   * 
   * @param model
   *            The model to set
   */
  public void setModel(KTableModel model) {
    this.model = model;
    if (this.boxProvider != null && this.model != null) {
      fillDocument();
    }
  }

}