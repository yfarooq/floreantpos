/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
/*
 * NotesDialog.java
 *
 * Created on September 8, 2006, 9:43 PM
 */

package com.floreantpos.ui.dialog;

import com.floreantpos.main.Application;

/**
 *
 * @author  MShahriar
 */
public class NotesDialog extends POSDialog {
    
    /** Creates new form NotesDialog */
    public NotesDialog() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        titlePanel = new com.floreantpos.ui.TitlePanel();
        transparentPanel1 = new com.floreantpos.swing.TransparentPanel();
        posButton1 = new com.floreantpos.swing.PosButton();
        posButton2 = new com.floreantpos.swing.PosButton();
        noteView = new com.floreantpos.ui.views.NoteView();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);

        posButton1.setText(com.floreantpos.POSConstants.FINISH);
        posButton1.setPreferredSize(new java.awt.Dimension(120, 50));
        posButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doFinish(evt);
            }
        });

        transparentPanel1.add(posButton1);

        posButton2.setText(com.floreantpos.POSConstants.CANCEL);
        posButton2.setPreferredSize(new java.awt.Dimension(120, 50));
        posButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doCancel(evt);
            }
        });

        transparentPanel1.add(posButton2);

        getContentPane().add(transparentPanel1, java.awt.BorderLayout.SOUTH);

        noteView.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        getContentPane().add(noteView, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doCancel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doCancel
    	setCanceled(true);
    	dispose();
    }//GEN-LAST:event_doCancel

    private void doFinish(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doFinish
    	setCanceled(false);
    	dispose();
    }//GEN-LAST:event_doFinish
    
    @Override
    public void setTitle(String title) {
    	super.setTitle(Application.getTitle());
    	
    	titlePanel.setTitle(title);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.floreantpos.ui.views.NoteView noteView;
    private com.floreantpos.swing.PosButton posButton1;
    private com.floreantpos.swing.PosButton posButton2;
    private com.floreantpos.ui.TitlePanel titlePanel;
    private com.floreantpos.swing.TransparentPanel transparentPanel1;
    // End of variables declaration//GEN-END:variables
    
	public String getNote() {
		return noteView.getNote();
	}
    
}
