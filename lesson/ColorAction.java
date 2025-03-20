import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorAction implements ActionListener {
    private JPanel panel;
    private Color color;

    public ColorAction(JPanel panel, Color color) {
        this.panel = panel;
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setBackground(color);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Color Changer");
        JPanel panel = new JPanel();
        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");

        ColorAction yellowAction = new ColorAction(panel, Color.YELLOW);
        ColorAction blueAction = new ColorAction(panel, Color.BLUE);
        ColorAction redAction = new ColorAction(panel, Color.RED);

        yellowButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
