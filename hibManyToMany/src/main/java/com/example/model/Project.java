package com.example.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@Table(name="Project_Table")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Project_ID")
    private int projectId;

    @Column(name = "Project_Name")
    private String projectName;

    @ManyToMany(targetEntity = Employee.class)
    @JoinTable(name = "Employee_Project",
            joinColumns = {@JoinColumn(name = "pid")},
            inverseJoinColumns = {@JoinColumn(name = "eid")}
    )
    private List<Employee> employees;

    public  Project(){}

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
