package medical.controller;


import lombok.RequiredArgsConstructor;
import medical.entity.Department;
import medical.service.DepartmentService;
import medical.service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping
    String getAllDepartments(Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "department/departments";
    }

    @PostMapping("/new")
    String create(@ModelAttribute("newDepartment") Department department, @RequestParam("hospitalId") Long id) throws Exception {
        departmentService.save(id, department);
        return "redirect:/departments";
    }
//
//    @GetMapping("/saveDepartment")
//    String save(Model model) {
//        model.addAttribute("department", new Department());
//        model.addAttribute("hospitals", hospitalService.getAllHospital());
//        return "/department/saveDepartment";
    }


