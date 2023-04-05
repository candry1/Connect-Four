import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javafx.scene.input.KeyCode;

public class ClientGUI extends Application {
	TextField portEntry, ipEntry;
	Button exit, playAgain;
	Text welcomeText, finishedText;
	HashMap<String, Scene> sceneMap;
	ListView<String> listItems;
	GridPane gridpane;
	Client c = new Client();
	CFourInfo gameInfo = new CFourInfo();
	EventHandler<ActionEvent> updateButton;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Client GUI");

		portEntry = new TextField("Enter a port number + ENTER");
		ipEntry = new TextField("Enter an IP Address + ENTER");		//127.0.0.1
		ipEntry.setVisible(false);
		welcomeText = new Text("Welcome to Connect 4!");
		listItems = new ListView<String>();
		sceneMap = new HashMap<String,Scene>();
		gridpane = new GridPane();
		exit = new Button("Exit");
		playAgain = new Button("Play Again");

		//--------------------------------------------------------------------------------------------------------------

		updateButton = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(gameInfo.p1Turn == true){
					gameInfo.id = 1;
				} else if(gameInfo.p2Turn == true){
					gameInfo.id = 2;
				}
				if((gameInfo.p1Turn == true) || (gameInfo.p2Turn == true)) {
					GameButton b = (GameButton) event.getSource();
					if (b.enabled == true) {
						gameInfo.row = b.row;
						gameInfo.col = b.col;
						gameInfo.message = "Player" + gameInfo.id + " moved to (" + gameInfo.row + ", " + gameInfo.col + ")";
						c.send(gameInfo);
						if (gameInfo.p1Turn == true) {
							b.setStyle("-fx-background-color: chartreuse;");
							b.color = "chartreuse";
							gameInfo.p1Turn = false;
							gameInfo.p2Turn = true;
							gameInfo.message = "Player2's turn";
							c.send(gameInfo);
							//disable p1's board
							gameInfo.message = "**at this point we'll disable p1's board**";
							c.send(gameInfo);
						} else if (gameInfo.p2Turn == true) {
							b.setStyle("-fx-background-color: fuchsia;");
							b.color = "fuchsia";
							gameInfo.p2Turn = false;
							gameInfo.p1Turn = true;
							gameInfo.message = "Player1's turn";
							c.send(gameInfo);
							//disable p2's board
							gameInfo.message = "**at this point we'll disable p2's board**";
							c.send(gameInfo);
						}
						gameInfo.color = b.color;
						b.setDisable(true);
						b.enabled = false;

//						for (int i = 0; i < c.grid.size(); i++) {
//							if (c.grid.get(i) == b && i - 7 >= 0) {
//								c.grid.get(i - 7).enabled = true;
//								c.grid.get(i - 7).setDisable(false);
//							}
//							} else{
//								c.grid.get(i).setDisable(true);
//							}
//						}

						//check 4 winner...
						b.player = gameInfo.id;
//						gameInfo.hasWinner = c.winner(gameInfo);

					}
				}
			}
		};

		portEntry.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)) {
				if (Integer.parseInt(portEntry.getText()) >= 4000 && Integer.parseInt(portEntry.getText()) <= 6000) {
					ipEntry.setVisible(true);
					portEntry.setEditable(false);
				} else {
					portEntry.setText("Please enter another port from 4000-6000");
				}
			}
		});

		ipEntry.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)) {
				if(ipEntry.getText().equals("127.0.0.1")) {

					c = new Client(data -> { Platform.runLater(() -> {
							CFourInfo info = (CFourInfo) data;
							gameInfo.row = ((CFourInfo) data).row;
							gameInfo.col = ((CFourInfo) data).col;
							listItems.getItems().add(info.message);

					});},
							Integer.parseInt(portEntry.getText()), ipEntry.getText());

					for(int row = 0; row < 6; row++) {
						for (int col = 0; col < 7; col++) {
							GameButton curButton = new GameButton("white", row, col);
							if(row == 5){
								curButton.enabled = true;
								curButton.setDisable(false);
							}

							curButton.setStyle("-fx-background-color: " + curButton.color + ";");
							curButton.setPrefSize(30, 30);
							curButton.setOnAction(updateButton);
							gridpane.add(curButton, col, row);
							gridpane.getColumnConstraints().add(new ColumnConstraints(32));
							gridpane.getRowConstraints().add(new RowConstraints(32));

							c.grid.add(curButton);
						}
					}

					gameInfo.playerCount++;
					primaryStage.setScene(sceneMap.get("board"));
					c.send(gameInfo);
				} else {
					ipEntry.setText("Please do 127.0.0.1");
				}
			}
		});



		//--------------------------------------------------------------------------------------------------------------

		sceneMap.put("start", createStartScene());
		sceneMap.put("board", createBoardScene());
//		sceneMap.put("final", createFinalScreen());

		primaryStage.setScene(sceneMap.get("start"));
		primaryStage.show();
	}

	public Scene createStartScene() {
		welcomeText.setFont(Font.font("Comic Sans MS", 20));
		welcomeText.setFill(Color.WHITE);
		HBox welcomeTextBox = new HBox(welcomeText);
		portEntry.setMinWidth(250);
		portEntry.setMaxWidth(250);
		ipEntry.setMinWidth(250);
		ipEntry.setMaxWidth(250);
		portEntry.setPrefWidth(250);
		ipEntry.setPrefWidth(250);
		VBox portAndIPFields = new VBox(20, welcomeTextBox, portEntry, ipEntry);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(70, 200, 0, 220));
		root.setStyle("-fx-background-color: steelblue;");
		root.setCenter(portAndIPFields);

		return new Scene(root, 700,300);
	}

	public Scene createBoardScene() {
		BorderPane root = new BorderPane();
		gridpane.setStyle("-fx-background-color: steelblue;");
		gridpane.setPrefWidth(300);
		gridpane.setPadding(new Insets(35, 0, 0, 110));
		listItems.setStyle("-fx-font-size: 11;"+"-fx-border-size: 20;"+
				"-fx-border-color: steelblue;" + "-fx-background-color: steelblue;");
		listItems.setPrefSize(300, 300);
		VBox list = new VBox(20, listItems);

		root.setRight(gridpane);
		root.setLeft(list);

		return new Scene(root, 700,300);
	}






//	public Scene createFinalScreen() {
//		//set font, size and color to welcome text
//		if(gameInfo.winner == 1) {
//			finishedText = new Text("Player 1 won!!\nDo you want to play again, or exit?");
//		} else if(gameInfo.winner == 2){
//			finishedText = new Text("Player 2 won!!\nDo you want to play again, or exit?");
//		} else {
//			finishedText = new Text("The game was a tie...\nDo you want to play again, or exit?");
//		}
//		finishedText.setFont(Font.font("Comic Sans MS", 15));
//		finishedText.setFill(Color.WHITE);
//		HBox finishedTextBox = new HBox(finishedText);
//
//		portEntry.setMinWidth(250);
//		portEntry.setMaxWidth(250);
//		ipEntry.setMinWidth(250);
//		ipEntry.setMaxWidth(250);
//		portEntry.setPrefWidth(250);
//		ipEntry.setPrefWidth(250);
//		VBox portAndIPFields = new VBox(20, welcomeTextBox, portEntry, ipEntry);
//
//		BorderPane root = new BorderPane();
//		root.setPadding(new Insets(70, 200, 0, 220));
//		root.setStyle("-fx-background-color: steelblue;");
//		root.setCenter(portAndIPFields);
//
//		return new Scene(root, 700,300);
//	}
}