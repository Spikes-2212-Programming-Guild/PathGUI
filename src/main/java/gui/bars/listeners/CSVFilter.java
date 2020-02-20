package gui.bars.listeners;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CSVFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if(file.isDirectory())
            return true;

        if(file.getName().lastIndexOf('.') > 0)
            return file.getName().substring(file.getName().lastIndexOf('.')).toLowerCase().equals(".csv");
        return false;
    }

    @Override
    public String getDescription() {
        return "CSV Path Files";
    }
}
