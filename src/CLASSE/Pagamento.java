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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maiara
 */
@Entity
@Table(name = "pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p"),
    @NamedQuery(name = "Pagamento.findById", query = "SELECT p FROM Pagamento p WHERE p.id = :id"),
    @NamedQuery(name = "Pagamento.findByTxdesconto", query = "SELECT p FROM Pagamento p WHERE p.txdesconto = :txdesconto"),
    @NamedQuery(name = "Pagamento.findByTxacrescimo", query = "SELECT p FROM Pagamento p WHERE p.txacrescimo = :txacrescimo"),
    @NamedQuery(name = "Pagamento.findByDtatulizacao", query = "SELECT p FROM Pagamento p WHERE p.dtatulizacao = :dtatulizacao"),
    @NamedQuery(name = "Pagamento.findByUsuarioId", query = "SELECT p FROM Pagamento p WHERE p.usuarioId = :usuarioId"),
    @NamedQuery(name = "Pagamento.findByIeativo", query = "SELECT p FROM Pagamento p WHERE p.ieativo = :ieativo"),
    @NamedQuery(name = "Pagamento.findByVltotal", query = "SELECT p FROM Pagamento p WHERE p.vltotal = :vltotal")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TXDESCONTO")
    private Double txdesconto;
    @Column(name = "TXACRESCIMO")
    private Double txacrescimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTATULIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatulizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO_ID")
    private int usuarioId;
    @Column(name = "IEATIVO")
    private Short ieativo;
    @Column(name = "VLTOTAL")
    private Float vltotal;

    public Pagamento() {
    }

    public Pagamento(Integer id) {
        this.id = id;
    }

    public Pagamento(Integer id, Date dtatulizacao, int usuarioId) {
        this.id = id;
        this.dtatulizacao = dtatulizacao;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id   ;
    }

    public Double getTxdesconto() {
        return txdesconto;
    }

    public void setTxdesconto(Double txdesconto) {
        this.txdesconto = txdesconto;
    }

    public Double getTxacrescimo() {
        return txacrescimo;
    }

    public void setTxacrescimo(Double txacrescimo) {
        this.txacrescimo = txacrescimo;
    }

    public Date getDtatulizacao() {
        return dtatulizacao;
    }

    public void setDtatulizacao(Date dtatulizacao) {
        this.dtatulizacao = dtatulizacao;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Short getIeativo() {
        return ieativo;
    }

    public void setIeativo(Short ieativo) {
        this.ieativo = ieativo;
    }

    public Float getVltotal() {
        return vltotal;
    }

    public void setVltotal(Float vltotal) {
        this.vltotal = vltotal;
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
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CLASSE.Pagamento[ id=" + id + " ]";
    }
    
}
