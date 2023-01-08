package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
public class GymStorage {

    private static List<Client> clients = new ArrayList<>();

    private GymStorage() {}

    public static List<Client> getClients() {return clients;}

    public static void addClient(Client client) {clients.add(client);}


    public static boolean deleteClient(String email) {
       return clients.removeIf(client -> client.getEmail().equals(email));

    }

//G

    public static Client findByEmail(String email) {
        return clients.
                stream().
                filter(client -> client.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public static Client findByPhone(String phone) {
        return clients.stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
    }
}















