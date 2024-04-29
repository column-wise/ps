fibo_series = [0,1]
def fibo(n):
    for i in range(2, n+1):
        fibo_series.append(fibo_series[i-2]+fibo_series[i-1])
    
def solution(n):
    fibo(n)
    answer = fibo_series[n] % 1234567
    return answer