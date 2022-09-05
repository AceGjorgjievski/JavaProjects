package mk.ukim.finki.aps.kodovi.kod7_Tree.third;

public class BTreeTest {

    public static void main(String[] args) {
        BNode<Character> root, a, b, c;
        BTree<Character> tree = new BTree<Character>();
        root = tree.makeRoot('F');
        
        a = tree.addChild(root, BNode.LEFT, 'D');
        b = tree.addChild(a, BNode.LEFT, 'B');
        c = tree.addChild(b, BNode.LEFT, 'A');
        c = tree.addChild(b, BNode.RIGHT, 'C');
        c = tree.addChild(a, BNode.RIGHT, 'E');
        a = tree.addChild(root, BNode.RIGHT, 'G');
        b = tree.addChild(a, BNode.RIGHT, 'I');
        c = tree.addChild(b, BNode.LEFT, 'H');
        c = tree.addChild(b, BNode.RIGHT, 'J');
        
        tree.inorder();
        tree.inorderNonRecursive();
        tree.preorder();
        tree.preorderNonRecursive();
        tree.postorder();
        tree.postorderOppNonRecursive();
        
        tree.insertRight(a, 'P');
        tree.inorder();

        /*
        8
        0 48 ROOT
        1 46 LEFT 0
        2 45 LEFT 1
        3 47 RIGHT 1
        4 50 RIGHT 0

         */

    }
}