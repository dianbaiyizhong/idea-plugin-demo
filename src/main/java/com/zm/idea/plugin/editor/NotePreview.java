package com.zm.idea.plugin.editor;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.fileEditor.ex.FileEditorProviderManager;
import com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.RefreshQueue;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.components.BorderLayoutPanel;
import com.zm.idea.plugin.constant.PluginConstant;
import com.zm.idea.plugin.entity.CodeEntity;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * @author shuzijun
 */
public class NotePreview extends UserDataHolderBase implements FileEditor {


    private final Project project;
    private final CodeEntity codeEntity;


    private BorderLayoutPanel myComponent;
    private FileEditor fileEditor;

    private boolean isLoad = false;

    public NotePreview(Project project, CodeEntity codeEntity) {
        this.project = project;
        this.codeEntity = codeEntity;
    }

    @Override
    public @NotNull JComponent getComponent() {
        if (myComponent == null) {
            myComponent = JBUI.Panels.simplePanel();
            if (isLoad) {
                initComponent();
            }
        }
        return myComponent;
    }


    private void initComponent() {
        isLoad = true;
        NotePreview notePreview = this;
        ApplicationManager.getApplication().invokeLater(() -> {
            JBLabel loadingLabel = new JBLabel("Loading......");
            myComponent.addToCenter(loadingLabel);
            try {
                File file = ApplicationManager.getApplication().executeOnPooledThread(() -> {
                    File nodeFile = new File("D:\\project\\doc\\empty.md");
                    return nodeFile;
                }).get();
                if (file == null || !file.exists()) {
                    myComponent.addToCenter(new JBLabel("No note"));
                } else {
                    VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file);
                    FileEditorProvider[] editorProviders = FileEditorProviderManager.getInstance().getProviders(project, vf);

                    if (editorProviders != null && editorProviders.length > 0) {
                        fileEditor = editorProviders[0].createEditor(project, vf);
                        Disposer.register(notePreview, fileEditor);
                    } else {
                        fileEditor = new PsiAwareTextEditorProvider().createEditor(project, vf);
                        Disposer.register(notePreview, fileEditor);
                    }
                    myComponent.addToCenter(fileEditor.getComponent());
                    myComponent.addToTop(createToolbarWrapper(fileEditor.getComponent()));

                }
            } catch (Exception e) {
                myComponent.addToCenter(new JBLabel(e.getMessage()));
            } finally {
                myComponent.remove(loadingLabel);
            }
        });
    }


    private SplitEditorToolbar createToolbarWrapper(JComponent targetComponentForActions) {
        DefaultActionGroup actionGroup = (DefaultActionGroup) ActionManager.getInstance().getAction(PluginConstant.DEMO_EDITOR_NOTE);
        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar("Note" + ActionPlaces.TOOLBAR, actionGroup, true);
        actionToolbar.setTargetComponent(targetComponentForActions);
        SplitEditorToolbar splitEditorToolbar = new SplitEditorToolbar(null, actionToolbar);
        splitEditorToolbar.add(new JBLabel("网站已更换新笔记功能,此功能后续同步官网,请备份此版本下的笔记."), 0);

        return splitEditorToolbar;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return null;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) @NotNull String getName() {
        return PluginConstant.DEMO_EDITOR_TAB_VIEW + " Note";
    }

    @Override
    public void setState(@NotNull FileEditorState state) {
        if (state instanceof ConvergePreview.TabFileEditorState) {
            if (!isLoad && ((ConvergePreview.TabFileEditorState) state).isLoad()) {
                initComponent();
            } else {
                if (fileEditor != null) {
                    RefreshQueue.getInstance().refresh(false, false, null, fileEditor.getFile());
                }
            }
        } else {
            if (isLoad) {
                ApplicationManager.getApplication().invokeLater(() -> {
                    myComponent.removeAll();
                    myComponent.updateUI();
                    initComponent();
                });

            }
        }
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public @Nullable FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Override
    public void dispose() {
        if (fileEditor != null) {
            Disposer.dispose(fileEditor);
        }
    }

    @Override
    public @Nullable VirtualFile getFile() {
        if (fileEditor != null) {
            return fileEditor.getFile();
        } else {
            return null;
        }
    }
}
