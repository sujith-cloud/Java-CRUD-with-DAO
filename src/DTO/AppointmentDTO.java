package DTO;

public class AppointmentDTO {

    String id;
    String available_date;
    String available_time;
    String title;

    public String getAvailable_date() {
        return available_date;
    }

    public void setAvailable_date(String available_date) {
        this.available_date = available_date;
    }

    public String getAvailable_time() {
        return available_time;
    }

    public void setAvailable_time(String available_time) {
        this.available_time = available_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
