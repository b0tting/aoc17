banks = False
with open("input.txt") as f:
    unparsed = f.read();


i = 0;
size = len(unparsed)

groupcount = 0
score = 0
garbagescore = 0
ingarbage = False
ignorenext = False
while(i < size):
    currchar = unparsed[i]
    if ignorenext:
        ignorenext = False
    elif ingarbage:
        if currchar == ">":
            ingarbage = False
        elif currchar == "!":
            ignorenext = True
        else:
            garbagescore += 1
    elif not ingarbage:
        if currchar == "{":
            groupcount += 1
        elif currchar == "<":
            ingarbage = True
        elif currchar == "}" and groupcount > 0:
            score += groupcount
            groupcount -= 1
    i += 1
print("Score is " + str(score) + " and garbage score is " + str(garbagescore))

