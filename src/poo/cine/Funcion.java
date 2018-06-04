/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.cine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Romina
 */
public class Funcion {

    private int diaSemana;
    private int duracion;
    private Date horaInicio;
    private int numero;
    private Pelicula pelicula;
    private Sala sala;
    private List<Entrada> entradas;

    /**
     * Constructor por Defecto
     */
    public Funcion() {
        entradas = new ArrayList<Entrada>();
    }
    
    /**
     * Este constructor no incluye los atributos referenciales de la clase
     * Funcion
     * 
     * @param diaSemana
     * @param duracion
     * @param horaInicio
     * @param numero
     * @param pelicula
     * @param sala 
     */
    public Funcion(int diaSemana, int duracion, Date horaInicio, int numero, 
            Pelicula pelicula, Sala sala) {
        this.diaSemana = diaSemana;
        this.duracion = duracion;
        this.horaInicio = horaInicio;
        this.numero = numero;
        this.pelicula = pelicula;
        this.sala = sala;
    }
    
    /**
     * Calculamos la cantidad de butacas disponibles en base a la cantidad
     * total de la sala y las entradas vendidas para esta Función
     * 
     * @return cantidad de butacas disponibles
     */
    public int calcularDisponibilidad() {
        int disponibilidad;
        disponibilidad = capacidadSala() - entradas.size();
        return disponibilidad;
    }
    
    /**
     * Obtenemos la capacidad de la Sala en cantidad de butacas
     * 
     * @return cantidad total de butacas de la Sala
     */
    public int capacidadSala() {
        int capacidad = sala.getCapacidad();
        return capacidad;
    }
    
    /**
     * 
     * @return 
     */
    public boolean estaEnCurso() {
        boolean estaEnCurso = false;
        Calendar fecha = new GregorianCalendar();
        Calendar fecha2 = new GregorianCalendar();
        Calendar fecha3 = new GregorianCalendar();
        fecha2.add(Calendar.MINUTE, getDuracion());
        fecha.setTime(getHoraInicio());
        if(fecha3.after(fecha) && fecha3.before(fecha2)){
            estaEnCurso = true;
        }
        return estaEnCurso;
    }
    
    /**
     * Consultamos si queda al menos una Entrada disponible para esta función
     * 
     * @return 
     */
    public boolean hayLugar() {
        boolean hayLugar = false;
        if(calcularDisponibilidad() > 0){
            hayLugar = true;
        }
        return hayLugar;
    }
    
    /**
     * Devolvemos una representación en texto del día y hora de esta función
     * 
     * @return 
     */
    public String mostrarDiaHora() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dia de la funcion: ").append(getDiaSemana()).append("\n");
        sb.append("Hora de la funcion: ").append(getHoraInicio()).append("\n");
        return sb.toString();
    }

    // A continuación se listan todos los métodos de seteo
    // de cada atributo de la clase
    
    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
    /**
     * Recorremos las Funciones y armamos una cadena de texto con la 
     * representación de cada una de ellas.
     * 
     * @return cadena de texto con las funciones
     */
    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("Datos de la funcion:").append("\n");
       sb.append(mostrarDiaHora()).append("\n");
       sb.append("Pelicula: ").append(getPelicula()).append("\n");
       sb.append("Numero de sala: ").append(getSala().getNumero()).append("\n");
       sb.append("Numero de funcion: ").append(getNumero()).append("\n");
       sb.append("Duracion de la pelicula: ").append(getDuracion()).
               append("\n");
       return sb.toString();
    }
      
}
