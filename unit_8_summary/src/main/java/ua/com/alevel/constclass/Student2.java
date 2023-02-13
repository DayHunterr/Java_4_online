package ua.com.alevel.constclass;

public record Student2(String email, String password) implements Comparable<Student2> {

    @Override
    public int compareTo(Student2 o) {
        return 0;
    }
}
