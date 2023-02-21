package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Doctor;
import medical.repository.DoctorRepository;
import medical.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
doctorRepository.deleteById(id);
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.getById(id);
    }

    @Override
    public void update(Long id, Doctor newDoctor) {
doctorRepository.update(id, newDoctor);
    }
}
