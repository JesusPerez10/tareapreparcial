package Umg.formas.productos.Database.Dao;

import Umg.formas.productos.Database.Model.Producto;
import Umg.formas.productos.Database.conexion.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String INSERT_SQL = "INSERT INTO tb_producto (descripcion, origen) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE tb_producto SET descripcion = ?, origen = ? WHERE id_producto = ?";
    private static final String DELETE_SQL = "DELETE FROM tb_producto WHERE id_producto = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM tb_producto WHERE id_producto = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM tb_producto";

    public void insert(Producto producto) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getOrigen());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Producto producto) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getOrigen());
            preparedStatement.setInt(3, producto.getIdProducto());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idProducto) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, idProducto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producto selectById(int idProducto) {
        Producto producto = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            preparedStatement.setInt(1, idProducto);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                producto = new Producto(idProducto, descripcion, origen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public List<Producto> selectAll() {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                String origen = rs.getString("origen");
                productos.add(new Producto(idProducto, descripcion, origen));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}

