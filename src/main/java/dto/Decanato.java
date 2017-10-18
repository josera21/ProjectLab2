/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author josera
 */
@Entity
public class Decanato implements Serializable {
    @Id
    @GeneratedValue
    private int codigo;
    
    @OneToMany(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
    private Set<Carrera> carerra = new HashSet();
    
    private String nombre;
    private String Direccion;

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
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the carerra
     */
    public Set<Carrera> getCarerra() {
        return carerra;
    }

    /**
     * @param carerra the carerra to set
     */
    public void setCarerra(Set<Carrera> carerra) {
        this.carerra = carerra;
    }
}
