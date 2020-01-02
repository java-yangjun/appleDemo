package com.jung.channel.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/ac")
@Api("提供给中台接口")
@Slf4j
public class LoginPhoneController {

    @ApiOperation(value="中台调用此接口根据openId获取手机号", notes="")
    @PostMapping(value = "/getPhoneByOpenId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getLoginPhoneByOpenId() {
    }

}
