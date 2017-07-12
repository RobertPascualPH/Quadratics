/* *******************************************************
 * QuadMainFX.java
 *
 *
 * *******************************************************/
package ph.mmhsvictoria.apps.quadratics;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuadMainFX extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override public void start(Stage primaryStage) {

        // Create BorderPane and set as the root Scene.

        BorderPane bpane = new BorderPane();
        Scene scene = new Scene(bpane);


        // Place desired elements on the Top of BorderPane.

        VBox topPanel = new VBox();
        bpane.setTop(topPanel);

        Text programName = new Text("The Conic Sections");
        topPanel.getChildren().add(programName);

        TabPane tabpane = new TabPane();

        // Create the Tab for determining the properties of
        // the parabolic Functions.
        Tab qftab = new Tab("Parabolic Function");
        tabpane.getTabs().add(qftab);


        // Create the Tab for the elliptic function.
        Tab eltab = new Tab("Ellipse");
        tabpane.getTabs().add(eltab);


        // Create the Tab for the elliptic function.
        Tab hyptab = new Tab("Hyperbolic");
        tabpane.getTabs().add(hyptab);


        // Create the Tab for parabolic interpolation.
        Tab qitab = new Tab("Parabolic Interpolation");
        tabpane.getTabs().add(qitab);


        topPanel.getChildren().add(tabpane);


        // The Function Buttons
        HBox funcBox = new HBox();
        topPanel.getChildren().add(funcBox);

        Button goButton = new Button("Go");
        Button fn1Button = new Button("Fn1");
        Button fn2Button = new Button("Fn2");
        Button fn3Button = new Button("Fn3");

        funcBox.getChildren().addAll(goButton, fn1Button, fn2Button, fn3Button);

 
        // At the bottom we put in the various buttons

        HBox bottomHBox = new HBox();
        Button qButton = new Button("Quit");
        qButton.setOnAction(e->Platform.exit());
        Button cButton = new Button("Clear");
        bottomHBox.getChildren().addAll(qButton, cButton);

        bpane.setBottom(bottomHBox);

        // Prepare and show the primaryStage

        primaryStage.setScene(scene);
        primaryStage.setTitle("Conics Solver");
        primaryStage.show();


    }
}

