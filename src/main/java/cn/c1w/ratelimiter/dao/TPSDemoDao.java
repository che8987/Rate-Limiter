package cn.c1w.ratelimiter.dao;

import cn.c1w.ratelimiter.entity.TPSDemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TPSDemoDao extends JpaRepository<TPSDemo, Integer> {

    List<TPSDemo> getByOrderByCurrentDataTime();
}
