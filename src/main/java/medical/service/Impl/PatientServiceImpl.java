package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Patient;
import medical.repository.PatientRepository;
import medical.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getById(Long id) {
        return patientRepository.getById(id);
    }

    @Override
    public void update(Long id, Patient newPatient) {
        patientRepository.update(id, newPatient);
    }
}
