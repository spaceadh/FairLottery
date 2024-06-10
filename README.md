## Unfair Lottery - README

The purpose of this task is to evaluate whether the 'testee' will be able to develop a Java written Algorithm to do the following : 

1. Allow a user to be able to write prize values. It should take in Integer values only as prizes are to be written in Int Form. If not a number, it should exit the program gracefully.
2. Allow user to write the Names of the winners. The winners should be in String form. The names may or may fail to match the the number of(length) of the prizes writen earlier.
3. Sort the Prizes: First, it will need to sort the prize values in ascending order.
4. Initialize Allocation: Create a data structure to track the allocation of prizes to winners.  
NB: I chose Hashmap, due to its ability to deal with
    1. Flexibility with data types.
    2. Prevention of duplicate keys.
    3. Ease of implementation for key-value pair storage/addition(using'put') and retrieval(using 'get').
5. Allocate Prizes: Allocate prizes to winners in a way that minimizes the range of total values among them.

### Explaining the allocatePrizes function.

1. It initializes a map called allocation to keep track of which prizes are allocated to which winners.
2. It iterates through the sorted prize values in reverse order (from highest to lowest).
3. For each prize value, it finds the winner with the smallest total value assigned so far (minIndex) and allocate the prize to that winner.
4. It updates the total value for the winner (totalValues[minIndex]) by adding the allocated prize value.

## Edge Cases Considered and Handled
1. Invalid Input for Prize Values:
- If a non-integer value is entered for prize values, the program gracefully exits with an error message.

2. Unequal Number of Winners and Prizes:
- The program allows for an unequal number of winners and prizes. If the number of winners is less than the number of prizes, all prizes are allocated to winners sequentially until all prizes are allocated.
- If the number of winners is more than the number of prizes, the excess winners are ignored, following the Last In, First Out (LIFO) principle.

3. Empty Inputs: 
- The program handles cases where the user enters empty strings for prize values or winner names.


## How to run

1. Ensure that you have the JDK installed on your system. You can download it from the Oracle JDK website or use OpenJDK[https://openjdk.org/]
2. Git clone the project to your computer.
```copy
git clone https://github.com/spaceadh/FairLottery.git
```
3. Navigate to the project Directory
```copy
cd FairLottery
```
4. Since Java is a low-level language, it needs to be compiled first, before it can be ran.
```copy
javac FairLottery.java
```
5. Run the program
```copy
java FairLottery
```
6. Enter Prizes. The code allows only Int values, if you try to enter a string value, it will alert the user and exit the program grcefully. Example prizes include
```copy
100,300,400,500,600,700,800,200,900,1000
``` 
7. Enter Winner names: The name separation is done when a comma is detected. Example values include: 
```copy
Victor Alvin,Wachira,Purity,Waweru,James,Grace Ndanu,Joy Jerobon,Stephanie
```

The example output will be as follows : 
```
Fair distribution of prizes:
Victor Alvin:1000
Wachira:900
Purity:200,300
Waweru:800
James:700
Grace Ndanu:600
Joy Jerobon:500
Stephanie:400,100
```

NB. Environments currently on :
1. Ubuntu 22.04