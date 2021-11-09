package selenium_testes;

import model.Sala;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class ListarTodasSalas {
    public static void main(String[] args) throws InterruptedException {
        Sala sala = new Sala();

        //Iniciando o teste com uma lista vazia
        ArrayList<Sala> todasSalas = sala.salas;

        //INFORMA O DIRETORIO DO MOTOR CHROME PARA O SELENIUM
        String userPath = System.getProperty("user.dir");
        String chromeDriverPath = userPath + "/lib/chrome/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        String url = "http://localhost/view/index.html";

        //O método get abre uma página de uma certa url.
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println("Teste inicializado");
        Thread.sleep(2000);

        //Chama método responsável por verificar se existe salas cadastradas e navegar pelas páginas
        ListarTodasSalas.verificaListaSalas(todasSalas, url, driver);

        //Acrescentando um conjunto de salas a lista para teste
        todasSalas = sala.carregaSalas(sala.salas);
        System.out.println("Adicionou um conjunto de salas à lista");
        Thread.sleep(2500);

        String titulo = driver.getTitle();
        // Teste para verificar se voltamos ao menu principal no intuito de evitar erros
        if (titulo.equals("Menu Principal Cinema")) {
            //Chama método responsável por verificar se existe salas cadastradas e navegar pelas páginas
            ListarTodasSalas.verificaListaSalas(todasSalas, url, driver);
        }

        Thread.sleep(2500);
        System.out.println("Teste finalizado");
        driver.close();
    }

    private static void verificaListaSalas(ArrayList<Sala> todasSalas, String url, WebDriver driver) throws InterruptedException {
        if (todasSalas.isEmpty()) {
            //Lista vazia
            url = "http://localhost/view/nullSalas.html";
            driver.findElement(By.linkText("Listar todas as Salas")).click();
            driver.get(url);
            System.out.println("Lista de salas vazia!");
            Thread.sleep(2500);

            //Testes de navegação as páginas são as mesmas testadas na classe TesteSala
            driver.findElement(By.linkText("Cadastrar nova Sala")).click();
            System.out.println("Cadastrar nova Sala (Inseridos na função principal)");
            Thread.sleep(2500);
            driver.navigate().back();
            System.out.println("Voltou para tela de listagem");
            Thread.sleep(2500);
            driver.findElement(By.linkText("Voltar ao Menu Principal")).click();
            System.out.println("Voltou para o menu princípal");
            Thread.sleep(2500);

        } else {
            //Ocorre só se a lista tiver elementos
            url = "http://localhost/view/todasSalas.html";
            driver.findElement(By.linkText("Listar todas as Salas")).click();
            System.out.println("Listando salas já adicionadas");
            driver.get(url);

            Integer i = 0;
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement codigoElement = driver.findElement(By.id("novoTeste"));

            while (i < todasSalas.size()) {
                String codigo =String.valueOf(todasSalas.get(i).getCodigo());
                String nome = String.valueOf(todasSalas.get(i).getNome());
                String capacidade = String.valueOf(todasSalas.get(i).getCapacidade());
                String telefone = todasSalas.get(i).getTelefone_sala();
                String acessivel = todasSalas.get(i).isAcessivel() ? "Sim" : "Não";
                String str2d = todasSalas.get(i).getTipoExibicao().get("2d") ? "Sim" : "Não";
                String str3d = todasSalas.get(i).getTipoExibicao().get("3d") ? "Sim" : "Não";
                String strOutros = todasSalas.get(i).getTipoExibicao().get("outros") ? "Sim" : "Não";

                String f = "Código: " + codigo +
                        " || Nome: " + nome +
                        " || Capacidade: " + capacidade +
                        " || Telefone: " + telefone +
                        " || Sala Acessível: " + acessivel +
                        " || Sala 2D: " + acessivel +
                        " || Sala 3D: " + acessivel +
                        " || Sala outros tipos de exibição: " + acessivel;
               System.out.println(f);

                js.executeScript("var texto = " + " \""+f+"\" " + ";" +
                        "var para = document.createElement(\"p\");\n" +
                        "var node = document.createTextNode(texto);\n" +
                        "para.appendChild(node);\n" +
                        "var element = document.getElementById(\"novoTeste\");\n" +
                        "element.appendChild(para);", codigoElement);
                i=i+1;
            }
        }
    }
}