// Jeremy Mathew
// 501166347
// j11mathew@torontomu.ca

import java.util.ArrayList;

public class Season
{
	
	private ArrayList<String> episodeTitles;
	private ArrayList<String> episodeFiles;
	private ArrayList<Integer> episodeLengths;
	
	
	
	public Season(ArrayList<String> episodeTitles, ArrayList<String> episodeFiles, ArrayList<Integer> episodeLengths)
	{
		// Initialize instance variables. 
		this.episodeTitles = episodeTitles;
		this.episodeFiles = episodeFiles;
		this.episodeLengths = episodeLengths;
	}
	
	public ArrayList<String> getEpisodeTitles()
	{
		return episodeTitles;
	}

	public void setEpisodeTitles(ArrayList<String> episodeTitles)
	{
		this.episodeTitles = episodeTitles;
	}

	public ArrayList<String> getEpisodes()
	{
		return episodeFiles;
	}

	public void setEpisode(ArrayList<String> episodeFiles)
	{
		this.episodeFiles = episodeFiles;
	}

	public ArrayList<Integer> getEpisodeLengths()
	{
		return episodeLengths;
	}

	public void setEpisodeLenghts(ArrayList<Integer> episodeLengths)
	{
		this.episodeLengths = episodeLengths;
	}
	
}
