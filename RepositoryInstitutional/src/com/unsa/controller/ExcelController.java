package com.unsa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.unsa.entity.Metadata;



public class ExcelController {
	
	private List<Metadata> listMetadata = new ArrayList<Metadata>();
	private String path;
	private String nameInstitution;
	
	public ExcelController(String nameFile, String nameInstitution, List<Metadata> listMetaData) throws IOException {
		// TODO Auto-generated constructor stubno
		this.listMetadata = listMetadata;
		this.path = nameFile;
		this.nameInstitution = nameInstitution;
		
		writeXLSXFile();
		
	}
	
	

	public void writeXLSXFile() throws IOException {
		
		String excelFileName = path;//"/home/pc-vera/userpass.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int i=0; i < listMetadata.size(); i++ )
		{
				XSSFRow row = sheet.createRow(i);
				
		//		XSSFCell cell0 = row.createCell(0);			
		//		cell0.setCellValue(listMetadata.get(i).getAuthor());
				setRowData(row, i, listMetadata.get(i).getAuthor());
				setRowData(row, i, listMetadata.get(i).getCreator());
				setRowData(row, i, listMetadata.get(i).getIssued());
				setRowData(row, i, listMetadata.get(i).getAbstract_doc());
				setRowData(row, i, listMetadata.get(i).getOther());
				setRowData(row, i, listMetadata.get(i).getDescription());
				setRowData(row, i, listMetadata.get(i).getSource());
				setRowData(row, i, listMetadata.get(i).getTitle());
				setRowData(row, i, listMetadata.get(i).getPublisher());
				setRowData(row, i, listMetadata.get(i).getType());
				setRowData(row, i, listMetadata.get(i).getLanguage_iso());
				setRowData(row, i, listMetadata.get(i).getSubject());				
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	
	public void setRowData(XSSFRow row, int index ,String value){
		XSSFCell cell = row.createCell(index);				
		cell.setCellValue(value);
		
	}

}
