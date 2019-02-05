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
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id"),
    @NamedQuery(name = "Produto.findByNmproduto", query = "SELECT p FROM Produto p WHERE p.nmproduto = :nmproduto"),
    @NamedQuery(name = "Produto.findByDsobservacaoproduto", query = "SELECT p FROM Produto p WHERE p.dsobservacaoproduto = :dsobservacaoproduto"),
    @NamedQuery(name = "Produto.findByClassificacao", query = "SELECT p FROM Produto p WHERE p.classificacao = :classificacao"),
    @NamedQuery(name = "Produto.findByIeativo", query = "SELECT p FROM Produto p WHERE p.ieativo = :ieativo"),
    @NamedQuery(name = "Produto.findByDtatualizacao", query = "SELECT p FROM Produto p WHERE p.dtatualizacao = :dtatualizacao"),
    @NamedQuery(name = "Produto.findByVlproduto", query = "SELECT p FROM Produto p WHERE p.vlproduto = :vlproduto")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 70)
    @Column(name = "NMPRODUTO")
    private String nmproduto;
    @Size(max = 255)
    @Column(name = "DSOBSERVACAOPRODUTO")
    private String dsobservacaoproduto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASSIFICACAO")
    private int classificacao;
    @Column(name = "IEATIVO")
    private Short ieativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DTATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VLPRODUTO")
    private Float vlproduto;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, int classificacao, Date dtatualizacao) {
        this.id = id;
        this.classificacao = classificacao;
        this.dtatualizacao = dtatualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmproduto() {
        return nmproduto;
    }

    public void setNmproduto(String nmproduto) {
        this.nmproduto = nmproduto;
    }

    public String getDsobservacaoproduto() {
        return dsobservacaoproduto;
    }

    public void setDsobservacaoproduto(String dsobservacaoproduto) {
        this.dsobservacaoproduto = dsobservacaoproduto;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
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

    public Float getVlproduto() {
        return vlproduto;
    }

    public void setVlproduto(Float vlproduto) {
        this.vlproduto = vlproduto;
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
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CLASSE.Produto[ id=" + id + " ]";
    }
    
}
