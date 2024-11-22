package dao;

import entity.Asset;
import myexceptions.AssetNotFoundException;
import myexceptions.AssetNotMaintainException;
import myexceptions.EmployeeNotFoundException;
import util.*;
import java.sql.*;
import java.time.*;

public class AssetManagementServiceImpl implements AssetManagementService{


    public boolean addAsset(Asset asset) {

        String query="INSERT INTO assets(asset_id,name,type,serial_number,purchase_date,location,status,owner_id) VALUES(?,?,?,?,?,?,?,?)";

        try(Connection con=DBConnUtil.getConnection()) {

            PreparedStatement p=con.prepareStatement("Select * from employees where employee_id=?");
            p.setInt(1,asset.getOwnerId());
            ResultSet rs=p.executeQuery();
            if(!rs.next()) throw new EmployeeNotFoundException("Employee with id "+ asset.getOwnerId()+" is not found");

            p=con.prepareStatement(query);
            p.setInt(1, asset.getAssetId());
            p.setString(2,asset.getName());
            p.setString(3, asset.getType());
            p.setString(4,asset.getSerialNumber());
            p.setString(5, asset.getPurchaseDate());
            p.setString(6,asset.getLocation());
            p.setString(7, asset.getStatus());
            p.setInt(8, asset.getOwnerId());
            p.executeUpdate();
        }
        catch(EmployeeNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateAsset(Asset asset) {

        String query="UPDATE assets SET type=?, name=?, serial_number=?, purchase_date=?, location=?, status=?, owner_id=? WHERE asset_id=?";

        try(Connection con = DBConnUtil.getConnection()) {

            PreparedStatement p=con.prepareStatement("Select * from assets where asset_id=?");
            p.setInt(1,asset.getAssetId());
            ResultSet rs=p.executeQuery();
            if(!rs.next()) throw new AssetNotFoundException("Asset with id "+asset.getAssetId()+" is not found");

            PreparedStatement psmt=con.prepareStatement(query);
            psmt.setString(1, asset.getType());
            psmt.setString(2,asset.getName());
            psmt.setString(3, asset.getSerialNumber());
            psmt.setString(4,asset.getPurchaseDate());
            psmt.setString(5,asset.getLocation());
            psmt.setString(6, asset.getStatus());
            psmt.setInt(7,asset.getOwnerId());
            psmt.setInt(8, asset.getAssetId());
            psmt.executeUpdate();
        }
        catch(AssetNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAsset(int assetId) {

        String query="DELETE FROM assets WHERE asset_id=?";

        try(Connection con=DBConnUtil.getConnection()) {
            PreparedStatement p=con.prepareStatement("Select * from assets where asset_id=?");
            p.setInt(1,assetId);
            ResultSet rs=p.executeQuery();
            if(!rs.next()) throw new AssetNotFoundException("Asset with id "+assetId+" is not found");
            PreparedStatement psmt=con.prepareStatement(query);
            psmt.setInt(1, assetId);
            psmt.executeUpdate();
        }
        catch(AssetNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {

        String query="INSERT INTO asset_allocations(asset_id,employee_id,allocation_date) VALUES (?,?,?)";

        try(Connection con = DBConnUtil.getConnection()) {
            PreparedStatement p=con.prepareStatement("Select * from assets where asset_id=?");

            p.setInt(1,assetId);
            ResultSet rs=p.executeQuery();
            if(!rs.next()) throw new AssetNotFoundException("Asset with id "+assetId+" is not found");

            p=con.prepareStatement("Select * from employees where employee_id=?");
            p.setInt(1,employeeId);
            rs=p.executeQuery();
            if(!rs.next()) throw new EmployeeNotFoundException("Employee with id " +employeeId+" is not found");

            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setInt(1,assetId);
            psmt.setInt(2,employeeId);
            psmt.setString(3,allocationDate);
            psmt.executeUpdate();
        }
        catch(AssetNotFoundException | EmployeeNotFoundException |SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
        String query="Update asset_allocations set return_date=? where asset_id=? and employee_id=?";
        try(Connection con=DBConnUtil.getConnection()) {
            PreparedStatement p=con.prepareStatement("Select * from assets where asset_id=?");

            p.setInt(1,assetId);
            ResultSet rs=p.executeQuery();
            if(!rs.next()) throw new AssetNotFoundException("Asset with id "+assetId+" is not found");

            p=con.prepareStatement("Select * from employees where employee_id=?");
            p.setInt(1,employeeId);
            rs=p.executeQuery();
            if(!rs.next()) throw new EmployeeNotFoundException("Employee with id "+employeeId+" is not found");

            PreparedStatement psmt=con.prepareStatement(query);
            psmt.setString(1,returnDate);
            psmt.setInt(2,assetId);
            psmt.setInt(3, employeeId);
            psmt.executeUpdate();
        }
        catch(AssetNotFoundException | EmployeeNotFoundException |SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {

        String query="INSERT INTO maintenance_records(asset_id,maintenance_date,description,cost) VALUES(?,?,?,?)";

        try(Connection con=DBConnUtil.getConnection()) {
            PreparedStatement p=con.prepareStatement("Select * from assets where asset_id=?");
            p.setInt(1,assetId);
            ResultSet rs=p.executeQuery();
            if(!rs.next()) throw new AssetNotFoundException("Asset with id "+assetId+" is not found");
            PreparedStatement psmt=con.prepareStatement(query);
            psmt.setInt(1,assetId);
            psmt.setString(2, maintenanceDate);
            psmt.setString(3, description);
            psmt.setDouble(4, cost);
            psmt.executeUpdate();
        }
        catch(SQLException | AssetNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate, String status) {

        String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnUtil.getConnection()) {

            PreparedStatement p = con.prepareStatement("SELECT * FROM assets WHERE asset_id = ?");
            p.setInt(1, assetId);
            ResultSet rs = p.executeQuery();
            if (!rs.next()) throw new AssetNotFoundException("Asset with ID " + assetId + " is not found");

            p = con.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
            p.setInt(1, employeeId);
            rs = p.executeQuery();
            if (!rs.next()) throw new EmployeeNotFoundException("Employee with ID " + employeeId + " is not found");

            p = con.prepareStatement("SELECT MAX(maintenance_date) FROM maintenance_records WHERE asset_id = ?");
            p.setInt(1, assetId);
            rs = p.executeQuery();
            if (rs.next()) {
                String lastMaintenanceDate = rs.getString(1);
                if (lastMaintenanceDate != null && !lastMaintenanceDate.isEmpty()) {
                    LocalDate lastDate = LocalDate.parse(lastMaintenanceDate);
                    if (lastDate.isBefore(LocalDate.now().minusYears(2))) {
                        throw new AssetNotMaintainException("Asset not maintained for more than 2 years.");
                    }
                }
            }
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, assetId);
            st.setInt(2, employeeId);
            st.setString(3, reservationDate);
            st.setString(4, startDate);
            st.setString(5, endDate);
            st.setString(6, status);
            st.executeUpdate();
        } catch (SQLException | AssetNotFoundException | EmployeeNotFoundException | AssetNotMaintainException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean withdrawReservation(int reservationId) {

        String query="DELETE from reservations WHERE reservation_id=?";

        try(Connection conn = DBConnUtil.getConnection()) {
            PreparedStatement ptsmt=conn.prepareStatement(query);
            ptsmt.setInt(1, reservationId);
            ptsmt.executeUpdate();
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

}