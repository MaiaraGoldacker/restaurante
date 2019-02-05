/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA.CONSULTA;

import CLASSE.Usuario;
import DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class UsuariosMaisVendeuJTable extends AbstractTableModel{
    
    private List<Usuario> dados;
    private String[] colunas = {"Quantidade", "Usuário"};

    public UsuariosMaisVendeuJTable(List<Usuario> dadosV) throws SQLException {
        dados = dadosV;
    }

    public void addRow(Usuario p) {
        this.dados.add(p);
        this.fireTableDataChanged();
    }

    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getDsusuario(); 
                
            case 1:
               return dados.get(linha).getNmusuario();          
        }
        return null;
    }

    public Usuario get(int linha) {
        return this.dados.get(linha);
    }
}
