package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Doctor;
import medical.entity.Hospital;
import medical.entity.Patient;
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
    public void save(Long hospitalId, Long patientId, Long doctorId, Long departmentId, Appointment appointment) {
        Hospital hospital = hospitalRepository.getById(hospitalId);
        hospital.addAppointment(appointment);
        Patient patient = patientsRepository.getById(patientId);
        appointment.setPatient(patient);
        patient.addAppointment(appointment);
        Doctor doctor = doctorRepository.getById(doctorId);
        appointment.setDoctor(doctor);
        doctor.addAppointment(appointment);
        appointment.setDepartment(departmentRepository.getById(departmentId));
        appointmentRepository.save(appointment);

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
    public Appointment update(Long hospitalId, Long patientId, Long doctorId, Long departmentId, Appointment appointment, Long appointmentId) {
        appointment.setPatient(patientsRepository.getById(patientId));
        appointment.setDoctor(doctorRepository.getById(doctorId));
        appointment.setDepartment(departmentRepository.getById(departmentId));
        Appointment oldAppointment = getById(appointmentId);
        oldAppointment.setPatient(appointment.getPatient());
        oldAppointment.setDoctor(appointment.getDoctor());
        oldAppointment.setDepartment(appointment.getDepartment());
        oldAppointment.setDate(appointment.getDate());
        return appointmentRepository.update(oldAppointment);
    }

    @Override
    public void delete(Long appointmentId) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        appointment.setPatient(null);
        appointment.setDepartment(null);
        appointment.setDoctor(null);
        appointmentRepository.delete(appointmentId);
    }
}
