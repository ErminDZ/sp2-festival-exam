package dtos;

import entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
    private long id;
    private String name;
    private int duration;
    private String location;
    private String startDate;
    private String startTime;

    public MovieDTO(Movie m) {
        this.id = m.getId();
        this.name = m.getName();
        this.duration = m.getDuration();
        this.location = m.getLocation();
        this.startDate = m.getStartDate();
        this.startTime = m.getStartTime();
    }

    public MovieDTO(long id, String name, int duration, String location, String startDate, String startTime) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public static List<MovieDTO> getDtos(List<Movie> m){
        List<MovieDTO> mdtos = new ArrayList<>();
        m.forEach(mo->mdtos.add(new MovieDTO(mo)));
        return mdtos;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
