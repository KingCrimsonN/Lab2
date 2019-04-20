import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductButton extends JButton {
    Product p;

    ProductButton(Product product, boolean admin) {
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
//                ProductWindow.setCurrent(p);
                ProductWindow prw = new ProductWindow(p.getName(), admin, false, p);
            }
        });
    }
}
