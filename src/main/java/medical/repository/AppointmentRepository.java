package medical.repository;

import medical.entity.Appointment;

import java.util.List;

public interface AppointmentRepository {

   void save(Appointment appointment);

    Appointment getById(Long id);

    List<Appointment> getAll(Long id);

    Appointment update(Appointment newAppointment);

    void delete(Long id);
}
