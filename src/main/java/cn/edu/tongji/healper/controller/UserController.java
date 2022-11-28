package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.indto.LoginInfoInDto;
import cn.edu.tongji.healper.indto.RegisterInfoInDto;
import cn.edu.tongji.healper.model.ClientEntity;
import cn.edu.tongji.healper.model.ConsultantEntity;
import cn.edu.tongji.healper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 示例1，一个不完善的登陆，只包含了来访者Client手机号的查询
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginInfoInDto loginInfoInDto) {
        ClientEntity client = userService.findClientEntityByUserPhone(loginInfoInDto.getUserPhone());
        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Client doesn't exist!");
        } else if (client.getPassword().equals(loginInfoInDto.getUserPassword())) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password error!");
        }
    }

    // 示例2，根据手机号查找个人信息
    @GetMapping(value = "/info")
    public ResponseEntity getInfoByUserPhone(@RequestParam String userphone) {
        ClientEntity client = userService.findClientEntityByUserPhone(userphone);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            ConsultantEntity consultant = userService.findConsultantEntityByUserPhone(userphone);
            if (consultant != null) {
                return ResponseEntity.ok(consultant);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Phone not found!");
            }
        }
    }

    //注册功能，存储用户信息
    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterInfoInDto registerInfoInDto) {
        if (userService.addClientInfo(registerInfoInDto.getNickname(), registerInfoInDto.getPassword(), registerInfoInDto.getUserPhone(), registerInfoInDto.getSex())) {
            return ResponseEntity.ok("");
        } else {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout!");
        }

    }
}
