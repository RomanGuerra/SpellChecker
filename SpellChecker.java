import java.util.*;
import java.io.*;

/////////////////////////////////////////////////////////
// SpellChecker Class //
public class SpellChecker{

    // HashTable Data
    Map<Integer, Word> dictionary;

    // Constructor
    SpellChecker(){
        dictionary = new HashMap<Integer, Word>(100003);
        ProcessFile();
        runSpellChecker();
    }


    // Run SpellChecker //
    //////////////////////
    public void runSpellChecker(){
            //// READ INPUT ////
    System.out.println("This program checks the spelling of one word.");

    // Data
    String input = null;
    char choice;
    
    // Scanner
    Scanner keyboard = new Scanner(System.in);

    do {
        // Ask User for Input
        System.out.print("Enter Word: ");
        input = keyboard.nextLine();
        System.out.println("\nUser Input: " + input);

        spellCheck(input);

        // Ask User to Run Again
        System.out.println("\nRun this again? (Y or N): " + input);
        input = keyboard.nextLine();
        choice = input.charAt(0);


    } while (Character.toUpperCase(choice) == 'Y');

    keyboard.close();
    }

    
    // SpellCheck Method
    public List<Word> spellCheck(String str){
        int key = getHashKey(str);
        Word foundWord = dictionary.get(key);
        Word inputWord = new Word(str);
        if(hasKey(str) && inputWord.equals(foundWord)){
            System.out.print("Correct Spelling");
            List<Word> oneWord = new ArrayList<Word>();
            oneWord.add(dictionary.get(key));
            return oneWord;
        }
        else{
            System.out.print("Incorrect Spelling");
            List<Word> possibleWords = new ArrayList<Word>();
            possibleWords.add(dictionary.get(key));
            return possibleWords;
        }
    }
    

    // Display Keys
    public void displayKeys(){
        System.out.println(dictionary.keySet());
    }

    // Display Values
    public void displayValues(){
        System.out.println(dictionary.values());
    }

    // Returns True if Key is in HashTable
    public boolean hasKey(String str){
        String value = str.toLowerCase();
        int hashKey = getHashKey(value);
        return dictionary.containsKey(hashKey);
    }
    // Returns HashKey
    public int getHashKey(String str){
        String value = str.toLowerCase();
        int hashValue = value.hashCode();
        if(hashValue < 0){
            hashValue *= -1;
        }
        final int PRIME = 100003;
        int hashKey = hashValue % PRIME;
        return hashKey;
    }

    // Open & Read File
    public void ProcessFile(){
        // Data
        int wordCount = 0;

        // Try Opening File
        try{
            // Open File
            File file = new File("dictionary.txt");
            Scanner inputFile = new Scanner(file);

            // Read File
            while(inputFile.hasNextLine()){

                // Read File to String
                String data = inputFile.next();
                data = data.toLowerCase();
    
                // Create New Instance of Word Object
                Word newWord = new Word(data);
    
                // Put In HashTable
                dictionary.put(newWord.hashKey(), newWord);

                // Keep Count
                wordCount++;
            }
            //System.out.println("Words Read: " + wordCount);
            //System.out.println("Dictionary Size: " + dictionary.size());
            inputFile.close();
        }
        catch(FileNotFoundException | InputMismatchException ex){
            System.out.println("Error Opening File.");
        }
    }
}
/////////////////////////////////////////////////////////
// Word Class //
class Word{

    // Data
    String value;
    
    // Constructor
    Word(String value){
        this.value = value;
    }

    // HashKey Method
    public int hashKey(){
        
        int hashValue = value.hashCode();
        if(hashValue < 0){
            hashValue *= -1;
        }
        final int PRIME = 100003;
        int hashKey = hashValue % PRIME;
        return hashKey;
    }

    // toString
    public String toString(){
        return value;
    }

    // equals Method
    public boolean equals(Word compareWord){
        if(value.equals(compareWord.value)){
            return true;
        }
        else{
            return false;
        }
    }
}