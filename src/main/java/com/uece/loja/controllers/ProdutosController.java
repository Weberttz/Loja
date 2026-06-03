package com.uece.loja.controllers;

import com.uece.loja.models.Produto;
import com.uece.loja.models.ProdutoDto;
import com.uece.loja.services.ProdutosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
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

    @GetMapping("/criar")
    public String mostrarPaginaDeCriacao(Model model){
        ProdutoDto produtoDto = new ProdutoDto();
        model.addAttribute("produtoDto", produtoDto);
        return "produtos/CriarProduto";
    }

    @PostMapping("/criar")
    public String criarProduto(
            @Valid @ModelAttribute ProdutoDto produtoDto,
            BindingResult resultado
    ){

        if(produtoDto.getArquivoDeImagem() == null || produtoDto.getArquivoDeImagem().isEmpty()){
            resultado.addError(new FieldError("produtoDto", "arquivoDeImagem", "O arquivo de imagem é necessário"));
        }

        if( resultado.hasErrors()){
            return "produtos/CriarProduto";
        }

        MultipartFile imagem = produtoDto.getArquivoDeImagem();
        Date criadoEm = new Date();
        String nomeArquivo = criadoEm.getTime() + "_" + imagem.getOriginalFilename();

        try{
            String diretorio = "public/imagens/";
            Path caminho = Paths.get(diretorio);

            if(!Files.exists(caminho)){
                Files.createDirectories(caminho);
            }

            try(InputStream entradaStream = imagem.getInputStream()){
                Files.copy(entradaStream, Paths.get(caminho + nomeArquivo),
                        StandardCopyOption.REPLACE_EXISTING);
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setMarca(produtoDto.getMarca());
        produto.setCategoria(produtoDto.getCategoria());
        produto.setPreco(produtoDto.getPreco());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setCriadoEm(criadoEm);
        produto.setNomeDaImagem(nomeArquivo);

        repositorio.save(produto);

        return "redirect:/produtos";
    }

}
