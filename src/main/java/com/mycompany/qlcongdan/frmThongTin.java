/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.qlcongdan;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

/**
 *
 * @author pc
 */
public class frmThongTin extends javax.swing.JFrame {

    /**
     * Creates new form frmThongTin
     */
    String id = "C004";
    private Neo4jConnection neo4jConnection;
    public frmThongTin() {
        initComponents();
        btnGiayTo.setVisible(true);
        showIcon(); 
        showInformationCitizen(id);
        showTableDocument(id);
        showType();
    }
    
    public void showType()
    {
       String[] types = {"BHYT", "CCCD", "BHTN"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(types);
        cbo_Type.setModel(model); 
        cbo_Type.setSelectedItem(types[0]);
    };
    
    public void showTableDocument(String id)
    {
         DefaultTableModel model = (DefaultTableModel) tb_Document.getModel();
        model.setRowCount(0); 
       Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
        try (Session session = driver.session()) {

            String query = "MATCH (p:Citizen{id:'"+id+"'})-[t:OWNS_DOCUMENT]->(d:Document) RETURN d";
            Result result = session.run(query);
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Node document = record.get("d").asNode();
                String documentid = document.get("document_id").asString();
                String issue_date =  document.get("issue_date").asString();
                String expiry_date = document.get("expiry_date").asString();
                String type = document.get("type").asString();
                model.addRow(new Object[]{documentid, issue_date, expiry_date, type});
            }
            tb_Document.setModel(model);
        } catch (Exception e) {
            System.err.println("Error occurred while connecting to Neo4j: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.close(); 
        }
    }
    
    public frmThongTin(String id) {
        initComponents();
        pnGiayTo.setVisible(true);
        showIcon();
        this.id = id;
        showInformationCitizen(id);
    }
    
     public void showIcon()
    {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/user.png"));
        imgAvata.setIcon(icon);
    }
     
    public void showInformationCitizen(String id) {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
        try (Session session = driver.session()) {

            String citizenQuery = "MATCH (c:Citizen{id:'"+id+"'}) RETURN c limit 1";
            Result citizenResult = session.run(citizenQuery);
            while (citizenResult.hasNext()) {
                org.neo4j.driver.Record record = citizenResult.next();
                Node citizen = record.get("c").asNode();
                lb_IDCitizen.setText( citizen.get("id").asString());
                lb_Name.setText( citizen.get("name").asString());
                lb_Gender.setText( citizen.get("gender").asString());
                lb_Nationality.setText( citizen.get("nationality").asString());
                lb_DOB.setText( citizen.get("date_of_birth").asString());
            }
        } catch (Exception e) {
            System.err.println("Error occurred while connecting to Neo4j: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.close(); 
        }
}
    
    public void showAgency(String documentid)
    {
         Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
        try (Session session = driver.session()) {

            String query = "MATCH (d:Document{document_id:'"+documentid+"'})-[:ISSUED_BY]->(a:Agency) RETURN a";
            Result result = session.run(query);
            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Node citizen = record.get("a").asNode();
                txt_Agencyid.setText( citizen.get("agency_id").asString());
                txt_Agencyname.setText( citizen.get("agency_name").asString());
                txt_Agencyaddress.setText( citizen.get("agency_address").asString());
            }
        } catch (Exception e) {
            System.err.println("Error occurred while connecting to Neo4j: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.close(); 
        }
    }


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        imgAvata = new javax.swing.JLabel();
        btnExit = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnGiayTo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnCongViec = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnQuanHe = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnSuKien = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb_IDCitizen = new javax.swing.JLabel();
        lb_Name = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lb_Gender = new javax.swing.JLabel();
        lb_Nationality = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lb_DOB = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnContainer = new javax.swing.JPanel();
        pnSuKien = new javax.swing.JPanel();
        pnGiayTo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Document = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Documentid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Issuedate = new javax.swing.JTextField();
        txt_Expiredate = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_Agencyid = new javax.swing.JTextField();
        txt_Agencyname = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_Agencyaddress = new javax.swing.JTextField();
        cbo_Type = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btn_Create = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        pnCongViec = new javax.swing.JPanel();
        pnQuanHe = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        imgAvata.setText("jLabel11");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgAvata, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgAvata, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
        );

        btnExit.setBackground(new java.awt.Color(255, 0, 51));
        btnExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("EXIT");

        javax.swing.GroupLayout btnExitLayout = new javax.swing.GroupLayout(btnExit);
        btnExit.setLayout(btnExitLayout);
        btnExitLayout.setHorizontalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnExitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        btnExitLayout.setVerticalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExitLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnGiayTo.setBackground(new java.awt.Color(153, 153, 153));
        btnGiayTo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGiayTo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGiayTo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGiayToMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("GIẤY TỜ");

        javax.swing.GroupLayout btnGiayToLayout = new javax.swing.GroupLayout(btnGiayTo);
        btnGiayTo.setLayout(btnGiayToLayout);
        btnGiayToLayout.setHorizontalGroup(
            btnGiayToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGiayToLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnGiayToLayout.setVerticalGroup(
            btnGiayToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGiayToLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(21, 21, 21))
        );

        btnCongViec.setBackground(new java.awt.Color(153, 153, 153));
        btnCongViec.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCongViec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCongViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCongViecMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CÔNG VIỆC");

        javax.swing.GroupLayout btnCongViecLayout = new javax.swing.GroupLayout(btnCongViec);
        btnCongViec.setLayout(btnCongViecLayout);
        btnCongViecLayout.setHorizontalGroup(
            btnCongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnCongViecLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        btnCongViecLayout.setVerticalGroup(
            btnCongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCongViecLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnQuanHe.setBackground(new java.awt.Color(153, 153, 153));
        btnQuanHe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnQuanHe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuanHe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuanHeMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("QUAN HỆ");

        javax.swing.GroupLayout btnQuanHeLayout = new javax.swing.GroupLayout(btnQuanHe);
        btnQuanHe.setLayout(btnQuanHeLayout);
        btnQuanHeLayout.setHorizontalGroup(
            btnQuanHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnQuanHeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnQuanHeLayout.setVerticalGroup(
            btnQuanHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQuanHeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnSuKien.setBackground(new java.awt.Color(153, 153, 153));
        btnSuKien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSuKien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuKien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuKienMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("SỰ KIỆN");

        javax.swing.GroupLayout btnSuKienLayout = new javax.swing.GroupLayout(btnSuKien);
        btnSuKien.setLayout(btnSuKienLayout);
        btnSuKienLayout.setHorizontalGroup(
            btnSuKienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSuKienLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnSuKienLayout.setVerticalGroup(
            btnSuKienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSuKienLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnGiayTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCongViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnQuanHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSuKien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGiayTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCongViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanHe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("THÔNG TIN CÔNG DÂN");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Mã công dân:");

        lb_IDCitizen.setBackground(new java.awt.Color(0, 51, 255));
        lb_IDCitizen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_IDCitizen.setForeground(new java.awt.Color(0, 51, 255));
        lb_IDCitizen.setText("2001215742");

        lb_Name.setBackground(new java.awt.Color(0, 51, 255));
        lb_Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_Name.setForeground(new java.awt.Color(0, 51, 255));
        lb_Name.setText("2001215742");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tên công dân");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Giới tính");

        lb_Gender.setBackground(new java.awt.Color(0, 51, 255));
        lb_Gender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_Gender.setForeground(new java.awt.Color(0, 51, 255));
        lb_Gender.setText("2001215742");

        lb_Nationality.setBackground(new java.awt.Color(0, 51, 255));
        lb_Nationality.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_Nationality.setForeground(new java.awt.Color(0, 51, 255));
        lb_Nationality.setText("2001215742");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Quốc tịch:");

        lb_DOB.setBackground(new java.awt.Color(0, 51, 255));
        lb_DOB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_DOB.setForeground(new java.awt.Color(0, 51, 255));
        lb_DOB.setText("2001215742");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Năm sinh:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lb_IDCitizen, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(28, 28, 28)
                        .addComponent(lb_Nationality, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lb_IDCitizen))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lb_Gender)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lb_Name))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lb_Nationality))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lb_DOB))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnSuKien.setBackground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout pnSuKienLayout = new javax.swing.GroupLayout(pnSuKien);
        pnSuKien.setLayout(pnSuKienLayout);
        pnSuKienLayout.setHorizontalGroup(
            pnSuKienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        pnSuKienLayout.setVerticalGroup(
            pnSuKienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        pnGiayTo.setBackground(new java.awt.Color(255, 255, 255));

        tb_Document.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "document_id", "issue_date", "expire_date", "type"
            }
        ));
        tb_Document.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_DocumentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Document);

        jLabel1.setText("id:");

        jLabel2.setText("issue_date:");

        txt_Issuedate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_IssuedateFocusLost(evt);
            }
        });
        txt_Issuedate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IssuedateActionPerformed(evt);
            }
        });

        txt_Expiredate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ExpiredateFocusLost(evt);
            }
        });

        jLabel14.setText("expire_date:");

        jLabel15.setText("type:");

        jLabel16.setText("id:");

        jLabel17.setText("name:");

        jLabel18.setText("address:");

        cbo_Type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 204));
        jLabel19.setText("Agency");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_Expiredate, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Documentid, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_Type, javax.swing.GroupLayout.Alignment.LEADING, 0, 134, Short.MAX_VALUE)
                    .addComponent(txt_Issuedate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Agencyaddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Agencyname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_Agencyid, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_Documentid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Agencyid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_Issuedate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(txt_Agencyname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Expiredate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(txt_Agencyaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        btn_Create.setBackground(new java.awt.Color(51, 153, 0));
        btn_Create.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Create.setForeground(new java.awt.Color(255, 255, 255));
        btn_Create.setText("THÊM");
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });

        btn_Delete.setBackground(new java.awt.Color(255, 0, 0));
        btn_Delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_Delete.setText("XÓA");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Sua.setBackground(new java.awt.Color(153, 153, 0));
        btn_Sua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Sua.setForeground(new java.awt.Color(255, 255, 255));
        btn_Sua.setText("SỬA");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(btn_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Create, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Create, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout pnGiayToLayout = new javax.swing.GroupLayout(pnGiayTo);
        pnGiayTo.setLayout(pnGiayToLayout);
        pnGiayToLayout.setHorizontalGroup(
            pnGiayToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGiayToLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnGiayToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnGiayToLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnGiayToLayout.setVerticalGroup(
            pnGiayToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGiayToLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnGiayToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnCongViec.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout pnCongViecLayout = new javax.swing.GroupLayout(pnCongViec);
        pnCongViec.setLayout(pnCongViecLayout);
        pnCongViecLayout.setHorizontalGroup(
            pnCongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        pnCongViecLayout.setVerticalGroup(
            pnCongViecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        pnQuanHe.setBackground(new java.awt.Color(102, 102, 0));

        javax.swing.GroupLayout pnQuanHeLayout = new javax.swing.GroupLayout(pnQuanHe);
        pnQuanHe.setLayout(pnQuanHeLayout);
        pnQuanHeLayout.setHorizontalGroup(
            pnQuanHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        pnQuanHeLayout.setVerticalGroup(
            pnQuanHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnContainerLayout = new javax.swing.GroupLayout(pnContainer);
        pnContainer.setLayout(pnContainerLayout);
        pnContainerLayout.setHorizontalGroup(
            pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnGiayTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnCongViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnQuanHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnSuKien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnContainerLayout.setVerticalGroup(
            pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnGiayTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnCongViec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnQuanHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnSuKien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGiayToMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGiayToMouseClicked
       pnGiayTo.setVisible(true);
       pnCongViec.setVisible(false);
       pnQuanHe.setVisible(false);
       pnSuKien.setVisible(false);
    }//GEN-LAST:event_btnGiayToMouseClicked

    private void btnCongViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCongViecMouseClicked
        pnCongViec.setVisible(true);
        pnGiayTo.setVisible(false);
        pnQuanHe.setVisible(false);
        pnSuKien.setVisible(false);
    }//GEN-LAST:event_btnCongViecMouseClicked

    private void btnQuanHeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuanHeMouseClicked
        pnQuanHe.setVisible(true);
        pnCongViec.setVisible(false);
        pnGiayTo.setVisible(false);
        pnSuKien.setVisible(false);
    }//GEN-LAST:event_btnQuanHeMouseClicked

    private void btnSuKienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuKienMouseClicked
        pnSuKien.setVisible(true);
        pnQuanHe.setVisible(false);
        pnCongViec.setVisible(false);
        pnGiayTo.setVisible(false);
    }//GEN-LAST:event_btnSuKienMouseClicked

    
    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
         this.setVisible(false);
    }//GEN-LAST:event_btnExitMouseClicked

     public boolean isValidDate(String date) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
     
    private void tb_DocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DocumentMouseClicked
       int row = tb_Document.getSelectedRow(); 
        if (row != -1) { 
            String documentId = tb_Document.getValueAt(row, 0).toString();
            String issueDate = tb_Document.getValueAt(row, 1).toString();
            String expiryDate = tb_Document.getValueAt(row, 2).toString();
            String type = tb_Document.getValueAt(row, 3).toString();

            txt_Documentid.setText(documentId);
            txt_Issuedate.setText(issueDate); 
            txt_Expiredate.setText(expiryDate);
            cbo_Type.setSelectedItem(type);
            
            showAgency(documentId);
        }
    }//GEN-LAST:event_tb_DocumentMouseClicked

    private void txt_IssuedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IssuedateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IssuedateActionPerformed

    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
       if (!isValidData()) {
            JOptionPane.showMessageDialog(this, "Thông báo!", "Bạn điền chưa đủ thông tin!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
        try (Session session = driver.session()) {
            String documentID = txt_Documentid.getText(); 
            String issueDate = txt_Issuedate.getText();  
            String expiryDate = txt_Expiredate.getText(); 
            String type = cbo_Type.getSelectedItem().toString(); 
            String agencyid = txt_Agencyid.getText();
            String agencyname = txt_Agencyname.getText();
            String agencyaddress = txt_Agencyaddress.getText();

            String createDocumentQuery = "MATCH (p:Citizen {id: $id}) " + 
                                         "CREATE (d:Document {issue_date: $issue_date, expiry_date: $expiry_date, document_id: $document_id, type: $type}), " +
                                         "(a:Agency {agency_name: $agency_name, agency_address: $agency_address, agency_id: $agency_id}), " +
                                         "(p)-[:OWNS_DOCUMENT]->(d), (d)-[:ISSUED_BY]->(a)";

            session.run(createDocumentQuery, 
                Values.parameters(
                    "id", id, 
                    "document_id", documentID,
                    "issue_date", issueDate,
                    "expiry_date", expiryDate,
                    "type", type,
                    "agency_name", agencyname,
                    "agency_address", agencyaddress,
                    "agency_id", agencyid
                )
            );

            JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            clearData();
            showTableDocument(id);  

        } catch (Exception e) {
            System.err.println("Error occurred while connecting to Neo4j: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.close(); 
        }
    }//GEN-LAST:event_btn_CreateActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
       if (!isValidData()) {
            JOptionPane.showMessageDialog(this, "Thông báo!", "Bạn điền chưa đủ thông tin!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
        try (Session session = driver.session()) {
            String documentID = txt_Documentid.getText(); 
            String issueDate = txt_Issuedate.getText();  
            String expiryDate = txt_Expiredate.getText(); 
            String type = cbo_Type.getSelectedItem().toString(); 
            String agencyid = txt_Agencyid.getText();
            String agencyname = txt_Agencyname.getText();
            String agencyaddress = txt_Agencyaddress.getText();

            String updateDocumentQuery = "MATCH (p:Citizen {id: $id})-[:OWNS_DOCUMENT]->(d:Document {document_id: $document_id}) " + 
                                         "MATCH (d)-[r:ISSUED_BY]->(a:Agency {agency_id: $agency_id}) " +
                                         "SET d.issue_date = $issue_date, d.expiry_date = $expiry_date, d.type = $type, " +
                                         "a.agency_name = $agency_name, a.agency_address = $agency_address";

            session.run(updateDocumentQuery, 
                Values.parameters(
                    "id", id, 
                    "document_id", documentID,
                    "issue_date", issueDate,
                    "expiry_date", expiryDate,
                    "type", type,
                    "agency_name", agencyname,
                    "agency_address", agencyaddress,
                    "agency_id", agencyid
                )
            );

            JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
             clearData();
            showTableDocument(id);  

        } catch (Exception e) {
            System.err.println("Error occurred while connecting to Neo4j: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.close();  
        }

    }//GEN-LAST:event_btn_SuaActionPerformed

 
    
    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        if (!isValidData()) {
            JOptionPane.showMessageDialog(this, "Thông báo!", "Bạn điền chưa đủ thông tin!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
        try (Session session = driver.session()) {
            String documentID = txt_Documentid.getText();
            String agencyid = txt_Agencyid.getText();

            String deleteDocumentQuery = "MATCH (p:Citizen {id: $id})-[:OWNS_DOCUMENT]->(d:Document {document_id: $document_id}) " +
                                         "MATCH (d)-[:ISSUED_BY]->(a:Agency {agency_id: $agency_id}) " +
                                         "DETACH DELETE d, a";

            session.run(deleteDocumentQuery, 
                Values.parameters(
                    "id", id, 
                    "document_id", documentID,
                    "agency_id", agencyid
                )
            );

            JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo!", JOptionPane.INFORMATION_MESSAGE);
             clearData();
            showTableDocument(id);  

        } catch (Exception e) {
            System.err.println("Error occurred while connecting to Neo4j: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.close();  
        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void txt_IssuedateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_IssuedateFocusLost
        if(!isValidDate(txt_Issuedate.getText()))
        {
             JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo!", JOptionPane.ERROR_MESSAGE);
             txt_Issuedate.setText("");
        }
    }//GEN-LAST:event_txt_IssuedateFocusLost

    private void txt_ExpiredateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ExpiredateFocusLost
        if(!isValidDate(txt_Issuedate.getText()))
        {
             JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ!", "Thông báo!", JOptionPane.ERROR_MESSAGE);
             txt_Issuedate.setText("");
        }
    }//GEN-LAST:event_txt_ExpiredateFocusLost

    public boolean isValidData()
    {
        String documentid = txt_Documentid.getText();
        String issue_date = txt_Issuedate.getText();
        String expire_date = txt_Expiredate.getText();
        String agencyid = txt_Agencyid.getText();
        String agencyname = txt_Agencyname.getText();
        String address = txt_Agencyaddress.getText();
        if(documentid.isEmpty()||issue_date.isEmpty()||agencyid.isEmpty()||agencyname.isEmpty()||expire_date.isEmpty()|| address.isEmpty())
        {
            return false;
        }
           return true;
    }
    
    public void clearData()
    {
        txt_Documentid.setText("");
        txt_Issuedate.setText("");
         txt_Expiredate.setText("");
        txt_Agencyid.setText("");
       txt_Agencyname.setText("");
        txt_Agencyaddress.setText("");
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
            java.util.logging.Logger.getLogger(frmThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmThongTin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCongViec;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnGiayTo;
    private javax.swing.JPanel btnQuanHe;
    private javax.swing.JPanel btnSuKien;
    private javax.swing.JButton btn_Create;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JComboBox<String> cbo_Type;
    private javax.swing.JLabel imgAvata;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_DOB;
    private javax.swing.JLabel lb_Gender;
    private javax.swing.JLabel lb_IDCitizen;
    private javax.swing.JLabel lb_Name;
    private javax.swing.JLabel lb_Nationality;
    private javax.swing.JPanel pnCongViec;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JPanel pnGiayTo;
    private javax.swing.JPanel pnQuanHe;
    private javax.swing.JPanel pnSuKien;
    private javax.swing.JTable tb_Document;
    private javax.swing.JTextField txt_Agencyaddress;
    private javax.swing.JTextField txt_Agencyid;
    private javax.swing.JTextField txt_Agencyname;
    private javax.swing.JTextField txt_Documentid;
    private javax.swing.JTextField txt_Expiredate;
    private javax.swing.JTextField txt_Issuedate;
    // End of variables declaration//GEN-END:variables
}
