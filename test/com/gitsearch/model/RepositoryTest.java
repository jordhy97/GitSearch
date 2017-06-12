package com.gitsearch.model;

import static org.junit.Assert.assertEquals;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

/*
 * Nama File          : RepositoryTest.java
 * Tanggal dibuat     : 12/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas RepositoryTest yang berfungsi untuk melakukan pengujian unit (unit test) kelas Repository.
 *
 * @author Jordhy Fernando
 */
public class RepositoryTest {

  /**
   * Menguji method parseJson dengan menggunakan file testRepository.txt.
   */
  @Test
  public void testParseJson() {
    Scanner reader = new Scanner(new InputStreamReader(getClass()
        .getResourceAsStream("/test/testRepository.txt")));
    StringBuilder rawJson = new StringBuilder();
    while (reader.hasNext()) {
      rawJson.append(reader.nextLine());
    }
    ArrayList<Repository> repositories = Repository.parseJson(rawJson.toString());
    assertEquals("Repository name mismatch at index 0", "BrightSouls",
        repositories.get(0).getName());
    assertEquals("Repository description mismatch at index 0",
        "Tugas Besar Algoritma dan Struktur Data (IF2110)",
        repositories.get(0).getDescription());
    assertEquals("Repository URL mismatch at index 0",
        "https://github.com/jordhy97/BrightSouls", repositories.get(0).getUrl());
    assertEquals("Repository name mismatch at index 1", "GitSearch",
        repositories.get(1).getName());
    assertEquals("Repository description mismatch at index 1",
        "Desktop application to search GitHub users",
        repositories.get(1).getDescription());
    assertEquals("Repository URL mismatch at index 1",
        "https://github.com/jordhy97/GitSearch", repositories.get(1).getUrl());
  }

  /**
   * Menguji method generateHtmlText.
   */
  @Test
  public void testGenerateHtmlText() {
    Repository repository = new Repository("test", "this is a test",
        "http://test.com");
    String htmlText = repository.generateHtmlText();
    assertEquals("Generated Html text mismatch", htmlText, "<html><b>test</b><br>"
        + "<i>this is a test</i><br>URL: <a href=\"http://test.com\" target=\"_blank\">"
        + "http://test.com</a></html>");
  }

}