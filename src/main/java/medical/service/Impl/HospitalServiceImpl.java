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
    public void save(Hospital hospital) {
        if (hospital.getName().toLowerCase().length() < 1) {
            for (Character i : hospital.getName().toCharArray()) {
                if (!Character.isLetter(i)) {
                    System.out.println("Aty jok hospital bolboit!!!");
                }
            }
        } else {
            try {
                hospitalRepository.save(hospital);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalRepository.getAll();
    }



    @Override
    public Hospital getById(Long id) {
        return hospitalRepository.getById(id);
    }

    @Override
    public void update(Long id, Hospital newHospital) {
        hospitalRepository.update(id, newHospital);
    }

    @Override
    public void deleteById(Long id) {
        hospitalRepository.deleteById(id);
    }
}
