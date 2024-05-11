import sys

while True:
    try:
        x = int(sys.stdin.readline().rstrip())
        n = int(sys.stdin.readline().rstrip())
        l = []

        for i in range(n):
            l.append(int(sys.stdin.readline().rstrip()))

        l.sort()
        x *= 10e6
        min_pointer = 0
        max_pointer = n-1

        while min_pointer < max_pointer:
            if l[min_pointer] + l[max_pointer] == x:
                print("yes", l[min_pointer], l[max_pointer])
                break
            elif l[min_pointer] + l[max_pointer] < x:
                min_pointer += 1
            else:
                max_pointer -= 1
        else:
            print("danger")
    
    except:
        break