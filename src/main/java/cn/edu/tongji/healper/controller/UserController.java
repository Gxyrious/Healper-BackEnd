package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.indto.LoginInfoInDto;
import cn.edu.tongji.healper.model.ClientEntity;
import cn.edu.tongji.healper.service.ConsultService;
import cn.edu.tongji.healper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConsultService consultService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginInfoInDto loginInfoInDto) {
        ClientEntity client = userService.findUserEntityByUserPhone(loginInfoInDto.getUserPhone());
        if(client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Client doesn't exist!");
        } else if(client.getPassword().equals(loginInfoInDto.getUserPassword())) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password error!");
        }
    }

    @PostMapping(value = "/history")
    public ResponseEntity addHistory() {
        consultService.addConsultHistory(1, 1, 100);
        return ResponseEntity.ok("ok");
    }
}
