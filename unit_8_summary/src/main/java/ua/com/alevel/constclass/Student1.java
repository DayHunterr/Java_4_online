package ua.com.alevel.constclass;

public class Student1 extends LearnStatus {

    private final String email;
    private final String password;

    public Student1(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        final int a = 10;
        return password;
    }

    public boolean isEffective() {
        return super.effective;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
