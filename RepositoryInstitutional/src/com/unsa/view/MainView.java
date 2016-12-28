/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unsa.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellRendererPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.unsa.controller.AlgorithmsWord;
import com.unsa.controller.ExcelController;
import com.unsa.entity.Estadistica;
import com.unsa.entity.Metadata;
import com.unsa.model.ConnectionManager;
import com.unsa.src.MyJTable;

/**
 *
 * @author pc-vera
 */
public class MainView extends javax.swing.JFrame {
	JFileChooser file=null;
	AlgorithmsWord alg = null;
	List<Metadata> listMetaData = new ArrayList<Metadata>();
    /**
     * Creates new form MainView
     */
    public MainView() {
    	//ConnectionManager.GetConnection();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        lblArchivos = new javax.swing.JLabel();
        txtArchivos = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        btnProcesar = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        btnAbrirMetadata = new javax.swing.JButton();
        btnGuardarEn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblProcesando = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tableSalida = new javax.swing.JTable();
        //tableSalida = new MyJTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblInstitucion = new javax.swing.JTextField();
        lblSiglas = new javax.swing.JTextField();
        lblTipo = new javax.swing.JTextField();
        lblIdioma = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        btnGuardarOpciones = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuSeleccionar = new javax.swing.JMenuItem();
        menuProcesar = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Convertidor PDF - Metadata");
        setMinimumSize(new java.awt.Dimension(822, 642));

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección de Archivos"));

        lblArchivos.setText("Archivos");

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnProcesar.setText("Procesar PDFs");
        btnProcesar.setEnabled(false);
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnProcesarActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnAbrirMetadata.setText("Abrir Metadata");
        btnAbrirMetadata.setEnabled(false);
        btnAbrirMetadata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirMetadataActionPerformed(evt);
            }
        });

        btnGuardarEn.setText("Guardar");
        btnGuardarEn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEnActionPerformed(evt);
            }
        });

        jLabel2.setText("Guardar");

        lblProcesando.setText("Procesando");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblArchivos)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1)
                                    .addComponent(txtArchivos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGuardarEn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(lblProcesando)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnProcesar)
                .addGap(40, 40, 40)
                .addComponent(btnAbrirMetadata)
                .addGap(80, 80, 80))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArchivos)
                    .addComponent(txtArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEn)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProcesando)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirMetadata)
                    .addComponent(btnProcesar))
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Salida Procesamiento"));

        
        tableSalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Archivo", "Obs. Dudosa", "Obs. Faltante", "Abrir "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        tableSalida.addMouseListener(new java.awt.event.MouseAdapter() {
        	
        	
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	File[] files =file.getSelectedFiles();
            	
            	
                int row = tableSalida.rowAtPoint(evt.getPoint());
                int col = tableSalida.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    //System.out.println("Click en row: "+row+" col: "+col);
                	if(col==3){
                		int reply = JOptionPane.showConfirmDialog(null, "Desea abrir el archivo "+ 
                				tableSalida.getModel().getValueAt(row, 0)+" ?", "Abrir", JOptionPane.YES_NO_OPTION);
                	    if (reply == JOptionPane.YES_OPTION)
                	    {
                	    	executeCommand("soffice "+files[row].getAbsolutePath());
                	    }
                	}

                }
            }
        });
        
        
        scroll.setViewportView(tableSalida);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("Convertidor  PDF -  Metadata");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones Generales"));

        jLabel6.setText("Idioma:");

        jLabel4.setText("Institución:");

        jLabel7.setText("Siglas:");

        jLabel8.setText("Tipo Doc:");

        lblInstitucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblInstitucionActionPerformed(evt);
            }
        });

        btnEditar.setText(" Editar ");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardarOpciones.setText("Guardar");
        btnGuardarOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(41, 41, 41)
                        .addComponent(btnGuardarOpciones)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addComponent(lblInstitucion)
                    .addComponent(lblSiglas)
                    .addComponent(lblTipo)
                    .addComponent(lblIdioma))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblInstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarOpciones)
                    .addComponent(btnEditar))
                .addContainerGap())
        );

        jMenu1.setText("Archivo");

        menuSeleccionar.setText("Seleccionar");
        menuSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSeleccionarActionPerformed(evt);
            }
        });
        jMenu1.add(menuSeleccionar);

        menuProcesar.setText("Procesar");
        menuProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProcesarActionPerformed(evt);
            }
        });
        jMenu1.add(menuProcesar);

        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalir);

        jMenuBar1.add(jMenu1);

        menuEditar.setText("Opciones");

        jMenuItem4.setText("Editar Opciones");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuEditar.add(jMenuItem4);

        menuGuardar.setText("Guardar Opciones");
        menuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarActionPerformed(evt);
            }
        });
        menuEditar.add(menuGuardar);

        jMenuBar1.add(menuEditar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(275, 275, 275))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSeleccionarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_menuSeleccionarActionPerformed

    private void lblInstitucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblInstitucionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblInstitucionActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
    
    
       //JFileChooser file=new JFileChooser();
    	file = new JFileChooser();
       //FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF & DOC & DOCX Files", "pdf", "docx","doc");
       FileNameExtensionFilter filter1 = new FileNameExtensionFilter("PDF","pdf");
       FileNameExtensionFilter filter2 = new FileNameExtensionFilter("DOCX, DOC","docx","doc");
       //FileNameExtensionFilter filter3 = new FileNameExtensionFilter("DOC","doc");
       
       file.setFileFilter(filter1);       
       file.setFileFilter(filter2);
       
       
       
       file.setMultiSelectionEnabled(true);
	   file.showOpenDialog(null);
	   
	   File[] abrir= file.getSelectedFiles();
	   if(abrir.length==0){
		   JOptionPane.showMessageDialog(null, "No seleccionó ningun archivo");
		  return;
	   }
	   
	   btnProcesar.setEnabled(true);
	   
	   txtArchivos.setText(abrir[0].getParent());
	   String[] lnames = {"Nombre Archivo","Obs. Dudosa","Obs. Critica", "Abrir Archivo"};
	   
	   
	   DefaultTableModel model = new DefaultTableModel(lnames, 0);
	   tableSalida.setModel(model);
	   for (File file2 : abrir) {
		   Object[] data = new Object[4];
		   data[0]=file2.getName();
		   data[1]="";
		   
		   
		   model.addRow(data);
	   }
	   

    
    	
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnGuardarEnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEnActionPerformed
        // TODO add your handling code here:
    	JFileChooser jF1= new javax.swing.JFileChooser(); 
    	String ruta = ""; 
    	try{ 
	    	if(jF1.showSaveDialog(null)==jF1.APPROVE_OPTION){ 
	    	ruta = jF1.getSelectedFile().getAbsolutePath(); 
	    	//Aqui ya tiens la ruta,,,ahora puedes crear un fichero n esa ruta y escribir lo k kieras...
	    	jTextField1.setText(ruta);
	    	} 
	    	
    	} catch (Exception ex){ 
	    	ex.printStackTrace(); 
	    }
    	
    	
    	
    }//GEN-LAST:event_btnGuardarEnActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, IOException {//GEN-FIRST:event_btnProcesarActionPerformed
        // TODO add your handling code here:
    	if(lblInstitucion.getText().equals("") || lblInstitucion.getText().equals("")
    			|| lblIdioma.getText().equals("") || lblTipo.getText().equals("")){
    		
    	JOptionPane.showMessageDialog(null, "Una de las opciones generales está vacio");
    		return;
    	}
    	if(jTextField1.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "No se especificó la ruta donde guardar la metadata");
    		return;
    	}
    	
    	jProgressBar1.setValue(0);
    	
    	listMetaData.clear();
    	
    	
    	File[] listOfFiles= file.getSelectedFiles();
    	int count =0;
    	
    	
    	for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	
		    	if(file.getName().substring(file.getName().length() -1).equals("x")){ //is a docx
		    	    try {
		    	        XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
		    	        alg = new AlgorithmsWord(doc.getParagraphs());
		    	    } catch (IOException e) {
		    	        e.printStackTrace();
		    	    }
		    	} else { //is not a docx
		    	     try {
		    	         HWPFDocument doc = new HWPFDocument(new FileInputStream(file));
		    	         Range r = doc.getRange();
		    	         alg = new AlgorithmsWord(r);
		    	        } catch (IOException e) {
		    	            e.printStackTrace();
		    	        }
		    	}
		    	
		    	
		    	System.out.println(file.getName());
		    	
		    	Metadata metadata = new Metadata();
		    	metadata.setDescription(alg.getDescriptionOptional());
		    	metadata.setTitle(alg.getTitle());
		    	metadata.setIssued(alg.getIssued());
		    	List<String> autor =alg.getCreator();
		    	String v_autores="";
		    	String na="";
		    	if(autor!=null){
			    	for(int i=0; i<autor.size(); i++){
			    		String sa=autor.get(i).replaceAll("-", "");
			    		String saa=sa.replaceAll("\\*", "");
			    		String saaa=saa.replaceAll("_", "").trim();
			    		v_autores+=saaa+" //";			    		
			    	}
			    	if(!v_autores.equals("")){
			    		if(v_autores.length()>3){
			    			na=v_autores.substring(0, v_autores.length()-2).trim();	
			    		}			    		
			    	}
			    	
		    	}		    	
		    	
		    	
		    	
		    	metadata.setAuthor("");
		    	metadata.setCreator(na);
		    	metadata.setSubject(alg.getSubject());
		    	metadata.setAbstract_doc(alg.getAbstract());
		    	metadata.setPublisher(lblInstitucion.getText());
		    	metadata.setSource(lblInstitucion.getText()+" - "+lblSiglas.getText());
				metadata.setType(lblTipo.getText());
				metadata.setLanguage_iso(lblIdioma.getText());
				metadata.setOther("");
				metadata.setEscuela(alg.getSchool());
				
				Estadistica stadistic = new Estadistica();
				stadistic.setSizeAbstract(metadata.getAbstract_doc().length());
				stadistic.setSizeAutors(metadata.getAuthor().length());
				stadistic.setSizeEscuela(metadata.getEscuela().length());
				stadistic.setSizeFacultad(metadata.getDescription().length());
				stadistic.setSizeKeyWords(metadata.getSubject().length());
				//stadistic.setSizeSegundaEsp(sSegundaEsp);
				stadistic.setSizeTitle(metadata.getTitle().length());
				
				metadata.setStadistic(stadistic);
				
		    	listMetaData.add(metadata);    	
		    	int val_calculate = (count+1)*100/listOfFiles.length;
				jProgressBar1.setValue(val_calculate);
				count++;
		    	
		    }
		  
		}

    	String name = jTextField1.getText();
    	
    	
		ExcelController excel = new ExcelController(name, "UNSA", listMetaData);
		
		String[] lnames = {"Nombre Archivo","Obs. Dudosa","Obs. Critica", "Abrir Archivo"};
        DefaultTableModel model = new DefaultTableModel(lnames, 0);
		tableSalida.setModel(model);
		
		int contador =0;
		for (Metadata meta : listMetaData) {
			   Object[] data = new Object[4];
			   data[0]=listOfFiles[contador].getName();
			   
			   data[1]=meta.getStadistic().getObservationGeneral()? "Observacion":"";
			   data[2]=meta.getObservacionGeneral()? "Falta":"";
			   data[3]="abrir";
			   
			   model.addRow(data);
			   contador++;
		}
		
		
    	
    	btnAbrirMetadata.setEnabled(true);
    	
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void btnAbrirMetadataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirMetadataActionPerformed
        // TODO add your handling code here:
    	
    	executeCommand("soffice "+jTextField1.getText());
    	
    }//GEN-LAST:event_btnAbrirMetadataActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
    	lblInstitucion.setEditable(true);
		lblSiglas.setEditable(true);
		lblIdioma.setEditable(true);
		lblTipo.setEditable(true);
    	
    	
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarOpcionesActionPerformed
        // TODO add your handling code here:
    	if(!lblInstitucion.getText().equals("") || !lblInstitucion.getText().equals("")
    			|| !lblIdioma.getText().equals("") || !lblTipo.getText().equals("")){
    		
    	//JOptionPane.showMessageDialog(null, "Una de las opciones generales está vacio");
    		lblInstitucion.setEditable(false);
    		lblSiglas.setEditable(false);
    		lblIdioma.setEditable(false);
    		lblTipo.setEditable(false);
    		JOptionPane.showMessageDialog(null, "Cambios Guardados");
    	}else{
    		JOptionPane.showMessageDialog(null, "Una de las opciones generales está vacio");
    	}
    }//GEN-LAST:event_btnGuardarOpcionesActionPerformed

    private void menuProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProcesarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuProcesarActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSalirActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
    
	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirMetadata;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardarEn;
    private javax.swing.JButton btnGuardarOpciones;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblArchivos;
    private javax.swing.JTextField lblIdioma;
    private javax.swing.JTextField lblInstitucion;
    private javax.swing.JLabel lblProcesando;
    private javax.swing.JTextField lblSiglas;
    private javax.swing.JTextField lblTipo;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenuItem menuGuardar;
    private javax.swing.JMenuItem menuProcesar;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menuSeleccionar;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tableSalida;
    //private MyJTable tablesalida;
    private javax.swing.JTextField txtArchivos;
    // End of variables declaration//GEN-END:variables
}



// 	XWPFDocument docx = new XWPFDocument(new FileInputStream(file));
//AlgorithmsWord alg = new  AlgorithmsWord(docx.getParagraphs());

//System.out.println("Facultad:  "+alg.getDescription());
//System.out.println("Escuela: "+alg.getSchool());
//System.out.println("Titulo: " +alg.getTitle());
//System.out.println("AÑO: "+alg.getIssued());
//List<String> autores =alg.getCreator();
//if(autores!=null){
//	for(int i=0; i<autores.size(); i++){
	//	System.out.println("Autor "+i+": "+autores.get(i));
//	}
//}

//System.out.println("Palabras clave: " + alg.getSubject());
//System.out.println("RESUMEN: \n " + alg.getAbstract());