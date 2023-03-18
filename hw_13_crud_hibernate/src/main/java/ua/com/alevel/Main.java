package ua.com.alevel;

import ua.com.alevel.controller.impl.GarageControllerImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GarageControllerImpl garageControllerImpl = new GarageControllerImpl();
        garageControllerImpl.start();

    }
}
