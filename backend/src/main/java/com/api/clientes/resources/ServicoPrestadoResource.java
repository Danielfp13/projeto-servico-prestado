package com.api.clientes.resources;

import com.api.clientes.dto.ServicoPrestadoDto;
import com.api.clientes.model.entity.Cliente;
import com.api.clientes.model.entity.ServicoPrestado;
import com.api.clientes.repository.ClienteRepositoty;
import com.api.clientes.repository.ServicoPrestadoRepositoty;
import com.api.clientes.utils.StringToBigdecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/servicos-prestado")
//@CrossOrigin("http://localhost:4200")
public class ServicoPrestadoResource {

    @Autowired
    private ClienteRepositoty repo;

    @Autowired
    private ServicoPrestadoRepositoty servicoPrestadoRepositoty;

    @Autowired
    StringToBigdecimal stringToBigdecimal;

    @PostMapping
    public ResponseEntity<ServicoPrestado> insert(@Valid @RequestBody ServicoPrestadoDto obj){
       LocalDate data =  LocalDate.parse(obj.getData(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       Integer idCliente = obj.getIdCliente();

        Cliente cliente = repo.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente inesistente."));

        System.out.println(obj.toString());
        ServicoPrestado  servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(obj.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(stringToBigdecimal.convert(obj.getPreco()));

        servicoPrestado = servicoPrestadoRepositoty.save(servicoPrestado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servicoPrestado.getId()).toUri();
        return ResponseEntity.created(uri).body(servicoPrestado);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ServicoPrestado>> search(
            @RequestParam(value = "nome", defaultValue = "" , required = false) String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ){
    List<ServicoPrestado> lista =  servicoPrestadoRepositoty.findByNameClienteAndMonth(
            "%" + nome + "%",mes);
        return ResponseEntity.ok().body(lista);
    }

}
