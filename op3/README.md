# Part B – Test Cases (Disk Scheduling)

## Case 1 – Standard Request Queue

### Input

**Initial Head Position:** 15
**Initial Direction:** right
**Requests:** 98, 183, 37, 122, 14, 124, 65, 67

### FCFS (First Come First Serve)

**Service Order:** 53 &rarr; 98 &rarr; 183 &rarr; 37 &rarr; 122 &rarr; 14 &rarr; 124 &rarr; 65 &rarr; 67
**Total Head Movement:** 640  
**Average Seek Distance:** 80.00


### SSTF (Shortest Seek Time First)


**Service Order:** 53 &rarr; 65 &rarr; 67 &rarr; 37 &rarr; 14 &rarr; 98 &rarr; 122 &rarr; 124 &rarr; 183
**Total Head Movement:** 236
**Average Seek Distance:** 29.50

### SCAN 

**Service Order:** 53 &rarr; 65 &rarr; 67 &rarr; 98 &rarr; 122 &rarr; 124 &rarr; 183 &rarr; 37 &rarr; 14
**Total Head Movement:** 331
**Average Seek Distance:** 36.78

### Comparison (Case 1)

| Case | Algorithm | Total Head Movement | Average Seek Distance |
|------|-----------|---------------------|-----------------------|
| 1    | FCFS      | 640                 | 80.00                 |
| 1    | SSTF      | 236                 | 29.50                 |
| 1    | SCAN      | 331                 | 36.78                 |

---

## Case 2 – Clustered Requests

### Input

**Initial Head Position:** 50
**Initial Direction:** right
**Requests:** 45, 48, 52, 90, 150, 160


---

### FCFS

| Step | Current Head | Next Request | Distance Traveled |
|------|--------------|--------------|-------------------|
| 1    | 50           | 45           | 5                 |
| 2    | 45           | 48           | 3                 |
| 3    | 48           | 52           | 4                 |
| 4    | 52           | 90           | 38                |
| 5    | 90           | 150          | 60                |
| 6    | 150          | 160          | 10                |

**Total Head Movement:** 120  
**Average Seek Distance:** 20.00

---

### SSTF

// TODO: add SSTF results
---

---

### SCAN

// TODO: add SCAN results
---

---

## Comparison (Case 2)

| Case | Algorithm | Total Head Movement | Average Seek Distance |
|------|-----------|---------------------|-----------------------|
| 2    | FCFS      | 120                 | 20.00                 |

// TODO: add SSTF and SCAN results to comparisons
---

---

## Case 3 – Fairness Challenge

### Input

| Initial Head Position | Initial Direction |
|-----------------------|-------------------|
| 15                    | Right             |

**Requests:** 10, 12, 14, 16, 100, 102

---

### FCFS

| Step | Current Head | Next Request | Distance Traveled |
|------|--------------|--------------|-------------------|
| 1    | 15           | 10           | 5                 |
| 2    | 10           | 12           | 2                 |
| 3    | 12           | 14           | 2                 |
| 4    | 14           | 16           | 2                 |
| 5    | 16           | 100          | 84                |
| 6    | 100          | 102          | 2                 |

**Total Head Movement:** 97  
**Average Seek Distance:** 16.17

---

### SSTF

// TODO: add SSTF results
---

---

### SCAN

// TODO: add SCAN results
---

---

## Comparison (Case 3)

| Case | Algorithm | Total Head Movement | Average Seek Distance |
|------|-----------|---------------------|-----------------------|
| 3    | FCFS      | 97                  | 16.17                 |

// TODO: add SSTF and SCAN results to comparisons
---