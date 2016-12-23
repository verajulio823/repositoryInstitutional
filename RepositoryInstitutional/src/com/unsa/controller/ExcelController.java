package com.unsa.controller;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.unsa.entity.Metadata;



public class ExcelController {
	
	private List<Metadata> listMetadata = new ArrayList<Metadata>();
	private String path;
	private String nameInstitution;
	private XSSFCellStyle styleA;
	private XSSFCellStyle styleR;
	
	
	public ExcelController(String nameFile, String nameInstitution, List<Metadata> listMetaData) throws IOException {
		// TODO Auto-generated constructor stubno
		this.listMetadata = listMetaData;
		this.path = nameFile;
		this.nameInstitution = nameInstitution;
		
		
		writeXLSXFile();
		
	}
	
	

	public void writeXLSXFile() throws IOException {
		
		String excelFileName = path;//"/home/pc-vera/userpass.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;
		styleA= wb.createCellStyle();
		styleR= wb.createCellStyle();
		XSSFColor colorRojo = new XSSFColor(new java.awt.Color(254,117,20));//254, 0, 0));
		XSSFColor colorAmarillo = new XSSFColor(new java.awt.Color(254, 254, 0));
		
		styleA.setFillForegroundColor(colorAmarillo);
		styleA.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleR.setFillForegroundColor(colorRojo);
		styleR.setFillPattern(CellStyle.SOLID_FOREGROUND);
		//iterating r number of rows
		for (int i=0; i < listMetadata.size(); i++ )
		{	
			XSSFRow row = sheet.createRow(i);
			if(i==0){
				setRowData(row, 0, "dc.contributor.author");
				setRowData(row, 1, "dc.creator");
				setRowData(row, 2, "dc.date.issued");
				setRowData(row, 3, "dc.description.abstract");
				setRowData(row, 4, "dc.identifier.other");
				setRowData(row, 5, "dc.description");
				setRowData(row, 6, "dc.source");
				setRowData(row, 7, "dc.title");
				setRowData(row, 8, "dc.publisher");
				setRowData(row, 9, "dc.type");
				setRowData(row, 10,"dc.language.iso");
				setRowData(row, 11,"dc.subject");
				
			}else{
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
				
			
				
				
				
						
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	
	public void setRowData(XSSFRow row, int index ,String value){

		XSSFCell cell = row.createCell(index);	
		if(value.equals(null) || value.equals("")){
			if(index==0 || index ==4){
				cell.setCellValue(value);
			}else{
			cell.setCellStyle(styleR);
			cell.setCellValue(value);
			}
		}else{
			cell.setCellValue(value);
		}		
	}

}


