package com.davydov.webservice;

/*import org.springframework.web.bind.annotation.*;


//@RestController
public class WebServiceController {
    //private Peripheral periphery = new Peripheral();


    @RequestMapping(value = "/cities/list", method = RequestMethod.GET)
    public String showAll() {
        return "kek";//new Gson().toJson(periphery.getAll());
    }

    @RequestMapping(value = "/cities/{cityName}", method = RequestMethod.GET)
    public String show(@PathVariable("cityName") String cityName) {
        return "kek";//return new Gson().toJson(periphery.get(cityName));
    }

    @RequestMapping(value = "/cities/add/{cityName}", method = RequestMethod.POST)
    public boolean add(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        return true;//return periphery.add(cityName, description);
    }

    @RequestMapping(value = "/cities/remove/{cityName}", method = RequestMethod.GET)
    public boolean remove(@PathVariable("cityName") String cityName) {
        return true;//return periphery.remove(cityName);
    }

    @RequestMapping(value = "/cities/update/{cityName}", method = RequestMethod.POST)
    public boolean update(@PathVariable("cityName") String cityName, @RequestParam(value = "description") String description) {
        return true;//return periphery.update(cityName, description);
    }
}
*/