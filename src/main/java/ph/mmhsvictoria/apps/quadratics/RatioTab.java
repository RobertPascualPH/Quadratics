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

public class RatioTab {
    private static int selectSema;       // Initially selected since on top.
    private static TextField predictorFld;
    private static TextField outcomeFld;
    private static TextField constPFld;
    private static double predictor;
    private static double outcome;
    private static double constP;

    public static void clear() {
        predictorFld.setText("");
        outcomeFld.setText("");
        constPFld.setText("");
    }


    public static void computeRatios() {
        int sel;

        String inp = predictorFld.getText();
        String inq = outcomeFld.getText();
        String inr = constPFld.getText();

        if (inp.length() == 0) {
            outcome = Double.parseDouble(inq);
            constP = Double.parseDouble(inr);
            QuadMainFX.textarea.print("Direct Variation.\n");
            QuadMainFX.textarea.print(
                String.format("    Predictor = %12.4f\n", outcome/constP));
            QuadMainFX.textarea.print(
                String.format("    Constant  = %12.4f\n", constP));
            QuadMainFX.textarea.print(
                String.format("    Outcome   = %12.4f\n", outcome));

            QuadMainFX.textarea.print("Inverse Variation.\n");
            QuadMainFX.textarea.print(
                String.format("    Predictor = %12.4f\n", constP/outcome));
            QuadMainFX.textarea.print(
                String.format("    Constant  = %12.4f\n", predictor*outcome));
            QuadMainFX.textarea.print(
                String.format("    Outcome   = %12.4f\n", outcome));
        }
        else if (inr.length() == 0) {
            predictor = Double.parseDouble(inp);
            outcome = Double.parseDouble(inq);
            QuadMainFX.textarea.print("Direct Variation.\n");
            QuadMainFX.textarea.print(
                String.format("    Predictor = %12.4f\n", predictor));
            QuadMainFX.textarea.print(
                String.format("    Constant  = %12.4f\n", outcome/predictor));
            QuadMainFX.textarea.print(
                String.format("    Outcome   = %12.4f\n", outcome));

            QuadMainFX.textarea.print("Inverse Variation.\n");
            QuadMainFX.textarea.print(
                String.format("    Predictor = %12.4f\n", predictor));
            QuadMainFX.textarea.print(
                String.format("    Constant  = %12.4f\n", predictor*outcome));
            QuadMainFX.textarea.print(
                String.format("    Outcome   = %12.4f\n", outcome));
        }
        else if (inq.length() == 0) {
            predictor = Double.parseDouble(inp);
            constP = Double.parseDouble(inr);
            QuadMainFX.textarea.print("Direct Variation.\n");
            QuadMainFX.textarea.print(
                String.format("    Predictor = %12.4f\n", predictor));
            QuadMainFX.textarea.print(
                String.format("    Constant  = %12.4f\n", constP));
            QuadMainFX.textarea.print(
                String.format("    Outcome   = %12.4f\n", predictor*constP));

            QuadMainFX.textarea.print("Inverse Variation.\n");
            QuadMainFX.textarea.print(
                String.format("    Predictor = %12.4f\n", predictor));
            QuadMainFX.textarea.print(
                String.format("    Constant  = %12.4f\n", constP));
            QuadMainFX.textarea.print(
                String.format("    Outcome   = %12.4f\n", constP/predictor));
        }
    }

    public static void func1BtnPressed() {
        QuadMainFX.input1.setText("");
        QuadMainFX.input2.setText("Input 2");
        QuadMainFX.input3.setText("Input 3");

        QuadMainFX.textarea.print("Enter a value for x in input1 below then press Enter.\n");
        QuadMainFX.textarea.print("Input 2 and Input 3 will be ignored.\n");
    }


    public static void func3BtnPressed() {
        QuadMainFX.textarea.print("No function defined for button Fn3!\n");
    }

    public static void prepFuncButtons() {
	QuadMainFX.goButton.setOnAction(e->RatioTab.computeRatios());
	QuadMainFX.fn1Button.setOnAction(e->RatioTab.clear());
	    QuadMainFX.fn1Button.setText("Clr");
	QuadMainFX.fn2Button.setOnAction(e->QuadMainFX.noActionDefined());
	    QuadMainFX.fn2Button.setText("Fn2");
	QuadMainFX.fn3Button.setOnAction(e->QuadMainFX.noActionDefined());
	    QuadMainFX.fn3Button.setText("Fn3");
    }

    public static void tabSelected() {
        selectSema = 1 - selectSema;

        if (selectSema == 1) { // If this Tab is entered.
            QuadMainFX.textarea.print("Ratio Calculations\n");
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

        tabpane.getColumnConstraints().add(new ColumnConstraints(150));   // Applies to column 0
        tabpane.getColumnConstraints().add(new ColumnConstraints(150));   // Applies to column 1 

        Text txt1 = new Text("Enter values into any two fields below.\nDo not enter values into all three fields.");
        txt1.getStyleClass().add("normal-text");
        tabpane.add(txt1, 0, 0, 2, 1);

        Text Albl = new Text("Predictor");
            Albl.getStyleClass().add("field-labels");
            tabpane.add(Albl, 0, 1);  // Col = 0; Row = 1
            predictorFld = new TextField();
            predictorFld.setText("");
            tabpane.add(predictorFld, 1, 1);

        Text Clbl = new Text("const k");
            Clbl.getStyleClass().add("field-labels");
            tabpane.add(Clbl, 0, 2);
            constPFld = new TextField();
            constPFld.setText("");
            tabpane.add(constPFld, 1, 2);

        Text Blbl = new Text("Outcome");
            Blbl.getStyleClass().add("field-labels");
            tabpane.add(Blbl, 0, 3);  // Col = 0; Row = 3
            outcomeFld = new TextField();
            outcomeFld.setText("");
            tabpane.add(outcomeFld, 1, 3);

        selectSema = 0;
        RatioTab.prepFuncButtons();
    }
}

