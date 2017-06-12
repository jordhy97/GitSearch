package com.gitsearch.controller;

import com.gitsearch.view.ResultsPanel;
import com.gitsearch.view.UserPanel;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

/*
 * Nama File          : UserPanelController.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas UserPanelController yang berfungsi sebagai controller untuk UserPanel.
 *
 * @author Jordhy Fernando
 */
public class UserPanelController {

  private UserPanel view;
  private ResultsPanel resultsPanel;

  /**
   * Menciptakan sebuah UserPanelController.
   * @param view UserPanel yang akan dikendalikan.
   * @param resultsPanel ResultsPanel yang menampung UserPanel.
   */
  public UserPanelController(UserPanel view, ResultsPanel resultsPanel) {
    this.view = view;
    this.resultsPanel = resultsPanel;
    view.addPreviousButtonListener(new PreviousButtonListener());
    view.addNextButtonListener(new NextButtonListener());
    view.addBackToResultsButtonListener(new BackToResultsButtonListener());
    view.addRepositoriesListMouseListener(new RepositoriesListMouseListener());
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
    }
  }

  /**
   * Kelas yang menangani penenakan tombol back to results.
   */
  private class BackToResultsButtonListener implements ActionListener {

    /**
     * Menampilkan SearchResultPanel yang terdapat pada resultPanel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      resultsPanel.show("search results");
    }
  }

  /**
   * Kelas yang menangani pergerakan mouse pada daftar repository.
   */
  private class RepositoriesListMouseListener implements MouseListener {

    /**
     * Menampilkan halaman repository yang dipilih ketika melakukan double click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
        try {
          Desktop.getDesktop().browse(new URI(view.getSelectedRepoUrl()));
        } catch (Exception exception) {
          view.showErrorDialog("Failed to load repositories");
        }
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

}
