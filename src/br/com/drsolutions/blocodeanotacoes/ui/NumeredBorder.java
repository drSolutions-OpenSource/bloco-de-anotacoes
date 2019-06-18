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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.border.AbstractBorder;

/**
 * Incluir o número de cada linha em uma instância de JTextArea na interface, 
 * separando a numeração do conteúdo, através de uma linha na vertical.
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.1
 */
public class NumeredBorder extends AbstractBorder {
	
	private static final long serialVersionUID = -4087495328829063071L;

	private int lineHeight = 17;
    private final int characterHeight = 14;
    private final int characterWidth = 10;
    private final Color myColor;
    private JViewport viewport;

    /**
     * Inicializar a instância da classe definindo a cor da numeração em cinza.
     */
    public NumeredBorder() {
        this.myColor = new Color(164, 164, 164);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, 
                            int width, int height) {
        if (this.viewport == null) {
            searchViewport(c);
        }
        
        /* Definindo o tamanho da fonte */
        g.setFont(g.getFont().deriveFont(13f));
        //g.setFont(new Font("Serif", Font.PLAIN, 13));

        FontMetrics m = g.getFontMetrics(g.getFont()); // g is your current Graphics object
        double totalSize = g.getFont().getSize() * (m.getAscent() + m.getDescent()) / m.getAscent();
        
        String OS = System.getProperty("os.name").toLowerCase();
        
        if (OS.contains("win"))
            lineHeight = (int)totalSize + 3;
        else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"))
            lineHeight = (int)totalSize;
        else
            lineHeight = (int)totalSize;
        
        Point point;
//        Dimension d = null;
        if (this.viewport != null) {
            point = this.viewport.getViewPosition();			
        } else {
            point = new Point();
        }
        
        Color oldColor = g.getColor();
        g.setColor(this.myColor);
        
        double r = (double) height / (double) this.lineHeight;
        int rows = (int) (r + 0.5);
        
        String str = String.valueOf(rows);
        
        int maxLenght = str.length();
        
        int py;
        int i = 0;
        if (point.y > 0) {
            i = point.y / this.lineHeight;
        }
//        if (d != null) {
//            r = (double) d.height / (double) this.lineHeight;
//            rows = (int) (r + 0.5);
//            rows += i + 1;
//        }
        
        int lenght;
        int px;
        for( ; i < rows; i++) {			
            str = String.valueOf(i + 1);
            lenght = str.length();
            lenght = maxLenght - lenght;
            py = this.lineHeight * i + 15;
            if ((i+1)>=10000)
                px = this.characterWidth * lenght + 11;
            else if ((i+1)>=1000)
                px = this.characterWidth * lenght + 9;
            else if ((i+1)>=100)
                px = this.characterWidth * lenght + 7;
            else if ((i+1)>=10)
                px = this.characterWidth * lenght + 5;
            else
                px = this.characterWidth * lenght + 3;
            g.drawString(str, px, py);
        }
        
        int left = this.calculateLeft(height);
        
        g.drawLine(left, 0, left, height);
        g.setColor(oldColor);
    }
    
    @Override
    public Insets getBorderInsets(Component c) {
        int left = this.calculateLeft(c.getHeight()) + 10;
        return new Insets(1, left, 1, 1);
    }
    
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {			
        insets.top = 2;
        insets.left = this.calculateLeft(c.getHeight()) + 6;
        insets.bottom = 1;
        insets.right = 1;
        return insets;
    }
    
    protected int calculateLeft(int height) {
        double r = (double) height / (double) this.lineHeight;
        int rows = (int) (r + 0.5);
        String str = String.valueOf(rows);
        int lenght = str.length();
        return this.characterHeight * lenght;
    }
    
    protected void searchViewport(Component c) {
        Container parent = c.getParent();
        if (parent instanceof JViewport) {
            this.viewport = (JViewport) parent;
        }
    }
    
    /**
     * Criar a numeração de linhas na JTextArea passada no parâmento do método.
     * 
     * @param area sendo a JTextArea que receberá a numeração
     * @return sendo um JPanel com JScrollPane, que deve ser passado para o  
     *         método setContentPane() do JFrame em que a JTextArea foi criada
     */
    public static JPanel getPanel(JTextArea area) {
        JPanel panel = new JPanel(new BorderLayout());

        //JTextArea area = new JTextArea();
        area.setBorder(new NumeredBorder());
        
        /* Ajustar a fonte */
        area.setFont(new Font("Serif", Font.PLAIN, 13));
        
        /* Desabilitando o line wrap */
        area.setLineWrap(false);
        area.setWrapStyleWord(true);
        
        JScrollPane scroll = new JScrollPane(area);

        panel.add(scroll);                
        return panel;
    }
}