//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Playlist
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
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Playlist implements QueueADT<Song> {
	private SongNode first; //The current first song in the queue; the next one up to play (front of the queue)
	private SongNode last; //The current last song in the queue; the most-recently added one (back of the queue)
	private int numSongs; //The number of songs currently in the queue
	/**
	 * Constructs a new, empty playlist queue
	 */
	public Playlist(){
		this.first = null;
		this.last = null;
		
	}
	/**
	 * Adds a new song to the end of the queue
	 * 
	 * @param element - the song to add to the Playlist
	 */
	@Override
	public void enqueue(Song element) {
		SongNode enNode = new SongNode(element);
		if(last == null || first == null) { 
			//if playlist is null it sets enNode to the first and last node
			first = enNode;
			last = enNode;
			numSongs +=1;
		}
		else {
		last.setNext(enNode); //sets the next node of the tail to enNode
		last = enNode;
		numSongs +=1;
		// TODO Auto-generated method stub
		}
	}

	/**
	 *Removes the song from the beginning of the queue
	 *
	 *@return the song that was removed from the queue, or null if the queue is empty
	 */
	@Override
	public Song dequeue() {
		
		if(first == null) {
			return null; //returns null if first is empty
		}
		SongNode toRemove = first;
		if (first.getNext() == null) { //if first is the only node it empties the playlist
			first = null;
			numSongs--;
			return toRemove.getSong();
		}
		if (first.getNext() != null) {
			first = first.getNext(); //sets the next node to the head
			numSongs--;
			return toRemove.getSong();
		}

		return toRemove.getSong();
		// TODO Auto-generated method stub
	
	}

	/**
	 * Returns the song at the front of the queue without removing it
	 * 
	 * @return the song that is at the front of the queue, or null if the queue is empty
	 */
	@Override
	public Song peek() {
		if(first == null) {
			return null;
		 }
		 else {
			 return first.getSong(); //returns song at the head of the list
		 }
	}

	/**
	 * Returns true if and only if there are no songs in this queue
	 * 
	 * @return true if this queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if(numSongs == 0) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns the number of songs in this queue
	 * 
	 * @return the number of songs in this queue
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numSongs;
	}
	/**
	 * Creates and returns a formatted string representation of this playlist, with the string version of each song in the list on a separate line.
	 * 
	 * @return the string representation of this playlist
	 */
	@Override
	public String toString() {
		String add = "";
		SongNode newNode = first; //sets the newNode to first
		for(int i = 0; i < numSongs; i++) { //iterates through numSongs and prints it out
		add = add + newNode.getSong().toString() + "\n";
newNode = newNode.getNext();
		}
		return add;
		}

}
