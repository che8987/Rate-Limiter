package cn.c1w.ratelimiter.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TPSDemo")
public class TPSDemo {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime currentDataTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCurrentDataTime() {
        return currentDataTime;
    }

    public void setCurrentDataTime(LocalDateTime currentDataTime) {
        this.currentDataTime = currentDataTime;
    }
}
