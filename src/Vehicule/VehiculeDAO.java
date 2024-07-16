package Vehicule;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class VehiculeDAO {
    private String URL = "jdbc:mysql://localhost:3306/parking";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public VehiculeDAO(String URL, String USER, String PASSWORD) {
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

    public boolean existsByNumbPlate(String numbPlate) {
        String query = "SELECT COUNT(*) FROM vehicule WHERE numb_plate = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, numbPlate);
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

    public void addVehicule(Veh_mod vehicule) throws SQLException {
        String sql = "INSERT INTO Vehicule (numb_plate, mark, model, year, color, kilometer, access, add_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, vehicule.getNumb_plate());
        statement.setString(2, vehicule.getMark());
        statement.setString(3, vehicule.getModel());
        statement.setInt(4, vehicule.getYear());
        statement.setString(5, vehicule.getColor());
        statement.setInt(6, vehicule.getKilometer());
        statement.setInt(7, 1);
        statement.setString(8, vehicule.getAdd_date());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Le véhicule a été ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du véhicule.");
        }
    }

    public List<Veh_mod> getAllVehicules() throws SQLException {
        String sql = "SELECT * FROM Vehicule WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Veh_mod> vehicules = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String numb_plate = rs.getString("numb_plate");
            String mark = rs.getString("mark");
            String model = rs.getString("model");
            int year = rs.getInt("year");
            String color = rs.getString("color");
            int kilometer = rs.getInt("kilometer");
            String add_date = rs.getString("add_date");

            Veh_mod vehicule = new Veh_mod(id, numb_plate, mark, model, year, color, kilometer, add_date);
            vehicules.add(vehicule);
        }

        rs.close();
        pst.close();

        return vehicules;
    }

    public List<Veh_mod> getAllVehiculesTrash() throws SQLException {
        String sql = "SELECT * FROM Vehicule WHERE access = 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Veh_mod> vehicules = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String numb_plate = rs.getString("numb_plate");
            String mark = rs.getString("mark");
            String model = rs.getString("model");
            int year = rs.getInt("year");
            String color = rs.getString("color");
            int kilometer = rs.getInt("kilometer");
            String add_date = rs.getString("add_date");

            Veh_mod vehicule = new Veh_mod(id, numb_plate, mark, model, year, color, kilometer, add_date);
            vehicules.add(vehicule);
        }

        rs.close();
        pst.close();

        return vehicules;
    }

    public boolean deleteVehicule(Veh_mod vehicule) throws SQLException {
        String sql = "UPDATE Vehicule SET access = ? WHERE id = ?";
        connect();

        int id = vehicule.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean RestoreTrash(Veh_mod vehicule) throws SQLException {
        String sql = "UPDATE Vehicule SET access = ? WHERE id = ?";
        connect();

        int id = vehicule.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean SureDelete(Veh_mod vehicule) throws SQLException {

        String sql = "DELETE FROM Vehicule WHERE id = ?";
        connect();

        int id = vehicule.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean updateVehicule(Veh_mod vehicule) throws SQLException {
        String sql = "UPDATE Vehicule SET numb_plate = ?, mark = ?, model = ?, year = ?, color = ?, kilometer = ?, add_date = ? WHERE id = ?";
        connect();
        int id = vehicule.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, vehicule.getNumb_plate());
        statement.setString(2, vehicule.getMark());
        statement.setString(3, vehicule.getModel());
        statement.setInt(4, vehicule.getYear());
        statement.setString(5, vehicule.getColor());
        statement.setInt(6, vehicule.getKilometer());
        statement.setString(7, vehicule.getAdd_date());
        statement.setInt(8, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public List<Veh_mod> searchByModel(String model) throws SQLException {
        List<Veh_mod> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicule WHERE model LIKE ?";
        connect();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, model + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    String numbPlate = resultSet.getString("numb_plate");
                    String mark = resultSet.getString("mark");
                    int year = resultSet.getInt("year");
                    String color = resultSet.getString("color");
                    int kilometer = resultSet.getInt("kilometer");
                    String add_date = resultSet.getString("add_date");

                    Veh_mod vehicule = new Veh_mod(0, numbPlate, mark, model, year, color, kilometer, add_date);
                    vehicles.add(vehicule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return vehicles;
    }

    public List<Veh_mod> searchByNumbPlate(String numbPlate) throws SQLException {
        List<Veh_mod> vehicules = new ArrayList<>();
        String sql = "SELECT * FROM Vehicule WHERE numb_plate = ?";
        connect();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, numbPlate);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String mark = resultSet.getString("mark");
                    String model = resultSet.getString("model");
                    int year = resultSet.getInt("year");
                    String color = resultSet.getString("color");
                    int kilometer = resultSet.getInt("kilometer");
                    String add_date = resultSet.getString("add_date");

                    Veh_mod vehicule = new Veh_mod(0, numbPlate, mark, model, year, color, kilometer, add_date);
                    vehicules.add(vehicule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return vehicules;
    }
}
