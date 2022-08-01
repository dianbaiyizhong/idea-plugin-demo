package com.zm.idea.plugin.scala.window;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.jcef.JBCefBrowser;

import org.CustomSchemeHandlerFactory;
import org.cef.CefApp;
import org.jetbrains.annotations.NotNull;

public class WindowFactory implements ToolWindowFactory, DumbAware {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();



        CefApp.getInstance().registerSchemeHandlerFactory("http", "myapp", new CustomSchemeHandlerFactory());
        JBCefBrowser jbCefBrowser = new JBCefBrowser();
        Content content = contentFactory.createContent(jbCefBrowser.getComponent(), "", false);
        toolWindow.getContentManager().addContent(content);

        jbCefBrowser.loadURL("http://myapp/index.html");


    }
}
