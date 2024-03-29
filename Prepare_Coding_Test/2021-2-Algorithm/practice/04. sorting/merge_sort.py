def merge(A, i, j, k, l):
    B=[]
    first, last = i, l
    while i <= j and k <= l:
        if A[i] <= A[k]:
            B.append(A[i])
            i += 1
        else:
            B.append(A[k])
            k += 1
    for idx in range(i, j+1):
        B.append(A[idx])
    for idx in range(k, l+1):
        B.append(A[idx])
    for i in range(first, last + 1):
        A[i] = B[i - first]
    # i <= j and j < k <= l
    # 정렬된 두 부분 A[i..j]와 A[k..l]을 merge하는 함수
	
def m_sort(A, first, last):
    if first >= last:
        return
    b = (last - first) // 3
    m_sort(A, first, first + b)
    m_sort(A, first + b + 1, first + b * 2)
    m_sort(A, first + b * 2 + 1, last)
    merge(A, first, first + b, first + b + 1, first + b * 2)
    return merge(A, first, first + b * 2, first + b * 2 + 1, last)
    

	# 3-way merge sort - merge 함수를 이용해 적절히 합병한다

def check(A):
    for i in range(1, len(A)):
        if A[i-1] > A[i]:
            return False
    return A[0]+A[(len(A)//2)]+A[-1]

A = [int(x) for x in input().split()]
m_sort(A, 0, len(A)-1)
print(check(A))
