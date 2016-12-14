package com.unsa.src;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.unsa.controller.Algorithms;
import com.unsa.controller.AlgorithmsController;
import com.unsa.controller.CardKeyword;
import com.unsa.controller.ExcelController;
import com.unsa.controller.KeywordsExtractor;
import com.unsa.controller.PdfBoxController;
import com.unsa.entity.Facultad;
import com.unsa.entity.Metadata;
import com.unsa.model.ConnectionManager;


public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		ConnectionManager.GetConnection();
		
		List<Metadata> listMetaData = new ArrayList<Metadata>();
		
		String pathComplete = "/home/pc-vera/Documentos/OCRUNSA/ocr-bci/";
		File folder = new File(pathComplete);
		File[] listOfFiles = folder.listFiles();
		
		int count =0;
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	/*if(count==10){
		    		break;
		    	}
		    	*/
		    	
		        String name_file=file.getName().substring(0,file.getName().length()-4);
		        System.out.println("File: "+count+" name:" +name_file);
		        
		    	PdfBoxController pdf = new PdfBoxController(pathComplete+file.getName());
				AlgorithmsController alg = new AlgorithmsController(pdf.getText(1,1));
				
				System.out.println(alg.getTitle(pdf.getTextAreaTitleArray()));
			/*	
				Rectangle2D region = new Rectangle2D.Double(50, 300, 500, 250);
				String regionName = "region";
				PDFTextStripperByArea stripper;
				stripper = new PDFTextStripperByArea();
				stripper.addRegion(regionName, region);
				stripper.extractRegions(pdf.getPDPage());
				//System.out.println(stripper.extractRegions(pdf.getPDPage()));
				System.out.println(stripper.getTextForRegion("region"));
				*/
				//stripper.extractRegions(pdf);
				
				
				//System.out.println(alg.getAbstract(pdf));
				/*if(count==200){
					//System.out.println(alg.getAbstract(pdf));
	    		break;
				}*/
	    	
				
				/*if(name_file.equals("M-33569")){
					System.out.println(alg.getTitle(pdf.getTextAreaTitleArray()));
					
	    		break;
				}*/
				
				
				//System.out.println(alg.getAbstract(pdf));
				
				/*Metadata metadata = new Metadata();
				metadata.setCreator(alg.getCreator());
				metadata.setAuthor("asesor");
				metadata.setIssued(alg.getIssued());
				metadata.setAbstract_doc(alg.getAbstract(pdf));
				metadata.setOther(name_file);
				metadata.setDescription(alg.getDescription());
				metadata.setSource("Universidad Nacional de San Agustín - UNSA");
				metadata.setTitle(alg.getTitle());
				metadata.setPublisher("Universidad Nacional de San Agustín");
				metadata.setType("Tesis");
				metadata.setLanguage_iso("Español");
				metadata.setSubject(alg.getSubject(pdf));
				
		    	listMetaData.add(metadata);
		    	*/
		    	count++;
		    }
		}
		
		String name = "/home/pc-vera/metadata.xlsx";		
		ExcelController excel = new ExcelController(name, "UNSA", listMetaData);
		
		
		
		//String path = "/home/pc-vera/Documentos/OCRUNSA/ocr-bci/B2-M-17982.pdf";
		//System.out.println("Bale Berga la Bida");
		
		//PdfBoxController pdf = new PdfBoxController(path);
		
		/*List<CardKeyword> keywordsList = KeywordsExtractor.getKeywordsList(pdf.getText(1, 7));
		for (CardKeyword cardKeyword : keywordsList) {
			System.out.println("Frecuency :" + cardKeyword.getFrequency()+ " stem: "+cardKeyword.getStem());
		}
		*/
		
		//System.out.println(pdf.getResultText());
		
		//AlgorithmsController alg = new AlgorithmsController(pdf.getText(1,1));		
		
		
		//System.out.println(alg.getDescription()+ ": probabilidad:  "+alg.getProbabilityDescription());		
		
		//System.out.println("Palabras clave :" +alg.getSubject(pdf));
		
		//System.out.println("Resumen :"+ alg.getAbstract(pdf));
		
	/*	Metadata metadata = new Metadata();
		metadata.setCreator(alg.getCreator());
		metadata.setAuthor(alg.getAuthor());
		metadata.setIssued(alg.getIssued());
		metadata.setAbstract_doc(alg.getAbstract(pdf));
		metadata.setOther(alg.getIdentifier());
		metadata.setDescription(alg.getDescription());
		metadata.setSource("Universidad Nacional de San Agustín - UNSA");
		metadata.setTitle(alg.getTitle());
		metadata.setPublisher("Universidad Nacional de San Agustín");
		metadata.setType("Tesis");
		metadata.setLanguage_iso("Español");
		metadata.setSubject(alg.getSubject(pdf));
	*/	
		/*List<Metadata> listMetaData = new ArrayList<Metadata>();		
		
		listMetaData = setDataTest();
		String name = "/home/pc-vera/metadata.xlsx";
		
		ExcelController excel = new ExcelController(name, "UNSA", listMetaData);
		*/
		
		
	}
	
	

}
