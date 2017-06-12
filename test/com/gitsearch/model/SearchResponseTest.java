package com.gitsearch.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import org.junit.Test;

/*
 * Nama File          : SearchResponseTest.java
 * Tanggal dibuat     : 12/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas SearchResponseTest yang berfungsi untuk melakukan pengujian unit (unit test)
 * kelas SearchResponse.
 *
 * @author Jordhy Fernando
 */
public class SearchResponseTest {

  /**
   * Menguji method getResponse untuk response yang ada hasil (membutuhkan koneksi internet).
   */
  @Test
  public void getResponse_foundUsers() {
    try {
      SearchResponse searchResponse = SearchResponse.getResponse("https://api.github.com/"
          + "search/users?access_token=8d587e6ae4f620395cc0bf2711dc56bd433ead7b&"
          + "q=jordhy97+in:login");
      assertEquals("total count mismatch", 1, searchResponse.getTotalCount());
      assertFalse("incomplete results mismatch", searchResponse.isIncompleteResults());
      assertNotNull("users is null", searchResponse.getUsers());
    }
    catch (IOException e) {
      assertEquals("error message mismatch", "Failed to "
          + "load repositories, please check your internet connection", e.getMessage());
    }
  }

  /**
   * Menguji method getResponse untuk response yang tidak ada hasil (membutuhkan koneksi internet).
   */
  @Test
  public void getResponse_foundNoUsers() {
    try {
      SearchResponse searchResponse = SearchResponse.getResponse("https://api.github.com/search/"
          + "users?ccess_token=8d587e6ae4f620395cc0bf2711dc56bd433ead7b&"
          + "q=afwavabsebsc%20vsgvsacvasdcs+in:login");
      assertEquals("total count mismatch", 0, searchResponse.getTotalCount());
      assertFalse("incomplete results mismatch", searchResponse.isIncompleteResults());
      assertNotNull("users is null", searchResponse.getUsers());
    }
    catch (IOException e) {
      assertEquals("error message mismatch", "Failed to "
          + "load repositories, please check your internet connection", e.getMessage());
    }
  }
}