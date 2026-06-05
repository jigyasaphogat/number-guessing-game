import java.io.*;
import java.util.*;

public class scores {

    private static final String FILE = "leaderboard.txt";

    public static void saveScore(String name, int score) {
        List<String[]> list = loadRaw();
        boolean found = false;
        for (String[] e : list) {
            if (e[0].equalsIgnoreCase(name)) {
                int old = Integer.parseInt(e[1]);
                if (score > old) {
                    e[1] = String.valueOf(score);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            list.add(new String[]{name, String.valueOf(score)});
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (String[] e : list) {
                writer.write(e[0] + "," + e[1]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Could not save score: " + e.getMessage());
        }
    }

    public static List<String[]> loadSortedEntries() {
        List<String[]> list = loadRaw();
        list.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));
        return list;
    }

    private static List<String[]> loadRaw() {
        List<String[]> list = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    try {
                        Integer.parseInt(parts[1].trim());
                        list.add(new String[]{parts[0].trim(), parts[1].trim()});
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read leaderboard: " + e.getMessage());
        }
        return list;
    }
}
