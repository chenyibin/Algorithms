package chenyibin.web;

import org.junit.Assert;
import org.junit.Test;

import chenyibin.leetcode.common.TreeNode;

public class TestKthLargestElementInBinaryTree {

    @Test
    public void find3rdLargest()
    {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        root.right = new TreeNode(9);
        root.right.right = new TreeNode(15);
        root.right.left = new TreeNode(7);
        
        KthLargestElementInBinaryTree solver = new KthLargestElementInBinaryTree(root);
        
        int thirdLargest = solver.findKth(3);
        Assert.assertEquals(7, thirdLargest);
    }

    @Test
    public void find4thLargest()
    {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        root.right = new TreeNode(9);
        root.right.right = new TreeNode(15);
        root.right.left = new TreeNode(7);
        
        KthLargestElementInBinaryTree solver = new KthLargestElementInBinaryTree(root);
        
        int thirdLargest = solver.findKth(4);
        Assert.assertEquals(6, thirdLargest);
    }


    @Test
    public void find5thLargest()
    {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        root.right = new TreeNode(9);
        root.right.right = new TreeNode(15);
        root.right.left = new TreeNode(7);
        
        KthLargestElementInBinaryTree solver = new KthLargestElementInBinaryTree(root);
        
        int thirdLargest = solver.findKth(5);
        Assert.assertEquals(5, thirdLargest);
    }

}
