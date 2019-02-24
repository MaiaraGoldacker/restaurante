/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela.JTableMap;

import Dao.ComandaDAO;
import Classe.Comanda;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */

    public class ComandaJtable extends AbstractTableModel{
     
    private List<Comanda> dados;
    private String[] colunas = {"Código" , "Observação"};
     
    public ComandaJtable() throws SQLException{
        dados = ComandaDAO.getInstance().findAll(); 
    }
     
    public void addRow(Comanda c){
        this.dados.add(c);
        this.fireTableDataChanged();
    }
 
    public String getColumnName(int num){
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
        switch(coluna){
            case 0: return dados.get(linha).getId();
            case 1: return dados.get(linha).getDsobservacao();
        }   
        return null;
    }
    
    public Comanda get(int linha){
        return this.dados.get(linha);
    }
}

