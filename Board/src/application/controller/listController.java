package application.controller;

import java.io.IOException;
import java.util.List;

import application.Main;
import application.DTO.Board;
import application.Service.BoardServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class listController {

    @FXML
    private TableView<Board> boardTableView;

    @FXML
    private Button btnWrite;

    @FXML
    private TableColumn<Board, Integer> colNo;

    @FXML
    private TableColumn<Board, String> colWriter;

    @FXML
    private TableColumn<Board, String> colTitle;

    @FXML
    private TableColumn<Board, String> colRegDate;
    
    @FXML
    private TableColumn<Board, Integer> colView;

    List<Board> boardList = null;
    BoardServiceImpl boardST = new BoardServiceImpl();
    @FXML
    public void initialize() throws IOException{
    	boardList = boardST.list();
    	
    	// * TableColumn 에 Board 객체의 속성 매핑하기
    	colNo.setCellValueFactory( new PropertyValueFactory<>("No") );
    	colTitle.setCellValueFactory( new PropertyValueFactory<>("Title") );
    	colWriter.setCellValueFactory( new PropertyValueFactory<>("Writer") );
    	colRegDate.setCellValueFactory( new PropertyValueFactory<>("RegDate") );
    	colView.setCellValueFactory( new PropertyValueFactory<>("View") );
    	// (코드 설명)
    	// new PropertyvalueFactory("게터이름")		: 값으로 들어갈 객체의 게터 이름을 지정
    	// ex) new PropertyvalueFactory("Title") --> getTitle() 게터 메소드의 get을 제외한 Title과 일치
    	// * setCellValueFactory()					: 셀의 값을 지정하는 메소드
    	// ** module-info.java 에 java.base 모듈 추가해야 사용가능
    	
    	colNo.setStyle("-fx-alignment:center;");
    	colTitle.setStyle("-fx-alignment:center;");
    	colWriter.setStyle("-fx-alignment:center;");
    	colRegDate.setStyle("-fx-alignment:center;");
    	colView.setStyle("-fx-alignment:center;");
    	
    	// 테이블뷰에 데이터 추가하기
    	ObservableList<Board> list = FXCollections.observableArrayList(boardList);
    	boardTableView.setItems(list);
    	boardTableView.setOnMouseClicked( new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if ( event.getClickCount() == 2 ) {
					Board data = boardTableView.getSelectionModel().getSelectedItem();
					try {
						toView(data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
    }
    void toView(Board data) throws IOException {
    	// 텍스트 필드에 입력한 데이터
    	
    	// B.fxml 을 읽기
    	FXMLLoader vFXML = new FXMLLoader(Main.class.getResource("UI/View.fxml"));
    	// B 화면 로드
    	Parent vRoot = vFXML.load();
    	// B.fxml 에 연결된 컨트롤러 bController 가져오기
    	ViewController vController = vFXML.getController();
    	// B 화면으로 data 전달 (bController 메소드 호출하면서 data 전달)
    	vController.passData( data );	// A화면에서 B화면으로 데이터 전달
    	// B 화면으로 이동
    	Main.setRoot(vRoot);
    }
    
    @FXML
    void toInsert(ActionEvent event) throws IOException {
    	Main.setRoot("Write");
    }
    
}
