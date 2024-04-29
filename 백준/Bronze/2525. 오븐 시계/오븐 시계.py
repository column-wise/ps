startH, startM = map(int, input().split())
cookingTime = int(input())

minute = startM + cookingTime
cookingH = minute // 60
cookingM = minute % 60

endH = startH + cookingH
endM = cookingM

endH = endH % 24

print(endH, endM)