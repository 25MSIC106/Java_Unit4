import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import java.sql.*;

public class ViewExpenseController {

    @FXML
    TableView<Expense> table;
    @FXML
    TableColumn<Expense, String> titleCol, categoryCol, dateCol;
    @FXML
    TableColumn<Expense, Double> amountCol;

    @FXML
    public void initialize() {
        loadData();
    }

    private void loadData() {
        ObservableList<Expense> list = FXCollections.observableArrayList();
        try (Connection conn = DBConnection.connect()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM expenses");
            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("date")));
            }
            table.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteExpense() {
        Expense ex = table.getSelectionModel().getSelectedItem();
        if (ex == null)
            return;
        try (Connection conn = DBConnection.connect()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM expenses WHERE id=?");
            ps.setInt(1, ex.getId());
            ps.executeUpdate();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
