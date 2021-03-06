/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lojademanga.ConnectionFactory;
import model.Autor;

/**
 *
 * @author raphaela.crwagner
 * @see model.Autor
 */
public class DAOAutor {

    private Connection conn;

    public DAOAutor() {
        conn = ConnectionFactory.getConnection();
    }
    //@param autor objeto do tipo autor
    public void insert(Autor autor) throws SQLException {
        String sql = "INSERT INTO autor(nome) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, autor.getNome());
        stmt.execute();
        stmt.close();
    }
    //@return autor do tipo array list se sem erros, null se tiver erros
    public ArrayList<Autor> selectAll() {
        String sql = "Select * from Autor";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Autor> list = new ArrayList<>();

            while (rs.next()) {
                Autor au = new Autor();
                au.setId(rs.getInt("au_id"));
                au.setNome(rs.getString("au_nome"));

                list.add(au);
            }
            ConnectionFactory.closeConnection(conn, stmt, rs);
            return list;

        } catch (SQLException ex) {
            System.err.println("DAO AUTOR: " + ex);
            return null;
        }

    }

}
