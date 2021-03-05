/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbeans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import pojos.ImdbTop1000;

/**
 *
 * @author Somogyi László <proceed step by step>
 */
@ManagedBean
@SessionScoped
public class Lister {

    private List<ImdbTop1000> movies;
    
    public Lister() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        movies = session.createQuery("FROM ImdbTop1000").list();
        session.close();
        
    }

    public List<ImdbTop1000> getMovies() {
        return movies;
    }

    public void setMovies(List<ImdbTop1000> movies) {
        this.movies = movies;
    }
    
    
    
    
}
