import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class startscreen extends JFrame {

    private JTextField nameInput;
    private JLabel err;

    public startscreen() {
        ui();
    }

    private void ui() {
        setTitle("Number Guessing Game");
        setSize(420, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel main = new JPanel();
        main.setBackground(Color.WHITE);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(50, 60, 40, 60));
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(new EmptyBorder(10, 10, 0, 10));
        JButton lbBtn = iconBtn("🏆 Leaderboard");
        lbBtn.addActionListener(e -> new leaderboard(this));
        topBar.add(lbBtn);
        JLabel title = new JLabel("Number Guessing Game");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(20, 20, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subtitle = new JLabel("Guess right. Earn points. Beat the board.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(new Color(130, 130, 130));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nameLabel = new JLabel("Your name");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        nameLabel.setForeground(new Color(80, 80, 80));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameInput = new JTextField();
        nameInput.setFont(new Font("SansSerif", Font.PLAIN, 15));
        nameInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        nameInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        nameInput.setBackground(Color.WHITE);
        nameInput.setForeground(new Color(20, 20, 20));
        err = new JLabel(" ");
        err.setFont(new Font("SansSerif", Font.PLAIN, 12));
        err.setForeground(new Color(200, 60, 60));
        err.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton playBtn = new JButton("Play");
        playBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
        playBtn.setBackground(new Color(30, 30, 30));
        playBtn.setForeground(Color.WHITE);
        playBtn.setFocusPainted(false);
        playBtn.setBorderPainted(false);
        playBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        playBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameInput.addActionListener(e -> play());
        playBtn.addActionListener(e -> play());
        main.add(title);
        main.add(Box.createVerticalStrut(6));
        main.add(subtitle);
        main.add(Box.createVerticalStrut(36));
        main.add(nameLabel);
        main.add(Box.createVerticalStrut(8));
        main.add(nameInput);
        main.add(Box.createVerticalStrut(6));
        main.add(err);
        main.add(Box.createVerticalStrut(16));
        main.add(playBtn);
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Color.WHITE);
        root.add(topBar, BorderLayout.NORTH);
        root.add(main, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        JLabel footerLabel = new JLabel("🔗 GitHub: @jigyasaphogat");
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footerLabel.setForeground(new Color(140, 140, 140));
        footerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/jigyasaphogat"));
                } catch (Exception ex) {}
            }
        });
        footerPanel.add(footerLabel);
        root.add(footerPanel, BorderLayout.SOUTH);

        add(root);
        setVisible(true);
        nameInput.requestFocusInWindow();
    }

    private void play() {
        String name = nameInput.getText().trim();
        if (name.isEmpty()) {
            err.setText("Please enter your name to continue.");
            return;
        }
        if (name.length() > 20) {
            err.setText("Name must be 20 characters or less.");
            return;
        }
        setVisible(false);
        new game(name, this);
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
}
