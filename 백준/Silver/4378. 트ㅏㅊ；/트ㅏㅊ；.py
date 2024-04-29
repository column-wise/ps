import sys

keyboard = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./"
while True:
    a = sys.stdin.readline().rstrip()

    if a == '':
        break

    result = ""

    for idx in range(len(a)):
        if a[idx] != ' ':
            keyboard_idx = keyboard.index(a[idx])
            result += keyboard[keyboard_idx - 1]

        else:
            result += ' '

    print(result)