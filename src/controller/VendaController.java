package controller;

import dao.DAOVenda;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.tableModels.CarrinhoTableModel;
import model.Produto;

/**
 *
 * @author rogerio.slucon
 */
public class VendaController {
    private static VendaController INSTACE;
    private CarrinhoTableModel table = new CarrinhoTableModel();
//    private ClienteController controllerCli;
    private ProdutoController prodController; 
    
    public static VendaController getVendaController(){
        if(INSTACE != null){
            return INSTACE;
        }else{
            return INSTACE = new VendaController();
        }
    }
    
    private VendaController() {
//        controllerCli = ClienteController.getClienteController();
        prodController = ProdutoController.getProdutoController();
    }
    
    
    
    public boolean addCarrinho(String idProd, String qtd, String desconto){
        Produto prod = prodController.pesquisaPorId(Integer.parseInt(idProd));
        prod.setDesconto(Integer.parseInt(desconto));
        int qtdInt = Integer.parseInt(qtd);
        
        if(prod != null && qtdInt > 0){
            
            if(prod.getQtd() >= qtdInt){
                table.addProd(prod, qtdInt);
                return true;
            }
        }
        return false;
    }
    
    public void finalizarCompra(String cpf){
        ArrayList<Produto> produtos = table.getLista();
        ArrayList<Integer> qtd = table.getQtdLista();
        ArrayList<Produto> todosProd = new ArrayList<>();
        
        for (int i = 0; i < produtos.size(); i++) {
            for (int j = 0; j < qtd.get(i); j++) {
                todosProd.add(produtos.get(i));
            }
        }
        
        DAOVenda dao = new DAOVenda();
        Date d = new Date();
        String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        dao.gerarVenda(cpf, dStr, todosProd);
    }
    
    public void cancelarCompra(){
        table.cancelarCompra();
    }
    
    public CarrinhoTableModel getModel(){
        return table;
    }
    
}
