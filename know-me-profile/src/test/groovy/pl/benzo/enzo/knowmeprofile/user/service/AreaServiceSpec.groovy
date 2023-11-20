package pl.benzo.enzo.knowmeprofile.user.service

import pl.benzo.enzo.knowmeprofile.user.implementation.database.AreaRepository
import pl.benzo.enzo.knowmeprofile.user.implementation.mapper.AreaMapper
import pl.benzo.enzo.knowmeprofile.user.implementation.service.AreaService
import spock.lang.Specification



class AreaServiceSpec extends Specification {

    def areaRepository = Mock(AreaRepository)
    def areaMapper = Mock(AreaMapper)
    def service = new AreaService(areaRepository, areaMapper)


    def "deleteArea should call repository method"() {
        given:
        Long id = 1L

        when:
        service.deleteArea(id)

        then:
        1 * areaRepository.deleteAreaByUser_Id(id)
    }
}

