// Jeremy Mathew
// 501166347
// j11mathew@torontomu.ca

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
    private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions

	public Library()
	{
		songs 		= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>();
		playlists   = new ArrayList<Playlist>();
	    podcasts	= new ArrayList<Podcast>();
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content)
	{
		if (content.getType().equals(Song.TYPENAME)) // check if Song
		{
			if (songs.contains(content)) // Check if already downmloaded
			{
				throw new ContentDownloadedException("Song " + content.getTitle() + " already downloaded");
			}

			songs.add((Song)content);
		}

		else if (content.getType().equals(AudioBook.TYPENAME))  // check if AudioBook
		{
			if (audiobooks.contains(content)) // Check if already downmloaded
			{
				throw new ContentDownloadedException("Audiobook " + content.getTitle() + " already downloaded");
			}

			audiobooks.add((AudioBook)content);
		}

		else if (content.getType().equals(Podcast.TYPENAME))  // check if Podcast
		{
			if (podcasts.contains(content)) // Check if already downmloaded
			{
				throw new ContentDownloadedException("Podcast " + content.getTitle() + " already downloaded");
			}

			podcasts.add((Podcast)content);
		}
		return true;
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++) // for each song in songs, printinfo
		{
			int index = i + 1;
			System.out.print(index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++) // for each audiobook in audiobooks, printinfo
		{
			int index = i + 1;
			System.out.print(index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{

		for (int i = 0; i < podcasts.size(); i++) // for each podcast in podcasts, printinfo
		{
			int index = i + 1;
			System.out.print(index + ". ");
			podcasts.get(i).printInfo();
			System.out.println();	
		}
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++) // for each playlist in playlists, print title
		{
			int index = i + 1;
			System.out.println(index + ". " + playlists.get(i).getTitle());
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> arts = new ArrayList<String>();

		for (int i = 0; i < songs.size(); i++) // adds unique artists to arraylist
		{
			if (!(arts.contains(songs.get(i).getArtist())))
			{
				arts.add(songs.get(i).getArtist());
			}
		}

		for (int i = 0; i < arts.size(); i++) // prints out all artists in arraylist
		{
			System.out.println(i + 1 + ". " + arts.get(i));	
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		if (index > songs.size() || index < 1) // check if index is valid
		{
			throw new AudioContentNotFoundException("Song Not Found");
		}

		Song song = songs.remove(index-1);
		
		// Search all playlists
		for (int i = 0; i < playlists.size(); i++)
		{
			Playlist pl = playlists.get(i);

			if (pl.getContent().contains(song)) // check if song in playlist
			{
				pl.getContent().remove(song);
			}
		}
		
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b)
		{
			if (a.getYear() > b.getYear()) return 1;
			if (a.getYear() < b.getYear()) return -1;
			return 0;
		}

	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort()
	 Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b)
		{
			if (a.getLength() > b.getLength()) return 1;
			if (a.getLength() < b.getLength()) return -1;
			return 0;
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || index > songs.size()) // check if valid index
		{
			throw new AudioContentNotFoundException("Song Not Found");
		}

		songs.get(index-1).play();
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public void playPodcast(int index, int seasonInt, int episodeInt)
	{
		if (index < 1 || index > podcasts.size()) // Check if index valid
		{
			throw new AudioContentNotFoundException("Podcast Not Found");
		}

		Podcast podcast = podcasts.get(index-1);

		if (seasonInt < 1 || seasonInt > podcast.getSeasons().size()) // Check if Season valid
		{
			throw new AudioContentNotFoundException("Season Not Found");
		}
		
		if (index < 1 || index > podcast.getSeasons().get(seasonInt-1).getEpisodeTitles().size()) // Check if Episode valid
		{
			throw new AudioContentNotFoundException("Episode Not Found"); 
		}

		podcasts.get(index-1).play(seasonInt, episodeInt); // play

		
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public void printPodcastEpisodes(int index, int seasonInt)
	{
		if (index < 1 || index > podcasts.size()) // chjeck if index valid
		{
			throw new AudioContentNotFoundException("Podcast Not Found"); 
		}

		if (seasonInt < 1 || seasonInt > podcasts.get(index-1).getSeasons().size()) // check if season index is valid
		{
			throw new AudioContentNotFoundException("Season Not Found"); 
		}

		podcasts.get(index-1).printTOC(seasonInt);

	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		if (index < 1 || index > audiobooks.size()) // check if valid index
		{
			throw new AudioContentNotFoundException("AudioBook Not Found"); // ecception
		}

		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		if (index < 1 || index > audiobooks.size()) // check if valid index
		{
			throw new AudioContentNotFoundException("AudioBook Not Found"); // ecception
		}

		audiobooks.get(index-1).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		Playlist pl = new Playlist(title);

		if (playlists.contains(pl)) // check if playlist in list
		{
			throw new PlaylistExistsException("Playlist " + title + " Already Exists"); // ecception
		}

		playlists.add(pl);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{

		int index = playlists.indexOf(new Playlist(title)); // finf index of playlist
		
		if (index == -1)
		{
			throw new PlaylistNotFoundException("Playlist Not Found"); // ecception
		}
		playlists.get(index).printContents();
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		int index = playlists.indexOf(new Playlist(playlistTitle));
		
		if (index == -1)
		{
			throw new PlaylistNotFoundException("Playlist Not Found");
		}
		playlists.get(index).playAll();
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		int plIndex = playlists.indexOf(new Playlist(playlistTitle));
		
		if (plIndex == -1)
		{
			throw new PlaylistNotFoundException("Playlist Not Found"); 
		}

		Playlist pl = playlists.get(plIndex);
		
		if (indexInPL < 1 || indexInPL > pl.getContent().size())
		{
			throw new AudioContentNotFoundException("Invalid Playlist AudioContent #"); // invalid index
		}

		pl.play(indexInPL);
		
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		int indexofPl = playlists.indexOf(new Playlist(playlistTitle));
		
		if (indexofPl == -1)
		{
			throw new PlaylistNotFoundException("Playlist Not Found"); // Exception
		}
		
		if (type.equalsIgnoreCase("SONG")) // if Song
		{
			if (index < 1 || index > songs.size()) // check if index valid
			{
				throw new AudioContentNotFoundException("Song Not Found");  // Exception
			}

			playlists.get(indexofPl).addContent(songs.get(index - 1)); // add content
		}

		else if (type.equalsIgnoreCase("AUDIOBOOK")) // if Audiobook
		{
			if (index < 1 || index > audiobooks.size())  // check if index valid
			{
				throw new AudioContentNotFoundException("AudioBook Not Found");  // Exception
			}

			playlists.get(indexofPl).addContent(audiobooks.get(index - 1)); // add content
		}

		else if (type.equalsIgnoreCase("PODCAST")) // if Podcast
		{
			if (index < 1 || index > podcasts.size())  // check if index valid
			{
				throw new AudioContentNotFoundException("Podcast Not Found");  // Exception
			}

			playlists.get(indexofPl).addContent(podcasts.get(index - 1)); // add content
		}

		
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		int plIndex = playlists.indexOf(new Playlist(title));
		
		if (plIndex == -1)
		{
			throw new PlaylistNotFoundException("Playlist Not Found");
		}
		Playlist pl = playlists.get(plIndex);
		
		// Delete Content
		if (!pl.contains(index))
		{
			throw new AudioContentNotFoundException("Content Not Found");
		}
		pl.deleteContent(index); // Delete Content
	}
	
}

class AudioContentNotFoundException extends RuntimeException
{ 
	public AudioContentNotFoundException(String message)
	{ 
		super(message);
	}
}

class PlaylistNotFoundException extends RuntimeException
{ 
	public PlaylistNotFoundException(String message)
	{ 
		super(message);
	}
}

class PlaylistExistsException extends RuntimeException
{ 
	public PlaylistExistsException(String message)
	{ 
		super(message);
	}
}

class ContentDownloadedException extends RuntimeException
{ 
	public ContentDownloadedException(String message)
	{ 
		super(message);
	}
}

