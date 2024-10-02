import java.io.IOException;

public class StreamlitDashboard extends Application {

    // ...

    @Override
    public void start(Stage primaryStage) {
        // ...

        // Launch Streamlit App 1
        Button launchApp1Button = new Button("Launch Streamlit App 1");
        launchApp1Button.setOnAction(event -> {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("streamlit", "run", "app1.py");
                processBuilder.start();
            } catch (IOException e) {
                System.err.println("Error launching Streamlit App 1: " + e.getMessage());
            }
        });

        // Launch Streamlit App 2
        Button launchApp2Button = new Button("Launch Streamlit App 2");
        launchApp2Button.setOnAction(event -> {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("streamlit", "run", "app2.py");
                processBuilder.start();
            } catch (IOException e) {
                System.err.println("Error launching Streamlit App 2: " + e.getMessage());
            }
        });

        // ...
    }

    // ...
}