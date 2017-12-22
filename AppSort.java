package com.demo.hm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Hema
 *
 */
@Entity
@Table(name="APPSORT")
public class AppSort {

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Type( type = "int-array" )
    @Column(
        name = "unsorted_values", 
        columnDefinition = "integer[]"
    )
    private int[] unsortedValues;
	
	@Type( type = "int-array" )
    @Column(
        name = "sorted_values", 
        columnDefinition = "integer[]"
    )
    private int[] sortedValues;
	
	private int noOfSwap;
	
	public int getNoOfSwap() {
		return noOfSwap;
	}

	public void setNoOfSwap(int noOfSwap) {
		this.noOfSwap = noOfSwap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getUnsortedValues() {
		return unsortedValues;
	}

	public void setUnsortedValues(int[] unsortedValues) {
		this.unsortedValues = unsortedValues;
	}

	public int[] getSortedValues() {
		return sortedValues;
	}

	public void setSortedValues(int[] sortedValues) {
		this.sortedValues = sortedValues;
	}
	
	private String ResponseMessage;

	public String getResponseMessage() {
		return ResponseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		ResponseMessage = responseMessage;
	}
	

	@Override
	public String toString(){
		return "id="+id+",unsortedValues="+unsortedValues+",sortedValues="+sortedValues+",noOfSwap"+noOfSwap;
	}
}
