package com.davydov.webservice;

import com.davydov.database.Peripheral;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebServiceController {
    @Autowired
    private Peripheral periphery;


    @RequestMapping(value = "/cities/list", method = RequestMethod.GET)
    public String showAll() {
        return new Gson().toJson(periphery.getAll());
    }

    @RequestMapping(value = "/cities/{cityName}", method = RequestMethod.GET)
    public String show(@PathVariable("cityName") String cityName) {
        return new Gson().toJson(periphery.get(cityName));
    }

    @RequestMapping(value = "/cities/add/{cityName}", method = RequestMethod.POST)
    public String add(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        periphery.add(cityName, description);
        return "updated\n";
    }

    @RequestMapping(value = "/cities/remove/{cityName}", method = RequestMethod.GET)
    public String remove(@PathVariable("cityName") String cityName) {
        periphery.remove(cityName);
        return "updated\n";
    }

    @RequestMapping(value = "/cities/update/{cityName}", method = RequestMethod.POST)
    public String update(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        periphery.update(cityName, description);
        return "updated\n";
    }
}
