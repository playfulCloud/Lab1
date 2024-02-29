package org.example;

import org.example.zipperTool.Zipper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var test = List.of("/home/playfulCloud/IdeaProjects/test2");
        Zipper.zipEverything(test);
    }
}