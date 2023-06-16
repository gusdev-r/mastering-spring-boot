package test.outside;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutsideController {
    @GetMapping("testHelloOutside")
    public String testHelloOutside() {
        return "Testing a package outside of the note application";
    }
}
