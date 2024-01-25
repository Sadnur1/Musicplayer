//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Song
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

public class Song {
	private AudioUtility audioClip; //This song's AudioUtility interface to javax.sound.sampled
	private String artist; //The artist of this song
	private int duration; //The duration of this song in number of seconds
	private String title; //The title of this song

	/**
	 * Initializes all instance data fields according to the provided values
	 * 
	 * @param title - the title of the song, set to empty string if null
	 * @param artist - the artist of this song, set to empty string if null
	 * @param filepath - the full relative path to the song file, begins with the "audio" directory for P08
	 * @throws IllegalArgumentException - if the song file cannot be read
	 */
	public Song(String title, String artist, String filepath) throws IllegalArgumentException {
		if (title == null) { 
			title = "";
		}
		if (title != null) {
			this.title = title;

		}
		if (artist == null) {
			artist = "";
		}
		if (artist != null) {
			this.artist = artist;

		}
		try {
			this.audioClip = new AudioUtility(filepath); //creates new audioClip with the filepath
		} catch (IOException e) {
// TODO Auto-generated catch block
			throw new IllegalArgumentException("Illegal argument");
		}
		duration = audioClip.getClipLength(); 

	}

	/**
	 * Tests whether this song is currently playing using the AudioUtility
	 * 
	 * @return true if the song is playing, false otherwise
	 */
	public boolean isPlaying() {
		if (audioClip.isRunning()) { //if its running it returns true;
			return true;
		}
		return false;
	}

	/**
	 * Creates and returns a string representation of this Song
	 *
	 *@return a formatted string representation of this Song
	 */
	@Override
	public String toString() {
		int minutesLength; 

		double doubleMin;
		if (audioClip.getClipLength() >= 60) { //if audio clip is less than 60
			minutesLength = duration / 60; //divides duration by 60
			doubleMin = (duration / 60); //divied min by 60
			doubleMin = (doubleMin / 60) * 10; //multiply it by ten to make it easier to round
			double round = Math.round(doubleMin * 100.0) / 100.0; //rounds min
			round = round - .01; //subtracts 0.1

			String roundedMin = round + ""; //converts to string
			

			String replacedMin = roundedMin.replaceFirst(".", ""); //replaces period with empty string
			String spacedMin = replacedMin.replace('.', ' ');
			
			String trimmedMin = spacedMin.trim(); //trims min string

			return "\"" + getTitle() + "\"" + " (" + minutesLength + ":" + trimmedMin + ")" + " by " + getArtist();
			//returns to string with proper format
		} else {
			return "\"" + getTitle() + "\"" + " (" + "0" + ":" + audioClip.getClipLength() + ")" + " by " + getArtist();
		}

	}

	/**
	 * Accessor method for the song's title
	 * 
	 * @return the title of this song
	 */
	public String getTitle() {
		return this.title;
	}

	/** 
	 * Accessor method for the song's artist
	 * 
	 * @return the artist of this song
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * Uses the AudioUtility to start playback of this song, reopening the clip for playback if necessary
	 * 
	 */
	public void play() {

		if (audioClip.isReadyToPlay()) {
			audioClip.startClip();
		} else {
			audioClip.reopenClip(); //reopens clip if it fails for any reason
			audioClip.startClip();
		}
	}

	/**
	 * Uses the AudioUtility to stop playback of this song
	 */
	public void stop() {
		audioClip.stopClip();
	}
}