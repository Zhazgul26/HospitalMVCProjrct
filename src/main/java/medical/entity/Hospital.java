package medical.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String name;
    private String address;
    private String image;


    @OneToMany(mappedBy = "hospital", cascade = {ALL}, fetch = LAZY)
    private List<Doctor> doctors = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", fetch = LAZY)
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = {ALL}, fetch = LAZY)
    private List<Department> departments;

    public void addDepartment(Department department) {
        departments.add(department);
    }

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    private List<Appointment> appointments;
}
