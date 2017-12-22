inputs = "189,1,111,246,254,2,0,120,215,93,255,50,84,15,94,62".split(",")
rope = range(256)

##rope = "0,1,2,3,4".split(",")
##inputs = "3,4,1,5".split(",")

pos = 0
skip = 0
for input in inputs:
    pos_next = (pos + int(input)) % len(rope)
    newrope = []
    if(pos_next > pos):
        newrope.extend(rope[0:pos])
        newrope.extend(list(reversed(rope[pos:pos_next])))
        newrope.extend(rope[pos_next:len(rope)])
    else:
        wraparound = rope[pos:len(rope)] + rope[0:pos_next]
        wraparound = list(reversed(wraparound))
        if len(wraparound) == len(rope):
            newrope.extend(wraparound[-pos])
            newrope.extend(wraparound[0:(len(wraparound) - pos)])
        else:
            newrope.extend(wraparound[pos_next:len(wraparound)])
            newrope.extend(rope[pos_next:pos])
            newrope.extend(wraparound[0:pos_next])
    rope = newrope
    pos = (pos_next + skip) % len(rope)
    print(rope)
    skip += 1

