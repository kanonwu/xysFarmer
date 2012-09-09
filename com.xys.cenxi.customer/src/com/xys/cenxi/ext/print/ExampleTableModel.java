package com.xys.cenxi.ext.print;

import org.eclipse.swt.graphics.Point;

import de.kupzog.ktable.KTableCellEditor;
import de.kupzog.ktable.KTableCellRenderer;
import de.kupzog.ktable.KTableModel;

/**
 * This is an example KTableModel that can be printed using the
 * PrintKTableExample.
 * 
 * @author Kupzog
 */

public class ExampleTableModel implements KTableModel {
  private final static String[][] content = {
      { "Name", "Kupzog", "Hansson", "Walter", "Hutton" },
      { "Town", "Cologne", "Ystadt", "London", "Brighton" },
      { "Interest", "programming", "hunting", "rafting", "painting" } };

  public Object getContentAt(int col, int row) {
    return content[col][row];
  }

  public KTableCellEditor getCellEditor(int col, int row) {
    return null;
  }

  public void setContentAt(int col, int row, Object value) {
  }

  public int getRowCount() {
    return 5;
  }

  public int getFixedRowCount() {
    return 1;
  }

  public int getColumnCount() {
    return 3;
  }

  public int getFixedColumnCount() {
    return 0;
  }

  public int getColumnWidth(int col) {
    return 130;
  }

  public boolean isColumnResizable(int col) {
    return false;
  }

  public void setColumnWidth(int col, int value) {
  }

  public int getRowHeight() {
    return 20;
  }

  public int getFirstRowHeight() {
    return 20;
  }

  public boolean isRowResizable() {
    return false;
  }

  public int getRowHeightMinimum() {
    return 20;
  }

  public void setRowHeight(int value) {
  }

  public KTableCellRenderer getCellRenderer(int col, int row) {
    return null;
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