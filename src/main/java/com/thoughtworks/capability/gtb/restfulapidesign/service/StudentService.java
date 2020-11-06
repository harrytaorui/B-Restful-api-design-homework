package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final int GROUP_NUM = 6;
    private List<Student> studentList = new ArrayList<>();
    private List<Group> groupList = createGroups();
    private Integer studentId = 1;

    public void addStudent(Student student) {
        student.setId(studentId);
        studentList.add(student);
        studentId ++;
    }

    public Student getStudent(Integer id) throws Exception {
        Optional<Student> result = studentList.stream().filter(student -> student.getId() == id).findFirst();
        if (!result.isPresent()) {
            throw new Exception("Student not found");
        }
        return result.get();
    }

    public void updateStudent(Integer id, Student student) throws Exception {
        Student current = getStudent(id);
        current.setName(student.getName());
        current.setGender(student.getGender());
        current.setNote(student.getNote());
    }

    public void deleteStudent(Integer id) throws Exception {
        Student student = getStudent(id);
        studentList.remove(student);
    }

    public List<Student> getStudentList(String gender) {
        List<Student> newList = new ArrayList<>(studentList);
        if (StringUtils.isEmpty(gender)) {
            return newList;
        }
        List<Student> result = newList.stream()
                .filter(student -> student.getGender().equals(gender))
                .collect(Collectors.toList());
        return result;
    }

    public List<Group> getGroups() {
        return new ArrayList<>(groupList);
    }

    public void regroup() {
        groupList.forEach(group -> group.setStudentList(new ArrayList<>()));
        List<Student> tmp = new ArrayList<>(studentList);
        Collections.shuffle(tmp);
        for (int i = 0, j = 0; i < tmp.size(); i++) {
            j = j == GROUP_NUM ? 0 : j;
            groupList.get(j).getStudentList().add(tmp.get(i));
            j++;
        }
    }

    private List<Group> createGroups() {
        List<Group> tmp = new ArrayList<>();
        for (int i = 1; i < GROUP_NUM + 1; i++) {
            tmp.add(Group.builder()
                    .id(i)
                    .name("Team " + i)
                    .studentList(new ArrayList<>())
                    .build());
        }
        return tmp;
    }

    public void updateGroupName(Integer id, String name) {
        Group selected = groupList.get(id - 1);
        selected.setName(name);
    }
}
