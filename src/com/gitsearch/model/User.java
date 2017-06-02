package com.gitsearch.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/*
 * Nama File          : User.java
 * Tanggal dibuat     : 02/06/17
 * Tanggal perubahan  : 02/06/17
 */

/**
 * Kelas User yang merepresentasikan seorang pengguna Github.
 *
 * @author Jordhy Fernando
 */
public class User {

  private String username;
  private String reposUrl;
  private ArrayList<Repository> repositories;

  /**
   * Menciptakan pengguna Github dengan username,dan URL repository yang ditentukan.
   * @param username username pengguna Github.
   * @param reposUrl URL repository pengguna Github.
   */
  public User(String username, String reposUrl) {
    this.username = username;
    this.reposUrl = reposUrl;
    repositories = new ArrayList<>();
  }

  /**
   * Mengambil username pengguna Github ini.
   * @return username pengguna Github ini.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Mengambil daftar repository pengguna Github ini.
   * @return daftar repository pengguna Github ini.
   */
  public ArrayList<Repository> getRepositories() {
    return repositories;
  }

  /**
   * Memuat semua repository yang dimiliki oleh pengguna Github ini dari URL repository.
   * @throws IOException jika terjadi kesalahan I/O ketika melakukan koneksi ke URL repository
   * pengguna Github ini.
   */
  public void loadRepositories() throws IOException {
    HttpUrlConnector connector = new HttpUrlConnector(reposUrl, 5000);
    repositories = Repository.parseJson(connector.getContent());
  }

  /**
   * Mem-<i>parse</i> dan mengembalikan daftar pengguna Github yang diberikan dalam format JSON.
   *
   * Prekondisi: format JSON sesuai dengan format yang diberikan Github Search API untuk
   * nilai "items".
   * @param rawJson JSON yang ingin di-<i>parse</i>.
   * @return daftar pengguna Github hasil <i>parsing</i> rawJson.
   */
  public static ArrayList<User> parseJson(String rawJson) {
    JsonReader jsonReader = Json.createReader(new StringReader(rawJson));
    JsonArray array = jsonReader.readArray();
    ArrayList<User> parseResult = new ArrayList<>();
    for (JsonValue value : array) {
      String username = ((JsonObject) value).getJsonString("login").getString();
      String reposUrl = ((JsonObject) value).getJsonString("repos_url").getString();
      parseResult.add(new User(username, reposUrl));
    }
    jsonReader.close();
    return parseResult;
  }

}