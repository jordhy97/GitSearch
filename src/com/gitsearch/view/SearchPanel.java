package com.gitsearch.view;

import com.gitsearch.model.SearchQuery;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/*
 * Nama File          : SearchPanel.java
 * Tanggal dibuat     : 11/06/17
 * Tanggal perubahan  : 12/06/17
 */


/**
 * Kelas SearchPanel yang menangani tampilan panel pencarian.
 *
 * @author Jordhy Fernando
 */
public class SearchPanel extends JPanel{

  private JLabel logoLabel;
  private JTextField queryTextField;
  private JButton searchButton;

  private JLabel searchByLabel;
  private ButtonGroup searchByGroup;
  private JRadioButton emailRadioButton;
  private JRadioButton nameRadioButton;
  private JRadioButton usernameRadioButton;

  private JLabel filterLabel;
  private JCheckBox[] filtersCheckBox;
  private JComboBox[] filtersOptionsComboBox;
  private JSpinner[] filtersLowerLimitSpinner;
  private JLabel[] filtersAndLabel;
  private JSpinner[] filtersUpperLimitSpinner;

  private static final String[] FILTER_OPTIONS =
      {"between", "less than", "greater than", "minimum", "maximum", "equals"};

  /**
   * Menciptakan sebuah panel pencarian.
   */
  public SearchPanel() {
    createUIComponents();
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(3, 4, 3, 4);
    add(logoLabel, c);
    c.gridwidth = 10;
    add(queryTextField, c);
    c.gridwidth = 1;
    add(searchButton, c);

    c.gridy = 1;
    c.gridx = 1;
    c.gridwidth = 1;
    add(searchByLabel, c);
    c.gridx = 2;
    add(emailRadioButton, c);
    c.gridx = 3;
    add(nameRadioButton, c);
    c.gridx = 4;
    add(usernameRadioButton, c);

    c.gridy = 2;
    c.gridheight = 2;
    c.gridx = 1;
    add(filterLabel, c);

    c.gridheight = 1;
    for (int i = 0; i < filtersCheckBox.length; i++) {
      c.gridx = 2;
      add(filtersCheckBox[i], c);
      c.gridx = 3;
      add(filtersOptionsComboBox[i], c);
      c.gridx = 4;
      add(filtersLowerLimitSpinner[i], c);
      c.gridx = 5;
      add(filtersAndLabel[i], c);
      c.gridx = 6;
      add(filtersUpperLimitSpinner[i], c);
      c.gridy++;
    }

    setBackground(Color.WHITE);

  }

  /**
   * Menciptakan komponen-komponen yang terdapat pada panel pencarian.
   */
  private void createUIComponents() {
    logoLabel = new JLabel();
    ImageIcon logo = new ImageIcon("assets/logo.png");
    logoLabel.setIcon(logo);
    logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    logoLabel.setVisible(false);
    queryTextField = new JTextField();
    searchButton = new JButton("Search");
    searchByLabel = new JLabel("Search by:");
    createSearchByButtonGroup();
    filterLabel = new JLabel("Filter:");
    filterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    createFilters();
  }

  /**
   * Menciptakan kelompok tombol jenis pencarian.
   */
  private void createSearchByButtonGroup() {
    emailRadioButton = new JRadioButton("email", true);
    emailRadioButton.setOpaque(false);
    emailRadioButton.setActionCommand("email");
    nameRadioButton = new JRadioButton("name");
    nameRadioButton.setOpaque(false);
    nameRadioButton.setActionCommand("fullname");
    usernameRadioButton = new JRadioButton("username");
    usernameRadioButton.setOpaque(false);
    usernameRadioButton.setActionCommand("login");
    searchByGroup = new ButtonGroup();
    searchByGroup.add(emailRadioButton);
    searchByGroup.add(nameRadioButton);
    searchByGroup.add(usernameRadioButton);
  }

  /**
   * Menciptakan komponen-komponen filter.
   */
  private void createFilters() {
    filtersCheckBox = new JCheckBox[2];
    filtersOptionsComboBox = new JComboBox[2];
    filtersLowerLimitSpinner = new JSpinner[2];
    filtersAndLabel = new JLabel[2];
    filtersUpperLimitSpinner = new JSpinner[2];
    for (int i = 0; i < filtersCheckBox.length; i++) {
      if (i == 0) {
        filtersCheckBox[i] = new JCheckBox("Repositories");
      }
      else if (i == 1) {
        filtersCheckBox[i] = new JCheckBox("Followers");
      }
      filtersCheckBox[i].setOpaque(false);
      filtersOptionsComboBox[i] = new JComboBox(FILTER_OPTIONS);
      filtersOptionsComboBox[i].setBackground(Color.WHITE);
      filtersOptionsComboBox[i].setEnabled(false);
      filtersLowerLimitSpinner[i] =
          new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
      filtersLowerLimitSpinner[i].setPreferredSize(new Dimension(100, 24));
      filtersLowerLimitSpinner[i].setEnabled(false);
      filtersAndLabel[i] = new JLabel("and");
      filtersAndLabel[i].setEnabled(false);
      filtersUpperLimitSpinner[i] =
          new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
      filtersUpperLimitSpinner[i].setPreferredSize(new Dimension(100, 24));
      filtersUpperLimitSpinner[i].setEnabled(false);
    }
  }

  /**
   * Mengatur tampilan logo (ditampilkan atau tidak).
   * @param show true jika ingin ditampilkan dan false jika tidak.
   */
  public void showLogoLabel(boolean show) {
    logoLabel.setVisible(show);
  }

  /**
   * Memeriksa apakah filter pada posisi index digunakan atau tidak.
   * @param index indeks dari filter yang ingin diperiksa.
   * @return true jika filter pada posisi index digunakan dan false jika tidak.
   */
  public boolean isFilterUsed(int index) {
    return filtersCheckBox[index].isSelected();
  }

  /**
   * Mengaktifkan filter pada posisi index.
   * @param index indeks dari filter yang akan diaktifkan.
   */
  public void enableFilter(int index) {
    filtersOptionsComboBox[index].setEnabled(true);
    filtersLowerLimitSpinner[index].setEnabled(true);
    if (filtersOptionsComboBox[index].getSelectedItem().equals("between")) {
      filtersAndLabel[index].setEnabled(true);
      filtersUpperLimitSpinner[index].setEnabled(true);
    }
    else {
      filtersAndLabel[index].setEnabled(false);
      filtersUpperLimitSpinner[index].setEnabled(false);
    }
  }

  /**
   * Mematikan filter pada posisi index.
   * @param index indeks dari filter yang akan dimatikan.
   */
  public void disableFilter(int index) {
    filtersOptionsComboBox[index].setEnabled(false);
    filtersLowerLimitSpinner[index].setEnabled(false);
    filtersAndLabel[index].setEnabled(false);
    filtersUpperLimitSpinner[index].setEnabled(false);
  }

  /**
   * Menambahkan sebuah ActionListener pada filterCheckBox pada posisi index.
   * @param l ActionListener yang ingin ditambahkan.
   * @param index indeks dari filterCheckBox yang akan ditambahkan ActionListener.
   */
  public void addFilterCheckBoxListener(ActionListener l , int index) {
    filtersCheckBox[index].addActionListener(l);
  }

  /**
   * Menambahkan sebuah ActionListener pada filterOptionComboBox pada posisi index.
   * @param l ActionListener yang ingin ditambahkan.
   * @param index indeks dari filterOptionComboBox yang akan ditambahkan ActionListener.
   */
  public void addFilterOptionsComboBoxListener(ActionListener l , int index) {
    filtersOptionsComboBox[index].addActionListener(l);
  }

  /**
   * Menambahkan sebuah ActionListener pada tombol search.
   * @param l ActionListener yang ingin ditambahkan.
   */
  public void addSearchButtonListener(ActionListener l) {
    searchButton.addActionListener(l);
  }

  /**
   * Menambahkan sebuah KeyListener pada text field query.
   * @param l KeyListener yang ingin ditambahkan.
   */
  public void addQueryTextFieldKeyListener(KeyListener l) {
    queryTextField.addKeyListener(l);
  }

  /**
   * Menambahkan sebuah MouseListener pada label logo.
   * @param l MouseListener yang ingin ditambahkan.
   */
  public void addLogoLabelMouseListener(MouseListener l) {
    logoLabel.addMouseListener(l);
  }

  /**
   * Mengambil query pencarian yang dihasilkan oleh panel pencarian.
   * @return query pencarian berdasarkan nilai-nilai komponen panel pencarian.
   * @throws IOException  jika query pencarian yang dihasilkan tidak valid.
   */
  public SearchQuery getSearchQuery() throws IOException{
    SearchQuery query =
        new SearchQuery(queryTextField.getText(), searchByGroup.getSelection().getActionCommand());
    if (query.getQuery().equals("")) {
      throw new IOException("Cannot execute empty query");
    }
    for (int i = 0; i < 2; i++) {
      if (isFilterUsed(i)) {
        query.getFilters().get(i).setUsed(true);
        String limit;
        String chosenOption = (String) filtersOptionsComboBox[i].getSelectedItem();
        switch (chosenOption) {
          case "between":
            if ((Integer) filtersUpperLimitSpinner[i].getValue()
                < (Integer)filtersLowerLimitSpinner[i].getValue()) {
              throw new IOException("Lower bound of the filter cannot be greater than the upper bound");
            }
            limit = filtersLowerLimitSpinner[i].getValue() + ".." + filtersUpperLimitSpinner[i].getValue();
            break;
          case "less than":
            limit = "<" + filtersLowerLimitSpinner[i].getValue();
            break;
          case "greater than":
            limit = ">" + filtersLowerLimitSpinner[i].getValue();
            break;
          case "minimum":
            limit = "<=" + filtersLowerLimitSpinner[i].getValue();
            break;
          case "maximum":
            limit = ">=" + filtersLowerLimitSpinner[i].getValue();
            break;
          case "equals":
            limit = filtersLowerLimitSpinner[i].getValue().toString();
            break;
          default:
            limit = "";
        }
        query.getFilters().get(i).setLimit(limit);
      }
    }
    return query;
  }

  /**
   * Me-<i>reset</i> semua komponen pada panel pencarian.
   */
  public void resetPanel() {
    logoLabel.setVisible(false);
    queryTextField.setText("");
    emailRadioButton.setSelected(true);
    for (int i = 0; i < 2; i++) {
      filtersCheckBox[i].setSelected(false);
      filtersOptionsComboBox[i].setSelectedIndex(0);
      filtersOptionsComboBox[i].setEnabled(false);
      filtersLowerLimitSpinner[i].setValue(0);
      filtersLowerLimitSpinner[i].setEnabled(false);
      filtersAndLabel[i].setEnabled(false);
      filtersUpperLimitSpinner[i].setValue(0);
      filtersUpperLimitSpinner[i].setEnabled(false);
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
