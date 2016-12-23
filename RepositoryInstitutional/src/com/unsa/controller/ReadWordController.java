package com.unsa.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ReadWordController {
	XWPFDocument docx; 
	
	public void ReadWordController(String file) throws FileNotFoundException, IOException{
		docx = new XWPFDocument(new FileInputStream(file));
	}	
	
	public int getNumberPage(){
		
		return docx.getProperties().getExtendedProperties().getPages(); 
	}

}
