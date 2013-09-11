package racetrack

class Race {

    // Domain class attributes
    String name
    Date startDate
    String city
    String state
    BigDecimal distance
    BigDecimal cost
    Integer maxRunners

    static constraints = {
        // Set the order for the fields in the scaffolded view,
        // in addition to setting constraints for the fields.
        name(blank:false, maxSize:50)
        // Add custom validator to disallow dates in the past.
        startDate(validator: {return (it > new Date())})
        city()
        state(inList: ["GA", "NC", "SC", "VA"])
        distance(min:0.0)
        cost(min:0.0, max:0.0)
        maxRunners(min:0, max:10000)
    }

    BigDecimal inMiles() {
        return distance * 0.6214
    }

}
