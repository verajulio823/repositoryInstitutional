package com.unsa.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.unsa.controller.AlgorithmsWord;
import com.unsa.controller.ExcelController;
import com.unsa.entity.Metadata;
import com.unsa.model.ConnectionManager;

public class MainOffice {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		
		
		ConnectionManager.GetConnection();
		
		List<Metadata> listMetaData = new ArrayList<Metadata>();
		
		String pathComplete = "/home/pc-vera/Documentos/OCRUNSA/ocr-word/";
		File folder = new File(pathComplete);
		File[] listOfFiles = folder.listFiles();
		
		int count =0;
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	
		    	System.out.println(file.getName());
		    	XWPFDocument docx = new XWPFDocument(new FileInputStream(file));
		    	AlgorithmsWord alg = new  AlgorithmsWord(docx.getParagraphs());
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