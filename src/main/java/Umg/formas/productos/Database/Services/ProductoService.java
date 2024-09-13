package Umg.formas.productos.Database.Services;

import Umg.formas.productos.Database.Dao.ProductoDAO;
import Umg.formas.productos.Database.Model.Producto;

import java.util.List;

public class ProductoService {
    private ProductoDAO productoDAO = new ProductoDAO();

    public void crearProducto(Producto producto) {
        productoDAO.insert(producto);
    }

    public void actualizarProducto(Producto producto) {
        productoDAO.update(producto);
    }

    public void eliminarProducto(int idProducto) {
        productoDAO.delete(idProducto);
    }

    public Producto obtenerProductoPorId(int idProducto) {
        return productoDAO.selectById(idProducto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.selectAll();
    }
}
