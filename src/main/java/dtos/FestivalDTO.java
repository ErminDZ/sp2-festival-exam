package dtos;

import entities.Festival;

import java.util.ArrayList;
import java.util.List;

public class FestivalDTO {
    private long id;
    private String name;
    private String city;
    private String startDate;
    private int duration;

    public FestivalDTO(Festival f) {
        this.id = f.getId();
        this.name = f.getName();
        this.city = f.getCity();
        this.startDate = f.getStartDate();
        this.duration = f.getDuration();
    }

    public FestivalDTO(long id, String name, String city, String startDate, int duration) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.startDate = startDate;
        this.duration = duration;
    }

    public static List<FestivalDTO> getDtos(List<Festival> f){
        List<FestivalDTO> fdtos = new ArrayList<>();
        f.forEach(fe->fdtos.add(new FestivalDTO(fe)));
        return fdtos;
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
}

