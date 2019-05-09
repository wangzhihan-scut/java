package ADT.tree;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author: wangzh
 * @Date: 2019/5/6 0006 9:35
 */

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    //  关于泛型的超类型/子类型通配符
    /**
     * 节点类
     * @param <AnyType>
     */
    private static class BinaryNode<AnyType>{
        BinaryNode(AnyType theElement){
            this(theElement, null, null);
        }
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt){
            element = theElement;
            left = lt;
            right = rt;
        }
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }
    private BinaryNode<AnyType> root;
    public BinarySearchTree(){
        root = null;
    }
    public void clear(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }
    public boolean contains(AnyType x){
        return contains(x, root);
    }
    public AnyType fingMin(){
        return findMin(root).element;
    }
    public AnyType findMax(){
        return findMax(root).element;
    }
    public void insert(AnyType x){
        root = insert(x, root);
    }
    public void remove(AnyType x){
        root = remove(x, root);
    }
    public void printTree(){
        if(isEmpty()){
            System.out.println("Empty tree");
        }else{
            printTree(root);
        }
    }


    private boolean contains(AnyType x, BinaryNode<AnyType> root){
        if(root == null){
            return false;
        }
        int compareResult = x.compareTo(root.element);
        if(compareResult < 0){
            return contains(x, root.left);
        }else if(compareResult > 0){
            return contains(x, root.right);
        }else{
            return true;
        }
    }
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root){
        if(root == null){
            return null;
        }else if(root.left == null){
            return root;
        }else{
            return findMin(root.left);
        }
    }
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root){
        if(root != null){
            while(root.right != null){
                root = root.right;
            }
        }
        return root;
    }
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> root){
        if(root == null){
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(root.element);
        if(compareResult < 0){
            root.left = insert(x, root.left);
        }else if(compareResult > 0){
            root.right = insert(x, root.right);
        }else{
        }
        return root;
    }
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> root){
        if(root == null){
            return null;
        }
        int compareResult = x.compareTo(root.element);
        if(compareResult < 0){
            root.left = remove(x, root.left);
        }else if(compareResult > 0){
            root.right = remove(x, root.right);
        }else{
            if(root.left != null && root.right != null){
                //找到左子树最小的作为根节点，缺点：删除操作导致右子树比左子树深
                root.element = findMin(root).element;
                root.left = remove(root.element, root.left);
            }else {
                root = (root.left == null)? root.right : root.left;
            }
        }
        return root;
    }
    private void printTree(BinaryNode<AnyType> root){
        if(root != null){
            printTree(root.left);
            System.out.println(root.element);
            printTree(root.right);
        }
    }

    public static void main(String[] args){
        BinarySearchTree<Integer> root = new BinarySearchTree<>();
        final int number = 5;
        Random random = new Random(1);
        for(int i = 0;i<number;i++){
            root.insert(random.nextInt());
        }
        root.printTree();
        System.out.println("-----------------------------");
        root.remove(1749940626);
        root.remove(892128508);
        root.printTree();
        System.out.println("-----------------------------");
        System.out.println(root.fingMin());
        System.out.println(root.findMax());
    }
}
