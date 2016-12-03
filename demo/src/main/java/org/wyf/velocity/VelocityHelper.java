package org.wyf.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by wyf on 16/10/16.
 */
public class VelocityHelper {
    /**
     * 根据给定的vm模板和上下文生成html页面
     * @param inputVmFilePath vm模板
     * @param outputHtmlFilePath 输出html页面
     * @param context 上下文
     * @throws Exception
     */
    public static void generateHtml(String inputVmFilePath, String outputHtmlFilePath,
                                    VelocityContext context) throws Exception {
        try {
            Velocity.init();
            VelocityEngine engine = new VelocityEngine();
            Template template = engine.getTemplate(inputVmFilePath, "utf8");
            File outputFile = new File(outputHtmlFilePath);
            FileWriter writer = new FileWriter(outputFile);
            template.merge(context, writer);
            writer.close();
        } catch (Exception ex) {
            throw ex;
        }
    }
}