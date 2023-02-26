package medical.controller;

import lombok.RequiredArgsConstructor;

import medical.entity.Doctor;
import medical.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{id}/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private  final DoctorService doctorService;
    @GetMapping
    String getAllDepartments(@PathVariable("id") Long id, Model model){

        model.addAttribute("doctors",doctorService.getAll(id));
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
    String create(@ModelAttribute("doctor")Doctor doctor, @PathVariable("id") Long id) throws Exception {
        doctorService.save(id,doctor);
        return "redirect:/{id}/doctors";
    }
    @DeleteMapping("/{doctorId}/delete")
    String deleteById(@PathVariable("doctorId") Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctors";
    }

}
