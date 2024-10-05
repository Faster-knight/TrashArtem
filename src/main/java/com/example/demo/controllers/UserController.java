package com.example.demo.controllers;

import com.example.demo.app.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    public UserController() {
        this.userService = new UserService();
    }
    @PostMapping("/add")
    public ResponseEntity<HashMap<String, Object>> addUserRoute(
            @RequestBody(required = true) HashMap<String, Object> bodyRequest,
            @RequestHeader(required = false, name = "Application-Header",  defaultValue = "none") String AppHeaderInput
    ) {
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> bodyResponse = new HashMap<>();
        Integer id_temp;
        String name_temp;
        try {
            id_temp = (Integer)bodyRequest.get("id");
            name_temp = (String)bodyRequest.get("name");
        } catch (Exception e) {
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Objects.equals(AppHeaderInput, "AppHeader")) {
            // Вызываем User Service
            User temp = new User(id_temp, name_temp);
            boolean t = userService.addUserInAppData(temp);
            headersResponse.setContentType(MediaType.APPLICATION_JSON);
            bodyResponse.put("data", t);
        }
        else {
            bodyResponse.put("msg", "Application operation failed");
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> deleteUserRoute(
            @RequestBody(required = true) HashMap<String, Object> bodyRequest,
            @RequestHeader(required = false, name = "Application-Header", defaultValue = "none") String AppHeaderInput
    ) {
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> bodyResponse = new HashMap<>();
        Integer id_temp;
        try {
            id_temp = (Integer)bodyRequest.get("id");
        } catch (Exception e) {
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (Objects.equals(AppHeaderInput, "AppHeader")) {
            // Вызываем User Service
            boolean temp = userService.delUserInAppDataByID(id_temp);
            headersResponse.setContentType(MediaType.APPLICATION_JSON);
            bodyResponse.put("data", temp);
        }
        else {
            bodyResponse.put("msg", "Application operation failed");
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.OK);
    }
    // ПОЛОМКА С ОТСУТВИЕМ ПРОВЕРКИ НА HEADER УДАЛИ КОММЕНТАРИЙ
    @PostMapping("/all")
    public ResponseEntity<HashMap<String, Object>> getAllUsersRoute(
            @RequestHeader(required = false, name = "Application-Header", defaultValue = "none") String headerRequest
    ) {
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> bodyResponse = new HashMap<>();
        bodyResponse.put("data", userService.getUsersApp());
        return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.OK);
    }
    @PostMapping("/setName")
    public ResponseEntity<HashMap<String, Object>> setNameUserRoute(
            @RequestBody(required = true) HashMap<String, Object> bodyRequest,
            @RequestHeader(required = false, name = "Application-Header", defaultValue = "none") String value
    ) {
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> bodyResponse = new HashMap<>();
        try {
            Integer id = (Integer) bodyRequest.get("id");
            String new_name = (String) bodyRequest.get("name");
            boolean f = userService.setUserName(id, new_name);
            bodyResponse.put("data", f);
        } catch (Exception e) {
            bodyResponse.put("msg", "Operation failed");
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.OK);
    }
    @PostMapping("/contact/add")
    public ResponseEntity<HashMap<String, Object>> addContactUserRoute(
            @RequestBody(required = true) HashMap<String, Object> bodyRequest,
            @RequestHeader(required = false, name = "Application-Header", defaultValue = "none") String value
    ) {
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> bodyResponse = new HashMap<>();
        try {
            Integer id = (Integer)bodyRequest.get("id");
            String name = (String)bodyRequest.get("name");
            String value2 = (String)bodyRequest.get("value");
            boolean f = userService.addContactInUserByID(id, name, value2);
            bodyResponse.put("data", f);
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.OK);
        } catch (Exception e) {
            bodyResponse.put("msg", "not add success");
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ПОЛОМКА С ОТСУТВИЕМ ПРОВЕРКИ НА HEADER УДАЛИ КОММЕНТАРИЙ
    @PostMapping("/contact/delete")
    public ResponseEntity<HashMap<String, Object>> deleteContactUserRoute(
            @RequestBody(required = true) HashMap<String, Object> bodyRequest,
            @RequestHeader(required = false) String value
    ) {
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> bodyResponse = new HashMap<>();
        try {
            // Поломка с отсуствием проверки null in UserService
            Integer id = (Integer)bodyRequest.get("id");
            String name = (String)bodyRequest.get("name");
            boolean f = userService.delContactInUserBuID(id, name);
            bodyResponse.put("data", f);
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.OK);
        } catch (Exception e) {
            bodyResponse.put("msg", "not add success");
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/contact/get")
    public ResponseEntity<HashMap<String, Object>> getContactsUserRoute(
            @RequestBody(required = true) HashMap<String, Object> bodyRequest,
            @RequestHeader(required = false) String value
    ) {
        HttpHeaders headersResponse = new HttpHeaders();
        headersResponse.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> bodyResponse = new HashMap<>();
        try {
            // Поломка с отсуствием проверки null in UserService
            Integer id = (Integer)bodyRequest.get("id");
            HashMap<String, String> data = userService.getContactsByUserID(id);
            bodyResponse.put("data", data);
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.OK);
        } catch (Exception e) {
            bodyResponse.put("msg", "not add success");
            return new ResponseEntity<>(bodyResponse, headersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
