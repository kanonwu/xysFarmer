package com.xys.cenxi.ext.print;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * Used by MsgBox.
 * 
 * @author Friederich Kupzog
 */

class ButtonBar extends Composite {

  private RowLayout myLayout;

  private ArrayList myButtons;

  private int myButtonWidth;

  /** Erzeugt neuen ButtonBar */
  public ButtonBar(Composite owner, int buttonWidth) {
    super(owner, SWT.NONE);
    myButtonWidth = buttonWidth;
    myLayout = new RowLayout();
    myLayout.justify = true;
    myLayout.type = SWT.HORIZONTAL;
    myLayout.wrap = true;
    myLayout.spacing = 4;
    this.setLayout(myLayout);
    myButtons = new ArrayList();
  }

  /**
   * Fugt einen Button zur Leiste hinzu. Gibt eine Referenz auf den angelegten
   * Button zuruck.
   */
  public Button addButton(String name, String toolTip,
      SelectionListener selListener) {
    Button b = new Button(this, SWT.PUSH);
    b.setText(name);
    b.setToolTipText(toolTip);
    b.setLayoutData(new RowData(myButtonWidth, 25));
    if (selListener != null)
      b.addSelectionListener(selListener);
    myButtons.add(b);
    return b;
  }

  /**
   * Fugt einen Button zur Leiste hinzu, und registriert ihn bei der in
   * myShell ubergebenen Shell als DefaultButton.
   */
  public Button addButton(String name, String toolTip, Shell myShell,
      SelectionListener selListener) {
    Button b = addButton(name, toolTip, selListener);
    myShell.setDefaultButton(b);
    return b;
  }

}