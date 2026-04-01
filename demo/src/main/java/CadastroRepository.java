import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroRepository {
    private Connection conexao;

    public CadastroRepository() {
        conexao = FabricaConexao.getConexao();
    }

    public void incluir(Cadastro cadastro) {
        try {
            String sql = "INSERT INTO public.tab_cadastro(nome, idade)VALUES(?,?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cadastro.getNome());
            preparedStatement.setInt(2, cadastro.getIdade());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void alterar(Cadastro cadastro) {
        try {
            String sql = "UPDATE public.tab_cadastro SET  nome=?, idade=? WHERE id=?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cadastro.getNome());
            preparedStatement.setInt(2, cadastro.getIdade());
            preparedStatement.setInt(3, cadastro.getId());
            preparedStatement.execute();
            System.out.println("Alterado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Integer id) {
        try{
            String sql = "DELETE FROM public.tab_cadastro WHERE id=? ";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("Excluido com sucesso!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Cadastro> listar() {
        List<Cadastro> lista = new ArrayList<>();
        try {
            String sql = "SELECT id, nome, idade FROM public.tab_cadastro WHERE id=2 AND nome='Joana' AND idade=18;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            //stament.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                int idade = result.getInt("idade");
                System.out.println(id + " " + nome + " " + idade);

                Cadastro cadastro = new Cadastro();
                cadastro.setId(id);
                cadastro.setNome(nome);
                cadastro.setIdade(idade);
                lista.add(cadastro);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public Cadastro buscar(Integer id) {
        Cadastro cadastro = null;
        try {
            String sql = "SELECT id, nome, idade FROM public.tab_cadastro WHERE id=?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String nome = result.getString("nome");
                int idade = result.getInt("idade");
                System.out.println(id + " " + nome + " " + idade);

                cadastro = new Cadastro();
                cadastro.setId(id);
                cadastro.setNome(nome);
                cadastro.setIdade(idade);

                return cadastro;
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cadastro;
    }
}
