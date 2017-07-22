/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Clipboard utilities.
 */
public class ClipboardUtils {

    /**
     * Copies string to system clipboard.
     */
    public static void copyToClipboard(String str) {
        StringSelection copyItem = new StringSelection(str);
        Clipboard clipboard = getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(copyItem, null);
    }

    /**
     * Reads a string from system clipboard.
     */
    public static String getStringFromClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable paste = clipboard.getContents(null);
        if (paste == null) {
            return null;
        }
        try {
            return (String) paste.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception ex) {
            return null;
        }
    }

}