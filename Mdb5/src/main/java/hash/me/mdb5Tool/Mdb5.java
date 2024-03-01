package hash.me.mdb5Tool;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Mdb5 {

    public static boolean validate(String fileName,String md5FilePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Path.of(fileName)));
        byte[] digest = md.digest();
        String actualMD5 = bytesToHex(digest).toUpperCase();

        // Czytaj oczekiwany MD5 z pliku
        String expectedMD5 = Files.readString(Path.of(md5FilePath)).toUpperCase().trim();

        // Porównaj obliczony MD5 z oczekiwanym
        System.out.println(actualMD5.equals(expectedMD5));
        return actualMD5.equals(expectedMD5);
    }

    public static void generate(String fileName) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Path.of(fileName)));

        byte[] digest = md.digest();
        String data = bytesToHex(digest).toUpperCase();
        try (FileWriter fileWriter = new FileWriter(fileName + ".md5")) {
            fileWriter.write(data);
            System.out.println("file with md5 hash has been created" + fileName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
