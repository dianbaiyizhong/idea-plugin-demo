package com.zm.idea.plugin.editor;

import com.intellij.openapi.fileEditor.AsyncFileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.zm.idea.plugin.constant.PluginConstant;
import com.zm.idea.plugin.entity.CodeEntity;
import org.jetbrains.annotations.NotNull;

/**
 * @author shuzijun
 */
public class NoteProvider implements AsyncFileEditorProvider, DumbAware {

    private FileEditor fileEditor;

    @Override
    public @NotNull Builder createEditorAsync(@NotNull Project project, @NotNull VirtualFile file) {


        CodeEntity codeEntity = new CodeEntity();

        return new Builder() {
            @Override
            public FileEditor build() {
                return createSplitEditor(codeEntity, project);
            }
        };
    }

    protected FileEditor createSplitEditor(@NotNull CodeEntity codeEntity, Project project) {
        return new NotePreview(project, codeEntity);
    }

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        if (file.getPath().endsWith("md")) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        return createEditorAsync(project, file).build();
    }

    @Override
    public @NotNull String getEditorTypeId() {
        return PluginConstant.DEMO_EDITOR_TAB_VIEW + " Note view";
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }


}
