package medical.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medical.entity.Department;
import medical.repository.DepartmentRepository;
import medical.repository.HospitalRepository;
import medical.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
//    private final HospitalRepository hospitalRepository;


    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
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
}
