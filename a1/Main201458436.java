import javax.xml.crypto.Data;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Main201458436{

    public static ArrayList<String> ReadData(String pathname) {
        ArrayList<String> strlist = new ArrayList<String>();
        try {

            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            int j = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                strlist.add(line);
            }
            return strlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strlist;
    }

    public static ArrayList<ArrayList<Integer> > DataWash(ArrayList<String> Datalist) {
        ArrayList<ArrayList<Integer> > AIS = new ArrayList<ArrayList<Integer> >();
        ArrayList<Integer> IS = new ArrayList<Integer>();
        for (int i = 0; i < Datalist.size(); i++) {
            String Tstr = Datalist.get(i);
            if (Tstr.equals("A") == false) {
                IS.add(Integer.parseInt(Tstr));
            }
            if (Tstr.equals("A")) {
            	ArrayList<Integer> elemAIS = new ArrayList<Integer>(IS);
                AIS.add(elemAIS);
                IS.clear();
            }
        }
        return AIS;
    }



//%%%%%%%%%%%%%%%%%%%%%%% Procedure LongestComb() that will contain your code:

    //////////////////////////////////////////////////////////
    static int max_ref; // stores the LIS

    /* To make use of recursive calls, this function must return
   two things:
   1) Length of LIS ending with element arr[n-1]. We use
      max_ending_here for this purpose
   2) Overall maximum as the LIS may end with an element
      before arr[n-1] max_ref is used this purpose.
   The value of LIS of full array of size n is stored in
   *max_ref which is our final result */
    static int _lis(int arr[], int n)
    {
        // base case
        if (n == 1)
            return 1;

        // 'max_ending_here' is length of LIS ending with arr[n-1]
        int res, max_ending_here = 1;

        /* Recursively get all LIS ending with arr[0], arr[1] ...
           arr[n-2]. If   arr[i-1] is smaller than arr[n-1], and
           max ending with arr[n-1] needs to be updated, then
           update it */
        for (int i = 1; i < n; i++) {
            res = _lis(arr, i);
            if (arr[i - 1] < arr[n - 1] && res + 1 > max_ending_here)
                max_ending_here = res + 1;
        }

        // Compare max_ending_here with the overall max. And
        // update the overall max if needed
        if (max_ref < max_ending_here)
            max_ref = max_ending_here;

        // Return length of LIS ending with arr[n-1]
        return max_ending_here;
    }

    // The wrapper function for _lis()
    static int lis(int arr[], int n)
    {
        // The max variable holds the result
        max_ref = 1;

        // The function _lis() stores its result in max
        _lis(arr, n);

        // returns max
        return max_ref;
    }
    ////////////////////////////////////////////

    public static int LongestComb(int[] A, int n){
        /* Input is array of input sequence (a_1 <= a_2 <= ... <= a_n) as A[0,1,...,n-1], that is,
        a_1 = A[0], a_2 = A[1], ..., a_n = A[n-1].
        n = number of integers in sequence A
        This procedure should return the value of the longest anchored comb (>= 1) or 0 if there is no anchored comb.
        It should NOT return the respective anchored comb!
        */


        /* Here should be the description of the main ideas of your solution:
        describe the recursive relation used in your dynamic programming
        solution and outline how you implement it sequentially in your code below.

        SOME NOTATION: s.t. is abbreviation of "such that"
                       a <= b (a is smaller than or equal to b)
                       a >= b (a is greater than or equal to b)
                       max [ a , b ] denotes the larger among a and b
                       Given an array T[1,...,n] 
                       then M = max_{k: some condition C(k) holds} [ T[k] ],
                       M denotes the largest value T[k] over all indices k such that condition C(k) holds.
     
        The outer loop FOR EACH i 1 runs in the worst case for at most n indices j. Finally, for each of these two
        indices i, j from the two outer FOR loops, we define set L which has j-2-i+1 = j-i-1  L of set L.
         This argument shows that the worst case running.
        time of this part of the implementation is O(n^3).
         After we compute the content of the DP array L, we find themaximum max L[i,j] looping over all pairs
         of indices i,j such that 1  j 1, and because the number ofthese indices is O(n^2), this takes time O(n^2).
        Summarising, this argument shows that the worst case running time of this whole implementation is O(n^3).
        NOTE: For the purpose of this assignment we assume that such O(n^3) time algorithm is the fastest
        algorithm implementation for this problem.
        .....
        .....
        */

        /* Here should be the statement and description of the running time
        analysis of your implementation: describe how the running time depends on
        n (length of the input sequence), and give short argument.

        .....
        ..... 
        */

        /* Here should be the code of your procedure to solve the problem:
        (These comments of the text should be removed after your code is written here.)

        .....
        .....
        return ...;
        */

        int[][] L = new int[n][n]; // DP matrix
        int max = 0;   // max int among l indices
        int max_all = 0;   // result
        boolean flag = false;   // flag to show if L is empty

        // Initialise DP matrix, part (1)
        for (int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                L[i][j] = 0;
            }
        }
            // Initialise DP matrix, part (2)
            for (int i=0; i<n-1; i++) {
                if (A[i] < A[i+1]) {
                    L[i][i] = 1;
                }
            }

        // Loop through all elements and keep i fixed
        for (int i=0; i<n-1; i++) {
            // Skip if first pair is not tooth
            if (A[i] >= A[i + 1]) {
                continue;
            }
            // Add first pair as tooth
            // ***** This is done now in initialisation, part (2):
            //      L[i][i] = 1;
            for (int j = i + 2; j < n - 1; j++) {
                // Loop through all possible last teeth
                // Keep end j fixed
                // Find max L[i][l] such that i<=l<=j-2
                max = 0;
                flag = false;
                if (A[j] < A[j + 1]) {
                    // If last pair (j,j+1) is not a tooth
                    // then move to the next
                    for (int l = i; l < j - 1; l++) {
                        if ((A[l] < A[l + 1]) && (A[l + 1] > A[j]) && (L[i][l] > max)) {
                            // Keep max value among L[i][l] which
                            // correspond to A[l],A[l+1] that fulfil the conditions
                            max = L[i][l];
                            // State that L set is not empty
                            flag = true;
                        }
                    }
                    if (flag) {
                        // If L set was not empty then
                        // increase max value by one (adding A[j],A[j+1])
                        // to the previous max comb
                        max = max + 1;
                    }
                    // Store max comb to appropriate position
                    L[i][j] = max;
                }
            }
        }
        // Find max anchored comb amongst all results
        for (int i=0; i<n; i++) {
            for (int j = i; j < n - 1; j++) {
                if ((A[i] == A[j]) && (L[i][j] > max_all) && (A[i] < A[i + 1]) && (A[j] < A[j + 1])) {
                    // Also check that the comb is anchored
                    max_all = L[i][j];
                }
            }
        }
        return max_all;


    } // end of procedure LongestComb()



    public static int Computation(ArrayList<Integer> Instance, int opt){
        // opt=1 here means option 1 as in -opt1, and opt=2 means option 2 as in -opt2
        int NGounp = 0;
        int size = 0;
        int Correct = 0;
        size = Instance.size();

        int [] A = new int[size-opt];
        // NGounp = Integer.parseInt((String)Instance.get(0));
        NGounp = Instance.get(0); // NOTE: NGounp = 0 always, as it is NOT used for any purpose
                                           // It is just the first "0" in the first line before instance starts.
        for (int i = opt; i< size;i++ ){
            A[i-opt] = Instance.get(i);
        }
        int Size =A.length;
        if (NGounp >Size ){
            return (-1);
        }
        else {
            //Size
            int R = LongestComb(A,Size);
            return(R);
        }
    }

    public static String Test;


    public static void main(String[] args) {
    	if (args.length == 0) {
    		String msg = "Rerun with flag: " +
    		"\n\t -opt1 to get input from dataOne.txt " + 
    		"\n\t -opt2 to check results in dataTwo.txt";
    		System.out.println(msg);
    		return;
    	}
        Test = args[0];
        int opt = 2;
        String pathname = "dataTwo.txt";
        if (Test.equals("-opt1")) {
            opt = 1;
            pathname = "dataOne.txt";
        }


        ArrayList<String> Datalist = new ArrayList<String>();
        Datalist = ReadData(pathname);
        ArrayList<ArrayList<Integer> > AIS = DataWash(Datalist);

        int Nins = AIS.size();
        int NGounp = 0;
        int size = 0;
        if (Test.equals("-opt1")) {
            for (int t = 0; t < Nins; t++) {
                int Correct = 0;
                int Result = 0;
                ArrayList<Integer> Instance = AIS.get(t);
                Result = Computation(Instance, opt);
                System.out.println(Result);
            }
        }
        else {
            int wrong_no = 0;
            int Correct = 0;
            int Result = 0;
            ArrayList<Integer> Wrong = new ArrayList<Integer>();
            for (int t = 0; t < Nins; t++) {
                ArrayList<Integer> Instance = AIS.get(t);
                Result = Computation(Instance, opt);
                System.out.println(Result);
                Correct = Instance.get(1);
                if (Correct != Result) {
                    Wrong.add(t+1);
                    wrong_no=wrong_no+1;
                }
            }
            if (Wrong.size() > 0) {System.out.println("Index of wrong instance(s):");}
            for (int j = 0; j < Wrong.size(); j++) {
                System.out.print(Wrong.get(j));
                System.out.print(",");

                /*ArrayList Instance = (ArrayList)Wrong.get(j);
                for (int k = 0; k < Instance.size(); k++){
                    System.out.println(Instance.get(k));
                }*/
            }
            System.out.println("");
            System.out.println("Percentage of correct answers:");
            System.out.println(((double)(Nins-wrong_no) / (double)Nins)*100);

        }

    }
}
