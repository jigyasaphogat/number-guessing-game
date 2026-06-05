import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class game extends JFrame implements WindowListener, KeyListener {
    private String name;
    private int num;
    private int tries;
    private final int max = 7;
    private startscreen start;
    private CardLayout view;
    private JPanel body;
    private JLabel triesLabel;
    private JLabel msg;
    private JTextField input;
    private JLabel endTitle;
    private JLabel endSub;
    private List<String> guessHistory;
    private JLabel historyLabel;
    private int lastGuess;
    public game(String name, startscreen start) {
        this.name = name;
        this.start = start;
        reset();
        ui();
    }

    private void reset() {
        num = (int) (Math.random() * 100) + 1;
        tries = 0;
        guessHistory = new ArrayList<>();
        lastGuess = -1;
    }

    private int score() {
        return Math.max(0, max * 10 - (tries - 1) * 10);
    }

    private void ui() {
        setTitle("Number Guessing Game — " + name);
        setSize(420, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(this);
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(new EmptyBorder(10, 10, 0, 10));
        JButton lbBtn = iconBtn("🏆 Leaderboard");
        lbBtn.addActionListener(e -> new leaderboard(this));
        topBar.add(lbBtn);
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.WHITE);
        view = new CardLayout();
        body = new JPanel(view);
        body.setBackground(Color.WHITE);
        body.add(gamePanel(), "game");
        body.add(endPanel(), "end");
        main.add(body, BorderLayout.CENTER);
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.add(topBar, BorderLayout.NORTH);
        root.add(main, BorderLayout.CENTER);
        add(root);
        setVisible(true);
        input.requestFocusInWindow();
    }

    private JPanel gamePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 60, 40, 60));
        JLabel greet = new JLabel("Hi, " + name + "!");
        greet.setFont(new Font("SansSerif", Font.PLAIN, 13));
        greet.setForeground(new Color(130, 130, 130));
        greet.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel header = new JLabel("Guess my secret number");
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.setForeground(new Color(20, 20, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub = new JLabel("Input your guess (1–100) below");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        sub.setForeground(new Color(130, 130, 130));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        triesLabel = new JLabel(triesText());
        triesLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        triesLabel.setForeground(new Color(160, 160, 160));
        triesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        input = new JTextField();
        input.setFont(new Font("SansSerif", Font.PLAIN, 22));
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));
        input.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        input.setBackground(Color.WHITE);
        input.setForeground(new Color(20, 20, 20));
        input.addKeyListener(this);
        msg = new JLabel(" ");
        msg.setFont(new Font("SansSerif", Font.PLAIN, 14));
        msg.setForeground(new Color(80, 80, 80));
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyLabel = new JLabel("Guesses: None");
        historyLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        historyLabel.setForeground(new Color(140, 140, 140));
        historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton guessBtn = new JButton("Submit Guess");
        guessBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        guessBtn.setBackground(new Color(30, 30, 30));
        guessBtn.setForeground(Color.WHITE);
        guessBtn.setFocusPainted(false);
        guessBtn.setBorderPainted(false);
        guessBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guessBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        guessBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        input.addActionListener(e -> submit());
        guessBtn.addActionListener(e -> submit());
        panel.add(greet);
        panel.add(Box.createVerticalStrut(8));
        panel.add(header);
        panel.add(Box.createVerticalStrut(6));
        panel.add(sub);
        panel.add(Box.createVerticalStrut(20));
        panel.add(triesLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(input);
        panel.add(Box.createVerticalStrut(12));
        panel.add(msg);
        panel.add(Box.createVerticalStrut(6));
        panel.add(historyLabel);
        panel.add(Box.createVerticalStrut(16));
        panel.add(guessBtn);
        return panel;
    }

    private JPanel endPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(90, 60, 80, 60));
        endTitle = new JLabel(" ");
        endTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        endTitle.setForeground(new Color(20, 20, 20));
        endTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        endSub = new JLabel(" ");
        endSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        endSub.setForeground(new Color(130, 130, 130));
        endSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton retry = new JButton("Play Again");
        retry.setFont(new Font("SansSerif", Font.PLAIN, 13));
        retry.setForeground(new Color(60, 60, 60));
        retry.setBackground(Color.WHITE);
        retry.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        retry.setFocusPainted(false);
        retry.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retry.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        retry.setAlignmentX(Component.CENTER_ALIGNMENT);
        retry.addActionListener(e -> restart());
        panel.add(endTitle);
        panel.add(Box.createVerticalStrut(8));
        panel.add(endSub);
        panel.add(Box.createVerticalStrut(28));
        panel.add(retry);
        return panel;
    }

    private void submit() {
        String txt = input.getText().trim();
        if (txt.isEmpty()) {
            msg.setText("Please enter a number.");
            msg.setForeground(new Color(180, 60, 60));
            return;
        }
        int guess;
        try {
            guess = Integer.parseInt(txt);
        } catch (NumberFormatException ex) {
            msg.setText("That's not a valid number.");
            msg.setForeground(new Color(180, 60, 60));
            return;
        }
        if (guess < 1 || guess > 100) {
            msg.setText("Please guess between 1 and 100.");
            msg.setForeground(new Color(180, 60, 60));
            input.setText("");
            return;
        }
        // Check duplicate guess
        boolean alreadyGuessed = false;
        for (String h : guessHistory) {
            if (h.startsWith(guess + "↑") || h.startsWith(guess + "↓")) {
                alreadyGuessed = true;
                break;
            }
        }
        if (alreadyGuessed) {
            msg.setText("You already guessed " + guess + "!");
            msg.setForeground(new Color(180, 60, 60));
            input.setText("");
            input.requestFocusInWindow();
            return;
        }
        tries++;
        input.setText("");
        if (guess == num) {
            finish(true);
            return;
        }
        String direction = guess < num ? "↑" : "↓";
        guessHistory.add(guess + direction);
        historyLabel.setText("Guesses: " + String.join(", ", guessHistory));
        if (tries >= max) {
            finish(false);
            return;
        }
        // Update border color based on proximity
        if (lastGuess == -1) {
            setInputBorderColor(new Color(210, 210, 210), 1);
        } else {
            int prevDiff = Math.abs(lastGuess - num);
            int currDiff = Math.abs(guess - num);
            if (currDiff < prevDiff) {
                setInputBorderColor(new Color(230, 120, 40), 2); // Orange/Red (Warmer)
            } else if (currDiff > prevDiff) {
                setInputBorderColor(new Color(80, 140, 210), 2); // Blue (Colder)
            }
        }

        if (guess < num) {
            if (lastGuess == -1) {
                msg.setText("Too low! Try higher ↑");
            } else if (lastGuess < num) {
                if (guess > lastGuess) {
                    msg.setText("Getting warmer! Still too low ↑");
                } else {
                    msg.setText("Getting colder! Still too low ↑");
                }
            } else {
                msg.setText("Now you're too low! Try higher ↑");
            }
            msg.setForeground(new Color(60, 110, 190));
        } else {
            if (lastGuess == -1) {
                msg.setText("Too high! Try lower ↓");
            } else if (lastGuess > num) {
                if (guess < lastGuess) {
                    msg.setText("Getting warmer! Still too high ↓");
                } else {
                    msg.setText("Getting colder! Still too high ↓");
                }
            } else {
                msg.setText("Now you're too high! Try lower ↓");
            }
            msg.setForeground(new Color(200, 100, 30));
        }
        lastGuess = guess;
        triesLabel.setText(triesText());
        input.requestFocusInWindow();
    }

    private void finish(boolean win) {
        int s = win ? score() : 0;
        if (win) {
            endTitle.setText("You guessed it right!");
            endTitle.setForeground(new Color(30, 140, 80));
            setInputBorderColor(new Color(30, 140, 80), 2); // Green border
        } else {
            endTitle.setText("Game over");
            endTitle.setForeground(new Color(180, 60, 60));
            setInputBorderColor(new Color(180, 60, 60), 2); // Red border
        }
        endSub.setText("The number was " + num + ". Score: " + s + " pts");
        scores.saveScore(name, s);
        view.show(body, "end");
    }

    private void restart() {
        reset();
        msg.setText(" ");
        msg.setForeground(new Color(80, 80, 80));
        historyLabel.setText("Guesses: None");
        setInputBorderColor(new Color(210, 210, 210), 1); // Reset to default border
        triesLabel.setText(triesText());
        input.setText("");
        view.show(body, "game");
        input.requestFocusInWindow();
    }

    private String triesText() {
        int left = max - tries;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            if (i < left) {
                sb.append("● ");
            } else {
                sb.append("○ ");
            }
        }
        return sb.toString().trim() + " (" + left + " left)";
    }

    private void setInputBorderColor(Color color, int thickness) {
        input.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, thickness, true),
            BorderFactory.createEmptyBorder(7 - thickness, 13 - thickness, 7 - thickness, 13 - thickness)
        ));
    }

    private JButton iconBtn(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btn.setForeground(new Color(80, 80, 80));
        btn.setBackground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosing(WindowEvent e) {
        dispose();
        start.setVisible(true);
    }
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}

    @Override public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        String current = input.getText();
        if (!Character.isDigit(c) || current.length() >= 3) {
            e.consume();
        }
    }
    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
