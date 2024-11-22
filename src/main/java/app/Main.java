package app;

import dao.AssetManagementService;
import dao.AssetManagementServiceImpl;
import entity.Asset;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AssetManagementService imp = new AssetManagementServiceImpl();
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("--- Digital Asset Management System ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Update Asset");
            System.out.println("3. Delete Asset");
            System.out.println("4. Allocate Asset");
            System.out.println("5. Deallocate Asset");
            System.out.println("6. Perform Maintenance");
            System.out.println("7. Reserve Asset");
            System.out.println("8. Withdraw Asset");
            System.out.println("9. Exit");
            System.out.print("Enter the operation to perform: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addAsset(imp, sc);
                    break;
                case 2:
                    updateAsset(imp, sc);
                    break;
                case 3:
                    deleteAsset(imp, sc);
                    break;
                case 4:
                    allocateAsset(imp, sc);
                    break;
                case 5:
                    deallocateAsset(imp, sc);
                    break;
                case 6:
                    performMaintenance(imp, sc);
                    break;
                case 7:
                    reserveAsset(imp, sc);
                    break;
                case 8:
                    withdrawReservation(imp, sc);
                    break;
                case 9:
                    System.out.println("Exiting ... Bye");
                    sc.close();
                    return;
                default:
                    System.out.println("Incorrect Option. Please try again.");
            }
        }
    }

    private static void addAsset(AssetManagementService imp, Scanner sc) {
        System.out.print("Enter the assetId: ");
        int assetId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Asset Name: ");
        String assetName = sc.nextLine();
        System.out.print("Enter the type of the asset (laptop, vehicle, equipment, etc..): ");
        String type = sc.nextLine();
        System.out.print("Enter the Serial number of an asset: ");
        String serialNumber = sc.next();
        sc.nextLine();
        System.out.print("Enter the Purchase Date (yyyy-mm-dd): ");
        String purchaseDate = sc.nextLine();
        System.out.print("Enter the Location: ");
        String location = sc.nextLine();
        System.out.print("Enter the Status of an asset (in use, decommissioned, under maintenance): ");
        String status = sc.nextLine();
        System.out.print("Enter the ownerId: ");
        int ownerId = sc.nextInt();

        Asset asset = new Asset(assetId, assetName, type, serialNumber, purchaseDate, location, status, ownerId);
        boolean result = imp.addAsset(asset);
        if (result) {
            System.out.println("Asset Added Successfully");
        } else {
            System.out.println("Asset is not added");
        }

    }

    private static void updateAsset(AssetManagementService imp, Scanner sc) {

        System.out.print("Enter the Asset id to update: ");
        int assetId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Asset Name to update: ");
        String assetName = sc.nextLine();
        System.out.print("Enter the type of the asset to update (laptop, vehicle, equipment): ");
        String type = sc.nextLine();
        System.out.print("Enter the Serial number of an asset to update: ");
        String serialNumber = sc.next();
        sc.nextLine();
        System.out.print("Enter the Purchase Date (yyyy-mm-dd) to update: ");
        String purchaseDate = sc.nextLine();
        System.out.print("Enter the Location to update: ");
        String location = sc.nextLine();
        System.out.print("Enter the Status of an asset (in use, decommissioned, under maintenance) to update: ");
        String status = sc.nextLine();
        System.out.print("Enter the ownerId to update: ");
        int ownerId = sc.nextInt();

        Asset asset = new Asset(assetId, assetName, type, serialNumber, purchaseDate, location, status, ownerId);
        boolean result = imp.updateAsset(asset);
        if (result) {
            System.out.println("Asset Updated Successfully");
        } else {
            System.out.println("Asset is not Updated");
        }
    }

    private static void deleteAsset(AssetManagementService imp, Scanner sc) {
        System.out.print("Enter the Asset Id to delete: ");
        int assetId = sc.nextInt();

        boolean result = imp.deleteAsset(assetId);
        if (result) {
            System.out.println("Asset Deleted Successfully");
        } else {
            System.out.println("Asset is not Deleted");
        }
    }

    private static void allocateAsset(AssetManagementService imp, Scanner sc) {
        System.out.print("Enter the Asset Id to Allocate Asset: ");
        int assetId = sc.nextInt();
        System.out.print("Enter the Employee Id to Allocate Asset: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Date to Allocate Asset: ");
        String allocationDate = sc.nextLine();
        boolean result = imp.allocateAsset(assetId, employeeId, allocationDate);
        if (result) {
            System.out.println("Asset Allocated Successfully");
        } else {
            System.out.println("Asset is not Allocated");
        }
    }

    private static void deallocateAsset(AssetManagementService imp, Scanner sc) {
        System.out.print("Enter the Asset Id to deallocate Asset: ");
        int assetId = sc.nextInt();
        System.out.print("Enter the Employee Id to deallocate Asset: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Return Date to deallocate Asset: ");
        String returnDate = sc.nextLine();
        boolean result = imp.deallocateAsset(assetId, employeeId, returnDate);
        if (result) {
            System.out.println("Asset Deallocated Successfully");
        } else {
            System.out.println("Asset is not Deallocated");
        }
    }

    private static void performMaintenance(AssetManagementService imp, Scanner sc) {
        System.out.print("Enter the Asset Id to Perform Maintenance: ");
        int assetId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Maintenance Date: ");
        String maintenanceDate = sc.nextLine();
        System.out.print("Enter the Description: ");
        String description = sc.nextLine();
        System.out.print("Enter the Cost: ");
        double cost = sc.nextDouble();
        boolean result = imp.performMaintenance(assetId, maintenanceDate, description, cost);
        if (result) {
            System.out.println("Asset Maintenance record added Successfully");
        } else {
            System.out.println("Asset Maintenance record is not added");
        }
    }

    private static void reserveAsset(AssetManagementService imp, Scanner sc) {
        System.out.print("Enter the Asset Id for Reservation: ");
        int assetId = sc.nextInt();
        System.out.print("Enter the Employee id for Reservation: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Reservation Date: ");
        String reservationDate = sc.nextLine();
        System.out.print("Enter the Start Date for Reservation: ");
        String startDate = sc.nextLine();
        System.out.print("Enter the End Date for Reservation: ");
        String endDate = sc.nextLine();
        System.out.print("Enter the status of the asset: ");
        String status = sc.nextLine();
        boolean result = imp.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate, status);
        if (result) {
            System.out.println("Asset Reserved Successfully");
        } else {
            System.out.println("Asset is not reserved");
        }
    }

    private static void withdrawReservation(AssetManagementService imp, Scanner sc) {

        System.out.print("Enter the Reservation id to withdraw Reservation: ");

        int reservationId = sc.nextInt();
        boolean result = imp.withdrawReservation(reservationId);

        if (result) {
            System.out.println("Asset Reservation is withdrawn Successfully");
        } else {
            System.out.println("Asset Reservation is not withdrawn");
        }
    }
}
