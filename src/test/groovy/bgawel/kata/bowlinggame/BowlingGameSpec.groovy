package bgawel.kata.bowlinggame

import spock.lang.Specification

class BowlingGameSpec extends Specification {

    private BowlingGame game

    def "max score is 300 points for 12 strikes"() {
        when:
        (1..12).each {
            game.roll(10)
        }

        then:
        game.score() == 300
    }

    def "a game without any bonuses"() {
        when:
        (1..10).each {
            game.roll(5)
            game.roll(3)
        }

        then:
        game.score() == 80
    }

    def "a game with one spare"() {
        when:
        game.roll(6)
        game.roll(4)
        (2..10).each {
            game.roll(5)
            game.roll(3)
        }

        then:
        game.score() == 10 + 5 + 72
    }

    def "a game with one strike"() {
        when:
        game.roll(10)
        (2..10).each {
            game.roll(5)
            game.roll(3)
        }

        then:
        game.score() == 10 + 8 + 72
    }

    def setup() {
        game = new BowlingGame()
    }
}
