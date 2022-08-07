package com.zm.idea.plugin.swing;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TreeItem extends JPanel {

    public final JLabel label = new JLabel("这是描述");

    public final JLabel label2 = new JLabel("SdkUtils");

    public final JCheckBox check = new JCheckBox();

    public TreeItem() {
        this.check.setMargin(new Insets(0, 0, 0, 0));
        Border margin = new EmptyBorder(0, 10, 0, 0);
        label.setBorder(margin);
        add(label2);
        label2.setIcon(new ImageIcon("D:\\project\\idea-plugin-demo\\src\\main\\resources\\icons\\note.svg"));
        add(label);

    }

}
