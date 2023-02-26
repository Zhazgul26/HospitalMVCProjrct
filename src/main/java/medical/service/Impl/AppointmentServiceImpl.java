package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Hospital;
import medical.repository.*;
import medical.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final PatientRepository patientsRepository;
    private final DoctorRepository doctorRepository;


    @Override
    public void save(Long id, Appointment appointment) {
        Hospital hospital = hospitalRepository.getById(id);
        Appointment newAppointment = new Appointment();
        newAppointment.setId(appointment.getId());
        newAppointment.setDate(appointment.getDate());
        newAppointment.setDoctor(doctorRepository.getById(appointment.getDoctorId()));
        newAppointment.setDepartment(departmentRepository.getById(appointment.getDepartmentId()));
        newAppointment.setPatient(patientsRepository.getById(appointment.getPatientId()));
        hospital.addAppointment(newAppointment);
        System.out.println(appointmentRepository.save(newAppointment));
    }


    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.getById(id);
    }

    @Override
    public List<Appointment> getAll(Long id) {
        return appointmentRepository.getAll(id);
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        appointmentRepository.update(id, newAppointment);
    }

    @Override
    public void delete(Long id) {
        appointmentRepository.delete(id);
    }
}
