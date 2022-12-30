package ua.com.alevel.dao;

import ua.com.alevel.config.Release;
import ua.com.alevel.db.DbStudent2;
import ua.com.alevel.entity.Student;

import java.util.List;

@Release
public class StudentDao2 implements StudentDao {

    DbStudent2 dbStudent = DbStudent2.getInstance();

    public StudentDao2() {
        System.out.println("StudentDao2");
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
        return false;
    }
}
