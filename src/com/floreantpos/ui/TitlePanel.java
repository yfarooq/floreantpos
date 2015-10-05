/*
 * TitlePanel.java
 *
 * Created on February 27, 2006, 10:58 PM
 */

package com.floreantpos.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

/**
 *
 * @author  MShahriar
 */
public class TitlePanel extends com.floreantpos.swing.TransparentPanel {
    
    /** Creates new form TitlePanel */
    public TitlePanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jSeparator1 = new javax.swing.JSeparator();

        lblTitle.setFont(getTitleFont());
        lblTitle.setForeground(getTitleColor());
        lblTitle.setText(com.floreantpos.POSConstants.TITLE);

       setLayout(new BorderLayout(5, 5));
       add(lblTitle);
       add(jSeparator1, BorderLayout.SOUTH);
       
       setPreferredSize(new Dimension(300, 60));
    }// </editor-fold>//GEN-END:initComponents
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	Graphics2D g2 = (Graphics2D) g;
    	int x = 0, y = 0;
    	float width = getWidth();
    	float height = getHeight();
    	
    	Color color1 = Color.WHITE;
    	Color color2 = getBackground();
    	g2.setPaint(new GradientPaint(x,y,color1, width, height,color2));
    	g2.fillRect(x, y, (int) width, (int) height);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JSeparator jSeparator1;
    protected javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
    
    private Font getTitleFont(){
        Font f = lblTitle.getFont();
        f = f.deriveFont(Font.BOLD, 14);
        return f;
    }
    
    private Color getTitleColor(){
        return UIManager.getColor("TitledBorder.titleColor"); //$NON-NLS-1$
    }

	public String getTitle() {
		return lblTitle.getText();
	}

	public void setTitle(String title) {
		lblTitle.setText(title);
	}
}
