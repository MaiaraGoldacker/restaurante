/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela;

import Dao.UsuarioDAO;

/**
 *
 * @author maiara
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form cadastroProdutos
     */
    public Principal() {
        initComponents();

        if (UsuarioDAO.getInstance().getUsuarioLogado().getIetipopermissao() == 1) {
            btCadUsuarios.setVisible(false);         
            btConsPagtos.setVisible(false);
        } else if (UsuarioDAO.getInstance().getUsuarioLogado().getIetipopermissao() == 2) {
            btCadUsuarios.setVisible(false);          
            btConsPagtos.setVisible(false);        
            btCadComanda.setVisible(false);
            btCadProdutos.setVisible(false);
            btCadAdicionais.setVisible(false);   
        }

        btCadUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/user.png")));
        btCadComanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/com.png")));
        btCadProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/food.png")));
        btCadAdicionais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/bacon.png")));
        btRealizarPed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/carrin.png")));
        btRealizarPag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/pay.png")));
        btConsPed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/consped.png")));
        btConsPagtos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icone/paygrande.png")));
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Icone/Restaurant-icon.png")).getImage());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btRealizarPag = new javax.swing.JButton();
        btCadProdutos = new javax.swing.JButton();
        btConsPagtos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btRealizarPed = new javax.swing.JButton();
        btCadComanda = new javax.swing.JButton();
        btCadUsuarios = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btConsPed = new javax.swing.JButton();
        btCadAdicionais = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        opSair = new javax.swing.JMenu();
        opTrocarUsuario = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btRealizarPag.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btRealizarPag.setText("Realizar Pagamento");
        btRealizarPag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRealizarPag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btRealizarPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRealizarPagActionPerformed(evt);
            }
        });

        btCadProdutos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btCadProdutos.setText("Produtos");
        btCadProdutos.setToolTipText("");
        btCadProdutos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCadProdutos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCadProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadProdutosActionPerformed(evt);
            }
        });

        btConsPagtos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btConsPagtos.setText("Consulta de Lucros");
        btConsPagtos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btConsPagtos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btConsPagtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsPagtosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btRealizarPag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btCadProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btConsPagtos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btRealizarPag)
                .addGap(93, 93, 93)
                .addComponent(btCadProdutos)
                .addGap(95, 95, 95)
                .addComponent(btConsPagtos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btRealizarPed.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btRealizarPed.setText("Realizar Pedidos");
        btRealizarPed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRealizarPed.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btRealizarPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRealizarPedActionPerformed(evt);
            }
        });

        btCadComanda.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btCadComanda.setText("Comandas");
        btCadComanda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCadComanda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCadComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadComandaActionPerformed(evt);
            }
        });

        btCadUsuarios.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btCadUsuarios.setText("Usuários");
        btCadUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCadUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCadUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btCadComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btRealizarPed, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(btCadUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btRealizarPed)
                .addGap(92, 92, 92)
                .addComponent(btCadComanda)
                .addGap(100, 100, 100)
                .addComponent(btCadUsuarios)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        btConsPed.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btConsPed.setText("Consulta de Pedidos");
        btConsPed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btConsPed.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btConsPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsPedActionPerformed(evt);
            }
        });

        btCadAdicionais.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btCadAdicionais.setText("Adicionais");
        btCadAdicionais.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCadAdicionais.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCadAdicionais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadAdicionaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btConsPed, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
            .addComponent(btCadAdicionais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btConsPed)
                .addGap(93, 93, 93)
                .addComponent(btCadAdicionais)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        opSair.setText("Sair");
        opSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opSairMouseClicked(evt);
            }
        });
        jMenuBar1.add(opSair);

        opTrocarUsuario.setText("Trocar Usuário");
        opTrocarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opTrocarUsuarioMouseClicked(evt);
            }
        });
        jMenuBar1.add(opTrocarUsuario);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(473, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadUsuariosActionPerformed
        CrudUsuario tela = new CrudUsuario();
        tela.setVisible(true);
    }//GEN-LAST:event_btCadUsuariosActionPerformed

    private void btCadComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadComandaActionPerformed
        CrudComanda tela = new CrudComanda();
        tela.setVisible(true);
    }//GEN-LAST:event_btCadComandaActionPerformed

    private void btCadProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadProdutosActionPerformed
        CrudProdutos tela = new CrudProdutos();
        tela.setVisible(true);
    }//GEN-LAST:event_btCadProdutosActionPerformed

    private void btCadAdicionaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadAdicionaisActionPerformed
        CrudAdicional tela = new CrudAdicional();
        tela.setVisible(true);
    }//GEN-LAST:event_btCadAdicionaisActionPerformed

    private void btRealizarPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRealizarPedActionPerformed
        RealizarPedido tela = new RealizarPedido(0);
        tela.setVisible(true);
    }//GEN-LAST:event_btRealizarPedActionPerformed

    private void btRealizarPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRealizarPagActionPerformed
        RealizarPagamento tela = new RealizarPagamento();
        tela.setVisible(true);
    }//GEN-LAST:event_btRealizarPagActionPerformed

    private void btConsPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsPedActionPerformed
        ConsultaPedido tela = new ConsultaPedido();
        tela.setVisible(true);
    }//GEN-LAST:event_btConsPedActionPerformed

    private void opTrocarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opTrocarUsuarioMouseClicked
        dispose();
        TelaLogar tela = new TelaLogar();
        tela.setVisible(true);
    }//GEN-LAST:event_opTrocarUsuarioMouseClicked

    private void opSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opSairMouseClicked
        dispose();
    }//GEN-LAST:event_opSairMouseClicked

    private void btConsPagtosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsPagtosActionPerformed
        ConsultaLucro tela = new ConsultaLucro();
        tela.setVisible(true);
    }//GEN-LAST:event_btConsPagtosActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Principal().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadAdicionais;
    private javax.swing.JButton btCadComanda;
    private javax.swing.JButton btCadProdutos;
    private javax.swing.JButton btCadUsuarios;
    private javax.swing.JButton btConsPagtos;
    private javax.swing.JButton btConsPed;
    private javax.swing.JButton btRealizarPag;
    private javax.swing.JButton btRealizarPed;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JMenu opSair;
    private javax.swing.JMenu opTrocarUsuario;
    // End of variables declaration//GEN-END:variables
}