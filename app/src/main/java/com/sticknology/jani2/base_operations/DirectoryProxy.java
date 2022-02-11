package com.sticknology.jani2.base_operations;

import android.content.Context;

import java.io.File;
import java.util.List;

public class DirectoryProxy {

    public File getEndDirectory(List<String> dirNames, Context c) {

        File dir = new File(c.getFilesDir(), dirNames.get(0));
        for (int i = 1; i < dirNames.size(); i++) {
            dir = new File(dir, dirNames.get(i));
        }
        return dir;
    }
}
