import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFrameTask3 extends JFrame {
    private JPanel panel;

    public ButtonFrameTask3() {
        panel = new JPanel();
        
        class ColorAction implements ActionListener {
            private Color color;

            public ColorAction(Color color) {
                this.color = color;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(color);
            }
        }

        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");
        yellowButton.addActionListener(new ColorAction(Color.YELLOW));
        blueButton.addActionListener(new ColorAction(Color.BLUE));
        redButton.addActionListener(new ColorAction(Color.RED));

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        add(panel);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ButtonFrameTask3();
    }
}
