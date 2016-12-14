package com.unsa.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.unsa.entity.Facultad;
import com.unsa.model.ConnectionManager;

public class AlgorithmsController {
	private String pdf;
	private double probabilityFac;
	private int pagesDefaultKey = 17;
	private boolean headPage=false;
	private boolean footPage=false;
	
	public AlgorithmsController(String pdf) {
		// TODO Auto-generated constructor stub
		this.pdf = pdf;
	}



	// Autor de la tesis
	public String getCreator() {
		String pdfOriginal = pdf;
		// pasar a minuscula
		pdf = pdf.toLowerCase();
		pdf = removeCharSpecial(pdf);
		String[] rulesAuthor = { "magisteres:", "magisteres", "magister:",
				"magister", "maestros:", "maestros", "maestro:", "maestro",
				"autores:", "autores", "autor:", "autor", "bachilleres:",
				"bachilleres", "bachiller:", "bachiller",
				"presentado por el bach:", "presentado por el bach",
				"presentado por:", "presentado por","presentada por:","presentada por" };
		String[] rulesAuthor1 = { "para " };
		String valor = "";

		int countLine = 0;
		for (int i = 0; i < rulesAuthor.length; i++) {
			int index = pdf.indexOf(rulesAuthor[i]);
			if (index != -1) {

				String docpe = pdf.substring(index + rulesAuthor[i].length(),
						pdf.length() - 1);
				for (int j = 0; j < rulesAuthor1.length; j++) {
					int index2 = docpe.indexOf(rulesAuthor1[j]);
					if (index2 != -1) {
						for (int k = 0; k < index2; k++) {
							if (docpe.charAt(k) == '\n') {
								valor = valor + "/";

							} else {
								// varificacion de basura
								// Character.isLetter('~');
								if (Character.isLetter(docpe.charAt(k))
										|| docpe.charAt(k) == ' ') {
									valor = valor + docpe.charAt(k);
								}
								else {
									if (Character.isLetter(docpe.charAt(k))
											|| docpe.charAt(k) == 0) {
										valor = valor + docpe.charAt(k);
									}
								}
							}

						}
					}
				}
				break;
			}
		}
		return valor;
	}

	// recupera año
	public String getIssued() {
		pdf = pdf.toLowerCase();
		// String[] rulesAuthor
		// ={"peru","perú","junio","enero","sadi","_...----------,"};
		String[] rulesAuthor = { "1990", "1991", "1992", "1993", "1994",
				"1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002",
				"2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
				"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
				"2019", "2020", "2021", "2022", "2023" };
		String valor = "";
		int countLine = 0;

		String[] listaLineas = pdf.split("\n");
		if (listaLineas == null || listaLineas.length < 9) {
			return null;
		}

		for (int i = listaLineas.length - 1; i > listaLineas.length - 9; i--) {
			for (int j = 0; j < rulesAuthor.length; j++) {
				if (listaLineas[i].indexOf(rulesAuthor[j]) != -1) {
					return rulesAuthor[j];
				}
			}
		}
		return null;

	}
	
	public String getTitle(String[] ls){
		
		String valor="";
		String rules[]={"tesis","tomo","trabajo presentado", "presentada" , "presentado","informe tecnico","que para obtener","autor"};
		boolean flag = false;
		boolean flagtesis= false;
			for (int i = 0; i < ls.length; i++) {
				String ns=removeCharSpecial(ls[i].toLowerCase());
				//System.out.println(ns);
				if(i==0 ){					
					if(ns.equals("tesis") || ns.equals("tesis ") || ns.equals("tesis:") || ns.equals("tesis :")
						|| ns.equals("tesis : ") || ns.equals("tesis: ")){
						
						flagtesis=true;
					}				
				}
				
				if(flagtesis==false){
					
					for (int j = 0; j < rules.length; j++) {
						
							if(ns.indexOf(rules[j])!=-1){
								//System.out.println("ENCONTREEEE REGLAAA");
								if(ns.indexOf("informe tecnico:")!=-1 || ns.indexOf("informe tecnico :")!=-1 ){
									
								}else{

								j=rules.length+1;
								i=ls.length+1;
								flag =true;
								break;
								}
							}
						
					}
					if(ns.equals("titulo") || ns.equals("titulo ") || ns.equals("titulo: ")
							|| ns.equals("titulo :") || ns.equals("titulo : ")){
						
					}else{
							if(flag==false){
								valor=valor+" "+ls[i];
							}
						
					}
				}
				flagtesis=false;
			}
			
				
		
		return valor;
	}
	
	
	
	//Recupera el Asesor de la tesis
	public String getAuthor(){
		String[] rulesAuthor ={};
		String valor="";
		for(int i=0; i<rulesAuthor.length; i++){
		
		 int index = pdf.indexOf(rulesAuthor[i]);
		    for(int ind = index+rulesAuthor[i].length(); ind<pdf.length(); ind++){
		    	if(pdf.charAt(ind)=='\n'){
			 		break;
			 	}else{
			 		valor+=pdf.charAt(ind);
			 	}
		 	   
		    }
		    
		}
		
		return null;
	}
	
	//Recupera el Resumen de la tesis
	public String getAbstract(PdfBoxController pdfDoc){
		String valor ="";
		boolean nextPage =false;
		boolean readPage =false;
		boolean pageEnd =false;
		int pageActual =0;
		boolean isIndex = false;
		boolean flagIndex = false;
		
		String[] rules ={"resumen","sumario"};	
		for(int i=0; i<rules.length; i++){	
				
			for(int j=2; j<pagesDefaultKey; j++){
				String pdf_ori=pdfDoc.getText(j,j);
				String pdf_key=pdfDoc.getText(j,j).toLowerCase();
				String[] lineas = pdf_key.split("\n");
				String[] lineasOri = pdf_ori.split("\n");
				//flagIndex=false;
				//System.out.println("ESTOYYYYYYYYYYYYY PAGINAAAAAAAAAAAAAAAAAA "+ j );
				for(int k=0; k<lineas.length; k++){
					int index = lineas[k].indexOf(rules[i]);
					
					//System.out.println(lineas[k]);
					
					if(verifyIndex(pdf_key)){
					//	System.out.println(lineas[k]);
					//	System.out.println("encontreeeeeeeeeeeeeee indiceeeeeeeeeeeeeeee");
						k=lineas.length+1;
						break;
					}
					
					if(index!=-1 && lineas[k].length()<9 && k<=2){
						readPage=true;
						pageActual=j;
					//	System.out.println(lineas[k]);
					//	System.out.println("encnotreeeeeeeee resumen");
					}
					
					if(pageActual+1==j){
						nextPage=true;
					}
					
					if(readPage){
						//System.out.println("ENTREEEEEEEEEEEEEEEEEEEEEEe");
						//System.out.println(lineas[k]);
						if(verifyKeyWords(lineas[k])){
							//System.out.println("encontre palabra claveewwwwwwwwwwww");
							k=lineas.length+1;
							i=rules.length+1;
							j=pagesDefaultKey+1;
							break;
						}
						
						//System.out.println("linea:"+k+" "+lineas[k]);
						boolean h=isNotePageDoc(pdfDoc, j);
						boolean f=isNotePageFootDoc(pdfDoc, j);
						if(f==true && h==false){
							if(nextPage){
								
								if(verifyStamp(lineas[0])){
									pageEnd=true;
								}else{
								
									//pageEnd=lineas[0].equals("abstract");
									pageEnd=verifyAbstract(lineas[0]);
									String[] lp=lineas[0].split(" ");
									if(lp.length==1){
										pageEnd=true;
									};
								}
								if(pageEnd==true){
									k=lineas.length+1;
									i=rules.length+1;
									j=pagesDefaultKey+1;
									break;
								}
								
								
							}
							
							if(k!=lineas.length-1){
								valor+=lineasOri[k];
							}
						}
						if(f==true && h==true){
							if(nextPage){
								if(verifyStamp(lineas[0])){
									pageEnd=true;
								}else{

									//pageEnd=lineas[1].equals("abstract");
									pageEnd=verifyAbstract(lineas[1]);
									String[] lp=lineas[1].split(" ");
									if(lp.length==1){
										pageEnd=true;
									};
								}
									if(pageEnd==true){
										k=lineas.length+1;
										i=rules.length+1;
										j=pagesDefaultKey+1;
										break;
									}
								
								
							}
							
							
							if(k!=lineas.length-1 && k!=0){
								valor+=lineasOri[k];
							}
						}
						if(h==true && f==false){
							if(nextPage){
								if(verifyStamp(lineas[0])){
									pageEnd=true;
								}else{
								
									//pageEnd=lineas[1].equals("abstract");
									pageEnd=verifyAbstract(lineas[1]);
									String[] lp=lineas[1].split(" ");
									if(lp.length==1){
										pageEnd=true;
									};
								}
								if(pageEnd==true){
									k=lineas.length+1;
									i=rules.length+1;
									j=pagesDefaultKey+1;
									break;
								}	
								
							}
							
							if(k!=0){
								valor+=lineasOri[k];
							}
						}
						if(h==false && f==false){
							if(nextPage){
								if(verifyStamp(lineas[0])){
									pageEnd=true;
								}else{
									//pageEnd=lineas[0].equals("abstract");
									pageEnd=verifyAbstract(lineas[0]);
								
									
									String[] lp=lineas[0].split(" ");
									if(lp.length==1){
										pageEnd=true;
									};
								}
								if(pageEnd==true){
									k=lineas.length+1;
									i=rules.length+1;
									j=pagesDefaultKey+1;
									break;
								}	
							}
							
							valor+=lineasOri[k];
						}
					}
				}
				
			}
			
		}
		 
		return valor;
	}
	
	//Recupera el identificador interno de la tesis
	public String getIdentifier(){
		 
		return null;
	}
	
	//Recupera la Facultad de la tesis
	public String getDescription() throws SQLException{
		pdf=pdf.toLowerCase();
		String[] rulesAuthor ={"facultad"};
		String valor="";	
		String fac= "";
		//Pair
		
		
		int countLine =0;		
		for(int i=0; i<rulesAuthor.length; i++){		
		 int index = pdf.indexOf(rulesAuthor[i]);
		 
		 if(index!=-1){
		    for(int ind = index+rulesAuthor[i].length(); ind<pdf.length(); ind++){
		    	if(pdf.charAt(ind)=='\n'){
		    		
		    		double sim =0.0;
		    		double mayor =-999999.0;
		
		    		
		    		List<Facultad> lf =ConnectionManager.getFacultades(); 
		    		for (Facultad facultad : lf) {
		    			//System.out.println("pdf:"+valor.toLowerCase()+ "    Bd:"+facultad.getNombre().substring(8, facultad.getNombre().length()) );
		    			//
		    			sim=Algorithms.similarity(valor, facultad.getNombre().substring(8,facultad.getNombre().length()));
		    			
		    			//System.out.println(sim);
		    			//System.out.println("SIM: "+sim+"pdf:"+valor+ "-    Bd:"+facultad.getNombre().substring(8, facultad.getNombre().length())+"-");
		    			if(sim>mayor){
		    				mayor=sim;
		    				fac=facultad.getNombre();
		    				probabilityFac=mayor;
		    			}
		    		}
		    		if(sim<0.25){
		    			if(countLine ==1){
			    			break;	
			    		}
			    		countLine++;
		    			
		    		}else{
		    			break;
		    			
		    		}
		    		
			 		
			 	}else{
			 		valor+=pdf.charAt(ind);
			 	}
		 	   
		    }
		}
		   // System.out.println(valor);
		    
		}
		
		
		
		return fac;

	}
	
	//Recupera las palabras claves de la tesis
	public String getSubject(PdfBoxController pdfDoc) throws FileNotFoundException, IOException{
		
	for(int j=2; j<pagesDefaultKey; j++){
			
		String pdf_key=pdfDoc.getText(j,j).toLowerCase();
		
		String[] rules ={"palabras clave","palabra clave"};		
		
		String valor="";	
		String valorFinal="";
		boolean flag =false;
		
		int countLine =0;
		int ind_flag=0;
		for(int i=0; i<rules.length; i++){		
		int index = pdf_key.indexOf(rules[i]);
		 
		if(index!=-1){
			int ind_s =  index+rules[i].length();

	    	if(pdf_key.charAt(ind_s)=='s'){
	    		ind_flag=ind_s+1;
	    	}else{
	    		ind_flag=ind_s;
	    	}
			
		    for(int ind =ind_flag; ind<pdf_key.length(); ind++){
		    	
		    	if(pdf_key.charAt(ind)!=':'){
		    		if(pdf_key.charAt(ind)=='\n'){	
		    			flag=true;
		    			if(countLine ==1){
		    				
			    			break;	
			    		}
		    			
			    		countLine++;
		    			
		    		}else{
				 		
				 		if(flag){
				 			if(StringUtils.isNumeric(valorFinal) || valorFinal.length()<=2){
				 				
				 			}else{
				 			valorFinal+=pdf_key.charAt(ind);
				 			}
				 		}else{
				 			valor+=pdf_key.charAt(ind);	
				 		}
				 	}
		    	}	 	   
		    }
		    System.out.println("valorFinal:" + valorFinal);
		    if(!isDigit(valorFinal)){
		    	return valor+valorFinal;
		    }else{
		    	return valor;
		    }
		    	
		}

		    
		}
		
		
		
		}
		
		return null;
 
	
    }
	
	
	public double getProbabilityDescription(){
		return probabilityFac;
	}
	
	public boolean isDigit(String s){
		String d="";
		String[] lsRoman = {"i","ii","iii","iv","v","vi","vii","viii","ix","x","xi","xii","xiii"
							,"xiv","xv"};
		for(int i=0; i<lsRoman.length; i++){
			if(s.equals(lsRoman[i])){
				return true;
			}
		}
		
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i)!=' '){
				d+=s.charAt(i);
			}
		}
		CharSequence c= d;	
		
		//System.out.println("digit:"+c);
		//System.out.println("numeric:"+StringUtils.isNumeric(c));
		return StringUtils.isNumeric(c);
	}
	
	public boolean isNotePageDoc(PdfBoxController pdfDoc, int page){
		boolean hp = false;
		for(int j=page; j<page+1; j++){
			String pdf_key=pdfDoc.getText(j,j).toLowerCase();					
			String[] lineas = pdf_key.split("\n");			
			if(lineas[0].length()<=3){
				hp = true;
			}else{
				hp=false;
				break;
			}
		}
		if(hp==true){
			return true;
		}

		String pdfSiguiente=pdfDoc.getText(page+1,page+1).toLowerCase();
		String pdfActual=pdfDoc.getText(page,page).toLowerCase();
		String[] lineasSiguiente = pdfSiguiente.split("\n");
		String[] lineasActual = pdfActual.split("\n");
		double sim =Algorithms.similarity(lineasActual[0], lineasSiguiente[0]);
		if(sim>0.7){
			hp = true;
		}else{
			hp = false;
		}
		
		return hp;
	}
	
	public boolean isNotePageFootDoc(PdfBoxController pdfDoc, int page){
		boolean fp = false;
		for(int j=page; j<page+1; j++){
			String pdf_key=pdfDoc.getText(j,j).toLowerCase();					
			String[] lineas = pdf_key.split("\n");
			int ultimaLinea = lineas.length-1;
			if(lineas[ultimaLinea].length()<=3){
				fp = true;
			}else{
				fp=false;
				break;
			}
		}
		if(fp==true){
			return true;
		}

		String pdfSiguiente=pdfDoc.getText(page+1,page+1).toLowerCase();
		String pdfActual=pdfDoc.getText(page,page).toLowerCase();
		String[] lineasSiguiente = pdfSiguiente.split("\n");
		String[] lineasActual = pdfActual.split("\n");
		
		int ultimaLineaS = lineasSiguiente.length-1;
		int ultimaLineaA = lineasActual.length-1;
		double sim =Algorithms.similarity(lineasActual[ultimaLineaA], lineasSiguiente[ultimaLineaS]);
		if(sim>0.7){
			fp = true;
		}else{
			fp = false;
		}
		
		return fp;
	}
	
	public boolean isTitleInThePage(){
		
		return true;
	}
	public static String removeCharSpecial(String input) {

		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}
	
	public boolean verifyStamp(String s){
		String[] lStamp = {"propiedad biblioteca"};
		for (int i = 0; i < lStamp.length; i++) {
			int index=s.indexOf(lStamp[i]);
			if(index!=-1){
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyIndex(String s){
		
		String[] sn=s.split("\n");
		for(int i=0; i<5; i++){
			String sr =removeCharSpecial(sn[i]);
			if(sr.indexOf("indice")!=-1){
				return true;
			}
		}		
		return false;
	}
	
	public boolean verifyAbstract(String s){
		String sr=removeCharSpecial(s);
		String [] ls = {"abstract", "capitulo"};
		for(int i=0; i<ls.length; i++){
			if(sr.indexOf(ls[i])!=-1){
				return true;
			}
		}
		return false;
		
		/*if(s.indexOf("abstract")!=-1){
			return true;
		}else{
			return false;
		}*/
	}
	
	public boolean verifyKeyWords(String s){
		String[] lk = {"palabra clave", "palabras clave"};
		for(int i=0; i<lk.length; i++){
			if(s.indexOf(lk[i])!=-1){
				return true;
			}
		}
		return false;
	}
	
}
