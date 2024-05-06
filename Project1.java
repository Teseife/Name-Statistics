import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;


// TODO: Auto-generated Javadoc
/**
 * The Class Project1.
 */
public class Project1 {
	
    /** The scnr. */
    static Scanner scnr = new Scanner(System.in); 
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String [] args){
        // Scanner object Initiation

        int Option;

        boolean exit = false;

        System.out.println("Enter your String Input here:");

        String input = scnr.nextLine();

        while (!exit){
            System.out.println(
                    "1: Display List Ordered\n" +
                     "2: Display Full Names\n" +
                     "3: Display Single Names\n" +
                      "4: Display Name Statistics\n" +
                      "5: Display Names with Even Length\n" +
                      "6: Display Names with Odd Length\n" +
                      "7: Display Names not Capitalized\n" +
                       "8: Display Most Frequent Name\n" +
                       "9: Enter new list of Names\n" +
                       "0: Quit Program");

            System.out.println("Enter your Opiton:");
            Option = scnr.nextInt();
            scnr.nextLine(); // consume the \n that was left by nextInt.
            switch (Option){
                case 1:
                    displayListOrdered(input);
                    break;
                case 2:
                    displayFullNames(input);
                    break;
                case 3:
                    displaySingleNames(input);
                    break;
                case 4:
                    displayNameStatistics(input);
                    break;
                case 5:
                    displayEvenNames(input);
                    break;
                case 6:
                    displayOddNames(input);
                    break;
                case 7:
                    displayUncapitalizedNames(input);
                    break;
                case 8:
                    String freqName = displayMostFrequentName(input);
                    System.out.println(freqName);
                    break;
                case 9:
                    enterNewList(input);
                    break;
                case 0:
                	System.out.println("Program Exiting");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }// end Swicth
        }// end While.
    }// end of Main
    
    /**
     * Display list ordered.
     *
     * @param input the input
     */
    public static void displayListOrdered(String input){ 
        String words[] = InputParser(input);
        Arrays.sort(words,String.CASE_INSENSITIVE_ORDER);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        for (String word:words){
            list.add(word);
        }
        System.out.println(list);
    }// end displayListOrdered
    
    /**
     * Display full names.
     *
     * @param input the input
     */
    public static void displayFullNames(String input){ 
        String Names[] = InputParser(input);
        Arrays.sort(Names,String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i < Names.length; i++){
            if(Names[i].contains(" ")){
                System.out.println(Names[i]);
            }// end of if
        }// end for loop
    }// end displayFullNames
    
    /**
     * Display single names.
     *
     * @param input the input
     */
    public static void displaySingleNames(String input){ 
        String Names[] = InputParser(input); 
        Arrays.sort(Names,String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i < Names.length; i++){
            if(!Names[i].contains(" ")){
                System.out.println(Names[i]);
            }// end of if
        }// end for loop
    }// end displaySingleNames
    
    /**
     * Display name statistics.
     *
     * @param input the input
     */
    public static void displayNameStatistics(String input){ 
        String Names[] = InputParser(input);
        int nameLength = Names.length;
        int charCount = charParser(input); 
        float avgLength = (float)charCount/nameLength; 
        String shortestName = shortestName(input);
        String longestName = longestName(input);
        double standarddeviation = calculateStandardDeviation(input);

        System.out.println(String.format("Name Count: %d\n" + "Letter Count Total: %d\n" + "Avg Name Length: %.2f\n" +
                "Shortest Name: %s\n" +
                "Longest Name: %s\n" +
                "Population Standard Deviation:%.2f",nameLength,charCount,avgLength,shortestName,longestName,standarddeviation));
    }// end displayFullNames

    /**
     * Shortest name.
     *
     * @param input the input
     * @return the string
     */
    public static String shortestName(String input){ 
    	
        String [] Names = InputParser(input);
        Arrays.sort(Names,Comparator.comparing(String::length));
        return Names[0];
    }// end of shortestName

    /**
     * Longest name.
     *
     * @param input the input
     * @return the string
     */
    public static String longestName(String input){ 
        String [] Names = InputParser(input);
        Arrays.sort(Names,Comparator.comparing(String::length).reversed());
        return Names[0];
    }// end of longestName

    /**
     * Calculate standard deviation.
     *
     * @param input the input
     * @return the double
     */
    public static double calculateStandardDeviation(String input) { 
        String [] names = InputParser(input);
        int totalLength = 0;
        int count = 0;

        for (String name : names) { 
            totalLength += name.replace(" ", "").length();
            count++;
        }// end of for
        double mean = (double) totalLength / count; 
        double sumOfSquaredDifferences = 0;
        for (String name : names) {
            int lengthWithoutSpaces = name.replace(" ", "").length();
            sumOfSquaredDifferences += Math.pow(lengthWithoutSpaces - mean, 2);
        }// end of for
        double variance = sumOfSquaredDifferences / count; 

        return Math.sqrt(variance);
        
    }//calculateStandardDeviation

    /**
     * Display even names.
     *
     * @param input the input
     */
    public static void displayEvenNames(String input){ 
        String[] Names = InputParser(input); 
        Arrays.sort(Names, String.CASE_INSENSITIVE_ORDER); 
        for(int i = 0; i < Names.length; i++){
            if(Names[i].contains(" ")){
                int length = (Names[i].length() - 1);
                if (length % 2 == 0){
                    System.out.println(Names[i]);
                }// end of inner if
            }// end of outer if
            else if (Names[i].length() % 2 ==0){
                System.out.println(Names[i]);
            }// end of else if
        }// end for
    }// end of displayEvenNames

    /**
     * Display odd names.
     *
     * @param input the input
     */
    public static void displayOddNames(String input){ 
        String[] Names = InputParser(input); 
        Arrays.sort(Names, String.CASE_INSENSITIVE_ORDER); 
        for(int i = 0; i < Names.length; i++){
            if(Names[i].contains(" ")){
                int length = (Names[i].length() - 1);
                if (length % 2 != 0){
                    System.out.println(Names[i]);
                }// end of inner if
            }// end of outer if
            else if (Names[i].length() % 2 !=0){
                System.out.println(Names[i]);
            }// end of else if
        }// end for
    }// end of displayOddNames
    
    /**
     * Display uncapitalized names.
     *
     * @param input the input
     */
    public static void displayUncapitalizedNames(String input){ 
        String [] Names = InputParser(input); 
        Arrays.sort(Names,String.CASE_INSENSITIVE_ORDER);
        for(String name: Names){
            if(Character.isLowerCase(name.charAt(0))){
                System.out.println(name);
            }// end of if
        }// end of for each
    }// end of displayUncapitalizedNames
    
    /**
     * Display most frequent name.
     *
     * @param input the input
     * @return the string
     */
    public static String displayMostFrequentName(String input) { 
        String[] Name = InputParser(input.toLowerCase()); 
        Arrays.sort(Name);
        
        String mostFrequentName = "";
        int maxCount = 0;
        int currentCount = 1;

        for(int i = 1; i < Name.length; i++){
            if(Name[i].equals(Name[i - 1])){
                currentCount++;
            }// end of if
            else {
                if(currentCount > maxCount){
                    maxCount = currentCount;
                    mostFrequentName = Name[i-1];
                }// end if
                currentCount = 1;
            }// end of else
        }// end of for loop

        if(currentCount > maxCount){
            mostFrequentName = Name[Name.length - 1];
        }// end of if

        if(maxCount == 1){
            return "No most frequent name";
        }// end of if

        return "Most Frequent Name:" + mostFrequentName;
    }// end of findMostFrequentName

    /**
     * Enter new list.
     *
     * @param oldInput the old input
     */
    public static void enterNewList(String oldInput){
        System.out.println("Enter list to replace old one:");
        String newinput = scnr.nextLine();
        String[] InputArr = InputParser(oldInput);
        String[] NewInput = InputParser(newinput);
        ArrayList<String> names = new ArrayList<>(Arrays.asList(InputArr));
        names.clear();

        for(String name: NewInput){
            names.add(name);
        }//
        System.out.println("Old List:" + oldInput);
        System.out.println("New List:" + names);
    }// end of enterNewList
    
    /**
     * Char parser.
     *
     * @param input the input
     * @return the int
     */
    public static int charParser(String input){ 
        int charCount = 0;
        if(input.length() == 0){ 
            return charCount;
        }// end of if
        for( int i = 0; i < input.length(); i++){
            if(Character.isAlphabetic(input.charAt(i))){
                charCount++;
            }
        } // end for loop
        return charCount;
    }// end of charParser
    
    /**
     * Input parser.
     *
     * @param input the input
     * @return the string[]
     */
    public static String[] InputParser(String input) { 
        String words[] = input.split(",\\s*");
        return words;
    } // end of Inputparser()
}// end of class