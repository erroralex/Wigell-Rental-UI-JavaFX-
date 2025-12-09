# 🏕️ Wigell Camping Admin Portal

**Wigell Camping Admin Portal** is a robust, Java-based management system designed for the administration of a premium camping membership club. It provides comprehensive tools for managing inventory (vehicles & gear), tracking rentals, handling membership data, and visualizing financial performance.

The application is built using **Pure JavaFX** (No FXML) with a focus on Object-Oriented Design principles, layered architecture, and custom UI styling.

---

## ✨ Key Features

### 1. ⚙️ Dynamic Inventory & Rental System
* **Polymorphic Inventory:** Manages a diverse catalog of rentable items, abstracting generic `Gear` (Tents, Backpacks) and `Vehicles` (RVs, Caravans) under a unified system.
* **Smart Availability:** Automatically filters items based on their current rental status.
* **Rental Lifecycle:** Full workflow support for creating new rentals, calculating costs, and processing returns.

### 2. 💳 Strategy-Based Pricing Engine
The system implements the **Strategy Pattern** to calculate rental costs dynamically based on membership tiers:
* **Standard:** Base daily rates.
* **Student:** Applies a **20% discount** to all rentals.
* **Premium:** Applies a **20% surcharge** (reflecting premium service add-ons).

### 3. 🧑‍🤝‍🧑 Membership Management
* **CRUD Operations:** Create, Read, Update, and Delete members with ease.
* **History Tracking:** View detailed rental history for every individual member.
* **Data Integrity:** Input validation ensures data consistency across the application.

### 4. 📈 Analytics Dashboard
* **Real-time Profits:** Instantly calculates income for "Today" vs "Total" historical revenue.
* **Data Visualization:** Features a dynamic **Bar Chart** that renders rental income over the last 14 days.

### 5. 🎨 Custom UI & UX
* **Programmatic JavaFX:** The entire UI is built in pure Java code, offering strict type safety and fine-grained control over layout behavior.
* **Theming Engine:** Supports hot-swapping between **Dark Mode** (Default) and **Light Mode** CSS themes.
* **Session Timer:** A multi-threaded background service tracks active session time in the custom title bar.

---

## 🛠️ Technical Architecture

This project follows a strict **Model-View-Service** architecture to ensure separation of concerns and high cohesion.

### Design Patterns Used
* **Strategy Pattern:** Used for `IPricePolicy` to decouple pricing logic from the rental service.
* **Singleton Pattern:** Ensures `Inventory`, `MemberRegistry`, and `RentalRegistry` have a single shared instance throughout the application lifecycle.
* **Observer Pattern:** Utilized via JavaFX Observables to update the UI (Charts, Tables) instantly when underlying data changes.

### Technology Stack
* **Language:** Java 8+
* **GUI Framework:** JavaFX (Controls, Charts, Graphics)
* **Build Tool:** Maven
* **Persistence:** JSON (via Jackson Library) for saving/loading data.
* **Icons:** Ikonli (FontAwesome)

---

## 🔑 Login Credentials

The system includes a login gatekeeper. Use the following credentials to access the dashboard:

* **Username:** `admin` (or any name you prefer)
* **Password:** `0000`

---

## 📂 Project Structure

```
src/
  └── com.nilsson.camping
      ├── app/            # Application entry, Session & Language management
      │   ├── MainApp.java
      │   ├── UserSession.java
      │   └── LanguageManager.java
      │
      ├── data/           # JSON Persistence & File handling
      │   ├── DataHandler.java
      │   └── ProfitsHandler.java
      │
      ├── model/          # Data entities
      │   ├── items/      # Inheritance: Item -> Vehicle/Gear
      │   ├── policies/   # Strategy Pattern: IPricePolicy implementations
      │   ├── registries/ # Singleton data stores (Inventory, Members)
      │   └── Member.java, Rental.java, DailyProfit.java
      │
      ├── service/        # Business Logic Layer
      │   ├── InventoryService.java
      │   ├── MembershipService.java
      │   ├── ProfitsService.java
      │   ├── RentalService.java
      │   └── SessionTimerService.java
      │
      └── ui/             # JavaFX User Interface
          ├── dialogs/    # Pop-up windows (Add/Edit forms)
          ├── views/      # Main content screens (Tabs)
          ├── CustomTitleBar.java
          ├── RootLayout.java
          ├── SideNavigation.java
          └── UIUtil.java

resources/
  ├── data/json/          # Database files (.json)
  │   ├── gear.json
  │   ├── members.json
  │   ├── profits.json
  │   ├── rentals.json
  │   └── vehicles.json
  │
  ├── i18n/               # Internationalization bundles
  │   ├── messages_en.properties
  │   └── messages_sv.properties
  │
  └── *.css, *.png        # Theme stylesheets & Images
```
---
