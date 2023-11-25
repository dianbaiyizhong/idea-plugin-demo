package com.zm.idea.plugin.core;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

public class MyStartupActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {

        System.out.println("________runActivity");
        // 启动的时候判断是否是自定义工程，如果是，那就选中toolWindows
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("myDemo");
        toolWindow.activate(null);
    }
}