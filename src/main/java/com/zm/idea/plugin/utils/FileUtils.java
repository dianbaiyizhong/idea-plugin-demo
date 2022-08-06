package com.zm.idea.plugin.utils;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.RefreshQueue;

import java.io.File;

public class FileUtils {
    public static void openFileEditorAndSaveState(File file, Project project, boolean isOpen) {
        ApplicationManager.getApplication().invokeLater(() -> {
            VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);
            if (isOpen) {
                OpenFileDescriptor descriptor = new OpenFileDescriptor(project, vf);
                FileEditorManager.getInstance(project).openTextEditor(descriptor, false);
            }
        });
    }
}
