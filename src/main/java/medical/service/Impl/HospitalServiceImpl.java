package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Hospital;
import medical.repository.HospitalRepository;
import medical.service.HospitalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;


    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public Hospital getById(Long id) {
        return hospitalRepository.getById(id);
    }

    @Override
    public void update(Long id, Hospital newHospital) {
        hospitalRepository.update(id, newHospital);
    }
}
