package com.gitsearch.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * Nama File          : SearchQueryTest.java
 * Tanggal dibuat     : 12/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas SearchQueryTest yang berfungsi untuk melakukan pengujian unit (unit test)
 * kelas SearchQuery.
 *
 * @author Jordhy Fernando
 */
public class SearchQueryTest {

  /**
   * Menguji method generateUrl.
   */
  @Test
  public void generateUrl() {
    SearchQuery searchQuery = new SearchQuery("test", "login");
    assertEquals("Generated URL mismatch", "https://api.github.com/"
        + "search/users?access_token=8d587e6ae4f620395cc0bf2711dc56bd433ead7b&"
        + "q=test+in:login&page=1&per_page=15", searchQuery.generateUrl());
  }

}