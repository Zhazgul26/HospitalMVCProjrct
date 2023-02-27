package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Hospital;
import medical.repository.DepartmentRepository;
import medical.repository.DoctorRepository;
import medical.repository.HospitalRepository;
import medical.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
private final DoctorRepository doctorRepository;

    @Override
    public Department save(Long id ,Department department) {
        Hospital hospital = hospitalRepository.getById(id);
        hospital.addDepartment(department);
        department.setHospital(hospital);
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll(Long  id) {
        return departmentRepository.getAll(id);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.getById(id);
    }

    @Override
    public void update(Long id, Department newDepartment) {
        departmentRepository.update(id, newDepartment);
    }



    @Override
    public void assignDoctor(Long doctorId, Doctor doctor) {
        Department department = departmentRepository.getById(doctor.getDepartmentId());
        Doctor oldDoctor = doctorRepository.getById(doctorId);
        oldDoctor.addDepartment(department);
        department.addDoctor(oldDoctor);
        departmentRepository.assignDoctor(oldDoctor);
    }
}
