package com.uece.loja.services;

import com.uece.loja.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
