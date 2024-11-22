import dao.AssetManagementService;
import dao.AssetManagementServiceImpl;
import entity.Asset;
import myexceptions.EmployeeNotFoundException;
import myexceptions.AssetNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssetManagementTest {
    static AssetManagementService imp = null;

    @BeforeAll
    static void beforeAll() {
        imp = new AssetManagementServiceImpl();
    }

    private Asset createAsset(int assetId, String name, String type, String serialNumber, String purchaseDate, String location, String status, int ownerId) {
        return new Asset(assetId, name, type, serialNumber, purchaseDate, location, status, ownerId);
    }

    @Test
    void testAddAsset() {
        Asset asset1 = createAsset(201, "HP Spectre x360", "Laptop", "HP1234XYZ", "2023-05-22", "Boston", "In Use", 1);
        Asset asset2 = createAsset(202, "Audi Q7", "Vehicle", "AUDI4567AB", "2021-11-05", "Los Angeles", "Decommissioned", 2);
        Asset asset3 = createAsset(203, "Epson L3150 Printer", "Equipment", "EPSON7890LM", "2022-04-13", "San Francisco", "Under Maintenance", 3);
        Asset asset4 = createAsset(204, "Lenovo ThinkPad X1", "Laptop", "LENOVO1122XP", "2023-08-15", "Chicago", "In Use", 4);
        Asset asset5 = createAsset(205, "Toyota Corolla", "Vehicle", "TOYOTA7777XY", "2023-12-01", "Dallas", "In Use", 5);

        assertTrue(imp.addAsset(asset1), "Asset 1 (HP Spectre x360) should be added successfully");
        assertTrue(imp.addAsset(asset2), "Asset 2 (Audi Q7) should be added successfully");
        assertTrue(imp.addAsset(asset3), "Asset 3 (Epson L3150 Printer) should be added successfully");
        assertTrue(imp.addAsset(asset4), "Asset 4 (Lenovo ThinkPad X1) should be added successfully");
        assertTrue(imp.addAsset(asset5), "Asset 5 (Toyota Corolla) should be added successfully");
    }

    @Test
    void performMaintenance() {

        assertTrue(imp.performMaintenance(101, "2024-05-10", "Battery Replacement", 150.00), "Maintenance for asset 101 should be successful");
        assertTrue(imp.performMaintenance(102, "2023-11-25", "Tire Change", 200.00), "Maintenance for asset 102 should be successful");
        assertTrue(imp.performMaintenance(103, "2022-09-15", "Printer Cartridge Replacement", 50.00), "Maintenance for asset 103 should be successful");
        assertTrue(imp.performMaintenance(104, "2024-03-30", "Screen Repair", 300.00), "Maintenance for asset 104 should be successful");
    }

    @Test
    void testReserve() {
        // Test asset reservations
        assertTrue(imp.reserveAsset(1, 1, "2024-11-25", "2024-11-27", "2024-11-29", "approved"), "Asset 1 should be reserved successfully");
        assertTrue(imp.reserveAsset(2, 2, "2024-11-26", "2024-11-28", "2024-11-30", "canceled"), "Asset 2 should be reserved successfully");
        assertTrue(imp.reserveAsset(5, 3, "2024-11-23", "2024-11-26", "2024-11-28", "approved"), "Asset 5 should be reserved successfully");
        assertTrue(imp.reserveAsset(1, 4, "2024-11-25", "2024-11-27", "2024-11-29", "approved"), "Asset 1 should be reserved successfully for employee 4");
        assertTrue(imp.reserveAsset(1, 5, "2024-11-25", "2024-11-27", "2024-11-29", "pending"), "Asset 1 should be reserved successfully for employee 5");
    }

    @Test
    void testEmployeeIdNotFound() {
        int invalidEmployeeId = 9999;
        int assetId = 5;
        String reservationDate = "2024-01-01";
        String startDate = "2024-01-02";
        String endDate = "2024-01-10";
        String status = "Reserved";

        assertThrows(EmployeeNotFoundException.class, () -> {
            imp.reserveAsset(assetId, invalidEmployeeId, reservationDate, startDate, endDate, status);
        }, "Employee with ID " + invalidEmployeeId + " should throw EmployeeNotFoundException");
    }

    @Test
    void testAssetIdNotFound() {
        int invalidAssetId = 9999;
        int employeeId = 1;
        String reservationDate = "2024-01-01";
        String startDate = "2024-01-02";
        String endDate = "2024-01-10";
        String status = "Reserved";

        assertThrows(AssetNotFoundException.class, () -> {
            imp.reserveAsset(invalidAssetId, employeeId, reservationDate, startDate, endDate, status);
        }, "Asset with ID " + invalidAssetId + " should throw AssetNotFoundException");
    }
}
