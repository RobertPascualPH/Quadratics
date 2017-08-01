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
    public static ScrollPane scrollpane;
    public static TextField input1;
    public static TextField input2;
    public static TextField input3;
    public static Button readbtn;
    public static TextOutputArea textarea;
    public static Button goButton;
    public static Button fn1Button;
    public static Button fn2Button;
    public static Button fn3Button;
    public static Button helpButton;
   
    public static void main(String args[]) {
        launch(args);
    }

    public static void setScrollPaneBottom() {
        // scrollpane.setVmax(10000.00);
        // textarea.print(String.format("Value of Vmax is %12.3f\n", scrollpane.getVmax()));
        // textarea.print(String.format("Value of Vvalue is %12.3f\n", scrollpane.getVvalue()));
        scrollpane.setVvalue(1);
    }

    public static void clearInput() {
        QuadMainFX.input1.setText("");
        QuadMainFX.input2.setText("");
        QuadMainFX.input3.setText("");
    }

    public static void clearTextArea() {
        QuadMainFX.textarea.clear();
    }

    public static void about() {
        String msg =   "\nSPECIAL FUNCTIONS SOLVER.\n"
                     + "(c) 2017 Robert Pascual\n"
                     + "Makabulos Memorial High School\n"
                     + "Victoria, Tarlac, Philippines\n\n";
        QuadMainFX.textarea.print(msg);
    }

    public static void help() {
        QuadMainFX.about();

        QuadMainFX.input1.setText("Input 1");
        QuadMainFX.input2.setText("Input 2");
        QuadMainFX.input3.setText("Input 3");

        textarea.print(
              "Select the proper Tab for your particular problem.\n\n"
            + "The Go and Fn buttons change roles depending on the Tab\n"
            + "you select. For example, the Go button when the Parabolic\n"
            + "Tab is selected will find the zeroes of the parabolic function.\n\n"
            + "Note also the names of the TextFields below.\n\n");
        QuadMainFX.setScrollPaneBottom();
    }

    public static void noActionDefined() {
        textarea.print("This Tab has no defined function for this button.\n");
    }

    public static void goBtnPressed() {
        textarea.print("No function defined for button Go!\n");
    }

    public static void func1BtnPressed() {
        QuadMainFX.noActionDefined();
    }

    public static void func2BtnPressed() {
        textarea.print("No function defined for button FN2!\n");
    }

    public static void func3BtnPressed() {
        textarea.print("No function defined for button FN3!\n");
    }

    @Override public void start(Stage primaryStage) {

        // Create BorderPane and set as the root Scene.

        BorderPane bpane = new BorderPane();
        Scene scene = new Scene(bpane);


        // Place desired elements on the Top of BorderPane.

        VBox topPanel = new VBox();
        bpane.setTop(topPanel);

        Text programName = new Text("SPECIAL FUNCTIONS");
        topPanel.getChildren().add(programName);

        TabPane tabpane = new TabPane();

        // Create the Tab for determining the properties of
        // the parabolic Functions.
        Tab qftab = new Tab("Parabolic");
        tabpane.getTabs().add(qftab);

        // Create the Tab for the elliptic function.
        Tab eltab = new Tab("Elliptic");
        tabpane.getTabs().add(eltab);


        // Create the Tab for the elliptic function.
        Tab hyptab = new Tab("Hyperbolic");
        tabpane.getTabs().add(hyptab);


        // Create the Tab for parabolic interpolation.
        Tab qitab = new Tab("Interpolation");
        tabpane.getTabs().add(qitab);


        topPanel.getChildren().add(tabpane);


        // The Function Buttons
        HBox funcBox = new HBox();
        topPanel.getChildren().add(funcBox);

        goButton = new Button("Go");
        fn1Button = new Button("Fn1");
        fn2Button = new Button("Fn2");
        fn3Button = new Button("Fn3");
        helpButton = new Button("Help");
            helpButton.setOnAction(e->help());

        funcBox.getChildren().addAll(goButton, fn1Button, 
                                     fn2Button, fn3Button, 
                                     helpButton);

        // The default Tab is the parabolic tab. We call it's initializer at
        // this point---after all the Function Buttons have been defined---
        // so that the ParabolicTab may initialize the action for each
        // Function Button.

        ParabolicTab.initialize(qftab);

        // The Center of the BorderPane will contain a scrollable TextArea and line
        // upon which we can enter two inputs.

        VBox ctrpane = new VBox();
        bpane.setCenter(ctrpane);
        textarea = new TextOutputArea();
            textarea.setEditable(false);
            textarea.setPrefHeight(450);
            textarea.setPrefWidth(550);
        scrollpane = new ScrollPane(textarea);
        ctrpane.getChildren().add(scrollpane);

        HBox inputbox = new HBox();
        input1 = new TextField();    input1.setPrefWidth(100);
        input2 = new TextField();    input2.setPrefWidth(100); 
        input3 = new TextField();    input3.setPrefWidth(100); 
        readbtn = new Button("Enter");
        Button clrbtn = new Button("Clear Input");
        clrbtn.setOnAction(e->QuadMainFX.clearInput());

        inputbox.getChildren().addAll(input1, input2, input3, readbtn, clrbtn);
        ctrpane.getChildren().add(inputbox);
        
        // At the bottom we put in the various buttons

        HBox bottomHBox = new HBox();
        Button qButton = new Button("Quit");     qButton.setOnAction(e->Platform.exit());
        Button cButton = new Button("Clear");    cButton.setOnAction(e->QuadMainFX.clearTextArea()); 
        bottomHBox.getChildren().addAll(qButton, cButton);

        bpane.setBottom(bottomHBox);


        // Now call all the other initializers
        InterpolationTab.initialize(qitab);


        // Prepare and show the primaryStage

        primaryStage.setScene(scene);
        primaryStage.setTitle("Conics Solver");
        primaryStage.show();
    }
}

