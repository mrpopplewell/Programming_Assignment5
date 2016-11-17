import java.util.*;
import java.io.*;

public class ProgrammingAssignment5 {
    public static void main(String[] args) {
        //counters
        long wordsFound = 0, wordsNotFound = 0, compsFound = 0, compsNotFound = 0;
        double avgCompWordsFound = 0, avgCompWordsNotFound = 0;
        
        BinarySearchTree[] dictionary = new BinarySearchTree[26];
        
        File f = new File ("random_dictionary.txt");
        for (int i = 0; i <= dictionary.length - 1; i++) 
            dictionary[i] = new BinarySearchTree<>();
        
        try {
            Scanner input = new Scanner (f);
            
            while (input.hasNext()){
                String value = input.next();
                value = value.toLowerCase();
                value = parser(value);
                dictionary[value.charAt(0) - 97].insert(value);
            } // while
            input.close();
        } // try
        
        catch (IOException e){
            System.out.println("Error reading file.");
        } // catch
        
        List<String> list = readFileForAnalysis();
        String[] wordArray = list.toArray(new String[list.size()]);
        
        
        
        for (int i = 0; i < list.size(); i++) {
            if (wordArray[i].charAt(0) - 97 > 0 && wordArray[i].charAt(0) - 97 <= 26) {
                if (dictionary[wordArray[i].charAt(0) - 97].search(wordArray[i])) {
                    wordsFound++; 
                }
                else
                    wordsNotFound++;
            }
        } // for
        
        for (int i = 0; i < dictionary.length - 1; i++) {
            compsFound = dictionary[i].getCompsFound();
            compsNotFound = dictionary[i].getCompsNotFound();
        } // for
        avgCompWordsFound = (double) wordsFound / compsFound;
        avgCompWordsNotFound = (double) wordsNotFound / compsNotFound;
        
        System.out.println("Words Found: " + wordsFound);
        System.out.println("WordsNotFound: " + wordsNotFound);
        System.out.println("Average number of comparisons for words found: " + avgCompWordsFound);
        System.out.println("Average number of comparisons for words not found: " + avgCompWordsNotFound);
        
    } // main
    
    /**
     * This method takes a Strings (s) and parses it by replacing all characters that aren't letters a-z or A-Z. The first section of the replace
     * line, the part with {Alpha} essentially replaces anything that isn't alphanumeric - anything that doesn't consist of letter or numbers. 
     * The second piece, the section with "\\d" replaces any remaining numbers with an empty space. Also, I added an if else statement to make sure
     * a null value was not returned, only potentially empty values which can more easily be taken care of. 
     * s must be String type. 
     * Returns a String type (s).
     * @param s
     * @return s
     */
    public static String parser(String s) {
        s = s.replaceAll("[^\\p{Alpha}]", "").replaceAll("\\d", "");
        if (s != null && !s.isEmpty())
            return s;
        else
            s = "";
        return s;
    } // parser
    
    /**
     * This is a better version of my file reader from programming assignment 5. It uses my parser method to edit each line of strings and, with a 
     * small edit, I could make it to where a user could input any file for reading. It checks to see if each value is empty before adding it to 
     * a list object. 
     * Returns a list object of String type (list).
     * @return list
     */
    public static List readFileForAnalysis() {
        File g = new File("oliver.txt");
        
        List<String> list = new ArrayList<String>();
        String value = "";
        
        try{
            Scanner input = new Scanner(g);
            
            while (input.hasNext()) {
                value = input.next();
                value = parser(value).toLowerCase();
                if (value != null && !value.isEmpty())
                    list.add(value); 
            } // while
            input.close();
        } // try
        catch(IOException e) {
            System.out.println("Error reading file.");
        } // catch
        return list;
    } // List
} // ProgrammingAssignment5
//Aurora
