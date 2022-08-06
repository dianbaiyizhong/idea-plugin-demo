package com.zm.idea.plugin.window;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.AnimatedIcon;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import com.zm.idea.plugin.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class WindowFactory implements ToolWindowFactory, DumbAware {
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
    private DefaultMutableTreeNode t = new DefaultMutableTreeNode("教师");
    private DefaultMutableTreeNode s = new DefaultMutableTreeNode("学生");

    private DefaultMutableTreeNode t1 = new DefaultMutableTreeNode("孔子");
    private DefaultMutableTreeNode t2 = new DefaultMutableTreeNode("孟子");
    private DefaultMutableTreeNode s1 = new DefaultMutableTreeNode("子路");
    private DefaultMutableTreeNode s2 = new DefaultMutableTreeNode("子游");
    private DefaultMutableTreeNode s3 = new DefaultMutableTreeNode("子渊");
    public static final String ACTION_PREFIX = "leetcode";

    public static final String LEETCODE_EDITOR_OPEN_CODE = ACTION_PREFIX + ".editor.openCode";

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

                ProgressManager.getInstance().run(new Task.Backgroundable(project, LEETCODE_EDITOR_OPEN_CODE, false) {
                    @Override
                    public void run(@NotNull ProgressIndicator progressIndicator) {
                        openContent("sss", project, false);

                        FileUtils.openFileEditorAndSaveState(new File("D:\\project\\bigdata\\bigdata-service\\data\\test.txt"), project, true);
                    }
                });

            }
        });


        JLabel label = new JLabel("Loading...", new AnimatedIcon.Default(), SwingConstants.CENTER);


        Content content = contentFactory.createContent(tree, "", false);
        toolWindow.getContentManager().addContent(content);


    }

    public static void openContent(String titleSlug, Project project, boolean isOpen) {

        String filePath = "D:\\project\\doc\\http返回值规约.md";
        File file = new File(filePath);
        if (file.exists()) {
            FileUtils.openFileEditorAndSaveState(file, project, isOpen);
        } else {
            FileUtils.openFileEditorAndSaveState(file, project, isOpen);
        }
    }
}
