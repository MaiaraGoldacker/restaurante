/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author maiara 
 */
@Entity
@Table(name = "adicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adicional.findAll", query = "SELECT a FROM Adicional a"),
    @NamedQuery(name = "Adicional.findById", query = "SELECT a FROM Adicional a WHERE a.id = :id"),
    @NamedQuery(name = "Adicional.findByNmadicional", query = "SELECT a FROM Adicional a WHERE a.nmadicional = :nmadicional"),
    @NamedQuery(name = "Adicional.findByVladicional", query = "SELECT a FROM Adicional a WHERE a.vladicional = :vladicional"),
    @NamedQuery(name = "Adicional.findByIeativo", query = "SELECT a FROM Adicional a WHERE a.ieativo = :ieativo"),
    @NamedQuery(name = "Adicional.findByDtatualizacao", query = "SELECT a FROM Adicional a WHERE a.dtatualizacao = :dtatualizacao")})
public class Adicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    
    @Size(min = 1, max = 45)
    @Column(name = "NMADICIONAL")
    @NotNull(message = "Campo Nome do adicional é obrigatório")
    private String nmadicional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VLADICIONAL")
    private float vladicional;
    @Column(name = "IEATIVO")
    private Short ieativo;
    @Column(name = "DTATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacao;
    
  

    public Adicional() {
    }

    public Adicional(Integer id) {
        this.id = id;
    }

    public Adicional(Integer id, String nmadicional, float vladicional) {
        this.id = id;
        this.nmadicional = nmadicional;
        this.vladicional = vladicional;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmadicional() {
        return nmadicional;
    }

    public void setNmadicional(String nmadicional) {
        this.nmadicional = nmadicional;
    }

    public float getVladicional() {
        return vladicional;
    }

    public void setVladicional(float vladicional) {
        this.vladicional = vladicional;
    }

    public Short getIeativo() {
        return ieativo;
    }

    public void setIeativo(Short ieativo) {
        this.ieativo = ieativo;
    }

    public Date getDtatualizacao() {
        return dtatualizacao;
    }

    public void setDtatualizacao(Date dtatualizacao) {
        this.dtatualizacao = dtatualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adicional)) {
            return false;
        }
        Adicional other = (Adicional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CLASSE.Adicional[ id=" + id + " ]";
    }

    /**
     * @return the quantAdicVendidos
     */
  
    
}
