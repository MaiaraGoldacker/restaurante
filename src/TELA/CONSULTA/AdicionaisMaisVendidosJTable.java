/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA.CONSULTA;

import CLASSE.Adicional;
import DAO.AdicionaisDAO;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class AdicionaisMaisVendidosJTable extends AbstractTableModel {

    private List<Adicional> dados;
    private String[] colunas = {"Quantidade", "Adicional"};
    private NumberFormat f = NumberFormat.getCurrencyInstance();

    public AdicionaisMaisVendidosJTable(List<Adicional> dadosV) throws SQLException {

        dados = dadosV;

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
                return String.valueOf(dados.get(linha).getVladicional()).replace(".0", "");
            case 1:
                return dados.get(linha).getNmadicional();
        }
        return null;
    }

    public Adicional get(int linha) {
        return this.dados.get(linha);
    }
}
