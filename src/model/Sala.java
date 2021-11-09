package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sala {

    private int codigo, capacidade;
    private String nome, telefone_sala;
    private boolean acessivel;
    private Map<String, Boolean> tipoExibicao = new HashMap<>();
    public ArrayList<Sala> salas = new ArrayList<>();

    public Sala() {
    }

    public Sala(int codigo, int capacidade, String nome, String telefone_sala, boolean acessivel, Map<String, Boolean> tipoExibição) {
        this.codigo = codigo;
        this.capacidade = capacidade;
        this.nome = nome;
        this.telefone_sala = telefone_sala;
        this.acessivel = acessivel;
        this.tipoExibicao = tipoExibição;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone_sala() {
        return telefone_sala;
    }

    public void setTelefone_sala(String telefone_sala) {
        this.telefone_sala = telefone_sala;
    }

    public boolean isAcessivel() {
        return acessivel;
    }

    public void setAcessivel(boolean acessivel) {
        this.acessivel = acessivel;
    }

    public Map<String, Boolean> getTipoExibicao() {
        return tipoExibicao;
    }

    public void setTipoExibicao(Map<String, Boolean> tipoExibicao) {
        this.tipoExibicao = tipoExibicao;
    }

    @Override
    public String toString() {
        String str2D;
        String str3D;
        String strOutros;
        String salaAcessivel;

        str2D = tipoExibicao.get("2d") ?  "Sim" :  "Não";
        str3D = tipoExibicao.get("3d") ?  "Sim" :  "Não";
        strOutros = tipoExibicao.get("outros") ?  "Sim" :  "Não";
        salaAcessivel = acessivel ? "Sim" : "Não";

        return  "\ncodigo da sala = " + codigo +
                "\ncapacidade = " + capacidade +
                "\nnome = " + nome +
                "\ntipo de exibição: " +
                "\n\t2D: " + str2D +
                "\n\t3D: " + str3D +
                "\n\tOutros: " + strOutros +
                "\ntelefone da sala = " + telefone_sala +
                "\nacessivel = " + salaAcessivel;
    }

    //Incluir Salas
    public ArrayList<Sala> incluirSala(ArrayList<Sala> salas, Sala sala){
        salas.add(sala);
        return salas;
    }

    //Buscando/carregando Salas
    public ArrayList<Sala> carregaSalas(ArrayList<Sala> salas){
        ArrayList carregaArraySala = new ArrayList();

        tipoExibicao.put("2d",true);
        tipoExibicao.put("3d",true);
        tipoExibicao.put("outros",true);
        carregaArraySala.add(new Sala(1,20,"Sala 1","33115540",true,tipoExibicao));

        tipoExibicao.replace("2d",false);
        tipoExibicao.replace("3d",true);
        tipoExibicao.replace("outros",true);
        carregaArraySala.add(new Sala(2,25,"Sala 2","33115530",true,tipoExibicao));

        tipoExibicao.replace("2d",true);
        tipoExibicao.replace("3d",false);
        tipoExibicao.replace("outros",true);
        carregaArraySala.add(new Sala(3,20,"Sala 3","33115510",false, tipoExibicao));

        tipoExibicao.replace("2d",true);
        tipoExibicao.replace("3d",true);
        tipoExibicao.replace("outros",false);
        carregaArraySala.add(new Sala(4,30,"Sala 4","33115570",false,tipoExibicao));

        tipoExibicao.replace("2d",false);
        tipoExibicao.replace("3d",false);
        tipoExibicao.replace("outros",true);
        carregaArraySala.add(new Sala(5,15,"Sala 5","33115580",false, tipoExibicao));

        tipoExibicao.replace("2d",true);
        tipoExibicao.replace("3d",false);
        tipoExibicao.replace("outros",false);
        carregaArraySala.add(new Sala(6,22,"Sala 6","33115590",true, tipoExibicao));

        tipoExibicao.replace("2d",false);
        tipoExibicao.replace("3d",true);
        tipoExibicao.replace("outros",false);
        carregaArraySala.add(new Sala(7,25,"Sala 7","33115520",true, tipoExibicao));

        return carregaArraySala;
    }

    //Buscando sala específica
    public Sala buscarSala(ArrayList<Sala> salas, Integer codigo){

        for (Sala s : salas) {
            if (s.codigo == codigo){
                return s;
            }
        }
        return null;
    }

    //Removendo sala
    public void deletaSala(ArrayList<Sala> salas, int codigo){
        for (Sala s : salas) {
            if (s.codigo == codigo){
                salas.remove(s);
                System.out.println("\nSala deletada com sucesso!");
                break;
            }
        }
    }
}
