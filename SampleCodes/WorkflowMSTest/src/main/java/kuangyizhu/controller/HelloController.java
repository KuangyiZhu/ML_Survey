package kuangyizhu.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kuangyizhu.TestProperties;
//import kuangyizhu.repository.TransactionDAO;
//import kuangyizhu.repository.TransactionDO;
//import kuangyizhu.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.http.ResponseEntity;
/*import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;*/
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private TestProperties testProperties;

/*    @Autowired
    private TransactionService transactionService;*/

/*
    @Autowired
    private TransactionDAO transactionDAO;
*/

/*
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;
*/

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/readConfigure")
    public String readConfigure() {return testProperties.getHelloworld();}

    @GetMapping("/testResponse")
    public ResponseEntity<String> testResponse () {
        return ResponseEntity.ok()
                .header("Test", "Test")
                .body("This is a test");
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static public class Test implements Serializable {

        @JsonProperty("int")
        private Integer intValue = 0;
        private String stringValue = null;
        private String stringValue2 = null;
        private Boolean boolValue = false;
        private Date datetimeValue = null;
        @JsonIgnore
        private int zzz = 0;

    }

    @RequestMapping("/testJsonResponse")
    @ResponseBody
    public Test testJsonResponse() {
        return Test.builder()
                .intValue(1)
                .stringValue("test")
                .stringValue2(null)
                .boolValue(false)
                .datetimeValue(new Date())
                .build();
    }

    @RequestMapping(value = "/testJsonRequestResponse", method = RequestMethod.POST)
    @ResponseBody
    public Test testJsonRequestResponse(@RequestBody Test test) {
        return Test.builder()
                .intValue(test.getIntValue())
                .stringValue2(test.getStringValue())
                .stringValue(null)
                .boolValue(test.getBoolValue())
                .datetimeValue(new Date())
                .build();
    }

    @GetMapping("/testRestTemplate")
    public String testRestTempalte() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.google.com";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

/*    @GetMapping("/testJPA")
    @ResponseBody
    public TransactionDO getTransaction(@RequestParam (value = "transId") String transactionId) {
        return transactionDAO.findByTransactionId(transactionId).get().get(0);
    }*/

/*    @RequestMapping(value = "/testJPAInsert", method = RequestMethod.POST)
    public String testJPAInsert(@RequestBody Test test) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        System.out.println(ow.writeValueAsString(test));
        transactionService.insert(ow.writeValueAsString(test));
        return "Success";
    }*/

/*    @GetMapping("/testJPAQueryParam")
    @ResponseBody
    public List<TransactionDO> testJPAQueryParam(@RequestParam (value = "transId") String transactionId) {
        return transactionDAO.findByIdParam(transactionId);
    }*/

/*    @GetMapping("/testJPAQueryParamNative")
    @ResponseBody
    public List<String> testJPAQueryParamNative(@RequestParam (value = "transId") String transactionId) {
        return transactionDAO.findByIdParamNative(transactionId);
    }*/

/*
    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input) {
        this.kafkaTemplate.send("WorkflowTest", input);
    }
*/

/*    @KafkaListener(id = "WorkflowTestGroup", topics = "WorkflowTest")
    public void listen(String input, Acknowledgment ack)  {
        System.out.print("input value: " + input);
        ack.acknowledge();
    }*/

}

