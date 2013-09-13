package racetrack

import org.codehaus.groovy.grails.plugins.codecs.Base64Codec
import org.junit.*
import grails.test.mixin.*

@TestFor(UserController)
@Mock(User)
class UserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/user/list" == response.redirectedUrl
    }

    public void setUp() {
        String.metaClass.encodeAsBase64 = {->
            Base64Codec.encode(delegate)
        }
        String.metaClass.encodeAsSHA = {->
            SHACodec.encode(delegate)
        }
    }

    void testAuthenticate() {
        def jdoe = new User(login:"jdoe", password:"password".encodeAsSHA())
        mockDomain(User, [jdoe])
        controller.params.login = "jdoe"
        controller.params.password = "password"

        controller.authenticate()
        assertNotNull controller.session.user
        assertEquals "jdoe", controller.session.user.login

        response.reset()
        controller.params.password = "foo"
        controller.authenticate()
        assertTrue controller.flash.message.startsWith("Sorry, jdoe")
    }

}
