/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splaytree;

/**
 *
 * @author Hiddenpants-H
 */
import java.util.*;


/** Class Node **/
class SplayNode
{
    SplayNode left, right, parent;
    String element;

    /** Constructor **/
    public SplayNode()
    {
        this(null, null, null, null);
    }
    /** Constructor **/
    public SplayNode(String ele)
    {
        this(ele, null, null, null);
    }
    /** Constructor **/
    public SplayNode(String ele, SplayNode left, SplayNode right, SplayNode parent)
    {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.element = ele;
    }
}

/** Class SplayTree **/
class SplayTree
{
    public SplayNode root;
    private int count = 0;
    private ArrayList<String> top = new ArrayList<String>();
    Queue<SplayNode> queue = new LinkedList<SplayNode>();
    /** Constructor **/
    public SplayTree()
    {
        root = null;
    }

    /** Function to check if tree is empty **/
    public boolean isEmpty()
    {
        return root == null;
    }

    /** clear tree **/
    public void clear()
    {
        root = null;
        count = 0;
    }
    private boolean checkLessThan(String x, String y){
        int At = 0;
        boolean rs = false;
        while(x.length() > At && y.length() > At){
            if(x.charAt(At) < y.charAt(At)){
                return true;
            }else if(x.charAt(At) > y.charAt(At)){
                return false;
            }else
                At++;
        }
        if(x.length() == At){
            rs = true;
        }
        return rs;
    }
    /** function to insert element */
    public void insert(String ele)
    {
        SplayNode z = root;
        SplayNode p = null;
        while (z != null)
        {
            p = z;
            if(ele.equals(z.element)){
                Splay(z);
                return; 
            }
            if (checkLessThan(p.element,ele))
                z = z.right;
            else
                z = z.left;
        }
        z = new SplayNode();
        z.element = ele;
        z.parent = p;
        if (p == null)
            root = z;
        else if (checkLessThan(p.element,ele))
            p.right = z;
        else
            p.left = z;
        Splay(z);
        count++;
    }
    /** rotate **/
    public void makeLeftChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");

        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.right != null)
            c.right.parent = p;

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

    /** rotate **/
    public void makeRightChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    /** function splay **/
    private void Splay(SplayNode x)
    {
        while (x.parent != null)
        {
            SplayNode Parent = x.parent;
            SplayNode GrandParent = Parent.parent;
            if (GrandParent == null)
            {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            }
            else
            {
                if (x == Parent.left)
                {
                    if (Parent == GrandParent.left)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else
                    {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else
                {
                    if (Parent == GrandParent.left)
                    {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    }
                    else
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
//        queue.clear();
//        queue.add(root);
    }

    /** function to remove element **/
    public void remove(String ele)
    {
        SplayNode node = findNode(ele);
        remove(node);
    }

    /** function to remove node **/
    private void remove(SplayNode node)
    {
        if (node == null)
            return;

        Splay(node);
        if( (node.left != null) && (node.right !=null))
        {
            SplayNode min = node.left;
            while(min.right!=null)
                min = min.right;

            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        }
        else if (node.right != null)
        {
            node.right.parent = null;
            root = node.right;
        }
        else if( node.left !=null)
        {
            node.left.parent = null;
            root = node.left;
        }
        else
        {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
        node = null;
        count--;
    }

    /** Functions to count number of nodes **/
    public int countNodes()
    {
        return count;
    }

    /** Functions to search for an element **/
    public boolean search(String val)
    {
        return findNode(val) != null;
    }

    public SplayNode findNode(String ele)
    {
        SplayNode PrevNode = null;
        SplayNode z = root;
        while (z != null)
        {
            PrevNode = z;
            if (ele.equals(z.element)){
                Splay(z);
                return z;
            }else if (checkLessThan(z.element, ele))
                z = z.right;
            else
                z = z.left;
        }
//        if(PrevNode != null)
//        {
//            Splay(PrevNode);
//            return null;
//        }
        return null;
    }
    public ArrayList<String> suggest(){
        top.clear();
        queue.clear();
        queue.add(root);
        sug();
        return top;
    }
    
    public void sug(){
        if (top.size() >= 10) {
            return;
        }
        SplayNode node = queue.poll();
        if(node.left != null) queue.add(node.left);
        if(node.right != null) queue.add(node.right);
        top.add(node.element);
        sug();
    }
    

}


