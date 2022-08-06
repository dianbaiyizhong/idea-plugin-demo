package com.zm.idea.plugin.editor;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class MyEditorEditor extends SplitTextEditorProvider {
    public MyEditorEditor() {
        super(new PsiAwareTextEditorProvider(), new ConvergeProvider(new FileEditorProvider[]{new NoteProvider()}, new String[]{"wiki"}));
    }


    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        if (file.getPath().endsWith("txt")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Builder createEditorAsync(@NotNull Project project, @NotNull VirtualFile file) {

        final Builder firstBuilder = getBuilderFromEditorProvider(this.myFirstProvider, project, file);
        final Builder secondBuilder = getBuilderFromEditorProvider(this.mySecondProvider, project, file);
        return new Builder() {
            public FileEditor build() {
                return createSplitEditor(firstBuilder.build(), secondBuilder.build());
            }
        };
    }

    @Override
    protected FileEditor createSplitEditor(@NotNull FileEditor firstEditor, @NotNull FileEditor secondEditor) {
        return new QuestionEditorWithPreview((TextEditor) secondEditor, firstEditor);
    }
}
