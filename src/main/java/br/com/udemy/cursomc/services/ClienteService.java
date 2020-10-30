package br.com.udemy.cursomc.services;

import br.com.udemy.cursomc.domain.Cliente;
import br.com.udemy.cursomc.repositories.ClienteRepository;
import br.com.udemy.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id +", Tipo: " + Cliente.class.getName()
        ));
    }
}
