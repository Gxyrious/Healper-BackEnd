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
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static cn.edu.tongji.healper.util.MD5Utils.stringToMD5;

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
            ConsultantEntity consultant = userService.findConsultantEntityByUserPhone(loginInfoInDto.getUserPhone());
            if (consultant != null) {
                LoginInfoOutDto loginInfoOutDto = new LoginInfoOutDto();
                loginInfoOutDto.setUser(consultant);
                loginInfoOutDto.setUserType(UserType.consultant);
                return ResponseEntity.ok(loginInfoOutDto);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User doesn't exist!");
        } else if (client.getPassword().equals(stringToMD5(loginInfoInDto.getUserPassword()))) {
            LoginInfoOutDto loginInfoOutDto = new LoginInfoOutDto();
            loginInfoOutDto.setUser(client);
            loginInfoOutDto.setUserType(UserType.client);
            return ResponseEntity.ok(loginInfoOutDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password error!");
        }
    }

    // 示例2，根据手机号查找个人信息
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

    //注册功能，判断手机号是否重复，并将密码md5加密后存储用户信息
    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterInfoInDto registerInfoInDto) {
        if (userService.findConsultantEntityByUserPhone(registerInfoInDto.getUserPhone()) == null) {//先判断手机号是否已经存在
            if (userService.addClientInfo(registerInfoInDto.getNickname(), registerInfoInDto.getPassword(), registerInfoInDto.getUserPhone(), registerInfoInDto.getSex())) {
                return ResponseEntity.ok("");
            } else {
                return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout!");
            }

        } else {
            System.out.println("手机号重复");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Phone already exist!");
        }

    }

}

