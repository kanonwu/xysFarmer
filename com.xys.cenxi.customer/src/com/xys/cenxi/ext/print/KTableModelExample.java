package com.xys.cenxi.ext.print;

import java.util.HashMap;

import org.eclipse.swt.graphics.Point;

import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableModel;
import de.kupzog.ktable.editors.KTableCellEditorCombo;
import de.kupzog.ktable.editors.KTableCellEditorText;

/**
 * @author Friederich Kupzog
 */
public class KTableModelExample implements KTableModel {

  private int[] colWidths;

  private int rowHeight;

  private HashMap content;

  /**
   * 
   */
  public KTableModelExample() {
    colWidths = new int[getColumnCount()];
    for (int i = 0; i < colWidths.length; i++) {
      colWidths[i] = 270;
    }
    rowHeight = 18;
    content = new HashMap();
  }

  // Inhalte

  public Object getContentAt(int col, int row) {
    // System.out.println("col "+col+" row "+row);
    String erg = (String) content.get(col + "/" + row);
    if (erg != null)
      return erg;
    return col + "/" + row;
  }

  /*
   * overridden from superclass
   */
  public KTableCellEditor getCellEditor(int col, int row) {
    if (col % 2 == 0) {
      KTableCellEditorCombo e = new KTableCellEditorCombo();
      e
          .setItems(new String[] { "First text", "Second text",
              "third text" });
      return e;
    } else
      return new KTableCellEditorText();
  }

  /*
   * overridden from superclass
   */
  public void setContentAt(int col, int row, Object value) {
    content.put(col + "/" + row, value);
    //
  }

  // Umfang

  public int getRowCount() {
    return 100;
  }

  public int getFixedRowCount() {
    return 2;
  }

  public int getColumnCount() {
    return 100;
  }

  public int getFixedColumnCount() {
    return 1;
  }

  // GroBen

  public int getColumnWidth(int col) {
    return colWidths[col];
  }

  public int getRowHeight() {
    return rowHeight;
  }

  public boolean isColumnResizable(int col) {
    return true;
  }

  public int getFirstRowHeight() {
    return 22;
  }

  public boolean isRowResizable() {
    return true;
  }

  public int getRowHeightMinimum() {
    return 18;
  }

  public void setColumnWidth(int col, int value) {
    colWidths[col] = value;
  }

  public void setRowHeight(int value) {
    if (value < 2)
      value = 2;
    rowHeight = value;
  }

  // Rendering

  public KTableCellRenderer getCellRenderer(int col, int row) {
    return KTableCellRenderer.defaultRenderer;
  }

@Override
public Point belongsToCell(int arg0, int arg1) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int getFixedHeaderColumnCount() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int getFixedHeaderRowCount() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int getFixedSelectableColumnCount() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int getFixedSelectableRowCount() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int getRowHeight(int arg0) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public String getTooltipAt(int arg0, int arg1) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isRowResizable(int arg0) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void setRowHeight(int arg0, int arg1) {
	// TODO Auto-generated method stub
	
}

}