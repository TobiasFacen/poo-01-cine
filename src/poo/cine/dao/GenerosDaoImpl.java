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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.cine.Genero;
import poo.cine.ui.PantallaAdministracionPelicula;

/**
 *
 * @author joaquinleonelrobles
 */
public class GenerosDaoImpl implements GenerosDao {
    
    private final List<Genero> generos;
    private Connection conexion;
    private Statement sentencia;
    public GenerosDaoImpl() {
        this.generos = new ArrayList<>();
        this.generos.add(new Genero("Drama"));
        this.generos.add(new Genero("Comedia"));
        this.generos.add(new Genero("Acción"));
        this.generos.add(new Genero("Otro"));
        this.generos.add(new Genero("Terror"));
        this.generos.add(new Genero("Suspenso"));
        this.generos.add(new Genero("Ciencia Ficcion"));
        
    }

    @Override
    public List<Genero> obtenerTodos() {
        return generos;
    }
    
}