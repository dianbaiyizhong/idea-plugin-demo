package com.zm.idea.plugin.listener;

import cn.hutool.core.util.RandomUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleRootModificationUtil;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.zm.idea.plugin.constant.PluginConstant;
import com.zm.idea.plugin.utils.FileUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class TreeMouseListener extends MouseAdapter {

    private JTree tree;
    private Project project;

    public TreeMouseListener(JTree tree, Project project) {
        this.tree = tree;
        this.project = project;

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int selRow = tree.getRowForLocation(e.getX(), e.getY());
        System.out.println(selRow);


        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
            File file = new File(System.getProperty("java.io.tmpdir") + File.separator + PluginConstant.PLUGIN_ID + File.separator + RandomUtil.randomInt(1, 10) +
                    ".java");
            FileUtils.saveFile(file, "public");
            FileUtils.openFileEditorAndSaveState(file, project, true);


            LibraryTable.ModifiableModel model = LibraryTablesRegistrar.getInstance().getLibraryTable(project).getModifiableModel();

            Library library = model.createLibrary("hutool");
            Library.ModifiableModel libraryModel = library.getModifiableModel();
            String[] classRootUrls = libraryModel.getUrls(OrderRootType.CLASSES);
            for (String classRootURL : classRootUrls) {
                libraryModel.removeRoot(classRootURL, OrderRootType.CLASSES);
            }

            // 加入新的jar
            libraryModel.addRoot(VirtualFileManager.constructUrl("jar", "D:\\hutool-all-5.8.5.jar" + "!/"), OrderRootType.CLASSES);


            // 提交庫變更
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    libraryModel.commit();
                    model.commit();
                }
            });

            Module[] modules = ModuleManager.getInstance(project).getModules();
            for (Module module : modules) {
                if (ModuleRootManager.getInstance(module).getModifiableModel().findLibraryOrderEntry(library) == null) {
                    ModuleRootModificationUtil.addDependency(module, library);
                }
            }


        }

    }


}