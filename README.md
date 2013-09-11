## RaceTrack

This is a small web application built using [Grails](http://grails.org). It follows along with the application built in the [Getting Started with Grails, Second Edition](http://www.infoq.com/minibooks/grails-getting-started).

## Dependencies

The book uses Grails 1.2. I'm using the latest version of Grails (at this time is 2.2.4).

* Java JDK
* Grails 2.2.4

## Usage

You can use the project by cloning the repository and running the grails run-app command.

```bash
$ git clone https://bitbucket.org/csu_cscarborough/racetrack
$ cd racetrack
$ grails run-app
```

Then browse to [localhost:8080/racetrack](http://localhost:8080/racetrack) to see the application in action.

## Issues with following the book

Here are a list of the issues I've found while following along with the book (which uses Grails 1.2).

### Chapter 4

#### Changing Error Messages

Pages 28-29 deal with customizing the error messages on a by-class by-property basis. This works just fine, but it seems in the newer versions of Grails, when scaffolding, the HTML5 _required_ properties are added to the fields. This will make HTML5 validate your fields with a compatible browser, and will not use your customized error messages. The easiest way I found to disable this is to install the [grails templates](http://grails.org/doc/latest/ref/Command%20Line/install-templates.html) (which I've done in this project), and to edit the `src/templates/scaffolding/renderEditor.template` file (shown below). Comment out the `!isOptional()` method (line 259) and add `return false`. Comment out lines 182-183 as well.

```groovy
// Comment out lines 182-183 shown here.
// if (cp.min != null) sb << ' min="' << cp.min << '"'
// if (cp.max != null) sb << ' max="' << cp.max << '"'

// Comment out the isOptional() method and add return false.
private boolean isRequired() {
    // !isOptional()
    return false
}
```

This will then allow the custom error messages used in `i18n/messages.properties` to be used. You could also just statically scaffold the views and manually remove the HTML5 validation tags.

#### Testing Validations

On page 35, when you begin testing, you'll see the following piece of code.

```groovy
import grails.test.*

class RaceTests extends GrailsUnitTestCase {
    // ...
    void testInMiles() {
        def race = new Race(distance:5.0)
        assertEquals 3.107, race.inMiles()
    }
}
```

`GrailsUnitTestCase` is not necessarily needed, so your autogenerated tests will not show the inheritance like the sample code. It will more than likely be using the grails.test.mixin package. The functionality otherwise is the same. Once discrepancy I've found though is that the `assertEquals(double expected, double actual)` method has been deprecated in favor of `assertEquals(double expected, double actual, double epsilon)`. Epsilon is the value that the two doubles can be off by. So your test case will actually end up looking like this:

```groovy
package racetrack
import grails.test.mixin.*
import org.junit.*

@TestFor(Race)
class RaceTests {
    void testInMiles() {
        def race = new Race(distance:5.0)
        assertEquals(3.107, race.inMiles(), 0)
     }
}
```