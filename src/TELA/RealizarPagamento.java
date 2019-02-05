/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA;

import DAO.AdicionaisDAO;
import DAO.AdicionalPedidoDAO;
import DAO.ComandaDAO;
import DAO.PedidoDAO;
import CLASSE.Adicional;
import CLASSE.AdicionalPedido;
import CLASSE.Comanda;
import CLASSE.Pedido;
import REGRAS.RegrasPagamento;
import TELA.JTABLE.RealizarPedido.PedidoJtable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author maiara
 */
public class RealizarPagamento extends javax.swing.JFrame {

    /**
     * Creates new form cadastroProdutos
     */
    private NumberFormat f = NumberFormat.getCurrencyInstance();
    public List<Comanda> listaComandasSelecionadas;
    private List<Pedido> listaTodosPedidos;

    public RealizarPagamento() {
        initComponents();
        listaComandasSelecionadas = new ArrayList<Comanda>();
        listaTodosPedidos = new ArrayList<Pedido>();

        carregaDetalhesTela();
        carregarTabelaPedido(0);
        carregaComandas();

    }

    public void carregaDetalhesTela() {
        btCom.setVisible(false);
        btCom1.setVisible(false);
        btCom2.setVisible(false);
        btCom3.setVisible(false);
        btCom4.setVisible(false);
        btCom5.setVisible(false);
        btCom6.setVisible(false);
        btCom7.setVisible(false);
        btCom8.setVisible(false);
        btCom9.setVisible(false);
        btCom10.setVisible(false);
        btCom11.setVisible(false);
        btCom12.setVisible(false);
        btCom13.setVisible(false);
        btCom14.setVisible(false);
        btCom15.setVisible(false);
        btCom16.setVisible(false);
        btCom17.setVisible(false);
        btCom18.setVisible(false);
        btCom19.setVisible(false);
        btCom20.setVisible(false);

        btCarregarComanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/carr.png")));
        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limp.png")));
        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png")));
        btRealizarPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pag.png")));
        btAltPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alter.png")));
        btSelecAdicionais.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/more.png")));
        btCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
        btCom20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/mesa.png")));
       this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/pay.png")).getImage());    

        setDefaultCloseOperation(RealizarPedido.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                btSair.doClick();
            }
        });
        this.setExtendedState(MAXIMIZED_BOTH);
        edValor.setVisible(false);
    }

    private void carregaComandas() {
        try {
            List<Comanda> comanda = ComandaDAO.getInstance().findAll();

            for (Comanda com : comanda) {
                if (btCom.getText().equalsIgnoreCase("")) {
                    btCom.setVisible(true);
                    btCom.setText(String.valueOf(com.getId()));
                } else if (btCom1.getText().equalsIgnoreCase("")) {
                    btCom1.setVisible(true);
                    btCom1.setText(String.valueOf(com.getId()));
                } else if (btCom2.getText().equalsIgnoreCase("")) {
                    btCom2.setVisible(true);
                    btCom2.setText(String.valueOf(com.getId()));
                } else if (btCom3.getText().equalsIgnoreCase("")) {
                    btCom3.setVisible(true);
                    btCom3.setText(String.valueOf(com.getId()));
                } else if (btCom4.getText().equalsIgnoreCase("")) {
                    btCom4.setVisible(true);
                    btCom4.setText(String.valueOf(com.getId()));
                } else if (btCom5.getText().equalsIgnoreCase("")) {
                    btCom5.setVisible(true);
                    btCom5.setText(String.valueOf(com.getId()));
                } else if (btCom6.getText().equalsIgnoreCase("")) {
                    btCom6.setVisible(true);
                    btCom6.setText(String.valueOf(com.getId()));
                } else if (btCom7.getText().equalsIgnoreCase("")) {
                    btCom7.setVisible(true);
                    btCom7.setText(String.valueOf(com.getId()));
                } else if (btCom8.getText().equalsIgnoreCase("")) {
                    btCom8.setVisible(true);
                    btCom8.setText(String.valueOf(com.getId()));
                } else if (btCom9.getText().equalsIgnoreCase("")) {
                    btCom9.setVisible(true);
                    btCom9.setText(String.valueOf(com.getId()));
                } else if (btCom10.getText().equalsIgnoreCase("")) {
                    btCom10.setVisible(true);
                    btCom10.setText(String.valueOf(com.getId()));
                } else if (btCom11.getText().equalsIgnoreCase("")) {
                    btCom11.setVisible(true);
                    btCom11.setText(String.valueOf(com.getId()));
                } else if (btCom12.getText().equalsIgnoreCase("")) {
                    btCom12.setVisible(true);
                    btCom12.setText(String.valueOf(com.getId()));
                } else if (btCom13.getText().equalsIgnoreCase("")) {
                    btCom13.setVisible(true);
                    btCom13.setText(String.valueOf(com.getId()));
                } else if (btCom14.getText().equalsIgnoreCase("")) {
                    btCom14.setVisible(true);
                    btCom14.setText(String.valueOf(com.getId()));
                } else if (btCom15.getText().equalsIgnoreCase("")) {
                    btCom15.setVisible(true);
                    btCom15.setText(String.valueOf(com.getId()));
                } else if (btCom16.getText().equalsIgnoreCase("")) {
                    btCom16.setVisible(true);
                    btCom16.setText(String.valueOf(com.getId()));
                } else if (btCom17.getText().equalsIgnoreCase("")) {
                    btCom17.setVisible(true);
                    btCom17.setText(String.valueOf(com.getId()));
                } else if (btCom18.getText().equalsIgnoreCase("")) {
                    btCom18.setVisible(true);
                    btCom18.setText(String.valueOf(com.getId()));
                } else if (btCom19.getText().equalsIgnoreCase("")) {
                    btCom19.setVisible(true);
                    btCom19.setText(String.valueOf(com.getId()));
                } else if (btCom20.getText().equalsIgnoreCase("")) {
                    btCom20.setVisible(true);
                    btCom20.setText(String.valueOf(com.getId()));
                }
            }
        } catch (Exception ex) {

        }
    }

    public void carregarTabelaPedido(int idComanda) {
        try {
            List<Pedido> pedidosEncontrados = PedidoDAO.getInstance().findAllAtender(2, idComanda);

            for (int i = 0; i < pedidosEncontrados.size(); i++) {
                listaTodosPedidos.add(pedidosEncontrados.get(i));
            }
            PedidoJtable tm = new PedidoJtable(listaTodosPedidos, 0);
            tbPedidos.setModel(tm);
            edTotalPagto.setText(f.format(RegrasPagamento.getInstance().calcularTotalPagto(listaTodosPedidos, edDesconto.getText(), edAcrescimo.getText(), false)));
            edValor.setText(String.valueOf(RegrasPagamento.getInstance().calcularTotalPagto(listaTodosPedidos, edDesconto.getText(), edAcrescimo.getText(), true)));
        } catch (SQLException ex) {
            Logger.getLogger(CrudProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpResumoPagto = new javax.swing.JPanel();
        lbTotal = new javax.swing.JLabel();
        edTotalPagto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edDesconto = new javax.swing.JFormattedTextField();
        edAcrescimo = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        btLimpar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btAltPedido = new javax.swing.JButton();
        btRealizarPagamento = new javax.swing.JButton();
        edValor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jpPedido = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btCom = new javax.swing.JButton();
        btCom2 = new javax.swing.JButton();
        btCom1 = new javax.swing.JButton();
        btCom3 = new javax.swing.JButton();
        btCom4 = new javax.swing.JButton();
        btCom5 = new javax.swing.JButton();
        btCom6 = new javax.swing.JButton();
        btCom7 = new javax.swing.JButton();
        btCom8 = new javax.swing.JButton();
        btCom9 = new javax.swing.JButton();
        btCom10 = new javax.swing.JButton();
        btCom11 = new javax.swing.JButton();
        btCom12 = new javax.swing.JButton();
        btCom13 = new javax.swing.JButton();
        btCom14 = new javax.swing.JButton();
        btCom15 = new javax.swing.JButton();
        btCom16 = new javax.swing.JButton();
        btCom17 = new javax.swing.JButton();
        btCom18 = new javax.swing.JButton();
        btCom19 = new javax.swing.JButton();
        btCom20 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        edComandaPedido = new javax.swing.JTextField();
        btCarregarComanda = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        edAdicionaisSelecionados = new javax.swing.JTextArea();
        btSelecAdicionais = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpResumoPagto.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jpResumoPagto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        lbTotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotal.setText("TOTAL");

        edTotalPagto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        edTotalPagto.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Desconto em %");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Acréscimo em %");

        edDesconto.setToolTipText("");
        edDesconto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        edDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edDescontoFocusLost(evt);
            }
        });

        edAcrescimo.setToolTipText("");
        edAcrescimo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        edAcrescimo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edAcrescimoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jpResumoPagtoLayout = new javax.swing.GroupLayout(jpResumoPagto);
        jpResumoPagto.setLayout(jpResumoPagtoLayout);
        jpResumoPagtoLayout.setHorizontalGroup(
            jpResumoPagtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumoPagtoLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edTotalPagto))
        );
        jpResumoPagtoLayout.setVerticalGroup(
            jpResumoPagtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumoPagtoLayout.createSequentialGroup()
                .addGroup(jpResumoPagtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResumoPagtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTotal)
                        .addComponent(edTotalPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jpResumoPagtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(edDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btLimpar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
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

        btAltPedido.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btAltPedido.setText("Alterar Pedido");
        btAltPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAltPedidoActionPerformed(evt);
            }
        });

        btRealizarPagamento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btRealizarPagamento.setText("Realizar Pagamento");
        btRealizarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRealizarPagamentoActionPerformed(evt);
            }
        });

        edValor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        edValor.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(489, Short.MAX_VALUE)
                .addComponent(btAltPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRealizarPagamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSair)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(874, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSair)
                    .addComponent(btLimpar)
                    .addComponent(btAltPedido)
                    .addComponent(btRealizarPagamento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Realizar Pagamentos");
        jLabel4.setAlignmentX(0.5F);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel4.setName("lbGerenciaProj"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tbPedidos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tbPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPedidos.setName("tbConsultaProduto"); // NOI18N
        tbPedidos.setRowHeight(18);
        tbPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPedidosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbPedidos);

        javax.swing.GroupLayout jpPedidoLayout = new javax.swing.GroupLayout(jpPedido);
        jpPedido.setLayout(jpPedidoLayout);
        jpPedidoLayout.setHorizontalGroup(
            jpPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jpPedidoLayout.setVerticalGroup(
            jpPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153)), "Selecionar Comandas ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        btCom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btComActionPerformed(evt);
            }
        });

        btCom2.setToolTipText("");
        btCom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom2ActionPerformed(evt);
            }
        });

        btCom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom1ActionPerformed(evt);
            }
        });

        btCom3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btCom3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom3ActionPerformed(evt);
            }
        });

        btCom4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom4ActionPerformed(evt);
            }
        });

        btCom5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom5ActionPerformed(evt);
            }
        });

        btCom6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom6ActionPerformed(evt);
            }
        });

        btCom7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btCom7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom7ActionPerformed(evt);
            }
        });

        btCom8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom8ActionPerformed(evt);
            }
        });

        btCom9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom9ActionPerformed(evt);
            }
        });

        btCom10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom10ActionPerformed(evt);
            }
        });

        btCom11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom11ActionPerformed(evt);
            }
        });

        btCom12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom12ActionPerformed(evt);
            }
        });

        btCom13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom13ActionPerformed(evt);
            }
        });

        btCom14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom14ActionPerformed(evt);
            }
        });

        btCom15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom15ActionPerformed(evt);
            }
        });

        btCom16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom16ActionPerformed(evt);
            }
        });

        btCom17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom17ActionPerformed(evt);
            }
        });

        btCom18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom18ActionPerformed(evt);
            }
        });

        btCom19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btCom19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom19ActionPerformed(evt);
            }
        });

        btCom20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCom20ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Comanda");

        edComandaPedido.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        btCarregarComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarregarComandaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edComandaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCarregarComanda)
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btCom)
                            .addComponent(btCom8)
                            .addComponent(btCom12)
                            .addComponent(btCom16)
                            .addComponent(btCom4))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btCom5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btCom1)
                                    .addComponent(btCom9)
                                    .addComponent(btCom13)
                                    .addComponent(btCom17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btCom10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btCom11))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btCom2)
                                        .addGap(31, 31, 31)
                                        .addComponent(btCom3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btCom18)
                                        .addGap(31, 31, 31)
                                        .addComponent(btCom19))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btCom6)
                                        .addGap(31, 31, 31)
                                        .addComponent(btCom7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btCom14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btCom15)))
                                .addGap(157, 157, 157))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btCom20)
                        .addGap(0, 367, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btCom1)
                    .addComponent(btCom)
                    .addComponent(btCom2)
                    .addComponent(btCom3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCom6)
                    .addComponent(btCom5)
                    .addComponent(btCom4)
                    .addComponent(btCom7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btCom9)
                            .addComponent(btCom8)
                            .addComponent(btCom10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btCom12)
                            .addComponent(btCom13)
                            .addComponent(btCom14)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btCom11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCom15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btCom16)
                            .addComponent(btCom17)
                            .addComponent(btCom19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCom20))
                    .addComponent(btCom18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edComandaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(btCarregarComanda))
                .addGap(22, 22, 22))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionais selecionados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 16))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        edAdicionaisSelecionados.setColumns(20);
        edAdicionaisSelecionados.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        edAdicionaisSelecionados.setRows(5);
        edAdicionaisSelecionados.setEnabled(false);
        jScrollPane1.setViewportView(edAdicionaisSelecionados);

        btSelecAdicionais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecAdicionaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 477, Short.MAX_VALUE)
                    .addComponent(btSelecAdicionais)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(btSelecAdicionais)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(549, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpResumoPagto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpResumoPagto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 480, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRealizarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRealizarPagamentoActionPerformed
       String retorno = RegrasPagamento.getInstance().salvarPagamento(listaComandasSelecionadas, edAcrescimo.getText(), edDesconto.getText(), edValor.getText());
        JOptionPane.showMessageDialog(null, retorno);
    }//GEN-LAST:event_btRealizarPagamentoActionPerformed

    private void btAltPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAltPedidoActionPerformed

        try {
            int linha = tbPedidos.getSelectedRow();
            PedidoJtable tm;
            tm = new PedidoJtable(listaTodosPedidos, 0);

            Pedido ped = tm.get(linha);

            RealizarPedido pedido = new RealizarPedido(ped.getComandaId());
            pedido.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(RealizarPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAltPedidoActionPerformed

    private void limpa() throws SQLException {
        listaComandasSelecionadas.clear();
        listaTodosPedidos.clear();
        edTotalPagto.setEnabled(true);
        edTotalPagto.setText("0");
        edTotalPagto.setEnabled(false);
        edDesconto.setText("");
        edAcrescimo.setText("");

        PedidoJtable tm;
        tm = new PedidoJtable(listaTodosPedidos, 0);
        carregarTabelaPedido(0);

        edComandaPedido.setText("");
    }

    private void btCarregarComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarregarComandaActionPerformed
        carregarTabelaPedido(Integer.parseInt(edComandaPedido.getText()));
    }//GEN-LAST:event_btCarregarComandaActionPerformed

    private void btComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btComActionPerformed
        carregaComandaIndividual(btCom.getText());
    }//GEN-LAST:event_btComActionPerformed

    private void carregaComandaIndividual(String idComanda) {
        boolean jaInseriu = false;

        for (Comanda lista : listaComandasSelecionadas) {
            if (lista.getId() == Integer.parseInt(idComanda)) {
                jaInseriu = true;
            }
        }
        if (!jaInseriu) {
            carregarTabelaPedido(Integer.parseInt(idComanda));
            Comanda c = ComandaDAO.getInstance().getById(Integer.parseInt(idComanda));
            listaComandasSelecionadas.add(c);
        }
    }

    private void btCom4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom4ActionPerformed
        carregaComandaIndividual(btCom4.getText());
    }//GEN-LAST:event_btCom4ActionPerformed

    private void btCom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom2ActionPerformed
        carregaComandaIndividual(btCom2.getText());
    }//GEN-LAST:event_btCom2ActionPerformed

    private void btCom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom1ActionPerformed
        carregaComandaIndividual(btCom1.getText());
    }//GEN-LAST:event_btCom1ActionPerformed

    private void btCom3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom3ActionPerformed
        carregaComandaIndividual(btCom3.getText());
    }//GEN-LAST:event_btCom3ActionPerformed

    private void btCom5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom5ActionPerformed
        carregaComandaIndividual(btCom5.getText());
    }//GEN-LAST:event_btCom5ActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        try {
            limpa();
        } catch (SQLException ex) {
            Logger.getLogger(RealizarPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btLimparActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void tbPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPedidosMouseClicked
        try {
            carregaAdicionaisEmTela();
        } catch (SQLException ex) {
            Logger.getLogger(RealizarPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbPedidosMouseClicked

    private void edDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edDescontoFocusLost
        if ((!"".equalsIgnoreCase(edTotalPagto.getText())) && (!"".equalsIgnoreCase(edDesconto.getText()))) {
            edTotalPagto.setText(f.format(RegrasPagamento.getInstance().calcularTotalPagto(listaTodosPedidos, edDesconto.getText(), edAcrescimo.getText(), false)));
        }
    }//GEN-LAST:event_edDescontoFocusLost

    private void edAcrescimoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edAcrescimoFocusLost

        if ((!"".equalsIgnoreCase(edTotalPagto.getText())) && (!"".equalsIgnoreCase(edDesconto.getText()))) {

            edTotalPagto.setText(f.format(RegrasPagamento.getInstance().calcularTotalPagto(listaTodosPedidos, edDesconto.getText(), edAcrescimo.getText(), false)));

        }
    }//GEN-LAST:event_edAcrescimoFocusLost

    private void btCom6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom6ActionPerformed
        carregaComandaIndividual(btCom6.getText());
    }//GEN-LAST:event_btCom6ActionPerformed

    private void btCom7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom7ActionPerformed
        carregaComandaIndividual(btCom7.getText());
    }//GEN-LAST:event_btCom7ActionPerformed

    private void btCom8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom8ActionPerformed
        carregaComandaIndividual(btCom8.getText());
    }//GEN-LAST:event_btCom8ActionPerformed

    private void btCom9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom9ActionPerformed
        carregaComandaIndividual(btCom9.getText());
    }//GEN-LAST:event_btCom9ActionPerformed

    private void btCom10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom10ActionPerformed
        carregaComandaIndividual(btCom10.getText());
    }//GEN-LAST:event_btCom10ActionPerformed

    private void btCom11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom11ActionPerformed
        carregaComandaIndividual(btCom11.getText());
    }//GEN-LAST:event_btCom11ActionPerformed

    private void btCom12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom12ActionPerformed
        carregaComandaIndividual(btCom12.getText());
    }//GEN-LAST:event_btCom12ActionPerformed

    private void btCom13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom13ActionPerformed
        carregaComandaIndividual(btCom13.getText());
    }//GEN-LAST:event_btCom13ActionPerformed

    private void btCom14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom14ActionPerformed
        carregaComandaIndividual(btCom14.getText());
    }//GEN-LAST:event_btCom14ActionPerformed

    private void btCom15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom15ActionPerformed
        carregaComandaIndividual(btCom15.getText());
    }//GEN-LAST:event_btCom15ActionPerformed

    private void btCom16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom16ActionPerformed
        carregaComandaIndividual(btCom16.getText());
    }//GEN-LAST:event_btCom16ActionPerformed

    private void btCom17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom17ActionPerformed
        carregaComandaIndividual(btCom17.getText());
    }//GEN-LAST:event_btCom17ActionPerformed

    private void btCom18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom18ActionPerformed
        carregaComandaIndividual(btCom18.getText());
    }//GEN-LAST:event_btCom18ActionPerformed

    private void btCom19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom19ActionPerformed
        carregaComandaIndividual(btCom19.getText());
    }//GEN-LAST:event_btCom19ActionPerformed

    private void btCom20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCom20ActionPerformed
        carregaComandaIndividual(btCom20.getText());
    }//GEN-LAST:event_btCom20ActionPerformed

    private void btSelecAdicionaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecAdicionaisActionPerformed
        try {
            int linha = tbPedidos.getSelectedRow();
            PedidoJtable tm;
            tm = new PedidoJtable(listaTodosPedidos, 0);

            if (linha != -1) {
                Pedido pedido = tm.get(linha);
                ConsultaAdicionais tela = new ConsultaAdicionais(pedido.getId(), pedido.getProdutoId(), true);
                tela.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um produto para cadastrar adicionais!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(RealizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSelecAdicionaisActionPerformed

    private void carregaAdicionaisEmTela() throws SQLException {
        edAdicionaisSelecionados.setText("");
        int linha = tbPedidos.getSelectedRow();
        PedidoJtable tm;
        tm = new PedidoJtable(listaTodosPedidos, 0);

        Pedido pedido = tm.get(linha);
        edAdicionaisSelecionados.setText(RegrasPagamento.getInstance().carregaAdicionaisPedidos(pedido));
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
            java.util.logging.Logger.getLogger(RealizarPagamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RealizarPagamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RealizarPagamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RealizarPagamento.class
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
                new RealizarPagamento().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAltPedido;
    private javax.swing.JButton btCarregarComanda;
    private javax.swing.JButton btCom;
    private javax.swing.JButton btCom1;
    private javax.swing.JButton btCom10;
    private javax.swing.JButton btCom11;
    private javax.swing.JButton btCom12;
    private javax.swing.JButton btCom13;
    private javax.swing.JButton btCom14;
    private javax.swing.JButton btCom15;
    private javax.swing.JButton btCom16;
    private javax.swing.JButton btCom17;
    private javax.swing.JButton btCom18;
    private javax.swing.JButton btCom19;
    private javax.swing.JButton btCom2;
    private javax.swing.JButton btCom20;
    private javax.swing.JButton btCom3;
    private javax.swing.JButton btCom4;
    private javax.swing.JButton btCom5;
    private javax.swing.JButton btCom6;
    private javax.swing.JButton btCom7;
    private javax.swing.JButton btCom8;
    private javax.swing.JButton btCom9;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btRealizarPagamento;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSelecAdicionais;
    private javax.swing.JFormattedTextField edAcrescimo;
    private javax.swing.JTextArea edAdicionaisSelecionados;
    private javax.swing.JTextField edComandaPedido;
    private javax.swing.JFormattedTextField edDesconto;
    private javax.swing.JTextField edTotalPagto;
    private javax.swing.JTextField edValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpPedido;
    private javax.swing.JPanel jpResumoPagto;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tbPedidos;
    // End of variables declaration//GEN-END:variables
}
