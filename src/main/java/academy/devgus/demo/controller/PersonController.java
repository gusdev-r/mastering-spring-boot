package academy.devgus.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"v1/customers", "v1/customers/"})
public class PersonController {
    List<String> NAMES = List.of("Gustavo", "Mateus", "João", "Sarah", "maria");

    public List<String> list() {
        return NAMES;
    }

    @GetMapping("filter")
    public List<String> filter(@RequestParam(defaultValue = "") String name) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(name)).toList();
    }

    @GetMapping("filterOptional")
    public List<String> filter(@RequestParam Optional<String> name) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(name.orElse(""))).toList();
    }

    @GetMapping("filterList")
    public List<String> filter(@RequestParam(name = "name") List<String> name) {
        return NAMES.stream().filter(name::contains).toList();
    }

    @GetMapping("{name}")
    public String findByName(@PathVariable String name) {
        return NAMES.stream().filter(n -> n.equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(() -> "");
    }

}
