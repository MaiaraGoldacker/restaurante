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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM Pedido p WHERE p.id = :id"),
    @NamedQuery(name = "Pedido.findByObspedido", query = "SELECT p FROM Pedido p WHERE p.obspedido = :obspedido"),
    @NamedQuery(name = "Pedido.findByDtatualizacao", query = "SELECT p FROM Pedido p WHERE p.dtatualizacao = :dtatualizacao"),
    @NamedQuery(name = "Pedido.findByStpedido", query = "SELECT p FROM Pedido p WHERE p.stpedido = :stpedido"),
    @NamedQuery(name = "Pedido.findByProdutoId", query = "SELECT p FROM Pedido p WHERE p.produtoId = :produtoId"),
    @NamedQuery(name = "Pedido.findByComandaId", query = "SELECT p FROM Pedido p WHERE p.comandaId = :comandaId"),
    @NamedQuery(name = "Pedido.findByUsuarioId", query = "SELECT p FROM Pedido p WHERE p.usuarioId = :usuarioId"),
    @NamedQuery(name = "Pedido.findByPagamentoId", query = "SELECT p FROM Pedido p WHERE p.pagamentoId = :pagamentoId")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 200)
    @Column(name = "OBSPEDIDO")
    private String obspedido;
    @Column(name = "DTATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STPEDIDO")
    private int stpedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUTO_ID")
    private int produtoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMANDA_ID")
    private int comandaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO_ID")
    private int usuarioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAGAMENTO_ID")
    private int pagamentoId;

    public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Pedido(Integer id, int stpedido, int produtoId, int comandaId, int usuarioId, int pagamentoId) {
        this.id = id;
        this.stpedido = stpedido;
        this.produtoId = produtoId;
        this.comandaId = comandaId;
        this.usuarioId = usuarioId;
        this.pagamentoId = pagamentoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObspedido() {
        return obspedido;
    }

    public void setObspedido(String obspedido) {
        this.obspedido = obspedido;
    }

    public Date getDtatualizacao() {
        return dtatualizacao;
    }

    public void setDtatualizacao(Date dtatualizacao) {
        this.dtatualizacao = dtatualizacao;
    }

    public int getStpedido() {
        return stpedido;
    }

    public void setStpedido(int stpedido) {
        this.stpedido = stpedido;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getComandaId() {
        return comandaId;
    }

    public void setComandaId(int comandaId) {
        this.comandaId = comandaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPagamentoId() {
        return pagamentoId;
    }

    public void setPagamentoId(int pagamentoId) {
        this.pagamentoId = pagamentoId;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CLASSE.Pedido[ id=" + id + " ]";
    }
    
}
