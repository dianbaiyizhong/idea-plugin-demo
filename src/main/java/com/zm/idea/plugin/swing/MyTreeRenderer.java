package com.zm.idea.plugin.swing;


import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class MyTreeRenderer extends TreeItem implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        return this;
    }
}
