## 정수 n개의 합 2
>크기가 n인 수열이 주어졌을 때, 이 중 연속하는 k개의 원소들의 합 중 가장 큰 합을 구하는 프로그램을 작성해보세요.    
>**입력 형식**    
>첫 번째 줄에는 n과 k가 공백을 사이에 두고 주어집니다.    
>두 번째 줄에는 n개의 정수가 공백을 두고 차례대로 주어집니다.   
>* 2 ≤ n ≤ 100,000
>* 1 ≤ k ≤ n
>* -100 ≤ 원소의 크기 ≤ 100
>
>**출력 형식**    
>연속하는 k개의 합 중 가장 큰 값을 출력합니다.

**풀이** : 연속하는 숫자들의 합을 구해야 하므로 prefix_sum 리스트를 만들어 `prefix_sum[i] = arr[0]부터 arr[i-1] 까지의 합(1번째~i번째의 합)` 이 저장되도록 만들어 활용한다.

```python
import sys
n, k = tuple(map(int, input().split()))
arr = list(map(int, input().split()))
prefix_sum = [0] * (n+1)

# prefix_sum 채우기
for i in range(1, n+1):
    prefix_sum[i] = arr[i-1] + prefix_sum[i-1]

max_sum = -sys.maxsize # 최댓값을 구해야 하므로 정수 최소값으로 초기화

# max_sum 값 구하기
for i in range(k, n+1):
    tmp = prefix_sum[i] - prefix_sum[i-k] # 연속하는 k개의 원소의 합 구해서
    max_sum = max(max_sum, tmp) # 최소값으로 갱신

print(max_sum) # 답 출력
```

## 정수 n개의 합 3
>1에서 100 사이의 숫자로만 이루어진 n * n 크기의 2차원 격자 상태가 주어졌을 때, k * k 크기의 정사각형이 격자를 벗어나지 않게 잡았을 때 정사각형 내 숫자들의 합이 최대가 되도록 하는 프로그램을 작성해보세요.    
>**입력 형식**    
>첫 번째 줄에는 n과 k가 공백을 사이에 두고 주어집니다.    
>두 번째 줄 부터는 n개의 줄에 걸쳐 각 행에 해당하는 n개의 숫자가 공백을 두고 차례대로 주어집니다.   
>* 2 ≤ k ≤ n ≤ 500
>* 1 ≤ 원소의 크기 ≤ 100
>
>**출력 형식**    
>격자를 벗어나지 않는 k * k 크기의 정사각형 중 사각형 내 숫자들의 합이 최대가 되는 경우의 합을 출력합니다.

**풀이** : get_sum() 함수에 prefix sum을 사용해 사각형 내 숫자들의 합을 구하는 코드 작성 - [Prefix Sum 개념 참고](https://github.com/KimGaeun309/TIL/blob/main/Prepare_Coding_Test/2022-W-Coding_Camp/220212/220212_%EC%88%98%EC%97%85_%ED%95%84%EA%B8%B0.md#prefix-sum)

```python
import sys
n, k = tuple(map(int, input().split()))
arr = [
    list(map(int, input().split()))
    for _ in range(n)
]

prefix_sum = [[0 for _ in range(n+1)] for _ in range(n+1)]

# prefix sum 채우기
for i in range(1, n+1):
    for j in range(1, n+1):
        prefix_sum[i][j] = prefix_sum[i-1][j] + prefix_sum[i][j-1] - prefix_sum[i-1][j-1] + arr[i-1][j-1]

# 사각형 내 숫자들의 합을 구하는 get_sum 함수 작성
def get_sum(x1, y1, x2, y2):
    return prefix_sum[x2][y2] - prefix_sum[x1-1][y2] - prefix_sum[x2][y1-1] + prefix_sum[x1-1][y1-1]

max_sum = -sys.maxsize

# max_sum 구하기
for i in range(1, n-k+2):
    for j in range(1, n-k+2):
        tmp = get_sum(i, j, i+k-1, j+k-1)
        max_sum = max(max_sum, tmp)

print(max_sum) # 답 출력
```

## 부분 수열의 합이 k
>n개의 정수로 이루어진 수열에서 연속된 구간의 합을 구하려합니다.    
>모든 연속된 구간의 합 중에서 합이 k인 것의 개수를 구하는 프로그램을 작성해보세요.    
>**입력 형식**    
>첫 번째 줄에 n과 k가 공백을 두고 주어집니다.    
>두 번째 줄에 n개의 정수가 공백을 두고 주어집니다.  
>* 3 ≤ n ≤ 1,000
>* 1 ≤ k ≤ 1,000,000
>* 1 ≤ 주어진 숫자 ≤ 1,000,000
>
>**출력 형식**    
>모든 연속된 구간의 합 중에서 합이 k인 것의 개수를 출력합니다.

**풀이** : prefix sum을 미리 구해두고 이중 for loop을 돌며 모든 구간의 합을 구해 k와 비교하며 cnt 값을 구한다.

```python
n, k = tuple(map(int, input().split()))
nums = list(map(int, input().split()))

prefix_sum = [0] * (n+1)

# prefix_sum 채우기
for i in range(n):
    prefix_sum[i+1] = prefix_sum[i] + nums[i]

# s ~ e 구간의 합 구하는 함수
def get_sum(s, e):
    return prefix_sum[e] - prefix_sum[s-1]

cnt = 0   # 구간 합이 k인 횟수 세기
for e in range(1, n+1):
    for s in range(1, e):
        tmp_sum = get_sum(s, e)
        if tmp_sum == k:
            cnt += 1
print(cnt) # 답 출력
```

## 돌의 소속
>1부터 N까지 번호가 붙은 N개의 돌이 있습니다. 각 돌은 그룹 1, 2, 3 중 하나에 무조건 속합니다. 각 돌이 어떤 그룹에 속하는지 주어졌을 때, Q개의 돌 번호 범위마다 각 그룹의 돌이 몇개씩 있는지 구하는 프로그램을 작성해보세요.    
>**입력 형식**    
>첫 번째 줄에 N, Q가 주어집니다. Q는 주어진 범위의 개수입니다.     
>두 번째 줄부터 N개의 줄에 걸쳐 i + 1번째 줄에 i번 돌이 속한 그룹이 주어집니다.    
>N + 2번째 줄부터 Q개의 줄에 걸쳐 N + i + 1번째 줄에 i번째 범위에 대한 정보가 주어집니다. 각 범위는 a, b로 주어지며 이는 a이상 b이하를 의미합니다.
>* 1 ≤ N ≤ 100,000
>* 1 ≤ Q ≤ 100,000
>* a ≤ b
>
>**출력 형식**    
>첫 번째 줄부터 Q개의 줄에 걸쳐 i번째 줄에 i번째 범위 내에 있는 1, 2, 3번 그룹의 돌 개수를 공백을 사이에 두고 출력합니다.

**풀이 1** : 그룹별로 prefix sum 저장해 풀기. 

```python
n, q = tuple(map(int, input().split()))

group1, group2, group3 = [0] * (n+1), [0] * (n+1), [0] * (n+1)  
prefix1, prefix2, prefix3 = [0] * (n+1), [0] * (n+1), [0] * (n+1)

# 그룹별 돌 개수 저장 (group1[i] 가 1이면 i번 돌이 그룹1에 들어 있다는 뜻)
for i in range(1, n+1):
    num = int(input())
    if num == 1:
        group1[i] += 1
    elif num == 2:
        group2[i] += 1
    else:
        group3[i] += 1
        
# 질의 입력받아 저장
queries = [
    tuple(map(int, input().split()))
    for _ in range(q)
]

# 각 그룹의 prefix sum 구하기
for i in range(1, n+1):
    prefix1[i] = prefix1[i-1] + group1[i]
    prefix2[i] = prefix2[i-1] + group2[i]
    prefix3[i] = prefix3[i-1] + group3[i]

# 질의마다 각 그룹에 있는 범위 내의 돌 개수 출력
for x, y in queries:
    # group1
    print(prefix1[y] - prefix1[x-1], end=' ')
    # group2
    print(prefix2[y] - prefix2[x-1], end=' ')
    # group4
    print(prefix3[y] - prefix3[x-1])
```

** 풀이 2** : prefix_sum을 이차원 배열로 만들어 prefix_sum[j] 에 j번째 그룹의 prefix_sum 저장

```python
n, q = tuple(map(int, input().split()))

prefix_sum = [[0 for _ in range(n+1)] for _ in range(4)]

# 그룹 입력받아 prefix_sum 만들기
for i in range(1, n+1):
    group = int(input())
    prefix_sum[group][i] += 1
    for j in range(1, 4):
        prefix_sum[j][i] += prefix_sum[j][i-1]

# 질의 저장
queries = [
    tuple(map(int, input().split()))
    for _ in range(q)
]

# 질의마다
for x, y in queries:
    # 그룹별 구간에 있는 돌의 개수 저장
    for j in range(1, 4):
        print(prefix_sum[j][y] - prefix_sum[j][x-1], end=' ')
    print()
```

## 구간에 속한 문자의 개수
> n x m 크기의 2차원 격자가 알파벳 소문자 a, b, c 로만 이루어져 있습니다.
>k개의 질의에 대해 주어진 직사각형 범위 안에 각각 a b c 가 몇 개씩 있는지를 구하는 프로그램을 작성해보세요.    
>**입력 형식**    
>첫 번째 줄에 n, m, k가 공백을 사이에 두고 주어집니다.    
>두 번째 줄부터 n개의 줄에 걸쳐 각 행에 해당하는 길이가 m인 문자열이 공백없이 주어집니다.    
>n+2 번째 줄부터 k개의 줄에 걸쳐 직사각형 범위를 나타내는 (r_1, c_1, r_2, c_2) 값이 공백을 사이에 두고 주어집니다. 이는 r_1행 c_1열에서부터 r_2행 c_2열까지로 이루어진 직사각형을 의미합니다.    
>* 1 ≤ n, m ≤ 1,000
>* 1 ≤ k ≤ 100,000
>
>**출력 형식**     
>첫 번째 줄부터 k개의 줄에 걸쳐 각 주어진 범위 안에 속해있는 a, b, c 문자의 개수를 각각 공백을 사이에 두고 출력합니다.

**풀이** : 

```python

```