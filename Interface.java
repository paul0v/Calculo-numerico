/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * @author batat
 */
public class Interface extends javax.swing.JFrame {

    int i = 0;
    int j = 0;
    int maxIt = 0;
    double erro = 0;
    int columStep = 0;
    int lineStep = 0;
    boolean isIsolado = false;

    int chuteStep = 0;

    boolean posivel = true;
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToggleButton jToggleButton1;

    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();

        Sistema sist = new Sistema();
        Sistema sist2 = new Sistema();
        GaussJacobi gj = new GaussJacobi(sist);
        GaussSeidel gs = new GaussSeidel(sist);
        Escalonamento esc = new Escalonamento();

        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i = (int) jSpinner2.getValue();
                j = (int) jSpinner1.getValue();
                maxIt = (int) jSpinner3.getValue();
                erro = Double.parseDouble(jTextField1.getText());
                sist.criarSist(i, j);
                sist2.criarSist(i, j + 1);
                System.out.println(sist2.getSist().size());
                System.out.println(sist2.getSist().get(0).getEq().size());
                boolean isLocked = jToggleButton1.isSelected();
                jSpinner1.setEnabled(!isLocked);
                jSpinner2.setEnabled(!isLocked);
                jSpinner3.setEnabled(!isLocked);
                jTextField1.setEnabled(!isLocked);
                jTextField3.setText("[" + lineStep + "][" + columStep + "]");
                columStep = 0;
                lineStep = 0;
                jButton2.setEnabled(true);

            }
        });


        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("" + lineStep + " " + columStep);
                if (lineStep < i) {
                    if (columStep < j) {
                        String textFieldValue = jTextField4.getText();
                        if (!textFieldValue.isEmpty()) { // Check if the text field is not empty
                            sist.preencheSist(lineStep, columStep,
                                    Double.parseDouble(textFieldValue));
                            sist2.preencheSist(lineStep, columStep,
                                    Double.parseDouble(textFieldValue));
                            columStep++;
                            sist.imprime();
                            jTextField3.setText("[" + lineStep + "][" + columStep + "]");
                            jTextField4.setText("");
                        } else {
                            jTextField3.setText("insira um numero");
                        }
                    } else {
                        String textFieldValue = jTextField4.getText();
                        sist.setResposta(lineStep, Double.parseDouble(textFieldValue));
                        sist2.preencheSist(lineStep, columStep, Double.parseDouble(textFieldValue));
                        sist.imprime();
                        lineStep++;
                        columStep = 0;
                        jTextField3.setText("[" + lineStep + "][" + columStep + "]");
                        if(lineStep == i){
                            jTextField3.setText("chutes");
                        }
                        jTextField4.setText("");

                    }
                } else if (chuteStep < i) {
                    String textFieldValue = jTextField4.getText();
                    if (!textFieldValue.isEmpty()) { // Check if the text field is not empty
                        gj.preencheChute(Double.parseDouble(textFieldValue));
                        gs.preencheChute(Double.parseDouble(textFieldValue));
                        chuteStep++;
                        sist.imprime();
                        jTextField3.setText("chute :[" + chuteStep + "]");
                    } else {
                        jTextField3.setText("insira um numero");
                    }
                } else {
                    jButton1.setEnabled(true);
                    jButton2.setEnabled(false);
                    jTextField3.setText("Finalizado");
                    posivel = esc.escalonarSistema(sist2);
                    System.out.println("Sistema escalonado");
                    sist2.imprime();
                }

            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!isIsolado) {
                    sist.isolaSist();
                    isIsolado = true;
                }
                jTextArea2.setText("Gaus Jacobi : \n");
                jTextArea2.append(gj.resolvePorErro(erro));
                jTextArea2.append("\n");
                jTextArea2.append("Gaus Seidel : \n");
                jTextArea2.append(gs.resolvePorErro(erro));
                jTextArea2.append("\nMetodo Direto : \n");
                if (posivel) {
                    jTextArea2.append(esc.resolverSistema(sist2));
                } else {
                    jTextArea2.append("Sistema Impossivel");
                }
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!isIsolado) {
                    sist.isolaSist();
                    isIsolado = true;
                }
                if (maxIt == 0) {
                    jTextArea2.setText("Gaus Jacobi : \n");
                    jTextArea2.append(gj.resolvePorErro(erro));
                    jTextArea2.append("\n");
                    jTextArea2.append("Gaus Seidel : \n");
                    jTextArea2.append(gs.resolvePorErro(erro));
                    jTextArea2.append("\nMetodo Direto : \n");
                    if (posivel) {
                        jTextArea2.append(esc.resolverSistema(sist2));
                    } else {
                        jTextArea2.append("Sistema Impossivel");
                    }
                } else {
                    jTextArea2.setText("Gaus Jacobi : \n");
                    jTextArea2.append(gj.resolvePorIteracao(maxIt));
                    jTextArea2.append("\n");
                    jTextArea2.append("Gaus Seidel : \n");
                    jTextArea2.append(gs.resolvePorIteracao(maxIt));
                    jTextArea2.append("\nMetodo Direto : \n");
                    if (posivel) {
                        jTextArea2.append(esc.resolverSistema(sist2));
                    } else {
                        jTextArea2.append("Sistema Impossivel");
                    }
                }
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSpinner3 = new javax.swing.JSpinner();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 500));
        setPreferredSize(new java.awt.Dimension(400, 500));

        jSpinner1.setMinimumSize(new java.awt.Dimension(128, 22));
        jSpinner1.setPreferredSize(new java.awt.Dimension(82, 22));

        jSpinner2.setPreferredSize(new java.awt.Dimension(82, 22));

        jToggleButton1.setText("Definir");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Resultado Aqui !!!");
        jTextArea2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Por Erro");

        jLabel2.setText("i :");

        jLabel3.setText("j :");

        jLabel4.setText("Erro :");

        jLabel5.setText("Maximo de iterações :");

        jTextField1.setText("0.00001");

        jSpinner3.setPreferredSize(new java.awt.Dimension(82, 22));

        jTextField3.setMinimumSize(new java.awt.Dimension(65, 22));

        jButton2.setText("Adicionar");

        jLabel7.setText("Proximo :");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Defina os parametros :");

        jButton3.setText("Por Iteração");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel10)
                                                                        .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                305,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jLabel9))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel3,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        14,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jSpinner1,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jSpinner2,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel4)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextField1,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        79,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jToggleButton1)
                                                                                        .addComponent(jLabel5))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jSpinner3,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        135,
                                                                        Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButton2)
                                                                        .addComponent(jLabel7,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                        .addComponent(jTextField3,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                84,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(jTextField4))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                layout
                                                        .createSequentialGroup()
                                                        .addGap(15, 15, 15)
                                                        .addComponent(jButton1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                83,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButton3)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel9)
                                                .addGap(294, 294, 294))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jSpinner1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jSpinner2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jTextField1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jSpinner3,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jToggleButton1)
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField4,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton2))
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                22,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField3,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(33, 33, 33)
                                                .addGroup(layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton1)
                                                        .addComponent(jButton3))
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        147,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(28,
                                                        Short.MAX_VALUE)))));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>
    // End of variables declaration
}
