import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DBConnection.connect(); // create table

        Button addBtn = new Button("Add Expense");
        Button viewBtn = new Button("View Expenses");

        addBtn.setOnAction(e -> open(stage, "/addExpense.fxml"));
        viewBtn.setOnAction(e -> open(stage, "/viewExpense.fxml"));

        VBox box = new VBox(20, addBtn, viewBtn);
        box.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(box, 300, 200));
        stage.setTitle("Expense Tracker");
        stage.show();
    }

    private void open(Stage stage, String file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            stage.setScene(new Scene(loader.load(), 400, 350));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
