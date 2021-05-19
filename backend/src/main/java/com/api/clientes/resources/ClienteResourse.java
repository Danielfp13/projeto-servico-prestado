package com.api.clientes.resources;

import com.api.clientes.Service.ClienteService;
import com.api.clientes.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
//@CrossOrigin("http://localhost:4200")
public class ClienteResourse {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente cliente){
        cliente = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente, @PathVariable Integer id){
        cliente =service.update(cliente,id);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente cliente = service.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = service.findAll();
        return ResponseEntity.ok().body(clientes);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<Cliente>> findPage(
            @RequestParam(value = "page" , defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage" , defaultValue = "24") Integer linePerPage,
            @RequestParam(value = "direction" , defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy" , defaultValue = "nome") String orderBy
    ){
        Page<Cliente> pageCliente = service.findPage(page,linePerPage,direction,orderBy);
        return ResponseEntity.ok().body(pageCliente);
    }

}