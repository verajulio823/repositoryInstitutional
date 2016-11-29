package com.unsa.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.unsa.controller.ExcelController;
import com.unsa.controller.PdfBoxController;
import com.unsa.entity.Metadata;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String path = "/home/pc-vera/Documentos/OCRUNSA/ocr-bci/B2-C-1660-1.pdf";
		System.out.println("Bale Berga la Bida");
		
		PdfBoxController pdf = new PdfBoxController(path);
		System.out.println(pdf.getResultText());
		
		List<Metadata> listMetaData = new ArrayList<Metadata>();		
		
		listMetaData = setDataTest();
		String name = "/home/pc-vera/metadata.xlsx";
		
		ExcelController excel = new ExcelController(name, "UNSA", listMetaData);
		
	}
	
	public static List<Metadata> setDataTest(){
		
		List<Metadata> list = new ArrayList<Metadata>();
		Metadata data = new Metadata();
		data.setAuthor("Julio");
		data.setCreator("daryel");
		data.setIssued("2016");
		data.setAbstract_doc("este es mi abstract");
		data.setDescription("test of description");
		data.setLanguage_iso("latin index");
		data.setOther("otrossssssssssss");
		data.setPublisher("Scielo");
		data.setSource("Universidad Nacional de san Agustin");
		data.setSubject("machine learning, deep learning");
		data.setTitle("Sistema de recomendación");
		
		Metadata data1 = new Metadata();
		data1.setAuthor("Jhon");
		data1.setCreator("momroy");
		data1.setIssued("2016");
		data1.setAbstract_doc("este es mi abstract222");
		data1.setDescription("test of description 222");
		data1.setLanguage_iso("latin index 22");
		data1.setOther("otrossssssssssss 22");
		data1.setPublisher("Scielo 22");
		data1.setSource("Universidad Nacional de san Agustin");
		data1.setSubject("machine learning, deep learning");
		data1.setTitle("Sistema de recomendación 2");
		
		list.add(data);
		list.add(data1);
		return list;
	}

}
