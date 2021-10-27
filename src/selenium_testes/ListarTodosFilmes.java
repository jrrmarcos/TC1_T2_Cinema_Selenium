package selenium_testes;

import model.Filme;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class ListarTodosFilmes {

    public static void main(String[] args) throws InterruptedException {
        Filme filme = new Filme();

        //Iniciando o teste com uma lista vazia
        ArrayList<Filme> todosFilmes = filme.filmes;

        //INFORMA O DIRETORIO DO MOTOR CHROME PARA O SELENIUM
        String userPath = System.getProperty("user.dir");
        String chromeDriverPath = userPath + "/lib/chrome/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        String url = "http://localhost/view/index.html";
        //O método get abre uma página de uma certa url.
        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);

        //clica no item de menu Listar todos os Filmes
        driver.findElement(By.linkText("Listar todos os Filmes")).click();

        //Chama método responsável por verificar se existe filmes cadastrados
        //e navegar pelas páginas
        ListarTodosFilmes.verificaListaFilmes(todosFilmes, url, driver);

        //Acrescentando um conjunto de filmes a lista para teste
        todosFilmes = filme.carregaFilmes(filme.filmes);

        String titulo = driver.getTitle();
        // Teste para verificar se voltamos ao menu principal de forma a evitar erros
        if (titulo.equals("Menu Principal Cinema")) {
            //Chama método responsável por verificar se existe filmes cadastrados
            //e navegar pelas páginas
            ListarTodosFilmes.verificaListaFilmes(todosFilmes, url, driver);
        }

        Thread.sleep(5000);
        driver.close();
    }

    private static void verificaListaFilmes(ArrayList<Filme> todosFilmes, String url, WebDriver driver) throws InterruptedException {
        if (todosFilmes.isEmpty()) {
            // LISTA VAZIA
            url = "http://localhost/view/nullFilmes.html";
            driver.findElement(By.linkText("Listar todos os Filmes")).click();
            driver.get(url);
            Thread.sleep(1500);

            //Testes de navegação as páginas são as mesmas testadas na classe TesteFilme
            driver.findElement(By.linkText("Cadastrar novo Filme")).click();
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Voltar ao Menu Principal")).click();
            Thread.sleep(2000);

        } else {
            // OCORRE SOMENTE SE A LISTA TIVER ELEMENTOS
            url = "http://localhost/view/todosFilmes.html";
            driver.findElement(By.linkText("Listar todos os Filmes")).click();
            driver.get(url);

            Integer i = 0;

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement codigoElement = driver.findElement(By.id("novoTeste"));

            while (i < todosFilmes.size()) {
                String codigo =String.valueOf(todosFilmes.get(i).getCodigo());
                String titulo = String.valueOf(todosFilmes.get(i).getNome());
                String ano = String.valueOf(todosFilmes.get(i).getAno_de_lancamento());
                String diretor = String.valueOf(todosFilmes.get(i).getDiretor());
                String ator = todosFilmes.get(i).getAtor();
                String f = "Código: " + codigo + " || Título: " + titulo + " || Diretor: " + diretor + " || Ator/Atriz principal: " + ator + " || Ano de lançamento: " + ano;
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
