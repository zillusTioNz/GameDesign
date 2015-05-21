package marblehole;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class cycle {
    //initialize
    public int[][] map2D = new int[2][6];
    public int[] playerScore = new int[2];
    public mainFrame m = new mainFrame();

    // Fixed value
    public void cycle(int x, int y, int numPlayer) {

        map2D = new int[x][y];
        playerScore = new int[numPlayer];
    }

    public int[][] getData() {
        return map2D;
    }

    public int[] getScore() {
        return playerScore;
    }

    public boolean putDownStone(int currectPosition, int turn, int[][] data) {

        int turnOwner = turn;
        boolean turnStatus = false;

        int marbel = data[turn][currectPosition];
        int totalMarble = marbel;

        map2D = data;
        map2D[turn][currectPosition] = 0;

        while (marbel > 0) {

            if (currectPosition == 5) {
                currectPosition = -1;
                if (turnOwner == turn) {
                    playerScore[turnOwner]++;
                    marbel--;
                    if (marbel == 0) {
                        // continue
                        turnStatus = true;
                    }
                }

                if (turn == 0) {
                    turn = 1;
                } else if (turn == 1) {
                    turn = 0;
                }
            }

            if (marbel > 0) {
                map2D[turn][++currectPosition] += 1;
                marbel--;
            }

            try {
                if (marbel == 0 && map2D[turn][currectPosition] > 1) {
                    marbel = map2D[turn][currectPosition];
                    totalMarble += marbel;
                    map2D[turn][currectPosition] = 0;
                }

                if (marbel == 0 && map2D[turn][currectPosition] == 1 && turn == turnOwner) {
                    if (totalMarble > 7) {

                        if (map2D[(turnOwner + 1) % 2][currectPosition] > 0) {
                            int oppositePosition = 0;
                            switch (currectPosition) {
                                case 0:
                                    oppositePosition = 5;
                                    break;
                                case 1:
                                    oppositePosition = 4;
                                    break;
                                case 2:
                                    oppositePosition = 3;
                                    break;
                                case 3:
                                    oppositePosition = 2;
                                    break;
                                case 4:
                                    oppositePosition = 1;
                                    break;
                                case 5:
                                    oppositePosition = 0;
                                    break;
                            }
                            playerScore[turnOwner] += map2D[turn][currectPosition];
                            playerScore[turnOwner] += map2D[(turn + 1) % 2][oppositePosition];

                            map2D[turnOwner][currectPosition] = 0;
                            map2D[(turnOwner + 1) % 2][oppositePosition] = 0;
                            JOptionPane.showMessageDialog(m, "Steal");
                            // end turn
                            turnStatus = false;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
            
        
        return turnStatus;
    }
}
