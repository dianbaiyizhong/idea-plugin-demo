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

public class QuestionEditorWithPreview extends TextEditorWithPreview {
    public static final Key<QuestionEditorWithPreview> PARENT_SPLIT_EDITOR_KEY = Key.create(PluginConstant.PLUGIN_ID + "Question Split");

    public QuestionEditorWithPreview(@NotNull TextEditor editor, @NotNull FileEditor preview) {
        super(editor, preview, "Question Editor", Layout.SHOW_EDITOR_AND_PREVIEW);
        editor.putUserData(PARENT_SPLIT_EDITOR_KEY, this);
        preview.putUserData(PARENT_SPLIT_EDITOR_KEY, this);
    }

    @Nullable
    protected ActionGroup createLeftToolbarActionGroup() {
        if (false) {
            return (ActionGroup) ActionManager.getInstance().getAction(PluginConstant.LEETCODE_EDITOR_GROUP);
        } else {
            return null;
        }

    }

    @Nullable
    protected ActionGroup createRightToolbarActionGroup() {
        if (true) {
            return (ActionGroup) ActionManager.getInstance().getAction(PluginConstant.LEETCODE_EDITOR_GROUP);
        } else {
            return null;
        }
    }

    @NotNull
    protected ActionGroup createViewActionGroup() {
        if (true) {
            return new DefaultActionGroup(
                    getShowEditorAndPreviewAction(),
                    getShowPreviewAction()
            );
        } else {
            return new DefaultActionGroup(
                    getShowEditorAction(),
                    getShowEditorAndPreviewAction()
            );
        }


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
        if (true) {
            if (((TextEditor) myPreview).getEditor() == null) {
                return myEditor;
            }
            return (TextEditor) myPreview;
        } else {
            if ((myEditor).getEditor() == null) {
                return (TextEditor) myPreview;
            }
            return myEditor;
        }

    }

    public FileEditor getPreviewEditor() {
        return myPreview == getTextEditor() ? myEditor : myPreview;
    }
}
