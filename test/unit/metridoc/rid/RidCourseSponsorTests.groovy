package metridoc.rid

import grails.test.mixin.*
import org.junit.*

import static RidCourseSponsor.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RidCourseSponsor)
class RidCourseSponsorTests {

    @Test
    void testBootStrap() {
        List<String> cSponsor = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                "Dental", "SP2", "Design", "Annenberg", "Law", "Coursera",
                "Independent Research", "Outside Entity(please indicate)");
        for (String i in cSponsor) {
            def c = new RidCourseSponsor(name: i)
            c.save()
            if(c.hasErrors()) println c.errors
        }

        assert list().size() > 0
        def nameList = RidCourseSponsor.findAllByName("SEAS")
        assert nameList.size() == 1
        for(RidCourseSponsor i in nameList) {
            assert "SEAS" == i.name
        }

        def c = RidCourseSponsor.createCriteria()
        def names = c.get {
            projections {
                countDistinct('name')
            }
        }
        assert 15 == names


    }
}
