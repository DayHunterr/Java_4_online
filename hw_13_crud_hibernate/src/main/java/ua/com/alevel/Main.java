package ua.com.alevel;

import ua.com.alevel.controller.impl.GarageControllerImpl;

public class Main {
    //just for repo
    public static void main(String[] args) {
        GarageControllerImpl garageControllerImpl = new GarageControllerImpl();
        garageControllerImpl.start();
    }
}
