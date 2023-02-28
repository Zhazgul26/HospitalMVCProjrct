package medical.controller;

import lombok.RequiredArgsConstructor;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.service.DepartmentService;
import medical.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{id}/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @GetMapping
    String getAllDepartments(@PathVariable("id") Long id, Model model,@ModelAttribute("department")Department department){
        model.addAttribute("doctors",doctorService.getAll(id));
        model.addAttribute("departments",departmentService.getAll(id));
        model.addAttribute("hospitalId",id);
        return "doctor/doctors";
    }
    @GetMapping("/saveDoctor")
    String save(Model model,@PathVariable("id")Long id){
        model.addAttribute("doctor",new Doctor());
        model.addAttribute("hospitalId",id);
        return "/doctor/saveDoctor";
    }
    @PostMapping("/new")
    String create(@ModelAttribute("doctor")Doctor doctor, @PathVariable("id") Long id) {
        doctorService.save(id,doctor);
        return "redirect:/{id}/doctors";
    }
    @GetMapping("/{doctorId}/departments")
    String departments(@PathVariable("id")Long id,
                       @PathVariable("doctorId")Long doctorId,
                       Model model){
        model.addAttribute("doctor", doctorService.getById(doctorId));
        model.addAttribute("departments", doctorService.getAllDepartmentDoctorById(doctorId));
        model.addAttribute("doctors",doctorService.getAll(id));
        return "doctor/departments";
    }
    @GetMapping("/{doctorId}/assignDepartment")
    String assignToDepartment(@PathVariable("id")Long id, @PathVariable("doctorId") Model model){
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAll(id));
        return "doctor/assignToDepartment";
    }
    @PostMapping("/{doctorId}/saveAssignDepartment")
    String saveAssignDepartment(@PathVariable("doctorId")Long doctorId,
                                @ModelAttribute("doctor") Doctor doctor){
        departmentService.assignDoctor(doctorId, doctor);
        return "redirect:/{id}/doctors";
    }
    @DeleteMapping("{doctorId}/delete")
    String deleteById(@PathVariable("doctorId") Long doctorId, @PathVariable String id) {
        doctorService.deleteById(doctorId);
        return "redirect:/{id}/doctors";

    }
    @GetMapping("/{doctorId}/edit")
    String getUpdate(@PathVariable("doctorId") Long doctorId, Model model,@PathVariable("id") Long id) {
        model.addAttribute("doctor",doctorService.getById(doctorId));
        model.addAttribute("hospitalId",id);
        return "doctor/updateDoctor";
    }

    @PostMapping("/{doctorId}/up")
    String updateDoctor(@PathVariable("doctorId") Long doctorId, @ModelAttribute("doctor") Doctor doctor) {
        doctorService.update(doctorId,doctor);
        return "redirect:/{id}/doctors";
    }

}