package com.unsa.controller;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfBoxController {
	private PDFTextStripper pdfStripper = null;
    private PDDocument pdDoc = null;
    private COSDocument cosDoc = null;
    private String path;
    private String result;

	
	public PdfBoxController(String path) {
		// TODO Auto-generated constructor stub
		this.path=path;
		this.result =getText();
	}
	
	public String getResultText(){
		return result;
	}
	
	private String getText(){
        File file = new File(path);
        String parsedText ="";
        try {
        	PDFParser parser = new PDFParser(new RandomAccessFile(file,"r")); 
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);
            parsedText = pdfStripper.getText(pdDoc);
           // System.out.println(parsedText);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

		return parsedText;
	}
	

}
