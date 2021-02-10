package cn.c1w.ratelimiter.web.controller;

import cn.c1w.ratelimiter.annotation.RateLimit;
import cn.c1w.ratelimiter.entity.TPSDemo;
import cn.c1w.ratelimiter.service.TPSDemoDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("ratelimiter")
public class RateLimiterController {

    @Autowired
    private TPSDemoDaoService service;

    @RateLimit(limitNum = 10.0)
    @GetMapping("/getResult")
    public String getResult() {
        return "success";
    }

    //    @RateLimit(limitNum = 10.0)
    @RateLimit(limitNum = 10.0)
    @PostMapping("/save")
    public void save() {
        TPSDemo demo = new TPSDemo();
        demo.setCurrentDataTime(LocalDateTime.now());
        service.save(demo);
    }

    @GetMapping("/getByOrderByCurrentDataTime")
    public List<TPSDemo> getByOrderByCurrentDataTime() {
        return service.getByOrderByCurrentDataTime();
    }
}
