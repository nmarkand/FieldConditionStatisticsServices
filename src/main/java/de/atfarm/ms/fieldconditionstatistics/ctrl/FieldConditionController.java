package de.atfarm.ms.fieldconditionstatistics.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionRequest;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionResponse;
import de.atfarm.ms.fieldconditionstatistics.services.FieldConditionService;

@Controller
@RestController
@RequestMapping("/")
public class FieldConditionController {
    
    private static final Logger log = LoggerFactory.getLogger(FieldConditionController.class);
    
    private FieldConditionService fieldConditionService;
    
    @Autowired
    public FieldConditionController(FieldConditionService fieldConditionService) {
        this.fieldConditionService = fieldConditionService;
    }
    
    @RequestMapping(path = "field-statistics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldStatistics>  getFieldStatistics() {
        log.info("Get field-statistics");
        return ResponseEntity.ok().body(fieldConditionService.getFieldStatistics());
    }
    
    @RequestMapping(path = "field-conditions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreFieldConditionResponse> storeFieldCondition(@RequestBody StoreFieldConditionRequest storeFieldConditionRequest) {
        log.info("Store field-conditions");
        return ResponseEntity.ok()
                .body(new StoreFieldConditionResponse(fieldConditionService.storeFieldCondition(storeFieldConditionRequest).getId()));
    }
}
