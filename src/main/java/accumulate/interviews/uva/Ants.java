package accumulate.interviews.uva;

import java.util.Scanner;

public class Ants {

    public static void main(String args[]) throws Exception {
        //initialize buffered reader
        Scanner sc = new Scanner(System.in);
        //initialize test cases
        int testCases = sc.nextInt();
        //declare current ant
        int currentAnt;
        while(testCases > 0) {
            //initialize length of rod and number of ants
            int length = sc.nextInt();
            int numberOfAnts = sc.nextInt();
            //initialize min and max to zero
            int min = 0;
            int max = 0;
            //iterate while there are still remaining ants to process
            while(numberOfAnts > 0) {
                //read in current ant
                currentAnt = sc.nextInt();
                //calculate whether ant is closer to left side of rod or right side of rod
                currentAnt = currentAnt < length - currentAnt ? currentAnt : length - currentAnt;
                //update minimum time to most restrictive ant minimum time
                if(currentAnt > min) {
                    min = currentAnt;
                }
                //update maximum time to most restrictive ant maximum time
                if(length - currentAnt > max) {
                    max = length - currentAnt;
                }
                //decrement number of ants remaining
                numberOfAnts--;
            }

            //print min and max of current test case
            System.out.println(min + " " + max);
            //decrement number of test cases remaining
            testCases--;
        }
    }
}