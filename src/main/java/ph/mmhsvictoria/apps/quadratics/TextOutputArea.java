/* ***************************************************
 * TextOutputArea.java
 *
 * ***************************************************/
package ph.mmhsvictoria.apps.quadratics;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TextOutputArea extends TextArea {
    private String outputStr = "";

    public void TextOutputArea() {
        outputStr = "";
        this.setText(outputStr);
        this.setWrapText(true);
    }

    public void print(String str) {
        outputStr = outputStr + str;
        this.setText(outputStr);
    }

    public void clear() {
        outputStr = "";
        this.setText(outputStr);
    }
}

