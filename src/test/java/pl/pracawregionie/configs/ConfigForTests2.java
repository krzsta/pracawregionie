package pl.pracawregionie.configs;

public class ConfigForTests2 extends ConfigBase {

    //na 99% nie potrzebujesz tych dwoch oddzielnych klas do tego i mozesz dziedziczyc bezposrednio bo ConfigBase
    public ConfigForTests2() throws Exception {
        super("QWEqwe123", "testspwr+", "@gmail.com", "http://dev:dev@stage.pracawregionie.pl/");
    }
}