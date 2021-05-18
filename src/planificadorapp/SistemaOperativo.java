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
public class SistemaOperativo 
{
    private final int NPROCESOS = 6;
    private Proceso[] procesos;
    //private int nProcesosActuales;

    public SistemaOperativo(Proceso[] procesos) {
        this.procesos = procesos;
    }
    
    public SistemaOperativo() {
        this.procesos = new Proceso[NPROCESOS];
        
        for(int i=0; i<NPROCESOS; i++)
        {
            procesos[i] = null;
        }
    }
    
    //------------------------------------------------
    
    public void addProceso(String nombre, float tLlegada, float tCPU)
    {
        Proceso p = new Proceso(nombre, tLlegada, tCPU);
        
        for(int i=0; i<NPROCESOS; i++)
        {
            if(procesos[i] == null)
            {
                procesos[i] = p;
                break;
            }
        }
    }
    
    public void borrarProceso(int index)
    {
        for(int i=0; i<NPROCESOS; i++)
        {
            if(i == index)
            {
                procesos[i] = null;
            }
        }
    }
    
    public void modificarProceso()
    {
        
    }
    
    public void planificarProcesos()
    {
        Planificador planificador = new Planificador();
        planificador.srtf(procesos, ProcesosActuales());
        
    }
    
    public int ProcesosActuales()
    {
        int nProcesosActuales = 0;
        
        for (int i = 0; i < NPROCESOS; i++) 
        {
            if(procesos[i] == null)
            {
                nProcesosActuales = i;
                break;
            }
        }
        
        return nProcesosActuales;
    }
}
