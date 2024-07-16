package Maintenance_Meet_Up;

import java.util.*;

import Maintenance.Maint_mod;

import java.sql.*;

import Vehicule.Veh_mod;

public class Mmu_DAO {
    private String URL = "jdbc:mysql://localhost:3306/parking";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Mmu_DAO(String URL, String USER, String PASSWORD) {
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

    public boolean existsByMaintenanceAndVehicle(String numbPlate, String typeMaint, String markVeh, String modelVeh,
            int yearVeh, String colorVeh) {
        String query = "SELECT COUNT(*) FROM maintenance_meet_up WHERE numb_plate_vehicle = ? AND type_maintenance = ? AND mark_vehicle = ? AND model_vehicle = ? AND year_vehicle = ? AND color_vehicle = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, numbPlate);
            preparedStatement.setString(2, typeMaint);
            preparedStatement.setString(3, markVeh);
            preparedStatement.setString(4, modelVeh);
            preparedStatement.setInt(5, yearVeh);
            preparedStatement.setString(6, colorVeh);
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

    public List<Mmu_mod> getAllMmu() throws SQLException {
        String sql = "SELECT * FROM maintenance_meet_up WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Mmu_mod> mmus = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int id_maint = rs.getInt("id_maintenance");
            String type_maint = rs.getString("type_maintenance");
            String description_maint = rs.getString("description_maintenance");
            double price_maint = rs.getDouble("price_maintenance");
            int id_veh = rs.getInt("id_vehicle");
            String numb_plate_vehicle = rs.getString("numb_plate_vehicle");
            String mark_veh = rs.getString("mark_vehicle");
            String model_veh = rs.getString("model_vehicle");
            String color_veh = rs.getString("color_vehicle");
            int year_veh = rs.getInt("year_vehicle");
            String add_date = rs.getString("add_date");

            Mmu_mod mmu = new Mmu_mod(id, id_maint, type_maint, description_maint, price_maint, id_veh,
                    numb_plate_vehicle, mark_veh, model_veh, color_veh, year_veh, add_date);
            mmus.add(mmu);
        }

        rs.close();
        pst.close();

        return mmus;
    }

    public List<Mmu_mod> getAllMmuTrash() throws SQLException {
        String sql = "SELECT * FROM maintenance_meet_up WHERE access = 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Mmu_mod> mmus = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int id_maint = rs.getInt("id_maintenance");
            String type_maint = rs.getString("type_maintenance");
            String description_maint = rs.getString("description_maintenance");
            double price_maint = rs.getDouble("price_maintenance");
            int id_veh = rs.getInt("id_vehicle");
            String numb_plate_vehicle = rs.getString("numb_plate_vehicle");
            String mark_veh = rs.getString("mark_vehicle");
            String model_veh = rs.getString("model_vehicle");
            String color_veh = rs.getString("color_vehicle");
            int year_veh = rs.getInt("year_vehicle");
            String add_date = rs.getString("add_date");

            Mmu_mod mmu = new Mmu_mod(id, id_maint, type_maint, description_maint, price_maint, id_veh,
                    numb_plate_vehicle, mark_veh, model_veh, color_veh, year_veh, add_date);
            mmus.add(mmu);
        }

        rs.close();
        pst.close();

        return mmus;
    }

    public boolean addMmu(Mmu_mod mmu) throws SQLException {
        String sql = "INSERT INTO maintenance_meet_up (id_maintenance, type_maintenance, description_maintenance, price_maintenance, id_vehicle, numb_plate_vehicle, mark_vehicle, model_vehicle, color_vehicle, year_vehicle, access, add_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, mmu.getIdMaint());
        statement.setString(2, mmu.getTypeMaint());
        statement.setString(3, mmu.getDescriptionMaint());
        statement.setDouble(4, mmu.getPriceMaint());
        statement.setInt(5, mmu.getIdVeh());
        statement.setString(6, mmu.getNumbPlateVehicle());
        statement.setString(7, mmu.getMarkVeh());
        statement.setString(8, mmu.getModelVeh());
        statement.setString(9, mmu.getColorVeh());
        statement.setInt(10, mmu.getYearVeh());
        statement.setInt(11, 1);
        statement.setString(12, mmu.getAdd_date());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean updateMmu(Mmu_mod mmu) throws SQLException {
        String sql = "UPDATE maintenance_meet_up SET id_maintenance = ?, type_maintenance = ?, description_maintenance = ?, price_maintenance = ?, id_vehicle = ?, numb_plate_vehicle= ?, mark_vehicle = ?, model_vehicle = ?, color_vehicle = ?, year_vehicle = ?, add_date = ? WHERE id = ?";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, mmu.getIdMaint());
        statement.setString(2, mmu.getTypeMaint());
        statement.setString(3, mmu.getDescriptionMaint());
        statement.setDouble(4, mmu.getPriceMaint());
        statement.setInt(5, mmu.getIdVeh());
        statement.setString(6, mmu.getNumbPlateVehicle());
        statement.setString(7, mmu.getMarkVeh());
        statement.setString(8, mmu.getModelVeh());
        statement.setString(9, mmu.getColorVeh());
        statement.setInt(10, mmu.getYearVeh());
        statement.setString(11, mmu.getAdd_date());
        statement.setInt(12, mmu.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteMmu(Mmu_mod mmu) throws SQLException {
        String sql = "UPDATE maintenance_meet_up SET access = ? WHERE id = ?";
        connect();

        int id = mmu.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean RestoreMmuTrash(Mmu_mod mmu) throws SQLException {
        String sql = "UPDATE maintenance_meet_up SET access = ? WHERE id = ?";
        connect();

        int id = mmu.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean SureMmuDelete(Mmu_mod mmu) throws SQLException {

        String sql = "DELETE FROM maintenance_meet_up WHERE id = ?";
        connect();

        int id = mmu.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public List<Mmu_mod> searchMmu(String npv) throws SQLException {
        List<Mmu_mod> mmus = new ArrayList<>();
        String checkAccessSql = "SELECT access FROM vehicule WHERE numb_plate = ? AND access = 1";
        String fetchMmuSql = "SELECT * FROM maintenance_meet_up WHERE numb_plate_vehicle = ? AND access = 1";

        connect();

        try {
            boolean accessAllowed = false;

            try (PreparedStatement checkAccessStmt = con.prepareStatement(checkAccessSql)) {
                checkAccessStmt.setString(1, npv);
                try (ResultSet accessResultSet = checkAccessStmt.executeQuery()) {
                    if (accessResultSet.next()) {
                        accessAllowed = true;
                    }
                }
            }

            if (accessAllowed) {
                try (PreparedStatement fetchRmuStmt = con.prepareStatement(fetchMmuSql)) {
                    fetchRmuStmt.setString(1, npv);
                    try (ResultSet resultSet = fetchRmuStmt.executeQuery()) {
                        while (resultSet.next()) {
                            String type_maint = resultSet.getString("type_maintenance");
                            String description_maint = resultSet.getString("description_maintenance");
                            double price_maint = resultSet.getDouble("price_maintenance");
                            String numb_plate_vehicle = resultSet.getString("numb_plate_vehicle");
                            String mark_veh = resultSet.getString("mark_vehicle");
                            String model_veh = resultSet.getString("model_vehicle");
                            String color_veh = resultSet.getString("color_vehicle");
                            int year_veh = resultSet.getInt("year_vehicle");
                            String add_date = resultSet.getString("add_date");

                            Mmu_mod mmu = new Mmu_mod(0, 0, type_maint, description_maint, price_maint, 0,
                                    numb_plate_vehicle,
                                    mark_veh, model_veh, color_veh, year_veh, add_date);
                            mmus.add(mmu);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return mmus;
    }

    public boolean isMaintenanceAlreadyAssociated(int vehicleId, String typeMaint) throws SQLException {
        String sql = "SELECT COUNT(*) FROM maintenance_meet_up WHERE id_vehicle = ? AND type_maintenance = ?";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, vehicleId);
        statement.setString(2, typeMaint);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        boolean exists = resultSet.getInt(1) > 0;

        resultSet.close();
        statement.close();
        disconnect();
        return exists;
    }

}
