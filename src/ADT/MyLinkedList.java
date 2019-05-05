package ADT;


/**
 * @Author: wangzh
 * @Date: 2019/4/29 0029 8:56
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    private static class Node<AnyType>{
        //私有类中数据的访问属性无关紧要
        public AnyType data;
        public Node<AnyType> prev;
        private Node<AnyType> next;
        public Node(AnyType d, Node<AnyType> p,  Node<AnyType> n){
            data = d;
            prev = p;
            next = n;
        }
    }
    public void clear(){
        doClear();
    }
    public MyLinkedList(){
        doClear();
    }
    public int size(){
        return theSize;
    }

    /**
     * public: 在表结尾处插入节点
     * @param x
     * @return
     */
    public boolean add(AnyType x){
        add(size(), x);
        return true;
    }

    /**
     * idx: [0, size()] 在idx节点前插入节点, idx=size()时表示在尾节点前添加
     * @param idx
     * @param x
     */
    public void add(int idx, AnyType x){
        addBefore(getNode(idx,0, size()), x);
    }

    /**
     * idx: [0, size()-1] 取得idx的节点值
     * @param idx
     * @return
     */
    public AnyType get(int idx){
        return getNode(idx).data;
    }


    /**
     * idx:[0, size()-1] 给表中idx节点赋值
     * @param idx
     * @param newVal
     * @return
     */
    public AnyType set(int idx, AnyType newVal){
        Node<AnyType> anyTypeNode = getNode(idx);
        AnyType oldVal = anyTypeNode.data;
        anyTypeNode.data = newVal;
        return oldVal;
    }

    /**
     * idx:[0, size()-1] 删除表中idx节点
     * @param idx
     * @return
     */
    public AnyType remove (int idx){
        AnyType data = remove(getNode(idx));
        return data;
    }


    private void doClear(){
        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, null, null);
        beginMarker.next = endMarker;
        endMarker.prev = beginMarker;
        theSize = 0;
    }
    private Node<AnyType> getNode(int idx){
//        if(idx > size() || idx < 0){
//            throw new IndexOutOfBoundsException();
//        }
        return getNode(idx, 0, size()-1);

    }
    private Node<AnyType> getNode(int idx, int lower, int upper){
        Node<AnyType> search;
        if(idx < lower || idx > upper){
            throw new IndexOutOfBoundsException();
        }
        if(idx < size() / 2){
            search = beginMarker;
            for(int i = 0;i<=idx;i++){
                search = search.next;
            }
        }else{
            search = endMarker;
            for(int i = 0;i<=size()-idx;i++){
                search = search.prev;
            }
        }
        return search;
    }
    private void addBefore(Node<AnyType> cur,  AnyType x){
        Node<AnyType> newNode = new Node<>(x, cur.prev, cur);
        cur.prev = cur.prev.next = newNode;
        theSize++;
    }
    private AnyType remove(Node<AnyType> p){
        p.prev.next = p.next;
        p.next.prev =p.prev;
        theSize--;
        return p.data;
    }

    @Override
    public java.util.Iterator<AnyType> iterator(){
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements java.util.Iterator<AnyType>{

        private Node<AnyType> nextNode = beginMarker.next;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return nextNode != endMarker;
        }

        @Override
        public AnyType next() {
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            AnyType data = nextNode.data;
            nextNode = nextNode.next;
            okToRemove = true;
            return data;
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(nextNode.prev);
            okToRemove = false;
        }
    }
}
