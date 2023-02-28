package medical.controller;

import jakarta.transaction.Transactional;
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
    String create(@ModelAttribute("patient")Patient patient, @PathVariable("id") Long id) throws Exception {
        patientService.save(id,patient);
        return "redirect:/{id}/patients";
    }
    @GetMapping("/{patientId}/edit")
    String getUpdate(@PathVariable("patientId") Long patientId, Model model,@PathVariable("id") Long id) {
        model.addAttribute("patient",patientService.getById(patientId));
        model.addAttribute("hospitalId",id);
        return "patient/updatePatient";
    }

    @PostMapping("/{patientId}/up")
    String updatePatient(@PathVariable("patientId") Long patientId, @ModelAttribute("patient") Patient patient) {
        patientService.update(patientId,patient);
        return "redirect:/{id}/patients";
    }
    @DeleteMapping("{patientId}/delete")
    String delete (@PathVariable("patientId") Long patientId) {
        patientService.delete(patientId);
        return "redirect:/{id}/patients";
    }
}
