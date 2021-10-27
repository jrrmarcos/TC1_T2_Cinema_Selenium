package selenium_testes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavegacaoMenuFilme {
    public static void main(String[] args) throws InterruptedException {

        //INFORMA O DIRETORIO DO MOTOR CHROME PARA O SELENIUM
        String userPath = System.getProperty("user.dir");
        String chromeDriverPath = userPath + "/lib/chrome/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        String url = "http://localhost/view/index.html";

        //O método get abre uma página de uma certa url.
        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(2000);

        //Verifica comportamento do item de menu cadastrar novo filme
        driver.findElement(By.linkText("Cadastrar novo Filme")).click();
        System.out.println("Entrei na tela de cadastro de filme!");
        Thread.sleep(2000);

        //Ir para a página anterior
        driver.navigate().back();
        System.out.println("Voltei para a página inicial!");
        Thread.sleep(2000);

        //Ir para a próxima posterior novamente
        driver.navigate().forward();
        System.out.println("Fui para a tela de cadastrar filmes novamente!");
        Thread.sleep(2000);

        //Ir para a página anterior
        driver.navigate().back();
        System.out.println("Voltei para a tela inicial!");
        Thread.sleep(2000);


        //Verifica comportamento do item de menu listar um filme
        driver.findElement(By.linkText("Listar um Filme")).click();
        System.out.println("Entrei na tela de listar filme!");
        Thread.sleep(2000);

        //Ir para a página anterior
        driver.navigate().back();
        System.out.println("Voltei para a página inicial!");
        Thread.sleep(2000);

        //Ir para a próxima posterior novamente
        driver.navigate().forward();
        System.out.println("Voltei para a tela de listar filme novamente!");
        Thread.sleep(2000);

        //Ir para a página anterior
        driver.navigate().back();
        System.out.println("Por fim, voltei para a página inicial!");
        Thread.sleep(2000);

        driver.close();
        System.out.println("Teste finalizado.");
    }
}
