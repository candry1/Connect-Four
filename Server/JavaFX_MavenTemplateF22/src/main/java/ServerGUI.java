import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

public class ServerGUI extends Application {
	Server s;
	HashMap<String, Scene> sceneMap;
	TextField portEntry;
	Button onButton;
	ListView<String> listItems = new ListView<String>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Server GUI");

		sceneMap = new HashMap<String, Scene>();
		portEntry = new TextField("Enter a port number + ENTER");
		onButton = new Button("Start Game");
		onButton.setVisible(false);

		//--------------------------------------------------------------------------------------------------------------

		portEntry.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				if (Integer.parseInt(portEntry.getText()) >= 4000 && Integer.parseInt(portEntry.getText()) <= 6000) {
					s = new Server(data -> {
						Platform.runLater(() -> {
							listItems.getItems().add(data.toString());
						});
					},
							Integer.parseInt(portEntry.getText()));
					onButton.setVisible(true);
				} else {
					portEntry.setText("Please enter another port from 4000-6000");
				}
			}
		});

		onButton.setOnAction(e -> {
			primaryStage.setScene(sceneMap.get("gameStats"));
		});

		//--------------------------------------------------------------------------------------------------------------

		sceneMap.put("start", createStartScene());
		sceneMap.put("gameStats", createListViewScene());

		primaryStage.setScene(sceneMap.get("start"));
		primaryStage.show();
	}

	public Scene createStartScene() {
		Text welcome = new Text("Welcome to Connect 4");
		welcome.setFont(Font.font("Comic Sans MS", 20));
		welcome.setFill(Color.WHITE);

		Button blank = new Button();
		blank.setVisible(false);
		Button blank2 = new Button();
		blank2.setVisible(false);

		HBox onButtonBox = new HBox(90, blank, onButton);
		HBox welcomeBox = new HBox(20, blank2, welcome);
		VBox portAndOnButton = new VBox(30, welcomeBox, portEntry, onButtonBox);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(150, 100, 0, 100));
		root.setStyle("-fx-background-color: steelblue;");
		root.setCenter(portAndOnButton);

		return new Scene(root, 500, 500);
	}

	public Scene createListViewScene() {
		BorderPane root = new BorderPane();
		listItems.setStyle("-fx-font-size: 11;"+"-fx-border-size: 20;"+
				"-fx-border-color: royalblue;");
		listItems.setPrefSize(400, 400);
		TextField blank = new TextField();
		blank.setVisible(false);
		VBox list = new VBox(20, blank, listItems);
		root.setCenter(list);
		root.setStyle("-fx-background-color: steelblue;");
		return new Scene(root, 500, 500);
	}
}