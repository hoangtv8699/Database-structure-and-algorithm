/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdtnearest;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class KDNode {
    int axis;
    double[] x;
    int id;
    boolean checked;
    boolean orientation;
    double distance;

    KDNode Parent;
    KDNode Left;
    KDNode Right;

    public KDNode(double[] x0, int axis0) {
        x = new double[2];
        axis = axis0;
        for (int k = 0; k < 2; k++)
            x[k] = x0[k];

        Left = Right = Parent = null;
        checked = false;
        id = 0;
    }

    public KDNode FindParent(double[] x0) {
        KDNode parent = null;
        KDNode next = this;
        int split;
        while (next != null) {
            split = next.axis;
            parent = next;
            if (x0[split] > next.x[split])
                next = next.Right;
            else
                next = next.Left;
        }
        return parent;
    }

    public KDNode Insert(double[] p) {
        //x = new double[2];
        KDNode parent = FindParent(p);
        if (equal(p, parent.x, 2) == true)
            return null;

        KDNode newNode = new KDNode(p, parent.axis + 1 < 2 ? parent.axis + 1
                : 0);
        newNode.Parent = parent;

        if (p[parent.axis] > parent.x[parent.axis]) {
            parent.Right = newNode;
            newNode.orientation = true; //
        } else {
            parent.Left = newNode;
            newNode.orientation = false; //
        }

        return newNode;
    }

    boolean equal(double[] x1, double[] x2, int dim) {
        for (int k = 0; k < dim; k++) {
            if (x1[k] != x2[k])
                return false;
        }

        return true;
    }

    double distance2(double[] x1, double[] x2, int dim) {
        double S = 0;
        for (int k = 0; k < dim; k++)
            S += (x1[k] - x2[k]) * (x1[k] - x2[k]);
        return S;
    }
}

class KDTree {
    KDNode Root;

    double d_min;
    KDNode[] nearest_neighbour = new KDNode[5];
    int countNear = 0;

    int KD_id;

    int nList;

    KDNode CheckedNodes[];
    int checked_nodes;
    KDNode List[];


    public KDTree(int i) {
        Root = null;
        KD_id = 1;
        nList = 0;
        List = new KDNode[i];
        CheckedNodes = new KDNode[i];

    }

    Comparator<KDNode> axisX = new Comparator<KDNode>() {
        @Override
        public int compare(KDNode sv1, KDNode sv2) {
            if (sv1.x[0] < sv2.x[0]) {
                return -1;
            } else {
                if (sv1.x[0] == sv2.x[0]) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    };
    Comparator<KDNode> axisY = new Comparator<KDNode>() {
        @Override
        public int compare(KDNode sv1, KDNode sv2) {
            if (sv1.x[1] < sv2.x[1]) {
                return -1;
            } else {
                if (sv1.x[1] == sv2.x[1]) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    };

    Comparator<KDNode> compare = new Comparator<KDNode>() {
        @Override
        public int compare(KDNode sv1, KDNode sv2) {
            if (sv1.distance < sv2.distance) {
                return -1;
            } else {
                if (sv1.distance == sv2.distance) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    };
    public boolean add(double[] x) {
        if (nList >= 2000000 - 1)
            return false; // can't add more points

        if (Root == null) {
            Root = new KDNode(x, 0);
            Root.id = KD_id++;
            List[nList++] = Root;
        } else {
            KDNode pNode;
            if ((pNode = Root.Insert(x)) != null) {
                pNode.id = KD_id++;
                List[nList++] = pNode;
            }
        }

        return true;
    }

    public void add(KDNode[] a, int head, int tail, int axis) {
        int mid = (head + tail) / 2;
        if (axis % 2 == 0) {
            Arrays.sort(a, head, tail, axisX);
        } else Arrays.sort(a, head, tail, axisY);
        add(a[mid].x);
        if (head < mid) {
            add(a, head, mid - 1, axis + 1);
        }
        if (mid < tail) {
            add(a, mid + 1, tail, axis + 1);
        }
    }

    public KDNode[] find_nearest(double[] x) {
        if (Root == null)
            return null;
        checked_nodes = 0;
        KDNode parent = Root.FindParent(x);
        d_min = Root.distance2(x, parent.x, 2);
        parent.distance = d_min;
        parent.checked = true;
        nearest_neighbour[countNear] = parent;
        search_parent(parent, x);
        uncheck();

        return nearest_neighbour;
    }

    public KDNode search_parent(KDNode parent, double[] x) {


        KDNode search_root = parent;
        while (parent != null) {
            check_subtree(parent, x);
            search_root = parent;
            parent = parent.Parent;
        }

        return search_root;
    }

    public void check_subtree(KDNode node, double[] x) {
        if ((node == null) || node.checked)
            return;

        CheckedNodes[checked_nodes++] = node;
        node.checked = true;
        set_bounding_cube(node, x);

        int dim = node.axis;
        double d = node.x[dim] - x[dim];

        if (d * d > d_min) {
            if (node.x[dim] > x[dim])
                check_subtree(node.Left, x);
            else
                check_subtree(node.Right, x);
        } else {
            check_subtree(node.Left, x);
            check_subtree(node.Right, x);
        }
    }

    public void set_bounding_cube(KDNode node, double[] x) {
        if (node == null)
            return;
        double d = (node.x[0] - x[0]) * (node.x[0] - x[0]) + (node.x[1] - x[1]) * (node.x[1] - x[1]);
        node.distance = d;
        if (countNear < 4) {
            nearest_neighbour[++countNear] = node;
            if(countNear == 4){
                Arrays.sort(nearest_neighbour,compare);
                d_min = nearest_neighbour[4].distance;
            }
        }else{
            if (d < d_min){
                nearest_neighbour[4] = node;
                Arrays.sort(nearest_neighbour,compare);
                d_min = nearest_neighbour[4].distance;
            }
        }
    }

    public void uncheck() {
        for (int n = 0; n < checked_nodes; n++) {
            CheckedNodes[n].checked = false;
            //System.out.println(CheckedNodes[n].x[0] + " " + CheckedNodes[n].x[1]);
        }
    }

}

public class KDTNearest {
    public static void main(String args[]) throws IOException {
        int numpoints = 1000;
        KDNode[] a = new KDNode[numpoints];
        KDTree kdt = new KDTree(numpoints);
        double x[] = new double[2];
        Random rd = new Random();
        for (int i = 0; i < 1000; i++) {
            x[0] = rd.nextInt(1000);
            x[1] = rd.nextInt(1000);
            KDNode b = new KDNode(x, 0);
            a[i] = b;
        }
        kdt.add(a, 0, numpoints - 1, 0);

//        x[0] = 4;
//        x[1] = 7;
//        kdt.add(x);
//
//        x[0] = 7;
//        x[1] = 13;
//        kdt.add(x);
//
//        x[0] = 9;
//        x[1] = 4;
//        kdt.add(x);
//
//        x[0] = 11;
//        x[1] = 10;
//        kdt.add(x);
//
//        x[0] = 16;
//        x[1] = 10;
//        kdt.add(x);
//
//        x[0] = 15;
//        x[1] = 3;
//        kdt.add(x);
//
//        x[0] = 14;
//        x[1] = 11;
//        kdt.add(x);

        System.out.println("Nhap toa do diem can search");
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        double sx = Double.parseDouble(br.readLine());
        double sy = Double.parseDouble(br.readLine());

        double s[] = {sx, sy};
        KDNode[] kdn = kdt.find_nearest(s);
        System.out.println("The nearest neighbor is: ");
        for(int i = 0;i < 5; i++){
            System.out.println(kdn[i].distance+ " " + kdn[i].x[0] + " " + kdn[i].x[1]);
        }
    }
}
