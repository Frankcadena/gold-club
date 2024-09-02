	package com.example.goldClub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goldClub.models.Producto;
import com.example.goldClub.repository.ProductoRepository;

@Service
	public class ProductoService {

	    @Autowired
	    private ProductoRepository productoRepository;

	    public List<Producto> listarProductos() {
	        return productoRepository.findAll();
	    }

	    public Producto guardarProducto(Producto producto) {
	        return productoRepository.save(producto);
	    }

	    public void eliminarProducto(Long id) {
	        productoRepository.deleteById(id);
	    }
	}