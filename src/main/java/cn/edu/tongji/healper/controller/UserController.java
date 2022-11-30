package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.indto.LoginInfoInDto;
import cn.edu.tongji.healper.indto.RegisterInfoInDto;
import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;

import cn.edu.tongji.healper.indto.UserInDto;
import cn.edu.tongji.healper.outdto.LoginInfoOutDto;
import cn.edu.tongji.healper.outdto.UserType;

import cn.edu.tongji.healper.service.UserService;
import cn.edu.tongji.healper.util.SMSUtils;
import cn.edu.tongji.healper.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static cn.edu.tongji.healper.util.MD5Utils.stringToMD5;

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
        } else if (client.getPassword().equals(stringToMD5(loginInfoInDto.getUserPassword()))) {
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

    //注册功能，判断手机号是否重复，并将密码md5加密后存储用户信息
    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterInfoInDto registerInfoInDto) {
        if (userService.findConsultantEntityByUserPhone(registerInfoInDto.getUserPhone()) == null) {//先判断手机号是否已经存在
            if (userService.addClientInfo(registerInfoInDto.getNickname(), registerInfoInDto.getPassword(), registerInfoInDto.getUserPhone(), registerInfoInDto.getSex())) {
                return ResponseEntity.ok("ok");
            } else {
                return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout!");
            }

        } else {
            System.out.println("手机号重复");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Phone already exists!");
        }
    }

    //修改用户信息
    @PostMapping(value = "/info")
    public ResponseEntity change(@RequestBody ClientEntity client) {
        userService.updateClientInfo(client);
        return ResponseEntity.ok("change success!");
    }

    // 发送手机短信验证码
    @PostMapping("/sendMsg")
    public ResponseEntity sendMsg(@RequestBody LoginInfoInDto loginInfoInDto, HttpSession session) {
        //获取手机号
        String phone = loginInfoInDto.getUserPhone();
        if (phone != null && phone.length() > 0) {
            //看个人需求自行编写，已生成随机的4位验证码为例
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
//            log.info("code={}", code);

            //调用阿里云提供的短信服务API完成发送短信
            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);

            //需要将生成的验证码保存到Session
            session.setAttribute(phone, code);
            return ResponseEntity.ok("短信发送成功");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("短信发送失败");
    }

}

