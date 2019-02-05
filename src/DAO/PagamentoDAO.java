package DAO;

import CLASSE.Pagamento;
import CLASSE.Pedido;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

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
        //   remove(entityManager.find(Produto.class, id));
        Pagamento p = entityManager.find(Pagamento.class, id);
        merge(p);
    }

    @SuppressWarnings("unchecked")
    public List<Pagamento> findAll() throws SQLException {
        List<Pagamento> prod = new ArrayList<Pagamento>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT ID FROM Pagamento");
        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        conn.close();
        return prod;
    }

    public List<Pagamento> findAllPersonalizado(Date dtIni, Date dtFim, int idUsuario, int idPagto) throws SQLException {
        List<Pagamento> prod = new ArrayList<Pagamento>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        PreparedStatement stmt = null;

        String sql = "";
        String dtI = "";
        String dtF = "";
        if (dtIni != null && dtFim != null) {
            dtI = String.valueOf(dtIni) + " 00:00:00";
            dtF = String.valueOf(dtFim) + " 23:59:59";
        }

        sql = " select pa.ID from pagamento pa "
                + " inner join pedido as pe on pa.id = pe.pagamento_id "
                + " inner join produto as pr on  pr.id = pe.produto_id "
                + " where 1=1 "
                + " and (DTATULIZACAO BETWEEN ? AND ? "
                + " or (? = '' and ? = '')) "
                + " and (pa.usuario_id = ? or ? = 0) "
                + " and (pa.id = ? or ? = 0) "
                + " group by pa.id "
                + " order by pa.DTATULIZACAO ";

        stmt = conn.prepareStatement(sql);

        stmt.setString(1, dtI);
        stmt.setString(2, dtF);
        stmt.setString(3, dtI);
        stmt.setString(4, dtF);
        stmt.setInt(5, idUsuario);
        stmt.setInt(6, idUsuario);
        stmt.setInt(7, idPagto);
        stmt.setInt(8, idPagto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        conn.close();
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

    private void remove(Pagamento pagamento) {
        try {
            entityManager.getTransaction().begin();
            pagamento
                    = entityManager.find(Pagamento.class, pagamento.getId());
            entityManager.remove(pagamento);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
