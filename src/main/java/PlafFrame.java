import javax.swing.*;

public class PlafFrame extends JFrame {
    private JPanel buttonPanel;

    public PlafFrame() {
//        setSize(600, 400);
        buttonPanel = new JPanel();

        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos) {
            makeButton(info.getName(), info.getClassName());
        }
        add(buttonPanel);
//        根据内容调整窗口大小
//        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new PlafFrame();
            frame.setTitle("ChangeLaF");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private void makeButton(String name, String className) {
        JButton button = new JButton(name);
        buttonPanel.add(button);

        button.addActionListener(event ->{
            try {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(this);
//                pack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
