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
	private XSSFCellStyle styleP;
	
	
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
		styleP= wb.createCellStyle();
		XSSFColor colorRojo = new XSSFColor(new java.awt.Color(254,117,20));//254, 0, 0));
		XSSFColor colorAmarillo = new XSSFColor(new java.awt.Color(236,254,032)); //254, 254, 0));
		XSSFColor colorPlomo = new XSSFColor(new java.awt.Color(160,160,160)); //254, 254, 0));
		
		styleA.setFillForegroundColor(colorAmarillo);
		styleA.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleR.setFillForegroundColor(colorRojo);
		styleR.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleP.setFillForegroundColor(colorPlomo);
		styleP.setFillPattern(CellStyle.SOLID_FOREGROUND);
		//iterating r number of rows
		//System.out.println("SIZE " + listMetadata.size());
		for (int i=-1; i < listMetadata.size(); i++ )
		{	
			XSSFRow row = sheet.createRow(i+1);
			if(i==-1){
				setRowData(row, 0, "Nombre.Archivo",false);
				setRowData(row, 1, "dc.contributor.author",false);
				setRowData(row, 2, "dc.creator",false);
				setRowData(row, 3, "dc.date.issued",false);
				setRowData(row, 4, "dc.description.abstract",false);
				setRowData(row, 5, "dc.identifier.other",false);
				setRowData(row, 6, "dc.description",false);
				setRowData(row, 7, "dc.source",false);
				setRowData(row, 8, "dc.title",false);
				setRowData(row, 9, "dc.publisher",false);
				setRowData(row, 10, "dc.type",false);
				setRowData(row, 11,"dc.language.iso",false);
				setRowData(row, 12,"dc.subject",false);
				setRowData(row, 13,"dc.segundaEspecialidad",false);
				setRowData(row, 14,"thesis.degree.name",false);
				setRowData(row, 14,"thesis.degree.discipline",false);
				
			}else{
				setRowData(row, 0, listMetadata.get(i).getFileName(),false);
				setRowData(row, 1, listMetadata.get(i).getAuthor(),false);
				setRowData(row, 2, listMetadata.get(i).getCreator(),listMetadata.get(i).getStadistic().getSizeAutors());
				setRowData(row, 3, listMetadata.get(i).getIssued(),false);
				setRowData(row, 4, listMetadata.get(i).getAbstract_doc(),listMetadata.get(i).getStadistic().getSizeAbstract());
				setRowData(row, 5, listMetadata.get(i).getOther(),false);
				setRowData(row, 6, listMetadata.get(i).getDescription(),listMetadata.get(i).getStadistic().getSizeFacultad());
				setRowData(row, 7, listMetadata.get(i).getSource(),false);
				setRowData(row, 8, listMetadata.get(i).getTitle(),listMetadata.get(i).getStadistic().getSizeTitle());
				setRowData(row, 9, listMetadata.get(i).getPublisher(),false);
				setRowData(row, 10, listMetadata.get(i).getType(),false);
				setRowData(row, 11, listMetadata.get(i).getLanguage_iso(),false);
				setRowData(row, 12, listMetadata.get(i).getSubject(),listMetadata.get(i).getStadistic().getSizeKeyWords());
				setRowData(row, 13, listMetadata.get(i).getSegundaEspecialidad(),false);
				setRowData(row, 14, listMetadata.get(i).getDegreeName(),listMetadata.get(i).getStadistic().getSizeDegreeName());
				setRowData(row, 15, listMetadata.get(i).getEscuela(),listMetadata.get(i).getStadistic().getSizeEscuela());
			}
				
						
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	
	public void setRowData(XSSFRow row, int index ,String value, boolean observacion){

		XSSFCell cell = row.createCell(index);	
		if(value.equals(null) || value.equals("")){
			if(index==1 || index ==5){
				cell.setCellValue(value);
			}else{
				cell.setCellStyle(styleR);
				cell.setCellValue(value);
			}
		}else{
			if(value.equals("Fail")){
				cell.setCellStyle(styleP);
				cell.setCellValue(value);
			}else{
			
				if(observacion){
					cell.setCellStyle(styleA);
					cell.setCellValue(value);	
				}else{
					cell.setCellValue(value);
				}
			}
		}		
	}

}


