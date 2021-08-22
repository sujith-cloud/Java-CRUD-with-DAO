package DTO;

public class AttendanceDTO {
    String id;
    String appointment;
    String status;
    String attendance_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(
            String attendance_date) {
        this.attendance_date = attendance_date;
    }

}
