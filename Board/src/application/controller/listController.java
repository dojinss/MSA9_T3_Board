package application.controller;

import java.util.List;

import application.DTO.Board;
import application.Service.BoardServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public void initialize() {
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
    	
    }
    
    @FXML
    void toInsert(ActionEvent event) {

    }

}
