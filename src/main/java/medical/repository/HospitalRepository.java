package medical.repository;

import medical.entity.Hospital;

import java.util.List;

public interface HospitalRepository {

   void save(Hospital hospital);

    List<Hospital> getAll();

    void deleteById(Long id);

    Hospital getById(Long id);

    void update(Long id, Hospital newHospital);
}
