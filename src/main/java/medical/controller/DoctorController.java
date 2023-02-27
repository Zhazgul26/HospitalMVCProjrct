package medical.controller;

import lombok.RequiredArgsConstructor;

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
    String getAllDepartments(@PathVariable("id") Long id, Model model) {

        model.addAttribute("doctors", doctorService.getAll(id));
        model.addAttribute("hospitalId", id);
        return "doctor/doctors";
    }

    @GetMapping("/saveDoctor")
    String save(Model model, @PathVariable("id") Long id) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("hospitalId", id);
        return "/doctor/saveDoctor";
    }

    @PostMapping("/new")
    String create(@ModelAttribute("doctor") Doctor doctor, @PathVariable("id") Long id) throws Exception {
        doctorService.save(id, doctor);
        return "redirect:/{id}/doctors";
    }

    @DeleteMapping("/{doctorId}/delete")
    String deleteById(@PathVariable("doctorId") Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctors";
    }

    @GetMapping("/{doctorId}/departments")
    String departments(@PathVariable("id") Long id,
                       @PathVariable("doctorId") Long doctorId,
                       Model model) {
        model.addAttribute("doctor", doctorService.getById(doctorId));
        model.addAttribute("departments", departmentService.getAll(id));
        model.addAttribute("doctors", doctorService.getAll(id));
        return "doctor/departments";
    }

    @GetMapping("/{doctorId}/assignDepartment")
    String assignToDepartment(@PathVariable("id") Long id, @PathVariable("doctorId") Long doctorId, Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAll(id));
        return "doctor/assignToDepartment";
    }

    @PostMapping("/{doctorId}/saveAssignDepartment")
    String saveAssignDepartment(@PathVariable("id") Long id,
                                @PathVariable("doctorId") Long doctorId,
                                @ModelAttribute("doctor") Doctor doctor) {
        departmentService.assignDoctor(doctorId, doctor);
        return "redirect:/{id}/doctors";
    }
}
