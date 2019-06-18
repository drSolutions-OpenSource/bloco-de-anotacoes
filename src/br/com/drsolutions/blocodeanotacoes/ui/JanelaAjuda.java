/*
 * Copyright 2018 [Drsolutions Tecnologia em Informática Ltda.-ME].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.drsolutions.blocodeanotacoes.ui;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;

/**
 * Jabela de Conteúdo da Ajuda ao usuário do Bloco de Anotações.
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.1
 */
public class JanelaAjuda extends javax.swing.JDialog {

	private static final long serialVersionUID = -8304710080290772989L;

	/**
     * Lê um arquivo HTML de ajuda do sistema operacional, e depois, coloca
     * este conteúdo na tela para a leitura do usuário final.
     * 
     * @param indiceArquivo 
     */
    private void lerArquivo(int indiceArquivo) {
         /** Arquivos com as mensagens do usuário e com as respostas */
        String arquivoCaminho = "/br/com/drsolutions/blocodeanotacoes/html/";
        String arquivoMensagens;
        
        if (indiceArquivo == 0 )
            arquivoMensagens = arquivoCaminho + "AjudaUtilizacao.html";
        else
            arquivoMensagens = arquivoCaminho + "AjudaDesenvolvedores.html";
        
        String textoHtml = "";
        /** Lendo o conteúdo do arquivo HTML */
        try {
            URL urlToDictionary = this.getClass().getResource(arquivoMensagens);
            InputStream is = urlToDictionary.openStream();
            InputStreamReader ir = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(ir);
            
            String s;
            try {
                s = br.readLine();
                
                while (s != null) {
                    /* Adicionando linha */
                    textoHtml += s;
                    s = br.readLine();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao ler linhas do "
                        + "arquivo:\n" + arquivoMensagens,
                        "Erro!", JOptionPane.ERROR_MESSAGE); 
                System.exit(0);
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao abrir o "
                        + "arquivo:\n" + arquivoMensagens,
                        "Erro!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Falha:\n"
                    + ex,
                    "Erro!", JOptionPane.ERROR_MESSAGE);             
            System.exit(0);
        }
        
        txtEditorAjuda.setText(textoHtml);
        txtEditorAjuda.setCaretPosition(0);
    }
    
    /**
     * Cria a janela Coteúdo da Ajuda, onde o usuário final pode selecionar 
     * qual tópico de ajuda deve ser exibido para a leitura.
     * 
     * @param parent
     * @param modal 
     */
    public JanelaAjuda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        /* Centralizar a janela */
        setLocationRelativeTo(null);

        /* Lendo o arquivo AjudaUtilizacao.html */
        lerArquivo(0);
        
        /* Colocando o título da janela */
        setTitle("Ajuda - Bloco de Anotações v1.1");
       
        
        listaItens.addListSelectionListener((ListSelectionEvent arg0) -> {
            if (!arg0.getValueIsAdjusting()) {
                lerArquivo(listaItens.getSelectedIndex());
            }
        });
        
        /* Deixar o link do HTML clicável */
        txtEditorAjuda.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(e.getURL().toURI());
                    } catch (IOException | URISyntaxException ex) {
                        //ex.printStackTrace();
                    }
                }
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaItens = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEditorAjuda = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajuda");
        setResizable(false);

        listaItens.setModel(new javax.swing.AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			
			String[] strings = { "Utilização", "Desenvolvedor" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaItens.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaItens.setToolTipText("Selecione a ítem desejado");
        listaItens.setFocusCycleRoot(true);
        listaItens.setFocusable(false);
        listaItens.setSelectedIndex(0);
        jScrollPane1.setViewportView(listaItens);

        txtEditorAjuda.setEditable(false);
        txtEditorAjuda.setContentType("text/html"); // NOI18N
        txtEditorAjuda.setFocusable(false);
        txtEditorAjuda.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane3.setViewportView(txtEditorAjuda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Função principal da janela Conteúdo da Ajuda.
     * 
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
        } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaAjuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaAjuda dialog = new JanelaAjuda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listaItens;
    private javax.swing.JEditorPane txtEditorAjuda;
    // End of variables declaration//GEN-END:variables

}
