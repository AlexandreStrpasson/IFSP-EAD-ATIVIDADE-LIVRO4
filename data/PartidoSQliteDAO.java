package data;

import model.Partido;
import java.sql.*;
import java.util.List;

public class PartidoSQliteDAO implements PartidoDAO{
    @Override
    public void salvar(Partido p) {
        String sql = "INSERT INTO partido values (?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, p.getNumero());
            stmt.setString(2,p.getNome());
            stmt.setString(3,p.getSigla());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Partido p) {

        String sql = "UPDATE partido SET nome=?, sigla=? WHERE numero=?";
        try (PreparedStatement stmt = ConnectionFactory.criaStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getSigla());
            stmt.setInt(3, p.getNumero());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Partido p){

        String sql = "DELETE FROM partido WHERE numero=?";
        try(PreparedStatement stmt =ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, p.getNumero());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Partido buscar(int numero){
        String sql = "SELECT * FROM partido WHERE numero=?";
        Partido p = null;
        try(PreparedStatement stmt = ConnectionFactory.criaStatement(sql)){
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                p = new Partido(rs.getInt("numero"), rs.getString("nome"),
                        rs.getString("sigla"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Partido> buscarTodos() {
        return null;
    }

}