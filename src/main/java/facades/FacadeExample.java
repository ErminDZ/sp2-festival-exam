package facades;

import dtos.FestivalDTO;
import dtos.GuestDTO;
import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.Festival;
import entities.Guest;
import entities.Movie;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public RenameMeDTO create(RenameMeDTO rm){
        RenameMe rme = new RenameMe(rm.getDummyStr1(), rm.getDummyStr2());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RenameMeDTO(rme);
    }
    public RenameMeDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        RenameMe rm = em.find(RenameMe.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new RenameMeDTO(rm);
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = getEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }
    
    public List<RenameMeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<RenameMe> query = em.createQuery("SELECT r FROM RenameMe r", RenameMe.class);
        List<RenameMe> rms = query.getResultList();
        return RenameMeDTO.getDtos(rms);
    }

    public FestivalDTO getFestivalById(long festivalId) {
        EntityManager em = emf.createEntityManager();
        Festival f = em.find(Festival.class, festivalId);
        return new FestivalDTO(f);
    }

    public MovieDTO getMovieById(long movieId) {
        EntityManager em = emf.createEntityManager();
        Movie m = em.find(Movie.class, movieId);
        System.out.println("her :" + m);
        return new MovieDTO(m);
    }

    public List<MovieDTO> getAllShows(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m ", Movie.class);
        List<Movie> m = query.getResultList();
        System.out.println(m);
        return MovieDTO.getDtos(m);
    }

    public List<GuestDTO> getAllGuest(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Guest> query = em.createQuery("SELECT g FROM Guest g ", Guest.class);
        List<Guest> g = query.getResultList();
        System.out.println(g);
        return GuestDTO.getDtos(g);
    }

    public List<FestivalDTO> getAllFestival(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Festival> query = em.createQuery("SELECT f FROM Festival f ", Festival.class);
        List<Festival> g = query.getResultList();
        System.out.println(g);
        return FestivalDTO.getDtos(g);
    }

    public GuestDTO assignShow(long guestId, long movieID) {
        EntityManager em = emf.createEntityManager();
        try {
            Guest g = em.find(Guest.class, guestId);
            Movie m = em.find(Movie.class, movieID);

            g.addMovies(m);
            m.addGuests(g);

            em.getTransaction().begin();
            em.merge(g);
            em.getTransaction().commit();
            return new GuestDTO(g);
        } finally {
            em.close();
        }
    }


    public MovieDTO createShow(MovieDTO mo) {
        Movie m = new Movie(mo.getName(),mo.getDuration(),mo.getLocation(),mo.getStartDate(),mo.getStartTime());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println("hej" + m);
        return new MovieDTO(m);
    }

    public GuestDTO createGuest(GuestDTO gu) {
        Guest g = new Guest(gu.getName(),gu.getPhone(),gu.getEmail(),gu.getStatus(),new Movie(),new Festival());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println("hej" + g);
        return new GuestDTO(g);
    }

    public FestivalDTO createFestival(FestivalDTO fe) {
        Festival f = new Festival(fe.getName(),fe.getCity(),fe.getStartDate(),fe.getDuration());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println("hej" + f);
        return new FestivalDTO(f);
    }

    public Response deleteShow(long id) {
        EntityManager em= emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE FROM Movie m WHERE m.id = :id").setParameter("id", id);
            q.executeUpdate();
            em.getTransaction().commit();
            return Response.ok().build();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        //fe.deleteShow(2);

        //fe.getShowsByGuest(1);//Virker ikke

        fe.assignShow(1,1);

        //MovieDTO mo = new MovieDTO(1L,"hej",4,"hej","gje","ngei");
        //fe.createShow(mo);

        //GuestDTO gu = new GuestDTO(1L,"eighbne",5256325,"heigie@","godkent");
        //fe.createGuest(gu);

        //FestivalDTO fes = new FestivalDTO(1L,"eigbneig","eigei","iegbne",1);
        //fe.createFestival(fes);
    }

}
