package ADT;


import java.util.Iterator;

/**
 * @Author: wangzh
 * @Date: 2019/4/28 0028 8:49
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>{
    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private AnyType [] theItems;

    public MyArrayList() {
        doClear();
    }
    public void clear(){
        doClear();
    }
    public void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public void trimSize(){
        ensureCapacity(size());
    }

    public AnyType get(int idx){
        if(idx < 0 || idx >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }
    public AnyType set(int idx, AnyType newVal){
        if(idx < 0 || idx >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    /**
     * 改变容量，复制到新的的数组
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize){
            return;
        }
        AnyType [] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for(int i = 0;i<size();i++){
            theItems[i] = old[i];

        }
//        List<AnyType> list = new ArrayList<>();
    }

    public boolean add(AnyType x){
        add(size(), x);
        return true;
    }
    public void add(int idx, AnyType x){
        //是否满了，否则扩容
        if(theItems.length == size()){
            ensureCapacity(size() * 2 + 1);
        }
        for(int i = size();i > idx;i--){
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize++;
    }
    public AnyType remove(int idx){
        AnyType removedItem = theItems[idx];
        for(int i = idx;i < size() - 1;i++){
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedItem;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public java.util.Iterator<AnyType> iterator(){
        return new ArrayListIterator();
    }
    public class ArrayListIterator implements java.util.Iterator<AnyType>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
//            if(current == 0) {
//                MyArrayList.this.remove(current);
//            }else{
//                MyArrayList.this.remove(current--);
//            }
            MyArrayList.this.remove(current);
        }
    }
    public static  void main(String[] args){
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        Iterator iterator1 = myArrayList.iterator();
        for(int i = 0;iterator1.hasNext();i++){
            if(i == 0){
                iterator1.remove();
            }
            System.out.println(iterator1.next());
        }
//        System.out.println(myArrayList.size());
    }
}
