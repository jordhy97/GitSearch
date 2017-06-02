package com.gitsearch.model;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/*
 * Nama File          : SearchResponse.java
 * Tanggal dibuat     : 02/06/17
 * Tanggal perubahan  : 02/06/17
 */

/**
 * Kelas SearchResponse yang merepresentasikan respon pencarian
 * yang didapatkan dari pencarian pengguna Github dengan menggunakan Github Search API.
 *
 * @author Jordhy Fernando
 */
public class SearchResponse {

  private int totalCount;
  private boolean incompleteResults;
  private ArrayList<User> users;

  /**
   * Menciptakan sebuah respon pencarian kosong.
   */
  public SearchResponse() {
    totalCount = 0;
    incompleteResults = false;
    users = new ArrayList<>();
  }

  /**
   * Mengambil jumlah hasil pencarian dari respon pencarian ini.
   * @return jumlah hasil pencarian dari respon pencarian ini.
   */
  public int getTotalCount() {
    return totalCount;
  }

  /**
   * Memeriksa apakah hasil dari respon pencarian ini lengkap atau tidak.
   * @return true jika hasilnya tidak lengkap dan false jika lengkap.
   */
  public boolean isIncompleteResults() {
    return incompleteResults;
  }

  /**
   * Mengambil daftar pengguna Github dari respon pencarian ini.
   * @return daftar pengguna Github dari respon pencarian ini.
   */
  public ArrayList<User> getUsers() {
    return users;
  }

  /**
   * Mengambil dan mengembalikan respon pencarian dari URL pencarian yang diberikan.
   * @param url URL pencarian.
   * @return respon pencarian dari URL yang diberikan.
   * @throws IOException jika terjadi kesalahan I/O ketika melakukan koneksi ke URL pencarian.
   */
  public static SearchResponse getResponse(String url) throws IOException {
    HttpUrlConnector connector = new HttpUrlConnector(url, 5000);
    JsonReader jsonReader = Json.createReader(new StringReader(connector.getContent()));
    JsonObject object = jsonReader.readObject();
    SearchResponse result = new SearchResponse();
    result.totalCount = object.getInt("total_count");
    result.incompleteResults = object.getBoolean("incomplete_results");
    result.users = User.parseJson(object.getJsonArray("items").toString());
    jsonReader.close();
    return result;
  }

}
