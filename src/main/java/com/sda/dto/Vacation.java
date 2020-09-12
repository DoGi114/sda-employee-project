package com.sda.dto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "vacations")
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

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public LocalDate getStarts() {
        return starts;
    }

    public void setStarts(LocalDate starts) {
        this.starts = starts;
    }

    public LocalDate getEnds() {
        return ends;
    }

    public void setEnds(LocalDate ends) {
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
