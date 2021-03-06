package selenium_testes;

import model.Sala;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

public class TesteSala {
    public static void main(String[] args) throws InterruptedException {

        Sala sala = new Sala();
        Map<String, Boolean> tipoExibicao= new HashMap<>();
        sala.salas = sala.carregaSalas(sala.salas);

        //INFORMA O DIRETORIO DO MOTOR CHROME PARA O SELENIUM
        String userPath = System.getProperty("user.dir");
        String chromeDriverPath = userPath + "/lib/chrome/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        String url = "http://localhost/view/index.html";

        //O método get abre uma página de uma certa url.
        driver.get(url);
        System.out.println("Teste Inicializado");
        driver.manage().window().maximize();
        Thread.sleep(2500);

        //Clica no item de menu cadastrar nova sala
        driver.findElement(By.linkText("Cadastrar nova Sala")).click();
        System.out.println("Abriu Cadastrar nova Sala");
        Thread.sleep(1000);

        String titulo = driver.getTitle();
        if(titulo.equals("Cadastar nova Sala")) {
            System.out.println("Estamos na página correta. Cadastrando sala!");
            driver.findElement(By.id("codigo")).sendKeys("8");
            driver.findElement(By.id("nome")).sendKeys("Sala 8");
            driver.findElement(By.id("capacidade")).sendKeys("50");

            //Check Box
            WebElement cb2D = driver.findElement(By.id("2d"));
            WebElement cb3D = driver.findElement(By.id("3d"));
            WebElement cbOutros = driver.findElement(By.id("Outros"));

            //Alternando clicks entre os checkbox
            cb2D.click();
            cb3D.click();
            cbOutros.click();
            Thread.sleep(500);
            cb3D.click();
            Thread.sleep(500);
            cbOutros.click();
            Thread.sleep(500);
            cb3D.click();
            Thread.sleep(500);
            cb2D.click();

            //Radio Button
            WebElement radio1 = driver.findElement(By.id("true"));
            WebElement radio2 = driver.findElement(By.id("false"));

            //Alternando clicks entre os radio buttons
            radio1.click();
            Thread.sleep(500);
            radio2.click();
            Thread.sleep(500);
            radio1.click();

            driver.findElement(By.id("telefone")).sendKeys("1633778595");
            Thread.sleep(2000);

            //Tipos de exibição
            tipoExibicao.put("2d", cb2D.isSelected());
            tipoExibicao.put("3d", cb3D.isSelected());
            tipoExibicao.put("outros", cbOutros.isSelected());

            //Sala acessível
            Boolean salaAcessivel;
            salaAcessivel = (radio1.isSelected()) ? true : false;

            Sala novaSala = new Sala(
                    Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value")),
                    Integer.parseInt(driver.findElement(By.id("capacidade")).getAttribute("value")),
                    driver.findElement(By.id("nome")).getAttribute("value"),
                    driver.findElement(By.id("telefone")).getAttribute("value"),
                    salaAcessivel,
                    tipoExibicao
            );

            sala.incluirSala(sala.salas, novaSala);
            System.out.println("Inseriu informações");
            Thread.sleep(2500);

            //Botão enviar
            driver.findElement(By.id("submit")).sendKeys("10");
            System.out.println("Gravou informações");
            Thread.sleep(500);

            WebElement button = driver.findElement(By.id("submit"));
            Actions actions = new Actions(driver);
            actions.moveToElement(button).click().build().perform();

            Thread.sleep(1000);

            //Confirmando a inclusão da Sala
            System.out.println("Confirmando inclusão da sala 8");
            for (Sala s : sala.salas) {
                System.out.println(s.toString());
            }
            System.out.println("Sala cadastrada com sucesso!");
            Thread.sleep(2000);
        } else {
            System.out.println("Estamos na página errada!");
        }


        //Clica no menu listar UMA Sala
        driver.findElement(By.linkText("Listar uma Sala")).click();
        System.out.println("Abriu Listar uma Sala");
        Thread.sleep(2000);

        //Lista a sala com Id 4
        driver.findElement(By.id("codigo")).sendKeys("4");

        Integer codigo = Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value"));
        Sala buscaSala = sala.buscarSala(sala.salas, codigo);
        Thread.sleep(2000);

        System.out.println("Buscando e listando sala 4");
        System.out.println(buscaSala);
        driver.findElement(By.xpath("/html/body/form/input[2]")).click();
        Thread.sleep(2000);

        //Altera a variável título para verificação da página
        titulo = driver.getTitle();
        if(titulo.equals("Lista Salas")) {

            //Preenche formulário com informações da sala selecionada
            System.out.println("Estamos na página correta");
            driver.findElement(By.id("codigo")).sendKeys("4");
            System.out.println("Preenchendo com informações da sala 4");

            //JS incluí o atributo readonly no campo "código" do formulário
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement codigoElement = driver.findElement(By.id("codigo"));
            js.executeScript("document.getElementById('codigo').setAttribute('readonly',true)", codigoElement);

            driver.findElement(By.id("nome")).sendKeys(buscaSala.getNome());
            driver.findElement(By.id("capacidade")).sendKeys(String.valueOf(buscaSala.getCapacidade()));
            driver.findElement(By.id("telefone")).sendKeys(buscaSala.getTelefone_sala());

            WebElement radio1 = driver.findElement(By.id("true"));
            WebElement radio2 = driver.findElement(By.id("false"));

            //Preenche o radio button
            if (buscaSala.isAcessivel()) {
                radio1.click();
            } else {
                radio2.click();
            }

            WebElement cb2D = driver.findElement(By.id("2d"));
            WebElement cb3D = driver.findElement(By.id("3d"));
            WebElement cbOutros = driver.findElement(By.id("Outros"));

            if (buscaSala.getTipoExibicao().get("2d")) {
                cb2D.click();
            }
            if (buscaSala.getTipoExibicao().get("3d")) {
                cb3D.click();
            }
            if (buscaSala.getTipoExibicao().get("outros")) {
                cbOutros.click();
            }

            System.out.println("Informações preenchidas!");
            Thread.sleep(2000);

            //Altera dados da sala selecionada
            System.out.println("Agora será alterado as informações da sala selecionada (4)");
            driver.findElement(By.id("nome")).clear();
            driver.findElement(By.id("nome")).sendKeys("Novo nome da Sala");
            driver.findElement(By.id("capacidade")).clear();
            driver.findElement(By.id("capacidade")).sendKeys("100");

            //Alterna o valor dos checkBox
            cb2D.click();
            cb3D.click();
            cbOutros.click();

            tipoExibicao.replace("2d", cb2D.isSelected());
            tipoExibicao.replace("3d", cb3D.isSelected());
            tipoExibicao.replace("outros", cbOutros.isSelected());

            //Alternando clicks entre os radio buttons
            radio2.click();

            driver.findElement(By.id("telefone")).clear();
            driver.findElement(By.id("telefone")).sendKeys("99999999999");

            Boolean salaAcessivel;
            salaAcessivel = (radio1.isSelected()) ? true : false;

            for (Sala s : sala.salas){
                if(Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value")) == s.getCodigo()){
                    s.setNome(driver.findElement(By.id("nome")).getAttribute("value"));
                    s.setCapacidade(Integer.parseInt(driver.findElement(By.id("capacidade")).getAttribute("value")));
                    s.setTipoExibicao(tipoExibicao);
                    s.setAcessivel(salaAcessivel);
                    s.setTelefone_sala(driver.findElement(By.id("telefone")).getAttribute("value"));
                }
            }

            //Conferindo a alteraçao do item
            for (Sala s : sala.salas){
                if(Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value")) == s.getCodigo()){
                    System.out.println(s.toString());
                    System.out.println("Dados da sala alterados com sucessos");
                }
            }

            driver.findElement(By.cssSelector("body > a:nth-child(4)")).sendKeys("10");
            Thread.sleep(1000);

            WebElement button = driver.findElement(By.cssSelector("body > a:nth-child(4)"));
            Actions actions = new Actions(driver);
            actions.moveToElement(button).click().build().perform();

        }
        System.out.println("Alteração de informações concluída!");
        Thread.sleep(2500);

        //Clica no menu seleciona Sala
        driver.findElement(By.xpath("/html/body/p[3]/a")).click();
        Thread.sleep(2500);

        driver.findElement(By.id("codigo")).sendKeys("8");
        Thread.sleep(2500);

        codigo = Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value"));
        buscaSala = sala.buscarSala(sala.salas, codigo);
        Thread.sleep(2500);
        System.out.println(buscaSala);

        driver.findElement(By.xpath("/html/body/form/input[2]")).click();
        Thread.sleep(2500);

        //Altera a variável título para verificação da página
        titulo = driver.getTitle();
        if(titulo.equals("Lista Salas")){
            //Preenche as informações da sala selecionada
            System.out.println("Estamos na página correta");
            driver.findElement(By.id("codigo")).sendKeys("8");

            //JS incluí o atributo readonly no campo "código" do formulário
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement codigoElement = driver.findElement(By.id("codigo"));
            js.executeScript("document.getElementById('codigo').setAttribute('readonly',true)", codigoElement);

            driver.findElement(By.id("nome")).sendKeys(buscaSala.getNome());
            driver.findElement(By.id("capacidade")).sendKeys(String.valueOf(buscaSala.getCapacidade()));
            driver.findElement(By.id("telefone")).sendKeys(buscaSala.getTelefone_sala());

            WebElement radio1 = driver.findElement(By.id("true"));
            WebElement radio2 = driver.findElement(By.id("false"));

            //Preenche o radio button
            if (buscaSala.isAcessivel()) {
                radio1.click();
            } else {
                radio2.click();
            }

            WebElement cb2D = driver.findElement(By.id("2d"));
            WebElement cb3D = driver.findElement(By.id("3d"));
            WebElement cbOutros = driver.findElement(By.id("Outros"));

            if(buscaSala.getTipoExibicao().get("2d")){
                cb2D.click();
            }
            if(buscaSala.getTipoExibicao().get("3d")){
                cb3D.click();
            }
            if(buscaSala.getTipoExibicao().get("outros")){
                cbOutros.click();
            }

            Thread.sleep(2500);

            System.out.println("Testando a remoção de uma sala");
            sala.deletaSala(sala.salas,Integer.parseInt(driver.findElement(By.id("codigo")).getAttribute("value")));

            driver.findElement(By.cssSelector("body > a:nth-child(5)")).sendKeys("10");
            Thread.sleep(1000);

            WebElement button = driver.findElement(By.cssSelector("body > a:nth-child(5)"));
            Actions actions = new Actions(driver);
            actions.moveToElement(button).click().build().perform();

            //Verificando se item foi deletado realmente
            for (Sala s: sala.salas){
                System.out.println(s.toString());
            }
        }

        Thread.sleep(2500);
        System.out.println("Teste finalizado");
        driver.close();
    }
}
