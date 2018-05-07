package org.wyf;

import org.junit.Test;
import org.wyf.spider.TestDynamicIp;
import org.wyf.spider.WebClientCrawler;

/**
 * Created by wangyaofu on 2018/5/7.
 */
public class WebClientCrawlerTest {
    public static boolean isRunning = true;

    @Test
    public void test() {
        long fetchIpSeconds = 5;
        int threadNum = 1;
        int testTime = 3;
        // 是否加载JS，加载JS会导致速度变慢
        boolean useJS = false;
        // 请求超时时间，单位毫秒，默认5秒
        int timeOut = 5000;
        String targetUrl = "https://www.aliyun.com/";

        for (int i = 0; i < threadNum; i++) {
            WebClientCrawler webClientCrawler = new WebClientCrawler(10, targetUrl, useJS, timeOut);
            webClientCrawler.start();
        }
        while(isRunning){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
