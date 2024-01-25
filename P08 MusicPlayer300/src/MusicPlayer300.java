//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    MusicPlayer300
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
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayer300 {
	private String filterArtist; //The artist to play if filterPlay is true; should be null otherwise
	private boolean filterPlay; //Whether the current playback mode should be filtered by artist; false by default
	private Playlist playlist; //The current playlist of Songs

	/**
	 * Creates a new MusicPlayer300 with an empty playlist
	 */
	public MusicPlayer300() {

		playlist = new Playlist(); //Creates a new playlist
		filterPlay = false;
		filterArtist = null;

	}

	/**
	 * Stops any song that is playing and clears out the playlist
	 */
	public void clear() {
		if (playlist == null) {
			playlist = null;
		} else {
			playlist.peek().stop(); // stops any song playing
			playlist = null; //clears playlist
		}
	}

	/**
	 * Provides a string representation of all songs in the current playlist
	 * @return a string representation of all songs in the current playlist
	 */
	public String printPlaylist() {
		return this.playlist.toString();
	}

	/**
	 * Stops playback of the current song (if one is playing) and advances to the next song in the playlist.
	 * 
	 * @throws IllegalStateException
	 */
	public void playNextSong() throws IllegalStateException {
		if (playlist.isEmpty() || playlist == null) {
			throw new IllegalStateException("Playlist is null");
		}

		if (filterPlay == true) { //checks if filterPlay = true
			int i = 0;
			if (playlist.peek().isPlaying()) { //stops song playing at the head of the playlist

				playlist.peek().stop();
				

			}
			playlist.dequeue(); 
			while (i < playlist.size()) { 
		//iterates through playlist and checks if filterPlay matches the peek.artist
				if (filterArtist.equals(playlist.peek().getArtist())) {
					playlist.peek().play();
					break;
				} else {
					playlist.dequeue(); //if it doesnt match it removes it
					
				}
				
				
			}

		}
		else {
		if (playlist.peek().isPlaying()) {

			playlist.peek().stop();
			

		}
		playlist.dequeue(); 
		if (playlist.isEmpty() || playlist == null) {
			throw new IllegalStateException("Playlist is null");
		}
		playlist.peek().play(); //plays song at the head of the playlist
		
		}
	}
	/**Loads a single song to the end of the playlist given the title, artist, and filepath. Filepaths for P08 must refer to files in the audio directory.
	 * @param title - the title of the song
	 * @param artist - the artist of this song
	 * @param - the full relative path to the song file, begins with the "audio" directory for P08
	 * 
	 * @throws IllegalArgumentException - if the song file cannot be read
	 */
	public void loadOneSong(String title, String artist, String filepath) {
		try {
			Song loadSong = new Song(title, artist, filepath);
		} catch (IllegalArgumentException E) {
			throw new IllegalArgumentException("File undredable");
		}
		Song loadSong = new Song(title, artist, filepath); //creates a new song
		playlist.enqueue(loadSong); //adds the song at the end of the list
	}

	/**
	 * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded. Note that filenames in the provided files do NOT include the audio directory, and will need that added before they are loaded. Print "Loading" and the song's title in quotes before you begin loading a song, and an "X" if the load was unsuccessful for any reason.
	 * 
	 * @param file - the File object to load
	 * @throws FileNotFoundException - if the playlist file cannot be loaded
	 */
	public void loadPlaylist(File file) throws FileNotFoundException {
		try {

			Scanner scanner = new Scanner(file); 
			//Creates a new scanner object with given file
			while (scanner.hasNextLine()) { 

				if (scanner.hasNextLine()) {

					String next = scanner.nextLine();

					String splitComma[] = next.split(","); //splits line at comma
					System.out.println("Loading " + "\"" + splitComma[0] + "\""); 
					loadOneSong(splitComma[0], splitComma[1], "audio" + File.separator + splitComma[2]); //loads one song at a time

				} else {
					scanner.close();
					System.out.println("x");

				}

			}

		} catch (FileNotFoundException E) {
			loadPlaylist(file);
			throw new FileNotFoundException("x");
		}
	}

	/**
	 * Creates and returns the menu of options for the interactive console program.
	 * 
	 * @return the formatted menu String
	 */
	public String getMenu() {
		String b = "Enter one of the following options:" + "\n"
				+ "[A <filename>] to enqueue a new song file to the end of this playlist" + "\n"
				+ "[F <filename>] to load a new playlist from the given file" + "\n"
				+ "[L] to list all songs in the current playlist" + "\n"
				+ "[P] to start playing ALL songs in the playlist from the beginning" + "\n"
				+ "[P -t <Title>] to play all songs in the playlist starting from <Title>" + "\n"
				+ "[P -a <Artist>] to start playing only the songs in the playlist by Artist" + "\n"
				+ "[N] to play the next song" + "\n" + "[Q] to stop playing music and quit the program";
		return b; //returns menu

	}

	/** 
	 * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user.
	 * @param in - user input
	 */
	public void runMusicPlayer300(Scanner in) {
		boolean quitMenu = false; // q is true when user quits

		while (quitMenu == false) { 
			System.out.println(getMenu()); //prints menu
			System.out.print(">");
			String userInput = in.next(); //user input

			if (userInput.startsWith("A")) { //checks if user inputed 'A'

				String userString = userInput.replaceFirst("A", ""); //parses the input after A
				userString.strip();

				String fileInput = in.next();

				System.out.print("Artist: "); 
				String artistsUser = in.next(); //user input for artist

				System.out.print("Title: ");
				String songUser = in.next();  //user input for title
				try {
					loadOneSong(songUser, artistsUser, fileInput); //tries to add song at the end of playlist
				} catch (Exception E) {
					System.out.println("Unable to load song");
				}
				
			}

			if (userInput.equals("P")) { //checks if user inputed 'P'
				String secondUserInput = in.nextLine();

				if (secondUserInput.contains("-a")) { //checks if user inputed 'P -a'
					filterPlay = true; //sets filter play equal to true

					String title = secondUserInput.replaceFirst("P -a", ""); //parses input after P -a
					title = title.replace("-a", "").trim();
					filterArtist = title; //sets the filter artist
					System.out.println(title);

					try {
					
						playNextSong(); //plays song
						Thread.sleep(10000); 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				else if (secondUserInput.contains("-t")) { //checks if user inputed 'P -t'

					String title = secondUserInput.replaceFirst("P -t", ""); //parses input after P -t
					title = title.replace("-t", "").trim();
					System.out.println(title);

					int i = 0;
					while (i < playlist.size()) { //iterates through playlist and plays first instance of title
						
						if (playlist.peek().getTitle().equals(title) == false) {

							playlist.dequeue();

						} else {
							playlist.peek().play();
							break;
						}

					}
				}

				if (playlist.isEmpty()) {
					System.out.println("No More Songs!");
				} else {
					

					playlist.peek().play(); //plays song at head of the playlist
					System.out.println("Playing " + playlist.peek());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			if (userInput.equals("F")) { //checks if user inputed 'F'
				String playlistInput = userInput.replaceFirst("F", "");
				playlistInput.strip();
				String fileUser = in.next(); 
				File newUserFile = new File(fileUser); //creates a new file based on user input
				try {
					loadPlaylist(newUserFile); //tries to load playlist given file
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (userInput.equals("A") == false && userInput.contains("F") == false && userInput.contains("L") == false
					&& userInput.contains("P") == false && userInput.contains("P -t") == false && userInput.contains("P -a") == false
					&& userInput.contains("N") == false && userInput.contains("Q") == false) { 
				//if input doesn't follow menu, it prints out message
				System.out.println("Sorry can't understand!");
			}
			//checks if user inputed 'N'
			if (userInput.startsWith("N")) {
				if (playlist.isEmpty()) {
					System.out.println("No More Songs!");
				} else {
					playNextSong();
				}
			}
			//checks if user inputed 'Q' and quits the menu if they do
			if (userInput.startsWith("Q")) {
				System.out.println("Goodbye");
				quitMenu = true;
			}
			
			//checks if user inputed 'L'
			if (userInput.startsWith("L")) {
				if (playlist.isEmpty()) {
					System.out.println("EMPTY");
				} else {
					System.out.print(printPlaylist()); //prints playlist
				}

			}
		}
	}

}
