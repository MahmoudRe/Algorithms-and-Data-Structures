package main.Endterm2021;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MWSTTest {

    /*
     *        root: [24 42 80]
     *             /   |      \
     *            /    |       \
     *           /     |        \
     *          /      |         \
     *      [5 8 10]  [31 37]      [100]
     *        |          |  \          \
     *        |          |   \          \
     *        |          |    \          \
     *       [6]      [33 36] [39]       [120]
     */
    @Test
    public void testExampleCousin() {
        MWSTNode root = new MWSTNode(Arrays.asList(24, 42, 84), null, null);
        MWSTNode firstChild = new MWSTNode(Arrays.asList(5, 8, 10), root, null);
        MWSTNode secondChild = new MWSTNode(Arrays.asList(31, 37), root, null);
        MWSTNode fourthChild = new MWSTNode(Arrays.asList(100), root, null);
        MWSTNode firstGrandChild = new MWSTNode(Arrays.asList(6), firstChild, null);
        firstChild.setChildren(new LinkedList<>(Arrays.asList(null, firstGrandChild, null, null)));
        MWSTNode secondGrandChild = new MWSTNode(Arrays.asList(33, 36), secondChild, null);
        MWSTNode thirdGrandChild = new MWSTNode(Arrays.asList(39), secondChild, null);
        secondChild.setChildren(new LinkedList<>(Arrays.asList(null, secondGrandChild, thirdGrandChild)));
        MWSTNode fourthGrandChild = new MWSTNode(Arrays.asList(120), fourthChild, null);
        fourthChild.setChildren(new LinkedList<>(Arrays.asList(null, fourthGrandChild)));
        root.setChildren(new LinkedList<>(Arrays.asList(firstChild, secondChild, null, fourthChild)));
        MultiWaySearchTree tree = new MultiWaySearchTree(root);
        assertEquals(fourthGrandChild, MWST.getCousin(tree, thirdGrandChild));
    }

    /*
     *        root: [24 42 80]
     *             /   |      \
     *            /    |       \
     *           /     |        \
     *          /      |         \
     *       [5 8 10] [31]      [100]
     *         |                    \
     *         |                     \
     *         |                      \
     *        [6]                     [120]
     */
    @Test
    public void testExampleUncle() {
        MWSTNode root = new MWSTNode(Arrays.asList(24, 42, 84), null, null);
        MWSTNode firstChild = new MWSTNode(Arrays.asList(5, 8, 10), root, null);
        MWSTNode secondChild = new MWSTNode(Arrays.asList(31), root, null);
        MWSTNode fourthChild = new MWSTNode(Arrays.asList(100), root, null);
        MWSTNode grandChild = new MWSTNode(Arrays.asList(6), firstChild, null);
        firstChild.setChildren(new LinkedList<>(Arrays.asList(null, grandChild, null, null)));
        MWSTNode otherGrandChild = new MWSTNode(Arrays.asList(120), fourthChild, null);
        secondChild.setChildren(new LinkedList<>(Arrays.asList(null, otherGrandChild)));
        root.setChildren(new LinkedList<>(Arrays.asList(firstChild, secondChild, null, fourthChild)));
        MultiWaySearchTree tree = new MultiWaySearchTree(root);
        assertEquals(firstChild, MWST.getUncle(tree, otherGrandChild));
    }

    /*
     *        root: [24 42 80]
     *             /   |      \
     *            /    |       \
     *           /     |        \
     *          /      |         \
     *       [5 8 10] [31]      [100]
     *                   \
     *                    \
     *                     \
     *                    [33 40]
     *                       |
     *                       |
     *                       |
     *                      [34]
     *                          \
     *                           \
     *                            \
     *                            [35]
     */
    @Test
    public void testExampleSearch() {
        MWSTNode root = new MWSTNode(Arrays.asList(24, 42, 84), null, null);
        MWSTNode firstChild = new MWSTNode(Arrays.asList(5, 8, 10), root, null);
        MWSTNode secondChild = new MWSTNode(Arrays.asList(31), root, null);
        MWSTNode fourthChild = new MWSTNode(Arrays.asList(100), root, null);
        MWSTNode grandChild = new MWSTNode(Arrays.asList(33, 40), secondChild, null);
        secondChild.setChildren(new LinkedList<>(Arrays.asList(null, grandChild)));
        MWSTNode grandGrandChild = new MWSTNode(Arrays.asList(34), grandChild, null);
        grandChild.setChildren(new LinkedList<>(Arrays.asList(null, grandGrandChild, null)));
        MWSTNode grandGrandGrandChild = new MWSTNode(Arrays.asList(35), grandGrandChild, null);
        grandGrandChild.setChildren(new LinkedList<>(Arrays.asList(null, grandGrandGrandChild)));
        root.setChildren(new LinkedList<>(Arrays.asList(firstChild, secondChild, null, fourthChild)));
        MultiWaySearchTree tree = new MultiWaySearchTree(root);
        assertTrue(MWST.restrictedSearch(tree, 8));
        assertFalse(MWST.restrictedSearch(tree, 44));
        assertFalse(MWST.restrictedSearch(tree, 35));
    }

    /*
     *        root: [24 42 80]
     *             /   |      \
     *            /    |       \
     *           /     |        \
     *          /      |         \
     *      [5 8 10]  [31 37]      [100]
     *        |          |  \
     *        |          |   \
     *        |          |    \
     *       [6]      [33 36] [39]
     */
    @Test
    public void testCousinNull() {
        MWSTNode root = new MWSTNode(Arrays.asList(24, 42, 84), null, null);
        MWSTNode firstChild = new MWSTNode(Arrays.asList(5, 8, 10), root, null);
        MWSTNode secondChild = new MWSTNode(Arrays.asList(31, 37), root, null);
        MWSTNode fourthChild = new MWSTNode(Arrays.asList(100), root, null);
        MWSTNode firstGrandChild = new MWSTNode(Arrays.asList(6), firstChild, null);
        firstChild.setChildren(new LinkedList<>(Arrays.asList(null, firstGrandChild, null, null)));
        MWSTNode secondGrandChild = new MWSTNode(Arrays.asList(33, 36), secondChild, null);
        MWSTNode thirdGrandChild = new MWSTNode(Arrays.asList(39), secondChild, null);
        secondChild.setChildren(new LinkedList<>(Arrays.asList(null, secondGrandChild, thirdGrandChild)));
        root.setChildren(new LinkedList<>(Arrays.asList(firstChild, secondChild, null, fourthChild)));
        MultiWaySearchTree tree = new MultiWaySearchTree(root);
        assertNull(MWST.getCousin(null, null));
        assertNull(MWST.getCousin(null, fourthChild));
        assertNull(MWST.getCousin(tree, null));
    }

    /*
     *        root: [24 42 80]
     *             /   |      \
     *            /    |       \
     *           /     |        \
     *          /      |         \
     *       [5 8 10] [31]      [100]
     *         |
     *         |
     *         |
     *        [6]
     */
    @Test
    public void testUncleNull() {
        MWSTNode root = new MWSTNode(Arrays.asList(24, 42, 84), null, null);
        MWSTNode firstChild = new MWSTNode(Arrays.asList(5, 8, 10), root, null);
        MWSTNode secondChild = new MWSTNode(Arrays.asList(31), root, null);
        MWSTNode fourthChild = new MWSTNode(Arrays.asList(100), root, null);
        MWSTNode grandChild = new MWSTNode(Arrays.asList(6), firstChild, null);
        firstChild.setChildren(new LinkedList<>(Arrays.asList(null, grandChild, null, null)));
        root.setChildren(new LinkedList<>(Arrays.asList(firstChild, secondChild, null, fourthChild)));
        MultiWaySearchTree tree = new MultiWaySearchTree(root);
        assertNull(MWST.getUncle(null, null));
        assertNull(MWST.getUncle(null, grandChild));
        assertNull(MWST.getUncle(tree, null));
    }

    @Test
    public void testSearchNull() {
        assertFalse(MWST.restrictedSearch(null, 8));
        assertFalse(MWST.restrictedSearch(null, 34));
    }
}