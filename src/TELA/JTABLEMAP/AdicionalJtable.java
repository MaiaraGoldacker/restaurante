/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA.JTABLEMAP;

import DAO.AdicionaisDAO;
import CLASSE.Adicional;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class AdicionalJtable extends AbstractTableModel {

    private List<Adicional> dados;
    private String[] colunas = {"Nome", "Valor do adicional"};
    private NumberFormat f = NumberFormat.getCurrencyInstance();

    public AdicionalJtable(List<Adicional> dadosV) throws SQLException {

        if (dadosV != null) {
            dados = dadosV;
        } else {
            dados = AdicionaisDAO.getInstance().findAll();
        }
    }

    public void addRow(Adicional a) {
        this.dados.add(a);
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
                return dados.get(linha).getNmadicional();
            case 1:
                return f.format(dados.get(linha).getVladicional());
        }
        return null;
    }

    public Adicional get(int linha) {
        return this.dados.get(linha);
    }
}
