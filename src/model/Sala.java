package model;

import java.util.ArrayList;

public class Sala {

    public ArrayList<Sala> salas = new ArrayList<>();
    private int codigo, capacidade;
    private String nome, tipo_de_exibicao, telefone_sala;
    private boolean acessivel;

    public Sala() {
    }

    public Sala(int codigo, int capacidade, String nome, String tipo_de_exibicao, String telefone_sala, boolean acessivel) {
        this.setCodigo(codigo);
        this.setCapacidade(capacidade);
        this.setNome(nome);
        this.setTipo_de_exibicao(tipo_de_exibicao);
        this.setTelefone_sala(telefone_sala);
        this.setAcessivel(acessivel);
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

    public String getTipo_de_exibicao() {
        return tipo_de_exibicao;
    }

    public void setTipo_de_exibicao(String tipo_de_exibicao) {
        this.tipo_de_exibicao = tipo_de_exibicao;
    }

    public String getTelefone_sala() { return telefone_sala; }

    public void setTelefone_sala(String telefone_sala) {
        if(telefone_sala.length() == 10){
            telefone_sala = "(" + telefone_sala.substring(0,2) + ")" +
                    telefone_sala.substring(2,6) + "-" + telefone_sala.substring(6,10);
        } else if (telefone_sala.length() == 11){
            telefone_sala = "(" + telefone_sala.substring(0,2) + ")" +
                    telefone_sala.substring(2,7) + "-" + telefone_sala.substring(7,11);
        } else { telefone_sala = ""; }
        this.telefone_sala = telefone_sala; }

    public boolean isAcessivel() {
        return acessivel;
    }

    public void setAcessivel(boolean acessivel) {
        this.acessivel = acessivel;
    }

    @Override
    public String toString() {

        return  "\ncodigo da sala = " + codigo +
                "\ncapacidade = " + capacidade +
                "\nnome = " + nome +
                "\ntipo de exibição = " + tipo_de_exibicao +
                "\ntelefone da sala = " + telefone_sala +
                "\nacessivel = " + acessivel +
                "\n";
    }

    public ArrayList<Sala> carregaSalas(ArrayList<Sala> salas){
        Sala aux = new Sala();

        Sala s1 = new Sala(1,30,"Sala 1","3D","33115520",true);
        Sala s2 = new Sala(2,15,"Sala 2","2D","33115590",true);
        Sala s3 = new Sala(3,50,"Sala 3","2D","33115580", false);
        Sala s4 = new Sala(4,60,"Sala 4","2D","33115570",true);
        Sala s5 = new Sala(5,45,"Sala 5","3D","33115510", false);
        Sala s6 = new Sala(6,42,"Sala 6","4D","33115530", true);
        Sala s7 = new Sala(7,55,"Sala 7","2D","33115540", false);

        salas = aux.incluirSala(salas,s1);
        salas = aux.incluirSala(salas,s2);
        salas = aux.incluirSala(salas,s3);
        salas = aux.incluirSala(salas,s4);
        salas = aux.incluirSala(salas,s5);
        salas = aux.incluirSala(salas,s6);
        salas = aux.incluirSala(salas,s7);

        return salas;
    }

    public Sala buscarSala(ArrayList<Sala> salas, Integer codigo){
        for (Sala s : salas) {
            if (s.codigo == codigo){
                return s;
            }
        }
        return null;
    }

    public ArrayList<Sala> deletaSala(ArrayList<Sala> salas, int codigo){
        for (Sala s : salas) {
            if (s.codigo == codigo){
                salas.remove(s);
                System.out.println("\nFilme deletado com sucesso\n");
                return salas;
            }
        }
        return salas;
    }

    public ArrayList<Sala> incluirSala(ArrayList<Sala> salas, Sala sala){
        for (Sala item : salas){
            if (item.getCodigo() == codigo){
                System.out.println("Sala não cadastrada - Código informa já existe!");
                return salas;
            }
        }
        salas.add(sala);
        return salas;
    }

    public ArrayList<Sala> alterarSala(ArrayList<Sala> salas, Sala sala){
        for (Sala s : salas) {
            if (s.codigo == sala.codigo){
                s.setCapacidade(sala.getCapacidade());
                s.setNome(sala.getNome());
                s.setTipo_de_exibicao(sala.getTipo_de_exibicao());
                String tel = sala.getTelefone_sala().replace("(","").replace(")","").replace("-","");
                s.setTelefone_sala(tel);
                s.setAcessivel(sala.isAcessivel());
                return salas;
            }
        }
        return salas;
    }
}
