package gui.menus;

import gui.GUI;

import javax.swing.*;

public class PathMenuBar extends JMenuBar {
    public PathMenuBar(GUI gui) {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');

        JMenuItem newFile = new JMenuItem("New");
        newFile.setMnemonic('N');
        newFile.addActionListener(new NewListener(gui));

        JMenuItem open = new JMenuItem("Open");
        open.setMnemonic('O');
        open.addActionListener(new OpenListener(gui));

        JMenuItem save = new JMenuItem("Save As");
        save.setMnemonic('S');
        save.addActionListener(new SaveListener(gui));

        file.add(newFile);
        file.add(open);
        file.addSeparator();
        file.add(save);
        add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');

        JMenuItem mirror = new JMenuItem("Mirror Path");
        mirror.setMnemonic('M');
        mirror.addActionListener(new MirrorListener(gui));

        JMenuItem undo = new JMenuItem("Undo");
        undo.setMnemonic('U');
        undo.addActionListener(new UndoListener(gui));

        edit.add(undo);
        edit.addSeparator();
        edit.add(mirror);
        add(edit);
    }
}
