package com.zm.idea.plugin.api;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;

import java.util.concurrent.ExecutionException;

public class DemoApi {


    public static String getDemoTreeData() {
        try {
            return ApplicationManager.getApplication().executeOnPooledThread(() -> {
                HttpRequest httpRequest = HttpRequest.of("https://www.baidu.com/").method(Method.GET);
                String body = httpRequest.execute().body();
                return body;
            }).get();
        } catch (Exception e) {
            return null;
        }


    }


}
