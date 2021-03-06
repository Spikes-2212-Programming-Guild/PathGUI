package gui.bars;

import gui.GUI;
import gui.bars.listeners.*;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The menu bar for the path GUI.
 *
 * @author Eran Goldstein
 */
public class PathMenuBar extends JMenuBar {
    /**
     * Sets up the menu bar, menus and menu items.
     *
     * @param gui a user interface object
     */
    public PathMenuBar(GUI gui) {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');

        JMenuItem newFile = new JMenuItem("New", new ImageIcon(getClass().getResource("/res/icon/new.png")));
        newFile.addActionListener(new NewListener(gui));
        newFile.setMnemonic('N');
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem open = new JMenuItem("Open", new ImageIcon(getClass().getResource("/res/icon/open.png")));
        open.addActionListener(new OpenListener(gui));
        open.setMnemonic('O');
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem save = new JMenuItem("Save", new ImageIcon(getClass().getResource("/res/icon/save.png")));
        save.addActionListener(new SaveListener(gui));
        save.setMnemonic('S');
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem export = new JMenuItem("Export");
        export.addActionListener(new ExportListener(gui));
        export.setMnemonic('E');
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

        file.add(newFile);
        file.add(open);
        file.addSeparator();
        file.add(save);
        file.add(export);
        add(file);

        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');

        JMenuItem undo = new JMenuItem("Undo", new ImageIcon(getClass().getResource("/res/icon/undo.png")));
        undo.addActionListener(new UndoListener(gui));
        undo.setMnemonic('U');
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem redo = new JMenuItem("Redo", new ImageIcon(getClass().getResource("/res/icon/redo.png")));
        redo.addActionListener(new RedoListener(gui));
        redo.setMnemonic('R');
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem generate = new JMenuItem("Preview");
        generate.addActionListener(new GenerateListener(gui));
        generate.setMnemonic('P');
        generate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem quickGeneration = new JMenuItem("Quick Preview");
        quickGeneration.addActionListener(new QuickGenerationListener(gui));
        quickGeneration.setMnemonic('Q');
        quickGeneration.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem mirror = new JMenuItem("Mirror Path",
                new ImageIcon(getClass().getResource("/res/icon/mirror.png")));
        mirror.addActionListener(new MirrorListener(gui));
        mirror.setMnemonic('M');
        mirror.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));

        edit.add(undo);
        edit.add(redo);
        edit.addSeparator();
        edit.add(generate);
        edit.add(quickGeneration);
        edit.addSeparator();
        edit.add(mirror);
        add(edit);
    }
}
