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
		this.listMetadata = listMetaData;
		this.path = nameFile;
		this.nameInstitution = nameInstitution;
		
		for (int i = 0; i < listMetaData.size(); i++) {
			System.out.println("METADATA: "+ this.listMetadata.get(i).getAuthor());
		}
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
				setRowData(row, 0, listMetadata.get(i).getAuthor());
				setRowData(row, 1, listMetadata.get(i).getCreator());
				setRowData(row, 2, listMetadata.get(i).getIssued());
				setRowData(row, 3, listMetadata.get(i).getAbstract_doc());
				setRowData(row, 4, listMetadata.get(i).getOther());
				setRowData(row, 5, listMetadata.get(i).getDescription());
				setRowData(row, 6, listMetadata.get(i).getSource());
				setRowData(row, 7, listMetadata.get(i).getTitle());
				setRowData(row, 8, listMetadata.get(i).getPublisher());
				setRowData(row, 9, listMetadata.get(i).getType());
				setRowData(row, 10, listMetadata.get(i).getLanguage_iso());
				setRowData(row, 11, listMetadata.get(i).getSubject());				
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
