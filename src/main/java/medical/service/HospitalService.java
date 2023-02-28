package medical.service;

import medical.entity.Hospital;

import java.util.List;

public interface HospitalService {
    void save(Hospital hospital);

    List<Hospital> getAll();


    Hospital getById(Long id);

    void update(Long id, Hospital newHospital);

    void deleteById(Long id);

}

