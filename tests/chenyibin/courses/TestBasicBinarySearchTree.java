package chenyibin.courses;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class TestBasicBinarySearchTree
{

    // Tree under test
    BasicBinarySearchTree<Integer> tree;

    @Before
    public void setUp()
    {
        tree = new BasicBinarySearchTree<>();
    }
    
    @Test
    public void testAddRemoveRoot()
    {
        tree.insert(10);
        tree.remove(10);

        Assert.assertTrue(tree.getInOrderTraversal().isEmpty());
    }
    
    @Test
    public void testAddOne()
    {
        tree.insert(10);
        
        List<Integer> expected = Lists.newArrayList(10);
        Assert.assertEquals(expected, tree.getInOrderTraversal());
    }
    
    @Test
    public void testAddTwoBiggerFirst()
    {
        tree.insert(10);
        tree.insert(5);

        List<Integer> expected = Lists.newArrayList(5, 10);
        Assert.assertEquals(expected, tree.getInOrderTraversal());
    }

    @Test
    public void testAddTwoSmallerFirst()
    {
        tree.insert(5);
        tree.insert(10);

        List<Integer> expected = Lists.newArrayList(5, 10);
        Assert.assertEquals(expected, tree.getInOrderTraversal());
    }

    @Test
    public void testAddFiveUnordered()
    {
        tree.insert(5);
        tree.insert(10);
        tree.insert(7);
        tree.insert(9);
        tree.insert(45);

        List<Integer> expected = Lists.newArrayList(5, 7, 9, 10, 45);
        Assert.assertEquals(expected, tree.getInOrderTraversal());
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

}
