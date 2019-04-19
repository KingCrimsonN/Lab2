import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StoreWindow extends JFrame {
    private static ProductWindow pw;

    public static Department getCurrent() {
        return current;
    }

    private static Department current;
    private static boolean admin = true;
    Store s = FileInput.readConfig("config.ttt");
    private Font font;
    private Color black = new Color(30, 28, 31);
    private Color darkgray = new Color(86, 86, 86);
    private Color gray = new Color(155, 155, 155);
    private Color white = new Color(213, 213, 213);
    private ProductPane pp = new ProductPane();
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
        this.setSize(1020, 800);
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
        buttonAppearanceSetting(search);
        search.setPreferredSize(new Dimension(120, 30));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                current = (Department)
//                pp.set();
//                SwingUtilities.updateComponentTreeUI(pp);
            }
        });
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
        //localization of panel of products
        {
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.BOTH;
            c.gridheight = GridBagConstraints.REMAINDER;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 0.85;
            c.weighty = 0.8;
        }
        add(new JScrollPane(pp), c);
    }

    static boolean isAdmin() {
        return admin;
    }

    public static void main(String[] args) {
        StoreWindow sw = new StoreWindow("MINECRAFT IS MY LIFE");
        sw.setVisible(true);
    }

    static void addProduct(Product p) {
        current.add(p);
    }

    static void delProduct(Product p) {
        current.remove(p);
    }

    class ProductPane extends JPanel {
        ArrayList<Product> products;
        private JButton p;

        ProductPane() {
            super();
            this.setBackground(darkgray);
            this.setLayout(new GridLayout(0, 4));
        }

        void set() {
            this.removeAll();
            products = current.products;
            for (int i = 0; i < products.size(); i++) {
                p = new ProductButton(products.get(i));
                p.setFont(font);
                p.setBackground(darkgray);
                p.setForeground(white);
                p.setBorder(BorderFactory.createLineBorder(black, 4));
                this.add(p);
            }
            SwingUtilities.updateComponentTreeUI(pp);
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
                    pp.set();
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
                buttonAppearanceSetting(addD);
                addD.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = JOptionPane.showInputDialog(null, "Enter name of the new department", "New department name", JOptionPane.PLAIN_MESSAGE);
                        if (name == null || name.length() < 1)
                            JOptionPane.showMessageDialog(null, "Please enter name!");
                        else if (s.checkUnique(name)) {
                            s.add(name);
                            department.addItem(s.getDepartment(s.departments.size() - 1));
                        } else JOptionPane.showMessageDialog(null, "Department \"" + name + "\" already exists!");
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
                JButton delD = new JButton("Delete Department");
                buttonAppearanceSetting(delD);
                delD.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int response = JOptionPane.showConfirmDialog(null,
                                "Delete department \"" + current.getName() + "\"?", "Deleting department", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) s.remove(current);
                        //TODO
                        pp.set();
                    }
                });
                //Localization of button "Delete department"
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
                add(delD, c);
                JButton addP = new JButton("Add new product");
                buttonAppearanceSetting(addP);
                addP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ProductWindow.setCurrent(new Product("", 0, 0, "pics\\default.png"));
                        Product product = new ProductWindow("New Product").getProduct();
                        if (current.checkUnique(current.toString())) current.add(product);
                        else
                            JOptionPane.showMessageDialog(null, "Product " + product.getName() + " is already exists!");
                        pp.set();
                    }
                });
                //Localization of button "Add Product"
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
                add(addP, c);
                JButton save = new JButton("Save into file");
                buttonAppearanceSetting(save);
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
                    c.gridy = 5;
                    c.weighty = 0.4;
                    c.insets = new Insets(40, 20, 40, 20);
                }
                add(save, c);
            } else {
                JButton cart = new JButton();
                Icon icon = new ImageIcon("pics\\minecart.png");
                cart.setIcon(icon);
                cart.setText("Cart");
                cart.setHorizontalTextPosition(SwingConstants.RIGHT);
                buttonAppearanceSetting(cart);
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
            user = new JButton();
            user.setText(admin ? "Log in as User" : "Log in as Admin");
            user.setHorizontalTextPosition(SwingConstants.RIGHT);
            buttonAppearanceSetting(user);
            user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    admin = admin ? false : true;
                    user.setText(admin ? "Log in as User" : "Log in as Admin");
//                                        SwingUtilities.updateComponentTreeUI(sp);
                }
            });
            //Localization of button "Add to cart"
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridheight = 1;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 6;
                c.weighty = 0.4;
                c.insets = new Insets(10, 10, 10, 10);
            }
            add(user, c);
        }
    }

    private void buttonAppearanceSetting(JButton button) {
        button.setFont(font);
        button.setBackground(darkgray);
        button.setForeground(white);
        button.setBorder(BorderFactory.createLineBorder(black, 4));
    }
}

