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
        agregarGenerosABaseDeDatos(generos);
    }

    @Override
    public List<Genero> obtenerTodos() {
        return generos;
    }
    private void agregarGenerosABaseDeDatos(List<Genero> generos){
        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/poo-cine", "root", "root");
            sentencia = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaAdministracionPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS 'poo-cine'.'Generos'('genero' TEXT NULL)");
                
            } catch (SQLException ex) {
                Logger.getLogger(GenerosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        for(Genero g : generos){
            String cadena = g.getNombre();
            try {
                sentencia.executeUpdate("ALTER TABLE 'poo-cine'.'Generos' INSERT INTO 'genero' VALUES (cadena)");
                        } catch (SQLException ex) {
                Logger.getLogger(GenerosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}