package chenyibin.leetcode;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import chenyibin.leetcode.common.TreeNode;

public class TestBinaryTreeInorderTraversal {

    @Test
    public void testBalancedTree()
    {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(20);
        
        BinaryTreeInOrderTraversal traversal = new BinaryTreeInOrderTraversal();
        
        List<Integer> reference = traversal.inOrderTraversal(root);
        List<Integer> testing = traversal.inOrderWithOneStack(root);
        Assert.assertEquals(reference, testing);
    }

    @Test
    public void testLeftTree()
    {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        BinaryTreeInOrderTraversal traversal = new BinaryTreeInOrderTraversal();
        
        List<Integer> reference = traversal.inOrderTraversal(root);
        List<Integer> testing = traversal.inOrderWithOneStack(root);
        Assert.assertEquals(reference, testing);
    }
    
    @Test
    public void testRightTree()
    {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(20);
        root.right.right.right = new TreeNode(50);

        BinaryTreeInOrderTraversal traversal = new BinaryTreeInOrderTraversal();
        
        List<Integer> reference = traversal.inOrderTraversal(root);
        List<Integer> testing = traversal.inOrderWithOneStack(root);
        Assert.assertEquals(reference, testing); 
    }
}
