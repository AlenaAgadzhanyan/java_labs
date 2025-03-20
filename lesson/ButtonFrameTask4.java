import javax.swing.*;
import java.awt.*;

public class ButtonFrameTask4 extends JFrame {
    private JPanel panel;

    public ButtonFrameTask4() {
        panel = new JPanel();

        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");

        yellowButton.addActionListener(e -> panel.setBackground(Color.YELLOW));
        blueButton.addActionListener(e -> panel.setBackground(Color.BLUE));
        redButton.addActionListener(e -> panel.setBackground(Color.RED));

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        add(panel);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ButtonFrameTask4();
    }
}
