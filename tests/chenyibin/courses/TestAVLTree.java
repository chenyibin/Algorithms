package chenyibin.courses;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class TestAVLTree {
    // Tree under test
    AVLTree<Integer> tree;

    @Before
    public void setUp()
    {
        tree = new AVLTree<>();
    }
    

    @Test
    public void testAddFiveElementsWithRemoval()
    {
        tree.insert(5);
        tree.insert(101); // temp
        tree.insert(55); // temp
        tree.insert(10); // temp
        tree.remove(10); // removed
        tree.insert(10);
        tree.insert(7);
        tree.remove(101); // removed
        tree.insert(9);
        tree.insert(45);
        tree.remove(55); // removed

        List<Integer> expected = Lists.newArrayList(5, 7, 9, 10, 45);
        Assert.assertEquals(expected, tree.getInOrderTraversal());
    }
    
    @Test
    public void testTreeBalance()
    {
    
        tree.insert(5);
        tree.insert(101);
        tree.insert(55);
        tree.insert(10);
        tree.insert(7);
        tree.insert(9);
        tree.insert(45);
        
        List<BasicTreeNode<Integer>> nodes = tree.getInOrderNodeTraversal();

        Optional<Integer> unbalanced = nodes.stream()
            .filter((node) -> Math.abs(AVLTree.getBalance(node)) > 1)
            .map(BasicTreeNode<Integer>::getValue)
            .findAny();
            
        Assert.assertFalse(unbalanced.isPresent());
    }
}
