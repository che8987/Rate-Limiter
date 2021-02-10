package cn.c1w.ratelimiter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RateLimiterAppTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() throws Exception {
        int threadSize = 20;
        CountDownLatch downLatch = new CountDownLatch(20);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);
        for (int i = 0; i < threadSize; i++) {
            fixedThreadPool.submit(() -> {
                restTemplate.postForObject("http://127.0.0.1:8181/ratelimiter/save/", null, String.class);
                downLatch.countDown();
            });
        }
        downLatch.await();
        fixedThreadPool.shutdown();
        List<LinkedHashMap<Integer, String>> results = restTemplate.getForObject(
                "http://127.0.0.1:8181/ratelimiter/getByOrderByCurrentDataTime/", List.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        results.forEach(result -> {
            System.out.println(String.valueOf(result.get("id")));
            try {
                System.out.println(df.parse(result.get("currentDataTime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}
