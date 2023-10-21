module com.openjfx.chisquare {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    
    opens com.openjfx.chisquare to javafx.fxml;
    exports com.openjfx.chisquare;
}
