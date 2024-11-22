package dao;
import entity.Asset;

public interface AssetManagementService {


    boolean addAsset(Asset asset);
    boolean updateAsset(Asset asset);
    boolean deleteAsset(int assetId);
    boolean allocateAsset(int assetId,int employeeId, String allocationDate);
    boolean deallocateAsset(int assetId, int employeeId, String returnDate);
    boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost);

    boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate, String status);

    boolean withdrawReservation(int reservationId);

}


