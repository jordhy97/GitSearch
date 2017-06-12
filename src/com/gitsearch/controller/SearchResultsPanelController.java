package com.gitsearch.controller;

import com.gitsearch.model.SearchResponse;
import com.gitsearch.view.ResultsPanel;
import com.gitsearch.view.SearchResultsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/*
 * Nama File          : SearchResultsPanelController.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */


/**
 * Kelas SearchResultsPanelController yang berfungsi sebagai controller untuk SearchResultsPanel.
 *
 * @author Jordhy Fernando
 */
public class SearchResultsPanelController {

  private SearchResultsPanel view;
  private ResultsPanel resultsPanel;

  /**
   * Menciptakan sebuah SearchPanelController.
   * @param view SearchResultsPanel yang akan dikendalikan.
   * @param resultsPanel ResultsPanel yang menampung SearchResultsPanel.
   */
  public SearchResultsPanelController(SearchResultsPanel view, ResultsPanel resultsPanel) {
    this.view = view;
    this.resultsPanel = resultsPanel;
    view.addPreviousButtonListener(new PreviousButtonListener());
    view.addNextButtonListener(new NextButtonListener());
    view.addUsernameListKeyListener(new UsernameListKeyListener());
    view.addUsernameListMouseListener(new UsernameListMouseListener());
  }

  /**
   * Kelas yang menangani penenakan tombol previous.
   */
  private class PreviousButtonListener implements ActionListener {

    /**
     * Mengatur view untuk menampilkan halaman sebelumnya.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      view.previousPage();
      try {
        view.setModel(SearchResponse.getResponse(view.getQuery().generateUrl()));
        view.update();
      } catch (IOException exception) {
        view.showErrorDialog(exception.getMessage());
      }
    }
  }

  /**
   * Kelas yang menangani penenakan tombol next.
   */
  private class NextButtonListener implements ActionListener {

    /**
     * Mengatur view untuk menampilkan halaman berikutnya.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      view.nextPage();
      try {
        view.setModel(SearchResponse.getResponse(view.getQuery().generateUrl()));
        view.update();
      } catch (IOException exception) {
        view.showErrorDialog(exception.getMessage());
      }
    }
  }

  /**
   * Kelas yang menangani penenakan tombol pada keyboard di daftar username.
   */
  private class UsernameListKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Menampilkan detil pengguna Github yang dipilih ketika tombol enter dilepas.
     */
    @Override
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        processesSelectedUser();
      }
    }
  }

  /**
   * Kelas yang menangani pergerakan mouse pada daftar username.
   */
  private class UsernameListMouseListener implements MouseListener {

    /**
     * Menampilkan detil pengguna Github yang dipilih ketika melakukan double click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
        processesSelectedUser();
      }
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

  /**
   * Memroses dan menampilkan detil pengguna Github yang dipilih.
   */
  private void processesSelectedUser() {
    try {
      view.getSelectedUser().loadRepositories();
    } catch (IOException exception) {
      view.showErrorDialog(exception.getMessage());
    }
    resultsPanel.setUserModel(view.getSelectedUser());
    resultsPanel.show("user");
  }
}
