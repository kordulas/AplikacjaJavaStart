package io.file;

import exception.NoSuchFileTypeException;
import io.ConsolePrinter;
import io.DataReader;
import java.util.Locale;

public class FileManagerBuilder {

    private ConsolePrinter printer;
    private DataReader dataReader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader dataReader) {
        this.printer = printer;
        this.dataReader = dataReader;
    }

    public FileManager build() {
        printer.printLine("Wybierz format danych: ");
        FileType fileType = getFileType();
        switch (fileType){
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Nieobslugiwany typ danych");
           }
        }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            String type = dataReader.getString().toUpperCase(Locale.ROOT);
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Nie obslugiwany typ danych, wybierz ponownie.");
            }
        }while (!typeOk);
        return result;
    }
    private void printTypes() {
        for(FileType type : FileType.values())
            printer.printLine(type.name());
    }
}