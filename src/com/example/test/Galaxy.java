package com.example.test;

//works with the github repositories

public class Galaxy {
	
	String galaxyName;
	long galaxyColonies;
	double galaxyLifeforms;
	int galaxyFleets, galaxyStarships, galaxySolarSystems, galaxyPlanets;
	
	public Galaxy(String name, int solarSys, int planets)
	{
		galaxyName = name;
		galaxySolarSystems = solarSys;
		galaxyPlanets = planets;
		galaxyColonies = 0;
		galaxyLifeforms = 0;
		galaxyFleets = 0;
		galaxyStarships = 0;
	}
		
	void setGalaxyColonies(long numberColonies){
		galaxyColonies = numberColonies;
	}
	int getGalaxySolarSystems(){
		return galaxySolarSystems;
	}
	
	int getGalaxyPlanets(){
		return galaxyPlanets;
	}
	
	long getGalaxyColonies(){
		return galaxyColonies;
	}
	
	void setGalaxyPopulation(double numberLifeforms){
		galaxyLifeforms = numberLifeforms;
	}
	
	double getGalaxyPopulation(){
		return galaxyLifeforms;
	}
	
	void setGalaxyFleets(int numberFleets){
		galaxyFleets = numberFleets;
	}
	
	int getGalaxyFleets(){
		return galaxyFleets;
	}
	
	void setGalaxyStarships(int numberStarships){
		galaxyStarships = numberStarships;
	}
	
	int getGalaxyStarships(){
		return galaxyStarships;
	}
	
}
