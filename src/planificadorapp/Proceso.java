/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planificadorapp;

/**
 *
 * @author usuario
 */
public class Proceso 
{
    private String nombre;
    private float tLlegada;
    private float tCPU;
    private float tComienzo;
    private float tFin;
    private float tEspera;

    public Proceso(String nombre, float tLlegada, float tCPU) {
        this.nombre = nombre;
        this.tLlegada = tLlegada;
        this.tCPU = tCPU;
    }
    
    public Proceso() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float gettLlegada() {
        return tLlegada;
    }

    public void settLlegada(float tLlegada) {
        this.tLlegada = tLlegada;
    }

    public float gettCPU() {
        return tCPU;
    }

    public void settCPU(float tCPU) {
        this.tCPU = tCPU;
    }

    public float gettComienzo() {
        return tComienzo;
    }

    public void settComienzo(float tComienzo) {
        this.tComienzo = tComienzo;
    }

    public float gettFin() {
        return tFin;
    }

    public void settFin(float tFin) {
        this.tFin = tFin;
    }

    public float gettEspera() {
        return tEspera;
    }

    public void settEspera(float tEspera) {
        this.tEspera = tEspera;
    }

    

}
