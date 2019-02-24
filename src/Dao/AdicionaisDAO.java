package Dao;

import Classe.Adicional;
import Regra.Utilidades;
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
public class AdicionaisDAO {

    private static AdicionaisDAO instance;
    protected EntityManager entityManager;

    public static AdicionaisDAO getInstance() {
        if (instance == null) {
            instance = new AdicionaisDAO();
        }

        return instance;
    }

    private AdicionaisDAO() {
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
        Adicional a = entityManager.find(Adicional.class, id);
        a.setIeativo((short) 0);
        merge(a);
    }

    @SuppressWarnings("unchecked")
    public List<Adicional> findAll() throws SQLException {
        List<Adicional> com = new ArrayList<Adicional>();
        Statement stmt = Utilidades.getInstance().pegarConexaoBD().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM ADICIONAL WHERE IEATIVO=1");
        while (rs.next()) {
            com.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegarConexaoBD().close();
        return com;
    }

    public List<Adicional> findAllMaisVendidos() throws SQLException {
        List<Adicional> adic = new ArrayList<Adicional>();

        String sql = " select a.id, count(ap.id) apelido from pagamento pa  inner join pedido as pe on pa.id = pe.pagamento_id  \n"
                + "inner join produto as pr on  pr.id = pe.produto_id  \n"
                + "inner join adicional_pedido ap on ap.pedido_id = pe.id and ap.produto_id = pr.id\n"
                + "inner join adicional a on a.id = ap.adicional_id \n"
                + "where 1=1\n"
                + "and pr.ieativo = 1\n"
                + "and pa.ieativo = 1\n"
                + "group by a.id\n"
                + "order by count(ap.id) desc";

        PreparedStatement stmt = Utilidades.getInstance().pegarConexaoBD().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            adic.add(getById(rs.getInt("ID")));

            for (Adicional a : adic) {
                if (a.getId() == rs.getInt("ID")) {
                    a.setVladicional(rs.getInt("apelido"));
                }
            }
        }
        Utilidades.getInstance().pegarConexaoBD().close();
        return adic;
    }

    public Adicional getById(final int id) {
        return entityManager.find(Adicional.class, id);
    }

    public void persist(Adicional adicional) {    
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(adicional);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Adicional adicional) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(adicional);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
