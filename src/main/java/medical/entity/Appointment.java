package medical.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Patient;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.EAGER;
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


    @ManyToOne(fetch = EAGER)
    private Patient patient;

    @ManyToOne(fetch = EAGER)
    private Doctor doctor;

    @OneToOne(fetch = EAGER, cascade = CascadeType.ALL)
    private Department department;
}