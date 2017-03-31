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

def heuristic(mine, your):
    speed = mine["SPEED"] / 125.0;
    attackRatio = float(mine["ATTACK"]) / float(your["DEFENSE"])
    growthRatio = float(your["GROWTH"]) / 3.0

    if your["GROWTH"] == 2:
        growthRatio = 3

    shouldAttack = 0
    if mine["TROOPS"] > 2:
        shouldAttack = 1
    if mine["TROOPS"] > 10:
        shouldAttack = 2

    owner = 2
    if your["ID"] > -1:
        owner = 1

    troopFactor = 0
    if mine["TROOPS"] * 2 > your["TROOPS"]:
        troopFactor = 1
    if mine["TROOPS"] > your["TROOPS"]:
        troopFactor = 2

    distance = 1.0 / ( (mine["X"]-your["X"])**2 + (mine["Y"]-your["Y"])**2 )**0.5

    return speed * attackRatio * troopFactor * growthRatio * owner * distance * shouldAttack

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

        bestFrom = -1
        bestTo = -1
        bestScore = -1000
        for mine in myplanets:
            for your in notmyplanets:
                h = heuristic(mine, your)
                if h > bestScore:
                    bestFrom = mine["PLANETID"]
                    bestTo = your["PLANETID"]
                    bestScore = h

        if bestFrom != -1 and bestTo != -1:
            attack(myid, auth, gameid, bestFrom, bestTo)

        status = getStatus(gameid, myid, auth)
        if status["WHOWON"] != -1:
            keepPlaying = False

    if myid == status["WHOWON"]:
        print(":)")
    else:
        print(":(")
