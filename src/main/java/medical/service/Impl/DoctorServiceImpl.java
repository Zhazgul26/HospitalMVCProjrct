package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Doctor;
import medical.entity.Hospital;
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
    @Override
    public Doctor save(Long id,Doctor doctor) {
        Hospital hospital = hospitalRepository.getById(id);
        hospital.addDoctor(doctor);
        doctor.setHospital(hospital);
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAll(Long id) {
        return doctorRepository.getAll(id);
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

    @Override
    public void assignDoctor(Long appointmentId, Long doctorId) throws IOException {
        doctorRepository.assignDoctor(appointmentId, doctorId);
    }
}
