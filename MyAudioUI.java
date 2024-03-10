// Jeremy Mathew
// 501166347
// j11mathew@torontomu.ca

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try // attempt action
			{
				String action = scanner.nextLine();

				if (action == null || action.equals(""))  // prompt for input
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT")) // quit program
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all store items
				{
					store.listAll();
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all audiobooks
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all podcasts
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all artists
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the start and end index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD"))
				{
					int begin = 0;
					int end = 0;
					System.out.print("From Store Content #: ");

					if (scanner.hasNextInt()) // get starting index
					{
						begin = scanner.nextInt();
						scanner.nextLine(); 
					}

					System.out.print("To Store Content #: "); 

					if (scanner.hasNextInt()) // get ending index
					{
						end = scanner.nextInt();
						scanner.nextLine(); 
					}

					AudioContent content;

					// for each index in [begin, end]
					for (int i = 0; i < (end - begin + 1); i++)
					{
						try // Attempt to get content
						{
							content = store.getContent(begin + i);

							if (content == null) // check if valid
							{
								System.out.println("Content Not Found in Store");
							}
							else 
							{
								mylibrary.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");
							}
						}
						catch (ContentDownloadedException e) // Content already downloaded
						{
							System.out.println(e.getMessage());
						}
					}
										
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					int index = 0;
					System.out.print("Song Number: ");

					if (scanner.hasNextInt()) // grab song index input
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}

					mylibrary.playSong(index); // call associated library function
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					int index = 0;
					System.out.print("Audio Book Number: ");

					if (scanner.hasNextInt()) // grab audiobook index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					mylibrary.printAudioBookTOC(index); // call associated library function
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int index = 0;
					int chapter = 0;
					System.out.print("Audio Book Number: ");

					if (scanner.hasNextInt()) // grab audiobook index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					System.out.print("Chapter: ");

					if (scanner.hasNextInt()) // grab chapter index input
					{
						chapter = scanner.nextInt();
						scanner.nextLine();
					}

					mylibrary.playAudioBook(index, chapter); // call associated library function

				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) 
				{
					int index = 0;
					int seasonInt = 0;
					System.out.print("Podcast Number: ");

					if (scanner.hasNextInt()) // grab podcast index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					System.out.print("Season: ");

					if (scanner.hasNextInt()) // grab season index input
					{
						seasonInt = scanner.nextInt();
						scanner.nextLine(); 
					}

					mylibrary.printPodcastEpisodes(index, seasonInt); // call associated library function

				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					int index = 0;
					int seasonInt = 0;
					int episodeInt = 0;
					System.out.print("Podcast Number: ");

					if (scanner.hasNextInt()) // grab podcast index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					System.out.print("Season: ");

					if (scanner.hasNextInt()) // grab season index input
					{
						seasonInt = scanner.nextInt();
						scanner.nextLine(); 
					}

					System.out.print("Episode: ");

					if (scanner.hasNextInt()) // grab episode index input
					{
						episodeInt = scanner.nextInt();
						scanner.nextLine(); 
					}

					mylibrary.playPodcast(index, seasonInt, episodeInt); // call associated library function
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					String playlisttString = "";
					System.out.print("Playlist Title: ");

					if (scanner.hasNextLine()) // grab playlist title input
					{
						playlisttString = scanner.nextLine(); 
					}

					mylibrary.playPlaylist(playlisttString); // call associated library function
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					int index = 0;
					String playlisttString = "";
					System.out.print("Playlist Title: ");

					if (scanner.hasNextLine()) // grab title input
					{
						playlisttString = scanner.nextLine(); 
					}

					System.out.print("Library Content #: ");

					if (scanner.hasNextInt()) // grab library index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					mylibrary.playPlaylist(playlisttString, index); // call associated library function
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					int index = 0;
					System.out.print("Library Song #: ");

					if (scanner.hasNextInt()) // grab library index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					mylibrary.deleteSong(index); // call associated library function
					
				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					String playlisttString = "";
					System.out.print("Playlist Title: ");

					if (scanner.hasNextLine()) // grab playlist title index
					{
						playlisttString = scanner.nextLine(); 
					}

					mylibrary.makePlaylist(playlisttString); // call associated library function
					
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					String playlisttString = "";
					System.out.print("Playlist Title: ");

					if (scanner.hasNextLine()) // grab playlist title input
					{
						playlisttString = scanner.nextLine(); 
					}

					mylibrary.printPlaylist(playlisttString); // call associated library function
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					String type = "";
					int index = 0;
					String playlisttString = "";
					System.out.print("Playlist Title: ");

					if (scanner.hasNextLine()) // grab playlist title input
					{
						playlisttString = scanner.nextLine(); 
					}

					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");

					if (scanner.hasNextLine()) // grab content type input
					{
						type = scanner.nextLine(); 
					}

					System.out.print("Library Content #: ");

					if (scanner.hasNextInt()) // grab library index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					mylibrary.addContentToPlaylist(type, index, playlisttString); // call associated library function

				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					int index = 0;
					String playlisttString = "";
					System.out.print("Playlist Title: ");

					if (scanner.hasNextLine()) // grab playlist title input
					{
						playlisttString = scanner.nextLine(); 
					}

					System.out.print("Library Content #: ");

					if (scanner.hasNextInt()) // grab library index input
					{
						index = scanner.nextInt();
						scanner.nextLine(); 
					}

					mylibrary.delContentFromPlaylist(index, playlisttString); // call associated library function
				}
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}
				else if (action.equalsIgnoreCase("SEARCH")) // Search by title
				{
					String title = "";
					System.out.print("Title: ");

					if (scanner.hasNextLine()) // grab title string
					{
						title = scanner.nextLine(); 
					}

					if (!store.getTitleMap().containsKey(title)) // if not found
					{
						System.out.println("No matches for " + title);
					}
					else
					{
						int index = store.getTitleMap().get(title);
						System.out.print(index + ". ");
						store.getContent(index).printInfo();
					}
				}
				else if (action.equalsIgnoreCase("SEARCHA")) // Search by artist
				{
					String artist = "";
					System.out.print("Artist: ");

					if (scanner.hasNextLine()) // grab artist string
					{
						artist = scanner.nextLine(); 
					}

					if (!store.getArtistMap().containsKey(artist)) // if not found
					{
						System.out.println("No matches for " + artist);
					}
					else
					{
						ArrayList<Integer> indexes = store.getArtistMap().get(artist);

						// for each index
						for (int i = 0; i < indexes.size(); i++)
						{
							System.out.print(indexes.get(i) + ". ");
							store.getContent(indexes.get(i)).printInfo();
							System.out.println();
						}
					}
				}
				else if (action.equalsIgnoreCase("SEARCHG")) // Search by genre
				{
					String type = "";
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");

					if (scanner.hasNextLine()) // grab genre string
					{
						type = scanner.nextLine(); 
					}

					if (!store.getGenreMap().containsKey(type)) // if not found
					{
						System.out.println("No matches for " + type);
					}
					else
					{
						ArrayList<Integer> indexes = store.getGenreMap().get(type);

						// for each index
						for (int i = 0; i < indexes.size(); i++) 
						{
							System.out.print(indexes.get(i) + ". ");
							store.getContent(indexes.get(i)).printInfo();
							System.out.println();
						}
					}
				}
				else if (action.equalsIgnoreCase("DOWNLOADA")) // download all content of artist
				{
					String artist = "";
					System.out.print("Artist: "); 

					if (scanner.hasNextLine()) // get artist string
					{
						artist = scanner.nextLine(); 
					}

					if (!store.getArtistMap().containsKey(artist)) // if not found
					{
						System.out.println("No matches found for " + artist);
					}
					else
					{
						ArrayList<Integer> indexes = store.getArtistMap().get(artist);
						AudioContent content;

						// for each index
						for (int i = 0; i < indexes.size(); i++) 
						{
							try // Attempt to get content
							{
								content = store.getContent(indexes.get(i));
								mylibrary.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
							}
							catch (ContentDownloadedException e) // Content already downloaded
							{
								System.out.println(e.getMessage());
							}
						}
					}	
				}
				else if (action.equalsIgnoreCase("DOWNLOADG")) // download all content of genre
				{
					String type = "";
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");

					if (scanner.hasNextLine()) // grab genre string
					{
						type = scanner.nextLine(); 
					}

					if (!store.getGenreMap().containsKey(type)) // if not found
					{
						System.out.println("No matches found for " + type);
					}
					else
					{
						ArrayList<Integer> indexes = store.getGenreMap().get(type);
						AudioContent content;

						// for each index
						for (int i = 0; i < indexes.size(); i++) 
						{
							try // Attempt to get content
							{
								content = store.getContent(indexes.get(i));
								mylibrary.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " added to Library");
							}
							catch (ContentDownloadedException e) // Content already downloaded
							{
								System.out.println(e.getMessage());
							}
						}
					}	
				}
				else if (action.equalsIgnoreCase("SEARCHP")) // Search for string
				{
					String find = "";
					System.out.print("Search: ");

					if (scanner.hasNextLine()) // get string to find
					{
						find = scanner.nextLine(); 
					}

					ArrayList<AudioContent> contents = store.getContents();

					// for each content
					for (int i = 0; i < contents.size(); i++)
					{
						AudioContent a = contents.get(i);

						if (a.getTitle().contains(find) || a.getId().contains(find) || a.getType().contains(find) || a.getAudioFile().contains(action)) // checks audio content atributes 1/2
						{
							System.out.print(i+1 + ". ");
							contents.get(i).printInfo();
							System.out.println();
						}
						else if (String.valueOf(a.getYear()).contains(find) || String.valueOf(a.getLength()).contains(find)) // checks audio content atributes 2/2
						{
							System.out.print(i+1 + ". ");
							contents.get(i).printInfo();
							System.out.println();
						}
						else if (a.getType().equals(Song.TYPENAME)) // if song
						{
							Song b = (Song) a;

							if (b.getArtist().contains(find) || b.getComposer().contains(find) || b.getGenre().toString().contains(find)) // checks song atributes
							{
								System.out.print(i+1 + ". ");
								a.printInfo();
								System.out.println();
							}
						}
						else if (a.getType().equals(AudioBook.TYPENAME)) // if Audiobook
						{
							AudioBook c = (AudioBook) a;

							if (c.getAuthor().contains(find) || c.getNarrator().contains(find)) // checks audio book atributes 1/2
							{
								System.out.print(i+1 + ". ");
								a.printInfo();
								System.out.println();
							}
							else 
							{
								Boolean found = false;

								// loops through chapter titles and chapters
								for (int n = 0; n < c.getChapterTitles().size(); n++)
								{
									if (c.getChapterTitles().get(n).contains(find) || c.getChapters().get(n).contains(find)) // checks audio book atributes 2/2
									{
										found = true;
									}
								}

								if (found) // if found
								{
									System.out.print(i+1 + ". ");
									a.printInfo();
									System.out.println();
								}
							}
						}
					}
				}

				System.out.print("\n>");

			}

			catch (AudioContentNotFoundException e) // if content not found / playlist index invalid
			{
				System.out.println(e.getMessage());
				System.out.print("\n>");
			}

			catch (PlaylistExistsException e) // playlist already exist
			{
				System.out.println(e.getMessage());
				System.out.print("\n>");
			}

			catch (PlaylistNotFoundException e) // playlist not found
			{
				System.out.println(e.getMessage());
				System.out.print("\n>");
			}
		}
	}
}
