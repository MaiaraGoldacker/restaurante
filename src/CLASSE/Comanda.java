/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASSE;

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
@Table(name = "comanda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comanda.findAll", query = "SELECT c FROM Comanda c"),
    @NamedQuery(name = "Comanda.findById", query = "SELECT c FROM Comanda c WHERE c.id = :id"),
    @NamedQuery(name = "Comanda.findByIeativo", query = "SELECT c FROM Comanda c WHERE c.ieativo = :ieativo"),
    @NamedQuery(name = "Comanda.findByDsobservacao", query = "SELECT c FROM Comanda c WHERE c.dsobservacao = :dsobservacao"),
    @NamedQuery(name = "Comanda.findByDtatualizacao", query = "SELECT c FROM Comanda c WHERE c.dtatualizacao = :dtatualizacao")})
public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "IEATIVO")
    private Short ieativo;
    @Size(max = 250)
    @Column(name = "DSOBSERVACAO")
    private String dsobservacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacao;

    public Comanda() {
    }

    public Comanda(Integer id) {
        this.id = id;
    }

    public Comanda(Integer id, Date dtatualizacao) {
        this.id = id;
        this.dtatualizacao = dtatualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getIeativo() {
        return ieativo;
    }

    public void setIeativo(Short ieativo) {
        this.ieativo = ieativo;
    }

    public String getDsobservacao() {
        return dsobservacao;
    }

    public void setDsobservacao(String dsobservacao) {
        this.dsobservacao = dsobservacao;
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
        if (!(object instanceof Comanda)) {
            return false;
        }
        Comanda other = (Comanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CLASSE.Comanda[ id=" + id + " ]";
    }
    
}
