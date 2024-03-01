package hash.me;

import hash.me.mdb5Tool.Mdb5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
//        Mdb5.generate("/home/playfulCloud/IdeaProjects/Lab1/Mdb5/checkme.txt");
        Mdb5.validate("/home/playfulCloud/IdeaProjects/Lab1/Mdb5/checkme.txt","/home/playfulCloud/IdeaProjects/Lab1/Mdb5/checkme.txt.md5");
    }
}