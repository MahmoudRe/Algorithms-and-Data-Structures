package main.Endterm2021;
import java.util.*;

public class MWST {

    /**
     * See the description of the exercise.
     */
    public static MWSTNode getCousin(MultiWaySearchTree tree, MWSTNode node) {

        if(node == null || tree == null) return null;

        /* Go up two level to get grand parent */
        MWSTNode parent = node.getParent();
        if(parent == null) return null;

        MWSTNode grandParent = parent.getParent();
        if(grandParent == null) return null;

        /* Now go down two level deep though the farthest right path */

        //Get last element (farthest right uncle)
        LinkedList<MWSTNode> uncles = grandParent.getChildren();
        int numUncles = uncles.size();

        //Traverse uncles from right to left until you find the farthest cousin
        while(numUncles > 0) {
            MWSTNode rightUncle = uncles.get(numUncles - 1);

            //Ensure the last element isn't null and not the parent
            if(rightUncle == null || rightUncle.equals(parent)) {
                numUncles--;
                continue;
            }

            //Get farthest cousin
            LinkedList<MWSTNode> cousins = rightUncle.getChildren();
            int numCousins = cousins.size();
            MWSTNode rightCousin = null;

            //Ensure that this uncle does have a children
            while(numCousins > 0 && rightCousin == null) {
                rightCousin = cousins.get(numCousins - 1);
                numCousins--;
            }

            //Go back to next uncle (from right to left) if no cousins found
            if(rightCousin == null) {
                numUncles--;
                continue;
            }

            return rightCousin;
        }

        return null;
    }

    /**
     * See the description of the exercise.
     */
    public static MWSTNode getUncle(MultiWaySearchTree tree, MWSTNode node) {
        if(node == null || tree == null) return null;

        /* Go up two level to get grand parent */
        MWSTNode parent = node.getParent();
        if(parent == null) return null;

        MWSTNode grandParent = parent.getParent();
        if(grandParent == null) return null;

        /* Now go down one level deep though the farthest left path */

        //Get first element (farthest left uncle)
        LinkedList<MWSTNode> uncles = grandParent.getChildren();
        int indexUncle = 0;

        //Traverse uncles from left to right to skip null values
        while(indexUncle < uncles.size()) {
            MWSTNode leftUncle = uncles.get(indexUncle);

            //Skip this if null or the parent
            if(leftUncle == null || leftUncle.equals(parent)) {
                indexUncle++;
                continue;
            }

            return leftUncle;
        }

        return null;
    }

    /**
     * See the description of the exercise.
     */
    public static boolean restrictedSearch(MultiWaySearchTree tree, int key) {

        if(tree == null) return false;

        MWSTNode root = tree.getRoot();
        if(root == null) return false;

        if(root.getKeys().size() < 0)
            return false;

//        for(int i = root.getKeys().size(); i >= 0; i--) {
//
//        }

        return false;
    }
}

class MultiWaySearchTree {

    private MWSTNode root;

    public MultiWaySearchTree(MWSTNode root) {
        this.root = root;
    }

    public MWSTNode getRoot() {
        return this.root;
    }

    public void setRoot(MWSTNode root) {
        this.root = root;
    }
}

class MWSTNode {

    private List<Integer> keys;

    private MWSTNode parent;

    private LinkedList<MWSTNode> children;

    public MWSTNode(List<Integer> keys, MWSTNode parent, LinkedList<MWSTNode> children) {
        this.keys = keys;
        this.parent = parent;
        // If children is left as null, create a list of m + 1 nulls, where m is the number of keys
        if (children == null) {
            this.children = new LinkedList<>(Collections.nCopies(keys.size(), null));
        } else {
            this.children = children;
        }
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public void setKeys(List<Integer> keys) {
        this.keys = keys;
    }

    public MWSTNode getParent() {
        return parent;
    }

    public void setParent(MWSTNode parent) {
        this.parent = parent;
    }

    public LinkedList<MWSTNode> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<MWSTNode> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MWSTNode mwstNode = (MWSTNode) o;
        return Objects.equals(keys, mwstNode.keys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keys);
    }
}
