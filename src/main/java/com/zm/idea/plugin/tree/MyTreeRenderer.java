package com.zm.idea.plugin.tree;

import icons.DemoIcons;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MyTreeRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        Component ret = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        setIcon(DemoIcons.NOTE);


        return ret;
    }
}
