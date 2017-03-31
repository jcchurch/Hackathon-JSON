package client;

import game.Planet;

import java.util.ArrayList;
import java.util.Random;

public class RandomAlienInvasion {

	
	public static String toString(ArrayList<Planet> planets)
	{
		String s = "";
		for(Planet p : planets)
		{
			s+= p.getPlanetId() + "["+p.getAttack()+ " / "+p.getDefense()+"], ";
		}
		return s;
	}
	
	public static String toString(Planet p)
	{
		String s = "";
		
			s+= p.getPlanetId() + "["+p.getAttack()+ " / "+p.getDefense()+"], ";
		
		return s;
	}
	
	public static void main(String[] args) {
		AlienInvasionClient client = AlienInvasionClient.connect("localhost",8000);
		AlienGameState state = client.getStatus();
		
		while(state.getWhoWon().equals(""))
		{
			Random rand = new Random();
			Random rand2 = new Random();
			ArrayList<Planet> ripePlanets = state.getNeutralPlanets();
			ripePlanets.addAll(state.getTheirPlanets());

            client.startAttack(ripePlanets.get(rand.nextInt(ripePlanets.size())), state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size())));
			state = client.getStatus();
			
		}
		
		System.out.println(state.getWhoWon() + " WON!");

	}

}
