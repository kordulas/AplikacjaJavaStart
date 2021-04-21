package io.file;

import libary.model.Library;

public interface FileManager {

    Library importData();
    void exportData(Library library);
}
