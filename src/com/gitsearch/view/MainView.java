package com.gitsearch.view;

import static java.awt.Font.PLAIN;

import com.gitsearch.model.SearchQuery;
import com.gitsearch.model.SearchResponse;
import com.gitsearch.controller.SearchPanelController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Nama File          : MainView.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */

/**
 * Kelas MainView yang menangani tampilan aplikasi GitSearch.
 *
 * @author Jordhy Fernando
 */
public class MainView extends JFrame {

  private SearchPanel searchPanel;
  private JPanel homePanel;
  private JPanel mainPanel;
  private ResultsPanel resultsPanel;
  private CardLayout layout;
  private static final int WIDTH = 1024;
  private static final int HEIGHT = 768;

  /**
   * Menciptakan sebuah MainView.
   */
  public MainView() {
    createUIComponents();

    setTitle("GitSearch");
    ImageIcon icon = new ImageIcon("assets/logo.png");
    setIconImage(icon.getImage());
    setBackground(Color.WHITE);
    setLayout(layout);
    add(homePanel, "home");
    add(mainPanel, "main");

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  /**
   * Menciptakan komponen-komponen yang terdapat pada MainView.
   */
  private void createUIComponents() {
    searchPanel = new SearchPanel();
    SearchPanelController searchPanelController = new SearchPanelController(searchPanel, this);
    createHomePanel();
    createMainPanel();
    layout = new CardLayout();
  }

  /**
   * Menciptakan sebuah HomePanel.
   */
  private void createHomePanel() {
    JLabel logoLabel = new JLabel("GitSearch");
    ImageIcon logo = new ImageIcon("assets/logo.png");
    logoLabel.setIcon(logo);
    logoLabel.setFont(new Font("Serif", PLAIN, 34));
    homePanel = new JPanel();
    homePanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(3, 3, 3, 3);
    c.gridwidth = GridBagConstraints.REMAINDER;
    homePanel.add(logoLabel, c);
    homePanel.add(searchPanel, c);
    homePanel.setBackground(Color.WHITE);
  }

  /**
   * Menciptakan sebuah MainPanel.
   */
  private void createMainPanel() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    resultsPanel = new ResultsPanel();
    mainPanel.add(resultsPanel, BorderLayout.CENTER);
    mainPanel.setBackground(Color.WHITE);
  }

  /**
   * Memeriksa apakah MainView ini sedang menampilkan HomePanel atau tidak.
   * @return true jika iya dan false jika tidak.
   */
  public boolean isShowingHomePanel() {
    return homePanel.isVisible();
  }

  public void moveSearchPanel(String container) {
    if (container.equals("main")) {
      searchPanel.showLogoLabel(true);
      mainPanel.add(searchPanel, BorderLayout.NORTH);
    }
    else if (container.equals("home")) {
      searchPanel.resetPanel();
      homePanel.add(searchPanel);
    }
  }

  /**
   * Menampilkan panel dengan nama yang ditentukan.
   * @param name nama panel yang ingin ditampilkan (home atau main).
   */
  public void show(String name) {
      layout.show(this.getContentPane(), name);
      if (name.equals("main")) {
        resultsPanel.show("search results");
      }
  }

  /**
   * Mengatur model hasil pencarian untuk panel hasil pencarian yang terdapat pada MainView ini
   * (pada ResultsPanel-nya).
   * @param model hasil pencarian yang digunakan sebagai model (yang akan ditampilkan).
   */
  public void setSearchResultsModel(SearchResponse model) {
    resultsPanel.setSearchResultsModel(model);
  }

  /**
   * Mengatur query pencarian yang digunakan untuk mendapatkan hasil pencarian
   * pada panel hasil pencarian yang terdapat pada MainView ini (pada ResultsPanel-nya).
   * @param query query pencarian yang digunakan.
   */
  public void setSearchQuery(SearchQuery query) {
    resultsPanel.setSearchQuery(query);
  }
}
