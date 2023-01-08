package ua.com.alevel.service;

import ua.com.alevel.config.StudentFactory;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.StudentDao1;
import ua.com.alevel.entity.Student;

import java.util.List;

public class StudentService {

//    @Autowired
//    private StudentDao studentDao = (StudentDao) StudentFactory.getImplementationByClass(StudentDao.class);
    private StudentDao studentDao = new StudentDao1();

    public void create(Student student) {
        if (!existByEmail(student.getEmail())) {
            studentDao.create(student);
        }
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void delete(String id) {
        studentDao.delete(id);
    }

    public Student findById(String id) {
        return studentDao.findById(id);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    private boolean existByEmail(String email) {
        return studentDao.existByEmail(email);
    }
}
