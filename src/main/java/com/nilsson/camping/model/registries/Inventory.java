package com.nilsson.camping.model.registries;

import com.nilsson.camping.data.DataHandler;
import com.nilsson.camping.model.items.Gear;
import com.nilsson.camping.model.items.RecreationalVehicle;
import com.nilsson.camping.model.items.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

    private List<RecreationalVehicle> recreationalVehicleList = new ArrayList<>();
    private List<Gear> gearList = new ArrayList<>();

    private Inventory() {
        loadGearFromDataHandler();
        loadRecreationalVehiclesFromDataHandler();
    }

    public static Inventory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Inventory INSTANCE = new Inventory();
    }

    public List<RecreationalVehicle> getAvailableRecreationalVehicleList() {
        return recreationalVehicleList.stream().filter(rv -> !rv.isRented())
                .collect(Collectors.toList());
    }

    public List<RecreationalVehicle> getRecreationalVehicleList() {
        return recreationalVehicleList;
    }

    public List<Gear> getGearList() {
        return gearList;
    }

    public List<Gear> getAvailableGearList() {
        return gearList.stream().filter(g -> !g.isRented())
                .collect(Collectors.toList());
    }

    public void addRecreationalVehicle(RecreationalVehicle rv) {
        this.recreationalVehicleList.add(rv);
        DataHandler.saveRecreationalVehicle(this.recreationalVehicleList);
    }

    public void addGear(Gear gear) {
        this.gearList.add(gear);
        DataHandler.saveGear(this.gearList);
    }

    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        result.addAll(recreationalVehicleList);
        result.addAll(gearList);
        return result;
    }

    // Find by ID
    public Item findItemById(int id) {
        for (RecreationalVehicle rv : recreationalVehicleList) {
            if (rv.getItemId() == id) return rv;
        }
        for (Gear g : gearList) {
            if (g.getItemId() == id) return g;
        }
        return null;
    }

    private void loadRecreationalVehiclesFromDataHandler() {
        this.recreationalVehicleList = DataHandler.loadRecreationalVehicles();
    }

    private void loadGearFromDataHandler() {
        this.gearList = DataHandler.loadGear();
    }

    public boolean removeRecreationalVehicle(RecreationalVehicle rv) {
        boolean wasRemoved = this.recreationalVehicleList.remove(rv);
        if (wasRemoved) {
            DataHandler.saveRecreationalVehicle(this.recreationalVehicleList);
        }
        return wasRemoved;
    }

    public boolean removeGear(Gear gear) {
        boolean wasRemoved = this.gearList.remove(gear);
        if (wasRemoved) {
            DataHandler.saveGear(this.gearList);
        }
        return wasRemoved;
    }
}