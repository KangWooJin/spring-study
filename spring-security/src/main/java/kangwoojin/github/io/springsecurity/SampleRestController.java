package kangwoojin.github.io.springsecurity;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

    @GetMapping("/sample")
    public ResponseEntity<String> sampleApi() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("sample");
    }
}
