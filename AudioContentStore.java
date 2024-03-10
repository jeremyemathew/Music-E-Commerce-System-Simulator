// Jeremy Mathew
// 501166347
// j11mathew@torontomu.ca

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
	private ArrayList<AudioContent> contents; 
	private Map<String, Integer> titleMap;
	private Map<String, ArrayList<Integer>> artistMap;
	private Map<String, ArrayList<Integer>> genreMap;
	
	public AudioContentStore()
	{
		contents = ReadContent();

		// create maps for Search
		titleMap = new HashMap<String, Integer>();
		artistMap = new HashMap<String, ArrayList<Integer>>();
		genreMap = new HashMap<String, ArrayList<Integer>>();

		// Populate maps for Search
		for (int i = 0; i < contents.size(); i++)
		{
			// Title Search
			titleMap.put(contents.get(i).getTitle(), i+1);

			// Artist And Genre current index
			ArrayList<Integer> artistIndexes = new ArrayList<Integer>();
			artistIndexes.add(i+1);
			ArrayList<Integer> genreIndexes = new ArrayList<Integer>();
			genreIndexes.add(i+1);

			if (contents.get(i).getType().equals(Song.TYPENAME)) // check if Song
			{
				Song song = (Song) contents.get(i);

				// Artist Search
				if (artistMap.containsKey(song.getArtist())) // if key already exists
				{
					artistIndexes.addAll(0, artistMap.get(song.getArtist()));
					artistMap.replace(song.getArtist(), artistIndexes);
				}
				else
				{
					artistMap.put(song.getArtist(), artistIndexes);
				}

				// Genre Search
				if (genreMap.containsKey(song.getGenre().toString())) // if key already exists
				{
					genreIndexes.addAll(0, genreMap.get(song.getGenre().toString()));
					genreMap.replace(song.getGenre().toString(), genreIndexes);
				}
				else
				{
					genreMap.put(song.getGenre().toString(), genreIndexes);
				}
			}

			else if (contents.get(i).getType().equals(AudioBook.TYPENAME))  // check if AudioBook
			{
				AudioBook audiobook = (AudioBook) contents.get(i);

				// Author Search
				if (artistMap.containsKey(audiobook.getAuthor())) // if key already exists
				{
					artistIndexes.addAll(0, artistMap.get(audiobook.getAuthor()));
					artistMap.replace(audiobook.getAuthor(), artistIndexes);
				}
				else
				{
					artistMap.put(audiobook.getAuthor(), artistIndexes);
				}
			}
		}
	}
	
	public AudioContent getContent(int index)
	{
		if (index < 1 || index > contents.size())
		{
			return null;
		}
		return contents.get(index-1);
	}

	public ArrayList<AudioContent> getContents()
	{
		return contents;
	}
	
	public void listAll()
	{
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}
	

	private ArrayList<AudioContent> ReadContent()
	{
		// list to return
		contents = new ArrayList<AudioContent>();

		// reads file
		try
		{
			Scanner in = new Scanner(new File("store.txt")); // Scanner

			while (in.hasNextLine())
			{
            	String type = in.nextLine();
				if (type.equals(Song.TYPENAME))
				{
					// Basic info
					String id = in.nextLine();
					String title = in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());
					String artist = in.nextLine();
					String composer = in.nextLine();
					Song.Genre genre = Song.Genre.valueOf(in.nextLine());


					// file
					String file = "";

					int size = Integer.parseInt(in.nextLine());
					for (int i = 0; i < size; i++) // for each line in lyrics
					{
						file += in.nextLine() + "\n";
					}
					
					// Create content
					contents.add(new Song(title, year, id, Song.TYPENAME, file, length, artist, composer, genre, file));

					
				}
				else if (type.equals(AudioBook.TYPENAME))
				{
					// Basic info
					String id = in.nextLine();
					String title = in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());
					String author = in.nextLine();
					String narrator = in.nextLine();

					// Chapter titles
					ArrayList<String> chapterTitles = new ArrayList<String>();
					int size = Integer.parseInt(in.nextLine());

					for (int i = 0; i < size; i++) // for each title add to chaptertitles
					{
						chapterTitles.add(in.nextLine());
					}

					// Chapters
					ArrayList<String> chapters = new ArrayList<String>();
					int chapterSize = 0;
					String file = "";

					for (int i = 0; i < size; i++) // for each chapter
					{
						chapterSize = Integer.parseInt(in.nextLine());
						for (int n = 0; n < chapterSize; n++) // for each line in the chapter
						{
							file += in.nextLine() + "\n";
						}
						chapters.add(file);
					}
					
					// Create content
					contents.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chapterTitles, chapters));
				}
			}
		}

		catch (FileNotFoundException e) // if file not found
      	{ 
         	System.out.println(e.getMessage());
      	}

		 return contents;	
	}

	public Map<String, Integer> getTitleMap()
	{
		return titleMap;
	}

	public Map<String, ArrayList<Integer>> getArtistMap()
	{
		return artistMap;
	}

	public Map<String, ArrayList<Integer>> getGenreMap()
	{
		return genreMap;
	}

}
