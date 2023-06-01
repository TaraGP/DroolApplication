package com.Ivoyant.ConfigSB.controller;

import org.Ivoyant.model.ConfigDetails;
import org.Ivoyant.service.ConfigDeleteService;
import org.Ivoyant.service.ConfigGetService;
import org.Ivoyant.service.ConfigSetService;
import org.Ivoyant.service.ConfigUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

@RestController
public class ConfigController {

    private static final Logger logger= LoggerFactory.getLogger(ConfigController.class);
    @PostMapping("/config")
    public void postData(@RequestBody ConfigDetails configDetails) {
        try {
            ConfigSetService.setConfigDetails(configDetails);
        } catch (Exception e) {
            logger.error("failed to post data into DB in ConfigController:" +e);
           // System.out.println("Exception:" + e);
        }
    }

    @GetMapping("/config/{id}")
    public ResponseEntity<ConfigDetails> getData(@PathVariable int id) throws SQLException {
        ResponseEntity<ConfigDetails> responseEntity = null;
        try {
            ConfigDetails result = ConfigGetService.getConfigData(id);
            if (result != null) {
                responseEntity = ResponseEntity.ok(result);
            } else {
                responseEntity = ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
//            System.out.println("Exception: " + e);
            logger.error("failed to get data from DB in ConfigController :" +e);
        }
        return responseEntity;
    }

//    @PutMapping("/config/{id}")
//    public ResponseEntity<ConfigDetails> updateData(@PathVariable int id, @RequestBody ConfigDetails updatedConfig) throws SQLException {
//        ResponseEntity<ConfigDetails> responseEntity = null;
//        try {
//            boolean isUpdated = ConfigUpdateService.updateConfigData(id).isActive();
//            if (isUpdated) {
//                responseEntity = ResponseEntity.ok(updatedConfig);
//            } else {
//                responseEntity = ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            logger.error("failed to update data into DB in ConfigController :" +e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//        ConfigDetails existingConfig = ConfigGetService.getConfigData(id);
//        if (existingConfig == null) {
//            responseEntity = ResponseEntity.notFound().build();
//            logger.error("ID " + id + " not found in the database.");
//        }
//        return responseEntity;
//    }

    @PutMapping("/config/{id}")
    public ResponseEntity<ConfigDetails> updateData(@PathVariable int id, @RequestBody ConfigDetails updatedConfig) throws SQLException {
        ResponseEntity<ConfigDetails> responseEntity = null;
        try {
            ConfigDetails existingConfig = ConfigUpdateService.getConfigData(id);
            if (existingConfig != null) {
                ConfigDetails updatedConfigDetails = ConfigUpdateService.updateConfigData(id, updatedConfig);
                responseEntity = ResponseEntity.ok(updatedConfigDetails);
            } else {
                responseEntity = ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Failed to update data into DB in ConfigController: " + e);
        }
        return responseEntity;
    }

    @DeleteMapping("/config/{id}")
    public ResponseEntity<String> deleteData(@PathVariable int id) {
        ResponseEntity<String> responseEntity = null;
        try {
            // Delete the data with the specified ID
            boolean deleted = ConfigDeleteService.deleteConfigData(id);

            if (deleted) {
                responseEntity = ResponseEntity.ok("Data with ID " + id + " deleted successfully.");
            } else {
                responseEntity = ResponseEntity.notFound().build();
                System.out.println("item not found with the id: " + id);
            }
        } catch (Exception e) {
            //System.out.println("Exception: " + e);
            logger.error("failed to delete data from DB in ConfigController:" +e);
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return responseEntity;
    }

}