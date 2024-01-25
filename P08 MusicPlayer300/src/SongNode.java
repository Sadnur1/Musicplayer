//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongNode
// Course:   CS 300 Fall 2022
//
// Author:   (Sadiq Nur)
// Email:    (snur2@wisc.edu)
// Lecturer: (Hobbes LeGault)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Zakaria Nur
// Partner Email:   znur2@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x_ Write-up states that pair programming is allowed for this assignment.
//   __x_ We have both read and understand the course Pair Programming Policy.
//   __x_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         CS building help, dont remember name
// Online Sources:  Piazza, helped me figure out whats wrong with my getHead method
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class SongNode {
	private SongNode next;
	private Song song;
	/** 
	 * Constructs a single SongNode containing the given data, not linked to any other SongNodes
	 *
	 * @param data - the Song for this node
	 */
	public SongNode(Song data){
		if(data == null) {
			throw new IllegalArgumentException("Data is Null");
		}
		this.song = data;
		this.next = null;
	}
	/**
	 * Constructs a single SongNode containing the given data, linked to the specified SongNode
	 * @param data - the Song for this node
	 * @param next - the next node in the queue
	 */
	public SongNode(Song data, SongNode next){
		if(data == null) {
			throw new IllegalArgumentException("Data is Null");
		}
		this.song = data;
		this.next = next;
	}
	/**
	 * Accessor method for this node's data
	 * 
	 * @return - the Song in this node
	 */
	public Song getSong() {
		return this.song;
	}
	/**
	 * Accessor method for the next node in the queue
	 * 
	 * @return the SongNode following this one, if any
	 */
	public SongNode getNext() {
		return this.next;
	}
	/**
	 * Changes the value of this SongNode's next data field to the given value
	 * 
	 * @param next - the SongNode to follow this one; may be null
	 */
	public void setNext(SongNode next) {
		this.next = next;
	}
}
