package DAO;

import CLASSE.Pagamento;
import REGRAS.Utilidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author maiara
 */
public class PagamentoDAO {

    private static PagamentoDAO instance;
    protected EntityManager entityManager;

    public static PagamentoDAO getInstance() {
        if (instance == null) {
            instance = new PagamentoDAO();
        }

        return instance;
    }

    private PagamentoDAO() {
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
        Pagamento p = entityManager.find(Pagamento.class, id);
        merge(p);
    }

    @SuppressWarnings("unchecked")
    public List<Pagamento> findAll() throws SQLException {
        List<Pagamento> prod = new ArrayList<Pagamento>();
        Statement stmt = Utilidades.getInstance().pegaConexaoBD().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM Pagamento");
        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return prod;
    }

    public List<Pagamento> findAllPersonalizado(String dtIni, String dtFim, int idUsuario, int idPagto) throws SQLException {
        List<Pagamento> prod = new ArrayList<Pagamento>();
        PreparedStatement stmt = null;
        String sql = " select pa.ID from pagamento pa "
                + " inner join pedido as pe on pa.id = pe.pagamento_id "
                + " inner join produto as pr on  pr.id = pe.produto_id "
                + " where 1=1 "
                + " and (DTATULIZACAO BETWEEN ? AND ? "
                + " or (? = '' and ? = '')) "
                + " and (pa.usuario_id = ? or ? = 0) "
                + " and (pa.id = ? or ? = 0) "
                + " group by pa.id "
                + " order by pa.DTATULIZACAO ";

        stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement(sql);

        stmt.setString(1, dtIni);
        stmt.setString(2, dtFim);
        stmt.setString(3, dtIni);
        stmt.setString(4, dtFim);
        stmt.setInt(5, idUsuario);
        stmt.setInt(6, idUsuario);
        stmt.setInt(7, idPagto);
        stmt.setInt(8, idPagto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return prod;
    }

    public Pagamento getById(final int id) {
        return entityManager.find(Pagamento.class, id);
    }

    public int persist(Pagamento pagamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pagamento);
            entityManager.getTransaction().commit();
            return pagamento.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }

    public void merge(Pagamento pagamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(pagamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
