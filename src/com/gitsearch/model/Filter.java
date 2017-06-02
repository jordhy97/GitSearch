package com.gitsearch.model;

/*
 * Nama File          : Filter.java
 * Tanggal dibuat     : 02/06/17
 * Tanggal perubahan  : 02/06/17
 */

/**
 * Kelas Filter yang merepresentasikan filter yang dapat digunakan dalam pencarian
 * dengan menggunakan Github Search API.
 *
 * @author Jordhy Fernando
 */
public class Filter {

  private boolean used;
  private String limit;
  private String type;

  /**
   * Menciptakan sebuah filter dengan jenis tertentu.
   * @param type jenis filter.
   */
  public Filter(String type) {
    this.type = type;
    used = false;
  }

  /**
   * Memeriksa apakah filter ini digunakan atau tidak.
   * @return true jika filter ini digunakan dan false jika tidak.
   */
  public boolean isUsed() {
    return used;
  }

  /**
   * Mengaktifkan/mematikan filter ini.
   * @param used true jika filter ini diaktifkan dan false jika dimatikan.
   */
  public void setUsed(boolean used) {
    this.used = used;
  }

  /**
   * Mengambil batas yang digunakan pada filter ini.
   * @return batas yang digunakan pada filter ini.
   */
  public String getLimit() {
    return limit;
  }

  /**
   * Mengatur batas yang ingin digunakan pada filter ini.
   * @param limit batas yang ingin digunakan.
   */
  public void setLimit(String limit) {
    this.limit = limit;
  }

  /**
   * Mengambil jenis filter ini.
   * @return jenis filter ini.
   */
  public String getType() {
    return type;
  }

}
