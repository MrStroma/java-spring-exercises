package com.example.libreriaonlinejpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libreriaonlinejpa.dtos.LibroDTO;
import com.example.libreriaonlinejpa.entities.Autore;
import com.example.libreriaonlinejpa.entities.Libro;
import com.example.libreriaonlinejpa.repositories.AutoreRepository;
import com.example.libreriaonlinejpa.repositories.LibroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LibroService {

	@Autowired
	private LibroRepository lr;
	
	@Autowired
	private AutoreRepository ar;

	public List<LibroDTO> getAll() {
		List<LibroDTO> libri = new ArrayList<>();
		List<Libro> entities = lr.findAll();
		// for(Libro libro: entities) {
		// LibroDTO librodto = new LibroDTO();
		// librodto.setLibroId();
		// }

		for (int i = 0; i < entities.size(); i++) {
			Libro entity = entities.get(i);
			LibroDTO libro = new LibroDTO();
			libro.setLibroId(entity.getId());
			libro.setGenere(entity.getGenere());
			libro.setPrezzo(entity.getPrezzo());
			libro.setTitolo(entity.getTitolo());
			libro.setAutori(entity.getAutori());
			libri.add(libro);

		}

		return libri;
	}

	public void save(LibroDTO libro) {
		Libro libroentity = new Libro();
		libroentity.setId(libro.getLibroId());
		libroentity.setGenere(libro.getGenere());
		libroentity.setPrezzo(libro.getPrezzo());
		libroentity.setTitolo(libro.getTitolo());
		libroentity.setAutori(libro.getAutori());
		lr.save(libroentity);

	}
	
	 public void collegaAutoreLibro(int autoreId, int libroId) {
		 	Libro libro = lr.findById(libroId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
		 	Autore autore = ar.findById(autoreId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
		 	
		 	libro.getAutori().add(autore);
		 	autore.getLibri().add(libro);
		 	
		 	lr.save(libro);
		 	ar.save(autore);
	    }

}
