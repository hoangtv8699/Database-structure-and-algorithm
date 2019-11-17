package avltree;
import java.util.Scanner;

class AVLNode
{
    AVLNode left, right;
    String data;
    String means;
    int height;

    public AVLNode()
    {
        left = null;
        right = null;
        data = null;
        height = 0;
    }
    public AVLNode(String data, String means)
    {
        left = null;
        right = null;
        this.data = data;
        this.means = means;
        height = 0;
    }
}

class AVLTree
{
    private AVLNode root;

    public AVLTree()
    {
        root = null;
    }
    public boolean isEmpty()
    {
        return root == null;
    }
    public void makeEmpty()
    {
        root = null;
    }
    public void insert(String data, String means)
    {
        root = insert(data ,means, root);
    }
    private int height(AVLNode t )
    {
        return t == null ? -1 : t.height;
    }
    private int max(int lhs, int rhs)
    {
        return lhs > rhs ? lhs : rhs;
    }
    private boolean checklessthan(String x, String y){
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
    private AVLNode insert(String x ,String means, AVLNode t)
    {
        if (t == null)
            t = new AVLNode(x, means);
        else if ( checklessthan(x,t.data)) {
                    t.left = insert(x,means, t.left);
                    if (height(t.left) - height(t.right) == 2)
                        if (checklessthan(x ,t.left.data))
                            t = rotateWithLeftChild(t);
                        else
                            t = doubleWithLeftChild(t);
                } else {
                    t.right = insert(x ,means, t.right);
                    if (height(t.right) - height(t.left) == 2)
                        if (!checklessthan(x,t.right.data))
                            t = rotateWithRightChild(t);
                        else
                            t = doubleWithRightChild(t);
                }

        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    private AVLNode rotateWithLeftChild(AVLNode k2)
    {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    private AVLNode rotateWithRightChild(AVLNode k1)
    {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    private AVLNode doubleWithLeftChild(AVLNode k3)
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    private AVLNode doubleWithRightChild(AVLNode k1)
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(AVLNode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    public String  search(String val)
    {
        return search(root, val);
    }
    private String search(AVLNode r, String val)
    {
        String found = null;
        while (r != null)
        {
            if(r.data.equals(val)){
                found = r.means;
                break;
            }else if (checklessthan(val,r.data))
                r = r.left;
            else
                r = r.right;
            found = search(r, val);
        }
        return found;
    }
    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(AVLNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.data +" ");
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(AVLNode r)
    {
        if (r != null)
        {
            System.out.print(r.data +" ");
            preorder(r.left);
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(root);
    }
    private void postorder(AVLNode r)
    {
        if (r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.data +" ");
        }
    }

}

