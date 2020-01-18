package gui;

import javax.swing.*;

public class PathMenuBar extends JMenuBar {
    public PathMenuBar() {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');

        JMenuItem newFile = new JMenuItem("New");
        newFile.setMnemonic('N');

        JMenuItem open = new JMenuItem("Open");
        open.setMnemonic('O');

        JMenuItem saveAs = new JMenuItem("Save As");
        saveAs.setMnemonic('A');

        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic('S');

        file.add(newFile);
        file.add(open);
        file.addSeparator();
        file.add(saveAs);
        file.add(save);
        add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');

        JMenuItem attributes = new JMenuItem("Attributes");
        attributes.setMnemonic('A');

        edit.add(attributes);
        add(edit);
    }
}
