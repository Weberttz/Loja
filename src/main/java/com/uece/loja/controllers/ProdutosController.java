package com.uece.loja.controllers;

import com.uece.loja.models.Produto;
import com.uece.loja.services.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository repositorio;

    @GetMapping({"", "/"})
    public String mostrarListaDeProdutos(Model model){
        List<Produto> produtos = repositorio.findAll();
        model.addAttribute("produtos", produtos);
        return "produtos/index";
    }

}
