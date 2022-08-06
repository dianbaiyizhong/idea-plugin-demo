package com.zm.idea.plugin;

import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MyTree {

    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
    private DefaultMutableTreeNode t = new DefaultMutableTreeNode("1");
    private DefaultMutableTreeNode s = new DefaultMutableTreeNode("2");

    private DefaultMutableTreeNode t1 = new DefaultMutableTreeNode("3");
    private DefaultMutableTreeNode t2 = new DefaultMutableTreeNode("4");
    private DefaultMutableTreeNode s1 = new DefaultMutableTreeNode("5");
    private DefaultMutableTreeNode s2 = new DefaultMutableTreeNode("6");
    private DefaultMutableTreeNode s3 = new DefaultMutableTreeNode("7");
    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyTree window = new MyTree();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MyTree() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("我");
        frame.setBounds(100, 100, 543, 405);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 180, 358);
        panel.add(panel_1);
        panel_1.setLayout(null);
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(179, 0, 346, 358);
        panel.add(panel_2);
        panel_2.setLayout(new GridLayout(1, 0, 0, 0));

        JTextArea textArea = new JTextArea();
        panel_2.add(textArea);
        JTree tree = new JTree(root);
        root.add(s);
        root.add(t);
        s.add(s1);
        s.add(s2);
        s.add(s3);
        t.add(t1);
        t.add(t2);
        tree.setBounds(0, 0, 180, 358);
        panel_1.add(tree);
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // TODO Auto-generated method stub
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node.isLeaf()) {
                    textArea.setText(node.getUserObject().toString());
                }
            }
        });


    }

}
