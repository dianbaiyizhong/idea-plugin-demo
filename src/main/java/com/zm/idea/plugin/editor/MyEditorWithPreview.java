package com.zm.idea.plugin.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;
import com.intellij.openapi.util.Key;
import com.zm.idea.plugin.constant.PluginConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyEditorWithPreview extends TextEditorWithPreview {
    public static final Key<MyEditorWithPreview> PARENT_SPLIT_EDITOR_KEY = Key.create(PluginConstant.PLUGIN_ID + "Question Split");

    public MyEditorWithPreview(@NotNull TextEditor editor, @NotNull FileEditor preview) {
        super(editor, preview, "My Editor", Layout.SHOW_EDITOR_AND_PREVIEW);
        editor.putUserData(PARENT_SPLIT_EDITOR_KEY, this);
        preview.putUserData(PARENT_SPLIT_EDITOR_KEY, this);
    }

    @Nullable
    protected ActionGroup createLeftToolbarActionGroup() {
        return null;
    }

    @Nullable
    protected ActionGroup createRightToolbarActionGroup() {
        // 为左边的java编辑器视图增加按钮
        return (ActionGroup) ActionManager.getInstance().getAction(PluginConstant.DEMO_EDITOR_GROUP);
    }

    @NotNull
    protected ActionGroup createViewActionGroup() {
        return new DefaultActionGroup(
                getShowEditorAndPreviewAction(),
                getShowPreviewAction()
        );
    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return getTextEditor().getBackgroundHighlighter();
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return getTextEditor().getCurrentLocation();
    }

    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder() {
        return getTextEditor().getStructureViewBuilder();
    }


    @NotNull
    public TextEditor getTextEditor() {
        if (((TextEditor) myPreview).getEditor() == null) {
            return myEditor;
        }
        return (TextEditor) myPreview;
    }

    public FileEditor getPreviewEditor() {
        return myPreview == getTextEditor() ? myEditor : myPreview;
    }
}
