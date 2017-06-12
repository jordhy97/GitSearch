package com.gitsearch.model;

import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/*
 * Nama File          : Repository.java
 * Tanggal dibuat     : 02/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas Repository yang merepresentasikan sebuah repository yang dimiliki oleh seorang
 * pengguna Github.
 *
 * @author Jordhy Fernando
 */
public class Repository {

  private String name;
  private String description;
  private String url;

  /**
   * Menciptakan sebuah repository dengan nama, deskripsi, dan url yang ditentukan.
   * @param name nama repository.
   * @param description deskripsi repository.
   * @param url URL repository.
   */
  public Repository(String name, String description, String url) {
    this.name = name;
    this.description = description;
    this.url = url;
  }

  /**
   * Mengambil nama repository ini.
   * @return nama repository ini.
   */
  public String getName() {
    return name;
  }

  /**
   * Mengambil deskripsi repository ini.
   * @return deskripsi repository ini atau null jika tidak ada.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Mengambil URL repository ini.
   * @return URL repository ini.
   */
  public String getUrl() {
    return url;
  }

  /**
   * Mem-<i>parse</i> dan mengembalikan daftar repository yang diberikan dalam format JSON.
   *
   * <p>Prekondisi: format JSON sesuai dengan format yang diberikan Github API.
   * @param rawJson JSON yang ingin di-<i>parse</i>.
   * @return daftar repository hasil <i>parsing</i> rawJson.
   */
  public static ArrayList<Repository> parseJson(String rawJson) {
    JsonReader jsonReader = Json.createReader(new StringReader(rawJson));
    JsonArray array = jsonReader.readArray();
    ArrayList<Repository> parseResult = new ArrayList<>();
    for (JsonValue value : array) {
      String name = ((JsonObject) value).getJsonString("name").getString();
      String description = ((JsonObject) value).isNull("description") ? null :
          ((JsonObject) value).getJsonString("description").getString();
      String url = ((JsonObject) value).getJsonString("html_url").getString();
      parseResult.add(new Repository(name, description, url));
    }
    jsonReader.close();
    return parseResult;
  }

  /**
   * Menghasilkan teks HTML berdasarkan data repository.
   * @return teks HTML yang merepresentasikan data repository.
   */
  public String generateHtmlText() {
    String htmlText = "<html>";
    htmlText += "<b>" + name + "</b><br>";
    if (description == null) {
      htmlText += "<i>-</i><br>";
    } else {
      htmlText += "<i>" + description + "</i><br>";
    }
    htmlText += "URL: <a href=\"" + url + "\" target=\"_blank\">" + url
        + "</a></html>";
    return htmlText;
  }
}
