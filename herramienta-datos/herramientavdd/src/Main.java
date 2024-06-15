import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextArea inputTextArea;
    private JLabel sentencesLabel;
    private JLabel wordsLabel;
    private JLabel lettersLabel;
    private JLabel numbersLabel;
    private JButton analyzeButton;

    public Main() {
        setTitle("HVD-DMOSORIO");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        inputTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        sentencesLabel = new JLabel("Número de oraciones: ?");
        wordsLabel = new JLabel("Número de palabras: ?");
        lettersLabel = new JLabel("Número de letras: ?");
        numbersLabel = new JLabel("Número de números: ?");

        sentencesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        wordsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lettersLabel.setFont(new Font("Arial", Font.BOLD, 14));
        numbersLabel.setFont(new Font("Arial", Font.BOLD, 14));

        analyzeButton = new JButton("Analizar");
        analyzeButton.setFont(new Font("Arial", Font.BOLD, 16));
        analyzeButton.setBackground(Color.pink);
        analyzeButton.setForeground(Color.WHITE);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputTextArea.getText();
                int sentences = countSentences(text);
                int words = countWords(text);
                int letters = countLetters(text);
                int numbers = countNumbers(text);

                sentencesLabel.setText("Número de oraciones: " + sentences);
                wordsLabel.setText("Número de palabras: " + words);
                lettersLabel.setText("Número de letras: " + letters);
                numbersLabel.setText("Número de números: " + numbers);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JScrollPane(inputTextArea));

        panel.add(sentencesLabel);
        panel.add(wordsLabel);
        panel.add(lettersLabel);
        panel.add(numbersLabel);
        panel.add(analyzeButton);

        add(panel);
    }

    private int countSentences(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (c == '.' || c == '!' || c == '?') {
                count++;
            }
        }
        return count;
    }

    private int countWords(String text) {
        int count = 0;
        boolean inWord = false;
        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }
        return count;
    }

    private int countLetters(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                count++;
            }
        }
        return count;
    }

    private int countNumbers(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (c >= '0' && c <= '9') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
