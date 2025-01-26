# Asset Management Case Study


This project is a comprehensive implementation of an **Asset Management System**, designed to handle various operations like adding, updating, deleting, allocating, and reserving assets, along with maintenance tracking and exception handling.

For a detailed explanation of the project, including use cases, design considerations, and implementation details, please refer to:

📄 **[Asset Management System Explanation.pdf](./Asset%20Management%20System%20Explanation.pdf)**

---

### Features Implemented
1. Add, Update, Delete, Allocate, and Deallocate Assets
2. Maintenance Tracking
3. Asset Reservation with Validation
4. Exception Handling for Edge Cases:
   - `AssetNotFoundException`
   - `EmployeeNotFoundException`
   - `AssetNotMaintainException`

### How to Run the Project
1. Configure the database connection in `db.properties`.
2. Run the main class: `Main`.
3. Use the menu-driven interface for various operations.

---

### Technologies Used
- **Java** for backend logic
- **MySQL** for database management
- **JUnit** for unit testing
- **Custom Exceptions** for robust error handling

---

### Testing
Unit tests are included to verify key functionality:
- Asset creation
- Maintenance records
- Reservation functionality with exception handling

---

We hope this project provides a clear understanding of asset management concepts and their practical implementation!
