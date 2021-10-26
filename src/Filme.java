import java.util.ArrayList;

public class Filme {

    private int codigo, ano_de_lancamento;
    private String nome, diretor, ator;
    private ArrayList<Filme> filmes = new ArrayList<>();

    public Filme(){
    }

    public Filme(int codigo, int ano_de_lancamento, String nome, String diretor, String ator){
        this.codigo = codigo;
        this.ano_de_lancamento = ano_de_lancamento;
        this.nome = nome;
        this.diretor = diretor;
        this.ator = ator;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getAno_de_lancamento() {
        return ano_de_lancamento;
    }

    public void setAno_de_lancamento(int ano_de_lancamento) {
        this.ano_de_lancamento = ano_de_lancamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getAtor() {
        return ator;
    }

    public void setAtor(String ator) {
        this.ator = ator;
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "\ncodigo do filme=" + codigo +
                "\nano de lancamento=" + ano_de_lancamento +
                "\nnome='" + nome + '\'' +
                "\ndiretor='" + diretor + '\'' +
                "\nator='" + ator +
                '}';
    }

    public ArrayList<Filme> carregaFilmes(ArrayList<Filme> filmes){

        this.filmes.add(new Filme(1,1997,"Titanic","James Cameron","Leonardo DiCaprio"));
        this.filmes.add(new Filme(2,2016,"Deadpool"," Tim Miller","Ryan Reynolds"));
        this.filmes.add(new Filme(3,2013,"Invocação do Mal","James Wan","Vera Farmiga"));
        this.filmes.add(new Filme(4,2013,"Truque de Mestre","Louis Leterrier","Mark Ruffalo"));
        this.filmes.add(new Filme(5,2017,"It: A Coisa","Andy Muschietti","Bill Skarsgård"));
        this.filmes.add(new Filme(6,1988,"O Máskara","Chuck Russell","Jim Carrey"));
        this.filmes.add(new Filme(7,1991,"It – Capítulo Dois","Andy Muschietti","Bill Skarsgård"));

        return this.filmes;
    }

    public ArrayList<Filme> incluirFilme(ArrayList<Filme> filmes, Filme filme){
        filmes.add(filme);
        return filmes;
    }

    public Filme buscarFilme(ArrayList<Filme> filmes, Integer codigo){
        for (Filme f : filmes) {
            if (f.codigo == codigo){
                return f;
            }
        }
        return null;
    }

    public void deletaFilme(ArrayList<Filme> filmes, int codigo){
        for (Filme f : filmes) {
            if (f.codigo == codigo){
                filmes.remove(f);
                break;
            }
        }
    }

    public void alteraFilme(ArrayList<Filme> filmes, Filme filme){
        for (Filme f : filmes) {
            if (f.codigo == filme.codigo){
                f.setNome(filme.nome);
                f.setAno_de_lancamento(filme.ano_de_lancamento);
                f.setAtor(filme.ator);
                f.setDiretor(filme.diretor);
                break;
            }
        }
    }

}
