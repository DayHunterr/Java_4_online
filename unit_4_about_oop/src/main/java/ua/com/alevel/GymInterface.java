package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GymInterface {

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            crud(reader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create user, please enter 1");
        System.out.println("If you want update user, please enter 2");
        System.out.println("If you want find user, please enter 3");
        System.out.println("If you want delete user, please enter 4");
        System.out.println("If you want find all users, please enter 5");
        System.out.println("If you want close application, please enter 6");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1":
                create(reader);
                break;
            case "2": {
                if (GymStorage.getClients().isEmpty())
                    System.out.println("System does not contain any clients");
                else update(reader);
                break;
            }
            case "3":
                findByEmail(reader);
                break;
            case "4":
                deleteAllClientByEmail(reader);
                break;
            case "5":
                findAll();
                break;
            case "6":
                stop();
                break;
            default:
                System.out.println("Wrong input");
                break;
        }
        menu();
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Enter email of client which you want to update");
        String email = reader.readLine();
        Client client = GymStorage.findByEmail(email);
        if (client == null) {
            System.out.println("Client with such email does not exist");
            return;
        }
        while (true) {
            System.out.println(client);
            System.out.println("What do you want to update?");
            System.out.println("If you want to update email please enter 1");
            System.out.println("If you want to update phone number please enter 2");
            System.out.println("If you want to update subscription please enter 3");
            System.out.println("If you want to close menu enter 4");
            String update = reader.readLine();
            switch (update) {
                case "1": {
                    validateEmail(reader, client);
                    break;
                }
                case "2": {
                    validatePhone(reader, client);
                    break;
                }
                case "3":
                    subscription(reader, client);
                    break;
                case "4": {
                    return;
                }
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private void create(BufferedReader reader) throws IOException {
        Client client = new Client();
        System.out.println("Create user");

        validateName(reader, client);
        validateEmail(reader, client);
        validatePhone(reader, client);
        subscription(reader, client);

        GymStorage.addClient(client);
    }

    private void subscription(BufferedReader reader, Client client) throws IOException {
        System.out.println("What do you want?");
        System.out.println("If you want to buy subscription enter 1");
        System.out.println("If you want to delete subscription plan enter 2");
        String select = reader.readLine();
        switch (select) {
            case "1": {
                client.setSub(Subscription.SUBSCRIBER);
                System.out.println("Your subscription has been updated!");
                break;
            }
            case "2": {
                client.setSub(Subscription.UNSUBSCRIBER);
                System.out.println("Your subscription has been deleted!");
                break;
            }
            default:
                System.out.println("Wrong input");
                break;
        }
    }

    private void validateName(BufferedReader reader, Client client) throws IOException {
        while (true) {
            System.out.println("Please enter name");
            String name = reader.readLine();
            if (!name.matches(".*\\d.*")) {
                client.setName(name);
                break;
            } else {
                System.out.println("Invalid name");
            }
        }
    }

    private void validateEmail(BufferedReader reader, Client client) throws IOException {
        String email;
        while (true) {
            System.out.println("Please enter email");
            email = reader.readLine();
            if (GymStorage.findByEmail(email) == null) {
                if (email.matches("^(.+)@(.+)$")) {
                    client.setEmail(email);
                    break;
                } else {
                    System.out.println("Invalid email");
                }
            } else {
                System.out.println("Email is already exists");
            }
        }

    }

    private void validatePhone(BufferedReader reader, Client client) throws IOException {
        while (true) {
            System.out.println("Please enter phone");
            String phone = reader.readLine();
            if (GymStorage.findByPhone(phone) == null) {
                if (phone.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")) {
                    client.setPhone(phone);
                    break;
                } else {
                    System.out.println("Invalid phone");
                }
            } else {
                System.out.println("Phone is already exists");
            }
        }
    }

    private void findByEmail(BufferedReader reader) throws IOException {
        System.out.println("Find user by email");
        String email = reader.readLine();
        Client client = GymStorage.findByEmail(email);
        if (client == null) {
            System.out.println("Client with such email does not exist");
        } else {
            System.out.println(client);
        }
    }


    private void deleteAllClientByEmail(BufferedReader reader) throws IOException {
        System.out.println("Delete user by email");
        String email = reader.readLine();
        if (GymStorage.deleteClient(email)) {
            System.out.println("Client successfully deleted");
        } else {
            System.out.println("Client with such email does not exist");
        }
    }

    private void findAll() {
        System.out.println("Find all users");
        List<Client> clients = GymStorage.getClients();
        if (clients.isEmpty()) {
            System.out.println("System does not contain any clients");
        } else {
            clients.forEach(System.out::println);
        }
    }

    private void stop() {
        System.exit(0);
    }
}
