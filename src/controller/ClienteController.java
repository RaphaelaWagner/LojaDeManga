/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOCliente;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.tableModels.ClienteTableModel;
import model.Cliente;

/**
 *
 * @author raphaela.crwagner
 * @see dao.DAOCliente
 * @see model.Cliente
 * @see model.tableModels.ClienteTableModel;
 */
public class ClienteController {   
    private static ClienteController INSTANCE;
    
    private ClienteTableModel tableModel = new ClienteTableModel();
    private String erros;
    
//    private String cpfSelecionado = "";
    
    //Padrao de Projeto SINGLETON, garante uma unica instancia dessa classe
    //@return Instance
    public static ClienteController getClienteController(){
        if(INSTANCE != null){
            return INSTANCE;
        }else{
            return INSTANCE = new ClienteController();
        }
    }
    
    public ClienteController() {
        lerClientes();
    }
    /*@param Cpf objeto do tipo String
      @ return cliente: se cli existir, null: se não encontrar o cliente*/
    public Cliente pesquisaPorCpf(String Cpf){
        DAOCliente dao = new DAOCliente();
        Cliente cli = dao.buscarPorCpf(Cpf);
        if(cli != null){
            return cli;
        }else{
            return null;
        }
    }
    /*@param array campos do tipo JTextField
      @ return boolean - true: se encontrar cliente, false: se não encontrar cliente*/
    public boolean atualizaCliente(JTextField[] campos){
        Cliente cli = validarDados(campos);
        if(cli != null){
            DAOCliente dao = new DAOCliente();
            try {
                dao.update(cli);
                tableModel.getCli();
                return true;
            } catch (SQLException ex) {
                System.err.println("CC Erro Atualizar Cliente");
                Logger.getLogger(ClienteController.class.getName()).
                        log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    /*@ param array campos do tipo JTextField
      @ return boolean - true: Cadastro Realizado, false: Não conseguir cadastrar*/
    public boolean addCliente(JTextField[] campos){
        Cliente cli = validarDados(campos);
        
        
        
        if(cli != null){
            DAOCliente daocli = new DAOCliente();        
            Cliente aux = daocli.buscarPorCpf(cli.getCpf());
            if(aux == null){
                try {
                    DAOCliente dao = new DAOCliente();
                    dao.insert(cli);
                    tableModel.addLinha(cli);
                                
                
                JOptionPane.showMessageDialog(null,"Cadastro Realizado");
                return true;
                } catch (SQLException ex) {
                    System.err.println("CC Erro addCliente");
                    Logger.getLogger(ClienteController.class.getName())
                        .log(Level.SEVERE, null, ex);
                    return false;
                }
                
            }else{
               erros = "CPF Ja existente";
               return false;
            }
            
        } 
        return false;
    }
    /*@param array campos do tipo JTextField
      @return cliente: se nao houver erros, null: se houver erros*/
    public Cliente validarDados(JTextField[] campos){
        Cliente cli = new Cliente();
        erros = "";
        
        if(!campos[0].getText().trim().equals("")){
            cli.setCpf(campos[0].getText());
        }else{
            erros +="\n Campo Cpf Inválido";
        }
        if(!campos[1].getText().trim().equals("")){
            cli.setNome(campos[1].getText());
        }else{
            erros +="\n Campo Nome Inválido";
        }
        if(!campos[2].getText().trim().equals("")){
            cli.setEmail(campos[2].getText());
        }else{
            erros +="\n Campo E-mail Inválido"; 
        }
        if(!campos[3].getText().trim().equals("")){
            cli.setCel(campos[3].getText());
        }else{
            erros +="\n Campo Celular Inválido";
        }
        if(!campos[4].getText().trim().equals("")){
            cli.setTel(campos[4].getText());
        }else{
            erros +="\n Campo Telefone Inválido";
        }
        if(!campos[5].getText().trim().equals("")){
            cli.setCep(campos[5].getText());
        }else{
            erros +="\n Campo CEP Inválido"; 
        }
        if(!campos[6].getText().trim().equals("")){
            cli.setEndereco(campos[6].getText());
        }else{
            erros +="\n Campo Endereço Inválido";  
        }
        if(!campos[7].getText().trim().equals("")){
            cli.setCidade(campos[7].getText());
        }else{
            erros +="\n Campo Cidade Inválido";
        }
        if(!campos[8].getText().trim().equals("")){
            cli.setBairro(campos[8].getText());
        }else{
            erros +="\n Campo Bairro Inválido"; 
        }if(!campos[9].getText().trim().equals("")){
            cli.setComplemento(campos[9].getText());
        }
        
        cli.setStatus("A");
        
        if(erros.equals("")){
            return cli;
        }else{
            erros = "Campos Obrigatorios: \n" + erros;
            return null;
        }
    }
    
    //@deprecated
    public void lerClientes(){
        DAOCliente dao = new DAOCliente();
        tableModel.setList(dao.selectAll());
    }
    
    /*Getters e Setters
    @return tabela cliente*/
    public ClienteTableModel getTableModel() {
        return tableModel;
    }
    /*@return erro do tipo String */
    public String getErros(){
        String aux = erros;
        erros = "";
        return aux;
    }
    
    /*@param nome do tipo String
      @return boolean - true: lista estiver cheia, false: lista vazia*/
    public boolean filtrarPorNome(String nome){
        DAOCliente dao = new DAOCliente();
        ArrayList<Cliente> lista = new ArrayList<>();
        lista = dao.buscaPorNome(nome);
        if(!lista.isEmpty()){
            tableModel.setList(lista);
            tableModel.atualiza();
            return true;
        }else{
            return false;
        }
    }
    /*@param cpf do tipo String
      @return boolean-true: se existir cliente, false: se não houver cliente*/
    public boolean filtrarPorCPF(String cpf){
        DAOCliente dao = new DAOCliente();
        Cliente cli = dao.buscarPorCpf(cpf);
        if(cli != null){
            ArrayList<Cliente> lista = new ArrayList<>();
            lista.add(cli);
            tableModel.setList(lista);
            tableModel.atualiza();
            return true;
        }else{
            return false;
        }
    }
    
    public void listaCompleta(){
        tableModel.getCli();
    }
//    public void setCpfSelecionado(String cpf){
//        cpfSelecionado = cpf;
//    }
//    
//    public String getCpfSelecionado(){
//        String aux = cpfSelecionado;
//        cpfSelecionado = "";
//        return aux;
//    }
    
}
