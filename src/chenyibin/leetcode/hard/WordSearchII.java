package chenyibin.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem #212 on leetcode.com:
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell.
 * The same letter cell may not be used more than once in a word (but is OK in different words).
 * @author Yibin Chen
 */
public class WordSearchII {
    
    private class TrieNode {
        TrieNode[] next;
        String end;
        
        public TrieNode() {
            this.next = new TrieNode[26];
            this.end = null;
        }
        
        public void insert(String s, int pos) {
            if (pos >= s.length()) {
                end = s;
                return;
            }
            
            char c = s.charAt(pos);
            int nextIndex = (int)(c - 'a');
            if (next[nextIndex] == null) {
                next[nextIndex] = new TrieNode();
            }
            TrieNode selectedNext = next[nextIndex];
            selectedNext.insert(s, pos + 1);
        }
        
        public TrieNode getNext(char c) {
            int nextIndex = (int)(c - 'a');
            return next[nextIndex];
        }
    }
    
    Set<String> result;
    char[][] board;
    boolean[][] visited;
    int rows;
    int cols;
           
    public List<String> findWords(char[][] board, String[] words)
    {
        this.board = board;
        this.result = new HashSet<>();
        
        this.rows = board.length;
        this.cols = board[0].length;
        this.visited = new boolean[rows][cols];
        
        TrieNode dict = new TrieNode();
        for (String word : words) {
            dict.insert(word, 0);
        }
        
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                search(dict, row, col);
            }
        }
        return new ArrayList<>(this.result);
    }

    private void search(TrieNode node, int row, int col)
    {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        
        char boardChar = board[row][col];
        TrieNode next = node.getNext(boardChar);
        if (next == null) {
            return;
        }
        if (next.end != null) {
            result.add(next.end);
        }
        visited[row][col] = true;
        search(next, row, col + 1);
        search(next, row, col - 1);
        search(next, row + 1, col);
        search(next, row - 1, col);
        visited[row][col] = false;
    }
}
