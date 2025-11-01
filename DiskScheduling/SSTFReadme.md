Start
  │
  ▼
Read number of requests → request
  │
  ▼
Read requested track positions → positions[]
  │
  ▼
Read current head position → currentPosition
  │
  ▼
Initialize:
  maxHeap = PriorityQueue (reverseOrder)
  minHeap = PriorityQueue (natural order)
  │
  ▼
For each position in positions[]:
  ├─ If position < currentPosition → add to maxHeap
  └─ Else → add to minHeap
  │
  ▼
Initialize seekTime = 0, completed = 0
  │
  ▼
WHILE completed < request:
  │
  ├─ topMax = maxHeap.peek()
  ├─ topMin = minHeap.peek()
  │
  ├─ IF topMax == null → next = minHeap.poll()
  │
  ├─ ELSE IF topMin == null → next = maxHeap.poll()
  │
  └─ ELSE → 
        IF |currentPosition - topMax| < |currentPosition - topMin| 
            → next = maxHeap.poll()
        ELSE 
            → next = minHeap.poll()
  │
  ▼
Update:
  seekTime += |currentPosition - next|
  currentPosition = next
  completed++
  │
  └─ Loop back to WHILE
  │
  ▼
After WHILE loop ends:
  Print seekTime
  │
  ▼
End
