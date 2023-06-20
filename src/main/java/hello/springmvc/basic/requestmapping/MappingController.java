package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-go"}, method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mappingGetV2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId = {}", data);
        return "ok";
    }


    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String userId,
                              @PathVariable("") Long orderId ){
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    // params => 필수 파라미터, 해당 파라미터 정보가 있어야 실행
    @GetMapping(value = "mapping-param", params = "mode=debug")
    public String mappingPath(){
        log.info("mappingPath");
        return "ok";
    }


    // headers => 필수 헤더, 해당 헤더 정보가 있어야 실행
    @GetMapping(value = "mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    @GetMapping(value = "mapping-consume", consumes = "application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    @PostMapping(value = "mapping-produce", produces = "text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }

}
