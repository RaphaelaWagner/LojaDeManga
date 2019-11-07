package controller;

import dao.DAOProduto;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lojademanga.ProdutoTableModel;
import model.Produto;

/**
 *
 * @author gabriel.mmcarmo
 */

public class ProdutoController {
    
    private static ProdutoController INSTANCE;
    
    private ProdutoTableModel tableModel = new ProdutoTableModel();
    
    //Padrao de Projeto SINGLETON, garante uma unica instancia dessa classe
    public static ProdutoController getProdutoController(){
        if(INSTANCE != null){
            return INSTANCE;
        }else{
            return INSTANCE = new ProdutoController();
        }
    }
    
    // Métodos de acesso a DAO
    public Produto pesquisaPorId (int id){
        DAOProduto dao = new DAOProduto();
        Produto prod = dao.buscaPorId(id);
        if(prod != null){
            System.out.println(prod.getId());
            return prod;
        }else{
            return null;
        }
    }
    
    public void atualizaProduto(JTextField[] campos){
        Produto prod = validarDados(campos);
        if(prod != null){
            DAOProduto dao = new DAOProduto();
            try {
                dao.update(prod);
                tableModel.getProd();
            } catch (SQLException ex) {
                System.out.println("Erro Atualizar Produto");
                Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean addProduto(JTextField[] campos){
        System.out.println("Add2");
        Produto prod = validarDados(campos);
        
        if(prod != null){
            try {
                DAOProduto dao = new DAOProduto();
                dao.insert(prod);
                tableModel.addLinha(prod);
                return true;
            } catch (SQLException ex) {
                System.out.println("Erro addProduto");
                Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } 
        return false;
    }
    
    public void excluirProduto(JTextField[] campos){
        
    }
    
    //Temporario
    public void lerProdutos(){
        DAOProduto dao = new DAOProduto();
        tableModel.setList(dao.selectAll());
    }
    
    //Getters e Setters
    public ProdutoTableModel getTableModel() {
        return tableModel;
    }
    
    // Método de validação de campos
    public Produto validarDados(JTextField[] campos){
        Produto prod = new Produto();
        String erros = "";
        
        if(!campos[1].getText().trim().equals("")){
            prod.setTituloId(Integer.parseInt(campos[1].getText()));
        }else{
            erros +="\n - Título Campo Obrigatorio";
            campos[1].setBackground(Color.red);
        }
        if(!campos[2].getText().trim().equals("")){
            prod.setAutorId(Integer.parseInt(campos[2].getText()));
        }else{
            erros +="\n - Autor Campo Obrigatorio"; 
            campos[2].setBackground(Color.red);
        }
        if(!campos[3].getText().trim().equals("")){
            prod.setFornecedorId(Integer.parseInt(campos[3].getText()));
        }else{
            erros +="\n - Fornecedor Campo Obrigatorio";
            campos[3].setBackground(Color.red);
        }
        if(!campos[4].getText().trim().equals("")){
            prod.setGeneroId(Integer.parseInt(campos[4].getText()));
        }else{
            erros +="\n - Gênero Campo Obrigatorio";
            campos[4].setBackground(Color.red);
        }
        if(!campos[5].getText().trim().equals("")){
            prod.setEstanteId(Integer.parseInt(campos[5].getText()));
        }else{
            erros +="\n - Estante Obrigatorio"; 
            campos[5].setBackground(Color.red);
        }
        if(!campos[6].getText().trim().equals("")){
            prod.setPrateleiraId(Integer.parseInt(campos[6].getText()));
        }else{
            erros +="\n - Prateleira Campo Obrigatorio";  
            campos[6].setBackground(Color.red);
        }
        if(!campos[7].getText().trim().equals("")){
            prod.setEdicao(Integer.parseInt(campos[7].getText()));
        }else{
            erros +="\n - Edição Campo Obrigatorio";
            campos[7].setBackground(Color.red);
        }
        if(!campos[8].getText().trim().equals("")){
            prod.setEstiloId(Integer.parseInt(campos[8].getText()));
        }else{
            erros +="\n - Estilo Campo Obrigatorio"; 
            campos[8].setBackground(Color.red);    
        }
        if(!campos[9].getText().trim().equals("")){
            prod.setStatus(campos[9].getText());
        }
        if(!campos[10].getText().trim().equals("")){
            prod.setPreco(Integer.parseInt(campos[10].getText()));
        }
        
        prod.setStatus("A");
        
        System.out.println(erros);
        if(erros.equals("")){
            return prod;
        }else{
            return null;
        }
    }
}