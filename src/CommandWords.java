import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords {

    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("para", CommandWord.GO);
        validCommands.put("ajuda", CommandWord.HELP);
        validCommands.put("sair", CommandWord.QUIT);
        validCommands.put("investigar", CommandWord.INVESTIGATE);
        validCommands.put("falar", CommandWord.TALK);
        validCommands.put("voltar", CommandWord.BACK);
        validCommands.put("pegar", CommandWord.TAKE);
        validCommands.put("soltar", CommandWord.DROP);
        validCommands.put("bolsa", CommandWord.BAG);
        validCommands.put("olhar", CommandWord.LOOK);
    }

    /**
     * Find the CommandWord associated with a command word.
     *
     * @param commandWord The word to look up (as a string).
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     * if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() {
        for (String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

    public void getKeyHash(CommandWord word) {
            for(String key: validCommands.keySet()) {
                if(validCommands.get(key).equals(word)) {
                    System.out.println(key);
                }
        }
    }

}