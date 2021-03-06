/* *****************************************************
 * ParabolicTab.java
 *
 * *****************************************************/
package ph.mmhsvictoria.apps.quadratics;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ParabolicTab {
    private static int selectSema;       // Initially selected since on top.
    private static TextField Afld;
    private static TextField Bfld;
    private static TextField Cfld;
    private static double a = 0.00;
    private static double b = 0.00;
    private static double c = 0.00;

    public static void clear() {
        Afld.setText("");
        Bfld.setText("");
        Cfld.setText("");
    }

    public static void findZeros() {
        a = Double.parseDouble(Afld.getText());
        b = Double.parseDouble(Bfld.getText());
        c = Double.parseDouble(Cfld.getText());

        double discr = b*b - 4*a*c;
        QuadMainFX.textarea.print(
                String.format("Discriminant = (%-12.4f)\n", discr));
        if (discr >= 0) {
            double x1 = (-b + Math.sqrt(discr))/(2*a);
            double x2 = (-b - Math.sqrt(discr))/(2*a);
            QuadMainFX.textarea.print(
                String.format("Real roots   = (%-12.4f, %-12.4f)\n", x1, x2));
        }
        else {
            QuadMainFX.textarea.print("No real roots.\n");
        }

        if (a != 0) {
            double vx = -b/(2*a);
            double vy = (a*vx + b)*vx + c;
            QuadMainFX.textarea.print(
                String.format("Vertex       = (%-12.4f, %-12.4f)\n", vx, vy));
        }
    }

    public static void funcEval() {
        a = Double.parseDouble(Afld.getText());
        b = Double.parseDouble(Bfld.getText());
        c = Double.parseDouble(Cfld.getText());

        double xinp = Double.parseDouble(QuadMainFX.input1.getText());
        double res = (a*xinp + b)*xinp + c;
        QuadMainFX.textarea.print(String.format("F(%12.4f) = %12.4f\n", xinp, res));
    }

    public static void func1BtnPressed() {
        QuadMainFX.input1.setText("");
        QuadMainFX.input2.setText("Input 2");
        QuadMainFX.input3.setText("Input 3");

        QuadMainFX.textarea.print("Enter a value for x in input1 below then press Enter.\n");
        QuadMainFX.textarea.print("Input 2 and Input 3 will be ignored.\n");
        QuadMainFX.readbtn.setOnAction(e -> funcEval());
    }

    public static void copyInterpolationTab() {
        double avf = InterpolationTab.Acoeff;
        double bvf = InterpolationTab.Bcoeff;
        double cvf = InterpolationTab.Ccoeff;

        Afld.setText(String.format("%-12.4f", avf));
        Bfld.setText(String.format("%-12.4f", bvf));
        Cfld.setText(String.format("%-12.4f", cvf));
    }

    public static void func3BtnPressed() {
        QuadMainFX.textarea.print("No function defined for button Fn3!\n");
    }

    public static void prepFuncButtons() {
	QuadMainFX.goButton.setOnAction(e->ParabolicTab.findZeros());
	QuadMainFX.fn1Button.setOnAction(e->ParabolicTab.func1BtnPressed());
	    QuadMainFX.fn1Button.setText("f(x)");
	QuadMainFX.fn2Button.setOnAction(e->ParabolicTab.copyInterpolationTab());
	    QuadMainFX.fn2Button.setText("CFInt");
	QuadMainFX.fn3Button.setOnAction(e->ParabolicTab.clear());
	    QuadMainFX.fn3Button.setText("Clr");
    }

    public static void tabSelected() {
        selectSema = 1 - selectSema;

        if (selectSema == 1) { // If this Tab is entered.
            QuadMainFX.textarea.print("Parabolic Functions\n");
            prepFuncButtons();
        }
        else {   // If tab is exited.
            QuadMainFX.textarea.print("\n");
        }
    }

    public static void initialize(Tab parTab) {
        GridPane tabpane = new GridPane();
        parTab.setContent(tabpane);
        parTab.setOnSelectionChanged(e -> tabSelected());

        // Set constraunts on the first column. Note that each succeeding
        // addition to the list of column constraints applies that
        // constraints to succeeding columns.

        tabpane.getColumnConstraints().add(new ColumnConstraints(50));   // Applies to column 0
        tabpane.getColumnConstraints().add(new ColumnConstraints(100));   // Applies to column 1 

        Text txt1 = new Text("Enter values for the coefficient and press Go.");
            txt1.getStyleClass().add("normal-text");
            tabpane.add(txt1, 0, 0, 2, 1);

        Text Albl = new Text("A");
            Albl.getStyleClass().add("field-labels");
            tabpane.add(Albl, 0, 1);  // Col = 0; Row = 1
            Afld = new TextField();
            Afld.setText("");
            tabpane.add(Afld, 1, 1);

        Text Blbl = new Text("B");
            Blbl.getStyleClass().add("field-labels");
            tabpane.add(Blbl, 0, 2);  // Col = 0; Row = 2
            Bfld = new TextField();
            Bfld.setText("");
            tabpane.add(Bfld, 1, 2);

        Text Clbl = new Text("C");
            Clbl.getStyleClass().add("field-labels");
            tabpane.add(Clbl, 0, 3);
            Cfld = new TextField();
            Cfld.setText("");
            tabpane.add(Cfld, 1, 3);

        ParabolicTab.prepFuncButtons();
        selectSema = 1;
    }
}

