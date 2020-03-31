package com.webservice;


import com.database.Peripheral;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class WebServiceController {
    private Peripheral periphery = new Peripheral();

    public static void main(String[] args) {
        SpringApplication.run(WebServiceController.class, args);
    }

    @RequestMapping(value = "/cities/list", method = RequestMethod.GET)
    public StringBuilder show_all() {
        return periphery.showAll();
    }

    @RequestMapping(value = "/cities/{cityName}", method = RequestMethod.GET)
    public String show(@PathVariable("cityName") String cityName) {
        return periphery.show(cityName);
    }

    @RequestMapping(value = "/cities/add/{cityName}", method = RequestMethod.POST)
    public String add(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        return periphery.add(cityName, description);
    }

    @RequestMapping(value = "/cities/remove/{cityName}", method = RequestMethod.GET)
    public String remove(@PathVariable("cityName") String cityName) {
        return periphery.remove(cityName);
    }

    @RequestMapping(value = "/cities/update/{cityName}", method = RequestMethod.POST)
    public String update(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        return periphery.update(cityName, description);
    }
}
