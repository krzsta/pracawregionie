package pl.pracawregionie.configs;

import pl.pracawregionie.Gender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class ConfigBase {

    protected final String emailSuffix;
    protected String password;
    protected String emailPrefix;
    protected WebDriver driver;
    protected String basicURL;
    protected ChromeOptions chromeOptions;
    protected String userName;
    protected String userSurname;
    protected String userLogin;
    protected String fullEmail;
    protected Gender gender; // mozna zrzutowac do int jesli trzeba przez (int)gender
    protected String strGender;

    public ConfigBase(String password, String emailPrefix, String emailSuffix, String basicURL) throws IOException {
        this.password = password;
        this.emailPrefix = emailPrefix;
        this.emailSuffix = emailSuffix;
        this.basicURL = basicURL;
        this.userName = readRandomFromFile("/src/test/resources/files/namesForEmail.csv");
        this.userSurname = readRandomFromFile("/src/test/resources/files/surnamesForEmail.csv");
        this.userLogin = userName + userSurname;
        this.fullEmail = emailPrefix + userLogin + emailSuffix;
        this.gender = this.userName.toLowerCase().endsWith("a") ? Gender.FEMALE : Gender.MALE;
        this.strGender = this.gender.toString();
    }

    //prawdopodobnie mozna dodac odpowiednie importy i tutaj atrybut @BeforeTest, a te wołania w klasach potomnych do wyjebania
    protected void beforeTest() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized", "--disable-extensions");
        driver = new ChromeDriver(chromeOptions);
        driver.get(basicURL);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //j.w. @AfterTest
    protected void afterTest() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    private String readRandomFromFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();

        // try with resources == auto close stream, no need to use *.close()
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + path))) {
            for (String line; (line = reader.readLine()) != null; ) { //powinno dzialac ale nie kompiluje mi sie wiec mozliwe ze jednak nie ;)
                lines.add(line);
            }
        }

        return lines.size() > 0
                ? lines.get(new Random().nextInt(lines.size()))
                : "";
    }
}
