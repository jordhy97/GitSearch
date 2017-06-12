package com.gitsearch.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Nama File          : HttpUrlConnector.java
 * Tanggal dibuat     : 02/06/17
 * Tanggal perubahan  : 02/06/17
 */

/**
 * Kelas HttpUrlConnector yang berfungsi untuk melakukan koneksi ke sebuah URL dan mengambil
 * konten dari URL tersebut.
 *
 * @author Jordhy Fernando
 */
public class HttpUrlConnector {

  private String url;
  private int timeout;

  /**
   * Menciptakan sebuah HttpUrlConnector dengan URL dan batas waktu koneksi yang ditentukan.
   *
   * <p>Prekondisi: timeout tidak bernilai negatif
   * @param url URL yang akan disambungkan.
   * @param timeout batas waktu untuk melakukan koneksi ke url(dalam ms).
   */
  public HttpUrlConnector(String url, int timeout) {
    this.url = url;
    this.timeout = timeout;
  }

  /**
   * Mengambil dan mengembalikan konten dari URL HttpUrlConnector ini.
   * @return konten dari URL HttpUrlConnector ini.
   * @throws IOException jika terjadi kesalahan I/O ketika melakukan koneksi ke URL.
   */
  public String getContent() throws IOException {
    HttpURLConnection con = null;
    try {
      URL url = new URL(this.url);
      con = (HttpURLConnection) url.openConnection();
      con.setConnectTimeout(timeout);
      con.setReadTimeout(timeout);
      InputStream ins = con.getInputStream();
      InputStreamReader isr = new InputStreamReader(ins);
      BufferedReader in = new BufferedReader(isr);
      StringBuilder content = new StringBuilder();
      String line = in.readLine();
      while (line != null) {
        content.append(line);
        line = in.readLine();
      }
      in.close();
      con.disconnect();
      return content.toString();
    } catch (IOException e) {
      if (con != null) {
        con.disconnect();
      }
      e.printStackTrace();
      throw new IOException(((Integer) con.getResponseCode()).toString());
    }
  }

}
