package com.ui.dto;

public class UpdateComputerDTO {
    private String id;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    private String name;
    private String introduced;
    private String discontinued;
    private String mannufacturer;

    public String getDiscontinued() {
	return discontinued;
    }

    public String getIntroduced() {
	return introduced;
    }

    public String getMannufacturer() {
	return mannufacturer;
    }

    public String getName() {
	return name;
    }

    public void setDiscontinued(String discontinued) {
	this.discontinued = discontinued;
    }

    public void setIntroduced(String introduced) {
	this.introduced = introduced;
    }

    public void setMannufacturer(String mannufacturer) {
	this.mannufacturer = mannufacturer;
    }

    public void setName(String name) {
	this.name = name;
    }
}
