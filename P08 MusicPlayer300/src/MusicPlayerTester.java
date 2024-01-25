
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    MusicPlayerTester
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
import java.io.FileNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Music Tester class
 * 
 * @author Zakaria and Sadiq Nur
 */
public class MusicPlayerTester {

	/**
	 * Tests the song node class
	 * 
	 * 
	 * @return true if the method is correct
	 */
	public static boolean testSongNode() {
		Song tune = new Song("all-i-want-for-xmas", "hello", "audio" + File.separator + "all-i-want-for-xmas.mid"); // creating
																													// a
																													// song
		SongNode test = new SongNode(tune); // creating a songnode with our song
		Song tune2 = new Song("poo", "too", "audio" + File.separator + "1.mid"); // creating second song
		SongNode test2 = new SongNode(tune2);
		SongNode tune3 = new SongNode(tune);

		SongNode valid1 = new SongNode(tune, test2);

		test2.setNext(tune3); // testing set next method
		if (valid1.getNext() != test2) { // if the songnode isn't equal it returns false
			return false;
		}
		if (test2.getNext() != tune3) {
			return false;
		}
		if (test.getSong() != tune) {
			return false;
		}

		return true;
	}

	/**
	 * Tests the enqueue method
	 * 
	 * 
	 * @return true if the method is correct
	 */
	public static boolean testEnqueue() {
		Playlist play = new Playlist(); // create empty playlist
		Song tune = new Song("all-i-want-for-xmas", "hello", "audio" + File.separator + "all-i-want-for-xmas.mid"); // creating
																													// new
																													// song

		Song tune2 = new Song("poo", "too", "audio" + File.separator + "1.mid"); // creating second song

		play.enqueue(tune); // enqueing the first song
		play.enqueue(tune2);// enqueing the second song
		if (play.peek() != tune) { // checking the first song of the playlist should be equal
			return false;
		}
		if (play.size() != 2) { // making sure both songs were added
			return false;
		}
		return true;
	}

	/**
	 * Tests the dequeue method
	 * 
	 * 
	 * @return true if the method is correct
	 */
	public static boolean testDequeue() {
		Playlist play = new Playlist(); // create empty playlist
		Song tune = new Song("all-i-want-for-xmas", "hello", "audio" + File.separator + "all-i-want-for-xmas.mid"); // creating
																													// new
																													// song

		Song tune2 = new Song("poo", "too", "audio" + File.separator + "1.mid"); // creating second song

		play.enqueue(tune); // enqueing the first song
		play.enqueue(tune2);// enqueing the seconds song
		play.dequeue();// dequeing the first song
		if (play.peek() != tune2) { // making sure the second song is now the first song in the playlist
			return false;
		}
		if (play.size() != 1) { // making sure the size is now subtracted by 1
			return false;
		}
		play.dequeue(); // dequeing the second song
		if (play.isEmpty() == false) { // making sure the playlist is empty now
			return false;
		}
		return true;
	}

	/**
	 * Tests the song constructor
	 * 
	 * 
	 * @return true if the method is correct
	 */
	public static boolean testSongConstructor() {

		Song tune = new Song("1.mid", "hello", "audio" + File.separator + "waterloo.mid"); // creating new song
		tune.play(); // making sure it plays
		try { // making sure it runs
			Thread.sleep(1000);
		} catch (InterruptedException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tune.getTitle() != "1.mid") { // checking the title method

			return false;

		}
		if (tune.getArtist() != "hello") { // checking the artist method

			return false;
		}
		try {
			Song tune2 = new Song("1.mid", "hello", "audio" + File.separator + "waterlooo.mid"); // creating new song
		} catch (IllegalArgumentException e) { // making sure it throws the right exception

		} catch (Exception e) { // if an illegal argument exception isnt thrown it returns false
			return false;
		}
		if (tune.isPlaying() != true) { // making sure the tune is playing
			System.out.println("do");
			return false;
		}

		String duration = tune.toString().strip(); // getting the duration
		duration = duration.substring(duration.indexOf("(") + 1, duration.lastIndexOf(")")); // checking to see if the
																								// duration matches
		if (duration.contains(":") == false) { // if it isn't formatted correctly return false;
			return false;
		}

		return true;
	}

	/**
	 * Tests the music player class
	 * 
	 * 
	 * @return true if the method is correct
	 */
	public static boolean testMusicPlayer300() {
		MusicPlayer300 player = new MusicPlayer300(); // create a new music player
		Scanner scan = new Scanner(System.in); // create a scanner that takes user input
		player.runMusicPlayer300(scan); // running the music player
		return true;
	}

	/**
	 * Tests the song playback
	 * 
	 * 
	 * @return true if the method is correct
	 */
	public static boolean testSongPlayback() {
		Song tune = new Song("1.mid", "hello", "audio" + File.separator + "1.mid"); // creating new song
		tune.play(); // playing the song
		try {
			Thread.sleep(1000); // sleeping the thread so the song can play
		} catch (InterruptedException e) {

		}
		if (tune.isPlaying() == false) { // making sure the song is playing which also tests the isplaying method
			return false;
		}
		tune.stop(); // stopping the song
		if (tune.isPlaying() == true) { // making sure the song is not playing which tests the stop method
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
//		System.out.println(testSongNode());
//		System.out.println(testEnqueue());
//		System.out.println(testDequeue());
//		System.out.println(testSongConstructor());
//		System.out.println(testSongPlayback());
System.out.println(testMusicPlayer300());

	}

}