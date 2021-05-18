/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planificadorapp;
import java.util.LinkedList;
/**
 *
 * @author usuario
 */
public class Planificador 
{
    
    public Planificador()
    {
        
    }
    
    //------------------------------------------------
    
    public Proceso[] fifo(Proceso[] procesos, int nProcesosActuales)
    {
        
        organizarVector(procesos, nProcesosActuales);
        
        for (int i = 0; i < nProcesosActuales; i++) 
        {
            //El primer proceso inicia cuando llega.
            if(i == 0)
            {
                procesos[i].settComienzo(procesos[i].gettLlegada());
                procesos[i].settFin( procesos[i].gettCPU() + procesos[i].gettComienzo() );
                procesos[i].settEspera( procesos[i].gettComienzo() - procesos[i].gettLlegada() ); 
            }
            else
            {
                //System.out.println("....");
                procesos[i].settComienzo( procesos[i-1].gettFin() );
                procesos[i].settFin( procesos[i].gettCPU() + procesos[i].gettComienzo() );
                procesos[i].settEspera( procesos[i].gettComienzo() - procesos[i].gettLlegada() );
            }
        }
        
        for(int i = 0; i < nProcesosActuales; i++)
        {
            System.out.print(procesos[i].getNombre()+"-"+procesos[i].gettCPU()
                    +"-"+procesos[i].gettComienzo()+"-"+procesos[i].gettFin()+"-"+procesos[i].gettEspera()+"\n");
        }
        
        return procesos;
    }
    
    public Proceso[] sjf(Proceso[] procesos, int nProcesosActuales)
    {
        float timepoActualCPU = 0;
        Proceso ProcesoMenorTiempoCPU;
        
        //si la lista esta vacia, no se puede planificar
        if(nProcesosActuales == 0)
        {
            return procesos;
        }
        
        //asignar un valor no nulo a ProcesoMenorTiempoCPU
        ProcesoMenorTiempoCPU = procesos[0];
        
        organizarVector(procesos, nProcesosActuales);
        
        for (int i = 0; i < nProcesosActuales; i++)
        {
            //El primer proceso inicia cuando llega.
            if(i == 0)
            {
                procesos[0].settComienzo(procesos[0].gettLlegada());
                procesos[0].settFin( procesos[0].gettCPU() + procesos[0].gettComienzo() );
                procesos[0].settEspera( procesos[0].gettComienzo() - procesos[0].gettLlegada() );
                
                timepoActualCPU = procesos[0].gettFin();
                
                System.out.println("tLlegada:  "+procesos[i].gettLlegada());
                System.out.println("tComienzo: "+procesos[i].gettComienzo());
            }
            else
            {
                System.out.println(".....");
                //buscar el siguiente proceso a ejecutar
                for (int j = 0; j < nProcesosActuales; j++)
                {
                    System.out.println("pmin: "+ProcesoMenorTiempoCPU.getNombre());
                    
                    if(procesos[j].gettLlegada() < timepoActualCPU && procesos[j].gettFin() == 0) //proceso.getFint() es 0 si el proceso nos e ha ejecutado
                    {
                        ProcesoMenorTiempoCPU = procesos[j];
                        System.out.println("lllll");
                        System.out.println("pmin: "+ProcesoMenorTiempoCPU.getNombre());
                    }
                }
                
                ProcesoMenorTiempoCPU.settComienzo( timepoActualCPU );
                ProcesoMenorTiempoCPU.settFin( ProcesoMenorTiempoCPU.gettCPU() + ProcesoMenorTiempoCPU.gettComienzo() );
                ProcesoMenorTiempoCPU.settEspera( ProcesoMenorTiempoCPU.gettComienzo() - ProcesoMenorTiempoCPU.gettLlegada() );

                timepoActualCPU = ProcesoMenorTiempoCPU.gettFin();
                System.out.println("timepoActualCPU: "+timepoActualCPU);
            }
        }
        
        for(int i = 0; i < nProcesosActuales; i++)
        {
            System.out.print(procesos[i].getNombre()+"-"+procesos[i].gettCPU()
                    +"-"+procesos[i].gettComienzo()+"-"+procesos[i].gettFin()+"-"+procesos[i].gettEspera()+"\n");
        }
        
        return procesos;
    }
    
    public Proceso[] srtf(Proceso[] procesos, int nProcesosActuales)
    {
        float tiempoActualCPU = 0;
        Proceso procesoMenorTiempoRemanente;
        Proceso procesoActual;
        
        //si la lista esta vacia, no se puede planificar
        if(nProcesosActuales == 0)
        {
            return procesos;
        }
        
        organizarVector(procesos, nProcesosActuales);
        
        procesoActual = procesos[0];
        procesoActual.settComienzo(procesoActual.gettLlegada());
        
        tiempoActualCPU = procesos[0].gettCPU() + procesos[0].gettLlegada();
        
        boolean isPlanificacionTerminada = false;
        
        do
        {
            for (int j = 0; j < nProcesosActuales; j++)
            {
                System.out.println("44");
                if(procesos[j].gettLlegada() < tiempoActualCPU && procesos[j].gettCPU() != 0) //proceso.getFint() es 0 si el proceso nos e ha ejecutado
                {
                    System.out.println("33");

                    if(procesos[j].gettCPU() < procesos[0].gettCPU())
                    {
                        procesoMenorTiempoRemanente = procesos[j];
                        tiempoActualCPU += procesoMenorTiempoRemanente.gettLlegada();
                        procesoMenorTiempoRemanente.settComienzo(tiempoActualCPU);
                        procesoMenorTiempoRemanente.settLlegada(tiempoActualCPU);

                        System.out.println("interrumpido por proceso: "+procesoMenorTiempoRemanente.getNombre());

                        procesoActual.settCPU(tiempoActualCPU - procesoActual.gettComienzo());
                        procesoActual.settFin(tiempoActualCPU);

                        float nuevoTiempoEspera = procesoActual.gettEspera() + procesoActual.gettComienzo() - procesoActual.gettLlegada();
                        procesoActual.settEspera(nuevoTiempoEspera);

                        procesoActual = procesoMenorTiempoRemanente;
                        System.out.println("pmin: "+procesoMenorTiempoRemanente.getNombre());
                    }
                }
                
                float tiempoTotalRestanteCPU = 0;
                
                for(int x=0; x<nProcesosActuales; x++)
                {
                    tiempoTotalRestanteCPU += procesos[x].gettCPU();
                }
                
                if(tiempoTotalRestanteCPU == 0)
                {
                    System.out.println("Final plani");
                    isPlanificacionTerminada = true;
                }
            }
        }
        while(!isPlanificacionTerminada);
        
        for(int i = 0; i < nProcesosActuales; i++)
        {
            System.out.print(procesos[i].getNombre()+"-"+procesos[i].gettCPU()
                    +"-"+procesos[i].gettComienzo()+"-"+procesos[i].gettFin()
                    +"-"+procesos[i].gettEspera()+"\n");
        }
        
        return procesos;
    }
    
    public void organizarVector(Proceso[] procesos, int nProcesosActuales)
    {
        for(int i = 0; i < nProcesosActuales - 1; i++)
        {
            for(int j = 0; j < nProcesosActuales - 1; j++)
            {
                if(procesos[j].gettLlegada() > procesos[j + 1].gettLlegada())
                {
                    Proceso tmp   = procesos[j+1];
                    procesos[j+1] = procesos[j];
                    procesos[j]   = tmp;
                }
            }
        }
    }
}
