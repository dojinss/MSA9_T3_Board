package application.controller;

import java.io.IOException;

import application.Main;
import application.DTO.Board;
import application.Service.BoardServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class WriteController {
	BoardServiceImpl boardService = new BoardServiceImpl();
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnList;

    @FXML
    private Button btnWrite;

    @FXML
    private TextArea contentText;

    @FXML
    private Label title;

    @FXML
    private TextField titleText;

    @FXML
    private TextField writeText;

    @FXML
    private Label writer;

    @FXML
    void toList(ActionEvent event) throws IOException {
    	Main.setRoot("List");
    }

    @FXML
    void write(ActionEvent event) throws IOException {
    	Board board = new Board();
    	board.setTitle( titleText.getText() );
    	board.setWriter( writeText.getText() );
    	board.setContent( contentText.getText() );
    	boardService.insert(board);
    	// 확인 경고창
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("게시글 작성완료");
		alert.setHeaderText(title.getText());
		alert.setContentText("글이 작성되었습니다.");
		// 경고창에서 OK 버튼 클릭 시
		if ( alert.showAndWait().get() == ButtonType.OK) {
			Main.setRoot("List");
		}
    }

}
