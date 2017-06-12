package com.gitsearch.view;

import com.gitsearch.model.Repository;
import com.gitsearch.model.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Nama File          : UserPanel.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */


/**
 * Kelas UserPanel yang menangani tampilan panel pengguna Github.
 *
 * @author Jordhy Fernando
 */
public class UserPanel extends JPanel {

  private User model;
  private JLabel usernameLabel;
  private JLabel helpLabel;
  private DefaultListModel<Repository> repositoriesListModel;
  private JList repositoriesList;
  private JButton previousButton;
  private JButton nextButton;
  private JButton backToResultsButton;
  private int currentPage;
  private static final int REPO_PER_PAGE = 5;

  /**
   * Menciptakan sebuah panel pengguna Github.
   */
  public UserPanel() {
    createUIComponents();
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(3, 4, 3, 4);
    c.fill = GridBagConstraints.BOTH;
    c.gridwidth = 10;
    add(usernameLabel, c);
    c.gridy = 1;
    add(helpLabel, c);
    c.gridy = 2;
    add(repositoriesList, c);
    c.gridy = 3;
    c.gridwidth = 1;
    add(backToResultsButton, c);
    add(previousButton, c);
    add(nextButton, c);
    setBackground(Color.WHITE);
  }

  /**
   * Menciptakan komponen-komponen yang terdapat pada panel pengguna Github.
   */
  private void createUIComponents() {
    usernameLabel = new JLabel();
    usernameLabel.setPreferredSize(new Dimension(740, 24));
    helpLabel = new JLabel("(double click on a repository to open it in your browser)");
    helpLabel.setFont(new Font(helpLabel.getFont().getFontName(), Font.PLAIN, 11));
    repositoriesListModel = new DefaultListModel<>();
    repositoriesList = new JList(repositoriesListModel);
    repositoriesList.setCellRenderer(new RepositoriesListCellRenderer());
    repositoriesList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    repositoriesList.setFixedCellHeight(75);
    repositoriesList.setSelectedIndex(0);
    repositoriesList.setPreferredSize(new Dimension(740, 375));
    previousButton = new JButton("< prev");
    nextButton = new JButton("next >");
    backToResultsButton = new JButton("back to results");
    currentPage = 1;
  }

  /**
   * Mengatur model pengguna Github untuk panel pengguna Github ini.
   * @param model pengguna Github yang digunakan sebagai model (yang akan ditampilkan)
   */
  public void setModel(User model) {
    this.model = model;
  }

  /**
   * Memperbarui tampilan panel pengguna Github ini.
   */
  public void update() {
    if (model.getRepositories().size() <= 1) {
      usernameLabel.setText(model.getUsername()
          + " (Repository: " + (model.getRepositories().size()) + ")");
    } else {
      usernameLabel.setText(model.getUsername()
          + " (Repositories: " + (model.getRepositories().size()) + ")");
    }
    repositoriesListModel.clear();
    for (int i = (currentPage - 1) * REPO_PER_PAGE; i < currentPage * REPO_PER_PAGE; i++) {
      if (i >= model.getRepositories().size()) {
        break;
      }
      repositoriesListModel.addElement(model.getRepositories().get(i));

    }
    if (currentPage == 1) {
      previousButton.setEnabled(false);
    } else {
      previousButton.setEnabled(true);
    }
    if (currentPage * REPO_PER_PAGE < model.getRepositories().size()) {
      nextButton.setEnabled(true);
    } else {
      nextButton.setEnabled(false);
    }
  }

  /**
   * Mengganti halaman pengguna Github menjadi halaman sebelumnya pada panel pengguna Github ini.
   */
  public void previousPage() {
    currentPage--;
    update();
  }

  /**
   * Mengganti halaman pengguna Github menjadi halaman berikutnya pada panel pengguna Github ini.
   */
  public void nextPage() {
    currentPage++;
    update();
  }

  /**
   * Mengambil URL repository yang dipilih pada panel pengguna Github ini.
   * @return URL repository yang dipilih.
   */
  public String getSelectedRepoUrl() {
    return model.getRepositories().get(repositoriesList.getSelectedIndex()).getUrl();
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
   * Menambahkan sebuah ActionListener pada tombol back to results.
   * @param l ActionListener yang ingin ditambahkan.
   */
  public void addBackToResultsButtonListener(ActionListener l) {
    backToResultsButton.addActionListener(l);
  }

  /**
   * Menambahkan sebuah MouseListener pada daftar repository pengguna Github.
   * @param l MouseListener yang ingin ditambahkan.
   */
  public void addRepositoriesListMouseListener(MouseListener l) {
    repositoriesList.addMouseListener(l);
  }

  /**
   * Kelas untuk mengatur tampilan daftar repository pengguna Github.
   */
  private class RepositoriesListCellRenderer extends DefaultListCellRenderer {

    /**
     * Mengembalikan komponen yang telah diatur untuk menampilkan value yang telah ditentukan.
     * @param list JList yang akan ditampilkan.
     * @param value nilai yang akan ditampilkan.
     * @param index indeks dari cell pada list.
     * @param selected true jika cell (komponen) ini dipilih dan false jika tidak.
     * @return komponen yang akan ditampilkan.
     */
    public Component getListCellRendererComponent(JList list, Object value, int index,
        boolean selected, boolean expanded) {
      JLabel label = new JLabel();
      label.setText(((Repository) value).generateHtmlText());
      label.setBorder(BorderFactory.createRaisedBevelBorder());
      label.setBackground(Color.WHITE);
      label.setOpaque(true);
      return label;
    }
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
