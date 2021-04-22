package com.nokia.modules.gwzx.controller;

import com.nokia.modules.gwzx.service.GwzxService;
import com.nokia.utils.RData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/gwzx")
public class GwzxController {

    @Autowired
    private GwzxService gwzxService;

    @GetMapping("/loss")
    public RData top1(@RequestParam Map<String, Object> params) {
        List list = gwzxService.getLoss();
        return RData.ok().put("page", list);
    }

    @GetMapping("/centre_down")
    public RData centre_down(@RequestParam Map<String, Object> params) {
        List list = gwzxService.getCentreDown();
        return RData.ok().put("page", list);
    }

    @GetMapping("/left_up")
    public RData left_up(@RequestParam Map<String, Object> params) {
        List list = gwzxService.getLeftUp();
        return RData.ok().put("page", list);
    }

    @GetMapping("/left_centre")
    public RData left_centre(@RequestParam Map<String, Object> params) {
        List list = gwzxService.getLeftCentre();
        return RData.ok().put("page", list);
    }

    @GetMapping("/left_down")
    public RData left_down(@RequestParam Map<String, Object> params) {
        List list = gwzxService.getLeftDown();
        return RData.ok().put("page", list);
    }

    @GetMapping("/righ_down")
    public RData righ_down(@RequestParam Map<String, Object> params) {
        List list = gwzxService.getRightDown();
        return RData.ok().put("page", list);
    }
}
