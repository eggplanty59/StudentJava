package ru.studentjava.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.studentjava.enity.Student;
import ru.studentjava.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable String id){
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student){
        return studentService.create(student);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Student update(@RequestBody Student student){
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        studentService.delete(id);
    }
}