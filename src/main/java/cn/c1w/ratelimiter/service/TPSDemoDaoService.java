package cn.c1w.ratelimiter.service;

import cn.c1w.ratelimiter.entity.TPSDemo;

import java.util.List;

public interface TPSDemoDaoService {

    List<TPSDemo> getByOrderByCurrentDataTime();
    void save(TPSDemo tpsDemo);
}
