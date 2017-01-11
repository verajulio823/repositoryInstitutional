package com.unsa.entity;

public class Estadistica {

	
	public int NUMC_DEFAULT_ABSTRACT = 5000;
	public int NUMC_DEFAULT_TITLE = 300;
	public int NUMC_DEFAULT_AUTHOR = 100;
	public int NUMC_DEFAULT_KEYWORDS = 300;
	public int NUMC_DEFAULT_FACULTAD = 80;
	public int NUMC_DEFAULT_ESCUELA = 80;
	public int NUMC_DEFAULT_ESPECIALIDAD = 80;
	
	private boolean sizeTitle;
	private boolean sizeAutors;
	private boolean sizeAbstract;
	private boolean sizeKeyWords;
	private boolean sizeFacultad;
	private boolean sizeEscuela;
	private boolean sizeSegundaEsp;
	private boolean sizeDegreeName;
	
	
	public boolean getSizeTitle() {
		return sizeTitle;
	}

	public void setSizeTitle(int sTitle) {
		if(sTitle>NUMC_DEFAULT_TITLE || sTitle< 15){
			sizeTitle =true;
		}else{
			sizeTitle = false;
		}
	}

	
	public boolean getSizeAutors() {
		return sizeAutors;
	}
	public void setSizeAutors(int sAutors) {
		if(sAutors>NUMC_DEFAULT_AUTHOR){
			sizeAutors =true;
		}else{
			sizeAutors = false;
		}
	}
	public boolean getSizeAbstract() {
		return sizeAbstract;
	}
	public void setSizeAbstract(int sAbstract) {
		if(sAbstract>NUMC_DEFAULT_ABSTRACT){
			sizeAbstract =true;
		}else{
			sizeAbstract = false;
		}
	}
	public boolean getSizeKeyWords() {
		return sizeKeyWords;
	}
	public void setSizeKeyWords(int sKeyWords) {
		if(sKeyWords>NUMC_DEFAULT_KEYWORDS){
			sizeKeyWords=true;
		}else{
			sizeKeyWords= false;
		}
	}
	public boolean getSizeFacultad() {
		return sizeFacultad;
	}
	public void setSizeFacultad(int sFacultad) {
		if(sFacultad>NUMC_DEFAULT_FACULTAD){
			sizeFacultad =true;
		}else{
			sizeFacultad = false;
		}
	}
	public boolean getSizeEscuela() {
		return sizeEscuela;
	}
	public void setSizeEscuela(int sEscuela) {
		if(sEscuela>NUMC_DEFAULT_ESCUELA){
			sizeEscuela=true;
		}else{
			sizeEscuela = false;
		}
	}
	public boolean getSizeSegundaEsp() {
		return sizeSegundaEsp;
	}
	public void setSizeSegundaEsp(int sSegundaEsp) {
		if(sSegundaEsp>NUMC_DEFAULT_ESPECIALIDAD){
			sizeSegundaEsp =true;
		}else{
			sizeSegundaEsp = false;
		}
	}
	
	public boolean getObservationGeneral(){
		return sizeAbstract || sizeAutors || sizeFacultad || sizeKeyWords || sizeTitle ? true:false;
	}

	public boolean getSizeDegreeName() {
		return sizeDegreeName;
	}

	public void setSizeDegreeName(int sDegreeName) {
		if(sDegreeName < 3){
			sizeDegreeName = true;
		}else{
			sizeDegreeName = false;
		}
				
	}
	
	

	
}
