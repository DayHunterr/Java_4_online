package ua.com.alevel;

public class Client {

    private String name;
    private String phone;
    private String email;
    private Subscription sub;


    public void setName(String name) {
            this.name = name;
    }

    public void setPhone(String phone) {
            this.phone = phone;
        }

    public void setEmail(String email) {
            this.email = email;
    }

    public void setSub(Subscription sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Subscription getSub() {
        return sub;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sub=" + sub +
                '}';
    }
}


