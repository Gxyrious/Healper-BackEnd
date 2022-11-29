package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.indto.LoginInfoInDto;
import cn.edu.tongji.healper.indto.RegisterInfoInDto;
import cn.edu.tongji.healper.model.ClientEntity;
import cn.edu.tongji.healper.model.ConsultantEntity;

import cn.edu.tongji.healper.outdto.LoginInfoOutDto;
import cn.edu.tongji.healper.outdto.UserType;
import cn.edu.tongji.healper.service.ConsultService;

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

    // 登录
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginInfoInDto loginInfoInDto) {
        ClientEntity client = userService.findClientEntityByUserPhone(loginInfoInDto.getUserPhone());
        if (client == null) {
            ConsultantEntity consultant = userService.findConsultantEntityByUserPhone(loginInfoInDto.getUserPhone());
            if (consultant != null) {
                LoginInfoOutDto loginInfoOutDto = new LoginInfoOutDto();
                loginInfoOutDto.setUser(consultant);
                loginInfoOutDto.setUserType(UserType.consultant);
                return ResponseEntity.ok(loginInfoOutDto);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User doesn't exist!");
        } else if (client.getPassword().equals(loginInfoInDto.getUserPassword())) {
            LoginInfoOutDto loginInfoOutDto = new LoginInfoOutDto();
            loginInfoOutDto.setUser(client);
            loginInfoOutDto.setUserType(UserType.client);
            return ResponseEntity.ok(loginInfoOutDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password error!");
        }
    }

    // 根据手机号查找个人信息
    @GetMapping(value = "/info")
    public ResponseEntity getInfoByUserPhone(@RequestParam String userphone) {
        ClientEntity client = userService.findClientEntityByUserPhone(userphone);
        if (client != null) { //先查询是否为来访者
            return ResponseEntity.ok(client);
        } else { //再查询是否为咨询师
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

    //修改用户信息
    @PostMapping(value = "/change")
    public ResponseEntity change(@RequestBody ClientEntity client) {
        userService.updateClientInfo(client);
        return ResponseEntity.ok("change success!");
    }
}
