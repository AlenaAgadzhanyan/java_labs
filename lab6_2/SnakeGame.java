import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int CELL_SIZE = 20;
    private static final int INITIAL_SPEED = 200;
    private static final int SPEED_INCREMENT = 10;
    private static final int MAX_SPEED = 50;

    private JPanel gamePanel;
    private JLabel scoreLabel;
    private int score = 0;

    private LinkedList<Point> snake;
    private Point food;
    private Direction direction = Direction.RIGHT;
    private boolean isRunning = false;

    private Timer gameTimer;
    private int currentSpeed = INITIAL_SPEED;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public SnakeGame() {
        setTitle("Реактивный Змей");
        setSize(WIDTH, HEIGHT + 40); // +40 for the title bar and borders
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gamePanel = new GamePanel();
        scoreLabel = new JLabel("Счет: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(gamePanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.NORTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != Direction.DOWN) {
                            direction = Direction.UP;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != Direction.UP) {
                            direction = Direction.DOWN;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != Direction.RIGHT) {
                            direction = Direction.LEFT;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != Direction.LEFT) {
                            direction = Direction.RIGHT;
                        }
                        break;
                    case KeyEvent.VK_SPACE: // Пауза/Старт
                        togglePause();
                        break;
                }
            } // Закрывающая скобка, которой не было в предыдущей версии.
        });

        setFocusable(true);
        requestFocusInWindow();

        startGame();
    }

    private void startGame() {
        snake = new LinkedList<>();
        snake.add(new Point(WIDTH / 2 / CELL_SIZE, HEIGHT / 2 / CELL_SIZE));
        food = createFood();
        direction = Direction.RIGHT;
        isRunning = true;
        score = 0;
        scoreLabel.setText("Счет: 0");
        currentSpeed = INITIAL_SPEED;
        createGameTimer(currentSpeed);
        gameTimer.start();

        Thread speedThread = new Thread(() -> {
            while (isRunning) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                synchronized (this) {
                    if (currentSpeed > MAX_SPEED && isRunning) {
                        currentSpeed -= SPEED_INCREMENT;
                        gameTimer.stop();
                        createGameTimer(currentSpeed);
                        gameTimer.start();
                        System.out.println("Скорость увеличена: " + currentSpeed);
                    }
                }
            }
        });
        speedThread.start();
    }

    private void createGameTimer(int speed) {
        gameTimer = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    move();
                    checkCollision();
                    checkFood();
                    gamePanel.repaint();
                }
            }
        });
    }

    private void togglePause() {
        if (isRunning) {
            isRunning = false;
            gameTimer.stop();
        } else {
            isRunning = true;
            gameTimer.start();
        }
        requestFocusInWindow();
    }

    private void move() {
        Point head = snake.getFirst();
        Point newHead = null;

        switch (direction) {
            case UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
        }

        snake.addFirst(newHead);

        if (!newHead.equals(food)) {
            snake.removeLast();
        } else {
            food = createFood();
            score += 10;
            scoreLabel.setText("Счет: " + score);
        }
    }

    private void checkCollision() {
        Point head = snake.getFirst();

        // Check for wall collisions
        if (head.x < 0 || head.x >= WIDTH / CELL_SIZE || head.y < 0 || head.y >= HEIGHT / CELL_SIZE) {
            gameOver();
            return;
        }

        // Check for self-collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver();
                return;
            }
        }
    }

    private void checkFood() {
        Point head = snake.getFirst();
        if (head.equals(food)) {
            food = createFood();
            score += 10;
            scoreLabel.setText("Счет: " + score);
        }
    }

   private Point createFood() {
        Random random = new Random();
        int maxX = WIDTH / CELL_SIZE;
        int maxY = HEIGHT / CELL_SIZE;
        Point newFood;
        do {
            newFood = new Point(random.nextInt(maxX), random.nextInt(maxY));
        } while (snake.contains(newFood));

        return newFood;
    }


    private void gameOver() {
        isRunning = false;
        gameTimer.stop();
        JOptionPane.showMessageDialog(this, "Игра окончена! Ваш счет: " + score);
        startGame();
    }

    class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

        private void draw(Graphics g) {
            // Fill background
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            // Draw snake
            g.setColor(Color.GREEN);
            for (Point point : snake) {
                g.fillRect(point.x * CELL_SIZE, point.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            // Draw food
            g.setColor(Color.RED);
            g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }
}