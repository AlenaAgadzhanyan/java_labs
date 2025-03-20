import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFrameTask2 extends JFrame implements ActionListener {
    private JPanel panel;

    public ButtonFrameTask2() {
        panel = new JPanel();
        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");

        yellowButton.addActionListener(this);
        blueButton.addActionListener(this);
        redButton.addActionListener(this);

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        add(panel);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Yellow":
                panel.setBackground(Color.YELLOW);
                break;
            case "Blue":
                panel.setBackground(Color.BLUE);
                break;
            case "Red":
                panel.setBackground(Color.RED);
                break;
        }
    }

    public static void main(String[] args) {
        new ButtonFrameTask2();
    }
}
