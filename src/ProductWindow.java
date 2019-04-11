import javax.swing.*;
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
    private JTextArea description = new JTextArea();
    private JSpinner number;
    private JTextField price = new JTextField();
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
        this.setSize(500, 300);
        this.setResizable(false);
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        description.setFont(font);
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setAlignmentX(CENTER_ALIGNMENT);
        description.setFont(font);
        description.setBackground(gray);
        description.setForeground(black);
        description.setAlignmentX(CENTER_ALIGNMENT);
        description.setAlignmentY(CENTER_ALIGNMENT);
        description.setMinimumSize(new Dimension(0, 50));
        description.setPreferredSize(new Dimension(0, 50));
        description.setMaximumSize(new Dimension(0, 50));
        if (admin) {
            description.setEditable(true);
            price.setEditable(true);
            SpinnerNumberModel spm = new SpinnerNumberModel();
            spm.setMinimum(0);
            number = new JSpinner(spm);
            button = new JButton("Save changes");
        } else {
            //setting description field
            description.setEditable(false);
            description.setText(current.getName());
            //setting price field
            price.setEditable(false);
            price.setText("" + current.getPrice());
            //setting spinner
            number = new JSpinner(new SpinnerNumberModel(0, 0, current.getAmount(), 1));
            button = new JButton("Add to bag");
        }
        //button listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (admin) {
                    current.setName(description.getText());
                    current.setPrice(Integer.valueOf(price.getText()));
                    current.setAmount((Integer) number.getValue());

                } else {
                }
            }
        });
        //Localization of textArea
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 10, 10, 20);
        c.fill = GridBagConstraints.BOTH;
        this.add(description, c);
        text = new JLabel("Price:");
        text.setFont(font);
        text.setBackground(darkgray);
        text.setForeground(white);
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        //localization of label "Price"
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.25;
        this.add(text, c);
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
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.75;
        this.

                add(price, c);

        text = new

                JLabel("Amount:");
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setFont(font);
        text.setBackground(darkgray);
        text.setForeground(white);
        //localization of label "Amount"
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.25;
        this.

                add(text, c);
        number.setFont(font);
        number.getEditor().

                getComponent(0).

                setBackground(darkgray);
        number.getEditor().

                getComponent(0).

                setForeground(white);
        number.setBorder(BorderFactory.createLineBorder(black, 4));
        number.setMinimumSize(new

                Dimension(100, 30));
        number.setPreferredSize(new

                Dimension(100, 30));
        number.setMaximumSize(new

                Dimension(100, 30));
        //localization of spinner
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.75;
        this.

                add(number, c);
        button.setFont(font);
        button.setBackground(darkgray);
        button.setForeground(white);
        button.setBorder(BorderFactory.createLineBorder(black, 4));
        //localization of the button
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new

                Insets(20, 10, 10, 10);

        c.fill = GridBagConstraints.BOTH;
        this.

                add(button, c);
        this.

                update(this.getGraphics());
    }

    void setProduct(Product current) {
        this.current = current;
    }

    void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static void main(String[] args) {
        new ProductWindow("product");
    }
}
