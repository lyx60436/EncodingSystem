// Import libraries
import java.util.Scanner;
import java.util.Arrays;

class Encoder 
{
	private String plainText;
	private char offsetChar;
	
	// Default constructor
	public Encoder(String plainText, char offsetChar) 
	{
		this.plainText = plainText.toUpperCase();
		this.offsetChar = Character.toUpperCase(offsetChar);
	}
	
	// Accessor methods
	public String getPlainText() 
	{
		return plainText;
	}
	
	public char getoffsetChar()
	{
		return offsetChar;
	}
	
	public char[] getReferenceTable() 
	{
		// Declare variables
		char[] reference_table = new char [44];
		int index = 0;
		
		// Get letters A to Z
		for (int i = 65; i <= 90; i++)
		{
			int acscii_val = i;
			reference_table[index] = (char) acscii_val;
			index++;
		}
		
		// Get digit 0 to 9
		for (int j = 48; j <= 57; j++) 
		{
			int acscii_val = j;
			reference_table[index] = (char) acscii_val;
			index++;
		}
		
		// Get special characters
		for (int k = 40; k <= 47; k++) 
		{
			int acscii_val = k;
			reference_table[index] = (char) acscii_val;
			index++;
		}
		
		return reference_table;
	}
	
	public char[] getSpecialTable() 
	{
		// Declare variables
		char[] special_character = new char[24];
		int index = 0;
		
		// Get special characters
		for (int i = 33; i <= 39; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		for (int i = 58; i <= 64; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		for (int i = 91; i <= 96; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		for (int i = 123; i <= 126; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		return special_character;
	}
	
	public char[] getShiftTable()
	{
		// Get the offset character and reference table
		char offset_char = getoffsetChar();
		char[] ref_table = getReferenceTable();
		
		//Declare variables
		int right_shift = 0;
		char [] shift_table = new char[44];
		
		// Iterate through the reference table to get new index to shift array to the right
		for (int i = 0; i < ref_table.length; i++)
		{
			if (offset_char == ref_table[i])
			{
				right_shift = i;
			}	
		}
		
		// Create shift table
		for (int i = 0; i < ref_table.length; i++)
		{
			int newPosition = (i + right_shift) % ref_table.length;
			shift_table[newPosition] = ref_table[i];
		}
		
		return shift_table;
	}
	
	
	// Display encoded message
	public String encode(String plainText) 
	{
		// Get the offset character, reference, special and shift tables
		char offset_char = getoffsetChar();
		char[] ref_table = getReferenceTable();
		char [] special_characters = getSpecialTable();
		char [] shift_table = getShiftTable();
		
		//Declare variables
		String encoded_message = String.valueOf(offset_char);
		
		// Check plain text with reference and shift table
		for (int i = 0; i < plainText.length(); i++)
		{
			for (int j = 0; j < ref_table.length; j++)
			{
				char plain_char = plainText.charAt(i);
				char ref_char = ref_table[j];
				
				// Check every character in plain text with reference table
				if (plain_char == ref_char)
				{
					// Get index of character in shift table
					int get_index = j;
					char get_char = shift_table[get_index];
					encoded_message += get_char;
				}
				// Check for whitespace in plain text
				else if (Character.isWhitespace(plain_char) == true)
				{
					char get_whitespace = plainText.charAt(i);
					encoded_message += get_whitespace;
					break;
				}
			}
		}
				
		// Check if plain text has special characters
		for (int i = 0; i < plainText.length(); i++)
		{
			for (int k = 0; k < special_characters.length; k++)
			{
				if (plainText.charAt(i) == special_characters[k]) 
				{
					// Get special character and special index
					char get_special_character = special_characters[k];
					int get_special_index = i;
					
					// Insert special character into the encoded message
					StringBuilder str = new StringBuilder(encoded_message);
					str.insert(get_special_index + 1, get_special_character).toString();
					encoded_message = str.toString();
				}
			}
		}
		
		return encoded_message;
	}
}

class Decoder 
{
	private String encodedText;
	private char offsetChar;
	
	// Default constructor
	public Decoder(String encodedText, char offsetChar) 
	{
		this.encodedText = encodedText.toUpperCase();
		this.offsetChar = Character.toUpperCase(offsetChar);
	}
	
	// Accessor methods
	public String getEncodedText() 
	{
		return encodedText;
	}
	
	public char getoffsetChar()
	{
		return offsetChar;
	}
	
	public char[] getReferenceTable() 
	{
		// Declare variables
		char[] reference_table = new char [44];
		int index = 0;
		
		// Get letters A to Z
		for (int i = 65; i <= 90; i++)
		{
			int acscii_val = i;
			reference_table[index] = (char) acscii_val;
			index++;
		}
		
		// Get digit 0 to 9
		for (int j = 48; j <= 57; j++) 
		{
			int acscii_val = j;
			reference_table[index] = (char) acscii_val;
			index++;
		}
		
		// Get special characters
		for (int k = 40; k <= 47; k++) 
		{
			int acscii_val = k;
			reference_table[index] = (char) acscii_val;
			index++;
		}
		
		return reference_table;
	}
	
	public char[] getSpecialTable() 
	{
		// Declare variables
		char[] special_character = new char[24];
		int index = 0;
		
		// Get special characters
		for (int i = 33; i <= 39; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		for (int i = 58; i <= 64; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		for (int i = 91; i <= 96; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		for (int i = 123; i <= 126; i++)
		{
			int acscii_val = i;
			special_character[index] = (char) acscii_val;
			index++;
		}
		
		return special_character;
	}
	
	// Display decoded message
	public String decode(String encodedText) 
	{
		// Get the offset character, reference, special and shift tables
		char offset_char = getoffsetChar();
		char[] ref_table = getReferenceTable();
		char [] special_characters = getSpecialTable();
		Encoder e2 = new Encoder(encodedText, offset_char);
		char [] shift_table = e2.getShiftTable();
		String decoded_message = "";
		
		// Check encoded text with reference and shift table
		for (int i = 1; i < encodedText.length(); i++)
		{
			for (int k = 0; k < shift_table.length; k++)
			{
				char encoded_char = encodedText.charAt(i);
				char shift_char = shift_table[k];
				
				// Check character of encoded text with shift table
				if (encoded_char == shift_char)
				{
					// Get index of character in reference table
					int get_idx = k;
					char get_character = ref_table[get_idx];
					decoded_message += get_character;
				}
				// Check for whitespace in encoded text
				else if (Character.isWhitespace(encoded_char) == true)
				{
					char whitespace = encodedText.charAt(i);
					decoded_message += whitespace;
					break;
				}
			}
		}
		
		// Check if encoded text has special characters
		for (int i = 0; i < encodedText.length(); i++)
		{
			for (int k = 0; k < special_characters.length; k++)
			{
				if (encodedText.charAt(i) == special_characters[k]) 
				{
					// Get special character and special index
					char get_special_character = special_characters[k];
					int get_special_index = i;
					
					// Insert special character into the decoded message
					StringBuilder str = new StringBuilder(decoded_message);
					str.insert(get_special_index - 1, get_special_character).toString();
					decoded_message = str.toString();
				}
			}
		}
		
		return decoded_message;
	}
}


public class Encoding_System 
{
	// Declare private variables
	private static Scanner input = new Scanner (System.in);
	
	// Display main menu
	private static void displayMenu() 
	{
		// Declare variables
		int option = 0;
		String text = "";
		char character;
		
		do 
		{
			System.out.println("Encoding/Decoding System");
			System.out.println("================================");
			System.out.println("1. Encode a plain text");
			System.out.println("2. Decode an encoded text");
			System.out.println("3. Quit");
			System.out.print("\nEnter your option: ");
			option = input.nextInt();
			
			switch(option) 
			{ 
				case 1:	input.nextLine();
                        System.out.println("\n\nEncryption Mode");
                        System.out.println("================================");
                        System.out.print("Enter plain text: ");
                        text = input.nextLine();
                        System.out.print("Enter offset character: ");
                        character = input.next().charAt(0);
                        Encoder e1 = new Encoder(text, character);
                        System.out.println("Message successfully encoded...");
                        System.out.printf("Your encoded message is %s%n%n%n", e1.encode(e1.getPlainText()));
                        break;
						
				case 2: input.nextLine();
                        System.out.println("\n\nDecryption Mode");
                        System.out.println("================================");
                        System.out.print("Enter decoded text: ");
                        text = input.nextLine();
                        System.out.print("Enter offset character: ");
                        character = input.next().charAt(0);
                        Decoder d1 = new Decoder(text, character);
                        System.out.println("Message successfully decoded...");
                        System.out.printf("Your decoded message is %s%n%n%n", d1.decode(d1.getEncodedText()));
			}
		}
		while(option != 3);
	}

	public static void main(String[] args) 
	{
		// Start the encoding/decoding system
		displayMenu();
	}

}
