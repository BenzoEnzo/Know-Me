package pl.benzo.enzo.knowmeprofile.user.core

import pl.benzo.enzo.knowmeprofile.user.implementation.util.GenerateID
import spock.lang.Specification

class GenerateIDSpec extends Specification {

    def "ID should have correct length"() {
        when:
        String id = GenerateID.create()

        then:
        id.length() == 9
    }

    def "IDs should be unique"() {
        given:
        Set<String> ids = new HashSet<>()
        int numberOfIdsToTest = 10000

        when:
        numberOfIdsToTest.times {
            ids.add(GenerateID.create())
        }

        then:
        ids.size() == numberOfIdsToTest
    }
}

