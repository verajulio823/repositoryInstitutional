package com.unsa.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//import org.apache.lucene.util.automaton.SortedIntSet.FrozenIntSet;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.unsa.entity.Estadistica;
import com.unsa.entity.Facultad;
import com.unsa.model.ConnectionManager;


public class AlgorithmsWord {
	String parrafo;
	//public Estadistica stadistic = new Estadistica();
	//List<XWPFParagraph> paragraphs;
	List<MyParagraph> paragraphs;
	
	int posicionParrafo=0;
	int NUM_PARRAFO_PROMEDIO=35;
	int NUM_INICIO_TESIS=15;
	int NUM_PAGE_DEFAULT= 17;
	
	
	public AlgorithmsWord(List<XWPFParagraph> paragraphs) {
		// TODO Auto-generated constructor stub
		this.paragraphs = new ArrayList<MyParagraph>();
		for (int i = 0; i < paragraphs.size(); i++) {
			MyParagraph myp = new MyParagraph();
			myp.setText(paragraphs.get(i).getText());
			this.paragraphs.add(myp);
		}
		
		//this.paragraphs=paragraphs;
	}
	public AlgorithmsWord(Range r){
		this.paragraphs = new ArrayList<MyParagraph>();
		
		for (int i = 0; i < r.numParagraphs(); i++) {
			MyParagraph myp = new MyParagraph();
			myp.setText(r.getParagraph(i).text());
			this.paragraphs.add(myp);
		}
		
	}


	
	public String getTitle(){
		for(int i=0; i<NUM_PARRAFO_PROMEDIO; i++){
			int index = paragraphs.get(i).getText().toLowerCase().indexOf("facultad");
			if(index!=-1){
				
				for(int j=i+1; j< paragraphs.size(); j++){
					if(!paragraphs.get(j).getText().trim().equals("")){
						int index2= paragraphs.get(j).getText().toLowerCase().indexOf("escuela");
						if(index2!=-1){							
				
							for(int k=j+1; k<paragraphs.size(); k++){
								if(!paragraphs.get(k).getText().trim().equals("")){
									
									if(verifySpecialization(paragraphs.get(k).getText())){
										for(int l=k+1; l<paragraphs.size(); l++){
											if(!paragraphs.get(l).getText().trim().equals("")){
												posicionParrafo =l;
												return paragraphs.get(l).getText();
											}
										}
									}
									
									posicionParrafo =k;
									return paragraphs.get(k).getText();
								}
							}
							
						}else{
							if(verifySpecialization(paragraphs.get(j).getText())){
								for(int k=j+1; k<paragraphs.size(); k++){
									if(!paragraphs.get(k).getText().trim().equals("")){
										posicionParrafo =k;
										return paragraphs.get(k).getText();
									}
								}
							}
							posicionParrafo=j;
							return paragraphs.get(j).getText();
						}
					}
				}
				
			}
			
		}
		return "";
	}
	
	public boolean verifySpecialization(String s){
		int uni = s.toLowerCase().indexOf("unidad de");
		int esp = s.toLowerCase().indexOf("especialidad");
		int post = s.toLowerCase().indexOf("postgrado");
		int postg = s.toLowerCase().indexOf("post-grado");
		if(uni!=-1){
			if(esp!=-1 || post !=-1 || postg !=-1){
				return true;
			}
		}
			return false;
		
	}
	
	public String getIssued(){
		
		String[] rules = {"2010","2011","2012","2013","2014","2015","2016","2017","2018"};
		for(int i=posicionParrafo; i<NUM_PARRAFO_PROMEDIO; i++){
			for(int j=0; j<rules.length; j++){
				int index = paragraphs.get(i).getText().indexOf(rules[j]);
				if(index!=-1){
					return rules[j];
				}
			}			
		}
		
		return "";
	}
	
	public List<String> getCreator(){
		
		List<String> autores = new ArrayList<String>();
		int numAutores=0;
		//System.out.println("NUMERO PARRAFOOOOOOOOOOOOOOOO: "+posicionParrafo);
		String[] rulesAuthor = {"tesis" ,"magisteres:", "magisteres", "magister:",
				"magister", "maestros:", "maestros", "maestro:", "maestro",
				"autores:", "autores", "autor:", "autor", "bachilleres:",
				"bachilleres", "bachiller:", "bachiller",
				"presentado por el bach:", "presentado por el bach",
				"presentado por:", "presentado por","presentada por:","presentada por" };
		String[] rulesAuthor1 = { "para " };
		for(int i=posicionParrafo+1; i<NUM_PARRAFO_PROMEDIO; i++){
			for(int j=0; j<rulesAuthor.length; j++){
				int index = paragraphs.get(i).getText().toLowerCase().indexOf(rulesAuthor[j]);
				if(index!=-1){
				//	System.out.println("ENCONTREEEEEEEEE TESISSSSSSSSSSSS ");
					for(int k=i+1; k< paragraphs.size(); k++){
					//	System.out.println("BUSCNADO AUTORESSSSSSSSSSS");
						if(!paragraphs.get(k).getText().trim().equals("")){
						//	System.out.println("ECNCONTREEE AUTORRRRR");
							if(paragraphs.get(k).getText().toLowerCase().indexOf("para ")!=-1)
							{
								return autores;
							}
							if(numAutores==2){
								return autores;
							}
//							System.out.println("autorrrrrrrrrrrrrrrrrr "+ paragraphs.get(k).getText());
							autores.add(paragraphs.get(k).getText());
							numAutores++;
							
						}
						
					}
					
				}
			}
		}

		
		return null;
	}
	
	public String getAuthor(){
		return null;
	}
	
	public String getDescription() throws SQLException{
		String fac="";
		
		for(int i=0; i<NUM_PARRAFO_PROMEDIO; i++){
			int index=paragraphs.get(i).getText().toLowerCase().indexOf("facultad");
			if(index!=-1){
				String sp=paragraphs.get(i).getText().toLowerCase();
				String valor="";
				int index_school = sp.indexOf("escuela");
				if(index_school!=-1){
					valor=sp.substring(index+8, index_school);
				}else{
					valor=sp.substring(index+8, sp.length());	
				}
				
				double sim =0.0;
	    		double mayor =-999999.0;
	    		List<Facultad> lf =ConnectionManager.getFacultades(); 
	    		for (Facultad facultad : lf) {
	    			
	    			sim=Algorithms.similarity(valor, facultad.getNombre().substring(8,facultad.getNombre().length()));
	    			if(sim>mayor){
	    				mayor=sim;
	    				fac=facultad.getNombre();
	    				
	    			}
	    		}
	    		
				return fac;				
			}
		}
		return "";
	}
	
	public String getDescriptionOptional(){
		String valor;
		String valor_final;
		for(int i=0; i<NUM_PARRAFO_PROMEDIO; i++){
			int index=paragraphs.get(i).getText().toLowerCase().indexOf("facultad");
			if(index!=-1){
				valor=paragraphs.get(i).getText().substring(index,paragraphs.get(i).getText().length()).trim();
				int index_school = valor.toLowerCase().indexOf("escuela");
				if(index_school!=-1){
					valor_final=valor.substring(0, index_school);
				}else{
					valor_final=valor.substring(0, valor.length());	
				}
				
				return valor_final;				
			}
		}
		return "";		
	}
	
	public String getSchool(){
		for(int i=0; i<NUM_PARRAFO_PROMEDIO; i++){
			int index=paragraphs.get(i).getText().toLowerCase().indexOf("escuela");
			if(index!=-1){
				return paragraphs.get(i).getText();				
			}
		}
		return "";
	}
	
	public String getAbstract(){
		String[] rules ={"resumen","sumario"};
		int pages = NUM_PARRAFO_PROMEDIO*NUM_PAGE_DEFAULT;
		int total_para=paragraphs.size();
		if(pages> total_para){
			pages = total_para-1;
		}
		boolean titulo_indice= false;
		String valor_parrafo="";
		int line_white=0;
		
		for(int i=0; i<pages; i++){
			for(int j=0; j<rules.length; j++){
				int index = paragraphs.get(i).getText().toLowerCase().indexOf(rules[j]);
				String sparrafo = paragraphs.get(i).getText().toLowerCase();
				if(index!=-1){
					if(sparrafo.trim().equals(rules[j])){
						for(int k=0; k<10; k++){
							String sp =paragraphs.get(i-k).getText().toLowerCase();
							String spe=removeCharSpecial(sp);
							int index_indice=spe.indexOf("indice");
							if(index_indice!=-1){
								if(spe.trim().equals("indice")){
									titulo_indice=true;
								}
							}
						}
						if(titulo_indice==true){
							
						}else{
							for(int next_p=i+1; next_p<pages; next_p++){
								String v_p = paragraphs.get(next_p).getText();
								String v_o=v_p;
								if(verifyKeyWords(v_o.toLowerCase())==false){
									
									//System.out.println("COMPROBACION: "+removeCharSpecial(v_o.toLowerCase()).indexOf("capitulo") + " COMPROBACIÓN 2 : "+ v_o.toLowerCase().indexOf("abstract") +" MIERDA ESTAS HABLANDO VERGA: "+v_o);
									 //Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
								      //  entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
									if(removeCharSpecial(v_o.toLowerCase()).indexOf("capitulo")==-1 
											&& v_o.toLowerCase().indexOf("abstract")==-1){
										//System.out.println("OSEA TE VALIO VERGASSSSSSSSSSSSSSSSSS");
										if(v_p.trim().equals("")){
											line_white++;
										}else{
											valor_parrafo=valor_parrafo+v_p;	
										}
										
									}else{
										//System.out.println("ENTREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEeeeee");
										if(v_o.toLowerCase().trim().equals("abstract")){
											return valor_parrafo;
										}
										if(v_o.trim().length()> "capitulo".length()+10){
										//	System.out.println("TAMAÑOOOOOOOOOOOOOOOOOOOO: "+ v_o.trim().length() +" xxxxxx "+"capitulo".length()+10);
											valor_parrafo=valor_parrafo+v_p;
										}else{
											return valor_parrafo;
										}
											
									}									
									
								}else{
									 return valor_parrafo;
								}
								
							}
						}
					}
				}
			}
		}
		
		return valor_parrafo;
	}
	
	public String getSubject(){
		String[] rules ={"palabras clave","palabra clave"};
		int ind_flag=0;
		String palabras_claves="";
		int pages = NUM_PARRAFO_PROMEDIO*NUM_PAGE_DEFAULT;
		int total_para=paragraphs.size();
		if(pages> total_para){
			pages = total_para-1;
		}
		
		for(int i=0; i<pages; i++)  {
			for(int j=0; j<rules.length; j++){
				int index = paragraphs.get(i).getText().toLowerCase().indexOf(rules[j]);
				int ind_s =  index+rules[j].length();
				if(index!=-1){
					String sp = paragraphs.get(i).getText().toLowerCase();
					if(sp.charAt(ind_s)=='s'){
						ind_flag=ind_s+1;
					}else{
			    		ind_flag=ind_s;
			    	}
					for(int k=ind_flag; k<sp.length(); k++){
						if(sp.charAt(k)!=':'){
							palabras_claves=palabras_claves+sp.charAt(k);
						}
					}
					
				}
				
			}
		}
		
		return palabras_claves.trim();
		
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
	
	public static String removeCharSpecial(String input) {

		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}

}
