/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela;

import Dao.PedidoDAO;
import Classe.Pedido;
import Regra.RegrasPedido;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maiara
 */
public class ConsultaPedido extends javax.swing.JFrame {

    /**
     * Creates new form cadastroProdutos
     */
    public ConsultaPedido() {

        initComponents();

        limpaTela();
        carregaTelaPedidos();
        carregaDetalhesTela();
    }

    private void carregaDetalhesTela() {
        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/sair.png")));
        btatu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/baix.png")));
        btatu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/baix.png")));
        btatu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/baix.png")));
        btatu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/baix.png")));
        btatu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/baix.png")));
        btatu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/baix.png")));
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/carr.png")));
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Icone/consped.png")).getImage());

        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);

        setDefaultCloseOperation(RealizarPedido.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                btSair.doClick();
            }
        });

        ThreadCalculaAtualizacao thread = new ThreadCalculaAtualizacao();
        thread.start();

        this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void carregaTelaPedidos() {
        try {
            Date dataPedido = null;
            List<Pedido> lista = null;
            List<Pedido> listaComanda = null;
            lista = PedidoDAO.getInstance().findAllPedidos();
            String desc = "";
            for (Pedido ped : lista) {
                listaComanda = PedidoDAO.getInstance().findAllAtender(1, ped.getComandaId());
                desc = "";
                for (Pedido pedCom : listaComanda) {
                    if (!desc.equalsIgnoreCase("")) {
                        desc += "\n";
                    }
                    desc += RegrasPedido.getInstance().carregarPedidosRealizados(pedCom);
                    dataPedido = pedCom.getDtatualizacao();
                }
                atualizaInformacoesPedido(dataPedido, desc, ped);
            }

        } catch (Exception ex) {
            Logger.getLogger(ConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atualizaInformacoesPedido(Date dataPedido, String desc, Pedido ped) {
        if (taDescPedido.getText().equalsIgnoreCase("")) {
            jPanel1.setVisible(true);
            taDescPedido.setText(desc);
            lbNrPedido.setText(String.valueOf(ped.getComandaId()));
            RegrasPedido.getInstance().calcularDiferencaHoras(dataPedido, lbtempo);
        } else if (taDescPedido1.getText().equalsIgnoreCase("")) {
            jPanel2.setVisible(true);
            taDescPedido1.setText(desc);
            lbNrPedido1.setText(String.valueOf(ped.getComandaId()));
            RegrasPedido.getInstance().calcularDiferencaHoras(dataPedido, lbtempo1);
        } else if (taDescPedido2.getText().equalsIgnoreCase("")) {
            jPanel3.setVisible(true);
            taDescPedido2.setText(desc);
            lbNrPedido2.setText(String.valueOf(ped.getComandaId()));
            RegrasPedido.getInstance().calcularDiferencaHoras(dataPedido, lbtempo2);
        } else if (taDescPedido3.getText().equalsIgnoreCase("")) {
            jPanel4.setVisible(true);
            taDescPedido3.setText(desc);
            lbNrPedido3.setText(String.valueOf(ped.getComandaId()));
            RegrasPedido.getInstance().calcularDiferencaHoras(dataPedido, lbtempo3);
        } else if (taDescPedido4.getText().equalsIgnoreCase("")) {
            jPanel5.setVisible(true);
            taDescPedido4.setText(desc);
            lbNrPedido4.setText(String.valueOf(ped.getComandaId()));
            RegrasPedido.getInstance().calcularDiferencaHoras(dataPedido, lbtempo4);
        } else if (taDescPedido5.getText().equalsIgnoreCase("")) {
            jPanel6.setVisible(true);
            taDescPedido5.setText(desc);
            lbNrPedido5.setText(String.valueOf(ped.getComandaId()));
            RegrasPedido.getInstance().calcularDiferencaHoras(dataPedido, lbtempo5);
        }
    }

    private void limpaTela() {
        taDescPedido.setText("");
        taDescPedido1.setText("");
        taDescPedido2.setText("");
        taDescPedido3.setText("");
        taDescPedido4.setText("");
        taDescPedido5.setText("");

        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbNrPedido = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescPedido = new javax.swing.JTextArea();
        btatu = new javax.swing.JButton();
        lbtempo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDescPedido1 = new javax.swing.JTextArea();
        btatu1 = new javax.swing.JButton();
        lbNrPedido1 = new javax.swing.JLabel();
        lbtempo1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taDescPedido2 = new javax.swing.JTextArea();
        btatu2 = new javax.swing.JButton();
        lbNrPedido2 = new javax.swing.JLabel();
        lbtempo2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btatu4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        taDescPedido3 = new javax.swing.JTextArea();
        lbNrPedido3 = new javax.swing.JLabel();
        lbtempo3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btatu3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        taDescPedido4 = new javax.swing.JTextArea();
        lbNrPedido4 = new javax.swing.JLabel();
        lbtempo4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        taDescPedido5 = new javax.swing.JTextArea();
        btatu5 = new javax.swing.JButton();
        lbNrPedido5 = new javax.swing.JLabel();
        lbtempo5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btAtualizar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbNrPedido.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbNrPedido.setText("jLabel1");

        taDescPedido.setColumns(20);
        taDescPedido.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        taDescPedido.setRows(5);
        jScrollPane1.setViewportView(taDescPedido);

        btatu.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btatu.setText("Atendido");
        btatu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btatuActionPerformed(evt);
            }
        });

        lbtempo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbtempo.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbtempo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btatu)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addComponent(lbNrPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbNrPedido)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btatu)
                    .addComponent(lbtempo))
                .addGap(5, 5, 5))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        taDescPedido1.setColumns(20);
        taDescPedido1.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        taDescPedido1.setRows(5);
        jScrollPane2.setViewportView(taDescPedido1);

        btatu1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btatu1.setText("Atendido");
        btatu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btatu1ActionPerformed(evt);
            }
        });

        lbNrPedido1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbNrPedido1.setText("jLabel1");

        lbtempo1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbtempo1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbtempo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btatu1)
                        .addContainerGap())
                    .addComponent(lbNrPedido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lbNrPedido1)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtempo1)
                    .addComponent(btatu1))
                .addGap(9, 9, 9))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        taDescPedido2.setColumns(20);
        taDescPedido2.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        taDescPedido2.setRows(5);
        jScrollPane3.setViewportView(taDescPedido2);

        btatu2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btatu2.setText("Atendido");
        btatu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btatu2ActionPerformed(evt);
            }
        });

        lbNrPedido2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbNrPedido2.setText("jLabel1");

        lbtempo2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbtempo2.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbtempo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btatu2)
                        .addContainerGap())
                    .addComponent(lbNrPedido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbNrPedido2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtempo2)
                    .addComponent(btatu2))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btatu4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btatu4.setText("Atendido");
        btatu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btatu4ActionPerformed(evt);
            }
        });

        taDescPedido3.setColumns(20);
        taDescPedido3.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        taDescPedido3.setRows(5);
        jScrollPane4.setViewportView(taDescPedido3);

        lbNrPedido3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbNrPedido3.setText("jLabel1");

        lbtempo3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbtempo3.setText("jLabel1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbtempo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btatu4)
                        .addGap(18, 18, 18))
                    .addComponent(lbNrPedido3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbNrPedido3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btatu4)
                    .addComponent(lbtempo3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btatu3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btatu3.setText("Atendido");
        btatu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btatu3ActionPerformed(evt);
            }
        });

        taDescPedido4.setColumns(20);
        taDescPedido4.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        taDescPedido4.setRows(5);
        jScrollPane5.setViewportView(taDescPedido4);

        lbNrPedido4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbNrPedido4.setText("jLabel1");

        lbtempo4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbtempo4.setText("jLabel1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbtempo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btatu3)
                        .addGap(27, 27, 27))
                    .addComponent(lbNrPedido4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lbNrPedido4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btatu3)
                    .addComponent(lbtempo4))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        taDescPedido5.setColumns(20);
        taDescPedido5.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        taDescPedido5.setRows(5);
        jScrollPane6.setViewportView(taDescPedido5);

        btatu5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btatu5.setText("Atendido");
        btatu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btatu5ActionPerformed(evt);
            }
        });

        lbNrPedido5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbNrPedido5.setText("jLabel1");

        lbtempo5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbtempo5.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbtempo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btatu5)
                        .addContainerGap())
                    .addComponent(lbNrPedido5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lbNrPedido5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btatu5)
                    .addComponent(lbtempo5))
                .addContainerGap())
        );

        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btAtualizar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.setActionCommand("Alt");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        btSair.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btSair.setText("Sair");
        btSair.setActionCommand("Alt");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSair)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSair, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btAtualizar))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Consulta de Pedidos");
        jLabel4.setAlignmentX(0.5F);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel4.setName("lbGerenciaProj"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 639, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        limpaTela();
        carregaTelaPedidos();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btatuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btatuActionPerformed
        try {
            RegrasPedido.getInstance().alterarStatusPedidos(PedidoDAO.getInstance().findAllAtender(1,Integer.parseInt(lbNrPedido.getText())));
            limpaTela();
            carregaTelaPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btatuActionPerformed

    private void btatu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btatu1ActionPerformed
        try {
            RegrasPedido.getInstance().alterarStatusPedidos(PedidoDAO.getInstance().findAllAtender(1,Integer.parseInt(lbNrPedido1.getText())));
            limpaTela();
            carregaTelaPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btatu1ActionPerformed

    private void btatu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btatu2ActionPerformed
        try {
            RegrasPedido.getInstance().alterarStatusPedidos(PedidoDAO.getInstance().findAllAtender(1,Integer.parseInt(lbNrPedido2.getText())));
            limpaTela();
            carregaTelaPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btatu2ActionPerformed

    private void btatu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btatu3ActionPerformed
        try {
            RegrasPedido.getInstance().alterarStatusPedidos(PedidoDAO.getInstance().findAllAtender(1, Integer.parseInt(lbNrPedido3.getText())));
            limpaTela();
            carregaTelaPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btatu3ActionPerformed

    private void btatu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btatu4ActionPerformed
        try {
            RegrasPedido.getInstance().alterarStatusPedidos(PedidoDAO.getInstance().findAllAtender(1,Integer.parseInt(lbNrPedido4.getText())));
            limpaTela();
            carregaTelaPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btatu4ActionPerformed

    private void btatu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btatu5ActionPerformed
        try {
            RegrasPedido.getInstance().alterarStatusPedidos(PedidoDAO.getInstance().findAllAtender(1,Integer.parseInt(lbNrPedido5.getText())));
            limpaTela();
            carregaTelaPedidos();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btatu5ActionPerformed

    class ThreadCalculaAtualizacao extends Thread {

        public void run() {
            while (true) {
                btAtualizar.doClick();

                try {
                    Thread.sleep(120000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaPedido.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaPedido().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btatu;
    private javax.swing.JButton btatu1;
    private javax.swing.JButton btatu2;
    private javax.swing.JButton btatu3;
    private javax.swing.JButton btatu4;
    private javax.swing.JButton btatu5;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbNrPedido;
    private javax.swing.JLabel lbNrPedido1;
    private javax.swing.JLabel lbNrPedido2;
    private javax.swing.JLabel lbNrPedido3;
    private javax.swing.JLabel lbNrPedido4;
    private javax.swing.JLabel lbNrPedido5;
    private javax.swing.JLabel lbtempo;
    private javax.swing.JLabel lbtempo1;
    private javax.swing.JLabel lbtempo2;
    private javax.swing.JLabel lbtempo3;
    private javax.swing.JLabel lbtempo4;
    private javax.swing.JLabel lbtempo5;
    private javax.swing.JTextArea taDescPedido;
    private javax.swing.JTextArea taDescPedido1;
    private javax.swing.JTextArea taDescPedido2;
    private javax.swing.JTextArea taDescPedido3;
    private javax.swing.JTextArea taDescPedido4;
    private javax.swing.JTextArea taDescPedido5;
    // End of variables declaration//GEN-END:variables
}
