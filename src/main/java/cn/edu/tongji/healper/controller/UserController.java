package cn.edu.tongji.healper.controller;

import cn.edu.tongji.healper.entity.User;
import cn.edu.tongji.healper.indto.LoginInfoInDto;
import cn.edu.tongji.healper.indto.RegisterInfoInDto;
import cn.edu.tongji.healper.entity.ClientEntity;
import cn.edu.tongji.healper.entity.ConsultantEntity;

import cn.edu.tongji.healper.indto.UpdatePasswdInDto;
import cn.edu.tongji.healper.indto.UploadImageInDto;
import cn.edu.tongji.healper.outdto.ConsultantInfoWithClient;
import cn.edu.tongji.healper.outdto.LoginInfoOutDto;
import cn.edu.tongji.healper.outdto.UserType;

import cn.edu.tongji.healper.po.ClientInfo;
import cn.edu.tongji.healper.po.ConsultantInfo;
import cn.edu.tongji.healper.service.UserService;
import cn.edu.tongji.healper.util.OSSUtils;
import cn.edu.tongji.healper.util.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static cn.edu.tongji.healper.util.MD5Utils.stringToMD5;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录
    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody LoginInfoInDto loginInfoInDto) {
        User user = userService.findUserByPhone(loginInfoInDto.getUserPhone());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User doesn't exist!");
        } else {
            if (user.getPassword().equals(stringToMD5(loginInfoInDto.getUserPassword()))) {
                LoginInfoOutDto outDto = new LoginInfoOutDto();
                outDto.setUser(user);
                if (user.getClass() == ClientEntity.class) {
                    outDto.setUserType(UserType.client);
                } else if (user.getClass() == ConsultantEntity.class) {
                    outDto.setUserType(UserType.consultant);
                }
                return ResponseEntity.ok(outDto);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password error!");
            }
        }
    }

    @GetMapping(value = "info")
    public ResponseEntity getInfoByUserId(@RequestParam Integer id, @RequestParam UserType userType) {
        if (userType == UserType.client) {
            ClientInfo clientInfo = userService.findClientInfoById(id);
            if (clientInfo != null) {
                return ResponseEntity.ok(clientInfo);
            }
        } else if (userType == UserType.consultant) {
            ConsultantInfo consultantInfo = userService.findConsultantInfoById(id);
            if (consultantInfo != null) {
                return ResponseEntity.ok(consultantInfo);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Person does not exist");
    }


    //注册功能，判断手机号是否重复，并将密码md5加密后存储用户信息
    @PostMapping(value = "register")
    public ResponseEntity register(@RequestBody RegisterInfoInDto registerInfoInDto) {
        User user = userService.findUserByPhone(registerInfoInDto.getUserPhone());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Phone already exists!");
        } else {
            ClientEntity newClient = userService.addClientInfo(
                    registerInfoInDto.getNickname(),
                    registerInfoInDto.getPassword(),
                    registerInfoInDto.getUserPhone(),
                    registerInfoInDto.getSex()
            );
            if (newClient != null) {
                return ResponseEntity.ok(newClient.getId());
            } else {
                return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout!");
            }
        }
    }

    //修改用户信息
    @PutMapping(value = "info")
    public ResponseEntity updateClientBasicInfo(@RequestBody ClientInfo client) {
        try {
            userService.updateClientInfo(client);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("e");
        }
    }

    @PutMapping(value = "passwd")
    public ResponseEntity updateClientPasswd(@RequestBody UpdatePasswdInDto inDto) {
        if (userService.checkPasswdWithId(inDto.getId(), inDto.getOldPasswd())) {
            if (userService.updateClientPasswd(inDto.getId(), inDto.getNewPasswd())) {
                return ResponseEntity.ok("Password updated!");
            } else {
                return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Failed to update!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Old password wrong!");
        }
    }

    // 发送手机短信验证码
    @PostMapping("sendMsg")
    public ResponseEntity sendMsg(@RequestBody LoginInfoInDto loginInfoInDto, HttpSession session) {
        //获取手机号
        String phone = loginInfoInDto.getUserPhone();
        if (phone != null && phone.length() > 0) {
            //看个人需求自行编写，已生成随机的4位验证码为例
            String code = SMSUtils.getCode(4);
//            log.info("code={}", code);

            //调用阿里云提供的短信服务API完成发送短信
            SMSUtils.sendMessage("阿里云短信测试", "SMS_154950909", phone, code);

            //需要将生成的验证码保存到Session
            session.setAttribute(phone, code);
            return ResponseEntity.ok("短信发送成功");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("短信发送失败");
    }

    @PostMapping("uploadImage")
    public ResponseEntity uploadImage(@RequestBody UploadImageInDto inDto) {
        try {
            String imageBase64 = inDto.getBase64();

            String imageType = imageBase64
                    .split("/", 3)[1]
                    .split(";", 2)[0];
            String imageName = inDto.getUserType().toString()
                    + "-" + inDto.getId()
                    + "." + imageType;

            byte[] imageBytes = OSSUtils.base64ToBytes(imageBase64.split("base64,")[1]);
            InputStream inputStream = OSSUtils.bytesToInputStream(imageBytes);
            String url = OSSUtils.uploadStream(inputStream, imageName);

            ClientInfo clientInfo = userService.findClientInfoById(inDto.getId());
            clientInfo.setProfile(url);
            userService.updateClientInfo(clientInfo);

            return ResponseEntity.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES).body(e);
        }
    }

    @GetMapping("consultants/label")
    public ResponseEntity findConsultantsWithLabel(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String label
    ) {
        try {
            List<ConsultantInfo> consultants = userService.findConsultantsByLabel(label, page, size);
            return ResponseEntity.ok(consultants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No %s consultants found!".formatted(label));

        }
    }

    @GetMapping("consultants/client")
    public ResponseEntity findConsultantsWithClient(
            @RequestParam Integer clientId,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestBody Map<String, String> map
    ) {
        try {
            String label = map.get("label");
            List<ConsultantInfoWithClient> consultants = userService.findConsultantsWithClient(clientId, label, page, size);
            return ResponseEntity.ok(consultants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);

        }
    }

    @PutMapping("consultant/info")
    public ResponseEntity updateConsultantLabel(@RequestBody ConsultantInfo consultant) {
        try {
            // 需要修改一下来访者改信息的api
            userService.updateConsultantInfo(consultant);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("consultant/label")
    public ResponseEntity updateConsultantLabel(@RequestBody String label) {
        // 怎么修改咨询师标签？
        try {

            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }



}

