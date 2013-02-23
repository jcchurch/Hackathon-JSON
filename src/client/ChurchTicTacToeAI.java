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
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChurchTicTacToeAI {

    public static class Move {
        public int p;
        public int q;
        public int score;

        public Move(int q, int p, int score) {
            this.p = p;
            this.q = q;
            this.score = score;
        }
    }

	public static final String host = "http://localhost:8000";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws JSONException {

		int id, gId, auth;
		boolean gameRunning = true;
		int map[][] = new int[3][3];

		JSONObject nullObject = new JSONObject("{}");

		JSONObject playerInfo = doRequest(nullObject, "/connect");
		if (playerInfo == null) {
			System.out.println("/connect request failed");
			return;
		}
		id = playerInfo.getInt("ID");
		gId = playerInfo.getInt("GAMEID");
		auth = playerInfo.getInt("AUTH");

		JSONObject getStatusObj = new JSONObject();
		getStatusObj.put("ID", id);
		getStatusObj.put("GAMEID", gId);
		getStatusObj.put("AUTH", auth);

		while (gameRunning) {

			JSONObject gStatus = doRequest(getStatusObj, "/game/status");
			gameRunning = gStatus.getBoolean("RUNNING");

			int moves = 0;
			JSONArray board = gStatus.getJSONArray("BOARD");
			for (int i = 0; i < board.length(); i++) {
				for (int j = 0; j < board.getJSONArray(i).length(); j++) {
					map[i][j] = board.getJSONArray(i).getInt(j);
					if (map[i][j] == -1)
						moves++;
				}
			}

			if (moves == 0) {
				System.out.println("No avaliable moves");
				gameRunning = false;
				break;
			}
			
			if(gStatus.getInt("WHOWON")!=-1 && gStatus.getInt("WHOWON")!=id){
				System.out.println("We lost :(");
				break;
			}

			if (gStatus.getInt("TURN") == id && gameRunning) {

                Move m = getMove(map, id);
                System.out.println(m.q+" "+m.p+" "+m.score);

				JSONObject moveCmd = new JSONObject();
				JSONArray mCmd = new JSONArray();
				moveCmd.put("ID", id);
				moveCmd.put("GAMEID", gId);
				moveCmd.put("AUTH", auth);
				mCmd.put(0, "MOVE");
				mCmd.put(1, m.q);
				mCmd.put(2, m.p);
				moveCmd.put("COMMAND", mCmd);
				JSONObject moveRet = doRequest(moveCmd, "/game/move");
				System.out.println(moveRet.getBoolean("WON"));
				if (moveRet.getBoolean("WON") == true) {
					System.out.println("I WON!");
					gameRunning = false;
					break;
				}

			} else {

				// if it isn't our turn give the other guy 1 second to think
				// about it before checking again
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

    public static Move getMove(int[][] map, int id) {
        int moves_available = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == -1) {
                    moves_available += 1;
                }
            }
        }

        if (moves_available == 9) {
            Move m = new Move(2,2,0);
            return m; // Should be 2,2
        }

        Move best = new Move(-1,-1,-1000000);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = id;
                    int s = evaluate(map, id);

                    if (moves_available > 1) {
                        Move x = getMove(map, 1-id);
                        s += -x.score;
                    }

                    map[i][j] = -1;

                    if (s > best.score) {
                        best.q = i;
                        best.p = j;
                        best.score = s;
                    }
                }
            }
        }

        return best;
    }

    public static int evaluate(int[][] map, int id) {
        // Horizontal
        for(int i = 0; i < 2; i++) {
            if (map[i][0] == id && map[i][1] == id && map[i][2] == id) return 100;
        }

        // Vertical
        for(int i = 0; i < 2; i++) {
            if (map[0][i] == id && map[1][i] == id && map[2][i] == id) return 100;
        }

        // Diagonal
        if (map[0][0] == id && map[1][1] == id && map[2][2] == id) return 100;
        if (map[2][0] == id && map[1][1] == id && map[0][2] == id) return 100;

        return 0;
    }

	public static JSONObject doRequest(JSONObject req, String urlPath) {
		String response = "";
		JSONObject resp = null;
		String data;
		try {
			data = URLEncoder.encode(req.toString(), "UTF-8");
			// Send data
			URL url = new URL(host + urlPath);
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

		} catch (UnsupportedEncodingException e) { // PRO error handling!

		} catch (MalformedURLException e) {

		} catch (IOException e) {

		} catch (JSONException e) {

		}

		return resp;
	}

}
