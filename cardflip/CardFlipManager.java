package com.example.cardgame.cardflip;

import com.example.cardgame.R;

import java.lang.Math;


class CardFlipManager {
    /**
     * The position of the cards
     */
    private int[] pos = new int[16];
    /**
     * The images of the card in the card board
     */
    private int[] image = new int[8];
    /**
     * Number of flips the player get
     */
    private int life = 15;
    private int countZero;
    private int countZero2;
    /**
     * Number of cards left to be flip
     */
    private int cardLeft = 16;
    /**
     * The score of the player
     */
    private static int score = 0;
    /**
     * Number of coins the player earned in the first game
     */
    private int coin;


    /**
     * Create a new CardFlipManager.
     */
    CardFlipManager() {
        for (int i = 0; i < 16; i++) {
            int num = (int) (Math.random() * ((7) + 1));
            if (num == 0 & countZero < 2) {
                pos[i] = num;
                countZero += 1;
            } else if (!contains(pos, num)) {
                pos[i] = num;
            } else {
                i--;
            }
        }
        int[] drawble = new int[]{R.drawable.goldenswords, R.drawable.fireball,
                R.drawable.lightningstrike, R.drawable.magicalheal, R.drawable.tsunami,
                R.drawable.chococornet, R.drawable.eyesofcthulu, R.drawable.fullmetalguy,
                R.drawable.getintherobot, R.drawable.mechcthun, R.drawable.nekomimi,
                R.drawable.reallybigfire, R.drawable.potofarcaneintellect, R.drawable.endlesseye};
        for (int i = 0; i < 8; i++) {
            int num2 = (int) (Math.random() * ((13) + 1));
            if (num2 == 0 & countZero2 < 1) {
                image[i] = drawble[num2];
                countZero2 += 1;
            } else if (!containonce(image, drawble[num2])) {
                image[i] = drawble[num2];
            } else {
                i--;
            }
        }
    }

    //Check if the item is in the array arr.
    private boolean containonce(int[] arr2, int item) {
        for (int n : arr2) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    //Check if the item is in the array arr for more than two times.
    private boolean contains(int[] arr, int item) {
        int count = 0;
        for (int n : arr) {
            if (item == n) {
                count += 1;
            }
        }
        return count >= 2;
    }


    void addCoin(int num) {
        coin += num;
    }

    void buyFlip() {
        if (coin != 0) {
            coin -= 1;
            life += 1;
        }
    }

    int getCoin() {
        return coin;
    }

    void addScore() {
        score += 1000;
    }

    int getScore() {
        return score;
    }

    void damage() {
        this.life -= 1;
    }

    int[] getPos() {
        return pos;
    }

    int getLife() {
        return life;
    }

    int[] getImage() {
        return image;
    }

    int getCardLeft() {
        return cardLeft;
    }

    void useCard() {
        this.cardLeft -= 2;
    }

}