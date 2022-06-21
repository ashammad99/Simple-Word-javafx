/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleword;

import com.sun.javafx.css.converters.FontConverter;
import java.awt.Insets;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed Hammad
 */
public class SimpleWord extends Application {
    
    //Static Variables
    Label textLabel = new Label("Text Align");
    ToggleButton rightAlignbtn = new ToggleButton();
    ToggleButton leftAlignbtn = new ToggleButton();
    ToggleGroup toggleGroup = new ToggleGroup();
    static ComboBox<String> fontsTypes;
    static RadioButton plainButton = new RadioButton("Plain");
    static RadioButton boldButton = new RadioButton("Bold");
    static RadioButton italicButton = new RadioButton("Italic");
    static RadioButton boldandItalicButton = new RadioButton("Bold/Italic");
    static TextField txtFontSize = new TextField("18");
    static TextArea textArea;
    static Font newFont;
    static String fontFamily = "Arial";
    static FontWeight fontWeight = FontWeight.NORMAL;
    static FontPosture fontPosture = FontPosture.REGULAR;
    static int fontSize = 18;

    public void start(Stage primaryStage) {
        textArea = new TextArea();
        textArea.setText("Programming 3 Lab");
        newFont = Font.font(fontFamily, fontSize);
        textArea.setFont(newFont);
        Image img = new Image("/images/background.png");
        ImageView imgView = new ImageView(img);
        StackPane root = new StackPane();
        root.getChildren().add(imgView);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().add(createHbox1());
        vbox.getChildren().add(createHbox2());
        vbox.getChildren().add(textArea);
        root.getChildren().add(vbox);
        root.setPadding(new javafx.geometry.Insets(10));
        Scene scene = new Scene(root, 400, 250);
        primaryStage.setTitle("Simple Word ^_^");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.show();
    }

    HBox createHbox1() {
        textLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, fontSize));
        Image leftAlignImg = new Image(getClass().getResourceAsStream("/images/align-left.png"));
        ImageView imgView1 = new ImageView(leftAlignImg);
        leftAlignbtn.setToggleGroup(toggleGroup);
        rightAlignbtn.setToggleGroup(toggleGroup);
        leftAlignbtn.setGraphic(imgView1);
        leftAlignbtn.setOnAction(e -> {
            textArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        });
        Image rightAlignImg = new Image(getClass().getResourceAsStream("/images/align-right.png"));
        ImageView imgView2 = new ImageView(rightAlignImg);
        rightAlignbtn.setGraphic(imgView2);
        leftAlignbtn.setSelected(true);
        rightAlignbtn.setOnAction(e -> {
            textArea.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        });
        fontsTypes = new ComboBox<String>();
        ObservableList list = fontsTypes.getItems();
        list.add("Arial");//, "", "",new Separator);
        list.add("New Times Roman");
        list.add("Freestyle Script");
        Separator sep = new Separator();
        sep.setPrefHeight(10);
        sep.setHalignment(HPos.CENTER);
        list.add(sep);
        list.add("Andalus");
        list.add("KufiLTRegular");
        fontsTypes.getSelectionModel().selectFirst();
        fontsTypes.prefWidth(40);

        fontsTypes.setOnAction(e -> {
            int choice = fontsTypes.getSelectionModel().getSelectedIndex();
            switch (choice) {
                case 0:
                    fontFamily = "Arial";
                    newFont = Font.font("Arial", fontWeight, fontPosture, fontSize);
                    break;
                case 1:
                    fontFamily = "New Times Roman";
                    newFont = Font.font("New Times Roman", fontWeight, fontPosture, fontSize);
                    break;
                case 2:
                    fontFamily = "Freestyle Script";
                    newFont = Font.font("Freestyle Script", fontWeight, fontPosture, fontSize);
                    break;
                case 4:
                    fontFamily = "Andalus";
                    newFont = Font.font("Andalus", fontWeight, fontPosture, fontSize);
                    break;
                case 5:
                    fontFamily = "KufiLTRegular";
                    newFont = Font.font("KufiLTRegular", fontWeight, fontPosture, fontSize);
                    break;
            }
            textArea.setFont(newFont);
        });
        HBox hBox = new HBox();
        hBox.setSpacing(20.0);
        hBox.getChildren().add(textLabel);
        hBox.getChildren().add(leftAlignbtn);
        hBox.getChildren().add(rightAlignbtn);
        hBox.getChildren().add(fontsTypes);
        return hBox;
    }

    HBox createHbox2() {
        ToggleGroup fontsStyle = new ToggleGroup();
        plainButton.setToggleGroup(fontsStyle);
        plainButton.setOnAction(e -> {
            fontPosture = fontPosture.REGULAR;
            fontWeight = fontWeight.NORMAL;
            newFont = Font.font(fontFamily, fontWeight, fontPosture, fontSize);
            textArea.setFont(newFont);
        });
        fontsStyle.selectToggle(plainButton);
        boldButton.setToggleGroup(fontsStyle);
        boldButton.setOnAction(e -> {
            fontPosture = fontPosture.REGULAR;
            fontWeight = fontWeight.BOLD;
            newFont = Font.font(fontFamily, fontWeight, fontPosture, fontSize);
            textArea.setFont(newFont);
        });
        italicButton.setToggleGroup(fontsStyle);
        italicButton.setOnAction(e -> {
            fontPosture = fontPosture.ITALIC;
            newFont = Font.font(fontFamily, fontWeight, fontPosture, fontSize);
            textArea.setFont(newFont);
        });
        boldandItalicButton.setToggleGroup(fontsStyle);
        boldandItalicButton.setOnAction(e -> {
            fontPosture = fontPosture.ITALIC;
            fontWeight = fontWeight.BOLD;
            newFont = Font.font(fontFamily, fontWeight, fontPosture, fontSize);
            textArea.setFont(newFont);
        });
        txtFontSize.setPrefWidth(45);
        //To Make Text Feild get only numbers
        txtFontSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtFontSize.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtFontSize.setEditable(true);
        txtFontSize.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (txtFontSize.getText().length() != 0) {
                    Integer size = Integer.parseInt(txtFontSize.getText());
                    textArea.setFont(newFont.font(size));
                }

            }
        });
        HBox hBox = new HBox();
        hBox.getChildren().add(plainButton);
        hBox.getChildren().add(boldButton);
        hBox.getChildren().add(italicButton);
        hBox.getChildren().add(boldandItalicButton);
        hBox.getChildren().add(txtFontSize);
        hBox.setSpacing(30.0);
        return hBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
