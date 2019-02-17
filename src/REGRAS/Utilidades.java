package REGRAS;

import CLASSE.Usuario;
import DAO.UsuarioDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author maiara
 */
public class Utilidades {

    private static Utilidades instance;

    public static Utilidades getInstance() {
        if (instance == null) {
            instance = new Utilidades();
        }

        return instance;
    }

    public String formataDataFiltro(String data, boolean ehInicio) throws ParseException {
        if (!data.equalsIgnoreCase("  /  /    ") && !data.equalsIgnoreCase("")) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            Date d = df.parse(data);
            df = new SimpleDateFormat("yyyy-MM-dd");
            if (ehInicio) {
                data = df.format(d) + " 00:00:00";
            } else {
                data = df.format(d) + " 23:59:59";
            }

        } else {
            data = "";
        }
        return data;
    }

    public Connection pegaConexaoBD() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        return conn;
    }

    public DefaultComboBoxModel carregaComboUsuario() throws SQLException {
        List<Usuario> lista = UsuarioDAO.getInstance().findAll();
        Vector vetor = new Vector();

        vetor.add("Sem filtro");
        for (Usuario u : lista) {
            vetor.add(u.getDsusuario());
        }
        DefaultComboBoxModel m = new DefaultComboBoxModel(vetor);
        return m;
    }
}
