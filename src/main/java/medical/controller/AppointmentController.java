package medical.controller;

import medical.entity.Appointment;
import medical.entity.Department;
import medical.entity.Doctor;
import medical.entity.Patient;
import medical.service.AppointmentService;
import medical.service.DepartmentService;
import medical.service.DoctorService;
import medical.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    private final DepartmentService departmentService;

    private final DoctorService doctorService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService, DepartmentService departmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.departmentService = departmentService;
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    public String getAllAppointment(@PathVariable Long id, Model model,
                                    @ModelAttribute("patient") Patient patient,
                                    @ModelAttribute("doctor") Doctor doctor,
                                    @ModelAttribute("department") Department department,
                                    @ModelAttribute("appointment") Appointment appointment){
        model.addAttribute("departments",departmentService.getAll(id));
        model.addAttribute("doctors",doctorService.getAll(id));
        model.addAttribute("patients",patientService.getAll(id));
        model.addAttribute("appointments",appointmentService.getAll(id));
        model.addAttribute("hospitalId",id);
        return "appointment/appointments";
    }

    @GetMapping("/{id}/newAppointment")
    public String createAppointment(@PathVariable Long id,Model model){
        model.addAttribute("newAppointment",new Appointment());
        model.addAttribute("hospitalId",id);
        return "appointment/addAppointment";
    }

    @PostMapping("/{id}/saveAppointment")
    public String saveAppointment(@ModelAttribute("newAppointment")Appointment appointment,
                                  @PathVariable("id") Long id){
        appointmentService.save(id,appointment);
        return "redirect:/appointments/"+id;
    }

    @GetMapping("/edit/{id}")
    public String updateAppointment(@PathVariable Long id,Model model){
        Appointment appointment = appointmentService.getById(id);
        model.addAttribute("appointment",appointment);
        model.addAttribute("hospitalId",appointment.getHospital().getId());
        return "appointment/updateAppointment";
    }

    @PostMapping("/{id}/{appointmentId}/update")
    public String saveUpdateAppointment(@ModelAttribute("appointment") Appointment appointment,
                                        @PathVariable("id")Long id,
                                        @PathVariable("appointmentId")Long appointmentId){
        appointmentService.update(appointmentId,appointment);
        return "redirect:/appointments/"+id;
    }

    @DeleteMapping("/{id}/{hosId}")
    public String deleteAppointmentById(@PathVariable("id") Long id,
                                        @PathVariable("hosId")Long hosId){
        appointmentService.delete(id);
        return "redirect:/appointments/"+hosId;
    }

    @PostMapping("{appointmentId}/assignPatient")
    public String assignPatient(@PathVariable("appointmentId")Long appointmentId,
                                @ModelAttribute("patient")Patient patient) throws IOException {
        Long id = patient.getId();
        patientService.assignPatient(appointmentId,id);
        return "redirect:/appointments/"+appointmentId;
    }

    @PostMapping("{appointmentId}/assignDoctor")
    public String assignDoctor(@PathVariable("appointmentId")Long appointmentId,
                               @ModelAttribute("doctor") Doctor doctor) throws IOException {
        Long id = doctor.getId();
        doctorService.assignDoctor(appointmentId,id);
        return "redirect:/appointments/"+appointmentId;
    }

    @PostMapping("{appointmentId}/assignDepartment")
    public String assignDepartmentToAppointment(@PathVariable("appointmentId")Long appointmentId,
                               @ModelAttribute("department") Department department) throws IOException {
        Long id = department.getId();
        departmentService.assignDepartmentToAppointment(appointmentId,id);
        return "redirect:/appointments/"+appointmentId;
    }
}
