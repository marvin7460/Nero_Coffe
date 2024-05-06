package com.example.nerocoffe.service;

import com.example.nerocoffe.model.Product;
import com.example.nerocoffe.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Product> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Product> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Product createProducto(Product producto) {
        return productoRepository.save(producto);
    }

    public void guardarImagen(MultipartFile archivo, Product producto) throws IOException {
        // Guardar el archivo en el sistema de archivos
        String nombreArchivo = StringUtils.cleanPath(archivo.getOriginalFilename());
        String directorio = "./imagenes-productos/";
        File almacenamiento = new File(directorio);
        if (!almacenamiento.exists()) {
            almacenamiento.mkdirs();
        }
        File archivoGuardado = new File(directorio + nombreArchivo);
        archivo.transferTo(archivoGuardado);

        // Guardar el nombre del archivo en la base de datos
        producto.setNombreImagen(nombreArchivo);
        productoRepository.save(producto);
    }

    public Resource cargarImagen(Long id) throws MalformedURLException {
        Product producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        String nombreArchivo = producto.getNombreImagen();
        Path rutaArchivo = Paths.get("./imagenes-productos/").resolve(nombreArchivo).toAbsolutePath();
        Resource recurso = new UrlResource(rutaArchivo.toUri());

        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("No se pudo leer el archivo: " + nombreArchivo);
        }

        return recurso;
    }
}
