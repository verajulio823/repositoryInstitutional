package com.unsa.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.unsa.controller.PdfBoxController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path = "/home/pc-vera/Documentos/OCRUNSA/ocr-bci/B2-C-1660-1.pdf";
		System.out.println("Bale Berga la Bida");
		
		PdfBoxController pdf = new PdfBoxController(path);
		System.out.println(pdf.getResultText());
		
		
	}

}
