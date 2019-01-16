import org.omg.SendingContext.RunTime;

/**
 * Lab 4: Generics <br />
 * The {@code GenericRedBlackTree} class <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 *              https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 *            </a>
 */
public class GenericRedBlackTree<K extends Comparable<K>, V> {
    public final boolean BLACK = false;
    public final boolean RED = true;

    private Node root = null;
    private int size = 0;


    /**
     * Search for the node by key, and return the corresponding value
     * @param key       {@code K} the key for searching
     * @return          {@code V} the value of the node, or {@code NULL} if not found
     */
    public V find(K key) {
        // TODO: Lab 4 Part 3-1 -- find an element from the tree

        return null;
    }

    /**
     * Insert an element to the tree
     * @param key       {@code K} the key of the new element
     * @param value     {@code V} the value of the new element
     */
    public void insert(K key, V value) {

        if (root == null) {
            root = new Node(key, value);
            return;
        }
        
        Node curr = root;
        while (curr.key() != null) {

            if (key.compareTo(curr.key()) > 0) {
                curr = curr.right;

            } else if (key.compareTo(curr.key()) < 0) {
                curr = curr.left;

            } else {
                curr.setValue(value);
                return;
            }

        }

        curr.set(key, value);

//        insertFixUp(curr);
    }

    private void insertFixUp(Node z) {

        Node y;

        while (z.parent.color == RED) {
            if (z.parent.isLeft()) {

                y = z.parent.parent.right;

                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;

                } else if (z.isRight()){
                    z = z.parent;

                }
            }
        }



    }

    private void leftRotate(Node x) {

        Node y = x.right;
        x.right = y.left;
        y.left.parent = x;
        y.parent = x.parent;

        if (x.parent == null) {
            root = y;

        } else if (x.isLeft()) {
            x.parent.left = y;

        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate( Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right.parent = x;
        y.parent = x.parent;

        if (x.parent == null) {
            root = y;

        } else if (x.isRight()) {
            x.parent.right = y;

        } else {
            x.parent.left = y;

        }

        y.right = x;
        x.parent = y;
    }

    /**
     * Remove an element from the tree
     * @param key       {@code K} the key of the element
     * @return          {@code V} the value of the removed element
     */
    public V remove(K key) {
        // TODO: Lab 4 Part 3-3 -- remove an element from the tree
        
        return null;
    }

    /**
     * Get the size of the tree
     * @return          {@code int} size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Cast the tree into a string
     * @return          {@code String} Printed format of the tree
     */
    @Override public String toString() {
        // TODO: Lab 4 Part 3-4 -- print the tree, where each node contains both value and color



        return null;
    }

    public void print() {

        printRecurse(root);

        System.out.println("--------------- Left rotate root");

        leftRotate(root.right);
        rightRotate(root.right);

        printRecurse(root);




    }

    public void printRecurse(Node node) {

        System.out.println(node);

        if (node.left != null) {
            System.out.print("<L> ");
            printRecurse(node.left);
//            System.out.print("</L> ");
        }

        if (node.right != null) {
            System.out.print("<R> ");
            printRecurse(node.right);
//            System.out.print("</R> ");

        }

    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {

        GenericRedBlackTree<Integer, String> rbt = new GenericRedBlackTree<>();
//        rbt.insert(-5, "-5");
        rbt.insert(0, "0");
        rbt.insert(-1, "-1");
        rbt.insert(1, "1");
        rbt.insert(2, "2");

        rbt.print();


//        GenericRedBlackTree<Integer, String> rbt = new GenericRedBlackTree<Integer, String>();
//        int[] keys = new int[10];
//        for (int i = 0; i < 10; i++) {
//            keys[i] = (int) (Math.random() * 200);
//            System.out.println(String.format("%2d Insert: %-3d ", i+1, keys[i]));
//            rbt.insert(keys[i], "\"" + keys[i] + "\"");
//        } // for (int i = 0; i < 10; i++)
//
//        System.out.println(rbt.root);                   // This helps to figure out the tree structure
//        System.out.println(rbt);
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(String.format("%2d Delete: %3d(%s)", i+1, keys[i], rbt.remove(keys[i])));
//            if ((i + 1) % 5 == 0) {
//                System.out.println(rbt);
//            } // if ((i + 1) % 5 == 0)
//        } // for (int i = 0; i < 10; i++)
    }

    private class Node implements Comparable{

        private K key;
        private V value;
        public boolean color = BLACK;

        public Node parent = null;
        public Node left = null;
        public Node right = null;

        public Node(K key, V value) {                   // By default, a new node is black with two NIL children
            this.key = key;
            this.value = value;

            if (key != null) {
                left = new Node(null, null);
                left.parent = this;

                right = new Node(null, null);
                right.parent = this;
            }
        }

        public K key()    { return key; }
        public V value()  { return value; }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean exists() {
            return (this.key() != null);
        }

        public boolean isLeft() {
            if (this.parent == null) {
                throw new RuntimeException("Node has no parent :(");
            }
            return (this.parent.left == this);
        }

        public boolean isRight() {
            if (this.parent == null) {
                throw new RuntimeException("Node has no parent :(");
            }
            return (this.parent.right == this);
        }

        public void set(K key, V value) {
            this.key = key;
            this.value = value;

            left = new Node(null, null);
            left.parent = this;

            right = new Node(null, null);
            right.parent = this;

            this.color = RED;


        }

        @Override
        public int compareTo(Object other) {

            try {
                Node tmp = ((Node) other);
                System.out.println("Comparing Node to Node");
                return this.key().compareTo(((Node) other).key());

            } catch (ClassCastException e) {
                throw new RuntimeException("Comparing Node to non-Node");
            }
        }

        /**
         * Print the tree node: red node wrapped by "<>"; black node by "[]"
         * @return          {@code String} The printed string of the tree node
         */
        @Override
        public String toString() {
            if (key == null)
                return "Nil";
            return (color == RED) ? "<" + key + "," + value + ">" : "[" + key + "," + value + "]";
        }
    }

}
