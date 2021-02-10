package cn.c1w.ratelimiter.service.impl;

import cn.c1w.ratelimiter.dao.TPSDemoDao;
import cn.c1w.ratelimiter.entity.TPSDemo;
import cn.c1w.ratelimiter.service.TPSDemoDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPSDemoDaoServiceImpl implements TPSDemoDaoService {

    @Autowired
    private TPSDemoDao dao;

    @Override
    public List<TPSDemo> getByOrderByCurrentDataTime() {
        return dao.getByOrderByCurrentDataTime();
    }

    @Override
    public void save(TPSDemo tpsDemo) {
        dao.save(tpsDemo);
    }
}
