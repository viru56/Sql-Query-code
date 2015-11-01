package com.sql.database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class _Frame extends JFrame {
    
   JTextArea jTextArea = new JTextArea(25,80);
    public _Frame() {
    	this.setVisible(true);
    	jTextArea.setText("NanSql>");
    	Font font = new Font(Font.SERIF, Font.PLAIN, 30);
    	this.jTextArea.setBackground(Color.black);
    	this.jTextArea.setForeground(Color.WHITE);
    	this.jTextArea.setFont(font);
        JScrollPane scrollingArea = new JScrollPane(jTextArea);
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(scrollingArea, BorderLayout.CENTER);
        this.jTextArea.addKeyListener(new _addKeyListener(this));
        //... Set window characteristics.
        this.setContentPane(content);
        this.setTitle("File Based Database- Sql Query Support.........devloped by viredner nehra                                                     *(All Copyright Reserved)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
}