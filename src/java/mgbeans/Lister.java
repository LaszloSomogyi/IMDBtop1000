/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbeans;

import comparators.GrossComparator;
import comparators.GrossReverseComparator;
import comparators.RankComparator;
import comparators.RankReverseComparator;
import comparators.YearComparator;
import comparators.YearReverseComparator;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.ImdbTop1000;

/**
 *
 * @author Somogyi László <proceed step by step>
 */
@ManagedBean
@SessionScoped
public class Lister {

    private String title;
    private String genre;
    private ImdbTop1000 selectedMovie;
    private List<ImdbTop1000> movies;
    private ImdbTop1000 oldestMovie;
    
    
    public Lister() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        movies = session.createQuery("FROM ImdbTop1000").list();
         
        oldestMovie = (ImdbTop1000) session.createQuery("FROM ImdbTop1000 WHERE Released_Year='1927'").uniqueResult();
        session.close();
    }
    
    public String details(ImdbTop1000 movie){
        selectedMovie = movie;
        return "movie_details";
    }
    
    public void sortByRank() {
        if(movies.get(0).getId()!=1) {
            Collections.sort(movies, new RankComparator());
        } else if(movies.get(0).getId()==1) {
            Collections.sort(movies, new RankReverseComparator());
        }
    }
    
    public void sortByYear() {
        if (movies.get(0).getId()==1) {
            Collections.sort(movies, new YearComparator());
        } else if (movies.get(0).equals(oldestMovie)) {
            Collections.sort(movies, new YearReverseComparator());
        } else {
            Collections.sort(movies, new YearComparator());
        }
    }
    
    public void sortByGross() {
        if (movies.get(0).getGross() != 936662225 && movies.get(0).getGross() != 1000045) {
            Collections.sort(movies, new GrossComparator());
        } else if (movies.get(0).getGross() == 1000045) {
            Collections.sort(movies, new GrossReverseComparator());
        } else {
            Collections.sort(movies, new GrossComparator());
        }
    }
    
    public void searchTitle(){
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM ImdbTop1000 WHERE title LIKE :ptitle");
        q.setString("ptitle", "%" + title + "%");
        movies = q.list();
        session.close();
    }
    
    public void searchGenre(){
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM ImdbTop1000 WHERE genre LIKE :pgenre");
        q.setString("pgenre", "%" + genre + "%");
        movies = q.list();
        session.close();
    }
    
    public List<ImdbTop1000> getMovies() {
        return movies;
    }

    public void setMovies(List<ImdbTop1000> movies) {
        this.movies = movies;
    }

    public ImdbTop1000 getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(ImdbTop1000 selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImdbTop1000 getOldestMovie() {
        return oldestMovie;
    }

    public void setOldestMovie(ImdbTop1000 oldestMovie) {
        this.oldestMovie = oldestMovie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
    
}
