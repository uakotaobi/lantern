package main;

import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

// Lanterna API documentation: https://mabe02.github.io/lanterna/apidocs/3.0/


public class my {


	// CSI-style escape sequences.
	public class FG {
			public static final String Black = "\u001b[0;30m";
			public static final String Red = "\u001b[0;31m";
			public static final String Green = "\u001b[0;32m";
			public static final String Orange = "\u001b[0;33m";
			public static final String Blue = "\u001b[0;34m";
			public static final String Magenta = "\u001b[0;35m";
			public static final String Cyan = "\u001b[0;36m";
			public static final String White = "\u001b[0;37m";
			public static final String Gray = "\u001b[1;30m";
			public static final String BrightRed = "\u001b[1;31m";
			public static final String BrightGreen = "\u001b[1;32m";
			public static final String Yellow = "\u001b[1;33m";
			public static final String BrightBlue = "\u001b[1;34m";
			public static final String BrightMagenta = "\u001b[1;35m";
			public static final String BrightCyan = "\u001b[1;36m";
			public static final String BrightWhite = "\u001b[1;37m";
	}
	public class BG {
			public static final String Black = "\u001b[0;40m";
			public static final String Red = "\u001b[0;41m";
			public static final String Green = "\u001b[0;42m";
			public static final String Orange = "\u001b[0;43m";
			public static final String Blue = "\u001b[0;44m";
			public static final String Magenta = "\u001b[0;45m";
			public static final String Cyan = "\u001b[0;46m";
			public static final String White = "\u001b[0;47m";
			public static final String Gray = "\u001b[1;40m";
			public static final String BrightRed = "\u001b[1;41m";
			public static final String BrightGreen = "\u001b[1;42m";
			public static final String Yellow = "\u001b[1;43m";
			public static final String BrightBlue = "\u001b[1;44m";
			public static final String BrightMagenta = "\u001b[1;45m";
			public static final String BrightCyan = "\u001b[1;46m";
			public static final String BrightWhite = "\u001b[1;47m";
	}

	/**
	 * Waits for the user to press any key.
	 * @throws IOException
	 */
	public static void waitForKeyPress(Terminal terminal) throws IOException {
		terminal.readInput();
	}

	/**
	 * Asks the user to type some input.
	 * @param Terminal The Terminal object we'll use for cursor positioning and
	 *        input.
	 * @param Screen The Screen object we'll use when we need to push screen
	 *        updates.
	 * @param Prompt A string that will be printed before gathering input from
	 *        the user.  Can be empty. but if it's not, then you should probably
	 *        put a space character at the end of it.
	 * @throws IOException
	 */
	public static String getline(Terminal terminal, Screen screen, String prompt) throws IOException {
		String result = "";
		boolean done = false;
		TextGraphics textGraphics = terminal.newTextGraphics();
		TerminalPosition cursorPosition = terminal.getCursorPosition();

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
					// The user canceled the input.  Quit the loop prematurely
					// so we can erase the cursor.
					result = "";
					done = true;
					break;
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
			textGraphics.putCSIStyledString(0, 3, String.format("%s<%s<%s<%s Character Selection %s>%s>%s>",
				FG.BrightCyan,
				FG.BrightBlue,
				FG.Blue,
				FG.BrightWhite,
				FG.Blue,
				FG.BrightBlue,
				FG.BrightCyan));
			screen.refresh();

			// Get the user's name
			terminal.setCursorPosition(0, 5);
			String name = getline(terminal, screen, "What is your name? ");
			textGraphics.putCSIStyledString(0, 6, String.format("I hope you enjoy your stay, %s%s.", FG.BrightRed, name));
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
