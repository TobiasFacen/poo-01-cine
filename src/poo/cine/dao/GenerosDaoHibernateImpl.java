/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.cine.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import poo.cine.Genero;

/**
 *
 * @author joaquinleonelrobles
 */
public class GenerosDaoHibernateImpl implements GenerosDao {
    
    private final SessionFactory sessionFactory;

    public GenerosDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Genero> obtenerTodos() {
        List<Genero> retorno = new ArrayList<Genero>();
        try (Session session = sessionFactory.openSession()) {
            retorno = session.createQuery("FROM Generos").list();
        }catch(Exception e){
            System.out.println("" + e.getMessage());
        }
        
        return retorno;
    }
    
}
