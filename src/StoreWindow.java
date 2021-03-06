import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * File: StoreWindow.java
 *
 * @author Lena Kurenkova
 * Window of store
 */
public class StoreWindow extends JFrame {
    static Store s = FileInput.readConfig("config.ttt");
    private Department current;//current department
    private static boolean admin = false;//admin/user
    private Font font;//font
    private Color black = new Color(30, 28, 31);
    private Color darkgray = new Color(86, 86, 86);
    private Color gray = new Color(155, 155, 155);
    private Color white = new Color(213, 213, 213);
    private SidePane sp;//sidepane with all buttons
    private ProductPane pp = new ProductPane();//product pane
    private JTextField find;//text field for searching products
    private JLabel l;//label
    private JButton search;//button to search for product

    /**
     * Creates window
     *
     * @param name title
     */
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
        Icon icon = new ImageIcon("pics\\logo.png");
        l = new JLabel(icon);
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
                pp.set(s.searchByName(find.getText()));
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
        sp = new SidePane();
        add(sp, c);
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
        JScrollPane jsp = new JScrollPane(pp);
        jsp.getVerticalScrollBar().setUnitIncrement(16);
        add(jsp, c);
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static void main(String[] args) {
        StoreWindow sw = new StoreWindow("EnderShop");
        sw.setVisible(true);
    }

    public ProductPane getPp() {
        return pp;
    }

    private void buttonAppearanceSetting(JButton button) {
        button.setFont(font);
        button.setBackground(darkgray);
        button.setForeground(white);
        button.setBorder(BorderFactory.createLineBorder(black, 4));
    }

    class ProductPane extends JPanel {
        ArrayList<Product> products;
        private JButton p;

        ProductPane() {
            super();
            this.setBackground(darkgray);
            this.setLayout(new GridLayout(0, 4));
        }

        void set(ArrayList<Product> list) {
            this.removeAll();
            products = list;
            if (list == null) {
                if (p != null)
                    p.removeAll();
                this.update(this.getGraphics());
                return;
            }
            for (int i = 0; i < products.size(); i++) {
                p = new ProductButton(StoreWindow.this, products.get(i), admin);
                buttonAppearanceSetting(p);
                this.add(p);
            }
            SwingUtilities.updateComponentTreeUI(pp);
        }
    }

    class SidePane extends JPanel {
        private JLabel pickDep;
        private JButton user;
        private JComboBox<Department> department;

        SidePane() {
            super();
            this.setBackground(darkgray);
            this.setLayout(new GridBagLayout());
            set();
        }

        private void set() {
            if (sp != null) sp.removeAll();
            GridBagConstraints c = new GridBagConstraints();
            pickDep = new JLabel("Departments");
            pickDep.setFont(font);
            pickDep.setBackground(darkgray);
            pickDep.setForeground(white);
            pickDep.setBorder(BorderFactory.createLineBorder(black, 4));
            //Localization of label "Departments"
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.BOTH;
                c.insets = new Insets(10, 10, 10, 10);
                c.gridheight = 1;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 0;
                c.weighty = 0.2;
            }
            add(pickDep, c);
            JButton addP = new JButton("Add new product");
            JButton stats = new JButton("Get Stats");
            JButton buy = new JButton("Purchase");
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
                    if (department.getItemAt(0) != null) {
                        current = (Department) department.getSelectedItem();
                        if (current != null)
                            pp.set(current.getProducts());
                        if (!addP.isEnabled()) addP.setEnabled(true);
                        if (!stats.isEnabled()) stats.setEnabled(true);
                        buy.setEnabled(false);
                    }
                }
            });
            //Localization of JComboBox with Departments
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.BOTH;
                c.insets = new Insets(10, 10, 10, 10);
                c.gridheight = 1;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 1;
                c.weighty = 0.;
            }
            add(department, c);
            if (admin) {
                JButton addD = new JButton("Add Department");
                JButton delD = new JButton("Delete Department");
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
                            current = s.getDepartment(s.departments.size() - 1);
                            department.setSelectedItem(current);
                            if (!department.isEnabled()) {
                                department.setEnabled(true);
                                department.removeItemAt(0);
                            }
                            if (!addP.isEnabled()) addP.setEnabled(true);
                            if (!delD.isEnabled()) delD.setEnabled(true);
                            if (!stats.isEnabled()) stats.setEnabled(true);
                        } else JOptionPane.showMessageDialog(null, "Department \"" + name + "\" already exists!");
                    }
                });
                //Localization of button "Add department"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(10, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 2;
                    c.weighty = 0.4;
                }
                add(addD, c);
                buttonAppearanceSetting(delD);
                delD.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (current != null) {
                            int response = JOptionPane.showConfirmDialog(null,
                                    "Delete department \"" + current.getName() + "\"?", "Deleting department", JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                s.remove(current);
                                if (!s.departments.isEmpty()) {
                                    department.removeItem(current);
                                    pp.set(current.getProducts());
                                } else {
                                    department.setSelectedItem(null);
                                    department.setEnabled(false);
                                    addP.setEnabled(false);
                                    delD.setEnabled(false);
                                    stats.setEnabled(false);
                                    pp.set(null);
                                }
                            }
                        }
                    }
                });
                //Localization of button "Delete department"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(10, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.weighty = 0.4;
                }
                add(delD, c);
                buttonAppearanceSetting(addP);
                addP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Product product = new Product("", 0, 0, current, "pics\\default.png", "");
                        if (s.checkUnique(product.getName())) current.add(product);
                        ProductWindow prw = new ProductWindow(StoreWindow.this, "New product", admin, true, product);
                        pp.set(current.getProducts());
                    }
                });
                //Localization of button "Add Product"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(10, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 4;
                    c.weighty = 0.4;
                }
                add(addP, c);
                if (current == null) addP.setEnabled(false);
                buttonAppearanceSetting(stats);
                if (current == null) stats.setEnabled(false);
                stats.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Products in department \"" + current.getName() + "\": " + current.getProducts().size() +
                                "\nTotal price of department: " + NumberFormat.getNumberInstance().format(current.groupPrice()) + " ol'mazi\nAll products: " +
                                s.getAllProducts().size() + "\nTotal price of the shop: " + NumberFormat.getNumberInstance().format(s.totalPrice()) + " ol'mazi", "Stats", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                //Localization of button "Get stats"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(10, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 5;
                    c.weighty = 0.4;
                }
                add(stats, c);
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
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(10, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 6;
                    c.weighty = 0.4;
                }
                add(save, c);
                if (s.departments == null || s.departments.size() == 0) {
                    addP.setEnabled(false);
                    delD.setEnabled(false);
                }
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
                        pp.set(s.getCart());
                        if (s.getCart() != null && s.getCart().size() > 0) buy.setEnabled(true);
                        else buy.setEnabled(false);
                    }
                });
                //Localization of button "Add to cart"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(10, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.weighty = 0.4;
                }
                add(cart, c);
                buttonAppearanceSetting(buy);
                if (s.getCart().size() == 0) buy.setEnabled(false);
                buy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int response = JOptionPane.showConfirmDialog(null, "Products: " + s.getCart().size() + "\nTotal price: " + s.cartPrice() + " ol'mazi\nWant to purchase?");
                        if (response == JOptionPane.YES_OPTION) {
                            s.purchase();
                            FileInput.saveFile(s.toString());
                            buy.setEnabled(false);
                            pp.set(s.getCart());
                        }
                    }
                });
                //Localization of button "Purchase"
                {
                    c.anchor = GridBagConstraints.CENTER;
                    c.fill = GridBagConstraints.BOTH;
                    c.insets = new Insets(10, 10, 10, 10);
                    c.gridheight = 1;
                    c.gridwidth = 1;
                    c.gridx = 0;
                    c.gridy = 4;
                    c.weighty = 0.4;
                }
                add(buy, c);
            }
            user = new JButton();
            user.setText(admin ? "Log in as User" : "Log in as Admin");
            user.setHorizontalTextPosition(SwingConstants.RIGHT);
            buttonAppearanceSetting(user);
            user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (admin) FileInput.saveFile(s.toString());
                    admin = !admin;
                    user.setText(admin ? "Log in as User" : "Log in as Admin");
                    sp.set();
                    pp.set(null);
                }
            });
            //Localization of button "Lod in"
            {
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.BOTH;
                c.insets = new Insets(10, 10, 10, 10);
                c.gridheight = 1;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 7;
                c.weighty = 0.4;
//                c.insets = new Insets(10, 10, 10, 10);
            }
            add(user, c);
        }
    }
}
