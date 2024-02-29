package org.example.zipperTool;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Zipper {



    public static void zipDir(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipDir(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        finishZip(fileToZip, fileName, zipOut);
    }

    private static void finishZip(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    public static void zipEverything(List<String> paths) throws IOException {
        FileOutputStream fos = new FileOutputStream("dirCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for(String path : paths){
            zipDir(new File(path),path,zipOut);
        }

        zipOut.close();
        fos.close();
    }





}
