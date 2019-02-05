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
@Table(name = "adicional_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdicionalPedido.findAll", query = "SELECT a FROM AdicionalPedido a"),
    @NamedQuery(name = "AdicionalPedido.findById", query = "SELECT a FROM AdicionalPedido a WHERE a.id = :id"),
    @NamedQuery(name = "AdicionalPedido.findByProdutoId", query = "SELECT a FROM AdicionalPedido a WHERE a.produtoId = :produtoId"),
    @NamedQuery(name = "AdicionalPedido.findByAdicionalId", query = "SELECT a FROM AdicionalPedido a WHERE a.adicionalId = :adicionalId"),
    @NamedQuery(name = "AdicionalPedido.findByPedidoId", query = "SELECT a FROM AdicionalPedido a WHERE a.pedidoId = :pedidoId"),
    @NamedQuery(name = "AdicionalPedido.findByDsobservacao", query = "SELECT a FROM AdicionalPedido a WHERE a.dsobservacao = :dsobservacao"),
    @NamedQuery(name = "AdicionalPedido.findByDtatualizacao", query = "SELECT a FROM AdicionalPedido a WHERE a.dtatualizacao = :dtatualizacao")})
public class AdicionalPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUTO_ID")
    private int produtoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADICIONAL_ID")
    private int adicionalId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEDIDO_ID")
    private int pedidoId;
    @Size(max = 400)
    @Column(name = "DSOBSERVACAO")
    private String dsobservacao;
    @Column(name = "DTATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacao;

    public AdicionalPedido() {
    }

    public AdicionalPedido(Integer id) {
        this.id = id;
    }

    public AdicionalPedido(Integer id, int produtoId, int adicionalId, int pedidoId) {
        this.id = id;
        this.produtoId = produtoId;
        this.adicionalId = adicionalId;
        this.pedidoId = pedidoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getAdicionalId() {
        return adicionalId;
    }

    public void setAdicionalId(int adicionalId) {
        this.adicionalId = adicionalId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
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
        if (!(object instanceof AdicionalPedido)) {
            return false;
        }
        AdicionalPedido other = (AdicionalPedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CLASSE.AdicionalPedido[ id=" + id + " ]";
    }
    
}
