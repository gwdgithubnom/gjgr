package org.gjgr.exam.tree;

import java.util.*;

/**
 * File Name : arithmetic4j - org.gjgr.exam.tree
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 9/2/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class BinTreeNode {
    private BinTreeNode leftChildren;
    private BinTreeNode rightChildren;
    private Object object;

    public BinTreeNode getLeftChildren() {
        return leftChildren;
    }

    public void setLeftChildren(BinTreeNode leftChildren) {
        this.leftChildren = leftChildren;
    }

    public BinTreeNode getRightChildren() {
        return rightChildren;
    }

    public void setRightChildren(BinTreeNode rightChildren) {
        this.rightChildren = rightChildren;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static List<BinTreeNode> preTraverse(BinTreeNode binTreeNode){
        List<BinTreeNode> binTreeNodes =new ArrayList<>();
        binTreeNodes.add(binTreeNode);
        if(binTreeNode.getLeftChildren()!=null){
            binTreeNodes.addAll(preTraverse(binTreeNode.getLeftChildren()));
        }
        if(binTreeNode.getRightChildren()!=null){
            binTreeNodes.addAll(preTraverse(binTreeNode.getRightChildren()));
        }
        return binTreeNodes;
    }


    public List<BinTreeNode> preTraverse(){
        List<BinTreeNode> binTreeNodes=new ArrayList<>();
        binTreeNodes.add(this);
        if(this.getLeftChildren()!=null) {
            binTreeNodes.addAll(preTraverse(this.getLeftChildren()));
        }
        if(this.getRightChildren()!=null){
            binTreeNodes.addAll(preTraverse(this.getRightChildren()));
        }
        return binTreeNodes;
    }


    public List<BinTreeNode> inTraverse(){
        List<BinTreeNode> binTreeNodes=new ArrayList<>();

        if(this.getLeftChildren()!=null){
            binTreeNodes.addAll(inTraverse(this.getLeftChildren()));
        }
        binTreeNodes.add(this);
        if(this.getRightChildren()!=null){
            binTreeNodes.addAll(inTraverse(this.getRightChildren()));
        }
        return binTreeNodes;
    }


    public static List<BinTreeNode> inTraverse(BinTreeNode binTreeNode){
        List<BinTreeNode> binTreeNodes=new ArrayList<>();
        if(binTreeNode.getLeftChildren()!=null){
            binTreeNodes.addAll(inTraverse(binTreeNode.getLeftChildren()));
        }
        binTreeNodes.add(binTreeNode);
        if(binTreeNode.getRightChildren()!=null){
            binTreeNodes.addAll(inTraverse(binTreeNode.getRightChildren()));
        }
        return binTreeNodes;
    }

    public List<BinTreeNode> postTraverse(){
        List<BinTreeNode> binTreeNodes=new ArrayList<>();
        if(this.getLeftChildren()!=null){
            binTreeNodes.addAll(postTraverse(this.getLeftChildren()));
        }
        binTreeNodes.add(this);
        if(this.getRightChildren()!=null){
            binTreeNodes.addAll(postTraverse(this.getRightChildren()));
        }
        return binTreeNodes;
    }


    public static List<BinTreeNode> postTraverse(BinTreeNode binTreeNode){
        List<BinTreeNode> binTreeNodes=new ArrayList<>();
        if(binTreeNode.getLeftChildren()!=null){
            binTreeNodes.addAll(inTraverse(binTreeNode.getLeftChildren()));
        }
        if(binTreeNode.getRightChildren()!=null){
            binTreeNodes.addAll(inTraverse(binTreeNode.getRightChildren()));
        }
        binTreeNodes.add(binTreeNode);
        return binTreeNodes;
    }


    public List<BinTreeNode> levelTraverse(){
        List<BinTreeNode> binTreeNodes=new ArrayList<>();
        binTreeNodes.addAll(levelTravser(this));
        return binTreeNodes;
    }

    public static List<BinTreeNode> levelTravser(BinTreeNode binTreeNode){
        Queue<BinTreeNode> queue=new ArrayDeque<>();
        List<BinTreeNode> binTreeNodes=new ArrayList<>();
        if(binTreeNode!=null)
            queue.offer(binTreeNode);
        while (!queue.isEmpty()){
            binTreeNodes.add(queue.peek());
            BinTreeNode binTreeNode1=queue.poll();
            if(binTreeNode1.getLeftChildren()!=null) {
                queue.add(binTreeNode1.getLeftChildren());
            }
            if(binTreeNode1.getRightChildren()!=null){
                queue.add(binTreeNode1.getRightChildren());
            }
        }
        return binTreeNodes;
    }

    public static void preOrder(BinTreeNode binTreeNode){
        Stack<BinTreeNode> stack=new Stack<>();
        BinTreeNode node=binTreeNode;
        while (node!=null||stack.size()>0){
            while (node!=null){
                doback(node);
                stack.push(node);
                node=node.getLeftChildren();
            }
            while ((stack.size()>0)){
                node=stack.pop();
                node=node.getLeftChildren();
            }
        }
    }

    public static void inOrder(BinTreeNode binTreeNode){
        Stack<BinTreeNode> stack=new Stack<>();
        BinTreeNode node=binTreeNode;
        while (node!=null|stack.size()>0){
            while (node!=null){
                stack.push(node);
                node=node.getLeftChildren();
            }
            if(stack.size()>0){
                node=stack.pop();
                doback(node);
                node=node.getRightChildren();
            }
        }
    }


    public static void postOrder(BinTreeNode binTreeNode){
        Stack<BinTreeNode> stack=new Stack<>();
        BinTreeNode node=binTreeNode;
        while (node!=null){
            for(;node.getLeftChildren()!=null;node.getLeftChildren()){
                stack.push(node);
            }
            ////当前结点无右子树或右子树已经输出
            while (node!=null&&(node.getRightChildren()!=null||node.getRightChildren()==binTreeNode)){
                ////操作上一个已输出结点
                doback(node);
                binTreeNode=node;
                if(stack.empty())
                    return;
                node=stack.pop();
            }
            //处理右子树
            stack.push(binTreeNode);
            node=node.getRightChildren();
        }
    }

    /**
     * do the node operation
     * @param binTreeNode
     * @author gwd
     */
    public static void doback(BinTreeNode binTreeNode){
        System.out.println(binTreeNode.getObject());
    }





}

class SimpleBinTreeTable{
    List<BinTreeNode> binTreeTable;
    BinTreeNode root;

    public SimpleBinTreeTable(){
        this.binTreeTable=new ArrayList<>();
        this.root=new BinTreeNode();
    }


}