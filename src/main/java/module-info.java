module com.openjfx.chisquare {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires javafx.base;
	
	// add static otherwise exception will be thrown, src: https://github.com/projectlombok/lombok/issues/1723#issuecomment-405934454.
	requires static lombok;
    
    opens com.openjfx.chisquare to javafx.fxml;
    opens com.openjfx.component;
    
    exports com.openjfx.component;
    exports com.openjfx.chisquare;
}
