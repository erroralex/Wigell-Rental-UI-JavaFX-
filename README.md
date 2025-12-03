# 🏕️ Wigell Camping Admin Portal

This is a comprehensive management application designed for overseeing camping memberships, inventory, vehicle rentals, and financial profits. The program is built entirely using **Java** with **programmatic JavaFX** for the graphical user interface.

---

## ✨ Key Features

### 1. ⚙️ Inventory & Rental Management
The system tracks two main categories of rentable items, all defined by their daily price and capacity:

* **Gear (e.g., Tents, Backpacks):** Items defined by `model`, `dailyPrice`, `type`, and `capacity`.
* **Recreational Vehicles (RVs/Caravans):** Defined by `make`, `model`, `dailyPrice`, `year`, `type`, and `capacity`.
* **Rentals:** A dedicated view for processing and managing active rental transactions.

### 2. 🧑‍🤝‍🧑 Membership Management
The application handles user data for the **Wigell Camping Premium Members Club**:

* **User Data:** Stores unique four-digit IDs, names, and contact information.
* **Membership Levels:** Supports different tiers, including `Premium`, `Standard`, and `Student`.

### 3. 📈 Profit and Analytics
A dedicated "Profits" view provides financial oversight of the business operations:

* **Income Summary:** Displays the **Income Today** and the **Total Recorded Income**.
* **Bar Chart Visualization:** Renders a responsive **Daily Income Over Time** bar chart to visualize financial trends.

### 4. 🎨 UI & Technology
The entire user interface is constructed dynamically using Java code (not FXML) and features a customizable appearance:

* **Programmatic JavaFX:** The UI is dynamically constructed using Java 8.
* **Theming:** The application supports two built-in themes: a **Dark Mode** (default) and a **Sleek Light Mode** theme, which can be toggled via the sidebar.
  
### 5. Persistence
The application loads and saves members, vehicles, gear, rentals and profit records in .json format.

---

## 🔑 Login Details

* **Username (Example):** `admin`
* **Password (Use):** `0000`
