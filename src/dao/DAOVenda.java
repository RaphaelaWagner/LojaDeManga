package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lojademanga.ConnectionFactory;
import model.Venda;
import model.VendaTemporaria;
/*
@see model.Venda
*/
public class DAOVenda {
    private Connection conn;
    
    //Temporario
    //private static HashMap<String, VendaTemporaria> listaVenda = new HashMap<String, VendaTemporaria>();

    private static ArrayList<Venda> listaVenda = new ArrayList<Venda>();
    
    private static int idVenda = 0;
    
    public DAOVenda(){    
        conn = ConnectionFactory.getConnection();
    }
    //@param venda objeto do tipo venda
    public void insert(Venda venda) throws SQLException {
        String sql = "INSERT INTO venda(ve_cliente, ve_funcionario) VALUES (?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, venda.getCliente());
        stmt.setInt(2, venda.getFuncionarios());
        stmt.execute();
        stmt.close();
    }
        //@return venda do tipo Array List se sem erros, null se com erros
    public ArrayList<Venda> selectAll() {
        String sql = "Select * from Venda";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Venda> list = new ArrayList<>();

            while (rs.next()) {
                Venda ve = new Venda();
                ve.setId(rs.getInt("ve_id"));
                ve.setFuncionarios(rs.getInt("ve_funcionario"));
                ve.setCliente(rs.getString("ve_cliente"));
                ve.setData(rs.getString("ve_data"));

                list.add(ve);
            }
            ConnectionFactory.closeConnection(conn, stmt, rs);
            return list;
        } catch (SQLException ex) {
            System.err.println("DAO Venda: " + ex);
            return null;
        }

    }
    //@param id do tipo Inteiro
    //@return venda objeto do tipo venda se sem erros, null se houver erros
    public Venda pegarVenda(int id) {
        String sql = "Select * from Venda where ve_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            Venda ve = new Venda();
            if (rs.next()) {
                ve.setId(rs.getInt("ve_id"));
                ve.setFuncionarios(rs.getInt("ve_funcionario"));
                ve.setCliente(rs.getString("ve_cliente"));
                ve.setData(rs.getString("ve_data"));      
            }
            ConnectionFactory.closeConnection(conn, stmt, rs);
            return ve;
        } catch (SQLException ex) {
            System.err.println("DAO Venda: " + ex);
            return null;
        }

    }
        //@return inteiro se sem erros, null se com erros
    public int pegaIdProximaVenda(){
        
        String sql = "select auto_increment from information_schema.tables"
            + " where table_name = 'venda' and table_schema = 'mangastore'";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("auto_increment");
            }else{
                return -1;
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DAOVenda.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }
        //@param id do tipo inteiro
        //@return inteiro se sem erros, null se com erros
     public int calculaValorVenda(int id){
        String sql = "select sum(produto.pd_preco) as soma from produto, "
                + "itens where itens.cp_compras = ? and "
                + "produto.pd_id = itens.cp_produto;";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
              return rs.getInt("soma");
            }
            
            ConnectionFactory.closeConnection(conn, stmt,rs);
            return -1;        
        } catch (SQLException ex) {
            System.err.println("DAO ITENS: " + ex);
            return -1;
        }
    }
    
     /*Nome, CPF, Codigo do Produto, Nome do Produto,
        Valor Produto, Quantidade, Valor SubTotal,
     Código da Compra*/
    //@param id do tipo String
    //@return venda objeto do tipo Venda se sem erros, null se com erros
    public Venda pegarVendaItens(String id){
        String sql = "select sum(produto.pd_preco) as soma from produto, "
                + "itens where itens.cp_compras = ? and "
                + "produto.pd_id = itens.cp_produto;";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Venda ve = new Venda();
                if(rs.next()){
                ve.setId(rs.getInt("ve_id"));
                ve.setFuncionarios(rs.getInt("ve_funcionario"));
                ve.setCliente(rs.getString("ve_cliente"));
                ve.setData(rs.getString("ve_data"));
                }
            ConnectionFactory.closeConnection(conn, stmt,rs);
            return ve;
        } catch (SQLException ex) {
            System.err.println("DAO ITENS: " + ex);
            return null;
            
        }
        
//        return listaVenda.get(idVenda);
    }
     
    //@deprecated 
    // TEMPORARIO
     
//    public void gerarVenda(VendaTemporaria venda){
//        String aux = Integer.toString(idVenda);
//        listaVenda.put(aux, venda);
//        idVenda++;
//    }
//    
//    public VendaTemporaria getVendaItens(String idVenda){
//        return listaVenda.get(idVenda);
//    }
//    
//    public Set<String> getKey() {
//        return listaVenda.keySet();
//    }
    //@param data
    //@return venda do tipo array List se sem erros, null com erros
    public ArrayList<Venda> PesqData(java.sql.Date dateI, java.sql.Date dateF){
        String sql = "select * from venda where ve_data between ? and ?;";
        ArrayList<Venda> lista = new ArrayList<>();
        
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt = conn.prepareStatement(sql);
                stmt.setDate(1, dateI);
                stmt.setDate(2, dateF);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    Venda ve = new Venda();
                    ve.setId(rs.getInt("ve_id"));
                    ve.setFuncionarios(rs.getInt("ve_funcionario"));
                    ve.setCliente(rs.getString("ve_cliente"));
                    ve.setData(rs.getString("ve_data"));

                    lista.add(ve);
            }
                ConnectionFactory.closeConnection(conn, stmt, rs);
                System.out.println("Retornando DAOVENDA:   " + lista);
                return lista;
            } catch (SQLException ex) {
                Logger.getLogger(DAOVenda.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        
    }
    
    
    
}
