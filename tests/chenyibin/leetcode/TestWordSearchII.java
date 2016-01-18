package chenyibin.leetcode;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import chenyibin.leetcode.hard.WordSearchII;

public class TestWordSearchII {
    
    @Test
    public void testBasicSearch() {
        WordSearchII searcher = new WordSearchII();
        
        char[][] board ={{'o','a','a','n'},
                         {'e','t','a','e'},
                         {'i','h','k','r'},
                         {'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        
        List<String> found = searcher.findWords(board, words);
        Assert.assertTrue(found.size() == 2);
    }
}
