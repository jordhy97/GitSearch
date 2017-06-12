package com.gitsearch.view;

import com.gitsearch.controller.SearchResultsPanelController;
import com.gitsearch.controller.UserPanelController;
import com.gitsearch.model.SearchQuery;
import com.gitsearch.model.SearchResponse;
import com.gitsearch.model.User;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;

/*
 * Nama File          : ResultsPanel.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */


/**
 * Kelas ResultPanel yang menampung SearchResultsPanel dan UserPanel.
 *
 * @author Jordhy Fernando
 */
public class ResultsPanel extends JPanel {

  private SearchResultsPanel searchResultsPanel;
  private UserPanel userPanel;
  private CardLayout layout;

  /**
   * Menciptakan sebuah ResultPanel.
   */
  public ResultsPanel() {
    createUIComponents();
    setLayout(layout);
    add(searchResultsPanel, "search results");
    add(userPanel, "user");
    setBackground(Color.WHITE);
    userPanel.setVisible(false);
  }

  /**
   * Menciptakan komponen-komponen yang terdapat pada ResultPanel.
   */
  private void createUIComponents() {
    searchResultsPanel = new SearchResultsPanel();
    SearchResultsPanelController searchResultsPanelController
        = new SearchResultsPanelController(searchResultsPanel, this);
    userPanel = new UserPanel();
    UserPanelController userPanelController = new UserPanelController(userPanel, this);
    layout = new CardLayout();
  }

  /**
   * Menampilkan panel dengan nama yang ditentukan.
   * @param name nama panel yang ingin ditampilkan (search results atau user).
   */
  public void show(String name) {
    layout.show(this, name);
  }

  /**
   * Mengatur model hasil pencarian untuk panel hasil pencarian yang terdapat pada ResultPanel ini.
   * @param model hasil pencarian yang digunakan sebagai model (yang akan ditampilkan).
   */
  public void setSearchResultsModel(SearchResponse model) {
    searchResultsPanel.setModel(model);
    searchResultsPanel.update();
  }

  /**
   * Mengatur query pencarian yang digunakan untuk mendapatkan hasil pencarian
   * pada panel hasil pencarian yang terdapat pada ResultPanel ini.
   * @param query query pencarian yang digunakan.
   */
  public void setSearchQuery(SearchQuery query) {
    searchResultsPanel.setQuery(query);
  }

  /**
   * Mengatur model pengguna Github untuk panel pengguna Github yang terdapat pada ResultPanel ini.
   * @param model pengguna Github yang digunakan sebagai model (yang akan ditampilkan).
   */
  public void setUserModel(User model) {
    userPanel.setModel(model);
    userPanel.update();
  }
}
