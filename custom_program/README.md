# Custom Program Java
**Step for implementation**
1. Add a Slot in Niagara's program to state the inputs and outputs
for inputs add a flags "`Execute on Change`" and "`Summary`", for outputs only add a flags "Summary"
2. declare the input type of data
 
    | Point type | Niagara type | data type |
    | ---------- | ------------ | --------- |
    | Number     | Numeric      | double    |
    | Boolean    | Boolean      | bool      |
    | Enum       | Enum         | enum      |
    | String     | String       | string    |

3. Write a Java code to compile and use
   to get value use "`getValue()`" and adjust value use "`setValue()`"
   
   Example:
   ```Java
   BStatusNumeric valueA = getInA();
   BStatusNumeric valueB = getInB();
   BStatusNumeric addedValue = getOutA();

   addedValue.setValue(valueA.getValue() + valueB.getValue())
   ```