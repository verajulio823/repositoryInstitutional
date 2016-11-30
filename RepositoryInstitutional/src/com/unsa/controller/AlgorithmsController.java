package com.unsa.controller;

public class AlgorithmsController {
	private String pdf;
	
	public AlgorithmsController(String pdf) {
		// TODO Auto-generated constructor stub
		this.pdf = pdf;
	}

	public String getCreator(){
		
		return null;
	}
	
	public String getIssued(){
		
		return null;
	}
	
	public String getTitle(){
		
		return null;
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
	public String getAbstract(){
		 
		return null;
	}
	
	//Recupera el identificador interno de la tesis
	public String getIdentifier(){
		 
		return null;
	}
	
	//Recupera la Facultad de la tesis
	public String getDescription(){
		pdf=pdf.toLowerCase();
		String[] rulesAuthor ={"facultad"};
		String valor="";
		
		String palabra = "escuela";
		
		int countLine =0;		
		for(int i=0; i<rulesAuthor.length; i++){		
		 int index = pdf.indexOf(rulesAuthor[i]);
		    for(int ind = index+rulesAuthor[i].length(); ind<pdf.length(); ind++){
		    	if(pdf.charAt(ind)=='\n'){
		    		
		    		if(countLine ==1){
		    			break;	
		    		}
		    		countLine++;
			 		
			 	}else{
			 		valor+=pdf.charAt(ind);
			 	}
		 	   
		    }
		   // System.out.println(valor);
		    
		}
		 
		return "Facultad"+ valor;
	}
	
	//Recupera las palabras claves de la tesis
	public String getSubject(){
		 
		return null;
	}
	
}
