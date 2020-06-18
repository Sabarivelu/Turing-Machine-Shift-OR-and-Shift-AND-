def to_bin(n, width=32):
    "Pad a binary number to WIDTH bits wide"
    s = bin(n).replace("0b", "")
    return (("%0" + str(width) + "d") % int(s))

# pg 20
def shift_and(pattern, text, trace=False):
    m = len(pattern)
    n = len(text)

    B = {}                              # char -> bitmask table
    for i in range(m):
        B[pattern[i]] = (B.get(pattern[i], 0) | (1 << i))

    # search
    D = 0
    for i in range(n):
        D = ((D << 1) | 1) & (B.get(text[i], 0))
        if trace:
            print("%s & B[%c] : %s" % (to_bin(D), text[i], to_bin(B.get(text[i], 0))))
        if D & (1 << (m - 1)):
            print("Found at %d" % (i - m + 1))

def min_shift_and(p, t):
    m = len(p)
    B = {}                              # char -> bitmask table
    for i in range(m):
        B[p[i]] = (B.get(p[i], 0) | (1 << i))

    hit = 1 << (m - 1)
    a = 0                               # accumulator
    for i in range(len(t)):
        a = ((a << 1) | 1) & B.get(t[i], 0)
        if a & hit:
            print("found at %d" % (i - m + 1))

def neg(x):
    return 0b11111111111111111111111111111111 - x

def shift_or(pattern, text, trace=False):
    """Same as shift_and, but invert masks and use OR to
    avoid an | in the inner loop."""
    m = len(pattern)
    n = len(text)
    neg0 = neg(0)
    print("%s" %neg0)

    # build table
    B = {}                              # char -> bitmask table
    for i in range(m):
        #print(B.get(pattern[i]))
        B[pattern[i]] = (B.get(pattern[i], 0) | (1 << i))
        #print(B[pattern[i]])

    B = {k: neg(B[k]) for k in B}
    # complement all bit masks in B, complement bit mask
    a = neg0
    print("%s" %a)

    hit = (1 << (m - 1))

    for i in range(len(text)):
        a = (((a << 1) & neg0) | B.get(text[i], neg0))
        if trace:
            print("%s & B[%c] : %s" % (to_bin(a), text[i], to_bin(B.get(text[i], neg0))))
        if a & hit == 0:
            print("found at %d" % (i - m + 1))

# shift_and("bananas", "there are bananas in this string", True)
#shift_and("bananas", "there are bananas in this string", False)
#min_shift_and("bananas", "there are bananas in this string")
shift_or("bananas", "sabarivelubananas", True)