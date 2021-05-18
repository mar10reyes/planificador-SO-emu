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
public class PlanificadorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {   
        SistemaOperativo so = new SistemaOperativo();
        
        so.addProceso("A",1,10);
        so.addProceso("B",3,4);
        so.addProceso("C",2,1);
        
        so.planificarProcesos();
    }
    
}
