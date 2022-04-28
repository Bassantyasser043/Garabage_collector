# Garabage_collector
you will implement four of the known algorithms used in Garbage Collectors:
1. Mark & Sweep GC
2. Mark & Compact GC
3. Copy GC
4. G1 GC
#### The input to your program will be:
Total size of the heap in bytes (this is useful only in G1 GC implementation)

File heap.csv : this is a comma separated file with three columns. Each line represents the information about a single allocated object. This object may be used or not used.

object-identifier: a unique 6 digits identifier of the allocated objects. 

<b> memory-start:</b> the index of the first byte in heap memory representing this object.

<b> memory-end:</b> the index of the last byte in heap memory representing this object

<b> File roots.txt:</b> this is a text file that lists object-identifiers that are currently in use. Any object that can not be reached directly or indirectly from objects listed in this file should be considered as a garbage. Each line in this file contains a single object-identifier.

<b> File pointers.csv: </b> this file stores the dependencies between different objects. It is a comma separated file with two columns

<b> parent-identifier: </b> a unique identifier for the parent object

<b> child-identifier: </b> a unique identifier for the child object referenced by the parent

#### The output of your program will be:
<b> File new-heap.csv: </b> this is a comma separated file with the same structure of the heap.csv showing the new memory layout after running the garbage collector
