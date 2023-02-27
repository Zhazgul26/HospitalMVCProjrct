package medical.controller;

import medical.entity.Patient;
import medical.entity.enams.Gender;
import medical.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public String getAllPatients(Model model,@PathVariable Long id){
        model.addAttribute("patients",patientService.getAll(id));
        model.addAttribute("hospitalId",id);
        return "patient/patients";
    }

    @GetMapping("/{id}/newPatient")
    public String createPatient(@PathVariable Long id,Model model){
        model.addAttribute("newPatient",new Patient());
        model.addAttribute("genderMale", Gender.MALE);
        model.addAttribute("genderFeMale",Gender.FEMALE);
        model.addAttribute("hospitalId",id);
        return "patient/addPatient";
    }

    @PostMapping("/{id}/savePatient")
    public String savePatient(@ModelAttribute("newPatient") Patient patient,
                              @PathVariable("id") Long id){
        patientService.save(id,patient);
        return "redirect:/patients/" +id;
    }

    @GetMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id,Model model){
        model.addAttribute("genderMale",Gender.MALE);
        model.addAttribute("genderFeMale",Gender.FEMALE);
        Patient patient = patientService.getById(id);
        model.addAttribute("patient",patient);
        model.addAttribute("hospitalId",patient.getHospital().getId());
        return "patient/updatePatient";
    }

    @PostMapping("/{id}/{patientId}/update")
    public String updateSavePatient(@PathVariable("id")Long id,
                                    @PathVariable("patientId")Long patientId,
                                    @ModelAttribute("patient")Patient patient){
        patientService.update(patientId,patient);
        return "redirect:/patients/"+id;
    }

    @DeleteMapping("/{id}/{hosId}")
    public String deleteByPatientId(@PathVariable("id")Long id,
                                    @PathVariable("hosId")Long hosId){
        patientService.deleteById(id);
        return "redirect:/patients/"+hosId;
    }
}
