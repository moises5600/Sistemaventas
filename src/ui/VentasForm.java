package ui;

import db.Conexion;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class VentasForm extends JFrame {
    JTextField txtProducto = new JTextField(15);
    JTextField txtCantidad = new JTextField(5);
    JTextField txtPrecio = new JTextField(10);

    JTextArea txtReporte = new JTextArea(10, 30);

    public VentasForm() {
        setTitle("Sistema de Ventas");
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        panelFormulario.add(new JLabel("Producto:")); panelFormulario.add(txtProducto);
        panelFormulario.add(new JLabel("Cantidad:")); panelFormulario.add(txtCantidad);
        panelFormulario.add(new JLabel("Precio:")); panelFormulario.add(txtPrecio);

        JButton btnRegistrar = new JButton("Registrar Venta");
        panelFormulario.add(btnRegistrar);

        add(panelFormulario, BorderLayout.NORTH);

        btnRegistrar.addActionListener(e -> registrarVenta());

        JButton btnReporte = new JButton("Mostrar Reporte");
        btnReporte.addActionListener(e -> mostrarReporte());
        add(btnReporte, BorderLayout.CENTER);
        add(new JScrollPane(txtReporte), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void registrarVenta() {
        try (Connection con = Conexion.conectar()) {
            String sql = "INSERT INTO ventas(producto, cantidad, precio, fecha) VALUES (?, ?, ?, NOW())";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtProducto.getText());
            ps.setInt(2, Integer.parseInt(txtCantidad.getText()));
            ps.setDouble(3, Double.parseDouble(txtPrecio.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Venta registrada");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarReporte() {
        try (Connection con = Conexion.conectar()) {
            String sql = "SELECT * FROM ventas";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Producto: ").append(rs.getString("producto"))
                  .append(", Cantidad: ").append(rs.getInt("cantidad"))
                  .append(", Precio: ").append(rs.getDouble("precio"))
                  .append(", Fecha: ").append(rs.getString("fecha")).append("\n");
            }
            txtReporte.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
