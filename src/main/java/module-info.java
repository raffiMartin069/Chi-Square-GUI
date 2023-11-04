module com.openjfx.chisquare {
	// Modular programming is used...
	// Adds required dependencies to the project.
	// opens a package to make it visible to whole project.
	// exports a project to make it accessible and visible to entire project.
	requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires javafx.base;
	requires java.desktop;
    opens com.openjfx.chisquare to javafx.fxml;
    opens com.openjfx.component;
    exports com.openjfx.chisquare;
}
