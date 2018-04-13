package org.wyf;
import org.junit.Before;
import org.junit.Test;
import org.wyf.elasticsearch.ISearch;
import org.wyf.elasticsearch.SearchImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by wyf on 16/9/25.
 */
public class ElasticsearchTest {
	ISearch search;

//	@Test
//	public void get() {
//		Map<String, Object> map = search.get("a", "elastic", "0");
//		Set<String> set = map.keySet();
//		for (Iterator<String> it = set.iterator(); it.hasNext();) {
//			String key = it.next();
//			System.out.println(key + ":" + map.get(key));
//		}
//	}
	 static int count = 0;
	 @Test
	 public void save() {
		 Map<String, Object> values = new HashMap<String, Object>();
		 values.put("username", "elastic");
		 values.put("password", "234");
		 while(true){
			 Map<String,Object> map = search.save("ab", "elastic", null, values);
			 System.out.println(search.searchES("ab", "elastic"));
			 try {
				
				Thread.sleep(1000);
				count ++;
				if(count % 30 == 0 ){
					System.out.println("==count："+count+"!");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
//		 Set<String> set = map.keySet();
//		 for (Iterator<String> it = set.iterator(); it.hasNext();) {
//		 String key = it.next();
//		 System.out.println(key + ":" + map.get(key));
//	 }
	 }

	 @Before
	 public void before() {
	 search = new SearchImpl();
	 }

//测试成功
//	public static void main(String[] args) {
//		ISearch search = new SearchImpl();
//		Map<String, Object> map = search.get("abtest", "ab", "0");
//		Set<String> set = map.keySet();
//		for (Iterator<String> it = set.iterator(); it.hasNext();) {
//			String key = it.next();
//			System.out.println(key + ":" + map.get(key));
//		}
//	}
}
