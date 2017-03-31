package client;

import game.Planet;

import java.util.ArrayList;
import java.util.Random;

public class MichelleAI_v3 {

	
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
			if(ripePlanets.size() > 0 && state.getMyPlanets().size() > 0)
			{

				Planet mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
				Planet theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
				
				
				
				
				boolean go = true;
				int index = 0;
				while(go)
				{
					index ++;
					if((mine.getAttack() >= theirs.getDefense() && mine.getSpeed() > theirs.getSpeed()) 
							&& (mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getAttack() >= theirs.getDefense() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							&& mine.getTroopCount() > theirs.getTroopCount())
							&& mine.getTroopsPerTick() > theirs.getTroopsPerTick()
							&& mine.getDefense() > theirs.getDefense()) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getAttack() >= theirs.getDefense() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							&& mine.getTroopCount() > theirs.getTroopCount())
							&& mine.getTroopsPerTick() > theirs.getTroopsPerTick()
							) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getAttack() >= theirs.getDefense() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							&& mine.getTroopCount() > theirs.getTroopCount())
							
							) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getAttack() >= theirs.getDefense() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							
							&& mine.getTroopsPerTick() > theirs.getTroopsPerTick()
							) )
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if(( 
							 mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							&& mine.getTroopCount() > theirs.getTroopCount())
							&& mine.getTroopsPerTick() > theirs.getTroopsPerTick()
							) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getDefense() >= theirs.getDefense() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							&& mine.getTroopCount() > theirs.getTroopCount())
							&& mine.getTroopsPerTick() > theirs.getTroopsPerTick()
							) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getAttack() > theirs.getAttack() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							&& mine.getTroopCount() > theirs.getTroopCount())
							&& mine.getTroopsPerTick() > theirs.getTroopsPerTick()
							) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getAttack() >= theirs.getDefense() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							&& mine.getTroopCount() > theirs.getTroopCount())
							
							) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if((mine.getAttack() >= theirs.getDefense() 
							&& mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							
							) )
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				while(go)
				{
					index ++;
					if(
							 mine.getTroopCount() > 2 
							&& mine.getSpeed() > theirs.getSpeed() 
							
							) 
							//||
							//mine.getSpeed() > theirs.getSpeed()) 
							//|| 
							//(mine.getTroopCount() > theirs.getTroopCount() && mine.getTroopsPerTick() > theirs.getTroopsPerTick()))
					{
						go = false;
					}
					else
					{
					rand = new Random();
					rand2 = new Random();
					mine = state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size()));
					theirs = ripePlanets.get(rand2.nextInt(ripePlanets.size()));
					if(index > (ripePlanets.size() * state.getMyPlanets().size() * 25))
					{
						go = false;
					}
					}
				}
				
				System.out.println("PLANET TO ATTACK: " + toString(theirs));
				System.out.println("MY PLANET: " + toString(mine));
				
				
				System.out.println("RIPE PLANETS: "+ripePlanets.size() + " --" + toString(ripePlanets));
				System.out.println("MY PLANETS: "+state.getMyPlanets().size() + " --" + toString(state.getMyPlanets()));
				
				client.startAttack(ripePlanets.get(rand.nextInt(ripePlanets.size())), state.getMyPlanets().get(rand.nextInt(state.getMyPlanets().size())));
				
				if(state.getMyPlanets().size() > ripePlanets.size())
				{
					System.out.println("OLD WINNING...");
				}
				else
				{
					System.out.println("OLD LOOSING...");
				}
				
				//long end = System.currentTimeMillis();
				//System.out.println(end - start);
			}
			//try {
				//Thread.sleep(100);
			//} catch (InterruptedException e) {
				//ignore
			//}
			state = client.getStatus();
			
		}
		
		System.out.println(state.getWhoWon() + " WON!");

	}

}
