package com.davydov.webservice;

import com.davydov.database.CityRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/cities")
public class WebServiceController {
    @Autowired
    private CityRepository repository;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAll() {
        System.out.println("\n");
        System.out.println(repository.findAll());


        return new Gson().toJson(repository.findAll());
    }

    @RequestMapping(value = "/{cityName}", method = RequestMethod.GET)
    public String get(@PathVariable("cityName") String cityName) {
        // return null if nothing found
        return new Gson().toJson(repository.getCity(cityName));
    }

    @RequestMapping(value = "/add/{cityName}", method = RequestMethod.POST)
    public String add(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        repository.addCity(cityName, description);
        return "updated\n";
    }

    @RequestMapping(value = "/remove/{cityName}", method = RequestMethod.GET)
    public String remove(@PathVariable("cityName") String cityName) {
        repository.removeCity(cityName);
        return "updated\n";
    }

    @RequestMapping(value = "/update/{cityName}", method = RequestMethod.POST)
    public String update(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        repository.updateCity(cityName, description);
        return "updated\n";
    }
}
