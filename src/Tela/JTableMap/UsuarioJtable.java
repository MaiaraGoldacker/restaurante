/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela.JTableMap;

import Dao.UsuarioDAO;
import Classe.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class UsuarioJtable extends AbstractTableModel {

    private List<Usuario> dados;
    private String[] colunas = {"Nome", "Usuário", "Tipo de permissão"};

    public UsuarioJtable() throws SQLException {
        dados = UsuarioDAO.getInstance().findAll();
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
                return dados.get(linha).getNmusuario();
            case 1:
                return dados.get(linha).getDsusuario();
            case 2:

                if (dados.get(linha).getIetipopermissao() == 0) {
                    return "Total";
                } else if (dados.get(linha).getIetipopermissao() == 1) {
                    return "Parcial";
                } else {
                    return "Mínima";
                }
        }
        return null;
    }

    public Usuario get(int linha) {
        return this.dados.get(linha);
    }
}
