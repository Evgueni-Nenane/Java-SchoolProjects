package model;

public class Sessao {

    private static Utilizador utilizadorLogado;

    public static void iniciarSessao(Utilizador utilizador) {
        utilizadorLogado = utilizador;
    }

    public static void terminarSessao() {
        utilizadorLogado = null;
    }

    public static Utilizador getUtilizadorLogado() {
        return utilizadorLogado;
    }

    public static boolean estaLogado() {
        return utilizadorLogado != null;
    }
}	