package org.wyf.spider;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.lang3.StringUtils;

public class TestDynamicIp {
    public static List<String> ipList = new ArrayList<String>();
    private HttpServiceImpl httpServiceImpl = new HttpServiceImpl();
	public static boolean gameOver = false;
	public static void main(String[] args) {
		long fetchIpSeconds = 5;
		int threadNum = 1;
		int testTime = 3;
		// 请填写全网代理IP订单号，填写之后才可以提取到IP哦
		String order = "一定要把这里改为单号哦~";
		// 你要抓去的目标网址
		String targetUrl = "http://cu.ctags.cn/v1.0/pages?orderid=AO20171229436729&pid=18512&aid=0&os=0";
		// 是否加载JS，加载JS会导致速度变慢
		boolean useJS = false;
		// 请求超时时间，单位毫秒，默认5秒
		int timeOut = 5000;

		if (order == null || "".equals(order)) {
			System.err.println("请输入全网代理IP动态代理订单号");
			return;
		}

		System.out.println(">>>>>>>>>>>>>>全网代理动态IP测试开始<<<<<<<<<<<<<<");
		System.out.println("***************");
		System.out.println("接口返回IP为国内各地区，每次最多返回10个");
		System.out.println("提取IP间隔 " + fetchIpSeconds + " 秒 ");
		System.out.println("开启爬虫线程 " + threadNum);
		System.out.println("爬虫目标网址  " + targetUrl);
		System.out.println("测试次数 3 ");
		System.out.println("***************\n");
		TestDynamicIp tester = new TestDynamicIp();
		//new Thread(tester.new GetIP(fetchIpSeconds * 1000, testTime, order)).start();
		for (int i = 0; i < threadNum; i++) {
			tester.new Crawler(10, targetUrl, useJS, timeOut).start();
		}
		while(!gameOver){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(">>>>>>>>>>>>>>全网代理动态IP测试结束<<<<<<<<<<<<<<");
		System.exit(0);
	}

	// 抓取IP138，检测IP
	public class Crawler extends Thread {
		@Override
		public void run() {
			while(!gameOver){
				List<String> imgUrlList = webParseHtml(targetUrl);
				if (imgUrlList != null) {
					for (String imgUrl : imgUrlList) {
						httpServiceImpl.httpGet(imgUrl, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36");

					}
				}
				try {
					Thread.sleep(sleepMs);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		long sleepMs = 200;
		boolean useJs = false;
		String targetUrl = "";
		int timeOut = 5000;

		public Crawler(long sleepMs, String targetUrl, boolean useJs, int timeOut) {
			this.sleepMs = sleepMs;
			this.targetUrl = targetUrl;
			this.useJs = useJs;
			this.timeOut = timeOut;
		}

		final String DIV_ID = "body";
		final String TAG_IMG = "img";
		final String IMG_SRC = "src";

		public List<String> webParseHtml(String url) {
			String html = "";
			List<String> imgUrlList = new ArrayList<String>();
			BrowserVersion[] versions = {BrowserVersion.INTERNET_EXPLORER_11, BrowserVersion.CHROME, BrowserVersion.FIREFOX_38, BrowserVersion.INTERNET_EXPLORER_8};
			WebClient client = new WebClient(versions[(int)(versions.length * Math.random())]);
			try {
				client.getOptions().setThrowExceptionOnFailingStatusCode(false);
				client.getOptions().setJavaScriptEnabled(useJs);
				client.getOptions().setCssEnabled(false);
				client.getOptions().setThrowExceptionOnScriptError(false);
				client.getOptions().setTimeout(timeOut);
				client.getOptions().setAppletEnabled(true);
				client.getOptions().setGeolocationEnabled(true);
				client.getOptions().setRedirectEnabled(true);

				String ipport = getAProxy();
				if (ipport != null) {
					ProxyConfig proxyConfig = new ProxyConfig(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1]));
					client.getOptions().setProxyConfig(proxyConfig);
				}else {
					System.out.print(".");
					//return "";
				}

				HtmlPage page = client.getPage(url);

				if (page != null && page.isHtmlPage()) {

					//Thread.sleep(10);  // 等待页面加载完成。

					DomNodeList<DomElement> elementsByTagName = page.getElementsByTagName(TAG_IMG);


					for (DomElement hele : elementsByTagName) {

						String imgUrl = hele.getAttribute(IMG_SRC);

						if (StringUtils.isNotBlank(imgUrl)) {
							imgUrlList.add(imgUrl);
						}
					}

					return imgUrlList;
				}

				System.out.println(getName() + " 使用代理 " + ipport + "请求目标网址返回HTML：" + html);

			} catch (Exception e) {
				return webParseHtml(url);
			} finally {
				client.close();
			}
			return imgUrlList;
		}
	}

}