package com.zm.idea.plugin.window;

import com.intellij.codeInsight.navigation.BackgroundUpdaterTask;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.AnimatedIcon;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.zm.idea.plugin.api.DemoApi;
import com.zm.idea.plugin.constant.PluginConstant;
import com.zm.idea.plugin.tree.MyTreeRenderer;
import com.zm.idea.plugin.tree.MyTreeRenderer2;
import com.zm.idea.plugin.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.concurrent.ExecutionException;

@Slf4j
public class WindowFactory implements ToolWindowFactory, DumbAware {
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
    private DefaultMutableTreeNode t = new DefaultMutableTreeNode("教师");
    private DefaultMutableTreeNode s = new DefaultMutableTreeNode("学生");

    private DefaultMutableTreeNode t1 = new DefaultMutableTreeNode("孔子");
    private DefaultMutableTreeNode t2 = new DefaultMutableTreeNode("孟子");
    private DefaultMutableTreeNode s1 = new DefaultMutableTreeNode("子路");
    private DefaultMutableTreeNode s2 = new DefaultMutableTreeNode("子游");
    private DefaultMutableTreeNode s3 = new DefaultMutableTreeNode("子渊");


    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//        JBCefBrowser jbCefBrowser = new JBCefBrowser();
//        Content content = contentFactory.createContent(jbCefBrowser.getComponent(), "", false);
//        toolWindow.getContentManager().addContent(content);
//        CefApp.getInstance().registerSchemeHandlerFactory("http", "myapp", new CustomSchemeHandlerFactory());
//
//        jbCefBrowser.loadURL("http://myapp/index.html");


        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Tree tree = new Tree(root);
        tree.setCellRenderer(new MyTreeRenderer2());
        root.add(s);
        root.add(t);
        s.add(s1);
        s.add(s2);
        s.add(s3);
        t.add(t1);
        t.add(t2);


        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                ProgressManager.getInstance().run(new Task.Backgroundable(project, PluginConstant.LEETCODE_EDITOR_OPEN_CODE, false) {
                    @Override
                    public void run(@NotNull ProgressIndicator progressIndicator) {

                        FileUtils.openFileEditorAndSaveState(new File("D:\\project\\idea-plugin-demo\\src\\main\\java\\com\\zm\\idea\\plugin\\api\\DemoApi.java"), project, true);
                    }
                });

            }
        });
        JLabel loadingLabel = new JLabel("Loading...", new AnimatedIcon.Default(), SwingConstants.CENTER);
        Content loadingContent = contentFactory.createContent(loadingLabel, "", false);
        toolWindow.getContentManager().addContent(loadingContent);

        Content treeContent = contentFactory.createContent(tree, "", false);

        ProgressManager.getInstance().run(new Task.Backgroundable(project, "getDemoTreeData") {

            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                progressIndicator.setIndeterminate(true);
                String body = DemoApi.getDemoTreeData();
                ApplicationManager.getApplication().invokeLater(() -> {
                    toolWindow.getContentManager().removeAllContents(false);
                    toolWindow.getContentManager().addContent(treeContent);
                });

            }
        });


    }


}
