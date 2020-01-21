package gui.menus;

import gui.GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PathMenuBar extends JMenuBar {
    public PathMenuBar(GUI gui) {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');

        JMenuItem newFile = new JMenuItem("New");
        newFile.addActionListener(new NewListener(gui));
        newFile.setMnemonic('N');
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem open = new JMenuItem("Open");
        open.setMnemonic('O');
        open.addActionListener(new OpenListener(gui));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem save = new JMenuItem("Save As");
        save.addActionListener(new SaveListener(gui));
        save.setMnemonic('S');
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        file.add(newFile);
        file.add(open);
        file.addSeparator();
        file.add(save);
        add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');

        JMenuItem mirror = new JMenuItem("Mirror Path");
        mirror.addActionListener(new MirrorListener(gui));
        mirror.setMnemonic('M');

        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(new UndoListener(gui));
        undo.setMnemonic('U');
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));

        edit.add(undo);
        edit.addSeparator();
        edit.add(mirror);
        add(edit);
    }
}
