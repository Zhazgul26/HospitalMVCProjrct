package medical.controller;

import lombok.RequiredArgsConstructor;

import medical.entity.Patient;
import medical.entity.enams.Gender;

import medical.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/{id}/patients")
@RequiredArgsConstructor
public class PatientController {


    private final PatientService patientService;
    @GetMapping
    String getAllDepartments(@PathVariable("id") Long id,Model model){
        List<Patient> patients = patientService.getAll(id);
        model.addAttribute("patients",patients);
        model.addAttribute("hospitalId",id);
        return "patient/patients";
    }
    @GetMapping("/savePatient")
    String save(Model model,@PathVariable("id")Long id){
        model.addAttribute("patient",new Patient());
        model.addAttribute("hospitalId",id);
        model.addAttribute("male", Gender.MALE.name());
        model.addAttribute("female", Gender.FEMALE.name());
        return "/patient/savePatient";
    }
    @PostMapping("/new")
    String create(@ModelAttribute("newPatient")Patient patient, @PathVariable("id") Long id) throws Exception {
        System.out.println("sss");
        patientService.save(id,patient);
        return "redirect:/{id}/patients";
    }

}



