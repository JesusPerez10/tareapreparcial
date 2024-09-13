package Umg.formas.productos;

import Umg.formas.productos.Database.Model.Producto;
import Umg.formas.productos.Database.Services.ProductoService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmproductos {
    private JPanel jPanelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblid;
    private JTextField textFieldidProducto;
    private JLabel lblnombre;
    private JTextField textFieldNombreProducto;
    private JLabel lblOrigen;
    private JComboBox comboBoxOrigen;
    private JButton buttonGrabar;
    private JButton buttonBuscar;
    private JButton buttonActualizar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmproductos");
        frame.setContentPane(new frmproductos().jPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public frmproductos() {

        comboBoxOrigen.addItem("China");
        comboBoxOrigen.addItem("Japon");
        comboBoxOrigen.addItem("Corea");

        buttonGrabar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Producto producto = new Producto();
                producto.setnombre(textFieldNombreProducto.getText());
                producto.setOrigen(comboBoxOrigen.getSelectedIndex().toString());
                try{
                    new ProductoServices().crearProducto(producto);
                    JOptionPane.showConfirmDialog(null,"Producto creado exitosamente");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Error al crear el producto");
                }

            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idproducto = textFieldidProducto.getText().isEmpty() ? 0 : Integer.parseInt(textFieldidProducto.getText());
                try {
                    Producto productoEncontrado = new ProductoService().obtenerProducto(idproducto);
                    if (productoEncontrado != null) {
                        textFieldNombreProducto.setText(productoEncontrado.getDescripcion);
                        comboBoxOrigen.setSelectedItem(productoEncontrado.getOrigen);
                    } else {
                        JOptionPane.showMessageDialog(null,"Codigo de producto no existe");

                    }

                }

            }
        });
    }
}
