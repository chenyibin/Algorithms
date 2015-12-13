package chenyibin.leetcode;

public class BullsAndCows {

    public String getHint(String secret, String guess)
    {
        int[] secretNums = new int[10];
        int[] guessNums  = new int[10];
        
        int bullCounter = 0;
        
        for (int i = 0; i < secret.length(); ++i) {
            char secretChar = secret.charAt(i);
            char guessChar = guess.charAt(i);

            if (secretChar == guessChar)
            {
                ++bullCounter;
                continue;
            }
            
            int secretNum = Character.getNumericValue(secretChar);
            int guessNum = Character.getNumericValue(guessChar);

            ++secretNums[secretNum];
            ++guessNums[guessNum];
        }
        
        int cowCounter = 0;
        for (int i = 0; i < secretNums.length; ++i) {
            cowCounter += Math.min(secretNums[i], guessNums[i]);
        }

        return String.format("%dA%dB", bullCounter, cowCounter);
    }
}
