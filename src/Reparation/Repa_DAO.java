package Reparation;

import java.sql.*;
import java.util.*;

public class Repa_DAO {
    private String URL = "jdbc:mysql://localhost:3306/parking";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Repa_DAO(String URL, String USER, String PASSWORD) {
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

    public boolean existsByProblem(String problem) {
        String query = "SELECT COUNT(*) FROM reparations_list WHERE problem = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, problem);
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

    public boolean existsBySolution(String solution) {
        String query = "SELECT COUNT(*) FROM reparations_list WHERE solution = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, solution);
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

    public List<Repa_mod> getAllReparations() throws SQLException {
        String sql = "SELECT * FROM reparations_list WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Repa_mod> repa = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String problem = rs.getString("problem");
            String solution = rs.getString("solution");
            double price = rs.getDouble("price");
            String add_date = rs.getString("add_date");

            Repa_mod rep = new Repa_mod(id, problem, solution, price, add_date);
            repa.add(rep);
        }

        rs.close();
        pst.close();

        return repa;
    }

    public List<Repa_mod> getAllReparationsTrash() throws SQLException {
        String sql = "SELECT * FROM reparations_list WHERE access = 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Repa_mod> repa = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String problem = rs.getString("problem");
            String solution = rs.getString("solution");
            double price = rs.getDouble("price");
            String add_date = rs.getString("add_date");

            Repa_mod rep = new Repa_mod(id, problem, solution, price, add_date);
            repa.add(rep);
        }

        rs.close();
        pst.close();

        return repa;
    }

    public void addReparations(Repa_mod rep) throws SQLException {
        String sql = "INSERT INTO reparations_list (problem, solution, price, access, add_date) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, rep.getProblem());
        statement.setString(2, rep.getSolution());
        statement.setDouble(3, rep.getPrice());
        statement.setInt(4, 1);
        statement.setString(5, rep.getAdd_date());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Réparations ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout de la Réparations.");
        }
    }

    public boolean updateMaintenance(Repa_mod rep) throws SQLException {
        String sql = "UPDATE reparations_list SET problem = ?, solution = ?, price = ?, add_date = ? WHERE id = ?";
        connect();
        int id = rep.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, rep.getProblem());
        statement.setString(2, rep.getSolution());
        statement.setDouble(3, rep.getPrice());
        statement.setString(4, rep.getAdd_date());
        statement.setInt(5, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteReparations(Repa_mod rep) throws SQLException {
        String sql = "UPDATE reparations_list SET access = ? WHERE id = ?";
        connect();

        int id = rep.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean RestoreTrashReparations(Repa_mod rep) throws SQLException {
        String sql = "UPDATE reparations_list SET access = ? WHERE id = ?";
        connect();

        int id = rep.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean SureDeleteReparations(Repa_mod rep) throws SQLException {

        String sql = "DELETE FROM reparations_list WHERE id = ?";
        connect();

        int id = rep.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public List<Repa_mod> searchProblem(String problem) throws SQLException {
        List<Repa_mod> repa = new ArrayList<>();
        String sql = "SELECT * FROM reparations_list WHERE problem LIKE ?";
        connect();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, "%" + problem + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    String solution = resultSet.getString("solution");
                    double price = resultSet.getDouble("price");
                    String add_date = resultSet.getString("add_date");

                    Repa_mod rep = new Repa_mod(0, problem, solution, price, add_date);
                    repa.add(rep);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return repa;
    }

}
