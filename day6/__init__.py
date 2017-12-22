

def redistribute(banks):
    max_value = max(banks)
    spreadindex = banks.index(max_value)
    banks[spreadindex] = 0;
    while(max_value > 0):
        spreadindex = spreadindex + 1 if spreadindex + 1 < len(banks) else 0
        banks[spreadindex] += 1
        max_value -= 1
    return banks

banks = False
with open("input.txt") as f:
    unparsed = f.read();
    banks = map(int, unparsed.split());


##banks = [0,2,7,0]
count = 0
going = True
old_banks = []
while(going):
    old_banks.append(banks[:])
    banks = redistribute(banks)
    for old_bank in old_banks:
        if(old_bank == banks):
            print("Found at " + str(count - old_banks.index(old_bank) + 1) + " cycles ago" )
            going = False
            break

    count += 1

print("Final count was: " + str(count))


