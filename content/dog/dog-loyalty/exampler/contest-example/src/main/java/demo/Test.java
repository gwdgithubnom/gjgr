package demo;

import java.util.*;
public class Test{
    static class Node{
        int value;
        List<Node> children = new ArrayList<>();
        Node(int v){
            value = v;
        }
        void  addChild( Node child){
            children.add(child);
        }
    }
   static int height = -1;
    public static void main(String args[]){

        Node root = new Node(0);
        Scanner scanner = new Scanner(System.in);
        int total  = scanner.nextInt();
        List<Node> nodeList = new ArrayList<Node>();
        for(int i = 0;i<total-1;i++){
            int left = scanner.nextInt();
            int right = scanner.nextInt();
           root = addChild(root,left,right);
        }
        getHeight(root,1);
        System.out.print(height);
    }
    public static void getHeight(Node node,int deep){
        if(node.children.size()==0)
        {
            height = Math.max(height,deep);
        }else {
            for(int i = 0 ;i<node.children.size();i++){
                getHeight(node.children.get(i),deep+1);
            }
        }
    }
    public static Node addChild(Node node,int l,int r){
        if(l == node.value) {
            Node child = new Node(r);
            node.addChild(child);
            return node;
        }
        else if(node.children==null){
            return node;
        }
        else {
            for(Node ch:node.children){
                ch = addChild(ch,l,r);
            }
        }
return node;
    }
}
