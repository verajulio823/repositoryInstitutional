package com.unsa.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.hwpf.HWPFDocument;



import com.unsa.controller.AlgorithmsWord;
import com.unsa.controller.ExcelController;
import com.unsa.entity.Metadata;
import com.unsa.model.ConnectionManager;

public class MainOffice {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		//MainOffice command = new MainOffice();
		//String comando = "soffice /home/pc-vera/Documentos/Arequipa.docx";
		//command.executeCommand(comando);
		String temp = "hola  mjndo  y vale verga la vida";
		
		String tempTitle = temp.substring(0,1).toUpperCase()+temp.substring(1,temp.length());
		System.out.println(tempTitle);
		
		
		AlgorithmsWord alg = null;
		ConnectionManager.GetConnection();
		
		System.out.println("hola".charAt("hola".length()-1));
		List<Metadata> listMetaData = new ArrayList<Metadata>();
		
		String pathComplete = "/home/pc-vera/Documentos/OCRUNSA/ocr-word/";
		File folder = new File(pathComplete);
		File[] listOfFiles = folder.listFiles();
		
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
		   // 	XWPFDocument docx = new XWPFDocument(new FileInputStream(file));
		    	//AlgorithmsWord alg = new  AlgorithmsWord(docx.getParagraphs());
		    	
		    	System.out.println("Facultad:  "+alg.getDescription());
		    	System.out.println("Escuela: "+alg.getSchool());
		    	System.out.println("Titulo: " +alg.getTitle());
		    	System.out.println("AÑO: "+alg.getIssued());
		    	List<String> autores =alg.getCreator();
		    	if(autores!=null){
			    	for(int i=0; i<autores.size(); i++){
			    		System.out.println("Autor "+i+": "+autores.get(i));
			    	}
		    	}
		    	System.out.println("Palabras clave: " + alg.getSubject());
		    	System.out.println("RESUMEN: \n " + alg.getAbstract());
		    	
		    	
		    	Metadata metadata = new Metadata();
		    	metadata.setDescription(alg.getDescriptionOptional());
		    	metadata.setTitle(alg.getTitle());
		    	metadata.setIssued(alg.getIssued());
		    	List<String> autor =alg.getCreator();
		    	String v_autores="";
		    	if(autores!=null){
			    	for(int i=0; i<autor.size(); i++){
			    		v_autores+=autor.get(i)+"//";			    		
			    	}
		    	}		    	
		    	
		    	String na=v_autores.substring(0, v_autores.length()-2);
		    	
		    	metadata.setAuthor("");
		    	metadata.setCreator(na);
		    	metadata.setSubject(alg.getSubject());
		    	metadata.setAbstract_doc(alg.getAbstract());
		    	metadata.setPublisher("Universidad Nacional de San Agustín");
		    	metadata.setSource("Universidad Nacional de San Agustín - UNSA");
				metadata.setType("Tesis");
				metadata.setLanguage_iso("Español");
				metadata.setOther("");
				
		    	listMetaData.add(metadata);
		    	
				
		    	
		    }
		}
		String name = "/home/pc-vera/metadataBiomedicas.xlsx";		
		
		
		ExcelController excel = new ExcelController(name, "UNSA", listMetaData);

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


}



// GET	 numbers of pages from a document DOCX
// int pageCount = extractor.getExtendedProperties().getPages();




/* 
 * 
 * LEER PARRAFOS Y SEPARAR CON TEXTOS ENRIQUECIDOS
 					List<XWPFRun> runs = p.getRuns();
				    if (runs != null) {
				        for (XWPFRun r : runs) {
				            String text = r.getText(0);
				            System.out.println(text);
				            System.out.println("---------------------------------------------------");
				        }
				    }
 
 
 * 
 * */


/*
for ( : ) {
if(count==34){
	count =0;
	break;
}
count++;
System.out.println(p.getParagraphText());
System.out.println("-------------------------------");
//System.out.println(p.getText());


}
*/