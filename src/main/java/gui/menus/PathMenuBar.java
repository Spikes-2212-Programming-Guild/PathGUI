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

        JMenuItem origin = new JMenuItem("Move to Origin");
        origin.setMnemonic('O');
        origin.addActionListener(new OriginListener(gui));

        JMenuItem mirror = new JMenuItem("Mirror Path");
        mirror.setMnemonic('M');
        mirror.addActionListener(new MirrorListener(gui));

        JMenuItem align = new JMenuItem("Align Path");
        align.setMnemonic('A');
        align.addActionListener(new AlignListener(gui));

        JMenuItem rotate180 = new JMenuItem("Rotate Path 180");
        rotate180.setMnemonic('P');
        rotate180.addActionListener(new Rotate180Listener(gui));

        JMenuItem rotate = new JMenuItem("Rotate Path");
        rotate.setMnemonic('R');
        rotate.addActionListener(new RotateListener(gui));

        edit.add(origin);
        edit.add(mirror);
        edit.addSeparator();
        edit.add(align);
        edit.add(rotate180);
        edit.add(rotate);
        add(edit);
    }
}
