/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.cine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import poo.cine.Genero;
import poo.cine.Pelicula;
import poo.cine.Personaje;
import poo.cine.ui.PantallaAdministracionPelicula;

/**
 *
 * @author joaquinleonelrobles
 */
public class PeliculasDaoHibernateImpl implements PeliculasDao {
    
    private final SessionFactory sessionFactory;
    private Statement sentencia;
    private Connection conexion;
    
    public PeliculasDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pelicula buscarPorNombre(String nombre) {
        Session session = sessionFactory.openSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Pelicula> query = builder.createQuery(Pelicula.class);
        Root<Pelicula> root = query.from(Pelicula.class);
        query.select(root);
        query.where(builder.equal(root.get("nombre"), nombre));
        
        Pelicula pelicula = session.createQuery(query).uniqueResult();
        
        session.close();
        
        return pelicula;
    }

    @Override
    public void guardar(Pelicula pelicula) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pelicula);
        session.getTransaction().commit();
        session.close();
    }
    private void agregarPeliculasABaseDeDatos(List<Pelicula> peliculas){
        try {
                sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS"
                        + "'poo-cine'.'Peliculas'('nombre' LONGTEXT, "
                        + "'genero' LONGTEXT, 'titulo_original' LONGTEXT, "
                        + "'año_estreno' YEAR, 'duracion' INT, "
                        + "'calificacion' LONGTEXT, 'pais_de_origen' LONGTEXT,"
                        + " 'personajes' LONGTEXT PRIMARY KEY ('nombre') NULL");
                
            } catch (SQLException ex) {
                Logger.getLogger(GenerosDaoImpl.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        for(Pelicula p : peliculas){
            String nombre = p.getNombre();
            Genero genero = p.getGenero();
            String titulo_original = p.getTituloOriginal();
            int anio_estreno = p.getAnioEstreno();
            int duracion = p.getDuracion();
            String calificacion = p.getCalificacion().getNombre();
            String pais_de_origen = p.getPaisDeOrigen().getNombre();
            List<Personaje> personajes = p.getPersonajes();
            for(Personaje pe : personajes){
                String personaje = pe.getNombreEnPelicula();
                try {
                    sentencia.executeUpdate("ALTER TABLE 'poo-cine'.'Peliculas'"
                            + "INSERT INTO ('nombre', 'genero', "
                            + "'titulo_original', 'año_estreno', 'duracion', "
                            + "'calificacion', 'pais_de_origen', 'personajes')"
                            + "VALUES (nombre, genero, titulo_original, "
                            + "anio_estreno, duracion, calificacion, "
                            + "pais_de_origen, personajes)");
                            } catch (SQLException ex) {
                    Logger.getLogger(PeliculasDaoHibernateImpl.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
