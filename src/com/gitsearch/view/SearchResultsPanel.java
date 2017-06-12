package com.gitsearch.view;

import com.gitsearch.model.SearchQuery;
import com.gitsearch.model.SearchResponse;
import com.gitsearch.model.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

/*
 * Nama File          : SearchResultsPanel.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */


/**
 * Kelas SearchResultsPanel yang menangani tampilan panel hasil pencarian.
 *
 * @author Jordhy Fernando
 */
public class SearchResultsPanel extends JPanel {

  private SearchResponse model;
  private SearchQuery query;
  private JLabel totalCountLabel;
  private JLabel helpLabel;
  private DefaultListModel<String> usernameListModel;
  private JList usernameList;
  private JButton previousButton;
  private JButton nextButton;

  /**
   * Menciptakan sebuah panel hasil pencarian.
   */
  public SearchResultsPanel() {
    createUIComponents();
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(3, 4, 3, 4);
    c.fill = GridBagConstraints.BOTH;
    c.gridwidth = 10;
    add(totalCountLabel, c);
    c.gridy = 1;
    add(helpLabel, c);
    c.gridy = 2;
    add(usernameList, c);
    c.gridy = 3;
    c.gridwidth = 1;
    add(previousButton, c);
    add(nextButton, c);
    setBackground(Color.WHITE);
  }

  /**
   * Menciptakan komponen-komponen yang terdapat pada panel hasil pencarian.
   */
  private void createUIComponents() {
    totalCountLabel = new JLabel();
    totalCountLabel.setPreferredSize(new Dimension(740, 24));
    helpLabel = new JLabel("(Press enter or double click on a selected user "
        + "to view the user details)");
    helpLabel.setFont(new Font(helpLabel.getFont().getFontName(), Font.PLAIN, 11));
    usernameListModel = new DefaultListModel<>();
    usernameList = new JList(usernameListModel);
    usernameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    usernameList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    usernameList.setFixedCellHeight(25);
    usernameList.setSelectedIndex(0);
    usernameList.setPreferredSize(new Dimension(740, 375));
    previousButton = new JButton("< prev");
    nextButton = new JButton("next >");
  }

  /**
   * Mengatur model hasil pencarian untuk panel hasil pencarian ini.
   * @param model hasil pencarian yang digunakan sebagai model (yang akan ditampilkan).
   */
  public void setModel(SearchResponse model) {
    this.model = model;
  }

  /**
   * Mengatur query pencarian yang digunakan untuk mendapatkan hasil pencarian
   * pada panel hasil pencarian ini.
   * @param query query pencarian yang digunakan.
   */
  public void setQuery(SearchQuery query) {
    this.query = query;
  }

  /**
   * Memperbarui tampilan panel hasil pencarian ini.
   */
  public void update() {
    if (model.getTotalCount() <= 1) {
      totalCountLabel.setText("Found " + model.getTotalCount() + " user");
    } else {
      totalCountLabel.setText("Found " + model.getTotalCount() + " users");
    }
    usernameListModel.clear();
    for (User user : model.getUsers()) {
      usernameListModel.addElement(user.getUsername());
    }
    if (query.getCurrentPage() == 1) {
      previousButton.setEnabled(false);
    } else {
      previousButton.setEnabled(true);
    }
    if (query.getCurrentPage() * SearchQuery.getResultPerPageLimit() < model.getTotalCount()) {
      nextButton.setEnabled(true);
    } else {
      nextButton.setEnabled(false);
    }
  }

  /**
   * Mengganti halaman pencarian menjadi halaman sebelumnya pada panel hasil pencarian ini.
   */
  public void previousPage() {
    query.setCurrentPage(query.getCurrentPage() - 1);
  }

  /**
   * Mengganti halaman pencarian menjadi halaman berikutnya pada panel hasil pencarian ini.
   */
  public void nextPage() {
    query.setCurrentPage(query.getCurrentPage() + 1);
  }

  /**
   * Mengambil query pencarian yang digunakan untuk mendapatkan hasil pencarian
   * pada panel pencarian ini.
   * @return query pencarian yang digunakan.
   */
  public SearchQuery getQuery() {
    return query;
  }

  /**
   * Mengambil pengguna Github yang dipilih pada panel hasil pencarian ini.
   * @return pengguna Github yang dipilih.
   */
  public User getSelectedUser() {
    return model.getUsers().get(usernameList.getSelectedIndex());
  }

  /**
   * Menambahkan sebuah ActionListener pada tombol previous.
   * @param l ActionListener yang ingin ditambahkan.
   */
  public void addPreviousButtonListener(ActionListener l) {
    previousButton.addActionListener(l);
  }

  /**
   * Menambahkan sebuah ActionListener pada tombol next.
   * @param l ActionListener yang ingin ditambahkan.
   */
  public void addNextButtonListener(ActionListener l) {
    nextButton.addActionListener(l);
  }

  /**
   * Menambahkan sebuah MouseListener pada daftar username pengguna Github.
   * @param l MouseListener yang ingin ditambahkan.
   */
  public void addUsernameListMouseListener(MouseListener l) {
    usernameList.addMouseListener(l);
  }

  /**
   * Menambahkan sebuah KeyListener pada daftar username pengguna Github.
   * @param l KeyListener yang ingin ditambahkan.
   */
  public void addUsernameListKeyListener(KeyListener l) {
    usernameList.addKeyListener(l);
  }

  /**
   * Menampilkan dialog pesan kesalahan.
   * @param message pesan kesalahan yang ingin ditampilkan.
   */
  public void showErrorDialog(String message) {
    JOptionPane.showMessageDialog(this.getParent(), message, "Error",
        JOptionPane.ERROR_MESSAGE);
  }
}