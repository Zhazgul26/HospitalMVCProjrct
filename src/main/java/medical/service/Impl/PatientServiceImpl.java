package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Appointment;
import medical.entity.Hospital;
import medical.entity.Patient;
import medical.repository.AppointmentRepository;
import medical.repository.HospitalRepository;
import medical.repository.PatientRepository;
import medical.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public void save(Long hospitalId, Patient patient) {
        Hospital hospital = hospitalRepository.getById(hospitalId);
        hospital.addPatient(patient);
        patient.setHospital(hospital);
        patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAll(Long id) {
        return patientRepository.getAll(id);
    }


    @Override
    public Patient getById(Long id) {
        return patientRepository.getById(id);
    }

    @Override
    public Patient update(Long id, Patient newPatient) {
        Patient oldPatient = patientRepository.getById(id);
        oldPatient.setFirstName(newPatient.getFirstName());
        oldPatient.setLastName(newPatient.getLastName());
        oldPatient.setGender(newPatient.getGender());
        oldPatient.setEmail(newPatient.getEmail());
        oldPatient.setPhoneNumber(newPatient.getPhoneNumber());
        return patientRepository.update(oldPatient);
    }

    @Override
    public void delete(Long id) {
        Patient patient = getById(id);
        Hospital hospital = patient.getHospital();
        List<Appointment> appointments = patient.getAppointments();
        appointments.forEach(appointment -> appointment.getPatient().setAppointments(null));
        appointments.forEach(appointment -> appointment.getDoctor().setAppointments(null));
        hospital.getAppointments().removeAll(appointments);
        for (int i = 0; i < appointments.size(); i++) {
            appointmentRepository.delete(appointments.get(i).getId());
        }
        patientRepository.deleteById(id);
    }


}
