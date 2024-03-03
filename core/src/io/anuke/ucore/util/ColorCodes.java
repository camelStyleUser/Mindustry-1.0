package io.anuke.ucore.util;

/**Note that these color codes will only work on linux or mac terminals.*/
public class ColorCodes{
	public static String FLUSH = "\033[H\033[2J";
	public static String RESET = "\u001B[0m";
	public static String BOLD = "\u001B[1m";
	public static String UNDERLINED = "\u001B[4m";
	public static String BLACK = "\u001B[30m";
	public static String RED = "\u001B[31m";
	public static String GREEN = "\u001B[32m";
	public static String YELLOW = "\u001B[33m";
	public static String BLUE = "\u001B[34m";
	public static String PURPLE = "\u001B[35m";
	public static String CYAN = "\u001B[36m";
	public static String LIGHT_RED = "\u001B[91m";
	public static String LIGHT_GREEN = "\u001B[92m";
	public static String LIGHT_YELLOW = "\u001B[93m";
	public static String LIGHT_BLUE = "\u001B[94m";
	public static String LIGHT_MAGENTA = "\u001B[95m";
	public static String LIGHT_CYAN = "\u001B[96m";
	public static String WHITE = "\u001B[37m";
	
	public static String BACK_DEFAULT = "\u001B[49m";
	public static String BACK_RED = "\u001B[41m";
	public static String BACK_GREEN = "\u001B[42m";
	public static String BACK_YELLOW = "\u001B[43m";
	public static String BACK_BLUE = "\u001B[44m";
	
	static{
		//disable color codes on windows
		
		if(System.getProperty("os.name").startsWith("Windows")){
			FLUSH = RESET = BOLD = UNDERLINED = BLACK = RED = GREEN = YELLOW = BLUE = PURPLE = CYAN 
					= LIGHT_RED = LIGHT_GREEN = LIGHT_YELLOW = LIGHT_BLUE = LIGHT_MAGENTA = LIGHT_CYAN 
					= WHITE = BACK_DEFAULT = BACK_RED = BACK_YELLOW = BACK_BLUE = "";
		}
	}
}
