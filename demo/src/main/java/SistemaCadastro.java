import java.util.List;

public class SistemaCadastro {
    public static void main(String[] args) {
        FabricaConexao.conectar();
        CadastroRepository repository = new CadastroRepository();
        Cadastro cadastro = repository.buscar(1);
        if(cadastro != null){
            System.out.println(cadastro.getNome() + " " + cadastro.getIdade());            
    }else{
        System.out.println("Cadastro não encontrado!");
    }
    }
}
