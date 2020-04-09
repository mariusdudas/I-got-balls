package IGotBalls;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    static private final int maxWidth = 800;
    static private final int maxHeight = 640;
    static JButton create, destroy;
    static private ArrayList<Ball> balls = new ArrayList<>();

    MyPanel() {
        setPreferredSize(new Dimension(maxWidth, maxHeight));
        setBackground(Color.black);
    }

    /**
     * The program will create a window wich contains a panel for Ball objects and two buttons
     * By each pressing of a button, a single ball will be created, and by each pressing of a second
     * button, always the newest ball will be deleted
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("I Got Balls!");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MyPanel panel = new MyPanel();
            window.add(panel);
            window.setLayout(new FlowLayout());
            create = new JButton("One more ball!");
            create.addActionListener(e -> {
                balls.add(new Ball(panel));
                balls.get(balls.size() - 1).start();
                if (!destroy.isEnabled()) destroy.setEnabled(true);
                window.repaint();
            });
            window.add(create);

            destroy = new JButton("Stop the ball!");
            destroy.setEnabled(false);
            destroy.addActionListener(e -> {
                balls.get(balls.size() - 1).stopTheBall();
                balls.remove(balls.size() - 1);
                if (balls.size() == 0) destroy.setEnabled(false);
                window.repaint();
            });
            window.add(destroy);

            window.pack();
            window.setVisible(true);
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Ball ball : balls) {
            g.setColor(ball.getColor());
            g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        }
    }
}
