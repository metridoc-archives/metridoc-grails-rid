package metridoc.rid

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(RidCourseSponsorController)
@Mock(RidCourseSponsor)
class RidCourseSponsorControllerTests {

    void testInvalidSave() {
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridCourseSponsor/list")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridCourseSponsor/list"

        controller.params.inForm = 1
        controller.save()

        assert response.redirectedUrl == '/ridCourseSponsor/list'
        assert flash.message == null
        assert RidCourseSponsor.count() == 0
    }

    void testValidSave() {
        def token = SynchronizerTokensHolder.store(session)
        controller.params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken("/ridCourseSponsor/list")
        controller.params[SynchronizerTokensHolder.TOKEN_URI] = "/ridCourseSponsor/list"

        controller.flash.alerts = []
        controller.params.name = "test"
        controller.params.inForm = 1
        controller.save()

        assert response.redirectedUrl == '/ridCourseSponsor/list'
        assert flash.message != null
        assert flash.alerts.size() == 0
        assert RidCourseSponsor.count() == 1

        // Cannot submit repeatedly
        response.reset()
        controller.save()
        assert response.redirectedUrl == '/ridCourseSponsor/list'
        assert flash.alerts.get(0) == "Don't click the create button more than one time to make duplicated submission!"
    }
}
