module Board {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires lombok;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base, lombok, java.sql;
	opens application.controller to javafx.fxml;
	opens application.DTO to javafx.base;
}
