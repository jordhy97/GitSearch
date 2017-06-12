package com.gitsearch.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

/*
 * Nama File          : UserTest.java
 * Tanggal dibuat     : 12/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas RepositoryTest yang berfungsi untuk melakukan pengujian unit (unit test) kelas User.
 *
 * @author Jordhy Fernando
 */
public class UserTest {

  /**
   * Menguji method loadRepositories (membutuhkan koneksi internet).
   */
  @Test
  public void loadRepositories() {
    User user = new User("jordhy97", "https://api.github.com/users/jordhy97/repos");
    try {
      user.loadRepositories();
      assertTrue("Repositories not loaded", user.isRepositoriesLoaded());
    }
    catch (IOException e) {
      assertEquals("error message mismatch", "Failed to "
          + "load repositories, please check your internet connection", e.getMessage());
    }
  }

  /**
   * Menguji method parseJson dengan menggunakan file testUser.txt.
   */
  @Test
  public void testParseJson() {
    Scanner reader = new Scanner(new InputStreamReader(getClass()
        .getResourceAsStream("/test/testUser.txt")));
    StringBuilder rawJson = new StringBuilder();
    while (reader.hasNext()) {
      rawJson.append(reader.nextLine());
    }
    ArrayList<User> users = User.parseJson(rawJson.toString());
    assertEquals("Username mismatch at index 0", "jordhy",
        users.get(0).getUsername());
    assertEquals("Username mismatch at index 1", "jordhy97",
        users.get(1).getUsername());
    assertEquals("Username mismatch at index 2", "jordhy12",
        users.get(2).getUsername());
  }

}