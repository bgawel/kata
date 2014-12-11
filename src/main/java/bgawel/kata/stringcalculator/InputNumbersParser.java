package bgawel.kata.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

class InputNumbersParser {

    private static final String DIFFERENT_DELIMITER_PREFIX = "//";

    private static final Pattern MULTIPLE_DELIMITERS_PATTERN = Pattern.compile("^\\/\\/\\[(.+)]\\\n(.+)");
    private static final String MULTIPLE_DELIMITERS_GROUP_SEPARATOR = Pattern.quote("][");

    private static final Pattern SINGLE_DELIMITER_PATTERN = Pattern.compile("^\\/\\/(.+)\\\n(.+)");

    private static final List<String> DEFAULT_DELIMITERS = Arrays.asList(",", "\n");

    private static final char REGEXP_OR = '|';

    public List<String> parse(final String inputNumbers) {
        List<String> parsedNumbers = new ArrayList<>();
        if (inputNumbersNotEmpty(inputNumbers)) {
            Pair<List<String>, String> delimitersAndNumbers = extractDelimitersAndNumbers(inputNumbers);
            String joinedDelimiters = joinDelimiters(delimitersAndNumbers.getLeft());
            parsedNumbers = splitNumbers(delimitersAndNumbers.getRight(), joinedDelimiters);
        }
        return parsedNumbers;
    }

    private Pair<List<String>, String> extractDelimitersAndNumbers(final String inputNumbers) {
        List<String> delimiters;
        String numbers;
        if (inputNumbers.startsWith(DIFFERENT_DELIMITER_PREFIX)) {
            Matcher matcher = MULTIPLE_DELIMITERS_PATTERN.matcher(inputNumbers);
            if (matcher.matches()) {
                delimiters = Arrays.asList(matcher.group(1).split(MULTIPLE_DELIMITERS_GROUP_SEPARATOR));
            } else {
                matcher = SINGLE_DELIMITER_PATTERN.matcher(inputNumbers);
                assert matcher.matches();
                delimiters = Arrays.asList(matcher.group(1));
            }
            numbers = matcher.group(2);
        } else {
            delimiters = DEFAULT_DELIMITERS;
            numbers = inputNumbers;
        }
        return Pair.of(escapeDelimiters(delimiters), numbers);
    }

    private List<String> escapeDelimiters(final List<String> delimiters) {
        List<String> escapedDelimiters = new ArrayList<>(delimiters.size());
        for (String delimiter : delimiters) {
            escapedDelimiters.add(Pattern.quote(delimiter));
        }
        return escapedDelimiters;
    }

    private String joinDelimiters(final List<String> delimiters) {
        return StringUtils.join(delimiters, REGEXP_OR);
    }

    private List<String> splitNumbers(final String numbers, final String regexpDelimiter) {
        return Arrays.asList(numbers.split(regexpDelimiter));
    }

    private boolean inputNumbersNotEmpty(final String inputNumbers) {
        return StringUtils.isNotBlank(inputNumbers);
    }
}
