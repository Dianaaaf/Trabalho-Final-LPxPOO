import java.sql.*;

public class testeBD {
    public static void main(String[] args) {

        String url = "C:\\Users\\AcerNotebook\\Desktop\\ProjetoFinal\\src\\main\\java\\testeBD.java";
        String usuario = "root";
        String senha = "";

        try{
            // faz a conexão com o bd
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            //declaração pra executar a query
            Statement statement = conexao.createStatement();

            //query para executar
            String query = "SELECT * FROM LIVROS";

            //executa e pega os resultados
            ResultSet resultado = statement.executeQuery(query);

            //percorre os resultados e imprime
            while(resultado.next()){
                String titulo = resultado.getString("Titulo");
                //.. demais campos
                System.out.println("Título: " + titulo);
            }
            // fecha os recursos;
            resultado.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}