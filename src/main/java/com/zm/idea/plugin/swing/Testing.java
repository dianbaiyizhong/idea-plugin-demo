package com.zm.idea.plugin.swing;


import javax.swing.*;

public class Testing {
    public void buildGUI() {
        JTree tree = new JTree();

        tree.setCellRenderer(new MyTreeRenderer());

        JFrame f = new JFrame();
        f.getContentPane().add(new JScrollPane(tree));
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Testing().buildGUI();

    }
}
