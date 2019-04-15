import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StoreWindow extends JFrame {
    private static ProductWindow pw;
    Store s = FileInput.readConfig("config.ttt");
    private static Department current;
    private boolean admin = true;
    private Font font;
    private Color black = new Color(30, 28, 31);
    private Color darkgray = new Color(86, 86, 86);
    private Color gray = new Color(155, 155, 155);
    private Color white = new Color(213, 213, 213);
    private JTextField find;
    private JLabel l;
    private JButton search;//button to search for product
    private JButton bag;//button to look in your bag

    StoreWindow(String name) {
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
        this.getContentPane().setBackground(darkgray);
        this.setSize(1000, 800);
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //label of the shop
        l = new JLabel("Logo:");
        l.setFont(font);
        l.setBackground(darkgray);
        l.setForeground(white);
        //localization of logo label
        {
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.BOTH;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 0.15;
            c.weighty = 0.2;
        }
        this.add(l, c);
        //search field
        find = new JTextField();
        find.setHorizontalAlignment(SwingConstants.LEFT);
        find.setFont(font);
        find.setBackground(darkgray);
        find.setForeground(white);
        find.setBorder(BorderFactory.createLineBorder(black, 4));
        find.setMinimumSize(new Dimension(100, 30));
        //localization of search field
        {
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.5;
        }
        this.add(find, c);
        //search button
        search = new JButton("Search");
        search.setFont(font);
        search.setBackground(darkgray);
        search.setForeground(white);
        search.setBorder(BorderFactory.createLineBorder(black, 4));
        search.setPreferredSize(new Dimension(120, 30));
        //localization of the button
        {
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 2;
            c.gridy = 0;
            c.weightx = 0.2;
        }
        this.add(search, c);
        if (admin) {
        } else {
        }
        //localization of side panel
        {
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.BOTH;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.15;
            c.weighty = 0.8;
        }
        add(new SidePanel(), c);
    }

    public static void main(String[] args) {
        StoreWindow sw = new StoreWindow("MINECRAFT IS MY LIFE");
        sw.setVisible(true);
    }

    static void addProduct(Product p) {
        current.add(p.name, p.price, p.amount);
    }

    void setAdmin(boolean admin) {
        this.admin = admin;
    }

    class ProductPane extends JPanel {
        Product[] products = (Product[]) current.getProducts().toArray();
        private JButton p;

        ProductPane() {
            super();

        }
    }

    class SidePanel extends JPanel {
        private JLabel pickDep;
        private JButton user;
        private JComboBox<Department> department;

        SidePanel() {
            super();
            this.setBackground(darkgray);
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            pickDep = new JLabel("Departments");
            pickDep.setFont(font);
            pickDep.setBackground(darkgray);
            pickDep.setForeground(white);
            pickDep.setBorder(BorderFactory.createLineBorder(black, 4));
            //Localization of label "Departments"
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridheight = 1;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 0;
                c.weighty = 0.2;
            }
            add(pickDep, c);
            //setting JComboBox
            department = new JComboBox<Department>();
            for (Department d : s.departments) department.addItem(d);
            department.setFont(font);
            department.setBackground(darkgray);
            department.setForeground(white);
            department.setBorder(BorderFactory.createLineBorder(black, 4));
            department.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current = (Department) department.getSelectedItem();
                }
            });
            //Localization of JComboBox with Departments
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridheight = 1;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 1;
                c.weighty = 0.;
            }
            add(department, c);
            if (admin) {
                JButton addD = new JButton("Add Department");
                addD.setFont(font);
                addD.setBackground(darkgray);
                addD.setForeground(white);
                addD.setBorder(BorderFactory.createLineBorder(black, 4));
                addD.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        s.add("Blocks   ");
                        department.addItem(s.getDepartment(s.departments.size() - 1));
                    }
                });
                //Localization of button "Add department"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 2;
                    c.weighty = 0.4;
                    c.insets = new Insets(40, 20, 40, 20);
                }
                add(addD, c);
                JButton addP = new JButton("Add product");
                addP.setFont(font);
                addP.setBackground(darkgray);
                addP.setForeground(white);
                addP.setBorder(BorderFactory.createLineBorder(black, 4));
                addP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pw = new ProductWindow("New Product");

                    }
                });
                //Localization of button "Add Product"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.weighty = 0.4;
                    c.insets = new Insets(40, 20, 40, 20);
                }
                add(addP, c);
                JButton save = new JButton("Save into file");
                save.setFont(font);
                save.setBackground(darkgray);
                save.setForeground(white);
                save.setBorder(BorderFactory.createLineBorder(black, 4));
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FileInput.saveFile(s.toString());
                    }
                });
                //Localization of button "Save to File"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 4;
                    c.weighty = 0.4;
                    c.insets = new Insets(40, 20, 40, 20);
                }
                add(save, c);
            } else {
                JButton cart = new JButton();
                Icon icon = new ImageIcon("pics\\minecart.png");
                cart.setIcon(icon);
                cart.setText("Add to cart");
                cart.setHorizontalTextPosition(SwingConstants.RIGHT);
                cart.setFont(font);
                cart.setBackground(darkgray);
                cart.setForeground(white);
                cart.setBorder(BorderFactory.createLineBorder(black, 4));
                cart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
                //Localization of button "Add to cart"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.weighty = 0.4;
                    c.insets = new Insets(10, 10, 10, 10);
                }
                add(cart, c);
            }
        }
    }
}

