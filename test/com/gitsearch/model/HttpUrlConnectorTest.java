package com.gitsearch.model;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import org.junit.Test;

/*
 * Nama File          : HttpUrlConnectorTest.java
 * Tanggal dibuat     : 12/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas HttpUrlConnectorTest yang berfungsi untuk melakukan pengujian unit (unit test)
 * kelas HttpUrlConnector.
 *
 * @author Jordhy Fernando
 */
public class HttpUrlConnectorTest {

  /**
   * Menguji method getContent (membutuhkan koneksi internet).
   */
  @Test
  public void testGetContent() {
    HttpUrlConnector con = new HttpUrlConnector("https://api.github.com/", 5000);
    try {
      assertNotNull("content is null", con.getContent());
    }
    catch (IOException e) {

    }
  }

}