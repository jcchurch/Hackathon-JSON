package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import game.Planet;

import org.json.JSONException;
import org.json.JSONObject;

public class AlienInvasionClient {
	
	private static String hostname = "localhost";
	private static int port = 8000;
	private static final String connect = "/connect";
	private static final String attack = "/game/move";
	private static final String status = "/game/status";
	private int playerId, playerAuth, gameId;
	
	
	
	public static AlienInvasionClient connect(String host, int port)
	{
		AlienInvasionClient.hostname = host;
		AlienInvasionClient.port = port;
		AlienInvasionClient temp = new AlienInvasionClient();
		JSONObject req = new JSONObject();
		String response = "";
		JSONObject resp = null;
		String data;
		try {
			data = URLEncoder.encode(req.toString(), "UTF-8");
			// Send data
			URL url = new URL("http://"+hostname+":"+port+connect);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				response += line;
				// System.out.println(line);
			}
			wr.close();
			rd.close();
			resp = new JSONObject(response);
			temp.gameId = resp.getInt("GAMEID");
			temp.playerAuth = resp.getInt("AUTH");
			temp.playerId = resp.getInt("ID");
			

		} catch (Exception e) { // PRO error handling!
			//oops
		} 
		
		return temp;
	}
	
	public AlienGameState attack(Planet target, Planet base)
	{
		JSONObject req = new JSONObject();
		try {
			req.put("FROM", base.getPlanetId());
			req.put("TO", target.getPlanetId());
			req.put("ID", playerId);
			req.put("AUTH", playerAuth);
			req.put("GAMEID", gameId);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		String response = "";
		JSONObject resp = null;
		String data;
		try {
			data = URLEncoder.encode(req.toString(), "UTF-8");
			// Send data
			URL url = new URL("http://"+hostname+":"+port+attack);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				response += line;
				// System.out.println(line);
			}
			wr.close();
			rd.close();
			resp = new JSONObject(response);

		} catch (Exception e) { // PRO error handling!

		} 
		

		return AlienGameState.parse(resp, playerId);
	}
	
	public void startAttack(Planet target, Planet base)
	{
		AsycAttack async = new AsycAttack();
		async.from = base;
		async.to = target;
		try {
			async.url = new URL("http://"+hostname+":"+port+attack);
		} catch (MalformedURLException e) {
			//oops
		}
		Thread thread = new Thread(async);
		thread.start();
	}
	
	public AlienGameState getStatus()
	{
		JSONObject req = new JSONObject();
		try {
			req.put("ID", playerId);
			req.put("AUTH", playerAuth);
			req.put("GAMEID", gameId);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		String response = "";
		JSONObject resp = null;
		String data;
		try {
			data = URLEncoder.encode(req.toString(), "UTF-8");
			// Send data
			URL url = new URL("http://"+hostname+":"+port+status);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				response += line;
				// System.out.println(line);
			}
			wr.close();
			rd.close();
			resp = new JSONObject(response);

		} catch (Exception e) { // PRO error handling!

		} 
		
		return AlienGameState.parse(resp, playerId);
	}
	
	private class AsycAttack implements Runnable
	{
		public Planet to;
		public Planet from;
		public URL url;

		public void run() {
			JSONObject req = new JSONObject();
			try {
				req.put("FROM", from.getPlanetId());
				req.put("TO", to.getPlanetId());
				req.put("ID", playerId);
				req.put("AUTH", playerAuth);
				req.put("GAMEID", gameId);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			String response = "";
			JSONObject resp = null;
			String data;
			try {
				data = URLEncoder.encode(req.toString(), "UTF-8");
				// Send data
				URLConnection conn = url.openConnection();
				conn.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(
						conn.getOutputStream());
				wr.write(data);
				wr.flush();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
					response += line;
					// System.out.println(line);
				}
				wr.close();
				rd.close();
				resp = new JSONObject(response);

			} catch (Exception e) { // PRO error handling!

			} 
			

		}
		
	}

}
