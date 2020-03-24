package com.webservice;

import com.database.MongoDBPoperations;
import com.database.config.MongoConfig;
import com.database.model.City;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class WebServiceController {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    private MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    private MongoDBPoperations ops = new MongoDBPoperations();

    public static void main(String[] args) {
        SpringApplication.run(WebServiceController.class, args);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public StringBuilder show_all() {
        return ops.getAllCities(mongoOperation);
    }

    @RequestMapping(value = "/show/{cityName}", method = RequestMethod.GET)
    public String show(@PathVariable("cityName") String cityName) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek != null) {
            return kek.getCityName() + ": " + kek.getDescription().concat("\n");
        }

        return "No info about " + cityName + "\n";
    }

    @RequestMapping(value = "/add/{cityName}", method = RequestMethod.POST)
    public String add(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek == null) {
            City city = new City(cityName, description);
            ops.saveCity(mongoOperation, city);

            return "Added.\n";
        }
        return "Already exists.\n";
    }

    @RequestMapping(value = "/remove/{cityName}", method = RequestMethod.GET)
    public String remove(@PathVariable("cityName") String cityName) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek != null) {
            ops.removeCity(mongoOperation, "cityName", cityName);
            return "Removed.\n";
        }
        return "Nothing to remove.\n";
    }

    @RequestMapping(value = "/update/{cityName}", method = RequestMethod.POST)
    public String update(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek != null) {
            ops.updateCity(mongoOperation, "cityName", cityName, "description", description);
            return "Updated.\n";
        }

        return "Nothing to update.\n";
    }
}
