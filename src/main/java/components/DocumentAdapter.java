package components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public interface DocumentAdapter extends DocumentListener {
    @Override
    default void changedUpdate(DocumentEvent e) {
        documentChanged(e);
    }

    @Override
    default void insertUpdate(DocumentEvent e) {
        documentChanged(e);
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
        documentChanged(e);
    }

    /**
     * Called when any DocumentListener method is invoked.
     *
     * @param e the {@link DocumentEvent} from the original {@link DocumentListener} method
     */
    void documentChanged(DocumentEvent e);
}
