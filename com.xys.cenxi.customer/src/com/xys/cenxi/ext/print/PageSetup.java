package com.xys.cenxi.ext.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * A static data storage and a GUI dialog to change the data. PDocument uses the
 * settings of the static variables in PageSetup. You can open a Dialog to
 * changes these values by creating a PageSetup and call the open() function.
 * 
 * @author Friederich Kupzog
 */
class PageSetup extends KDialog {
  protected Composite root;

  private Combo combFormat, cmbScalierung, cmbMargin;

  private Button butPortrait, butLandscape;

  public final static int MARGIN_SMALL = 0;

  public final static int MARGIN_MEDIUM = 1;

  public final static int MARGIN_HUGE = 2;

  public final static String[] formatNames = { "A3", "A4", "A5" };

  public final static String[] scalings = { "100%", "90%", "80%", "70%",
      "60%", "50%" };

  public static double paperHeight = 29.6;

  public static double paperWidth = 20.6;

  public static String format = "A4";

  public static boolean portrait = true;

  public static int scaling = 100;

  public static int marginStyle = MARGIN_MEDIUM;

  public PageSetup(Shell parent) {
    super(parent, "Seite einrichten", IconSource.getImage("print"));
    createContents();

    setDialogImage(IconSource.getImage("SeiteEinrichten"));
    addButtonRight("OK", "", true);
    addButtonRight("Abbrechen", "");
    combFormat.setText(format);
  }

  public int getShellStyle() {
    return SWT.CLOSE | SWT.APPLICATION_MODAL;
  }

  protected void createContents() {
    guiMainArea.setLayout(new FillLayout());
    root = new Composite(guiMainArea, SWT.NONE);
    final GridLayout gridLayout = new GridLayout();
    gridLayout.verticalSpacing = 10;
    gridLayout.numColumns = 2;
    root.setLayout(gridLayout);

    {
      final Label l = new Label(root, SWT.NONE);
      l.setText("Papierformat:");
      final GridData gridData_2 = new GridData();
      gridData_2.widthHint = 80;
      l.setLayoutData(gridData_2);
    }
    {
      combFormat = new Combo(root, SWT.BORDER | SWT.READ_ONLY);
      combFormat
          .setToolTipText("Bestimmt die PapiergroBe. Diese muss mit der Druckereinstellung ubereinstimmen.");
      for (int i = 0; i < formatNames.length; i++) {
        combFormat.add(formatNames[i]);
      }
      combFormat.setText(format);

      final GridData gridData_1 = new GridData(GridData.FILL_HORIZONTAL);
      gridData_1.widthHint = 180;
      combFormat.setLayoutData(gridData_1);
    }
    {
      final Label label = new Label(root, SWT.NONE);
      label.setText("Seitenrander:");
      label.setLayoutData(new GridData(GridData.FILL_BOTH));
    }
    {
      cmbMargin = new Combo(root, SWT.READ_ONLY);
      cmbMargin.setToolTipText("Bestimmt die Breite der Rander.");
      cmbMargin.add("Schmale Rander");
      cmbMargin.add("Normale Rander");
      cmbMargin.add("Breite Rander");
      cmbMargin.select(marginStyle);
      cmbMargin.setLayoutData(new GridData(GridData.FILL_BOTH));
    }
    {
      final Label label = new Label(root, SWT.NONE);
      final GridData gridData = new GridData(
          GridData.VERTICAL_ALIGN_BEGINNING);
      gridData.horizontalSpan = 1;
      label.setLayoutData(gridData);
      label.setText("Ausrichtung:");
    }
    {
      butPortrait = new Button(root, SWT.RADIO);
      butPortrait
          .setToolTipText("Bestimmt, ob das Papier hochkant oder Breit bedruckt werden soll. \nDiese Einstellung muss mit der des Druckers ubereinstimmen");
      butPortrait.setLayoutData(new GridData(
          GridData.VERTICAL_ALIGN_BEGINNING));
      butPortrait.setText("Hochformat");
      butPortrait.setSelection(portrait);
    }
    {
      final Label label = new Label(root, SWT.NONE);
    }
    {
      butLandscape = new Button(root, SWT.RADIO);
      butLandscape.setLayoutData(new GridData(
          GridData.VERTICAL_ALIGN_BEGINNING));
      butLandscape.setText("Breitformat");
      butLandscape.setSelection(!portrait);
      butLandscape
          .setToolTipText("Bestimmt, ob das Papier hochkant oder quer bedruckt werden soll. \nDiese Einstellung muss mit der des Druckers ubereinstimmen");
    }
    {
      final Label label = new Label(root, SWT.NONE);
      label.setText("Skalierung:");
      label.setLayoutData(new GridData(GridData.FILL_BOTH));
    }
    {
      cmbScalierung = new Combo(root, SWT.READ_ONLY);
      cmbScalierung.setItems(scalings);
      cmbScalierung.select(10 - (scaling / 10));
      cmbScalierung.setLayoutData(new GridData(GridData.FILL_BOTH));
      cmbScalierung
          .setToolTipText("Hiermit konnen Sie dir GroBe des Ausdrucks veringern, so daB mehr auf eine Seite passt.");
    }

  }

  /*
   * overridden from superclass
   */
  protected void onButton(Button button, String buttonText) {
    if (buttonText.equals("OK")) {
      saveSettings();
    }
    close();
  }

  protected void saveSettings() {
    format = combFormat.getText();
    scaling = 100 - 10 * (cmbScalierung.getSelectionIndex());
    marginStyle = cmbMargin.getSelectionIndex();

    portrait = butPortrait.getSelection();

    if (portrait) {
      paperHeight = getPaperHeightInCm(format);
      paperWidth = getPaperWidthInCm(format);
    } else {
      paperWidth = getPaperHeightInCm(format);
      paperHeight = getPaperWidthInCm(format);
    }

  }

  public static double getPaperHeightInCm(String formatName) {
    if (formatName.equals("A5")) {
      return 20.8;
    } else if (formatName.equals("A4")) {
      return 29.6;
    } else if (formatName.equals("A3")) {
      return 41.6;
    }
    return 1.0;
  }

  public static double getPaperWidthInCm(String formatName) {
    if (formatName.equals("A5")) {
      return 14.8;
    } else if (formatName.equals("A4")) {
      return 20.6;
    } else if (formatName.equals("A3")) {
      return 29.6;
    }
    return 1.0;
  }

}