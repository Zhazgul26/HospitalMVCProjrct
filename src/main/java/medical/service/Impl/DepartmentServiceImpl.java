package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Hospital;
import medical.repository.AppointmentRepository;
import medical.repository.DepartmentRepository;
import medical.repository.DoctorRepository;
import medical.repository.HospitalRepository;
import medical.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    private final AppointmentRepository appointmentRepository;

    @Override
    public void save(Long id, Department department) {
        Hospital hospital = hospitalRepository.getById(id);
        hospital.addDepartment(department);
        department.setHospital(hospital);
        departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepository.getAll(id);
    }

    @Override
    public void deleteById(Long id) {
        Department department = departmentRepository.getById(id);
        Hospital hospital = department.getHospital();

        List<Appointment> appointments = appointmentRepository.getAll(hospital.getId());
        List<Appointment> appointmentList = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getDepartment().getId().equals(id)) {
                appointmentList.add(appointment);
            }
        }
        appointmentList.forEach(appointment -> appointment.getDoctor().setAppointments(null));
        appointmentList.forEach(appointment -> appointment.getPatient().setAppointments(null));

        hospital.getAppointments().removeAll(appointmentList);
        for (int i = 0; i < appointmentList.size(); i++) {
            appointmentRepository.delete(appointmentList.get(i).getId());
        }
        departmentRepository.deleteById(id);
    }


    @Override
    public Department getById(Long id) {
        return departmentRepository.getById(id);
    }

    @Override
    public void update(Long id, Department newDepartment) {
        Department oldDepartment = getById(id);
        ;
        oldDepartment.setName(newDepartment.getName());
        departmentRepository.update(oldDepartment);
    }


    @Override
    public void assignDoctor(Long doctorId, Doctor doctor) {
        Department department = departmentRepository.getById(doctor.getDepartmentId());
        Doctor oldDoctor = doctorRepository.getById(doctorId);
        oldDoctor.addDepartment(department);
        department.addDoctor(oldDoctor);
        departmentRepository.assignDoctor(oldDoctor);
    }
}
