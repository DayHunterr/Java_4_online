package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
//        ListTest.test();
//        MapTest.test();
//        SetTest.test();
//        AnonimTest.test();

        GenericStudent<Student> studentGenericStudent = new GenericStudent<>();
        studentGenericStudent.add(new Student("Ivan", "Ivanov", 24));
        studentGenericStudent.add(new SchoolBoy("Ivan", "Ivanov", 24));
        System.out.println("studentGenericStudent = " + studentGenericStudent.getList());

        MatList<Double> list = new MatList<>();
        list.add(5.0);
        list.add(5.0, 8.9, 6.88, -9.345, 3.14);

//        GenericStudent<String> student1 = new GenericStudent<>();

//        Student student1 = new Student();
//        Student student2 = new Student();
//
//        student1.setFirstName("Maksim");
//        student1.setLastName("Pupkin");
//
//        student2.setFirstName("Ivan");
//        student2.setLastName("Pupkin");
//
//        System.out.println("equals " + student1.equals(student2));
//        System.out.println("student1 = " + student1);
//        System.out.println("student1 = " + student1.hashCode());
//        System.out.println("student2 = " + student2.hashCode());
    }
}
