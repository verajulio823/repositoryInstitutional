package com.unsa.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import org.apache.commons.lang3.text.WordUtils;
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
		String value="";
		
		String[] rules = {"2010","2011","2012","2013","2014","2015","2016","2017","2018"};
		for(int i=posicionParrafo; i<NUM_PARRAFO_PROMEDIO; i++){
			for(int j=0; j<rules.length; j++){
				int index = paragraphs.get(i).getText().indexOf(rules[j]);
				if(index!=-1){
					value = rules[j];
				}
			}			
		}
		
		return value;
	}
	
	public List<String> getCreator(){
		
		List<String> autores = new ArrayList<String>();
		int numAutores=0;
		//System.out.println("NUMERO PARRAFOOOOOOOOOOOOOOOO: "+posicionParrafo);
		String[] rulesAuthor = {"magisteres:", "magisteres", "magister:",
				"magister", "maestros:", "maestros", "maestro:", "maestro",
				"autores:", "autores", "autor:", "autor", "bachilleres:",
				"bachilleres", "bachiller:", "bachiller",
				"licenciadas :","licenciadas: ", "licenciada :","licenciada:","licenciada",
				"licenciados :","licenciados:", "licenciado :","licenciado:", "licenciado", 
				"presentado por el bach:", "presentado por el bach",
				"presentado por:", "presentado por","presentada por:","presentada por" };
		String[] rulesAuthor1 = { "para " };
		for(int i=posicionParrafo+1; i<NUM_PARRAFO_PROMEDIO; i++){
			for(int j=0; j<rulesAuthor.length; j++){
				int index = paragraphs.get(i).getText().toLowerCase().indexOf(rulesAuthor[j]);
				if(index!=-1){
				//	System.out.println("ENCONTREEEEEEEEE TESISSSSSSSSSSSS ");
					int index_final = paragraphs.get(i).getText().toLowerCase().indexOf("para obtener");
					if(index_final!=-1){
						String evaluar = paragraphs.get(i).getText().toLowerCase();
						if(index+rulesAuthor[j].length()>index_final){
							
						}else{
							String val_final =evaluar.substring(index+rulesAuthor[j].length(),index_final).trim();
							int index_y = val_final.indexOf(" y ");
							if(index_y!=-1){
								autores.add(val_final.substring(0,index_y));
								autores.add(val_final.substring(index_y+3,val_final.length()));
								return autores;
							}else{
								autores.add(val_final);
								return autores;
							}
						}
					}
					
					for(int k=i+1; k< paragraphs.size(); k++){
						//System.out.println("BUSCNADO AUTORESSSSSSSSSSS");
						if(!paragraphs.get(k).getText().trim().equals("")){
							//System.out.println("ECNCONTREEE AUTORRRRR");
							if(paragraphs.get(k).getText().toLowerCase().indexOf("para ")!=-1
									|| paragraphs.get(k).getText().toLowerCase().indexOf("para\t")!=-1
									|| paragraphs.get(k).getText().toLowerCase().indexOf("director de")!=-1)
							{
								return autores;
							}
							if(numAutores==2){
								return autores;
							}
							
							//System.out.println("autorrrrrrrrrrrrrrrrrr "+ paragraphs.get(k).getText());
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
				//System.out.println("ENCONTRE");
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
				String text_clear =paragraphs.get(i).getText().toLowerCase();
				String n_text = text_clear.substring(index, text_clear.length());
				
				return clearStringDegreeSchool(n_text);				
			}
		}
		return "";
	}
	
	
	public String getSegundaEspecialidad(){
		for(int i=0; i<NUM_PARRAFO_PROMEDIO; i++){
			int index=paragraphs.get(i).getText().toLowerCase().indexOf("segunda especialidad");
			if(index!=-1){
				return "Segunda Especialidad";				
			}
		}
		return "";
	}
	
	public String getDegreeName(){
		String[] rules ={"titulo profesional de","grado academico de", "titulo de la","titulo de"};
		
		for(int i=0; i<NUM_PARRAFO_PROMEDIO; i++){
			for(int j=0; j<rules.length; j++){
				String texto_clear = removeCharSpecial(paragraphs.get(i).getText().toLowerCase().trim());
				int index = texto_clear.indexOf(rules[j]);
				if(index!=-1){
					int index_final = texto_clear.length();
					int index_inicial = index+rules[j].length()+3; 
					if(index_final>=index_inicial){							
						return clearStringDegree(paragraphs.get(i).getText().toLowerCase().substring(index+ rules[j].length(), index_final)); 
					}else{
						return clearStringDegree(paragraphs.get(i+1).getText().toLowerCase());
					}
					
				}
			}
			
		}
		
		String unico="";
		for(int i=0; i<NUM_PARRAFO_PROMEDIO; i++){
			unico+= " "+paragraphs.get(i).getText();
		}
		
		unico= unico.replaceAll("\n", "");
		unico= unico.replaceAll("\t", "");
		
		unico=unico.trim();
		
		//System.out.println(unico);
		for(int j=0; j<rules.length; j++){
			int index =unico.indexOf(rules[j]);
		//	System.out.println("ENTREEE ACAAAA"+ index);
			if(index!=-1){
				int index_final =indexDifuse(unico);
				if(index_final !=-1){
					if(index_final>index){
						return clearStringDegree(unico.toLowerCase().substring(index+rules[j].length(), index_final));
					}
					
				}
			}
			 
		}
		
		
		return "";
	}
	
	public int indexDifuse(String a){
		a=a.trim();
		
		a=removeCharSpecial(a);
		
		String[] rules = {" - peru","- peru"," -peru","-peru"};
		for(int i=0; i<rules.length; i++){
			int index = a.indexOf(rules[i]);
			if(index!=-1){
				for(int j=index-1; j>0; j--){
					if(a.charAt(j)==' '){
						return j;
					}
				}
			}
			
		}
		return -1;
	}
	
	public String clearStringDegreeSchool(String s){
		String[] rules = {" De ", " La ", " Los "," El ", " Y ", " En "};
		String[] rules_change = {" de ", " la ", " los "," el ", " y ", " en "};
		s= s.replaceAll("escuela", "");
		s= s.replaceAll("profesional de", "");
		s= s.replaceAll("profesional", "");
		String capitalize =WordUtils.capitalize(s);
		
		//capitalize = capitalize.replaceAll("\t", " ");
		for(int i=0; i<rules.length; i++){
			capitalize =capitalize.replaceAll(rules[i], rules_change[i]);
		}
		
		capitalize=capitalize.trim();
		String result ="";
		for(int i=0; i<capitalize.length(); i++){
			if(Character.isLetter(capitalize.charAt(i)) || capitalize.charAt(i) ==' '){
				result+=capitalize.charAt(i);
			}
		}
		
		
		
		return result.trim();
	}
	
	public String clearStringDegree(String s){
		String[] rules = {" De ", " La ", " Los "," El ", " Y ", " En "};
		String[] rules_change = {" de ", " la ", " los "," el ", " y ", " en "};
		String capitalize =WordUtils.capitalize(s);
		//capitalize = capitalize.replaceAll("\t", " ");
		for(int i=0; i<rules.length; i++){
			capitalize =capitalize.replaceAll(rules[i], rules_change[i]);
		}
		
		capitalize=capitalize.trim();
		String result ="";
		for(int i=0; i<capitalize.length(); i++){
			if(Character.isLetter(capitalize.charAt(i)) || capitalize.charAt(i) ==' '){
				result+=capitalize.charAt(i);
			}
		}
		
		return result.trim();
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
						for(int k=0; k<i; k++){
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
					if(sp.trim().indexOf("resumen y palabras clave")!=-1){
						
					}else{
					
						//System.out.println("IND_S_ "+ind_s+" "+sp.length());
						if(sp.length()<=ind_s){
							for(int k=i+1; k<i+3; k++){
								if(!paragraphs.get(k).getText().trim().equals("")){
									return paragraphs.get(k).getText().trim();
								}
							}
						}
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
