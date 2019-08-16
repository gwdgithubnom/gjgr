package org.gjgr.exam.contest.pdd;

import org.gjgr.exam.tools.InputOutObserver;
import sun.reflect.generics.tree.Tree;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * File Name : arithmetic4j - edu.gjgr.exam
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/12/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class TreeCommand {
    public static void main(String[] args){
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(TreeCommand.class);
        Scanner scanner=new Scanner(inputStream);
        TreeNode root=new TreeNode();
        Integer n=Integer.parseInt(scanner.nextLine());
        Integer i=0;
        List<TreeNode> treeNodes=new ArrayList<>();
     /*   String[] s=scanner.nextLine().split(" ");
        int k=Integer.parseInt(s[1]);
        root.setValue(s[0]);
        treeNodes.add(root);
        BinTreeNode node=root;
        while(i<n){
            s=scanner.nextLine().split(" ");
            k=Integer.parseInt(s[1]);
            BinTreeNode treeNode=new BinTreeNode();
            treeNode.setValue(s[0]);
            BinTreeNode parent=treeNodes.get(k);
            parent.getTreeNodes().add(treeNode);
            treeNodes.add(i,treeNode);
            i++;
        }*/
       treeNodes.add(0,root);
        while(i<n){
            String[] s=scanner.nextLine().split(" ");
            Integer k=Integer.parseInt(s[1]);
            TreeNode treeNode=new TreeNode();
            treeNode.setValue(s[0]);
            TreeNode parent=treeNodes.get(k+1);
            parent.getTreeNodes().add(treeNode);
            treeNodes.add(treeNode);
            i++;
        }
        //System.out.println(root.getTreeNodes().get(0).getValue());
        print(null,root.getTreeNodes());

    }
    public static void print(String s,List<TreeNode> treeNodes){
        if(s==null)
            s="";
        else
            s=s+"  ";
        if(treeNodes!=null){
            int i=1;
            for(TreeNode treeNode:treeNodes){
                if(i==treeNodes.size()){
                    printLre(s,treeNode.getValue());
                    print(s,treeNode.getTreeNodes());
                    break;
                }
                printPre(s,treeNode.getValue());
                print(s,treeNode.getTreeNodes());
                i++;
            }
        }
    }

    public static void printPre(String s1,String s2){
        System.out.println(s1+"|--"+s2);
    }
    public static void printLre(String s1,String s2){
        System.out.println(s1+"`--"+s2);
    }


}
class TreeNode{
    private String value;
    private List<TreeNode> treeNodes;

    public TreeNode(){
        this.treeNodes=new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }

    public void setTreeNodes(List<TreeNode> treeNodes) {
        treeNodes.sort(new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        this.treeNodes = treeNodes;
    }

}
