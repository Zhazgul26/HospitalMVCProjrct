package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Hospital;
import medical.repository.AppointmentRepository;
import medical.repository.DoctorRepository;
import medical.repository.HospitalRepository;
import medical.service.DoctorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
private final AppointmentRepository appointmentRepository;
    @Override
    public void save(Long id, Doctor newDoctor) {
        Hospital hospital = hospitalRepository.getById(id);
        hospital.addDoctor(newDoctor);
        newDoctor.setHospital(hospital);
        doctorRepository.save(newDoctor);

    }

    @Override
    public List<Doctor> getAll(Long id) {
        return doctorRepository.getAll(id);
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.getById(id);
    }

    @Override
    public Doctor update(Long doctorId, Doctor doctor) {
        Doctor oldDoctor = getById(doctorId);
        oldDoctor.setFirstName(doctor.getFirstName());
        oldDoctor.setLastName(doctor.getLastName());
        oldDoctor.setEmail(doctor.getEmail());
        oldDoctor.setPosition(doctor.getPosition());
        return doctorRepository.update(oldDoctor);
    }

    @Override
    public void deleteById(Long id) {
        Doctor doctor = getById(id);
        Hospital hospital = doctor.getHospital();
        List<Appointment> appointments = doctor.getAppointments();

        doctor.setDepartments(null);
        appointments.forEach(appointment -> appointment.getDoctor().setAppointments(null));
        appointments.forEach(appointment -> appointment.getPatient().setAppointments(null));
        appointments.forEach(appointment -> appointment.getDepartment().setDoctors(null));
        appointments.forEach(appointment -> appointment.getDoctor().setDepartments(null));
        
        hospital.getAppointments().removeAll(appointments);
        for (int i = 0; i < appointments.size(); i++) {
            appointmentRepository.delete(appointments.get(i).getId());
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Department> getAllDepartmentDoctorById(Long doctorId) {
        return doctorRepository.getAllDepartmentDoctorById(doctorId);
    }
}



