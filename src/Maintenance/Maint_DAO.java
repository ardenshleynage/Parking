package Maintenance;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Maint_DAO {
    private String URL = "jdbc:mysql://localhost:3306/parking";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Maint_DAO(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;

    }

    protected void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    protected void disconnect() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public boolean existsByType(String type) {
        String query = "SELECT COUNT(*) FROM maintenance_list WHERE type = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByDescription(String description) {
        String query = "SELECT COUNT(*) FROM maintenance_list WHERE description = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, description);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Maint_mod> getAllMaintenance() throws SQLException {
        String sql = "SELECT * FROM maintenance_list WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Maint_mod> maints = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String type = rs.getString("type");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            String add_date = rs.getString("add_date");

            Maint_mod maint = new Maint_mod(id, type, description, price, add_date);
            maints.add(maint);
        }

        rs.close();
        pst.close();

        return maints;
    }

    public List<Maint_mod> getAllMaintenanceTrash() throws SQLException {
        String sql = "SELECT * FROM maintenance_list WHERE access = 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Maint_mod> maints = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String type = rs.getString("type");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            String add_date = rs.getString("add_date");

            Maint_mod maint = new Maint_mod(id, type, description, price, add_date);
            maints.add(maint);
        }

        rs.close();
        pst.close();

        return maints;
    }

    public void addMaintenance(Maint_mod maint) throws SQLException {
        String sql = "INSERT INTO maintenance_list (type, description, price, access, add_date) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, maint.getType());
        statement.setString(2, maint.getDescription());
        statement.setDouble(3, maint.getPrice());
        statement.setInt(4, 1);
        statement.setString(5, maint.getAdd_date());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Maintenance ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout de la maintenance.");
        }
    }

    public boolean updateMaintenance(Maint_mod maint) throws SQLException {
        String sql = "UPDATE maintenance_list SET type = ?, description = ?, price = ?, add_date = ? WHERE id = ?";
        connect();
        int id = maint.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, maint.getType());
        statement.setString(2, maint.getDescription());
        statement.setDouble(3, maint.getPrice());
        statement.setString(4, maint.getAdd_date());
        statement.setInt(5, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteMaintenance(Maint_mod maint) throws SQLException {
        String sql = "UPDATE maintenance_list SET access = ? WHERE id = ?";
        connect();

        int id = maint.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean RestoreTrashMaintenance(Maint_mod maint) throws SQLException {
        String sql = "UPDATE maintenance_list SET access = ? WHERE id = ?";
        connect();

        int id = maint.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean SureDeleteMaintenance(Maint_mod maint) throws SQLException {

        String sql = "DELETE FROM maintenance_list WHERE id = ?";
        connect();

        int id = maint.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public List<Maint_mod> searchType(String type) throws SQLException {
        List<Maint_mod> maints = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_list WHERE type LIKE ?";
        connect();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, "%" + type + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    String add_date = resultSet.getString("add_date");

                    Maint_mod maint = new Maint_mod(0, type, description, price, add_date);
                    maints.add(maint);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return maints;
    }

}
