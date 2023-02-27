package medical.controller;

import medical.entity.Department;
import medical.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public String getAllDepartments(@PathVariable Long id, Model model){
        model.addAttribute("departments",departmentService.getAll(id));
        model.addAttribute("hospitalId",id);
        return "department/departments";
    }

    @GetMapping("/{id}/newDepartment")
    public String createDepartment(@PathVariable Long id,Model model){
        model.addAttribute("newDepartment",new Department());
        model.addAttribute("hospitalId",id);
        return "department/addDepartment";
    }

    @PostMapping("/{id}/saveDepartment")
    public String saveDepartment(@ModelAttribute("newDepartment") Department department,@PathVariable("id") Long id){
        departmentService.save(id,department);
        return "redirect:/departments/" +id;
    }

    @GetMapping("/edit/{id}")
    public String updateDepartment(@PathVariable Long id, Model model){
        Department department = departmentService.getById(id);
        model.addAttribute("department",department);
        model.addAttribute("hospitalId",department.getHospital().getId());
        return "department/updateDepartment";
    }

    @PostMapping("/{id}/{departmentId}/update")
    public String saveUpdateDepartment(@ModelAttribute("department") Department department,
                                       @PathVariable("departmentId") Long departmentId,
                                       @PathVariable("id") Long id){
        departmentService.update(departmentId,department);
        return "redirect:/departments/" +id;
    }

    @DeleteMapping("/{id}/{hosId}")
    public String deleteByDepartmentId(@PathVariable("id") Long id,
                                       @PathVariable("hosId") Long hosId){
        departmentService.deleteById(id);
        return "redirect:/departments/" + hosId;
    }
}
