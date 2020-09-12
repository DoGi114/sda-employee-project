package com.sda.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "vacations")
@Data
public class Vacation {
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee owner;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private LocalDate starts;
    @Column
    private LocalDate ends;

    public Vacation(){

    }

    public Vacation(Employee owner, LocalDate starts, LocalDate ends) {
        this.owner = owner;
        this.starts = starts;
        this.ends = ends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        return id == vacation.id &&
                owner.equals(vacation.owner) &&
                starts.equals(vacation.starts) &&
                ends.equals(vacation.ends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, id, starts, ends);
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "owner=" + owner +
                ", id=" + id +
                ", starts=" + starts +
                ", ends=" + ends +
                '}';
    }
}
