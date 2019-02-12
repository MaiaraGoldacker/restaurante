/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASSE;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maiara
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNmusuario", query = "SELECT u FROM Usuario u WHERE u.nmusuario = :nmusuario"),
    @NamedQuery(name = "Usuario.findByDsusuario", query = "SELECT u FROM Usuario u WHERE u.dsusuario = :dsusuario"),
    @NamedQuery(name = "Usuario.findByIeativo", query = "SELECT u FROM Usuario u WHERE u.ieativo = :ieativo"),
    @NamedQuery(name = "Usuario.findByIetipopermissao", query = "SELECT u FROM Usuario u WHERE u.ietipopermissao = :ietipopermissao")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 150)
    @Column(name = "NMUSUARIO")
    private String nmusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DSUSUARIO")
    private String dsusuario;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DSSENHA")
    private byte[] dssenha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IEATIVO")
    private short ieativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IETIPOPERMISSAO")
    private int ietipopermissao;
    @Lob
    @Column(name = "SALT")
    private byte[] salt;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String dsusuario, byte[] dssenha, short ieativo, int ietipopermissao) {
        this.id = id;
        this.dsusuario = dsusuario;
        this.dssenha = dssenha;
        this.ieativo = ieativo;
        this.ietipopermissao = ietipopermissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmusuario() {
        return nmusuario;
    }

    public void setNmusuario(String nmusuario) {
        this.nmusuario = nmusuario;
    }

    public String getDsusuario() {
        return dsusuario;
    }

    public void setDsusuario(String dsusuario) {
        this.dsusuario = dsusuario;
    }

    public byte[] getDssenha() {
        return dssenha;
    }

    public void setDssenha(byte[] dssenha) {
        this.dssenha = dssenha;
    }

    public short getIeativo() {
        return ieativo;
    }

    public void setIeativo(short ieativo) {
        this.ieativo = ieativo;
    }

    public int getIetipopermissao() {
        return ietipopermissao;
    }

    public void setIetipopermissao(int ietipopermissao) {
        this.ietipopermissao = ietipopermissao;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CLASSE.Usuario[ id=" + id + " ]";
    }
    
}
