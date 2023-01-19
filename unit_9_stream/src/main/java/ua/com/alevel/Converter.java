package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Converter {

    public void run() {
        List<StudentEntity> studentEntities = generateStudentEntities();
        System.out.println("studentEntities = " + studentEntities);

        System.out.println();

        // old style
//        List<StudentDto> studentDtoList = studentEntities
//                .stream()
//                .map(studentEntity -> {
//                    StudentDto studentDto = new StudentDto();
//                    studentDto.setId(studentEntity.id());
//                    return studentDto;
//                })
//                .toList();

//        List<StudentDto> studentDtoList = studentEntities
//                .stream()
//                .map(studentEntity -> new StudentDto(studentEntity))
//                .collect(Collectors.toList());

        List<StudentDto> studentDtos = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntities) {
            studentDtos.add(new StudentDto(studentEntity));
        }

        List<StudentDto> studentDtoList = studentEntities
                .stream()
                .map(StudentDto::new)
                .toList();
        System.out.println("studentDtoList = " + studentDtoList);
    }

    private List<StudentEntity> generateStudentEntities() {
        return List.of(
                new StudentEntity(UUID.randomUUID().toString(), "Ivan", UUID.randomUUID().toString(), 20),
                new StudentEntity(UUID.randomUUID().toString(), "Petro", UUID.randomUUID().toString(), 20),
                new StudentEntity(UUID.randomUUID().toString(), "Maksim", UUID.randomUUID().toString(), 20),
                new StudentEntity(UUID.randomUUID().toString(), "Billi", UUID.randomUUID().toString(), 20),
                new StudentEntity(UUID.randomUUID().toString(), "Govard", UUID.randomUUID().toString(), 20)
        );
    }
}
