import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {
    public static void main(String[] args) {
        Server a = new Server();
        DataOutputStream out = null;
        DataInputStream in = null;
        Socket you = null;
        ServerSocket server = null;
        while (true) {
            try {
                server = new ServerSocket(6666);
            } catch (IOException e1) {
                System.out.println("正在监听");
            }
            try {
                you = server.accept();
            } catch (IOException e) {
            }
            if (you != null) {
                Socket t = you;
                while (true) {
                    String s = null;
                    try {
                        out = new DataOutputStream(t.getOutputStream());
                        in = new DataInputStream(t.getInputStream());
                        s = in.readUTF();
                        if (s.startsWith("1022")) {
                            a.textField.setBackground(Color.green);
                            s = in.readUTF();
                            if (s.startsWith("离线1")) {
                                a.textField.setBackground(Color.red);
                                break;
                            } else
                                break;
                        }
                        if (s.startsWith("1023")) {
                            a.textField1.setBackground(Color.green);
                            s = in.readUTF();
                            if (s.startsWith("离线2")) {
                                a.textField1.setBackground(Color.red);
                                break;
                            } else
                                break;
                        }
                        if (s.startsWith("1024")) {
                            a.textField2.setBackground(Color.green);
                            s = in.readUTF();
                            if (s.startsWith("离线3")) {
                                a.textField2.setBackground(Color.red);
                                break;
                            } else
                                break;
                        }
                    } catch (IOException ee) {
                        try {
                            t.close();
                        } catch (IOException eee) {
                        }
                        break;
                    }
                }
            } else {
                continue;
            }
        }
    }
    JFrame frame;
    JTextField textField;
    JTextField textField1;
    JTextField textField2;
    JPanel contentPane;
    public Server(){
        setTitle("网络监听");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JButton button = new JButton("A");
        button.setBounds(57,28,73,72);
        contentPane.add(button);
        JButton button1 = new JButton("B");
        button1.setBounds(190,28,73,72);
        contentPane.add(button1);
        JButton button2 = new JButton("C");
        button2.setBounds(326,28,73,72);
        contentPane.add(button2);
        textField = new JTextField();
        textField.setBounds(84,166,15,14);
        contentPane.add(textField);
        textField.setColumns(10);
        JLabel label = new JLabel("在线状态");
        label.setFont(new Font("黑体",Font.PLAIN,18));
        label.setBounds(10,123,86,22);
        contentPane.add(label);
        textField1 = new JTextField();
        textField1.setBackground(Color.RED);
        textField1.setBounds(221,166,15,14);
        contentPane.add(textField1);
        textField1.setColumns(10);
        textField2 = new JTextField();
        textField2.setBackground(Color.RED);
        textField2.setBounds(357,166,15,14);
        contentPane.add(textField2);
        textField2.setColumns(10);
        setVisible(true);
    }
}
