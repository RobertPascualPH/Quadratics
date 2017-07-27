/* *****************************************************
 * InterpolationTab.java
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

public class InterpolationTab {
    private static int selectSema;       // Initially not selected.
    private static TextField Afld;
    private static TextField Bfld;
    private static TextField Cfld;
    private static TextField numPoints;
    private static int npoints=3;
    private static double xC[];   // x coordinate
    private static double yC[];   // y coordinate
    private static int pointsRead = 0;
    public static double Acoeff;
    public static double Bcoeff;
    public static double Ccoeff;

    public static void readPoint() {
        double x, y;

        if (pointsRead < 3) {
            x = Double.parseDouble(QuadMainFX.input1.getText());
            y = Double.parseDouble(QuadMainFX.input2.getText());
            xC[pointsRead] = x;
            yC[pointsRead] = y;
            QuadMainFX.textarea.print(
                String.format("Point %d: (%10.2f, %10.2f)\n", pointsRead, 
                    xC[pointsRead], yC[pointsRead]));
            pointsRead++;
            QuadMainFX.clearInput();
        }

        if (pointsRead == 3) {  // Compute using three point interpolation.
            Acoeff = (yC[0] - yC[1])/(xC[0] - xC[1]) - (yC[0] - yC[2])/(xC[0] - xC[2]);
            Acoeff = Acoeff/(xC[1] - xC[2]);
            Bcoeff = (yC[0] - yC[1])/(xC[0] - xC[1]) - Acoeff*(xC[0] + xC[1]);
            Ccoeff = yC[0] - Bcoeff*xC[0] - Acoeff*xC[0]*xC[0];
            QuadMainFX.textarea.print(
                String.format("Coefficient vector = (%-14.5f, %-14.5f, %-14.5f)\n",
                              Acoeff, Bcoeff, Ccoeff));
        }
    }

    public static void goBtnPressed() {
        npoints = Integer.parseInt(numPoints.getText());
        pointsRead = 0;
        QuadMainFX.clearInput();
        QuadMainFX.textarea.print(
             String.format("Enter %d points (x,y) using the input fields below.\n", 
                 npoints));
        QuadMainFX.readbtn.setOnAction(e -> InterpolationTab.readPoint());
    }

    public static void tabSelected() {
        selectSema = 1 - selectSema;

        if (selectSema == 1) { // If this Tab is selected.
            QuadMainFX.textarea.print("Polynomial Interpolation\n");
            QuadMainFX.goButton.setOnAction(e->InterpolationTab.goBtnPressed());
            QuadMainFX.fn1Button.setOnAction(e->QuadMainFX.noActionDefined());
                QuadMainFX.fn1Button.setText("Fn1");
            QuadMainFX.fn2Button.setOnAction(e->QuadMainFX.noActionDefined());
                QuadMainFX.fn2Button.setText("Fn2");
            QuadMainFX.readbtn.setOnAction(e->QuadMainFX.noActionDefined());
        }
        else {
            QuadMainFX.textarea.print("\n");
        }
        QuadMainFX.fn3Button.setOnAction(e->QuadMainFX.about());
            QuadMainFX.fn3Button.setText("About");
    }

    public static void initialize(Tab parTab) {
        GridPane tabpane = new GridPane();
        parTab.setContent(tabpane);
        parTab.setOnSelectionChanged(e -> tabSelected());

        Text txtTitle = new Text("Polynomial Interpolation");
        tabpane.add(txtTitle, 0, 0);

        Text txtInstruct = new Text("Select number of points to interpolate then press Go");
        tabpane.add(txtInstruct, 0,1 );

        Text txtLbl1 = new Text("Number of points to interpolate");
            tabpane.add(txtLbl1, 0, 2);  // Col = 0; Row = 1
            numPoints = new TextField();
            numPoints.setText("3");
            tabpane.add(numPoints, 1, 2);
        selectSema = 0; // Initially not selected.
        xC = new double[3];
        yC = new double[3];
    }
}

