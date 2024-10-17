package hallpointer.address.logic.parser;

import static hallpointer.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hallpointer.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static hallpointer.address.logic.parser.CliSyntax.PREFIX_SESSION_NAME;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hallpointer.address.commons.core.index.Index;
import hallpointer.address.logic.commands.DeleteSessionCommand;
import hallpointer.address.logic.parser.exceptions.ParseException;
import hallpointer.address.model.session.SessionName;

/**
 * Parses input arguments and creates a new DeleteSessionCommand object
 */
public class DeleteSessionCommandParser implements Parser<DeleteSessionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSessionCommand
     * and returns a DeleteSessionCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSION_NAME, PREFIX_MEMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSION_NAME, PREFIX_MEMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSessionCommand.MESSAGE_USAGE));
        }
        SessionName name = ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSION_NAME).get());
        List<Index> memberIndexes = argMultimap.getAllValues(PREFIX_MEMBER).stream()
                .map(value -> {
                    try {
                        return ParserUtil.parseIndex(value);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid member index: " + value);
                    }
                }).collect(Collectors.toList());
        return new DeleteSessionCommand(name, memberIndexes);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
