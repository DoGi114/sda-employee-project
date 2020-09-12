package com.sda.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String position;
    @Column
    private Double salary;
    @Column
    private LocalDate birthYear;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Vacation> vacations;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String position, Double salary, LocalDate birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(birthYear, employee.birthYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, position, salary, birthYear);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", birthYear=" + birthYear +
                '}';
    }
}
