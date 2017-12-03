
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


## Dat was ook meteen potverdomme het einde van de pret!
## Poging 3.
class Cell():
    def __init__(self, value, x, y):
        self.x = x
        self.y = y
        self.value = value


def get_second_distance_from_center(distance):
    current = 0
    current_len = 0
    valueslist = []
    while(current < distance):
        if first:
            current_len += 1
            first = False
        else:
            first = True

        ## Get my current X,Y
        my_x = my_x


        if(current >= distance):
            steps = current_len - (current - distance)
            return steps;


print(get_distance_from_center(289326))
print(get_second_distance_from_center(289326))
