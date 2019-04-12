import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class ProductWindow extends JFrame {
    private boolean admin = true;
    private Product current;
    private JButton button;
    private JSpinner number;
    private JTextField price = new JTextField();
    private JTextField nameF = new JTextField();
    private JLabel text;
    private Font font;
    private Color black = new Color(30, 28, 31);
    private Color darkgray = new Color(86, 86, 86);
    private Color gray = new Color(155, 155, 155);
    private Color white = new Color(213, 213, 213);


    ProductWindow(String name) {
        super(name);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("minecraft.ttf")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        font = new Font("minecraft", Font.PLAIN, 16);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.getContentPane().setBackground(darkgray);
        this.setSize(600, 300);
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        text = new JLabel("Name:");
        text.setFont(font);
        text.setBackground(darkgray);
        text.setForeground(white);
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        //localization of label "Name"
        {
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0.25;
            c.weighty = 0.1;
        }
        this.add(text, c);
        nameF.setHorizontalAlignment(SwingConstants.CENTER);
        nameF.setFont(font);
        nameF.setBackground(darkgray);
        nameF.setForeground(white);
        nameF.setBorder(BorderFactory.createLineBorder(black, 4));
        nameF.setMinimumSize(new Dimension(100, 30));
        //localization of price field
        {
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.25;
        }
        this.add(nameF, c);
        if (admin) {
            nameF.setEditable(true);
            price.setEditable(true);
            SpinnerNumberModel spm = new SpinnerNumberModel();
            spm.setMinimum(0);
            number = new JSpinner(spm);
            //adding image on button
            Icon def = new ImageIcon("pics\\torch.png");//TODO set default pic
            JButton image = new JButton(def);
            image.setMinimumSize(new Dimension(150, 150));
            image.setPreferredSize(new Dimension(150, 150));
            image.setMaximumSize(new Dimension(150, 150));
            image.setFont(font);
            image.setBackground(darkgray);
            image.setForeground(white);
            image.setHorizontalAlignment(SwingConstants.CENTER);
            image.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileopen = new JFileChooser("pics");
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png");
                    fileopen.setFileFilter(filter);
                    int ret = fileopen.showDialog(button, "Choose File");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        String filename = fileopen.getSelectedFile().getName();
                        String extension = filename.substring(filename.lastIndexOf(".") + 1);
                        if (!extension.equals("png")) JOptionPane.showMessageDialog(null, "Choose png file!");
                        else {
                            Icon icon = new ImageIcon(fileopen.getSelectedFile().getAbsolutePath());
                            image.setIcon(icon);
                        }
                    }
                }
            });
            //localization of button image
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.NONE;
                c.gridheight = 3;
                c.gridwidth = 1;
                c.gridx = 2;
                c.gridy = 0;
                c.weightx = 0.25;
            }
            this.add(image, c);
            button = new JButton("Save changes");
        } else {
            //setting description field
            nameF.setEditable(false);
            nameF.setText(current.getName());
            //setting price field
            price.setEditable(false);
            price.setText("" + current.getPrice());
            //setting spinner
            number = new JSpinner(new SpinnerNumberModel(0, 0, current.getAmount(), 1));
            button = new JButton("Add to bag");
            //setting image label
            //Icon def = new ImageIcon("pics\\torch.png");//TODO set default pic
            //   text = new JLabel(def);
            text.setMinimumSize(new Dimension(150, 150));
            text.setPreferredSize(new Dimension(150, 150));
            text.setMaximumSize(new Dimension(150, 150));
            text.setFont(font);
            text.setBackground(darkgray);
            text.setForeground(white);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.NONE;
                c.gridheight = 3;
                c.gridwidth = 1;
                c.gridx = 2;
                c.gridy = 0;
                c.weightx = 0.25;
            }
            this.add(text, c);
        }
        //button listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (admin) {
                    if (price.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Enter price!");
                    else if (nameF.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Enter name!");
                    else
                        current = new Product(nameF.getText(), Integer.valueOf(price.getText()), (Integer) number.getValue());
                } else {
                    //TODO
                }
                dispose();
            }
        });
        text = new JLabel("Price:");
        text.setFont(font);
        text.setBackground(darkgray);
        text.setForeground(white);
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        //localization of label "Price"
        {
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.25;
            c.weighty = 0.1;
        }
        this.add(text, c);
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        price.setFont(font);
        price.setBackground(darkgray);
        price.setForeground(white);
        price.setBorder(BorderFactory.createLineBorder(black, 4));
        price.setMinimumSize(new Dimension(100, 30));
        price.setPreferredSize(new Dimension(100, 30));
        price.setMaximumSize(new Dimension(100, 30));
        price.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (!(Character.isDigit(c))) ke.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        //localization of price field
        {
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 0.25;
        }
        this.add(price, c);
        text = new JLabel("Amount:");
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setFont(font);
        text.setBackground(darkgray);
        text.setForeground(white);
        //localization of label "Amount"
        {
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 0.25;
            c.weighty = 0.1;
        }
        this.add(text, c);
        number.setFont(font);
        number.getEditor().getComponent(0).setBackground(darkgray);
        number.getEditor().getComponent(0).setForeground(white);
        number.setBorder(BorderFactory.createLineBorder(black, 4));
        number.setMinimumSize(new Dimension(100, 30));
        number.setPreferredSize(new Dimension(100, 30));
        number.setMaximumSize(new Dimension(100, 30));
        //localization of spinner
        {
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 0.25;
        }
        this.add(number, c);
        button.setFont(font);
        button.setBackground(darkgray);
        button.setForeground(white);
        button.setBorder(BorderFactory.createLineBorder(black, 4));
        //localization of the button
        {
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.weightx = 0.0;
            c.weighty = 0.0;
            c.anchor = GridBagConstraints.CENTER;
            c.insets = new Insets(20, 10, 10, 10);
            c.fill = GridBagConstraints.BOTH;
        }
        this.add(button, c);
    }

    public static void main(String[] args) {
        Product product = new Product("Torch", 4, 40);
        ProductWindow pr = new ProductWindow("product");
        pr.setResizable(false);
    }

    void setProduct(Product current) {
        this.current = current;
    }

    void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
