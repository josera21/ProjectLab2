/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author josera
 */
@Entity
public class Servicio implements Serializable{
    @Id
    private String usuariocedula;
    @Id
    private String articulocodigo;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn(name="usuariocedula", referencedColumnName="cedula")
    private Usuario usuario;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn(name="articulocodigo", referencedColumnName="codigo")
    private Articulo articulo;
    
    private String tipoServicio;

    /**
     * @return the usuariocedula
     */
    public String getUsuariocedula() {
        return usuariocedula;
    }

    /**
     * @param usuariocedula the usuariocedula to set
     */
    public void setUsuariocedula(String usuariocedula) {
        this.usuariocedula = usuariocedula;
    }

    /**
     * @return the articulocodigo
     */
    public String getArticulocodigo() {
        return articulocodigo;
    }

    /**
     * @param articulocodigo the articulocodigo to set
     */
    public void setArticulocodigo(String articulocodigo) {
        this.articulocodigo = articulocodigo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the tipoServicio
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * @param tipoServicio the tipoServicio to set
     */
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /**
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
