import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MontyHallGame extends JFrame {
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField chosenDoorField;
    private JButton playButton;
    private JTextArea resultArea;

    private int chosenDoor;
    private int prizeDoor;
    private int emptyDoor;

    public MontyHallGame() {
        setTitle("Jogo Monty Hall");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar a janela
        setLayout(new GridLayout(5, 1));

        titleLabel = new JLabel("Bem-vindo ao Jogo Monty Hall!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);

        instructionLabel = new JLabel("Escolha uma porta (1, 2, 3):");
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        add(instructionLabel);

        // Redefinindo o tamanho preferido do campo de texto para torná-lo menor
        chosenDoorField = new JTextField();
        chosenDoorField.setHorizontalAlignment(JTextField.CENTER);
        chosenDoorField.setPreferredSize(new Dimension(100, 20));
        add(chosenDoorField);

        playButton = new JButton("JOGAR");
        playButton.addActionListener(new PlayButtonListener());
        add(playButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(resultArea);
    }

    private class PlayButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                chosenDoor = Integer.parseInt(chosenDoorField.getText());
                if (chosenDoor < 1 || chosenDoor > 3) {
                    JOptionPane.showMessageDialog(null, "Opção inválida! Escolha uma porta válida (1, 2, 3).");
                    return;
                }

                Random random = new Random();
                prizeDoor = random.nextInt(3) + 1;
                do {
                    emptyDoor = random.nextInt(3) + 1;
                } while (emptyDoor == chosenDoor || emptyDoor == prizeDoor);

                int option = JOptionPane.showConfirmDialog(null, "Você deseja trocar de porta?", "Escolha", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "A porta vazia é a " + emptyDoor);
                    do {
                        chosenDoor = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma nova porta (1, 2, 3):"));
                    } while (chosenDoor == emptyDoor || chosenDoor < 1 || chosenDoor > 3);
                }

                if (chosenDoor == prizeDoor) {
                    resultArea.setText("Você ganhou, parabéns!");
                } else {
                    resultArea.setText("Você perdeu.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Entrada inválida! Por favor, insira um número válido (1, 2, 3).");
            }
        }
    }


    public static void main(String[] args) {
        MontyHallGame game = new MontyHallGame();
        game.setVisible(true);
    }
}