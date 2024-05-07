import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gui {
    private JFrame frame;
    private JTextField startWordField;
    private JTextField goalWordField;
    private JComboBox<String> algorithmComboBox;
    private JButton solveButton;
    private JTextArea resultArea;

    public Gui() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Word Ladder Solver");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel startWordLabel = new JLabel("Start Word:");
        startWordLabel.setBounds(20, 30, 80, 20);
        frame.getContentPane().add(startWordLabel);

        startWordField = new JTextField();
        startWordField.setBounds(110, 30, 200, 20);
        frame.getContentPane().add(startWordField);

        JLabel goalWordLabel = new JLabel("Goal Word:");
        goalWordLabel.setBounds(20, 60, 80, 20);
        frame.getContentPane().add(goalWordLabel);

        goalWordField = new JTextField();
        goalWordField.setBounds(110, 60, 200, 20);
        frame.getContentPane().add(goalWordField);

        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmLabel.setBounds(20, 90, 80, 20);
        frame.getContentPane().add(algorithmLabel);

        algorithmComboBox = new JComboBox<>(new String[]{"Uniform Cost Search", "Greedy Best-First Search", "A*"});
        algorithmComboBox.setBounds(110, 90, 200, 20);
        frame.getContentPane().add(algorithmComboBox);

        solveButton = new JButton("Solve");
        solveButton.setBounds(110, 120, 80, 30);
        frame.getContentPane().add(solveButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(20, 160, 400, 100);
        frame.getContentPane().add(scrollPane);

        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solveWordLadder();
            }
        });
    }

    private void solveWordLadder() {
        String startWord = startWordField.getText();
        String goalWord = goalWordField.getText();
        int type = algorithmComboBox.getSelectedIndex() + 1; // Index starts from 0

        if (startWord.isEmpty() || goalWord.isEmpty()) {
            resultArea.setText("Start Word and Goal Word cannot be empty!");
            return;
        }

        if (startWord.length() != goalWord.length()) {
            resultArea.setText("Start Word and Goal Word must have the same length!");
            return;
        }

        Map<String, Boolean> dictionary = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/dictionary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    dictionary.put(line, true);
                }
            }

            if (!dictionary.containsKey(startWord) || !dictionary.containsKey(goalWord)) {
                resultArea.setText("Start Word or Goal Word not found in dictionary!");
                return;
            }

            Solver wordLadderSolver = switch (type) {
                case 1 -> new UniformCostSearch(dictionary);
                case 2 -> new GreedyBestFirstSearch(dictionary);
                default -> {
                    assert type == 3;
                    yield new AStar(dictionary);
                }
            };
            wordLadderSolver.solve(startWord, goalWord);
            List<String> path = wordLadderSolver.getPath();
            if (path != null && !path.isEmpty()) {
                visualizePath(path);
            }
            int solLength = wordLadderSolver.getSolutionLength();
            if (solLength == 0) {
                resultArea.setText("No Solution Found!\n"+
                        "Total visited nodes: " + wordLadderSolver.getTotalVisitedNodes() + "\n" +
                        "Time Execution: " + wordLadderSolver.getTimeExec() + " milliseconds.");
            } else {
                resultArea.setText("Solution length: " + solLength + "\n" +
                        "Total visited nodes: " + wordLadderSolver.getTotalVisitedNodes() + "\n" +
                        "Time Execution: " + wordLadderSolver.getTimeExec() + " milliseconds.");
            }
        } catch (IOException ex) {
            resultArea.setText("Error: " + ex.getMessage() + "\n" + "Invalid input, Fail to execute the program!");
        } catch (Exception ex) {
            resultArea.setText("Error: " + ex.getMessage());
        }
    }

    private void visualizePath(List<String> path) {
        String[] columnNames = new String[path.get(0).length()]; // Assuming all words in the path have the same length
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = "Letter " + (i + 1); // Column headers like "Letter 1", "Letter 2", etc.
        }
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        String goalWord = path.get(path.size() - 1); // Assuming the last word in the path is the goal word

        for (String word : path) {
            char[] chars = word.toCharArray();
            char[] goalChars = goalWord.toCharArray();

            Object[] rowData = new Object[chars.length];
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c == goalChars[i]) {
                    rowData[i] = "<html><font color='green'>" + c + "</font></html>";
                } else {
                    rowData[i] = "<html><font color='red'>" + c + "</font></html>";
                }
            }
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(30); // Set row height
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set table header font
        table.setFont(new Font("Arial", Font.PLAIN, 12)); // Set table content font
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Set preferred size

        // Customizing table colors
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setSelectionBackground(Color.BLUE);
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(Color.BLACK);

        // Customizing scroll pane
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.setBackground(Color.LIGHT_GRAY);

        JOptionPane.showMessageDialog(null, scrollPane, "Word Ladder Path", JOptionPane.PLAIN_MESSAGE);
    }

    public void show() {
        frame.setVisible(true);
    }
}