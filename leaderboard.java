import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class leaderboard extends JDialog {

    public leaderboard(JFrame parent) {
        super(parent, "Leaderboard", true);
        ui();
    }

    private void ui() {
        setSize(360, 440);
        setLocationRelativeTo(getParent());
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        JPanel main = new JPanel();
        main.setBackground(Color.WHITE);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(30, 40, 30, 40));
        JLabel title = new JLabel("🏆 Leaderboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(20, 20, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub = new JLabel("Best scores (highest wins)");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        sub.setForeground(new Color(150, 150, 150));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(title);
        main.add(Box.createVerticalStrut(4));
        main.add(sub);
        main.add(Box.createVerticalStrut(24));
        List<String[]> list = scores.loadSortedEntries();
        if (list.isEmpty()) {
            JLabel empty = new JLabel("No scores yet. Play to get on the board!");
            empty.setFont(new Font("SansSerif", Font.PLAIN, 13));
            empty.setForeground(new Color(160, 160, 160));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            main.add(empty);
        } else {
            JPanel listPanel = new JPanel();
            listPanel.setBackground(Color.WHITE);
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            int limit = Math.min(list.size(), 10);
            String[] medals = {"🥇", "🥈", "🥉"};
            for (int i = 0; i < limit; i++) {
                String[] entry = list.get(i);
                String rank  = i < 3 ? medals[i] : (i + 1) + ".";
                String name  = entry[0];
                String score = entry[1] + " pts";
                JPanel row = row(rank, name, score, i % 2 == 0);
                listPanel.add(row);
                listPanel.add(Box.createVerticalStrut(4));
            }
            JScrollPane scroll = new JScrollPane(listPanel);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.setBackground(Color.WHITE);
            scroll.getViewport().setBackground(Color.WHITE);
            scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
            main.add(scroll);
        }
        main.add(Box.createVerticalStrut(20));
        JButton close = new JButton("Close");
        close.setFont(new Font("SansSerif", Font.PLAIN, 13));
        close.setForeground(new Color(60, 60, 60));
        close.setBackground(Color.WHITE);
        close.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        close.setFocusPainted(false);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        close.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(e -> dispose());
        main.add(close);
        add(main);
        setVisible(true);
    }

    private JPanel row(String rank, String name, String score, boolean shaded) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(shaded ? new Color(248, 248, 248) : Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(235, 235, 235), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        JLabel rankLabel = new JLabel(rank);
        rankLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        rankLabel.setForeground(new Color(80, 80, 80));
        rankLabel.setPreferredSize(new Dimension(36, 20));
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(20, 20, 20));
        JLabel scoreLabel = new JLabel(score);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        scoreLabel.setForeground(new Color(40, 40, 40));
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        p.add(rankLabel, BorderLayout.WEST);
        p.add(nameLabel, BorderLayout.CENTER);
        p.add(scoreLabel, BorderLayout.EAST);
        return p;
    }
}
