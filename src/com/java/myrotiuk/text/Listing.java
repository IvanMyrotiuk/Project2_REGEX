package com.java.myrotiuk.text;

/**
 * Class<code> Listing</code> for creating an instance of Listing from parsed
 * text
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public class Listing extends ElementOfText {

    /**
     * Field for storing a value of an instance
     */
	private String listing;
	
	/**
     * Parameterized constructor that has parameter
     *
     * @param nameOfListing for listing as listing
     * @param listing listing that will be created as an object
     */
	public Listing(String nameOfListing, String listing){
		super(nameOfListing);
		this.listing = listing;
	}
	
	/**
     * Method for getting a value of an instance of Listing
     *
     * @return value of an instance
     */
	public String getElement(){
		return this.toString();
	}
	
	public String toString() {
		return this.listing;
	}
}
