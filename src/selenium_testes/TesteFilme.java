package selenium_testes;

import model.Filme;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFilme {
    public static void main(String[] args) throws InterruptedException {
        Filme filme = new Filme();

        filme.filmes = filme.carregaFilmes(filme.filmes);

        //INFORMA O DIRETORIO DO MOTOR CHROME PARA O SELENIUM
        String userPath = System.getProperty("user.dir");
        String chromeDriverPath = userPath + "/lib/chromeDriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        String url = "http://localhost/view/index.html";
        //O método get abre uma página de uma certa url.
        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(2000);

        //clica no item de menu cadastrar novo filme
        driver.findElement(By.linkText("Cadastrar novo Filme")).click();

        String titulo = driver.getTitle();
        if(titulo.equals("Cadastrar Novo Filme")){

            driver.findElement(By.id("codigo")).sendKeys("7");
            driver.findElement(By.id("nome")).sendKeys("A Família Addams");
            driver.findElement(By.id("lancamento")).sendKeys("1991");
            driver.findElement(By.id("diretor")).sendKeys("Barry Sonnenfeld");
            driver.findElement(By.id("ator")).sendKeys("Anjelica Huston");


            Thread.sleep(1000);

            //Obtendo dados do novo filme
            Filme novofilme = new Filme(
                    Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value")),
                    Integer.parseInt(driver.findElement(By.id("lancamento")).getAttribute("value")),
                    driver.findElement(By.id("nome")).getAttribute("value"),
                    driver.findElement(By.id("diretor")).getAttribute("value"),
                    driver.findElement(By.id("ator")).getAttribute("value")
            );

            filme.incluirFilme(filme.filmes,novofilme);

            //Print no terminal apenas para confirmar a inclusão do filme
            for(Filme f : filme.filmes){
                System.out.println(f.toString());
            }
            System.out.println("Filme cadastrado com sucesso");

            driver.findElement(By.xpath("/html/body/form/input[6]")).click();

            Thread.sleep(2000);

        } else {
            System.out.println("Estamos na página errada");

        }

        //clica no item de menu seleciona Filme
        driver.findElement(By.linkText("Listar um Filme")).click();

        driver.findElement(By.id("codigo")).sendKeys("5");
        Thread.sleep(2000);

        Integer codigo = Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value"));

        Filme buscaFilme = filme.buscarFilme(filme.filmes, codigo);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/form/input[2]")).click();
        Thread.sleep(2000);

        //altera a variável título para verificação da página
        titulo = driver.getTitle();
        if(titulo.equals("Lista Filmes")) {
            System.out.println("Estamos na página correta");
            driver.findElement(By.id("codigo")).sendKeys("5");

            //JS incluí o atributo readonly no campo "código" do formulário
            JavascriptExecutor js = (JavascriptExecutor)driver;
            WebElement codigoElement = driver.findElement(By.id("codigo"));
            js.executeScript("document.getElementById('codigo').setAttribute('readonly',true)",codigoElement);

            driver.findElement(By.id("nome")).sendKeys(buscaFilme.getNome());
            driver.findElement(By.id("lancamento")).sendKeys(String.valueOf(buscaFilme.getAno_de_lancamento()));
            driver.findElement(By.id("diretor")).sendKeys(buscaFilme.getDiretor());
            driver.findElement(By.id("ator")).sendKeys(buscaFilme.getAtor());
            Thread.sleep(2000);

            //Alterando os dados do filme listado
            driver.findElement(By.id("nome")).clear();
            driver.findElement(By.id("nome")).sendKeys("Os Caçadores da Arca Perdida");
            driver.findElement(By.id("lancamento")).clear();
            driver.findElement(By.id("lancamento")).sendKeys("1981");
            driver.findElement(By.id("diretor")).clear();
            driver.findElement(By.id("diretor")).sendKeys("Steven Spielberg");
            driver.findElement(By.id("ator")).clear();
            driver.findElement(By.id("ator")).sendKeys("Harrison Ford");

            Filme novofilme = new Filme(
                    Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value")),
                    Integer.parseInt(driver.findElement(By.id("lancamento")).getAttribute("value")),
                    driver.findElement(By.id("nome")).getAttribute("value"),
                    driver.findElement(By.id("diretor")).getAttribute("value"),
                    driver.findElement(By.id("ator")).getAttribute("value")
            );

            filme.alteraFilme(filme.filmes, novofilme);
            driver.findElement(By.linkText("Alterar Item")).click();

            //Impressão no terminal apenas para confirmar alteração
            System.out.println(filme.buscarFilme(filme.filmes, 5));

            Thread.sleep(2000);
        }

        //clica no item de menu seleciona Filme
        driver.findElement(By.linkText("Listar um Filme")).click();

        driver.findElement(By.id("codigo")).sendKeys("5");
        Thread.sleep(2000);

        codigo = Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value"));

        buscaFilme = filme.buscarFilme(filme.filmes, codigo);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/form/input[2]")).click();
        Thread.sleep(2000);

        //altera a variável título para verificação da página
        titulo = driver.getTitle();
        if(titulo.equals("Lista Filmes")) {
            System.out.println("Estamos na página correta");
            driver.findElement(By.id("codigo")).sendKeys("5");

            //JS incluí o atributo readonly no campo "código" do formulário
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement codigoElement = driver.findElement(By.id("codigo"));
            js.executeScript("document.getElementById('codigo').setAttribute('readonly',true)", codigoElement);

            driver.findElement(By.id("nome")).sendKeys(buscaFilme.getNome());
            driver.findElement(By.id("lancamento")).sendKeys(String.valueOf(buscaFilme.getAno_de_lancamento()));
            driver.findElement(By.id("diretor")).sendKeys(buscaFilme.getDiretor());
            driver.findElement(By.id("ator")).sendKeys(buscaFilme.getAtor());
            Thread.sleep(2000);

            filme.deletaFilme(filme.filmes, codigo);
            driver.findElement(By.linkText("Apagar Item")).click();

            //Print no terminal para confirmar exclusão do filme
            //valor esperado "null"
            System.out.println(filme.buscarFilme(filme.filmes, 5));

        }

        Thread.sleep(2000);
        driver.close();

    }
}
