import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.*;

public class AddExpenseController {

    @FXML TextField titleField, categoryField, amountField, dateField;

    @FXML
    public void saveExpense() {
        try (Connection conn = DBConnection.connect()) {
            String sql = "INSERT INTO expenses(title,category,amount,date) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, titleField.getText());
            ps.setString(2, categoryField.getText());
            ps.setDouble(3, Double.parseDouble(amountField.getText()));
            ps.setString(4, dateField.getText());
            ps.executeUpdate();
            System.out.println("Saved!");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
