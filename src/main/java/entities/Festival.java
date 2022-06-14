package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Festival implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private String startDate;
    private int duration;

    public Festival() {
    }

    public Festival(String name, String city, String startDate, int duration) {
        this.name = name;
        this.city = city;
        this.startDate = startDate;
        this.duration = duration;
    }

    @OneToMany(mappedBy = "festival")
    private List<Guest> guests = new ArrayList<>();

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public void addGuests(Guest guest) {
        this.guests.add(guest);
        if (guest.getFestival() != this){
            guest.setFestival(this);
        }
    }

    @Override
    public String toString() {
        return "Festival{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", startDate='" + startDate + '\'' +
                ", duration=" + duration +
                ", guests=" + guests +
                '}';
    }
}
