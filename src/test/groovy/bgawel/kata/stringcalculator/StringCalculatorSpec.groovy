package bgawel.kata.stringcalculator

import spock.lang.Specification

class StringCalculatorSpec extends Specification {

    private StringCalculator calculator

    def "return 0 for an empty input string"() {
        when:
        def result = calculator.add("")

        then:
        result == 0
    }

    def "return an input number for a single number"() {
        when:
        def result = calculator.add("1")

        then:
        result == 1
    }

    def "return a sum of numbers for two numbers"() {
        when:
        def result = calculator.add("1,2")

        then:
        result == 3
    }

    def "return a sum of numbers for unknown number of arguments/numbers"() {
        when:
        def result = calculator.add("1,2,3,4,5")

        then:
        result == 15
    }

    def "handle new lines between numbers (instead of commas)"() {
        when:
        def result = calculator.add("1\n2,3")

        then:
        result == 6
    }


    def "support different delimiters"() {
        when:
        def result = calculator.add("//;\n1;2")

        then:
        result == 3
    }

    def "support different delimiters even if they are special regexp characters"() {
        when:
        def result = calculator.add("//.\n1.2")

        then:
        result == 3
    }

    def "throw an exception if a negative number is passed; show all of them in the exception message"() {
        when:
        def result = calculator.add("1,-2,3,-4")

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "negatives not allowed: -2,-4"
    }

    def "ignore numbers bigger than 1000"() {
        when:
        def result = calculator.add("2,1001,1000")

        then:
        result == 1002
    }

    def "support different delimiters of any length with the format //[delimiter]\n"() {
        when:
        def result = calculator.add("//[***]\n1***2***3")

        then:
        result == 6
    }

    def "support multiple different delimiters of any length with the format //[delim1][delim2]\n"() {
        when:
        def result = calculator.add("//[**][%%][.]\n1**2%%3.4")

        then:
        result == 10
    }

    def setup() {
        calculator = new StringCalculator()
    }
}
