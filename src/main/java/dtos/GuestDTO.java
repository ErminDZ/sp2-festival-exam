package dtos;

import entities.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestDTO {
    private long id;
    private String name;
    private int phone;
    private String email;
    private String status;

    public GuestDTO(Guest g) {
        this.id = g.getId();
        this.name = g.getName();
        this.phone = g.getPhone();
        this.email = g.getEmail();
        this.status = g.getStatus();
    }

    public GuestDTO(long id, String name, int phone, String email, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public static List<GuestDTO> getDtos(List<Guest> g){
        List<GuestDTO> gdtos = new ArrayList<>();
        g.forEach(gu->gdtos.add(new GuestDTO(gu)));
        return gdtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
