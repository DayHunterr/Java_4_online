package ua.com.alevel.en;

public enum Gender {

    MALE, FEMALE;

    boolean isMan(Gender gender) {
        return gender.equals(Gender.MALE);
    }
}
