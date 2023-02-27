package medical.controller;

import medical.entity.Hospital;
import medical.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String getAllHospitals(Model model){
        model.addAttribute("hospitals",hospitalService.getAll());
        return "hospital/hospitals";
    }

    @GetMapping("/new")
    public String createHospital(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "hospital/addHospitals";
    }

    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital") Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @DeleteMapping("{hospitalId}/delete")
    public String deleteHospitalById(@PathVariable("hospitalId") Long id){
        hospitalService.deleteById(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/edit/{id}")
    public String updateHospital(@PathVariable("id")Long id,Model model){
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/updateHospital";
    }

    @PostMapping("/{id}/update")
    public String saveUpdateHospital(@ModelAttribute("hospital") Long id,Hospital hospital){
        hospitalService.update(id,hospital);
        return "redirect:/hospitals";
    }

}
