package com.mademo.view2table.tool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class FileHandle {

    private static final Log log = LogFactory.getLog(FileHandle.class);


    public static String readFileToString(String filePath, Charset charset) {
        try {
            Path path = Paths.get(filePath);
            return Files.readString(path, charset);
        } catch (Exception e) {
            throw new RuntimeException("file not exists");
        }
    }

    public static void writeStringToFile(String filePath, String content, Charset charset, boolean isAppend) {
        try {
            Path path = Paths.get(filePath);

            if (isAppend) {
                Files.write(path, Collections.singleton(content), charset, StandardOpenOption.APPEND);
                log.info(String.format("write %s to %s success, append mode", content, filePath));
                return;
            }

            LocalDateTime timestamp = LocalDateTime.now();
            String backupFilePath = filePath + "_" + timestamp.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            Files.copy(path, Paths.get(backupFilePath), StandardCopyOption.REPLACE_EXISTING);

            Files.write(path, Collections.singleton(content), charset);
            log.info(String.format("write %s to %s success, overwrite mode", content, filePath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("write to file error");
        }
    }


    public static void main(String[] args) {

    }
}
