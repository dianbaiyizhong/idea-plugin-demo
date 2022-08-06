package com.zm.idea.plugin.tree;

import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class MyTreeRenderer2 extends TreeItem implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        return this;
    }
}
