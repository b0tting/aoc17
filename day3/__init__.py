
## MarkO: So for every two twists in the spiral, the distance traveled over that length of the spiral increase by one
## Also, note that that the distance from the center to the twists are EQUAL TO THAT LENGTH.
def get_distance_from_center(distance):
    current = 1
    current_len = 0
    first = False
    while(current <= distance):
        if first:
            current_len += 1
            first = False
        else:
            first = True
        current = current + current_len
        if(current >= distance):
            steps = current_len - (current - distance)
            return steps;

def get_xy(distance):
    current = 1
    current_len = 0
    first = False
    while (current <= distance):
        if first:
            current_len += 1
            first = False
        else:
            first = True
        current = current + current_len
        if (current >= distance):
            steps = current_len - (current - distance)
            return steps;


def get_second_distance_from_center(distance):
    arr = [[0 for col in range(19)] for row in range(19)]
    arr[9][9] = 1
    arr[9][10] = 1
    arr[8][10] = 2
    count = 0
    currenty = 8
    currentx = 10
    while count < 110:
        newval = 0
        if(currenty > (len(arr) / 2)):
            ## Snaking back up
            if(arr[currenty - 1][currentx] == 0):
                currenty -= 1;
                newval += arr[currenty - 1][currentx -1];
                newval += arr[currenty][currentx-1];
                newval += arr[currenty + 1][currentx-1];
                newval += arr[currenty + 1][currentx];
            ## Snalong right
            elif(arr[currenty][currentx + 1] == 0):
                currentx += 1;
                newval += arr[currenty][currentx -1];
                newval += arr[currenty -1][currentx-1];
                newval += arr[currenty -1][currentx];
                newval += arr[currenty - 1][currentx + 1];
            ## Snaking down
            else:
                currenty += 1;
                newval += arr[currenty -1 ][currentx];
                newval += arr[currenty -1][currentx + 1];
                newval += arr[currenty][currentx + 1];
                newval += arr[currenty + 1][currentx + 1];
        ## Top half of spiral
        else:
            ## Snaking back down
            if(arr[currenty + 1][currentx] == 0):
                currenty += 1;
                newval += arr[currenty -1 ][currentx];
                newval += arr[currenty -1][currentx + 1];
                newval += arr[currenty][currentx + 1];
                newval += arr[currenty + 1][currentx + 1];
            ## Snaking left
            elif(arr[currenty][currentx - 1] == 0):
                currentx -= 1;
                newval += arr[currenty][currentx + 1];
                newval += arr[currenty + 1][currentx + 1];
                newval += arr[currenty + 1][currentx];
                newval += arr[currenty + 1][currentx - 1];
            ## Snaking up
            else:
                currenty -= 1;
                newval += arr[currenty - 1][currentx - 1];
                newval += arr[currenty][currentx - 1];
                newval += arr[currenty + 1][currentx - 1];
                newval += arr[currenty + 1][currentx];
        arr[currenty][currentx] = newval
        print_arr(arr)
        if(newval > distance):
            print("NEWVAL IS " + str(newval))
            break;

def print_arr(arr):
    result = ""
    for rows in arr:
        for col in rows:
            result += str(col).ljust(10)
        result += "\n"
    print result




##print(get_distance_from_center(289326))
print(get_second_distance_from_center(289326))

