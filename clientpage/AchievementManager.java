package com.example.cardgame.clientpage;

class AchievementManager {

    static String highScoreAchievementChecker(int score) {

        if (score >= 300000) {
            return "Reach to " + 300000 + "/300000 points      COMPLETED";
        } else {
            return "Reach to " + score + "/300000 points      INCOMPLETE";
        }
    }

    static String coinAchievementChecker(int coin) {
        if (coin >= 120) {
            return "Remain " + 120 + "/120 coins at the end of the game      COMPLETED";
        } else {
            return "Remain " + coin + "/120 coins at the end of the game      INCOMPLETE";
        }
    }
}
