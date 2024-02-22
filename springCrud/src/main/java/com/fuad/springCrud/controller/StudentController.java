package com.fuad.springCrud.controller;

import com.fuad.springCrud.entity.Student;
import com.fuad.springCrud.exception.ResourceNotFoundException;
import com.fuad.springCrud.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Student not found for this id ::"+ id));
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id")
                                                 Long id, @RequestBody Student studentDto)
            throws ResourceNotFoundException {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Student not found for this id :: " + id));

        student.setEmail(studentDto.getEmail());
        student.setLastName(studentDto.getLastName());
        student.setFirstName(studentDto.getFirstName());
        student.setGender(studentDto.getGender());
        student.setAge(studentDto.getAge());
        student.setGrade(studentDto.getGrade());
        student.setQualification(studentDto.getQualification());
        student.setId(id);
        final Student updateStudent = studentRepository.save(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable(value = "id")
                                                 Long id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Student not found for this id :: " + id));

        studentRepository.delete(student);

        return ResponseEntity.ok(true);
    }

}
