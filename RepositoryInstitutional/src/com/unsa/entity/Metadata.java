package com.unsa.entity;

public class Metadata {
	
	private String author;
	private String creator;
	private String issued;
	private String abstract_doc;
	private String other;
	private String description;
	private String source;
	private String title;
	private String publisher;
	private String type;
	private String language_iso;
	private String subject;	
	private String escuela;
	private String fileName;
	private String advisor;
	private String uri_description;
	//private String uri_identifier;
	private String uri_rights;
	private String rights;
	private String level;
	private String degreeName;
	private String grantor;
	private String discipline;
	private String segundaEspecialidad;
	
	
	private Estadistica stadistic;
	
	public  Metadata(){
		
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getIssued() {
		return issued;
	}
	public void setIssued(String issued) {
		this.issued = issued;
	}
	public String getAbstract_doc() {
		return abstract_doc;
	}
	public void setAbstract_doc(String abstract_doc) {
		this.abstract_doc = abstract_doc;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLanguage_iso() {
		return language_iso;
	}
	public void setLanguage_iso(String language_iso) {
		this.language_iso = language_iso;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Estadistica getStadistic() {
		return stadistic;
	}

	public void setStadistic(Estadistica stadistic) {
		this.stadistic = stadistic;
	}

	public String getEscuela() {
		return escuela;
	}

	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
	
	
	public boolean getObservacionGeneral(){
		return abstract_doc.equals("") || title.equals("") || subject.equals("") || creator.equals("") || description.equals("") || issued.equals("")? true:false;
	}
	
	public boolean getFailGeneral(){
		return abstract_doc.equals("Fail") || title.equals("Fail") || subject.equals("Fail") || creator.equals("Fail") || description.equals("Fail") || issued.equals("Fail")? true:false;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public String getUri_description() {
		return uri_description;
	}

	public void setUri_description(String uri_description) {
		this.uri_description = uri_description;
	}

	/*public String getUri_identifier() {
		return uri_identifier;
	}

	public void setUri_identifier(String uri_identifier) {
		this.uri_identifier = uri_identifier;
	}*/

	public String getUri_rights() {
		return uri_rights;
	}

	public void setUri_rights(String uri_rights) {
		this.uri_rights = uri_rights;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String name) {
		this.degreeName = name;
	}

	public String getGrantor() {
		return grantor;
	}

	public void setGrantor(String grantor) {
		this.grantor = grantor;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getSegundaEspecialidad() {
		return segundaEspecialidad;
	}

	public void setSegundaEspecialidad(String segundaEspecialidad) {
		this.segundaEspecialidad = segundaEspecialidad;
	}
	
	

}
