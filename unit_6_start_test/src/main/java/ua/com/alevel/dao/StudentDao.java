package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.util.List;

public interface StudentDao {

    public abstract void create(Student student);
    void update(Student student);
    void delete(String id);
    Student findById(String id);
    List<Student> findAll();
    boolean existByEmail(String email);

//    default void test() {
//
//    }
}
