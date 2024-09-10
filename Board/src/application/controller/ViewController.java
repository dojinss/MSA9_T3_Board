package application.controller;

import java.io.IOException;

import application.Main;
import application.DAO.BoardDAO;
import application.DTO.Board;
import application.Service.BoardServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewController {
	Stage stage = null;
	BoardServiceImpl boardService = new BoardServiceImpl();
    @FXML
    private Button btnDel;

    @FXML
    private Button btnList;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label content;

    @FXML
    private Label regDate;

    @FXML
    private Label title;

    @FXML
    private Label updDate;

    @FXML
    private Label writer;
    
    @FXML
    private AnchorPane anchorPane;
    
    private static Board board = null;
    
    public void initialize() {
    	System.out.println("View파일 로드");
    }
    
    void passData(Board data) {
    	board = data;
    	title.setText(board.getTitle());
    	content.setText(board.getContent());
    	regDate.setText( board.getRegDate().toString() );
    	updDate.setText( board.getUpdDate().toString() );
    	boardService.select(board.getNo());
    }
    @FXML
    void toDelete(ActionEvent event) throws IOException {
    	// 확인 경고창
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("삭제 확인");
		alert.setHeaderText(title.getText());
		alert.setContentText("해당 글을 삭제 하시겠습니까?");
		
		// 경고창에서 OK 버튼 클릭 시
		if ( alert.showAndWait().get() == ButtonType.OK) {
			boardService.delete(board.getNo());
			System.out.println("게시글이 삭제되었습니다.");
			Main.setRoot("List");
		}
		
    }
    
    @FXML
    void toList(ActionEvent event) throws IOException {
    	Main.setRoot("List");
    }

    @FXML
    void toUpdate(ActionEvent event) throws IOException {
    	// B.fxml 을 읽기
    	FXMLLoader vFXML = new FXMLLoader(Main.class.getResource("UI/Update.fxml"));
    	// B 화면 로드
    	Parent vRoot = vFXML.load();
    	// B.fxml 에 연결된 컨트롤러 bController 가져오기
    	UpdateController vController = vFXML.getController();
    	// B 화면으로 data 전달 (bController 메소드 호출하면서 data 전달)
    	vController.passData( board );	// A화면에서 B화면으로 데이터 전달
    	// B 화면으로 이동
    	Main.setRoot(vRoot);
    }
    
}
