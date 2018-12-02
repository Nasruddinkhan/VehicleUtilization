package com.project.millatinventory.serviceimpl;

import java.util.List;

public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String exception="";
	List<String> list=null;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public InvalidDataException() {
		
	}
	public InvalidDataException(String exception) {
		this.exception=exception;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return exception;
	}
}
