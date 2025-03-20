import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ButtonFrameTask5 extends JFrame {
    private JPanel panel;

    public ButtonFrameTask5() {
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
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    ButtonFrameTask5.this,
                    "Вы действительно хотите закрыть окно?",
                    "Подтверждение",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (response == JOptionPane.YES_OPTION) dispose(); 
                else panel.setBackground(Color.LIGHT_GRAY);
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new ButtonFrameTask5();
    }
}
