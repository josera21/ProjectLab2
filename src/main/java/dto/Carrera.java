/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author josera
 */
@Entity
public class Carrera {
    @Id
    private int codigo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cod_decanato")
    private Decanato decanato;
    
    private String nombre;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the decanato
     */
    public Decanato getDecanato() {
        return decanato;
    }

    /**
     * @param decanato the decanato to set
     */
    public void setDecanato(Decanato decanato) {
        this.decanato = decanato;
    }
    
    
}
