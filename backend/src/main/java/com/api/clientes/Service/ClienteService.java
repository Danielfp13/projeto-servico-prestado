package com.api.clientes.Service;

import com.api.clientes.Service.exception.DataIntegrityException;
import com.api.clientes.Service.exception.ObjectNotFoundException;
import com.api.clientes.model.entity.Cliente;
import com.api.clientes.repository.ClienteRepositoty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositoty repositoty;


    public Cliente find(Integer id) {
        return  repositoty
                .findById(id)
                .orElseThrow( () -> new ObjectNotFoundException("Não existe clinte com id = " + id + "."));

    }

    public List<Cliente> findAll() {
        return repositoty.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linePerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page,linePerPage, Sort.Direction.valueOf(direction),orderBy);
        return repositoty.findAll(pageRequest);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repositoty.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não se pode excluir clientes com serviços.");
        }
    }

    public Cliente update(Cliente cliente, Integer id) {
        Cliente newCliente = find(id);
        BeanUtils.copyProperties(cliente,newCliente);
        newCliente.setId(id);
       // updateData(cliente,newCliente);
        return repositoty.save(newCliente);
    }

    public static void updateData(Cliente cliente, Cliente newCliente){
        newCliente.setCpf(cliente.getCpf());
        newCliente.setNome(cliente.getNome());
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        return repositoty.save(cliente);
    }
}
