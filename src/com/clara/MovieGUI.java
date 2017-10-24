package com.clara;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by we4954cp on 10/24/2017.
 */
public class MovieGUI extends JFrame {
    private JPanel mainPanel;
    private JSlider ratingSlider;
    private JLabel sliderValueLabel;
    private JCheckBox wouldSeeAgainCheckBox;
    private JTextField movieTitletextField;
    private JLabel movieOpinionLabel;
    private JButton quitButton;

    MovieGUI() {
        setTitle("Movie GUI");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 300));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ratingSlider.setMinimum(0);
        ratingSlider.setMaximum(5);

        String movieName = JOptionPane.showInputDialog(this, "Enter movie name");
        movieTitletextField.setText(movieName);

        updateOpinion();

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // System.exit(0); // 0 is no error
                int userResponse = JOptionPane.showConfirmDialog
                        (MovieGUI.this, "Exit?", "Quit?", JOptionPane.OK_CANCEL_OPTION);

                if (userResponse == JOptionPane.OK_OPTION) {
                    System.exit(0);
                } else {
                    // don't need to do anything.
                }

            }
        });

        ratingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ratingSlider.getValue();
                sliderValueLabel.setText(value + " stars");
                updateOpinion();
            }
        });

        wouldSeeAgainCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateOpinion();
            }
        });

        movieTitletextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateOpinion();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    public void updateOpinion() {
        // read values from components and create string with opinion
        boolean seeAgain = wouldSeeAgainCheckBox.isSelected();
        String movieName = movieTitletextField.getText();
        int rating = ratingSlider.getValue();
        String template = "You rated %s %d stars and you %s see again.";
        String opinion = String.format(template, movieName, rating, seeAgain ? "would" : "would not");

        movieOpinionLabel.setText(opinion);

    }
}
