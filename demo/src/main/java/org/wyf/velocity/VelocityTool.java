package org.wyf.velocity;

import org.apache.velocity.VelocityContext;

import java.util.HashMap;
import java.util.Vector;

public class VelocityTool {
    public static void main(String[] args) {
        try {
            VelocityContext context= new VelocityContext();
            context.put("name", "Kim Jung-un");
            context.put("gender", "Male");
            context.put("email", "XXX@XXX.gov");
            context.put("job", "Chairman of the WPK");
            context.put("company", "Workers' Party Of Korea (WPK)");
            context.put("address", "XXXXXXXXX, Pyongyang, North Korea");
            context.put("portraitPath", "kju.jpg");
            HashMap<String, String> hashMapContact = new HashMap<String, String>();
            hashMapContact.put("Tel", "XXX-XXXXXXXX");
            hashMapContact.put("Fax", "XXX-XXXXXXXX");
            hashMapContact.put("Mobile", "XXX-XXXX-XXXX");
            context.put("contactItems", hashMapContact);
            Vector<String> vectorRemark = new Vector<String>();
            vectorRemark.add("Kim Jong-un is the Chairman of the Workers' Party of Korea and supreme leader of the Democratic People's Republic of Korea (DPRK), commonly referred to as North Korea.");
            vectorRemark.add("Kim is the son of Kim Jong-il (1941–2011) and the grandson of Kim Il-sung (1912–1994).");
            vectorRemark.add("Kim obtained two degrees, one in Physics at Kim Il-sung University, and another as an Army officer at the Kim Il-sung Military University.");
            vectorRemark.add("Kim was named the World's 46th Most Powerful Person by the Forbes list of The World's Most Powerful People in 2013");
            context.put("remarks", vectorRemark);
            VelocityHelper.generateHtml(
                    "web_vm/velocity_test.vm",
                    "output.html",
                    context);
            System.out.println("生成完毕");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
