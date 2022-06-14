package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Guest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private String name;
    private int phone;
    private String email;
    private String status;

    @ManyToMany(mappedBy = "guests", cascade = CascadeType.PERSIST)
    private List<Movie> movies = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Festival festival;

    public Guest() {
    }

    public Guest(String name, int phone, String email, String status, Movie movie, Festival festival) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.movies = null;
        this.festival = festival;
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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovies(Movie movie) {
        if (!movie.getGuests().contains(this)){
            movie.getGuests().add(this);
        }
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
        if (!festival.getGuests().contains(this)){
            festival.getGuests().add(this);
        }
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", movies=" + movies +
                ", festival=" + festival +
                '}';
    }
}
