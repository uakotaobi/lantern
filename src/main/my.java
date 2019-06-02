package main;

import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

// Lanterna API documentation: https://mabe02.github.io/lanterna/apidocs/3.0/


public class my {

	//   public static enum KeyType {
	//       Escape
	//   }

	/**
	 * Waits for the user to press any key.
	 * @throws IOException
	 */
	public static void waitForKeyPress(Terminal terminal) throws IOException {
		terminal.readInput();
	}

	/**
	 * Asks the user to type some input.
	 */
	public static String getline(Terminal terminal, Screen screen, String prompt) throws IOException {
		String result = "";
		boolean done = false;
		TextGraphics textGraphics = terminal.newTextGraphics();
		TerminalPosition cursorPosition = terminal.getCursorPosition();
		TerminalSize terminalSize = terminal.getTerminalSize();

		// Print the prompt and then move the cursor to be just beyond it.
		textGraphics.putString(cursorPosition, prompt);
		cursorPosition = cursorPosition.withColumn(cursorPosition.getColumn() + prompt.length());
		screen.refresh();

		while (!done) {
			// Print a cursor character at the current position.
			textGraphics.putString(cursorPosition, "█");
			screen.refresh();

			// This is a blocking call.  There's no chance to print an
			// animated cursor while we're waiting for this.
			KeyStroke key = terminal.readInput();

			switch (key.getKeyType()) {
				case Character:
					result += key.getCharacter();
					// Echo the character the user entered.
					textGraphics.putString(cursorPosition, key.getCharacter().toString());
					cursorPosition = cursorPosition.withColumn(cursorPosition.getColumn() + 1);
					break;
				case Enter:
					// The user committed the changes.
					done = true;
					break;
				case Escape:
					// The user canceled the input.
					return "";
				case Backspace:
					if (result.length() > 0) {
						result = result.substring(0, result.length() - 1);

						// Clear the current cursor.
						textGraphics.putString(cursorPosition, " ");

						// Back off to the previous position.
						// During the next loop ieration, the cursor will print over it.
						cursorPosition = cursorPosition.withColumn(cursorPosition.getColumn() - 1);
					}
					break;
				default:
					// Ignore.
			}
		} // end (while not done)

		// Clear the cursor so the user isn't confused.
		textGraphics.putString(cursorPosition, " ");
		screen.refresh();

		return result;
	}

	public static void main(String[] args) throws IOException {

		// Setup terminal and screen layers
		Terminal terminal = new DefaultTerminalFactory().createTerminal();
		Screen screen = new TerminalScreen(terminal);
		screen.startScreen();
		screen.refresh();

		try {
			// Print welcome message
			final TextGraphics textGraphics = terminal.newTextGraphics();
			textGraphics.putString(10, 0, "Hi welcome to " + " VRION");
			screen.refresh();

			// Get the user's name
			terminal.setCursorPosition(0, 5);
			String name = getline(terminal, screen, "What is your name? ");
			textGraphics.putString(0, 6, String.format("I hope you enjoy your stay, %s.", name));
			screen.refresh();

			// Quit on the next keystroke
			textGraphics.putString(0, 8, "Press any key to exit", SGR.BOLD);
			screen.refresh();
			waitForKeyPress(terminal);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Shut down the terminal safely.
			try {
				if (screen != null) {
					screen.stopScreen();
				}
				if (terminal != null) {
					terminal.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
