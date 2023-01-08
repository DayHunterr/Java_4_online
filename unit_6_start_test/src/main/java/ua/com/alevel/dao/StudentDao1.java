package ua.com.alevel.dao;

import ua.com.alevel.config.Release;
import ua.com.alevel.db.DbStudent;
import ua.com.alevel.entity.Student;

import java.util.List;

//@Release
public class StudentDao1 implements StudentDao {

    DbStudent dbStudent = DbStudent.getInstance();

    public StudentDao1() {
        System.out.println("StudentDao1");
    }

    @Override
    public void create(Student student) {
        dbStudent.create(student);
    }

    @Override
    public void update(Student student) {
        dbStudent.update(student);
    }

    @Override
    public void delete(String id) {
        dbStudent.delete(id);
    }

    @Override
    public Student findById(String id) {
        return dbStudent.findById(id).get();
    }

    @Override
    public List<Student> findAll() {
        return dbStudent.findAll();
    }

    @Override
    public boolean existByEmail(String email) {
        return dbStudent.existByEmail(email);
    }
}
