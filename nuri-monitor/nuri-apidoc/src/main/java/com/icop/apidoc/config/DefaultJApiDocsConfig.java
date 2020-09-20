package com.icop.apidoc.config;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: liukj
 * @date: 2020/7/2
 * @description：
 */

public class DefaultJApiDocsConfig {

    private final static String projectName="NURI-APIDOC";
    private final static String version="20200702.0";
    private final static String projectPath="D:\\Document\\WorkSpace\\Idea\\personal\\clound\\nuri\\nuri-monitor\\nuri-apidoc\\src\\main\\resources\\static";

    public static void main(String[] args) {
        //System.out.println(projectName+version+projectPath);
        DocsConfig config = new DocsConfig();
        config.setProjectPath(obtainProjectPath());
        config.setProjectName(projectName);
        config.setApiVersion(version);
        config.setDocsPath(projectPath);
        config.setAutoGenerate(Boolean.TRUE);
        Docs.buildHtmlDocs(config);
    }

    public static String obtainProjectPath(){
        String path = System.getProperty("user.dir");
        System.out.println(path);
        return path;
    }

}
