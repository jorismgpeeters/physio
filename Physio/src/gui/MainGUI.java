/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import com.toedter.calendar.JDateChooser;
import datainterface.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import physio.*;

/**
 *
 * @author Lieselotte
 */
public class MainGUI extends javax.swing.JFrame {
    
    private PatientAdmin admin = null;

    /**
     * Creates new form NewJFrame
     */
    public MainGUI() throws DataException {
        initComponents();
        admin = new PatientAdmin();
        
        refreshOverzichtPanel();
        refreshPatientsPanel();
        refreshPhysiosPanel();
        refreshExercisesPanel();
    }
    
    private void refreshPatientLists() throws DataException {
        refreshOvzPatientsList();
        ovz_kin_list.setSelectedIndex(0);
        refreshPatPatientsList();
        pat_list.setSelectedIndex(0);
    } 
    
    private void refreshPhysioLists() throws DataException {
        refreshOvzPhysioList();
        ovz_kin_list.setSelectedIndex(0);
        refreshKinPhysiosList();
        kin_list.setSelectedIndex(0);
    }
    
    private void refreshOverzichtPanel() throws DataException {
        refreshOvzPatientsList();
        ovz_kin_list.setSelectedIndex(0);
        refreshOvzPhysioList();
        ovz_pat_list.setSelectedIndex(0);
    }
    
    private void refreshPatientsPanel() throws DataException {
        refreshPatPatientsList();
        pat_list.setSelectedIndex(0);
    }    
    
    private void refreshPhysiosPanel() throws DataException {
        refreshKinPhysiosList();
        kin_list.setSelectedIndex(0);
    }
    
    private void refreshExercisesPanel() throws DataException{
        refreshExercisesList();
    }
    
    private void refreshOvzPatientsList() throws DataException {
        DefaultListModel listModel = new DefaultListModel(); 
        for (Patient p : admin.getPatients()){
            listModel.addElement(p.getVoornaam() + " " + p.getAchternaam());
        }
        ovz_pat_list.setModel(listModel); 
    }
    
    private void refreshOvzPhysioList() throws DataException{
        DefaultListModel listModel = new DefaultListModel();
        for (Physio p : admin.getPhysios()){
            listModel.addElement(p.getVoornaam() + " " + p.getNaam());
        }
        ovz_kin_list.setModel(listModel);
    }
    
    private void refreshPatPatientsList() throws DataException {
        DefaultListModel listModel = new DefaultListModel(); 
        for (Patient p : admin.getPatients()){
            listModel.addElement(p.getVoornaam() + " " + p.getAchternaam());
        }
        pat_list.setModel(listModel); 
    }
    
    private void refreshKinPhysiosList() throws DataException {
        DefaultListModel listModel = new DefaultListModel(); 
        for (Physio p : admin.getPhysios()){
            listModel.addElement(p.getVoornaam() + " " + p.getNaam());
        }
        kin_list.setModel(listModel); 
    }    
    
    private void refreshExerciseProgramList(String patient) throws DataException{
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<ExerciseProgram> exerciseprogramList = admin.getExercisePrograms(patient);
        if(exerciseprogramList != null){
            for(ExerciseProgram exprog : exerciseprogramList){
                listModel.addElement(exprog.getVolgnummer());
            }
        }
        ovz_ovz_list.setModel(listModel);
        ovz_ovz_list.setSelectedIndex(listModel.size()-1);
    }
    
    private void refreshExercisesList() throws DataException{
       
        CustomisedTableModel tableModel = new CustomisedTableModel(
            new Object [][] {},
            new String [] {"Naam", "Foto", "Beginhouding", "Instructie"});
        ArrayList<Exercise> exerciseList = admin.getExercises();
        if(exerciseList != null){
            for(Exercise exercise : exerciseList){
                tableModel.addRow(new Object[]{exercise.getNaam(), exercise.getAfbeelding(), exercise.getBeginhouding(), exercise.getInstructie()});
                JLabel picLabel = new JLabel(exercise.getAfbeelding());
                JOptionPane.showMessageDialog(null, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
            }
            oef_table.setModel(tableModel);
        }
        
    }
    
    private void clearOvzKinInfo() 
    {
        setOvzKinInfo("","","","");
    }
    
    private void setOvzKinInfo(String riziv, String voornaam, String naam, String email)
    {
        ovz_kin_riziv.setText(riziv);
        ovz_kin_voornaam.setText(voornaam);
        ovz_kin_naam.setText(naam);
        ovz_kin_email.setText(email);          
    }
    
    private void clearOvzPatInfo() 
    {
        setOvzPatInfo("","","","");
    }    
    
    private void setOvzPatInfo(String nummer, String voornaam, String naam, String email)
    {
        ovz_pat_nummer.setText(nummer);
        ovz_pat_voornaam.setText(voornaam);
        ovz_pat_achternaam.setText(naam);
        ovz_pat_email.setText(email);          
    }   
    
    private void clearPatPatInfo() 
    {
        setPatPatInfo("","","","");
    }    
    
    private void setPatPatInfo(String nummer, String voornaam, String naam, String email)
    {
        pat_nummer.setText(nummer);
        pat_voornaam.setText(voornaam);
        pat_achternaam.setText(naam);
        pat_email.setText(email);          
    }   
    
    private void clearOvzOvzInfo()
    {
        setOvzOvzInfo("","");
    }
    
    private void setOvzOvzInfo(String datum, String kinesist)
    {
        ovz_ovz_datum.setText(datum);
        ovz_ovz_kinesist.setText(kinesist);
    }   
    
    private void clearKinKinInfo() 
    {
        setOvzKinInfo("","","","");
    }
    
    private void setKinKinInfo(String riziv, String voornaam, String naam, String email)
    {
        kin_riziv.setText(riziv);
        kin_voornaam.setText(voornaam);
        kin_achternaam.setText(naam);
        kin_email.setText(email);          
    }    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        ovz_tab = new javax.swing.JPanel();
        ovz_kin_panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ovz_kin_list = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ovz_kin_riziv = new javax.swing.JLabel();
        ovz_kin_voornaam = new javax.swing.JLabel();
        ovz_kin_naam = new javax.swing.JLabel();
        ovz_kin_email = new javax.swing.JLabel();
        ovz_pat_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ovz_pat_list = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ovz_pat_nummer = new javax.swing.JLabel();
        ovz_pat_voornaam = new javax.swing.JLabel();
        ovz_pat_achternaam = new javax.swing.JLabel();
        ovz_pat_email = new javax.swing.JLabel();
        ovz_ovz_panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ovz_ovz_list = new javax.swing.JList();
        ovz_ovz_voegToe = new javax.swing.JButton();
        ovz_ovz_wijzig = new javax.swing.JButton();
        ovz_ovz_wis = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        ovz_ovz_datum = new javax.swing.JLabel();
        ovz_ovz_kinesist = new javax.swing.JLabel();
        ovz_oef_panel = new javax.swing.JPanel();
        ovz_oef_voegToe = new javax.swing.JButton();
        ovz_oef_verwijder = new javax.swing.JButton();
        ovz_oef_print = new javax.swing.JButton();
        oef_oef_email = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        ovz_oef_table = new javax.swing.JTable();
        pat_tab = new javax.swing.JPanel();
        pat_pat_panel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pat_list = new javax.swing.JList();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pat_nummer = new javax.swing.JLabel();
        pat_voornaam = new javax.swing.JLabel();
        pat_achternaam = new javax.swing.JLabel();
        pat_email = new javax.swing.JLabel();
        pat_voegToe = new javax.swing.JButton();
        pat_wijzig = new javax.swing.JButton();
        pat_wis = new javax.swing.JButton();
        kin_tab = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        kin_list = new javax.swing.JList();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        kin_riziv = new javax.swing.JLabel();
        kin_voornaam = new javax.swing.JLabel();
        kin_achternaam = new javax.swing.JLabel();
        kin_email = new javax.swing.JLabel();
        kin_voegToe = new javax.swing.JButton();
        kin_wijzig = new javax.swing.JButton();
        kin_wis = new javax.swing.JButton();
        oef_tab = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        oef_table = new javax.swing.JTable();
        oef_voegToe = new javax.swing.JButton();
        oef_wijzig = new javax.swing.JButton();
        oef_wis = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ovz_kin_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Kinesist"));

        ovz_kin_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        ovz_kin_list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ovz_kin_listValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(ovz_kin_list);

        jLabel1.setText("Info:");

        jLabel2.setText("Riziv:");

        jLabel3.setText("Voornaam:");

        jLabel4.setText("Naam:");

        jLabel16.setText("Email:");

        ovz_kin_riziv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        ovz_kin_voornaam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        ovz_kin_naam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        ovz_kin_email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout ovz_kin_panelLayout = new javax.swing.GroupLayout(ovz_kin_panel);
        ovz_kin_panel.setLayout(ovz_kin_panelLayout);
        ovz_kin_panelLayout.setHorizontalGroup(
            ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_kin_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ovz_kin_panelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel16))
                        .addGap(55, 55, 55)
                        .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ovz_kin_riziv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ovz_kin_voornaam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ovz_kin_naam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ovz_kin_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ovz_kin_panelLayout.createSequentialGroup()
                        .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ovz_kin_panelLayout.setVerticalGroup(
            ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ovz_kin_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ovz_kin_riziv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ovz_kin_voornaam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ovz_kin_naam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_kin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(ovz_kin_email))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ovz_pat_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Patiënten"));

        ovz_pat_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        ovz_pat_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ovz_pat_list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ovz_pat_listValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ovz_pat_list);

        jLabel5.setText("Info:");

        jLabel6.setText("Nummer:");

        jLabel7.setText("Voornaam:");

        jLabel8.setText("Achternaam:");

        jLabel9.setText("Email:");

        ovz_pat_nummer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        ovz_pat_voornaam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        ovz_pat_achternaam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        ovz_pat_email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout ovz_pat_panelLayout = new javax.swing.GroupLayout(ovz_pat_panel);
        ovz_pat_panel.setLayout(ovz_pat_panelLayout);
        ovz_pat_panelLayout.setHorizontalGroup(
            ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_pat_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ovz_pat_panelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ovz_pat_panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ovz_pat_panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ovz_pat_email, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(ovz_pat_achternaam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ovz_pat_voornaam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ovz_pat_nummer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        ovz_pat_panelLayout.setVerticalGroup(
            ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_pat_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(ovz_pat_nummer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(ovz_pat_voornaam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ovz_pat_achternaam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ovz_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ovz_pat_email))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ovz_ovz_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Overzicht"));

        ovz_ovz_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        ovz_ovz_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ovz_ovz_list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ovz_ovz_listValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(ovz_ovz_list);

        ovz_ovz_voegToe.setText("Voeg toe");
        ovz_ovz_voegToe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ovz_ovz_voegToeActionPerformed(evt);
            }
        });

        ovz_ovz_wijzig.setText("Wijzig");
        ovz_ovz_wijzig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ovz_ovz_wijzigActionPerformed(evt);
            }
        });

        ovz_ovz_wis.setText("Wis");
        ovz_ovz_wis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ovz_ovz_wisActionPerformed(evt);
            }
        });

        jLabel25.setText("Info:");

        jLabel26.setText("Datum:");

        jLabel27.setText("Kinesist:");

        javax.swing.GroupLayout ovz_ovz_panelLayout = new javax.swing.GroupLayout(ovz_ovz_panel);
        ovz_ovz_panel.setLayout(ovz_ovz_panelLayout);
        ovz_ovz_panelLayout.setHorizontalGroup(
            ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_ovz_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ovz_ovz_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(ovz_ovz_panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27))
                                .addGap(37, 37, 37)
                                .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ovz_ovz_kinesist)
                                    .addComponent(ovz_ovz_datum)))))
                    .addGroup(ovz_ovz_panelLayout.createSequentialGroup()
                        .addComponent(ovz_ovz_voegToe)
                        .addGap(18, 18, 18)
                        .addComponent(ovz_ovz_wijzig)
                        .addGap(18, 18, 18)
                        .addComponent(ovz_ovz_wis)))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        ovz_ovz_panelLayout.setVerticalGroup(
            ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_ovz_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ovz_ovz_panelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(ovz_ovz_datum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(ovz_ovz_kinesist)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ovz_ovz_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ovz_ovz_voegToe)
                    .addComponent(ovz_ovz_wijzig)
                    .addComponent(ovz_ovz_wis))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ovz_oef_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Oefenschema"));

        ovz_oef_voegToe.setText("Voeg oefening toe");

        ovz_oef_verwijder.setText("Verwijder oefening");

        ovz_oef_print.setText("Print");

        oef_oef_email.setText("E-mail");

        ovz_oef_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(ovz_oef_table);

        javax.swing.GroupLayout ovz_oef_panelLayout = new javax.swing.GroupLayout(ovz_oef_panel);
        ovz_oef_panel.setLayout(ovz_oef_panelLayout);
        ovz_oef_panelLayout.setHorizontalGroup(
            ovz_oef_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_oef_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ovz_oef_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ovz_oef_panelLayout.createSequentialGroup()
                        .addComponent(ovz_oef_voegToe)
                        .addGap(18, 18, 18)
                        .addComponent(ovz_oef_verwijder)
                        .addGap(18, 18, 18)
                        .addComponent(ovz_oef_print)
                        .addGap(18, 18, 18)
                        .addComponent(oef_oef_email)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ovz_oef_panelLayout.setVerticalGroup(
            ovz_oef_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ovz_oef_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ovz_oef_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ovz_oef_voegToe)
                    .addComponent(ovz_oef_verwijder)
                    .addComponent(ovz_oef_print)
                    .addComponent(oef_oef_email))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout ovz_tabLayout = new javax.swing.GroupLayout(ovz_tab);
        ovz_tab.setLayout(ovz_tabLayout);
        ovz_tabLayout.setHorizontalGroup(
            ovz_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_tabLayout.createSequentialGroup()
                .addGroup(ovz_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ovz_kin_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ovz_pat_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ovz_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ovz_oef_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ovz_ovz_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        ovz_tabLayout.setVerticalGroup(
            ovz_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ovz_tabLayout.createSequentialGroup()
                .addComponent(ovz_kin_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ovz_pat_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ovz_tabLayout.createSequentialGroup()
                .addComponent(ovz_ovz_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ovz_oef_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Overzicht", ovz_tab);

        pat_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pat_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pat_list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                pat_listValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(pat_list);

        jLabel21.setText("Info:");

        jLabel22.setText("Nummer:");

        jLabel23.setText("Voornaam:");

        jLabel24.setText("Achternaam:");

        jLabel30.setText("Email:");

        pat_voegToe.setText("Voeg toe");
        pat_voegToe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pat_voegToeActionPerformed(evt);
            }
        });

        pat_wijzig.setText("Wijzig gegevens");
        pat_wijzig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pat_wijzigActionPerformed(evt);
            }
        });

        pat_wis.setText("Wis");
        pat_wis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pat_wisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pat_pat_panelLayout = new javax.swing.GroupLayout(pat_pat_panel);
        pat_pat_panel.setLayout(pat_pat_panelLayout);
        pat_pat_panelLayout.setHorizontalGroup(
            pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pat_pat_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(pat_pat_panelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel30))
                        .addGap(66, 66, 66)
                        .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pat_voornaam, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pat_nummer, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pat_achternaam, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pat_email, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pat_pat_panelLayout.createSequentialGroup()
                        .addComponent(pat_voegToe)
                        .addGap(18, 18, 18)
                        .addComponent(pat_wijzig)
                        .addGap(18, 18, 18)
                        .addComponent(pat_wis)))
                .addContainerGap(397, Short.MAX_VALUE))
        );
        pat_pat_panelLayout.setVerticalGroup(
            pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pat_pat_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pat_pat_panelLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(pat_nummer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(pat_voornaam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(pat_achternaam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(pat_email))
                        .addGap(51, 51, 51)
                        .addGroup(pat_pat_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pat_voegToe)
                            .addComponent(pat_wijzig)
                            .addComponent(pat_wis))))
                .addContainerGap(385, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pat_tabLayout = new javax.swing.GroupLayout(pat_tab);
        pat_tab.setLayout(pat_tabLayout);
        pat_tabLayout.setHorizontalGroup(
            pat_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 992, Short.MAX_VALUE)
            .addGroup(pat_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pat_pat_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pat_tabLayout.setVerticalGroup(
            pat_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
            .addGroup(pat_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pat_pat_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Patiënten", pat_tab);

        kin_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        kin_list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                kin_listValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(kin_list);

        jLabel35.setText("Info:");

        jLabel36.setText("Riziv:");

        jLabel37.setText("Voornaam:");

        jLabel38.setText("Achternaam:");

        jLabel39.setText("Email:");

        kin_voegToe.setText("Voeg toe");
        kin_voegToe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_voegToeActionPerformed(evt);
            }
        });

        kin_wijzig.setText("Wijzig gegevens");
        kin_wijzig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_wijzigActionPerformed(evt);
            }
        });

        kin_wis.setText("Wis");
        kin_wis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kin_wisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kin_tabLayout = new javax.swing.GroupLayout(kin_tab);
        kin_tab.setLayout(kin_tabLayout);
        kin_tabLayout.setHorizontalGroup(
            kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kin_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kin_tabLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addGap(63, 63, 63)
                        .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kin_voornaam, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kin_riziv, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kin_achternaam, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kin_email, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel35)
                    .addGroup(kin_tabLayout.createSequentialGroup()
                        .addComponent(kin_voegToe)
                        .addGap(18, 18, 18)
                        .addComponent(kin_wijzig)
                        .addGap(18, 18, 18)
                        .addComponent(kin_wis)))
                .addContainerGap(405, Short.MAX_VALUE))
        );
        kin_tabLayout.setVerticalGroup(
            kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kin_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kin_tabLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(kin_riziv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(kin_voornaam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(kin_achternaam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(kin_email))
                        .addGap(31, 31, 31)
                        .addGroup(kin_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kin_voegToe)
                            .addComponent(kin_wijzig)
                            .addComponent(kin_wis)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(472, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Kinesisten", kin_tab);

        oef_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Naam", "Foto", "Beginhouding", "Instructie"
            }
        ));
        jScrollPane7.setViewportView(oef_table);
        if (oef_table.getColumnModel().getColumnCount() > 0) {
            oef_table.getColumnModel().getColumn(0).setPreferredWidth(2);
        }

        oef_voegToe.setText("Voeg toe");

        oef_wijzig.setText("Wijzig oefening");

        oef_wis.setText("Wis");

        javax.swing.GroupLayout oef_tabLayout = new javax.swing.GroupLayout(oef_tab);
        oef_tab.setLayout(oef_tabLayout);
        oef_tabLayout.setHorizontalGroup(
            oef_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oef_tabLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(oef_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(oef_tabLayout.createSequentialGroup()
                        .addComponent(oef_voegToe)
                        .addGap(18, 18, 18)
                        .addComponent(oef_wijzig)
                        .addGap(18, 18, 18)
                        .addComponent(oef_wis))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        oef_tabLayout.setVerticalGroup(
            oef_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oef_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(oef_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oef_voegToe)
                    .addComponent(oef_wijzig)
                    .addComponent(oef_wis))
                .addContainerGap(289, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Oefeningen", oef_tab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ovz_kin_listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ovz_kin_listValueChanged
        clearOvzKinInfo();
        // Get physio associated with this selection event
        int index = ovz_kin_list.getSelectedIndex();
        if (index >= 0) {
            Physio p = admin.getPhysios().get(index);
            setOvzKinInfo(p.getRiziv(), p.getVoornaam(), p.getNaam(), p.getEmail());
        }      
    }//GEN-LAST:event_ovz_kin_listValueChanged

    private void ovz_pat_listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ovz_pat_listValueChanged
        clearOvzPatInfo();
        // Get patient associated with this selection event
        int index = ovz_pat_list.getSelectedIndex();
        if (index >= 0) {
            Patient p = admin.getPatients().get(index);
            setOvzPatInfo(p.getNummer(), p.getVoornaam(), p.getAchternaam(), p.getEmailadres());
            
            try{
                refreshExerciseProgramList(p.getNummer());
                ovz_ovz_list.setSelectedIndex(0);
            }
            catch (DataException e){};            
        }     
    }//GEN-LAST:event_ovz_pat_listValueChanged

    private void ovz_ovz_listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ovz_ovz_listValueChanged
        
        clearOvzOvzInfo();
        if(!ovz_ovz_list.isSelectionEmpty()){
            
            int volgnummer = (Integer)ovz_ovz_list.getSelectedValue();
            int index = ovz_pat_list.getSelectedIndex();
            Patient p = admin.getPatients().get(index);
            String pNummer = p.getNummer();
            SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
            try{
                ExerciseProgram exprog = admin.getExerciseProgramDetail(pNummer, volgnummer);
                String datum = sf.format(exprog.getDatum());
                Physio physio = exprog.getPhysio();
                String fullName = physio.getVoornaam() + " " + physio.getNaam();
                setOvzOvzInfo(datum, fullName);
            }
            catch (DataException e){};
        }        
    }//GEN-LAST:event_ovz_ovz_listValueChanged

    private void ovz_ovz_voegToeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovz_ovz_voegToeActionPerformed
        try{
            JDateChooser chooseDate = new JDateChooser(new java.util.Date());    
            JComboBox choosePhysio = new JComboBox();
            for(Physio p: admin.getPhysios()){
                choosePhysio.addItem(p.getVoornaam() + " " + p.getNaam());
            }
            choosePhysio.setSelectedIndex(0);
            Object[] message = {
                "Datum:", chooseDate, 
                "Kinesist:", choosePhysio};
            int optie = JOptionPane.showOptionDialog(this, message, "Geef gegevens nieuw oefenschema in", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(optie == JOptionPane.OK_OPTION){
                String patientnummer = null;
                int index = ovz_pat_list.getSelectedIndex();
                if (index >= 0) {
                    Patient p = admin.getPatients().get(index);
                    patientnummer = p.getNummer();
                }
                java.sql.Date datum = null;
                if(chooseDate.getDate() != null){
                   datum = new java.sql.Date(chooseDate.getDate().getTime()); 
                }
                String rizivkine = null;
                int indexcombo = choosePhysio.getSelectedIndex();
                if (indexcombo >= 0) {
                    Physio p = admin.getPhysios().get(indexcombo);
                    rizivkine = p.getRiziv();
                }
                admin.addExProg(patientnummer, datum, rizivkine);
                refreshExerciseProgramList(patientnummer);
            }
        }    
        catch(DataException e){
            JOptionPane.showMessageDialog(this, "Fout bij het invoeren van dit oefenschema");
        }
    }//GEN-LAST:event_ovz_ovz_voegToeActionPerformed

    private void ovz_ovz_wijzigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovz_ovz_wijzigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ovz_ovz_wijzigActionPerformed

    private void ovz_ovz_wisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovz_ovz_wisActionPerformed
         try{
            String patientnummer = null;
            int indexPatient = pat_list.getSelectedIndex();
            if (indexPatient >= 0) {
                Patient p = admin.getPatients().get(indexPatient);
                patientnummer = p.getNummer();
            }
            int volgnummer = (int)ovz_ovz_list.getSelectedValue();
            admin.deleteExProg(patientnummer, volgnummer);
            refreshExerciseProgramList(patientnummer);
        }
        catch(DataException e){}
    }//GEN-LAST:event_ovz_ovz_wisActionPerformed

    private void pat_listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_pat_listValueChanged
        clearPatPatInfo();
        // Get patient associated with this selection event
        int index = pat_list.getSelectedIndex();
        if (index >= 0) {
            Patient p = admin.getPatients().get(index);
            setPatPatInfo(p.getNummer(), p.getVoornaam(), p.getAchternaam(), p.getEmailadres());
        }     
    }//GEN-LAST:event_pat_listValueChanged

    private void kin_listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_kin_listValueChanged
        clearKinKinInfo();
        // Get physio associated with this selection event
        int index = kin_list.getSelectedIndex();
        if (index >= 0) {
            Physio p = admin.getPhysios().get(index);
            setKinKinInfo(p.getRiziv(), p.getVoornaam(), p.getNaam(), p.getEmail());
        }   
    }//GEN-LAST:event_kin_listValueChanged

    private void pat_voegToeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pat_voegToeActionPerformed
        try{
            JTextField inputNummer = new JTextField();
            JTextField inputVoornaam = new JTextField();
            JTextField inputAchternaam = new JTextField();
            JTextField inputEmail = new JTextField();
            Object[] message = {
                "Patientnummer:", inputNummer,
                "Voornaam:", inputVoornaam,
                "Achternaam:", inputAchternaam,
                "Emailadres:", inputEmail
            };
            int optie = JOptionPane.showOptionDialog(this, message, "Geef gegevens nieuwe patient in", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(optie == JOptionPane.OK_OPTION){
                admin.addPatient(inputNummer.getText(), inputAchternaam.getText(), inputVoornaam.getText(), inputEmail.getText());
                refreshPatientLists();
            }
        }    
        catch(DataException e){
            JOptionPane.showMessageDialog(this, "Fout bij het invoeren van deze patiënt");
        }
    }//GEN-LAST:event_pat_voegToeActionPerformed

    private void pat_wijzigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pat_wijzigActionPerformed
        try{
            int index = pat_list.getSelectedIndex();
                if (index >= 0) {
                    Patient p = admin.getPatients().get(index);
                    JTextField inputvoornaam = new JTextField(p.getVoornaam());
                    JTextField inputachternaam = new JTextField(p.getAchternaam());
                    JTextField inputEmail = new JTextField(p.getEmailadres());
                    Object[] message = {
                    "Voornaam:", inputvoornaam,
                    "Achternaam:", inputachternaam,
                    "Emailadres:", inputEmail
                    };
                    int optie = JOptionPane.showOptionDialog(this, message, "Wijzig gegevens", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(optie == JOptionPane.OK_OPTION){
                        admin.updatePatient(p.getNummer(), inputachternaam.getText(), inputvoornaam.getText(), inputEmail.getText());
                        refreshPatientLists();
                    }
                }
        }
        catch(DataException e){
            JOptionPane.showMessageDialog(this, "Fout bij het wijzigen van de gegevens van deze patiënt");
        }
    }//GEN-LAST:event_pat_wijzigActionPerformed

    private void pat_wisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pat_wisActionPerformed
        try{
            int index = pat_list.getSelectedIndex();
            if (index >= 0) {
                Patient p = admin.getPatients().get(index);
                admin.deletePatient(p);
                refreshPatientLists();
            }
        }
        catch(DataException e){
            JOptionPane.showMessageDialog(this, "Fout bij het verwijderen van deze patiënt");
        }
    }//GEN-LAST:event_pat_wisActionPerformed

    private void kin_voegToeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_voegToeActionPerformed
       try{
            JTextField inputRiziv = new JTextField();
            JTextField inputVoornaam = new JTextField();
            JTextField inputAchternaam = new JTextField();
            JTextField inputEmail = new JTextField();
            Object[] message = {
                "Riziv-nummer:", inputRiziv,
                "Voornaam:", inputVoornaam,
                "Achternaam:", inputAchternaam,
                "Emailadres:", inputEmail
            };
            int optie = JOptionPane.showOptionDialog(this, message, "Geef gegevens nieuwe kinesist in", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(optie == JOptionPane.OK_OPTION){
                admin.addPhysio(inputRiziv.getText(), inputAchternaam.getText(), inputVoornaam.getText(), inputEmail.getText());
                refreshPhysioLists();
            }
        }    
        catch(DataException e){
            JOptionPane.showMessageDialog(this, "Fout bij het invoeren van deze kinesist");
        }
    }//GEN-LAST:event_kin_voegToeActionPerformed

    private void kin_wijzigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_wijzigActionPerformed
        try{
            int index = kin_list.getSelectedIndex();
                if (index >= 0) {
                    Physio p = admin.getPhysios().get(index);
                    JTextField inputvoornaam = new JTextField(p.getVoornaam());
                    JTextField inputachternaam = new JTextField(p.getNaam());
                    JTextField inputEmail = new JTextField(p.getEmail());
                    Object[] message = {
                    "Voornaam:", inputvoornaam,
                    "Achternaam:", inputachternaam,
                    "Emailadres:", inputEmail
                    };
                    int optie = JOptionPane.showOptionDialog(this, message, "Wijzig gegevens", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(optie == JOptionPane.OK_OPTION){
                        admin.updatePhysio(p.getRiziv(), inputachternaam.getText(), inputvoornaam.getText(), inputEmail.getText());
                        refreshPhysioLists();
                    }
                }
        }
        catch(DataException e){
            JOptionPane.showMessageDialog(this, "Fout bij het wijzigen van de gegevens van deze patiënt");
        }
    }//GEN-LAST:event_kin_wijzigActionPerformed

    private void kin_wisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kin_wisActionPerformed
        try{
            int index = kin_list.getSelectedIndex();
            if (index >= 0) {
                Physio p = admin.getPhysios().get(index);
                admin.deletePhysio(p);
                refreshPhysioLists();
            }
        }
        catch(DataException e){}
    }//GEN-LAST:event_kin_wisActionPerformed

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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainGUI().setVisible(true);
                } catch (DataException ex) {}
            }
        });
    }
   
    
class CustomisedTableModel extends DefaultTableModel {  
  
    public CustomisedTableModel(Object rowData[][], Object columnNames[]) {  
         super(rowData, columnNames);  
      }  
     
    @Override  
      public Class getColumnClass(int col) {  
        if (col == 1)  
            return ImageIcon.class;  
        else return String.class;
    }  
  
    @Override  
      public boolean isCellEditable(int row, int col) {  
        return false;  
      }  
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel kin_achternaam;
    private javax.swing.JLabel kin_email;
    private javax.swing.JList kin_list;
    private javax.swing.JLabel kin_riziv;
    private javax.swing.JPanel kin_tab;
    private javax.swing.JButton kin_voegToe;
    private javax.swing.JLabel kin_voornaam;
    private javax.swing.JButton kin_wijzig;
    private javax.swing.JButton kin_wis;
    private javax.swing.JButton oef_oef_email;
    private javax.swing.JPanel oef_tab;
    private javax.swing.JTable oef_table;
    private javax.swing.JButton oef_voegToe;
    private javax.swing.JButton oef_wijzig;
    private javax.swing.JButton oef_wis;
    private javax.swing.JLabel ovz_kin_email;
    private javax.swing.JList ovz_kin_list;
    private javax.swing.JLabel ovz_kin_naam;
    private javax.swing.JPanel ovz_kin_panel;
    private javax.swing.JLabel ovz_kin_riziv;
    private javax.swing.JLabel ovz_kin_voornaam;
    private javax.swing.JPanel ovz_oef_panel;
    private javax.swing.JButton ovz_oef_print;
    private javax.swing.JTable ovz_oef_table;
    private javax.swing.JButton ovz_oef_verwijder;
    private javax.swing.JButton ovz_oef_voegToe;
    private javax.swing.JLabel ovz_ovz_datum;
    private javax.swing.JLabel ovz_ovz_kinesist;
    private javax.swing.JList ovz_ovz_list;
    private javax.swing.JPanel ovz_ovz_panel;
    private javax.swing.JButton ovz_ovz_voegToe;
    private javax.swing.JButton ovz_ovz_wijzig;
    private javax.swing.JButton ovz_ovz_wis;
    private javax.swing.JLabel ovz_pat_achternaam;
    private javax.swing.JLabel ovz_pat_email;
    private javax.swing.JList ovz_pat_list;
    private javax.swing.JLabel ovz_pat_nummer;
    private javax.swing.JPanel ovz_pat_panel;
    private javax.swing.JLabel ovz_pat_voornaam;
    private javax.swing.JPanel ovz_tab;
    private javax.swing.JLabel pat_achternaam;
    private javax.swing.JLabel pat_email;
    private javax.swing.JList pat_list;
    private javax.swing.JLabel pat_nummer;
    private javax.swing.JPanel pat_pat_panel;
    private javax.swing.JPanel pat_tab;
    private javax.swing.JButton pat_voegToe;
    private javax.swing.JLabel pat_voornaam;
    private javax.swing.JButton pat_wijzig;
    private javax.swing.JButton pat_wis;
    // End of variables declaration//GEN-END:variables
}
