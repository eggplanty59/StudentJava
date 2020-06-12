package ru.studentjava.service;

import org.springframework.stereotype.Service;
import ru.studentjava.enity.Student;
import ru.studentjava.exceptions.EntityAlreadyExistsException;
import ru.studentjava.exceptions.EntityIllegalArgumentException;
import ru.studentjava.exceptions.EntityNotFoundException;
import ru.studentjava.exceptions.EntityPassportExistException;
import ru.studentjava.jpa.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Object id) {
        Student student;
        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Integer parsedId;
        try {
            parsedId = Integer.valueOf(id.toString());
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор к нужному типу, текст ошибки: %s", ex));
        }
        student = studentRepository.findOne(parsedId);

        if (student == null) {
            throw new EntityNotFoundException(Student.TYPE_NAME, parsedId);
        }
        return student;
    }

    public Student create(Student student) {
        if (student == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (student.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Student existedStudent = studentRepository.findOne(student.getId());
        if (existedStudent != null) {
            throw new EntityAlreadyExistsException(Student.TYPE_NAME, existedStudent.getId());
        }
        List<Student> studentsByPassport = studentRepository.findByPassport(student.getPassport());
        if(studentsByPassport.size()>0){
            throw new EntityPassportExistException(Student.TYPE_NAME, student.getPassport());
        }
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        if (student == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (student.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Student existedStudent = studentRepository.findOne(student.getId());
        if (existedStudent == null) {
            throw new EntityNotFoundException(Student.TYPE_NAME, student.getId());
        }
        List<Student> studentsByPassport = studentRepository.findByPassport(student.getPassport());
        if(studentsByPassport.size()>0){
            throw new EntityPassportExistException(Student.TYPE_NAME, student.getPassport());
        }
        studentRepository.save(student);
        return existedStudent;
    }

    public void delete(Object id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }
}
