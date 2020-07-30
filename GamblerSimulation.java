import java.util.Random;
import java.util.Scanner;

class GamblerSimulation {

    // Declaring class variable stake & bet
    final static int STAKE = 100;
    final static int BET = 1;

    // calculating when to stop in case of winning or loosing
    static int maxBetAmount = STAKE + (STAKE * 50 / 100);
    static int minBetAmount = STAKE - (STAKE * 50 / 100);

    public static void main(String[] args) {

        // calling a function to calculate and print the gambling result till the given
        // number of days.
        int gambleResult = calculatingGambleResults();
        repeatingGamble(gambleResult);

        if (gambleResult <= 15) {
            System.out.println("Sorry you went in loss or you didn't profit");
            System.out.println("Can't continue");
        }
    }

    private static void repeatingGamble(int gambleResult) {
        Scanner sc = new Scanner(System.in);
        if (gambleResult > 15) {
            System.out.println("Would you like to continue to gamble");
            System.out.println("true / false");
            boolean userWish = sc.nextBoolean();
            if (userWish) {
                repeatingGamble(calculatingGambleResults());
            } else {
                System.out.println("Thanks for gambling with us.");
            }
        }
        sc.close();
    }

    // function to calculate the total amount after investing.
    public static int totalCalculation(int noOfDays, int ProfitOrLoss, int currentBalance) {
        return noOfDays * (currentBalance + ProfitOrLoss);
    }

    // function to print the per day gambling result after 20 days.
    public static void printingAfter20Days(int numOfDaysGambled, int numOfDaysWon, int numOfDaysLost) {
        // displaying the won and lost amount after 20 days.
        if (numOfDaysGambled >= 20) {
            System.out.println(
                    "Total amount won after " + numOfDaysGambled + " days: " + totalCalculation(numOfDaysWon, 50, 0));
            System.out.println("Total amount lost after " + numOfDaysGambled + " days: "
                    + totalCalculation(numOfDaysLost, 50, 0) + "\n");
        }
    }

    // creating a function to decide the gamble result.
    public static boolean gambling() {
        Random r = new Random();
        boolean result = r.nextBoolean();
        return result;
    }

    // creating a function to calculate the accounts for given number of days.
    public static int calculatingGambleResults() {
        // counter for number of days
        int numOfDaysGambled = 0;

        // counter for calculating the total amount won or lost.
        int numOfDaysWon = 0;
        int numOfDaysLost = 0;

        double maxWinPercentage = 0;
        double maxLossPercentage = 0;

        int dayWithMaxWin = 0;
        int dayWithMaxLoss = 0;

        // calculating the number of days.
        while (numOfDaysGambled < 30) {
            numOfDaysGambled++;

            // calculate the amount per gamble gamble.
            int currentBalance = STAKE;

            double numOfWins = 0;
            double numOfLoss = 0;

            while (maxBetAmount > currentBalance && currentBalance > minBetAmount) {
                // calling the function within the if statement.
                if (gambling() == true) {
                    currentBalance += BET;
                    numOfWins++;
                } else {
                    currentBalance -= BET;
                    numOfLoss++;
                }
            }

            double totalNumOfGamble = numOfWins + numOfLoss;

            double winPercentage = 100 * numOfWins / totalNumOfGamble;
            double LossPercentage = 100 * numOfLoss / totalNumOfGamble;

            if (maxWinPercentage < winPercentage) {
                maxWinPercentage = winPercentage;
                dayWithMaxWin = numOfDaysGambled;
            }

            if (maxLossPercentage < LossPercentage) {
                maxLossPercentage = LossPercentage;
                dayWithMaxLoss = numOfDaysGambled;
            }

            if (currentBalance == maxBetAmount) {
                numOfDaysWon++;
            } else {
                numOfDaysLost++;
            }
            // calling a function for printing after 20 days of gambling.
            printingAfter20Days(numOfDaysGambled, numOfDaysWon, numOfDaysLost);
        }

        // printing the number of days won or lost and result after 30 day gamble.
        System.out.println("Number of days won: " + numOfDaysWon);
        System.out.println("Number of days lost: " + numOfDaysLost);
        System.out.println("current Balance available: "
                + (totalCalculation(numOfDaysWon, 50, 100) + totalCalculation(numOfDaysLost, 50, 0)));
        System.out.println("day with max win: " + dayWithMaxWin + "th day");
        System.out.println("day with max loss: " + dayWithMaxLoss + "th day" + "\n");
        return numOfDaysWon;

    }
}