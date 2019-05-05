package ADT.tree;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * 记得之前编译原理有关于后缀表达式部分的，复习一下
 * @Author: wangzh
 * @Date: 2019/5/5 0005 15:02
 */
public class CreateExpressTree {
    class TreeNode{
        char val;
        TreeNode left;
        TreeNode right;

        public TreeNode(char val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static Map<Character, Integer> expressPriorityMap = new HashMap<>();
    static Map<Character, Integer> expressInStackPriorityMap = new HashMap<>();
    static {
        expressPriorityMap.put('+', 2);
        expressPriorityMap.put('-', 2);
        expressPriorityMap.put('*', 4);
        expressPriorityMap.put('/', 4);

        expressInStackPriorityMap.put('+', 3);
        expressInStackPriorityMap.put('-', 3);
        expressInStackPriorityMap.put('*', 5);
        expressInStackPriorityMap.put('/', 5);
        expressInStackPriorityMap.put('(', 0);
    }
    boolean isOperator(Character character){
        Set<Character> set = new HashSet<>();
        set.add('+');
        set.add('-');
        set.add('*');
        set.add('/');
        if(set.contains(character)){
            return true;
        }
        return false;
    }
    boolean isNum(Character character){
        boolean flag = false;
        if((character >= '0' && character <= '9')
                || (character >= 'a' && character <= 'z')
                || (character >= 'A' && character <= 'Z')){
            flag = true;
        }
        return flag;
    }

    /**
     * 中缀表达式转后缀表达式
     * @param express
     * @return
     */
    String in2postExpress(String express){
        StringBuilder postExpress = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        for(int i = 0;i<express.length();i++){
            Character character = express.charAt(i);
            if(isNum(character)){
                postExpress.append(character);
            }else if(isOperator(character)){
                while(!operatorStack.empty() &&
                        expressInStackPriorityMap.get(operatorStack.peek()) > expressPriorityMap.get(character)){
                    postExpress.append(operatorStack.pop());
                }
                operatorStack.push(character);
            }else if(character == '('){
                operatorStack.push(character);
            }else if(character == ')'){
                while(operatorStack.peek() != '('){
                    postExpress.append(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }
        while(!operatorStack.isEmpty()){
            postExpress.append(operatorStack.pop());
        }
        return postExpress.toString();
    }

    /**
     * 后缀表达式转树
     * @param express
     * @return
     */
    TreeNode postExpressTree(String express){
        Stack<TreeNode> treeNodeStack = new Stack<>();
        for(int i = 0;i<express.length();i++){
            TreeNode current = new TreeNode(express.charAt(i), null, null);
            if(isOperator(current.val)){
                TreeNode rightNode = treeNodeStack.pop();
                TreeNode leftNode = treeNodeStack.pop();
                current.left = leftNode;
                current.right = rightNode;
            }
            treeNodeStack.push(current);
        }
        return treeNodeStack.peek();
    }

    public static void main(String[] args){
        CreateExpressTree createExpressTree = new CreateExpressTree();
        String express = "ab+cde+**";
        TreeNode root = createExpressTree.postExpressTree(express);
        System.out.println(root.right.left.val);

        String infixExpress = "a+b*c+((d*e+f)*g+h/i)";
        infixExpress = "a+b-v";
        System.out.println("前缀表达式：" + infixExpress);
        System.out.println("后缀表达式：" + createExpressTree.in2postExpress(infixExpress));
    }
}
