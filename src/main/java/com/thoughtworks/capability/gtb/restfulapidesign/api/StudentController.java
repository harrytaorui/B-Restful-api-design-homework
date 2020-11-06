package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable Integer id) throws Exception {
        return studentService.getStudent(id);
    }

    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent(@PathVariable Integer id, Student student) throws Exception {
        studentService.updateStudent(id, student);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Integer id) throws Exception {
        studentService.deleteStudent(id);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudentList(@RequestParam(required = false) String gender) {
        return studentService.getStudentList(gender);
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void regroup() {
        studentService.regroup();
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getGroups() {
        return studentService.getGroups();
    }

    @PatchMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGroupName(@PathVariable Integer id, @RequestParam String name) {
        studentService.updateGroupName(id, name);
    }
}
