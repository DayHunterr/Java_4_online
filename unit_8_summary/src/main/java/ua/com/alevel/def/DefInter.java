package ua.com.alevel.def;

import ua.com.alevel.en.Gender;

public abstract interface DefInter {

    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";

    void def();

    default void def1() {
        System.out.println("DefInter.def1");
    }

    default boolean isMan(String gender) {
        return gender.equals(MALE);
    }

    default boolean isMan(Gender gender) {
        return gender.equals(Gender.MALE);
    }
}
