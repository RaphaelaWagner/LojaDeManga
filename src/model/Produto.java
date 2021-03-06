/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rogerio.slucon
 */
public class Produto {
    private int id,  autorId, tituloId, fornecedorId, generoId;
    String titulo, autor;
    private int estanteId, prateleiraId, edicao, estiloId, qtd;
    private String status;
    private float preco;
    
    //Temporario
    private int desconto;
    
    public Produto() {
    }

    public Produto(int id, int tituloId,
            int autorId, int fornecedorId,
            int generoId, int estanteId,
            int prateleiraId, int edicao,
            int estiloId, String status, float preco) {
        this.id = id;
        this.tituloId = tituloId;
        this.autorId = autorId;
        this.fornecedorId = fornecedorId;
        this.generoId = generoId;
        this.estanteId = estanteId;
        this.prateleiraId = prateleiraId;
        this.edicao = edicao;
        this.estiloId = estiloId;
        this.status = status;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTituloId() {
        return tituloId;
    }

    public void setTituloId(int tituloId) {
        this.tituloId = tituloId;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public int getEstanteId() {
        return estanteId;
    }

    public void setEstanteId(int estanteId) {
        this.estanteId = estanteId;
    }

    public int getPrateleiraId() {
        return prateleiraId;
    }

    public void setPrateleiraId(int prateleiraId) {
        this.prateleiraId = prateleiraId;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getEstiloId() {
        return estiloId;
    }

    public void setEstiloId(int estiloId) {
        this.estiloId = estiloId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    //Temporario

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }
    
    
}
