package com.xys.cenxi.ext.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;

/**
 * This example shows the basic use of KPrint classes by creating a little 9 x
 * 13 cm greeting card.
 * 
 * @author Friederich Kupzog
 * 
 */
class GreetingCardExample {
  private Display d;

  public GreetingCardExample() {

    d = new Display();

    // create an example document 13 cm x 9 cm with 1 cm borders
    PDocument doc = new PDocument(13, 9, 1, 1, 1, 1, 1.0, "Greeting Card");

    // put some text on it
    PTextBox t;

    t = new PTextBox(doc);
    t.setText("MANY GREETINGS FROM KPRINT");

    new PVSpace(doc, 0.1);
    new PHLine(doc, 0.2, SWT.COLOR_DARK_GREEN);
    new PVSpace(doc, 0.5);

    t = new PTextBox(doc, PBox.POS_BELOW, 0, 5);
    t
        .setText("We spent a nice time in this 5cm wide left adjusted Textbox.");

    new PHSpace(doc, PBox.POS_RIGHT, 0.3);

    t = new PTextBox(doc, PBox.POS_RIGHT, 0, 5);
    t
        .setText("The climate was fine and we had a lot of bright views. The class hierachie is unforgetable. Bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla.");

    new PVSpace(doc, 1);

    t = new PTextBox(doc, PBox.POS_BELOW, 1.0, 0);
    t.setText("See you soon: your personal swt programmer on holiday.");
    // Border lines are counted clockwise starting at the top.
    t.getBoxStyle().lines[0] = 0.05; // top
    t.getBoxStyle().lines[2] = 0.05; // bottom
    t.getBoxStyle().lineColor = SWT.COLOR_DARK_GREEN;

    // start a new Page
    new PPageBreak(doc);

    // some more text
    new PHSpace(doc, PBox.POS_BELOW | PBox.GRAB, 0);
    t = new PTextBox(doc, PBox.POS_RIGHT, 0, 1.3);
    t.setText("Affix stamp here");
    t.getBoxStyle().lines[0] = 0.01;
    t.getBoxStyle().lines[1] = 0.01;
    t.getBoxStyle().lines[2] = 0.01;
    t.getBoxStyle().lines[3] = 0.01;
    t.getTextStyle().setMarginBottom(0.1);
    t.getTextStyle().setMarginTop(0.1);
    t.getTextStyle().setMarginLeft(0.1);
    t.getTextStyle().setMarginRight(0.1);

    // open the print preview on this document
    PrintPreview pr = new PrintPreview(null, "Test", IconSource
        .getImage("print"), doc);
    pr.open();

    // dispose the document in the end
    d.dispose();
  }

  /**
   * This function would print the document witout the print preview.
   * 
   * @param doc
   */
  public void print(PDocument doc) {
    PrintDialog dialog = new PrintDialog(null, SWT.BORDER);
    PrinterData data = dialog.open();
    if (data == null)
      return;
    if (data.printToFile) {
      data.fileName = "print.out"; // you probably want to ask the user
      // for a filename
    }

    Printer printer = new Printer(data);
    GC gc = new GC(printer);
    PBox.setParameters(gc, printer, printer.getDPI(), 100);
    if (printer.startJob("DoSys Druckauftrag")) {
      printer.startPage();
      doc.layout();
      doc.draw(1);
      printer.endJob();
    }
    gc.dispose();

  }

  public static void main(String[] args) {
    new GreetingCardExample();
  }
}