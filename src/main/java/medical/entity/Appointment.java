package medical.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "appointment_id_gen")
    @SequenceGenerator(name = "appointment_id_gen", sequenceName = "appointment_id_seq", allocationSize = 1)
    private Long id;

    private LocalDate date;


    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private Patient patient;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private Doctor doctor;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private Department department;



    public Appointment(LocalDate date) {
        this.date = date;
    }

}