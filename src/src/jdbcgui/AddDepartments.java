/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jdbcgui;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static jdbcgui.DateValidator.isValidDateFormat;

/**
 *
 * @author Ahmer
 */


public class AddDepartments extends javax.swing.JFrame {

    /**
     * Creates new form AddDepartments
     */
    
    myDBCon dbCon;
    ResultSet rs;
    
    public AddDepartments() {
        initComponents();
        this.setLocationRelativeTo(null); // center form in screen 
        // set all error labels to invisible
        Dnumber_Error.setVisible(false);
        Dname_Error.setVisible(false);
        MgrStartDate_Error.setVisible(false);
        MgrID_Error.setVisible(false);
        
        try {
            // populate valid dno
            dbCon = new myDBCon();
            // get and populate valid department numbers 
            rs = dbCon.executeStatement("SELECT EmpID FROM Proj_Employees ORDER BY EmpID ASC"); // TODO -- CONFIRM W/ DR. WISSAM -- same here
            while (rs.next()) {
                cmbMgrID.addItem(rs.getString("EmpID"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
    
    // verify valid entry of integer values
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    // verify valid entry of double values
    public boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    void clearErrorLabels() { // clear all labels used to display error messages 
        Dnumber_Error.setVisible(false);
        Dname_Error.setVisible(false);
        MgrStartDate_Error.setVisible(false);
        MgrID_Error.setVisible(false);
    }
    
    // validate all user entry before sending new employee details to DB
    boolean isValidData() {
        clearErrorLabels();
        boolean result = true; // assume all details are true, set to false if otherwise 
        // you need to check format deails and make sure they are consistent with DB 
        if (Dnumber_Field.getText().trim().isEmpty() || !isInteger(Dnumber_Field.getText().trim()) || Dnumber_Field.getText().trim().length() > 38) {
            if (Dnumber_Field.getText().trim().isEmpty()) {
                Dnumber_Error.setText("Invalid. Cannot be empty.");
            } else if (!isInteger(Dnumber_Field.getText().trim())) {
                Dnumber_Error.setText("Invalid. Must be integer.");
            } else if (Dnumber_Field.getText().trim().length() > 38) {
                Dnumber_Error.setText("Invalid. Must be < 38 chars.");
            }
            Dnumber_Error.setVisible(true);
            result = false;
        }

        if (Dname_Field.getText().trim().isEmpty() || (Dname_Field.getText().trim().length() > 20)) {
            if (Dname_Field.getText().trim().isEmpty()) {
                Dname_Error.setText("Invalid. Cannot be empty.");
            } else if ((Dname_Field.getText().trim().length() > 20)) {
                Dname_Error.setText("Invalid. Must be < 20 chars.");
            }
            Dname_Error.setVisible(true);
            result = false;
        }
                
        if (MgrStartDate_Field.getText().trim().isEmpty() || !isValidDateFormat(MgrStartDate_Field.getText())) {
            if (MgrStartDate_Field.getText().trim().isEmpty()){
                MgrStartDate_Error.setText("Invalid. Cannot be empty.");
                MgrStartDate_Error.setVisible(true);
            } else if (!isValidDateFormat(MgrStartDate_Field.getText())){
                            MgrStartDate_Error.setText("Invalid. Date must be DD-MMM-YYYY.");
                MgrStartDate_Error.setVisible(true);
                
            }
            result = false;
        }

        return result;
    }
        
    void clearInputBoxes() { // clear for every new entry 
        
        Dnumber_Field.setText("");
        MgrStartDate_Field.setText("");
        Dname_Field.setText("");
        cmbMgrID.setSelectedIndex(0);
        
    }
    
    
    private boolean isDuplicate(int dnumber) throws SQLException {
        boolean isduplicate = false;
        String stmtSQL = "SELECT DNUMBER FROM PROJ_DEPARTMENTS WHERE DNUMBER = " + dnumber;
        rs = dbCon.executeStatement(stmtSQL);
        // isBeforeFirst() returns false if there are no data in the resultset
        isduplicate = rs.isBeforeFirst();

        return isduplicate;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        Dnumber_Label = new javax.swing.JLabel();
        Dname_Label = new javax.swing.JLabel();
        MgrID_Label = new javax.swing.JLabel();
        MgrStartDate_Label = new javax.swing.JLabel();
        Dname_Field = new javax.swing.JTextField();
        MgrStartDate_Field = new javax.swing.JTextField();
        cmbMgrID = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        Dnumber_Error = new javax.swing.JLabel();
        Dname_Error = new javax.swing.JLabel();
        MgrID_Error = new javax.swing.JLabel();
        MgrStartDate_Error = new javax.swing.JLabel();
        Dnumber_Field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Title.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        Title.setText("Add New Department");

        Dnumber_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Dnumber_Label.setText("DNUMBER:");

        Dname_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Dname_Label.setText("DNAME:");

        MgrID_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        MgrID_Label.setText("MGRID:");

        MgrStartDate_Label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        MgrStartDate_Label.setText("MGR START DATE:");

        Dname_Field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Dname_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dname_FieldActionPerformed(evt);
            }
        });

        MgrStartDate_Field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        MgrStartDate_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MgrStartDate_FieldActionPerformed(evt);
            }
        });

        cmbMgrID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        addButton.setFont(new java.awt.Font("Forte", 0, 24)); // NOI18N
        addButton.setText("Add New");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        Dnumber_Error.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        Dnumber_Error.setForeground(new java.awt.Color(255, 0, 0));
        Dnumber_Error.setText("error label");

        Dname_Error.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        Dname_Error.setForeground(new java.awt.Color(255, 0, 0));
        Dname_Error.setText("error label");

        MgrID_Error.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        MgrID_Error.setForeground(new java.awt.Color(255, 0, 0));
        MgrID_Error.setText("error label");

        MgrStartDate_Error.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        MgrStartDate_Error.setForeground(new java.awt.Color(255, 0, 0));
        MgrStartDate_Error.setText("error label");

        Dnumber_Field.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Dnumber_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dnumber_FieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(Title))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Dnumber_Label)
                            .addComponent(Dname_Label)
                            .addComponent(MgrID_Label)
                            .addComponent(MgrStartDate_Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MgrStartDate_Field)
                                    .addComponent(Dname_Field)
                                    .addComponent(cmbMgrID, 0, 171, Short.MAX_VALUE)
                                    .addComponent(Dnumber_Field))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Dnumber_Error, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                    .addComponent(Dname_Error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(MgrID_Error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(MgrStartDate_Error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Title)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Dnumber_Label)
                        .addComponent(Dnumber_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Dnumber_Error))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Dname_Label)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Dname_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Dname_Error)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MgrID_Label)
                    .addComponent(cmbMgrID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MgrID_Error))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MgrStartDate_Label)
                    .addComponent(MgrStartDate_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MgrStartDate_Error))
                .addGap(18, 18, 18)
                .addComponent(addButton)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Dname_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dname_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dname_FieldActionPerformed

    private void MgrStartDate_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MgrStartDate_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MgrStartDate_FieldActionPerformed

    
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this department?", "Confirmation", JOptionPane.YES_NO_OPTION);

    if (dialogResult == JOptionPane.YES_OPTION) {
        try {
            /*
            * you need also to verify that the empno is unique and not duplicate
            *
            */
            if (isValidData() && !isDuplicate(Integer.parseInt(Dnumber_Field.getText().trim()))) {
                // if new employee details are valid, then add new employee to DB

                // TODO: UPDATE CODE TO WORK FOR SQL IMPLEMENTATION
                String prepSQL = "INSERT INTO PROJ_DEPARTMENTS (Dnumber, Dname, MgrID, MgrStartDate) VALUES ("
                + Dnumber_Field.getText().trim() + ", "
                + "'" + Dname_Field.getText().toUpperCase() + "', "
                + cmbMgrID.getSelectedItem().toString() + ", "
                + "'" + MgrStartDate_Field.getText().trim() + "')";

                System.out.println(prepSQL);
                int result = dbCon.executePrepared(prepSQL);
                if (result > 0) {

                    javax.swing.JLabel label = new javax.swing.JLabel("New department added successfully.");
                    label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, label, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    clearInputBoxes();
                } else {

                }

                rs.close();
            } else {
                
                if (!Dnumber_Field.getText().trim().isEmpty() && isInteger(Dnumber_Field.getText().trim())) {
                    if (!isDuplicate(Integer.parseInt(Dnumber_Field.getText().trim()))) {
                        javax.swing.JLabel label = new javax.swing.JLabel("Please fix validation errors...");
                        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        javax.swing.JLabel label = new javax.swing.JLabel("Dnumber Already exists. Use a different Dnumber.");
                        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        // check validation errors
                    }
                
                }

            }
        } catch (SQLException e) {
            e.printStackTrace(); // This will print the stack trace to the console
            JOptionPane.showMessageDialog(null, "Error adding new department.");
        }
    }
    }//GEN-LAST:event_addButtonActionPerformed

    private void Dnumber_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dnumber_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Dnumber_FieldActionPerformed

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
            java.util.logging.Logger.getLogger(AddDepartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDepartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDepartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDepartments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDepartments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dname_Error;
    private javax.swing.JTextField Dname_Field;
    private javax.swing.JLabel Dname_Label;
    private javax.swing.JLabel Dnumber_Error;
    private javax.swing.JTextField Dnumber_Field;
    private javax.swing.JLabel Dnumber_Label;
    private javax.swing.JLabel MgrID_Error;
    private javax.swing.JLabel MgrID_Label;
    private javax.swing.JLabel MgrStartDate_Error;
    private javax.swing.JTextField MgrStartDate_Field;
    private javax.swing.JLabel MgrStartDate_Label;
    private javax.swing.JLabel Title;
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> cmbMgrID;
    // End of variables declaration//GEN-END:variables
}
