package application.controller;

import java.io.IOException;

import application.Main;
import application.DAO.BoardDAO;
import application.DTO.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class UpdateController {
	BoardDAO boardDAO = new BoardDAO();
	Board board = null;
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
    
    void passData(Board data) {
    	board = data;
    	titleText.setText( board.getTitle() );
    	writeText.setText( board.getWriter() );
    	contentText.setText( board.getContent() );
    }
    
    @FXML
    void write(ActionEvent event) throws IOException {
    	
    	
    	// 확인 경고창
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("게시글 수정완료");
		alert.setHeaderText(title.getText());
		alert.setContentText("글을 수정하시겠습니까?");
		
		// 경고창에서 OK 버튼 클릭 시
		if ( alert.showAndWait().get() == ButtonType.OK) {
			board.setTitle( titleText.getText() );
	    	board.setWriter( writeText.getText() );
	    	board.setContent( contentText.getText() );
	    	boardDAO.update(board);
			Main.setRoot("List");
		}
    }

}
