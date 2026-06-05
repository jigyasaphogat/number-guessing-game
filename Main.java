import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private int secretNum;
    private int attempts;
    private final int maxAttempts = 7;
    private JTextField guessInput;
    private JLabel statusLabel;
    private JLabel attemptsLabel;
    private JButton submitButton;

    public Main() {
        secretNum = (int) (Math.random() * 100) + 1;
        attempts = 0;

        setTitle("Number Guessing Game");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        statusLabel = new JLabel("Guess a secret number between 1 and 100:");
        add(statusLabel);

        guessInput = new JTextField(10);
        add(guessInput);

        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        add(attemptsLabel);

        submitButton = new JButton("Submit Guess");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitGuess();
            }
        });
        add(submitButton);

        setVisible(true);
    }

    private void submitGuess() {
        String text = guessInput.getText().trim();
        if (text.isEmpty()) {
            statusLabel.setText("Enter a valid number!");
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            statusLabel.setText("Not a number!");
            return;
        }

        attempts++;
        int left = maxAttempts - attempts;
        attemptsLabel.setText("Attempts left: " + left);
        guessInput.setText("");

        if (guess == secretNum) {
            statusLabel.setText("Correct! You win.");
            submitButton.setEnabled(false);
        } else if (left <= 0) {
            statusLabel.setText("Game Over! The number was " + secretNum);
            submitButton.setEnabled(false);
        } else if (guess < secretNum) {
            statusLabel.setText("Too low! Try again.");
        } else {
            statusLabel.setText("Too high! Try again.");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
