class Heap:
	def __init__(self, L=[]):
		self.A = L
	def __str__(self):
		return str(self.A)

	def heapify_down(self, k, n): #k 시작노드 n 트리의 총 노드 개수
		while 2*k+1 < n:
			L, R = 2*k + 1, 2*k + 2
			if L < n and self.A[L] > self.A[k]: #본인 k와 자식 둘 중 큰 값
					m = L
			else:
					m = k
			if R < n and self.A[R] > self.A[m]:
					m = R
			if m != k:
					self.A[k], self.A[m] = self.A[m], self.A[k]
					k, m = m, k
			else:
					break

	def make_heap(self):
			n=len(self.A)
			for k in range(n-1, -1, -1):
				self.heapify_down(k, n) #리프노드
				#자식이 있으면 

	def heap_sort(self):
		n = len(self.A)
		for k in range(len(self.A)-1, -1, -1):
				self.A[0], self.A[k] = self.A[k], self.A[0]
				n = n - 1
				self.heapify_down(0, n)
		
S = [int(x) for x in input().split()]
H = Heap(S)
H.make_heap()
H.heap_sort()
print(H)
