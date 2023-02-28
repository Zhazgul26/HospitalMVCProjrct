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
@RequestMapping("/{id}/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    String getAllDepartments(@PathVariable("id") Long id, Model model) {
        List<Department> departments = departmentService.getAll(id);
        model.addAttribute("departments", departments);
        model.addAttribute("hospitalId", id);
        return "department/departments";
    }

    @GetMapping("/saveDepartment")
    String save(Model model, @PathVariable("id") Long id) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitalId", id);
        return "/department/saveDepartment";
    }

    @PostMapping("/new")
    String create(@ModelAttribute("department") Department department, @PathVariable("id") Long id) throws Exception {
        departmentService.save(id, department);
        return "redirect:/{id}/departments";
    }

    @DeleteMapping("{departmentId}/delete")
    String delete (@PathVariable("departmentId") Long departmentId) {
        departmentService.deleteById(departmentId);
        return "redirect:/{id}/departments";
    }
    @GetMapping("/{departmentId}/edit")
    String getUpdate(@PathVariable("departmentId") Long departmentId, Model model,@PathVariable("id") Long id) {
        model.addAttribute("department",departmentService.getById(departmentId));
        model.addAttribute("hospitalId",id);
        return "department/updateDepartment";
    }

    @PostMapping("/{departmentId}/up")
    String updateDepartment(@PathVariable("departmentId") Long departmentId,
                            @ModelAttribute("department") Department department) {
        departmentService.update(departmentId,department);
        return "redirect:/{id}/departments";
    }
}





