import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame implements ActionListener, KeyListener {

    JPanel contentPane; // 创建面板
    BackgroundPanel backgroundPanel; // 声明BackgroundPanel类对象
    JTextField textField; // 创建文本框对象
    JPasswordField passwordField; // 创建密码框对象
    Socket socket; // 声明负责连接到服务器的套接字对象
    DataOutputStream out = null; // 创建数据输出流对象并初始化为空
    DataInputStream in = null; // 创建数据输入流对象并初始化为空
    String name = null; // 定义String变量存储用户名
    String password = null; // 定义String变量存储密码
    JButton jButton1 = new JButton("登录"); // 创建登录按钮
    JButton jButton2 = new JButton("离线"); // 创建离线按钮
    JLabel label2 = new JLabel(""); // 新建标签用来显示登录是否成功的信息
    JButton button = new JButton("用户"); // 设置用户，设为按钮
    int flag = 0; // 判断登录是否成功的变量
    int user = 0; // 判断登录的用户是哪位

    class BackgroundPanel extends JPanel{
        Image image; // 声明Image对象
        public BackgroundPanel(Image image){
            this.image = image;
            this.setOpaque(true); // 设置控件不透明
        }
        public void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            // 设置图片
            graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public Client(){
        backgroundPanel = new BackgroundPanel((new ImageIcon("1.jpeg")).getImage());
        backgroundPanel.setBounds(0,0,450,300);
        setTitle("登录页面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel label = new JLabel("用户名");
        label.setFont(new Font("黑体", Font.PLAIN, 18));
        label.setBounds(71, 119, 58, 20);
        contentPane.add(label);
        JLabel label1 = new JLabel("密码");
        label1.setFont(new Font("黑体",Font.PLAIN,18));
//        label1.setForeground(Color.WHITE);
        label1.setBounds(71,162,58,20);
        contentPane.add(label1);
        label2.setBounds(296,56,100,27);
        contentPane.add(label2);
        textField = new JTextField();
        textField.setBounds(139,118,209,21);
        contentPane.add(textField);
        textField.setColumns(10);
        passwordField = new JPasswordField();
        passwordField.setBounds(139, 161, 209, 21);
        contentPane.add(passwordField);
        button.setBounds(180,10,100,90);
        contentPane.add(button);
        textField.addKeyListener(this);
        passwordField.addKeyListener(this);
        jButton1.setBounds(71,199,139,41);
        jButton1.addActionListener(this);
        contentPane.add(jButton1);
        jButton2.setBounds(264, 199, 139, 41);
        jButton2.addActionListener(this);
        contentPane.add(jButton2);
        contentPane.add(backgroundPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == jButton1) {
            String name = textField.getText();
            String password = passwordField.getText();
            if (name.equals("1022") && password.equals("1234")) {
                label2.setText("登录成功！");
                label2.setForeground(Color.BLACK);
                flag = 1;
                user = 1;
            } else if (name.equals("1023") && password.equals("1234")) {
                label2.setText("登录成功！");
                label2.setForeground(Color.BLACK);
                flag = 1;
                user = 2;
            } else if (name.equals("1024") && password.equals("1234")) {
                label2.setText("登录成功！");
                label2.setForeground(Color.BLACK);
                flag = 1;
                user = 3;
            } else {
                label2.setText("登录失败！");
                label2.setForeground(Color.BLACK);
                flag = 0;
                user = 0;
            }
            if (flag == 1) {
                try {
                    socket = new Socket("127.0.0.1", 6666);
                    // 建立连接到服务器的套接字对象
                    in = new DataInputStream(socket.getInputStream());
                    // 获得网络连接输入
                    out = new DataOutputStream(socket.getOutputStream());
                    // 获得网络连接输出
                    name = textField.getText();
                    // 从文本框获取用户名
                    out.writeUTF(name);
                } catch (IOException e) {
                }
            }
        }
        if (actionEvent.getSource() == jButton2) {
            if (user == 1) {
                try {
                    String s = new String("离线1");
                    out.writeUTF(s);
                } catch (Exception e) {
                }
            } else if (user == 2) {
                try {
                    String s = new String("离线2");
                    out.writeUTF(s);
                } catch (Exception e) {
                }
            } else if (user == 3) {
                try {
                    String s = new String("离线3");
                    out.writeUTF(s);
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == keyEvent.VK_ENTER){
            keyEvent.getComponent().transferFocus();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    public static void main(String[] args) {
        Client client = new Client();
    }
}
