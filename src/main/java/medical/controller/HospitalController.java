package medical.controller;

import lombok.RequiredArgsConstructor;
import medical.entity.Hospital;
import medical.service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping
    String getAll(Model model) {
        List<Hospital> hospitals = hospitalService.getAll();
        model.addAttribute("hospitals", hospitals);
        return "hospital/hospitals";
    }

    @PostMapping("/new")
    String create(@ModelAttribute("hospital") Hospital hospital) {
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/savePage")
    String save(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/savePage";
    }

    @DeleteMapping("/{id}/delete")
    String deleteById(@PathVariable("id") Long id) {
        hospitalService.deleteById(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}/edit")
    String getUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("hospital", hospitalService.getById(id));
        return "hospital/updateHospital";
    }

    @PostMapping("/{id}/up")
    String updateHospital(@PathVariable("id") Long id, @ModelAttribute("hospital") Hospital hospital) {
        hospitalService.update(id, hospital);
        return "redirect:/hospitals";
    }
}
