# Part B – Test Cases (Disk Scheduling)

## Case 1 – Standard Request Queue

### Input

**Initial Head Position:** 15  
**Initial Direction:** right  
**Requests:** 98, 183, 37, 122, 14, 124, 65, 67  

### FCFS (First Come First Serve)

**Service Order:** 53 → 98 → 183 → 37 → 122 → 14 → 124 → 65 → 67  
**Total Head Movement:** 640  
**Average Seek Distance:** 80.00  

### SSTF (Shortest Seek Time First)

**Service Order:** 53 → 65 → 67 → 37 → 14 → 98 → 122 → 124 → 183  
**Total Head Movement:** 236  
**Average Seek Distance:** 29.50  

### SCAN

**Service Order:** 53 → 65 → 67 → 98 → 122 → 124 → 183 → 37 → 14  
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

### FCFS (First Come First Serve)

**Service Order:** 50 → 45 → 48 → 52 → 90 → 150 → 160  
**Total Head Movement:** 120  
**Average Seek Distance:** 20.00  

### SSTF (Shortest Seek Time First)

**Service Order:** 50 → 48 → 45 → 52 → 90 → 150 → 160  
**Total Head Movement:** 120  
**Average Seek Distance:** 20.00  

### SCAN

**Service Order:** 50 → 52 → 90 → 150 → 160 → 48 → 45  
**Total Head Movement:** 303  
**Average Seek Distance:** 43.29  

### Comparison (Case 2)

| Case | Algorithm | Total Head Movement | Average Seek Distance |
|------|-----------|---------------------|-----------------------|
| 2    | FCFS      | 120                 | 20.00                 |
| 2    | SSTF      | 120                 | 20.00                 |
| 2    | SCAN      | 303                 | 43.29                 |

---

## Case 3 – Fairness Challenge

### Input

**Initial Head Position:** 15  
**Initial Direction:** right  
**Requests:** 10, 12, 14, 16, 100, 102  

### FCFS (First Come First Serve)

**Service Order:** 15 → 10 → 12 → 14 → 16 → 100 → 102  
**Total Head Movement:** 97  
**Average Seek Distance:** 16.17  

### SSTF (Shortest Seek Time First)

**Service Order:** 15 → 14 → 12 → 10 → 16 → 100 → 102  
**Total Head Movement:** 97  
**Average Seek Distance:** 16.17  

### SCAN

**Service Order:** 15 → 16 → 100 → 102 → 14 → 12 → 10  
**Total Head Movement:** 373  
**Average Seek Distance:** 53.29  

### Comparison (Case 3)

| Case | Algorithm | Total Head Movement | Average Seek Distance |
|------|-----------|---------------------|-----------------------|
| 3    | FCFS      | 97                  | 16.17                 |
| 3    | SSTF      | 97                  | 16.17                 |
| 3    | SCAN      | 373                 | 53.29                 |
