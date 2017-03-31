import urllib.request
import json

url = "http://localhost:8000"

def connect():
    data = json.dumps({}).encode('UTF-8')
    req = urllib.request.Request(url+"/connect", data, {'Content-Type': 'application/json'})
    f = urllib.request.urlopen(req)
    response = f.read().decode('UTF-8')
    f.close()
    return json.loads(response)

def getStatus(gameid, id, auth):
    data = json.dumps({"GAMEID": gameid, "ID": id, "AUTH": auth}).encode('UTF-8')
    req = urllib.request.Request(url+"/game/status", data, {'Content-Type': 'application/json'})
    f = urllib.request.urlopen(req)
    response = f.read().decode('UTF-8')
    f.close()
    return json.loads(response)

def attack(id, auth, gameid, fromPlanet, toPlanet):
    data = json.dumps({
            "ID": id,
            "AUTH": auth,
            "GAMEID": gameid,
            "FROM": fromPlanet,
            "TO": toPlanet
           }).encode('UTF-8')
    req = urllib.request.Request(url+"/game/move", data, {'Content-Type': 'application/json'})
    f = urllib.request.urlopen(req)
    response = f.read().decode('UTF-8')
    f.close()
    return json.loads(response)

def getPlanets(myid, planets):
    myplanets = []
    notmyplanets = []

    for p in planets:
        if int(p["ID"]) == myid:
            myplanets.append(p)
        else:
            notmyplanets.append(p)

    return (myplanets, notmyplanets)

if __name__=='__main__':
    game = connect()
    myid = int(game["ID"])
    auth = game["AUTH"]
    gameid = game["GAMEID"]
    keepPlaying = True
    status = getStatus(gameid, myid, auth)

    while keepPlaying:
        (myplanets, notmyplanets) = getPlanets(myid, status["MAP"])

        bestFrom = myplanets[0]
        bestTo = notmyplanets[0]

        if bestFrom != -1 and bestTo != -1:
            attack(myid, auth, gameid, bestFrom, bestTo)

        status = getStatus(gameid, myid, auth)
        if status["WHOWON"] != -1:
            keepPlaying = False

    if myid == status["WHOWON"]:
        print(":)")
    else:
        print(":(")
