package cases;

/*
Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).

Сценарий должен состоять из следующих частей:

1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты
(чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
3) повторный вход в только что созданную учётную запись,
4) и ещё раз выход.

В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.

Проверки можно никакие не делать, только действия -- заполнение полей, нажатия на кнопки и ссылки.
Если сценарий дошёл до конца, то есть созданный пользователь смог выполнить вход и выход -- значит создание прошло успешно.
 */

import org.junit.After;
import org.junit.Test;
import pages.CreateAccountPage;
import pages.ShopMainPage;

import static config.app.LITECART_SHOP_MAIN;

public class SignUpTest extends AbstractTest {

    ShopMainPage mainPage;
    CreateAccountPage createAccountPage;
    String username;
    String password;

    @Test
    public void signUpTest() {
        openPage(LITECART_SHOP_MAIN);

        mainPage = new ShopMainPage(driver);
        mainPage.clickOnCreateAccountLink();

        createAccountPage = new CreateAccountPage(driver);
        createAccountPage.fillAllFieldsWithRandomData();
        username = createAccountPage.getEmail();
        password = createAccountPage.getPassword();
        mainPage.logout();

        mainPage.login(username, password);
        mainPage.logout();
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
