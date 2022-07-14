class CustomIterator {

    private int[] l;
    private int idx;

    public CustomIterator(int[] l) {
        this.l = l;
        this.idx = 0;
    }

    public int next() {
        if (this.hasNext()) {
            return this.l[this.idx++];
        } else {
            return -1;
        }

    }

    public boolean hasNext() {
        return this.idx < this.l.length;
    }
}

/*[1,2 5]
next -> 1
next -> 2
hasNext -> true
next -> 5
hasNext -> false
next -> -1



class ComplexIterator {

   CustomIterator[] custIteratorArr;

   int size;

   int currIndex;

    public ComplexIterator(CustomIterator[] l) {
        custIteratorArr = l;
        size = l.length;
        currIndex = 0;
    }

    public int next() {
        if (hasNext()) {
            CustomIterator custIte = custIteratorArr[currIndex];
            if(custIte.hasNext()){
                return custIte.next();
            } else {
                currIndex++;
                return next();
            }
        } else {
            return -1;
        }
    }

    public boolean hasNext() {
        if(currIndex>=size){
            return false;
        }
        CustomIterator custIte = custIteratorArr[currIndex];
        if(custIte.hasNext()){
            return true;
        } else {
            currIndex++;
            return hasNext();
        }
    }
}

//new ComplexIterator(new CustomIterator([1,2]),new CustomIterator([7,8]),new CustomIterator([]))

https://walmart.zoom.us/j/99109310199


[
new CustomIterator([1,2]),
new CustomIterator([7, 8]),
new CustomIterator([]),
new CustomIterator([4]),
new CustomIterator([])
]



next  -> 1
next  -> 2
next -> 7
next -> 8
hasNext -> true
next -> 4
hasNext -> false




class Adverstiment {
    String url;
    int freq;
}



PriorityQueue<Adverstiment> maxHeap = new ProrityQueue<>((a,b)->b.freq-a.freq);

walmart.com 2
flipkart.com 3

[flip,walmart]


Adverstiment[] arr;
l,r 
Map<String, Index> map;

arr[foundIndex].freq

*/











