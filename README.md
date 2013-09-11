## RaceTrack

This is a small web application built using [Grails](http://grails.org). It follows along with the application built in the [Getting Started with Grails, Second Edition](http://www.infoq.com/minibooks/grails-getting-started).

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