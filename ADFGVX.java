import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ADFGVX {
	final int ROW = 6; //Horizontal 
	final int COL = 6; //Vertical
	
	String toEncipher = ""; // The word/sentence that will get enciphered
	String cipherString = ""; // The word with the cipher text
	String postEncipher = ""; //The string, "uncut"
	String ppEncipher = ""; // The fully, enciphered word with spaces in between every 4 letters
	
	ArrayList<String> enciphered; // The word after it has been modified
	ArrayList<HashMap<String, String>> keyCollections; // 
	
	String keyWord = ""; // The key word that will determine the order of the cipher text
	
	String[] cipherText; //1-A, 2-D, 3-F, 4-G, 5-V, 6-X
	
	char[][] alphaGrid; //An empty polybius square
	String[][] cipherGrid; //The array containing the cipher text || Polybius square
	Map<Integer, String> keyWordTable; // Stores the enciphered text in the "table," it is a map, and not a hashmap, so that it may be natively sorted
	 
	String firstTr = ""; // Contains every fourth letter... starting from the 1st letter
	String secondTr = ""; //...starting from the 2nd letter
	String thirdTr = ""; //...starting from the 3rd letter
	String fourthTr = ""; //starting from the 4th letter
	
	public ADFGVX(String toE, String k)// Constructor
	{ 
		alphaGrid = new char[ROW][COL]; // Creates a 6 by 6 char[][] that will contain (A-9)
		cipherGrid = new String[ROW][COL]; // Creates a 6 by 6 char[][] containing the cipher text (AA-XX) 
		enciphered = new ArrayList<String>();
		cipherText = new String[6]; //Creates an array with A D F G V X 
		keyCollections = new ArrayList<HashMap<String, String>>();
		
		toEncipher = toE; //String to be encoded
		toEncipher = toEncipher.toUpperCase(); //Turns the string to be encoded into upper case
		toEncipher = toEncipher.replaceAll("\\s",""); // Gets rid of all the spaces
		keyWord = k; //String that will determine order of cipher text
	}
	
	public void fillAlpha()// Fills the Array || Generates a generic polybius square
	{ 
		int letter = 65; //Char begins at "A"
		for(int row = 0; row < alphaGrid.length; row++){ //Traverses the rows
			for(int col = 0; col < alphaGrid[row].length; col++){ //Traverses the columns
				if (letter >= 91){ //Checks if the letter progression has exceeded "Z"
					letter = 48; } // Sets the char to 0; proceeds to 0-9
				alphaGrid[row][col] = (char)letter; //Assigns A-9 to spaces in the Array
				letter++; }}//Proceeds to the following char
	}
	
	public String[] newCipherText() // Creates the array that contains A-X
	{
		cipherText[0] = "A";
		cipherText[1] = "D";
		cipherText[2] = "F";
		cipherText[3] = "G";
		cipherText[4] = "V";
		cipherText[5] = "X";
		return cipherText;
	}
	
	public String[][] newCipherGrid() //Creates the array that holds AA-XX
	{
		for (int row = 0; row < cipherGrid.length; row++) {
			for (int col = 0; col < cipherGrid[row].length; col++){
				cipherGrid[row][col] = (cipherText[row] + cipherText[col] + " "); 
				}
		}
		return cipherGrid;
	}
	
	public ArrayList<Character> randomAlpha()//Creates a shuffled ArrayList containing A-9
	{ 
		ArrayList<Character> randomAlpha = new ArrayList<Character>(); //Creates an ArrayList that will contain characters
		int letter = 65; // Char begins at "A"
		for (int row = 0; row < alphaGrid.length; row++){ //Traverses the rows
			for (int col = 0; col < alphaGrid[row].length; col++){ //Traverses the columns
				if (letter >= 91){
					letter = 48;
				}
				randomAlpha.add((char)letter); //Adds A-9 to the ArrayList
				letter++;}} 
		Collections.shuffle(randomAlpha); //Shuffles the list
		return randomAlpha; // Returns the ArrayList, therefore: randomAlpha() can be used as a randomized ArrayList containing A-9
	}
	
	public void fillRandomAlpha()//Fills alphaGrid with A-9 in random order.
	{ 
		int index = 0; //Set index to 0, as to begin traversing the ArrayList at index 0
		for(int row = 0; row < alphaGrid.length; row++){ //Traverses the rows
			for(int col = 0; col < alphaGrid[row].length; col++){ //Traverses the columns
				alphaGrid[row][col] = randomAlpha().get(index); //Assigns A-9 to spaces in the Array
				index++; }}//Proceeds to the following index in the randomized ArrayList
	}
	
	public void printArray() //for the alphaGrid
	{
		System.out.println();
		for (int row = 0; row < alphaGrid.length; row++){ // Traverses the row
			for (int col = 0;col < alphaGrid[row].length; col++){ // Traverses the column
				System.out.print(alphaGrid[row][col] + " "); } // Prints out the row
			System.out.println();  // Prints a new line, as to begin a new row}
		} System.out.println();}
	
	public void printArray(String[][] stringArr) //For specific string arrays
	{
		for (int row = 0; row < stringArr.length; row++){ // Traverses the row
			for (int col = 0;col < stringArr[row].length; col++){ // Traverses the column
				System.out.print(stringArr[row][col]); } // Prints out the row
			System.out.println();  // Prints a new line, as to begin a new row}
		}}
	
	public void replaceText() //Replaces the toEncipher with cipher text
	{
		for (int index = 0; index < toEncipher.length(); index++) { //Traverses toEncipher
			for (int row = 0; row < cipherGrid.length; row++){ // Traverse the rows of cipherGrid
				for(int col = 0; col < cipherGrid[row].length; col++){ //Traverse the columns of cipherGrid
					String stringValueOfChar = String.valueOf(alphaGrid[row][col]); //Converts the char to a string
					if (toEncipher.substring(index, index+1).equals(stringValueOfChar)){ // Finds the position of the letter in toEncipher in alphaGrid
						cipherString = cipherString + cipherGrid[row][col];} } } } // "Replaces" the position/(cipher text) in cipherGrid with the letter in toEncipher
		
		cipherString = cipherString.replaceAll("\\s","");
		
		System.out.println();
		System.out.println(cipherString);
	}
	
	public void createPostTranspose(char firstLetter, char secondLetter, char thirdLetter, char fourthLetter){ 
		//Will store every fourth letter (starting from their respective letters) to be placed in their column on the "transpose" table
		keyWordTable = new TreeMap<Integer, String>();
		
		try {
			for (int jindex = 0; jindex < cipherString.length(); jindex += 4){
				firstTr += cipherString.substring(jindex, jindex+1);
			}
			for (int kindex = 1; kindex < cipherString.length(); kindex += 4){
				secondTr += cipherString.substring(kindex, kindex+1);
			}
			for (int lindex = 2; lindex < cipherString.length(); lindex += 4){
				thirdTr += cipherString.substring(lindex, lindex+1);
			}
			for (int mindex = 3; mindex < cipherString.length(); mindex += 4){
				fourthTr += cipherString.substring(mindex, mindex+1);
			}
		} catch (IndexOutOfBoundsException e) {
		    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		    }
			
			// The sweet thing about maps is that they are natively ordered, which is why I converted the individual letters in the keyword to integers. 
			keyWordTable.put(Character.getNumericValue(firstLetter), firstTr);
			keyWordTable.put(Character.getNumericValue(secondLetter), secondTr);
			keyWordTable.put(Character.getNumericValue(thirdLetter), thirdTr);
			keyWordTable.put(Character.getNumericValue(fourthLetter), fourthTr);
	}
	
	public String cipheredWord(){
		//Adds the four chunks from the "transpose" table to a single string. The order is based on the keyword. 
		Collection keyColl = keyWordTable.values();
		Iterator keyItr = keyColl.iterator();
		while(keyItr.hasNext()){
			postEncipher += keyItr.next();
		}
		
		//Separates the entire string every 4 letters so that it may follow the rules of the ADFGVX cipher (Eg. AFGD FGXG FGXC) since the keyword is four letters long.
		for (int index = 0; index < postEncipher.length(); index++){
			char strToChar = postEncipher.charAt(index);
			if ((index+1)%4 == 0){
				ppEncipher += strToChar + " "; 
			} else {
				ppEncipher += strToChar;}
		}
		
		//Prints out the FINISHED ciphered message!!!
		System.out.println(ppEncipher);
		return ppEncipher;
	}
	
	public static void main(String[] args)
	{
		//Runs everything. 
		System.out.println("Message: ");
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		String inputEncipher = scanner.nextLine();
		System.out.println("Type a four letter keyword: ");
		System.out.print("> ");
		Scanner scanner2 = new Scanner(System.in);
		String inputKeyword = scanner2.nextLine();
		
		ADFGVX cipher = new ADFGVX(inputEncipher, inputKeyword); //Creates the char[][]
		
		inputKeyword = inputKeyword.toUpperCase();
		
		cipher.fillAlpha(); // Fills the array with A-9
		//cipher.fillRandomAlpha(); // Fills the array, randomly, with A-9
		
		cipher.newCipherText(); // Creates the 1D array containing the cipher text
		String[][] theCipherGrid = cipher.newCipherGrid(); // Fill the 2D array with the cipher text

		cipher.printArray(); //Prints a regular A-9 array	
		cipher.printArray(theCipherGrid); //Prints the cipher text array
		
		
		cipher.replaceText();
		
	}
	
}
