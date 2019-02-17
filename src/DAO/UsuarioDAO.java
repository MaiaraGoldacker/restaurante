package DAO;

import CLASSE.Usuario;
import REGRAS.Utilidades;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author maiara
 */
public class UsuarioDAO {

    private static UsuarioDAO instance;
    protected EntityManager entityManager;
    private Usuario usuarioLogado;

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }

        return instance;
    }

    private UsuarioDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidosPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public void removeById(final int id) {
        Usuario u = entityManager.find(Usuario.class, id);
        u.setIeativo((short) 0);
        merge(u);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usua = new ArrayList<Usuario>();
        Statement stmt = Utilidades.getInstance().pegaConexaoBD().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM USUARIO WHERE IEATIVO = 1");

        while (rs.next()) {
            usua.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return usua;
    }

    public List<Usuario> findByUser(String dsUsuario) throws SQLException {
        List<Usuario> usua = new ArrayList<Usuario>();

        PreparedStatement stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement("SELECT ID FROM USUARIO WHERE IEATIVO = 1 AND DSUSUARIO = ?");
        stmt.setString(1, dsUsuario);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            usua.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return usua;
    }

    public Usuario getById(final int id) {
        return entityManager.find(Usuario.class, id);
    }

    public void persist(Usuario usuario) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Usuario> findAllMaisVendeu() throws SQLException {
        List<Usuario> usua = new ArrayList<Usuario>();

        String sql = " select usu.id, count(usu.id) apelido, usu.nmUsuario from pagamento pa  inner join usuario as usu on pa.usuario_id = usu.id\n"
                + "where 1=1\n"
                + "and usu.ieativo = 1\n"
                + "and pa.ieativo = 1\n"
                + "group by usu.id\n"
                + "order by count(usu.id) desc\n";

        PreparedStatement stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            usua.add(getById(rs.getInt("ID")));

            for (Usuario a : usua) {
                if (a.getId() == rs.getInt("ID")) {
                    a.setDsusuario(rs.getString("apelido"));
                }
            }
        }
        Utilidades.getInstance().pegaConexaoBD().close();
        return usua;
    }

    public void merge(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    /**
     * @return the usuarioLogado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
