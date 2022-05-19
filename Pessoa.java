import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class Pessoa {
    private int codigo;
    private String nome;
    private String fone;
    private String email;

    //mapeamento objeto relacional
    //ORM: Hibernate, EclipseLink
    public void inserir() throws Exception{
        //1. Definir o comando SQL
        String sql = "INSERT INTO tb_pessoa (nome, fone, email) VALUES (?, ?, ?)";
        //2. Abrir uma conexão com o MySQL Server
        ConnectionFactory factory = new ConnectionFactory();
        Connection conexao = factory.getConnection();
        //3. Preparar o comando (solicitar ao MySQL Server que compile o comando SQL previamente)
        PreparedStatement ps = conexao.prepareStatement(sql);
        //4. Substituir os eventuais placeholders
        ps.setString(1, nome);
        ps.setString(2, fone);
        ps.setString(3, email);
        //5. Executar o comando
        ps.execute();
        //6. Fechar os recursos (conexão e o comando preparado)
        ps.close();
        conexao.close();
    }

    public void atualizar() throws Exception{
        //1. Especificar o comando SQL (UPDATE)
        String sql = "UPDATE tb_pessoa SET nome = ?, fone = ?, email = ? WHERE cod_pessoa = ?";
        //2. Abrir uma conexão com o MySQL Server
        ConnectionFactory factory = new ConnectionFactory();
        //try-with-resources (desde a versão 7 do Java SE)
        try(Connection conexao = factory.getConnection();
                //3. Preparar o comando
                PreparedStatement ps = conexao.prepareStatement(sql)){
            //4. Substituir os placeholders
            ps.setString(1, nome);
            ps.setString(2, fone);
            ps.setString(3, email);
            ps.setInt(4, codigo);
            //5. Executar o comando
            ps.execute();
            //6. Fechar os recursos: já está feito pelo try-with-resources
        }
    }

    public Pessoa (String nome, String fone, String email){
        setNome(nome);
        setFone(fone);
        setEmail(email);
    }

    public Pessoa(int codigo, String nome, String fone, String email){
        this(nome, fone, email);
        setCodigo(codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getEmail() {
        return email;
    }
    public String getFone() {
        return fone;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
