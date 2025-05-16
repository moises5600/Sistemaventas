package model;

import java.util.Date;

public class Venta {
    private int id;
    private String producto;
    private int cantidad;
    private double precio;
    private Date fecha;

    public Venta(String producto, int cantidad, double precio, Date fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public String getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }
    public Date getFecha() { return fecha; }

    public void setId(int id) { this.id = id; }
    public void setProducto(String producto) { this.producto = producto; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
