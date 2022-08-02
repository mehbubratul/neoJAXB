package com.mehbub.neoJAXB.controller;

import com.mehbub.neoJAXB.dto.User;
import com.mehbub.neoJAXB.enumeration.ResponseStatus;
import com.mehbub.neoJAXB.response.ResponseMessage;
import com.mehbub.neoJAXB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.AbstractMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    private final String USER_API = "https://jsonplaceholder.typicode.com/users";

    //region Public Methods - Marshalling

    /**
     * This method will save single user info in xml
     * Steps:
     * 1. Will call the user api
     * 2. Then, pass the user array to service class to save in xml file.
     */
    @PostMapping("/users/{id}")
    public ResponseEntity<ResponseMessage> saveUserInXML(@PathVariable Integer id) {
        String url = USER_API + "/{id}";
        User user = restTemplate.getForObject(url, User.class, id);
        AbstractMap.SimpleEntry<Integer, List<User>> entry
                = userService.saveUserInXML(user);
        if (entry.getKey().equals(ResponseStatus.SUCCESS.getValue())) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(
                            "Successfully saved user in xml file.",
                            ResponseStatus.SUCCESS.getValue(),
                            null
                    ));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(
                            new ResponseMessage(
                                    "Unable to save user info in xml.",
                                    ResponseStatus.EXCEPTION.getValue(),
                                    null));
        }
    }

    /**
     * This method will save list of user in xml
     * Steps:
     * 1. Will call the user api
     * 2. Then, pass the user array to service class to save in xml files.
     */
    @PostMapping("/users")
    public ResponseEntity<ResponseMessage> saveUsersInXML() {
        User[] users = restTemplate.getForObject(USER_API, User[].class);
        AbstractMap.SimpleEntry<Integer, List<User>> entry = userService.saveUsersInXML(users);

        if (entry.getKey().equals(ResponseStatus.SUCCESS.getValue())) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(
                            "Successfully saved user in xml file.",
                            ResponseStatus.SUCCESS.getValue(),
                            null
                    ));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(
                            new ResponseMessage(
                                    "Unable to save users info in xml.",
                                    ResponseStatus.EXCEPTION.getValue(),
                                    null));
        }
    }
    //endregion

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseMessage> getUserByIdFromXML(@PathVariable Integer id) {

        AbstractMap.SimpleEntry<Integer, List<User>> entry = userService.getUserByIdFromXML(id);

        if (entry.getKey() == ResponseStatus.VALIDATION.getValue()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(
                            "Unable to get user info from xml",
                            ResponseStatus.VALIDATION.getValue(),
                            null
                    ));
        }

        if (entry.getKey().equals(ResponseStatus.SUCCESS.getValue())) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(
                            "Successfully received user info from xml file.",
                            ResponseStatus.SUCCESS.getValue(),
                            entry.getValue()
                    ));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(
                            new ResponseMessage(
                                    "Unable to get user info from xml.",
                                    ResponseStatus.EXCEPTION.getValue(),
                                    null));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> getUsersFromXML() {

        AbstractMap.SimpleEntry<Integer, List<User>> entry = userService.getUsersFromXML();

        if (entry.getKey() == ResponseStatus.VALIDATION.getValue()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(
                            "Unable to get user info from xml",
                            ResponseStatus.VALIDATION.getValue(),
                            null
                    ));
        }

        if (entry.getKey().equals(ResponseStatus.SUCCESS.getValue())) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessage(
                            "Successfully received user info from xml file.",
                            ResponseStatus.SUCCESS.getValue(),
                            entry.getValue()
                    ));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(
                            new ResponseMessage(
                                    "Unable to get user info from xml.",
                                    ResponseStatus.EXCEPTION.getValue(),
                                    null));
        }
    }
}
