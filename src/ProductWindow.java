import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class ProductWindow extends JFrame {
    private Department dep;
    private Product current;
    private JLabel text;
    private Font font;
    private Color black = new Color(30, 28, 31);
    private Color darkgray = new Color(86, 86, 86);
    private Color gray = new Color(155, 155, 155);
    private Color white = new Color(213, 213, 213);
    private JButton button;
    private JSpinner number;
    private JTextField price = new JTextField();
    private JTextField nameF = new JTextField();
    private JTextField total = new JTextField();
    private JTextPane description = new JTextPane();

    ProductWindow(String name, boolean admin, boolean newP, Product current) {
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
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.getContentPane().setBackground(darkgray);
        this.setSize(600, 400);
        this.getContentPane().setLayout(new GridBagLayout());
//        this.current = current;
        this.dep = current.getDepartment();
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
        nameF.setText(current.getName());
        nameF.setHorizontalAlignment(SwingConstants.CENTER);
        nameF.setFont(font);
        nameF.setBackground(darkgray);
        nameF.setForeground(white);
        nameF.setBorder(BorderFactory.createLineBorder(black, 4));
        nameF.setMinimumSize(new Dimension(100, 30));
        nameF.setPreferredSize(new Dimension(100, 30));
        //localization of price field
        {
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 0;
                        c.weighty = 0.1;
            c.insets = new Insets(0, 10, 0, 0);
        }
        this.add(nameF, c);
        Icon def = new ImageIcon(current.getImage());
        if (admin) {
            nameF.setEditable(true);
            price.setEditable(true);
            description.setEditable(true);
            SpinnerNumberModel spm = new SpinnerNumberModel();
            spm.setMinimum(0);
            number = new JSpinner(spm);
            //adding image on button
            JButton image = new JButton(def);
            image.setMinimumSize(new Dimension(160, 160));
            image.setPreferredSize(new Dimension(160, 160));
            image.setMaximumSize(new Dimension(160, 160));
            buttonAppearanceSetting(image);
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
                            current.setImage("pics\\" + fileopen.getSelectedFile().getName());
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
                c.gridheight = 4;
                c.gridwidth = 1;
                c.gridx = 2;
                c.gridy = 0;
                c.weightx = 0.25;
            }
            this.add(image, c);
            if (!newP) {
                JButton del = new JButton("Delete Product");
                buttonAppearanceSetting(del);
                del.setHorizontalAlignment(SwingConstants.CENTER);
                del.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int response = JOptionPane.showConfirmDialog(null,
                                "Delete product \"" + current.getName() + "\"?", "Deleting product", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            dep.remove(current);
                            dispose();
                        }
                    }
                });
                //localization of button delete
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(20, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 2;
                    c.gridy = 5;
                    c.weightx = 0.25;
                }
                this.add(del, c);
            }
            button = new JButton("Save changes");
        } else {
            //setting description field
            nameF.setEditable(false);
            //setting price field
            price.setEditable(false);
            //setting spinner
            number = new JSpinner(new SpinnerNumberModel(0, 0, current.getAmount(), 1));
            button = new JButton("Add to cart");
            //setting description
            description.setEditable(false);
            //setting image label
            text = new JLabel(def);
            text.setMinimumSize(new Dimension(160, 160));
            text.setPreferredSize(new Dimension(160, 160));
            text.setMaximumSize(new Dimension(160, 160));
            text.setFont(font);
            text.setBackground(darkgray);
            text.setForeground(white);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.NONE;
                c.gridheight = 4;
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
                    if (nameF.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Enter name!");
                    else if (price.getText().isEmpty() || price.getText().equals("0"))
                        JOptionPane.showMessageDialog(null, "Enter price!");
                    else if ((Integer) number.getValue() == 0) JOptionPane.showMessageDialog(null, "Enter amount!");
                    else if (!StoreWindow.s.checkUniqueProduct(nameF.getText()) && !nameF.getText().equals(current.getName()))
                        JOptionPane.showMessageDialog(null, "Product " + nameF.getText() + " is already exists!");
                    else {
                        current.edit(nameF.getText(), Integer.valueOf(price.getText()), (Integer) number.getValue(), dep, current.getImage(), description.getText());
                        dep.add(current);
                        dispose();
                    }
                } else {
                    current.setAmount(current.getAmount() - (Integer) number.getValue());
                    for (int i = 0; i < StoreWindow.s.getCart().size(); i++) {
                        if (current.equals(StoreWindow.s.getCart().get(i).getName())) {
                            StoreWindow.s.cart.get(i).setAmount(StoreWindow.s.getCart().get(i).getAmount() + (Integer) number.getValue());
                            return;
                        }
                    }
                    if ((Integer) number.getValue() != 0) {
                        StoreWindow.s.addToCart(new Product(current.getName(), current.getPrice(), (Integer) number.getValue(), dep, current.getImage(), description.getText()));
                        dispose();
                    }else JOptionPane.showMessageDialog(null, "Enter amount", "Warning", JOptionPane.WARNING_MESSAGE);
                }
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
        price.setText("" + current.getPrice());
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
        number.setValue(current.getAmount());
        number.setFont(font);
        number.getEditor().getComponent(0).setBackground(darkgray);
        number.getEditor().getComponent(0).setForeground(white);
        number.setBorder(BorderFactory.createLineBorder(black, 4));
        number.setMinimumSize(new Dimension(100, 30));
        number.setPreferredSize(new Dimension(100, 30));
        number.setMaximumSize(new Dimension(100, 30));
        number.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                total.setText("" + Integer.valueOf(price.getText()) * (Integer) number.getValue());
            }
        });
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
        text = new JLabel("Total:");
        text.setFont(font);
        text.setBackground(darkgray);
        text.setForeground(white);
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        //localization of label "Total"
        {
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 3;
            c.weightx = 0.25;
            c.weighty = 0.1;
        }
        this.add(text, c);
        total.setText("" + Integer.valueOf(price.getText()) * (Integer) number.getValue());
        total.setFont(font);
        total.setBackground(darkgray);
        total.setForeground(white);
        total.setHorizontalAlignment(SwingConstants.RIGHT);
        total.setBorder(BorderFactory.createLineBorder(black, 4));
        total.setMinimumSize(new Dimension(100, 30));
        total.setPreferredSize(new Dimension(100, 30));
        total.setMaximumSize(new Dimension(100, 30));
        //localization of label counting total price
        {
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = 0.25;
            c.weighty = 0.1;
        }
        this.add(total, c);
        text = new JLabel("Description:");
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setFont(font);
        text.setBackground(darkgray);
        text.setForeground(white);
        //localization of label "Description"
        {
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 4;
            c.weightx = 0.25;
            c.weighty = 0.1;
        }
        this.add(text, c);
        description.setText(current.getDescription());
        description.setFont(font);
        description.setBackground(darkgray);
        description.setForeground(white);
        description.setBorder(BorderFactory.createLineBorder(black, 4));
        description.setMinimumSize(new Dimension(0, 50));
        description.setPreferredSize(new Dimension(0, 50));
        description.setMaximumSize(new Dimension(0, 50));
        //localization of text area with description
        {
            c.anchor = GridBagConstraints.WEST;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridheight = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridx = 1;
            c.gridy = 4;
            c.weighty = 0.2;
            c.insets = new Insets(0, 0, 0, 10);
        }
        this.add(new JScrollPane(description), c);
        buttonAppearanceSetting(button);
        button.setBorder(BorderFactory.createLineBorder(black, 4));
        //localization of the button
        {
            c.gridx = 0;
            c.gridy = 5;
            c.gridwidth = admin && !newP ? 2 : GridBagConstraints.REMAINDER;
            c.gridheight = 1;
            c.weightx = 0.0;
            c.weighty = 0.0;
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(20, 10, 10, 10);
        }
        this.add(button, c);
    }

    private void buttonAppearanceSetting(JButton button) {
        button.setFont(font);
        button.setBackground(darkgray);
        button.setForeground(white);
        button.setBorder(BorderFactory.createLineBorder(black, 4));
    }
}