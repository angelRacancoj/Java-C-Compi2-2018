/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import backEnd.analisisLexSintax.Lexer;
import backEnd.analisisLexSintax.sintactico;
import backEnd.files.ManejadorArchivo;
import backEnd.semantic.semanticManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author orlan
 */
public class codeEditor extends javax.swing.JPanel {

    private UndoManager undoM;
    String path;
    ManejadorArchivo filesManager;
    TextLineNumber textLine;
    semanticManager semanticM;

    Lexer lex;
    sintactico sintact;

    /**
     * Creates new form codeEditor
     *
     * @param path
     */
    public codeEditor(String path) {
        initComponents();
        semanticM = new semanticManager();

        this.lex = new Lexer(new StringReader(""), semanticM);
        this.sintact = new sintactico(lex, semanticM);

        filesManager = new ManejadorArchivo();
        this.path = path;
        textLine = new TextLineNumber(codeTextArea);
        jScrollPane1.setRowHeaderView(textLine);
        undoM = new UndoManager();
        Document doc = codeTextArea.getDocument();

        doc.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                System.out.println("Add edit");
                undoM.addEdit(e.getEdit());
            }
        });

        codeTextArea.addCaretListener(new CaretListener() {

            public void caretUpdate(CaretEvent e) {

                JTextArea editArea = (JTextArea) e.getSource();

                int linenum = 1;
                int columnnum = 1;

                try {

                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);

                    columnnum = caretpos - editArea.getLineStartOffset(linenum);

                    linenum += 1;

                } catch (BadLocationException ex) {

                }

                updateStatus(linenum, columnnum);
            }
        });

        InputMap inMap = codeTextArea.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actMap = codeTextArea.getActionMap();

        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");

        actMap.put("Undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (undoM.canUndo()) {
                        undoM.undo();
                    }
                } catch (CannotUndoException ex) {
                    ex.printStackTrace();
                }

            }
        });

        actMap.put("Redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (undoM.canRedo()) {
                        undoM.redo();
                    }
                } catch (CannotUndoException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private void updateStatus(int linenumber, int columnnumber) {
        lineColumn.setText("Line: " + linenumber + " Column: " + columnnumber);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        codeTextArea = new javax.swing.JTextArea();
        guardarButton = new javax.swing.JButton();
        lineColumn = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        errorsTextArea = new javax.swing.JTextArea();
        testButton = new javax.swing.JButton();

        codeTextArea.setColumns(20);
        codeTextArea.setRows(5);
        jScrollPane1.setViewportView(codeTextArea);

        guardarButton.setText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        lineColumn.setText("Errores");

        errorsTextArea.setColumns(20);
        errorsTextArea.setRows(5);
        jScrollPane2.setViewportView(errorsTextArea);

        testButton.setText("Analizar");
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lineColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 877, Short.MAX_VALUE)
                        .addComponent(testButton)
                        .addGap(18, 18, 18)
                        .addComponent(guardarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarButton)
                    .addComponent(lineColumn)
                    .addComponent(testButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        saveProgress();
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
        semanticM.resetAll();
        errorsTextArea.setText("");
        saveProgress();
        try {
            lex.yyreset(new StringReader(codeTextArea.getText()));
            this.sintact.parse();
            errorsTextArea.setText(semanticM.getVars());
            semanticM.create3DirCodeDoc();
            JOptionPane.showMessageDialog(this, "No se han hallado errores\nCodigo 3 Direcciones Generado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
            errorsTextArea.setText(semanticM.Errors());
            JOptionPane.showMessageDialog(this, "Se han hallado errores, realizar los cambios necesarios en el codigo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_testButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea codeTextArea;
    private javax.swing.JTextArea errorsTextArea;
    private javax.swing.JButton guardarButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lineColumn;
    private javax.swing.JButton testButton;
    // End of variables declaration//GEN-END:variables

    public void setText(String textIn) {
        codeTextArea.setText(textIn);
    }

    public String returnText() {
        return codeTextArea.getText();
    }

    private void saveProgress() {
        try {
            if (!filesManager.lecturaArchivo(path).equals(codeTextArea.getText())) {
                filesManager.guardarArchivo(path, codeTextArea.getText());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
