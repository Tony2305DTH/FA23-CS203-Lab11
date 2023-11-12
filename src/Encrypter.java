import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String decrypted = "";
    private String encrypted = "";

    String lowCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
    String upCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	String inputFile = readFile(inputFilePath);
    	
    	int shiftedPosition; 
    	char c;

	    for (int i = 0; i < inputFile.length(); i++) {

	    	if (lowCaseAlphabet.contains(inputFile.substring(i,i + 1)))  {  	
		        int position = lowCaseAlphabet.indexOf(inputFile.charAt(i));
		        shiftedPosition = (position +shift)%26;
		    	c = lowCaseAlphabet.charAt(shiftedPosition);
	        	encrypted += c;
			}
			else if (upCaseAlphabet.contains(inputFile.substring(i,i + 1)))  {  	
		        int position = upCaseAlphabet.indexOf(inputFile.charAt(i));
		        shiftedPosition = (position +shift)%26;
		    	c = upCaseAlphabet.charAt(shiftedPosition);
	        	encrypted += c;
	    		}
			else  if(inputFile.substring(i,i + 1) == "\n"){
		    	
				encrypted += "\n";	
			}
			
			else
				encrypted += inputFile.substring(i,i + 1);

	    }
    	
    	writeFile(encrypted, encryptedFilePath);

    	    	
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	String encryptedText = readFile(messageFilePath);
    	int shiftedPosition;
    	char c;
    	
	    for (int i = 0; i < encryptedText.length(); i++) {

	    	if (lowCaseAlphabet.contains(encryptedText.substring(i,i + 1)))  {  	
	    		int position = lowCaseAlphabet.indexOf(encryptedText.charAt(i));
		        shiftedPosition = (position - shift)%26;
		        if (shiftedPosition < 0) {
		        	shiftedPosition = shiftedPosition + 26;
		        }
		    	c = lowCaseAlphabet.charAt(shiftedPosition);
		    	decrypted += c;
    		}
    		else if (upCaseAlphabet.contains(encryptedText.substring(i,i + 1)))  {  	
		        int position = upCaseAlphabet.indexOf(encryptedText.charAt(i));
		        shiftedPosition = (position - shift)%26;
		        if (shiftedPosition < 0) {
		        	shiftedPosition = shiftedPosition + 26;
		        }
		    	c = upCaseAlphabet.charAt(shiftedPosition);
		    	decrypted += c;
	    		}
    		else  if(encryptedText.substring(i,i + 1) == "\n"){
		    	
    			decrypted += "\n";	
    		}
    		
    		else
    			decrypted += encryptedText.substring(i,i + 1);

	    }
   
	   // encryptedText.rem
    	writeFile(decrypted, decryptedFilePath);
   
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
        	while(fileScanner.hasNextLine()) {
        		message += fileScanner.nextLine() + "\n";
        	}  	
        }
        
        return message;
        }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try (PrintWriter write = new PrintWriter(filePath)){
    		write.print(data);
    	}catch(Exception e) {
    		System.out.println("error " + e.toString());
    	}
  
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
