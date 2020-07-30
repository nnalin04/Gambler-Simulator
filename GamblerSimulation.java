import java.util.Random;

class GamblerSimulation {

    // Declaring class variable stake & bet
    final static int STAKE = 100;
    final static int BET = 1;
    
    // calculating when to stop in case of winning or loosing
    static int maxBetAmount = STAKE + (STAKE * 50 / 100);
    static int minBetAmount = STAKE - (STAKE * 50 / 100);

    public static void main(String[] args) {
        // calling a function to calculate and print the gambling result till the given number of days
        calculatingGambleResults();
    }

    //creating a function to calculate the accounts for given number of days
    public static void calculatingGambleResults() {
        // counter for number of days
        int numOfDaysGambled = 0;

        // counter for calculating the total amount won or lost
        int numOfDaysWon = 0;
        int numOfDaysLost = 0;

        // calculating the number of days
        while (numOfDaysGambled < 30) {
            numOfDaysGambled++;

            // calling a function to calculate the result of gambling perDay
            int currentBalance = returnsOfGamblingPerDay(maxBetAmount, minBetAmount);

            if (currentBalance == maxBetAmount) {
                numOfDaysWon++;
            } else {
                numOfDaysLost++;
            }
            //calling a function for printing after 20 days of gambling
            printingAfter20Days(numOfDaysGambled,numOfDaysWon,numOfDaysLost);
        }

        // printing the number of days won or lost and result after 30 day gamble
        System.out.println("Number of days won: " + numOfDaysWon);
        System.out.println("Number of days lost: " + numOfDaysLost);
        System.out.println("current Balance available: "+ (totalCalculation(numOfDaysWon, 50, 100) + totalCalculation(numOfDaysLost, 50, 0)));
    }

    //function to calculate the total amount after investing
    public static int totalCalculation(int noOfDays, int ProfitOrLoss, int currentBalance) {
        return noOfDays * (currentBalance + ProfitOrLoss);
    }

    //function to calculate the returns of gambling per day
    public static int returnsOfGamblingPerDay(int maxBetAmount, int minBetAmount) {
        // calculate the amount per gamble gamble
        int currentBalance = STAKE;
        while (maxBetAmount > currentBalance && currentBalance > minBetAmount) {
            // calling the function within the if statement
            if (gambling() == true) {
                currentBalance += BET;
            } else {
                currentBalance -= BET;
            }
        }
        return currentBalance;
    }
    
    //function to print the per day gambling result after 20 days
    public static void printingAfter20Days(int numOfDaysGambled, int numOfDaysWon, int numOfDaysLost){
        // displaying the won and lost amount after 20 days
        if (numOfDaysGambled >= 20) {
            System.out.println("Total amount won after " + numOfDaysGambled + " days: "+ totalCalculation(numOfDaysWon, 50, 0));
            System.out.println("Total amount lost after " + numOfDaysGambled + " days: "+totalCalculation(numOfDaysLost, 50, 0) + "\n");
        }
    }

    // creating a function to decide the gamble result
    public static boolean gambling() {
        Random r = new Random();
        boolean result = r.nextBoolean();
        return result;
    }
}