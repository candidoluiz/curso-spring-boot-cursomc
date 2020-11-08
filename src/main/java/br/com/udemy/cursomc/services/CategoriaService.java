package br.com.udemy.cursomc.services;

import br.com.udemy.cursomc.domain.Categoria;
import br.com.udemy.cursomc.repositories.CategoriaRepository;
import br.com.udemy.cursomc.services.exceptions.DataIntegrityException;
import br.com.udemy.cursomc.services.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id +", Tipo: " + Categoria.class.getName()
        ));
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        find(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id){
        try{
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw  new DataIntegrityException("Não é possivel esxcluir uma categoria que possui produtos");
        }

    }
}
