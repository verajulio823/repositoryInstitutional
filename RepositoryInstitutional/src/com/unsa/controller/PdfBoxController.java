package com.unsa.controller;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PdfBoxController {
	private PDFTextStripper pdfStripper = null;
    private PDDocument pdDoc = null;
    private COSDocument cosDoc = null;
    private String path;
    private String result;
    private PDFParser parser = null;
    private PDPage pdpage=null;

	
	public PdfBoxController(String path) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		this.path=path;
		//this.result =getText();
		 File file = new File(path);       
	     parser = new PDFParser(new RandomAccessFile(file,"r")); 
	     parser.parse();
	     cosDoc = parser.getDocument();
         pdfStripper = new PDFTextStripper();
         pdDoc = new PDDocument(cosDoc);
	}
	
	public PDPage getPDPage(){
		pdpage=pdDoc.getPage(0);
		return pdpage;
	}
	
	public String getResultText(){
		return result;
	}
	
	public String getTextAreaTitle() throws IOException{
		Rectangle2D region = new Rectangle2D.Double(50, 300, 500, 250);
		String regionName = "region";
		PDFTextStripperByArea stripper;
		stripper = new PDFTextStripperByArea();
		stripper.addRegion(regionName, region);
		stripper.extractRegions(pdDoc.getPage(0));
		return stripper.getTextForRegion("region");
		//System.out.println(stripper.extractRegions(pdf.getPDPage()));
		//System.out.println(stripper.getTextForRegion("region"))
		
	}
	
	public String[] getTextAreaTitleArray() throws IOException{
		Rectangle2D region = new Rectangle2D.Double(50, 300, 500, 300);
		String regionName = "region";
		PDFTextStripperByArea stripper;
		stripper = new PDFTextStripperByArea();
		stripper.addRegion(regionName, region);
		stripper.extractRegions(pdDoc.getPage(0));
		String[] array =stripper.getTextForRegion("region").split("\n"); 
		return array;		
	}
	
	public String getText(int inicio, int fin){
       // File file = new File(path);
        String parsedText ="";
        try {
        	//PDFParser parser = new PDFParser(new RandomAccessFile(file,"r")); 
            //parser.parse();
            
            pdfStripper.setStartPage(inicio);
            pdfStripper.setEndPage(fin);
            //pdfStripper.get
            parsedText = pdfStripper.getText(pdDoc);
           // System.out.println(parsedText);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

		return parsedText;
	}

	public int getNumberPages() throws FileNotFoundException, IOException {		
		  File file = new File(path);	     	     
	      PDFParser parser = new PDFParser(new RandomAccessFile(file,"r")); 
	      parser.parse();
	      cosDoc = parser.getDocument();	     
	      pdDoc = new PDDocument(cosDoc);		 
		  return pdDoc.getNumberOfPages();
	}

	
	

}
