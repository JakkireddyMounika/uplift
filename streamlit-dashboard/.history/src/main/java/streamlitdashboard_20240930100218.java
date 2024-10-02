import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StreamlitDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a GUI for your dashboard
        VBox root = new VBox();
        root.getChildren().addAll(
                new Label("Streamlit Dashboard"),
                new Button("Launch Streamlit App 1"),
                new Button("Launch Streamlit App 2")
        );

        // Create a scene and set the stage
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Streamlit Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}