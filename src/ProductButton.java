import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * File: ProductButton.java
 *
 * @author Lena Kurenkova
 * Class of button product
 */
public class ProductButton extends JButton {
    Product p;

    /**
     * Create product button
     *
     * @param sw      parent window of store
     * @param product Product
     * @param admin   admin/user
     */
    ProductButton(StoreWindow sw, Product product, boolean admin) {
        super();
        this.p = product;
        Icon icon = new ImageIcon(p.getImage());
        this.setIcon(icon);
        this.setText(p.getName());
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p.getAmount() == 0 && !StoreWindow.isAdmin())
                    JOptionPane.showMessageDialog(null, "Sorry.. Product is not available now..", "Info", JOptionPane.PLAIN_MESSAGE);
                else {
                    ProductWindow prw = new ProductWindow(sw, p.getName(), admin, false, p);
                }
            }
        });
    }
}
