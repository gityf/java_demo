package org.wyf.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class WebMagicPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setRetrySleepTime(10).setTimeOut(100000).addHeader("User-Agent",  "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36");

    @Override
    public void process(Page page) {
        List<String> urlList = page.getHtml().$("img").$("img","src").all();
        page.addTargetRequests(urlList);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WebMagicPageProcessor()).addUrl("https://www.mi.com").thread(50).run();
    }
}