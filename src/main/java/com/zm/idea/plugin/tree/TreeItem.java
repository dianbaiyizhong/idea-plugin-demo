package com.zm.idea.plugin.tree;

import com.intellij.openapi.util.IconLoader;
import icons.DemoIcons;

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
//        setLayout(new BorderLayout());
        setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
//        GridLayout gl = new GridLayout(1, 2);//设计几行几列
//        setLayout(gl);
//        add(check, BorderLayout.WEST);
//        add(label2, BorderLayout.WEST);
//        add(label, BorderLayout.CENTER);
        add(label2);
        label2.setIcon(IconLoader.getIcon("/icons/note.svg"));
        add(label);


    }

}
