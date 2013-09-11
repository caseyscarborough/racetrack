import grails.util.GrailsUtil
import racetrack.Race
import racetrack.Registration
import racetrack.Runner

class BootStrap {

    def init = { servletContext ->
        if (GrailsUtil.isDevelopmentEnv()) {
            // Add sample runner.
            def jane = new Runner(
                firstName: "Jane",
                lastName: "Doe",
                dateOfBirth: (new Date() - 365*30),
                gender: "F",
                address: "123 Main Street",
                city: "Goose",
                state: "NC",
                zipcode: "12345",
                email: "jane@wherever.com"
            )
            jane.save()
            if(jane.hasErrors()) { println jane.errors }

            // Add sample race.
            def trot = new Race(
                name:"Turkey Trot",
                startDate:(new Date() + 90),
                city :"Duck",
                state: "NC",
                distance: 5.0,
                cost: 20.0,
                maxRunners: 350
            )
            trot.save()
            if(trot.hasErrors()){ println trot.errors }

            // Add sample registration.
            def reg = new Registration(
                paid: false,
                runner: jane,
                race: trot
            )
            reg.save()
            if(reg.hasErrors()){ println reg.errors }
        }
    }
    def destroy = {
    }
}
