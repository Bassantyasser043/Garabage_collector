
# Garabage_collector
you will implement four of the known algorithms used in Garbage Collectors:
1. Mark & Sweep GC
2. Mark & Compact GC
3. Copy GC
4. G1 GC
### The input to your program will be:
Total size of the heap in bytes (this is useful only in G1 GC implementation).
<b> File heap.csv : </b> this is a comma separated file with three columns. Each line represents the information about a single allocated object. This object may be used or not used.
<b> - object-identifier:</b> a unique 6 digits identifier of the allocated objects. 
<b> - memory-start:</b> the index of the first byte in heap memory representing this object.
<b> - memory-end:</b> the index of the last byte in heap memory representing this object.
<b> - File roots.txt:</b> this is a text file that lists object-identifiers that are currently in use. Any object that can not be reached directly or indirectly from objects listed in this file should be considered as a garbage. Each line in this file contains a single object-identifier.
<b> - File pointers.csv: </b> this file stores the dependencies between different objects. It is a comma separated file with two columns.
<b> - parent-identifier: </b> a unique identifier for the parent object
<b> - child-identifier: </b> a unique identifier for the child object referenced by the parent

### The output of your program will be:
<b> - File new-heap.csv: </b> this is a comma separated file with the same structure of the heap.csv showing the new memory layout after running the garbage collector.

![77](https://user-images.githubusercontent.com/78346070/165865450-45453558-fcd8-4a34-b206-15401e1b4a02.jpg)#### G1 Garbage Collector

- The Garbage-First (G1) collector targets machines with multi-processor machines and applications with large memory. In this section, we will give you a simplified explanation for how it works. If you need to know how it really works in real life, please refer to [this link](https://www.oracle.com/technetwork/tutorials/tutorials-1876574.html), but this is out of the scope of this simple project.

- G1 divides the heap into regions (here we will assume it is 16 regions).

![](https://lh3.googleusercontent.com/iNeJZxkdc-j5gwkNTMYaXCdp6oW93ItmsRmA-9mbS68V1tnt-_pcTdTwYnnMzor2vtSYpwsKM0lXG_tk9NtN0AboR9GFmGoWMazJ1cKn3Qn76krUu6wCoB8plhjXPwG-JuCewA1_Whs3AcbMXg)

- G1 does the mark and sweep phase similar to the Mark & Sweep GC. In the figure below, Red means active objects, Gray means garbage objects, and Green means unallocated free area.

![](https://lh6.googleusercontent.com/Oy_lcC3W5XBPmjy7M-lJgrIG7ouYFe0wi6PqRh3llaYL-UyYhGHHWOHmABVz7FF9YcOxMpt6nyXhKfxyK6V7CHSSj6ZSKIGE-VmvSDZiXJ2ScKIWom2yA6S22eqTguO13tB88SLuENFx2OizIA)

- After the mark phase completes, G1 knows which regions are mostly empty (all objects in it are garbage). It collects these regions first, which usually yields a large amount of free space. As you see now all the regions that were fully Gray (Regions 2, 5, 6 and 12) become Green, but there still some garbage objects in the regions that contain active objects (Regions 1 and 9).

![](https://lh4.googleusercontent.com/qSoNhEDCdweiU60mG7MxVWtA5DJFaxcHSSnMbxfm3wqpkwsU1DP6_SwvccaMJ2z9WzCmjoDU6_Vtt6nWb61ogEV4u28VQGdVbm0Kh8StwrGGjq0yjiWQP0QbT52_5MkbtY_UyPcjyBbxi_wEDQ)

- Next, it copies the active objects into the free regions, this have the effect of defragment partially filled areas (see region 9 and 1 which are partially filled has moved into region 2)

![](https://lh6.googleusercontent.com/3KDJttysNRI4sbl8SwzXUr2Ujx-ZH0Cjzqan3HkXCLbrZX6F7QdupfygKiRKzH17dS-ugajkRcxS0DkYrNdKoaAGf762a9TRD1k-uslT8aKWaKuoe5IwMO5PhMJt7CckvTMSmBpkAlHJxg2jKw)

- Finally, it marks the old regions that the objects were moved from as free areas

![](https://lh4.googleusercontent.com/hGouopOU41xyiY7wP_29QTdfgox_i3mat3kYlUeoKrCYZkW-q4AfuvCtOFzvsTkPJ7bomlYB94wC98RGu88pea6vP802gJuk-FRvHunYFE5Ft-QGgwcP6r1iOk7N5kDLNHQ-aX6e4zxuvdWCTw)
- Assumption: (needed to have the same output for all the students).

1) The maximum object size is smaller than a single region size (1/16 of the total heap size).

2) You should copy objects to the first fully empty regions starting from the beginning of the heap. If the object does not fully fit in the remaining area of the region, then try the next region.

3) You must try to minimize the internal fragmentation within the regions as far as possible.
