package com.gitsearch.controller;

import com.gitsearch.model.SearchQuery;
import com.gitsearch.model.SearchResponse;
import com.gitsearch.view.MainView;
import com.gitsearch.view.SearchPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/*
 * Nama File          : SearchPanelController.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */


/**
 * Kelas SearchPanelController yang berfungsi sebagai controller untuk SearchPanel.
 *
 * @author Jordhy Fernando
 */
public class SearchPanelController {

  private SearchPanel view;
  private MainView frame;

  /**
   * Menciptakan sebuah SearchPanelController.
   * @param view SearchPanel yang akan dikendalikan.
   * @param frame Tampilan aplikasi GitSearch yang menampung SearchPanel.
   */
  public SearchPanelController(SearchPanel view, MainView frame) {
    this.view = view;
    this.frame = frame;
    view.addFilterCheckBoxListener(new RepositoriesFilterCheckBoxListener(), 0);
    view.addFilterCheckBoxListener(new FollowersFilterCheckBoxListener(), 1);
    view.addFilterOptionsComboBoxListener(new RepositoriesFilterComboBoxListener(), 0);
    view.addFilterOptionsComboBoxListener(new FollowersFilterComboBoxListener(), 1);
    view.addSearchButtonListener(new SearchButtonListener());
    view.addQueryTextFieldKeyListener(new QueryTextFieldListener());
    view.addLogoLabelMouseListener(new LogoLabelListener());
  }

  /**
   * Kelas yang menangani penenakan tombol Search.
   */
  private class SearchButtonListener implements ActionListener {

    /**
     * Memroses query yang terdapat pada SearchPanel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      processesQuery();
    }
  }

  /**
   * Kelas yang menangani penekanan tombol pada keyboard di text field query.
   */
  private class QueryTextFieldListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Memroses query yang terdapat pada SearchPanel ketika tombol enter dilepas.
     */
    @Override
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        processesQuery();
      }
    }
  }

  /**
   * Memroses query yang terdapat pada SearchPanel dan menampilkan hasilnya.
   */
  private void processesQuery() {
    try {
      SearchQuery query = view.getSearchQuery();
      SearchResponse response = SearchResponse.getResponse(query.generateUrl());
      frame.setSearchQuery(query);
      frame.setSearchResultsModel(response);
      if (frame.isShowingHomePanel()) {
        frame.moveSearchPanel("main");
      }
      frame.show("main");
    } catch (IOException exception) {
      view.showErrorDialog(exception.getMessage());
    }
  }

  /**
   * Kelas yang menangani penekanan pada checkbox untuk filter repositories.
   */
  private class RepositoriesFilterCheckBoxListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (view.isFilterUsed(0)) {
        view.enableFilter(0);
      } else {
        view.disableFilter(0);
      }
    }
  }

  /**
   * Kelas yang menangani penekanan pada checkbox untuk filter followers.
   */
  private class FollowersFilterCheckBoxListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (view.isFilterUsed(1)) {
        view.enableFilter(1);
      } else {
        view.disableFilter(1);
      }
    }
  }

  /**
   * Kelas yang menangani pemilihan suatu nilai pada combo box untuk filter repositories.
   */
  private class RepositoriesFilterComboBoxListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      view.enableFilter(0);
    }
  }

  /**
   * Kelas yang menangani pemilihan suatu nilai pada combo box untuk filter followers.
   */
  private class FollowersFilterComboBoxListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      view.enableFilter(1);
    }
  }

  /**
   * Kelas yang menangani pergerakan mouse pada label logo.
   */
  private class LogoLabelListener implements MouseListener {

    /**
     * Menampilkan HomePanel.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      frame.moveSearchPanel("home");
      frame.show("home");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
  }
}
