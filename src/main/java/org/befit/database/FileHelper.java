package org.befit.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {

  private final String dirPath;

  public FileHelper(String path) throws IOException {
    this.dirPath = path;
    //this.dirPath = "src/main/resources/org/befit/inFileDatabase";
    if (Files.notExists(Paths.get(dirPath + "RecipesDB.db"))) {
      Files.createFile(Paths.get(dirPath + "RecipesDB.db"));
    }
    if (Files.notExists(Paths.get(dirPath + "DietsDB.db"))) {
      Files.createFile(Paths.get(dirPath + "DietsDB.db"));
    }
  }

  public void writeLineToFile(String line, String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath + fileName, true))) {
      writer.append(line).append("\n");
    }
  }

  public void deleteLineFromFile(String line, String fileName) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(dirPath + fileName));
         BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath + fileName, false))) {
      List<String> updatedLines = reader.lines()
          .filter(s -> !s.contains(line))
          .collect(Collectors.toList());

      for (String singleLine : updatedLines) {
        writer.append(singleLine).append("\n");
      }
    }
  }

  public List<String> readLinesFromFile(String fileName) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(dirPath + fileName))) {
      return reader.lines().collect(Collectors.toList());
    }
  }

}
