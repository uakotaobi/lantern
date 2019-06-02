package main;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
public class my {

//   public static enum KeyType {
//       Escape
//   }

	public static void main(String[] args) throws IOException {

		// Setup terminal and screen layers
		Terminal terminal = new DefaultTerminalFactory().createTerminal();
		Screen screen = new TerminalScreen(terminal);
		screen.startScreen();
		final TextGraphics textGraphics = terminal.newTextGraphics();
		Random random = new Random();
		TerminalSize terminalSize = screen.getTerminalSize();
//        for(int column = 0; column < terminalSize.getColumns(); column++) {
//            for(int row = 0; row < terminalSize.getRows(); row++) {
//                screen.setCharacter(column, row, new TextCharacter(
//                        ' ',
//                        TextColor.ANSI.DEFAULT,
//                         This will pick a random background color
//                        TextColor.ANSI.values()[random.nextInt(TextColor.ANSI.values().length)]));}

//        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
//        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
//        textGraphics.putString(2, 1, "Hi",SGR.BOLD);
		screen.refresh();
		screen.startScreen();
		terminal.flush();
		textGraphics.putString(10, 0, "Hi welcome to " + " VRION");
		screen.refresh();
		// Create panel to hold components
	    String temp =" ";
	    int value=0;
		KeyStroke keyStroke = terminal.readInput();
		terminal.flush();
		KeyType keyType = keyStroke.getKeyType();
		Panel panel = new Panel();
		
		//The outermost while loop gives the conditon of closing the swing terminal after esc is pressed
		
		ArrayList<String> text=new ArrayList<String>();
		while (!keyType.equals(KeyType.Escape)) {
			// This Portion of the code will fill an array list with charachters until the enter button is pressed
			while (!(keyType.equals(KeyType.Enter))) {
				terminal.setCursorVisible(false);
				keyStroke = terminal.readInput();
				keyType = keyStroke.getKeyType();
				text.add(keyType.toString());
				value++;
				temp =keyType.toString();
				//if((!(keyStroke.getKeyType()).equals(KeyType.Backspace))) {
				textGraphics.putString(value,6, (keyStroke.getCharacter()).toString());
				screen.refresh();
				}
			value=0;
			
		}

	}
}
