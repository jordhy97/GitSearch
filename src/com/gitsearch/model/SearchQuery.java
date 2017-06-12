package com.gitsearch.model;

import java.util.ArrayList;

/*
 * Nama File          : SearchQuery.java
 * Tanggal dibuat     : 02/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas SearchQuery yang merepresentasikan query pencarian dengan Github Search API
 * dan berfungsi untuk menghasilkan URL pencarian tersebut.
 *
 * @author Jordhy Fernando
 */
public class SearchQuery {

  private String query;
  private String type;
  private int currentPage;
  private ArrayList<Filter> filters;
  private static final int RESULT_PER_PAGE_LIMIT = 15;
  private static final String BASE_URL =
      "https://api.github.com/search/users?access_token=8d587e6ae4f620395cc0bf2711dc56bd433ead7b&q=";

  /**
   * Menciptakan sebuah query pencarian dengan query dan jenis tertentu.
   * @param query bagian query dari query pencarian.
   * @param type jenis query pencarian.
   */
  public SearchQuery(String query, String type) {
    this.query = query;
    this.type = type;
    currentPage = 1;
    filters = new ArrayList<>();
    filters.add(new Filter("repos"));
    filters.add(new Filter("followers"));
  }

  /**
   * Mengambil bagian query (username, email, atau nama dari pengguna yang ingin dicari)
   * dari query pencarian ini.
   * @return bagian query dari query pencarian ini.
   */
  public String getQuery() {
    return query;
  }

  /**
   * Mengatur bagian query (username, email, atau nama dari pengguna yang ingin dicari)
   * dari query pencarian ini.
   * @param query query (username, email, atau nama dari pengguna yang ingin dicari) yang baru.
   */
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   * Mengambil jenis query pencarian ini.
   * @return jenis query pencarian ini.
   */
  public String getType() {
    return type;
  }

  /**
   * Mengatur jenis query pencarian ini.
   * @param type jenis query pencarian yang baru.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Mengambil halaman saat ini pada query pencarian ini.
   * @return halaman saat ini pada query pencarian ini.
   */
  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * Mengatur halaman saat ini pada query pencarian ini.
   * @param currentPage halaman query pencarian yang baru.
   */
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }


  /**
   * Mengambil daftar filter yang dapat digunakan pada query pencarian ini.
   * @return daftar filter yang dapat digunakan pada query pencarian ini.
   */
  public ArrayList<Filter> getFilters() {
    return filters;
  }

  /**
   * Mengambil batas hasil pencarian per halaman untuk query pencarian.
   * @return batas hasil pencarian per halaman untuk query pencarian.
   */
  public static int getResultPerPageLimit() {
    return RESULT_PER_PAGE_LIMIT;
  }

  /**
   * Menghasilkan URL pencarian dari query pencarian ini.
   * @return URL pencarian yang bersesuaian dengan query pencarian ini.
   */
  public String generateUrl() {
    StringBuilder url = new StringBuilder(BASE_URL);
    url.append(query);
    url.append("+in:");
    url.append(type);
    for (Filter filter : filters) {
      if (filter.isUsed()) {
        url.append("+");
        url.append(filter.getType());
        url.append(":");
        url.append(filter.getLimit());
      }
    }
    url.append("&page=").append(currentPage).append("&per_page=").append(RESULT_PER_PAGE_LIMIT);
    return url.toString();
  }

}
