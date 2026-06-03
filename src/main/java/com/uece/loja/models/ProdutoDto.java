package com.uece.loja.models;

import jakarta.validation.constraints.*;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class ProdutoDto {
    @NotEmpty (message = "O nome é necessário")
    private String nome;
    @NotEmpty (message = "A marca é necessária")
    private String marca;
    @NotEmpty (message = "A categoria é necessária")
    private String categoria;

    @Min(0)
    private double preco;

    @Size (min=10, message = "A descrição deve ter no mínimo 10 caracteres")
    @Size (max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
    private String descricao;

    private MultipartFile arquivoDeImagem;

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public MultipartFile getArquivoDeImagem() {
        return arquivoDeImagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setArquivoDeImagem(MultipartFile arquivoDeImagem) {
        this.arquivoDeImagem = arquivoDeImagem;
    }
}

