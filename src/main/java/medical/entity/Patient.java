package medical.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medical.entity.enams.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "patient_id_gen")
    @SequenceGenerator(name = "patient_id_gen", sequenceName = "patient_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "First Name should not be empty")
    @Size(min = 2, max = 50, message = " First should be between 2 and 50 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last Name should not be empty")
    @Size(min = 2, max = 50, message = " Last  Name should be between 2 and 50 characters")
    private String lastName;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\+996\\d{3} \\d{3} \\d{3} $",
            message = "The phone number must be 12 digits long and start with +996 !!!")
    private String phoneNumber;

    private Gender gender;

    @Column(unique = true)
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST},
            fetch = EAGER)
    private Hospital hospital;

    @OneToMany(mappedBy = "patient", cascade = {ALL}, fetch = LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment){
        if(appointments==null){
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }
}