package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static duke.DukeConstants.EXIT;

/**
 * This class handles the parsing of user commands.
 */
public class Parser {

    /**
     * Constructor for the Parser class.
     */
    public Parser() {

    }

    /**
     * Parses the user input and returns a Command
     * for the program to respond to.
     *
     * @param taskList TaskList to be used.
     * @param input User input.
     * @param ui Ui for display.
     * @return Command for program to execute.
     * @throws DukeException Exception to be thrown
     */
    public Command parse(TaskList taskList, String input, Ui ui) throws DukeException {
        input = input.toLowerCase();
        if (input.equals(EXIT)) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("poyo")) { // for fun
            return new NullCommand("poyo");
        } else {
            String[] subStrs = input.split(" ", 2); // to identify the keyword used
            assert subStrs.length < 3 : "There should be 2 or less strings";
            int index;
            Task temp;
            switch (subStrs[0]) {
            case "mark":
                if (subStrs.length == 1) { // no number was given
                    throw new DukeException(DukeException.MISSING_INDEX);
                }
                try {
                    index = Integer.parseInt(subStrs[1]) - 1;
                    if (index < 0 || index >= taskList.getSize()) { // to check if index is out of range
                        throw new DukeException(DukeException.OUT_OF_RANGE);
                    }

                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException(DukeException.WRONG_FORMAT);
                }
            case "unmark":
                if (subStrs.length == 1) {
                    throw new DukeException(DukeException.MISSING_INDEX);
                }
                try {
                    index = Integer.parseInt(subStrs[1]) - 1;
                    if (index < 0 || index >= taskList.getSize()) { // check if index is out of range
                        throw new DukeException(DukeException.OUT_OF_RANGE);
                    }
                    return new UnmarkCommand(index);

                } catch (NumberFormatException e) {
                    throw new DukeException(DukeException.WRONG_FORMAT);
                }
            case "delete":

                if (subStrs.length == 1) {
                    throw new DukeException(DukeException.MISSING_INDEX);
                }
                try {
                    index = Integer.parseInt(subStrs[1]) - 1;
                    if (index < 0 || index >= taskList.getSize()) {
                        throw new DukeException(DukeException.OUT_OF_RANGE);
                    }
                    assert taskList.getSize() > 0 : "Tasklist should contain items";
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    return new NullCommand("invalid input");
                }
            case "todo":
                if (subStrs.length == 1) {
                    throw new DukeException(DukeException.MISSING_DESCRIPTION);
                }
                temp = new Todo(subStrs[1]);
                return new AddCommand(temp);
            case "deadline":
                if (subStrs.length == 1) {
                    throw new DukeException(DukeException.MISSING_DESCRIPTION);
                }
                String[] dlDescs = subStrs[1].split(" /by ", 2);
                if (dlDescs.length < 2) {
                    throw new DukeException(DukeException.MISSING_DATE);
                }
                try {
                    LocalDate date = LocalDate.parse(dlDescs[1]);
                    temp = new Deadline(dlDescs[0], date);
                    return new AddCommand(temp);
                    //break;
                } catch (DateTimeParseException e) {
                    throw new DukeException(DukeException.WRONG_FORMAT_DATE);
                }
            case "event":
                if (subStrs.length == 1) {
                    throw new DukeException(DukeException.MISSING_DESCRIPTION);
                }
                String[] eventDescs = subStrs[1].split(" /at ", 2);
                if (eventDescs.length < 2) {
                    throw new DukeException(DukeException.MISSING_DATE);
                }
                try {
                    String[] timeDescs = eventDescs[1].split(" ", 2);
                    if (timeDescs.length > 1) {
                        LocalDate date = LocalDate.parse(timeDescs[0]);
                        temp = new Event(eventDescs[0], date, timeDescs[1]);
                    } else {
                        LocalDate date = LocalDate.parse(eventDescs[1]);
                        temp = new Event(eventDescs[0], date);
                    }
                    return new AddCommand(temp);
                } catch (DateTimeParseException e) {
                    throw new DukeException(DukeException.WRONG_FORMAT_DATE);
                }
            case "find":
                if (subStrs.length == 1) { // no number was given
                    throw new DukeException(DukeException.MISSING_DESCRIPTION);
                } else {
                    return new FindCommand(subStrs[1]);
                }
            default:
                throw new DukeException(DukeException.UNRECOGNISED_COMMAND);
            }
        }
    }
}