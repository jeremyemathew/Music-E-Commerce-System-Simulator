// Jeremy Mathew
// 501166347
// j11mathew@torontomu.ca

import java.util.ArrayList;

/*
 * A Podcast is a type of AudioContent. 
 */
public class Podcast extends AudioContent
{
	public static final String TYPENAME = "PODCAST";
	
	private String host; 		// Can be multiple names separated by commas
	private ArrayList<Season> seasons;
	
	
	public Podcast(String title, int year, String id, String type, String audioFile, int length, String host, ArrayList<Season> seasons)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Podcast instance variables. 
		super(title, year, id, type, audioFile, length);
		this.host = host;
		this.seasons = seasons;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the podcast. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print host and number of seasons
	public void printInfo()
	{
		super.printInfo();
		System.out.println("Host: " + host);
		System.out.println("Seasons: " + seasons.size());	
	}
	
	// Play the podcast by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play(int seasonInt, int episodeInt)
	{
		super.setAudioFile(seasons.get(seasonInt-1).getEpisodeTitles().get(episodeInt-1) + "\n" + seasons.get(seasonInt-1).getEpisodes().get(episodeInt-1));
		super.play();
	}

	public void printTOC(int seasonInt)
	{
		for (int i = 0; i < seasons.get(seasonInt - 1).getEpisodeTitles().size(); i++)
		{
			int index = i + 1;
			System.out.print("Episode " + index + ". ");
			System.out.println(seasons.get(seasonInt - 1).getEpisodeTitles().get(i));
			System.out.println();	
		}
	}
	
	public String getHost()
	{
		return host;
	}
	public void setHost(String host)
	{
		this.host = host;
	}
	
	public ArrayList<Season> getSeasons()
	{
		return seasons;
	}
	public void setSeasons(ArrayList<Season> seasons)
	{
		this.seasons = seasons;
	}
	
}

