package medical.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "hospital_id_gen")
    @SequenceGenerator(name = "hospital_id_gen", sequenceName = "hospital_id_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = " Name should not be empty")
    @Size(min = 2, max = 50, message = " Name should be between 2 and 50 characters")
    private String name;

    private String address;
    private String image;


    @OneToMany(mappedBy = "hospital",cascade = {ALL}, fetch = LAZY)
    private List<Doctor> doctors ;
    public void addDoctor(Doctor doctor){
        if (doctors == null){
            doctors = new ArrayList<>();
        }else {
            doctors.add(doctor);
        }
    }

    @OneToMany(mappedBy = "hospital",cascade = ALL ,fetch = LAZY)
    private List<Patient> patients = new ArrayList<>();
    public void addPatient(Patient patient){
        if (patients == null){
            patients = new ArrayList<>();
        }else {
            patients.add(patient);
        }
    }

    @OneToMany(mappedBy = "hospital",cascade = ALL, fetch = LAZY)
    private List<Department> departments ;
    public void addDepartment(Department department){
        if (departments == null){
            departments = new ArrayList<>();
        }
        departments.add(department);
    }

    @OneToMany(cascade = ALL, fetch = LAZY)
    private List<Appointment> appointments ;
    public  void  addAppointment(Appointment appointment){
        if(appointments == null){
            appointments = new ArrayList<>();
        }appointments.add(appointment);
    }

}


