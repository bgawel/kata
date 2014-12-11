package bgawel.kata.stringcalculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * String Calculator Kata
 *
 * @see http://osherove.com/tdd-kata-1/
 * Important: make sure you only test for correct inputs. there is no need to test for invalid inputs for this kata
 * Incorrect inputs are simply not handled.
 *
 * @author bgawel
 */
public class StringCalculator {

    private static final int IGNORE_NUMBERS_BIGGER_THAN = 1000;

    private InputNumbersParser inputNumbersParser = new InputNumbersParser();

    public int add(final String inputNumbers) {
        List<String> singleNumbers = inputNumbersParser.parse(inputNumbers);
        int sum = 0;
        if (singleNumbers.size() > 0) {
            List<Integer> negativeNumbers = new ArrayList<>();
            Integer number;
            for (String numberAsString : singleNumbers) {
                number = Integer.valueOf(numberAsString);
                if (number < 0) {
                    negativeNumbers.add(number);
                } else if (number <= IGNORE_NUMBERS_BIGGER_THAN) {
                    sum += number;
                }
            }
            if (negativeNumbers.size() > 0) {
                negativesNotAllowed(negativeNumbers);
            }
        }
        return sum;
    }

    private void negativesNotAllowed(final List<Integer> negativeNumbers) {
        throw new IllegalArgumentException("negatives not allowed: " + StringUtils.join(negativeNumbers, ','));
    }
}
