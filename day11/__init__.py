

grid = {"n":(1, 0,-1), "nw": (1, -1, 0), "sw":(0, -1, 1), "s":(-1, 0,1), "se":(-1, 1,0), "ne":(0, 1,-1)}
def hex_move(starting, direction):
    move = grid[direction];
    return (starting[0] + move[0], starting[1] + move[1], starting[2] + move[2])


## https://www.redblobgames.com/grids/hexagons/
def distance(starting, end):
    return (abs(starting[0] - end[0]) + abs(starting[1] - end[1]) + abs(starting[2] - end[2])) / 2

starting = (0,0,0)
max = 0
with open("input.txt") as f:
    unparsed = f.read();
    end = starting
    moves = unparsed.split(",")
    for move in moves:
        end = hex_move(end, move)
        distancetravelled = distance(starting, end)
        max = distancetravelled if distancetravelled > max else max

print("Got to " + str(end) + " and went " + str(distance(starting, end)) + " and max ever was " + str(max))
