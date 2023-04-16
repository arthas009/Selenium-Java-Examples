import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class Functionality_LoginTests
 * Being used to execute tests based on login scenarios on Trendyol
 */
public class Functionality_LoginTests extends BaseClass {

    @Test(priority = 1, description = "Tries to login with correct user values. " +
            "Expects 'Hesabım' hover menu is shown.")
    public static void Verify_Login_ValidLogin() {
        homePageLogin.performLogin(getTestingUsername(), getTestingPassword());
        Assert.assertTrue(homePageLogin.checkHoverMyAccountIsVisible());
    }

    @Test(priority = 2, description = "Tries to login with a incorrect e-mail format (without @)." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public static void Verify_Login_InvalidUsernameAttempt() {
        Assert.assertFalse(homePageLogin.performLogin("yusufalti333gmail.com", getTestingPassword()));
        Assert.assertTrue(homePageLogin.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 3, description = "Tries to login with a incorrect e-mail format (length longer than 255). " +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public static void Verify_Login_ExceedingUsernameLength() {
        Assert.assertFalse(homePageLogin.performLogin("ASDQWEWQE1231231432AWQWEZXCXCZXCQ@@@ZĞCKOFKSZĞCKOFKSDOPFKPOD" +
                "AOKGPADGPĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSP" +
                "DOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISADFOGMDFPGMFOINOIRENTORENTEAOTERADF" +
                "GFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRE" +
                "NTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOG" +
                "MDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOK" +
                "GPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDDOPFKPO" +
                "DAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFD", getTestingPassword()));
        Assert.assertTrue(homePageLogin.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 4, description = "Tries to login with a incorrect e-mail format (more ambigue characters only)." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public static void Verify_Login_AmbigueUsernameCharacters() {
        Assert.assertFalse(homePageLogin.performLogin("--*1*23*140506'434*3466345*34*--123-12-5436][}][{[½{½{½$½$½@@@..;;", getTestingPassword()));
        Assert.assertTrue(homePageLogin.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 5, description = "Tries to login with a incorrect e-mail format (empty username)." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public static void Verify_Login_EmptyUsername() {
        Assert.assertFalse(homePageLogin.performLogin("", getTestingPassword()));
        Assert.assertTrue(homePageLogin.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 5, description = "Tries to login with a correct e-mail format but not registered before." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public static void Verify_Login_NonExistsUsername() {
        Assert.assertFalse(homePageLogin.performLogin("yusufalti4444@gmail.com", getTestingPassword()));
        Assert.assertTrue(homePageLogin.checkWrongPasswordOrMailWarningIsShown());
    }

    @Test(priority = 6, description = "Tries to login with a correct mail and incorrect password format (empty password)." +
            "Verifies Invalid password warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public static void Verify_Login_EmptyPassword() {
        Assert.assertFalse(homePageLogin.performLogin("yusufalti333@gmail.com", ""));
        Assert.assertTrue(homePageLogin.checkEmptyPasswordWarningIsShown());
    }

    @Test(priority = 7, description = "Tries to login with a correct mail and incorrect password format (empty password)." +
            "Verifies Invalid password warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public static void Verify_Login_ExceedingPasswordLength() {
        Assert.assertFalse(homePageLogin.performLogin("yusufalti333@gmail.com", "ASDQWEWQE1.231231432AWQWEZXCXCZXCQ@@@ZĞCKOFKSZĞCKOFKSDOPFKP" +
                "ODAOKGPADGPĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZX" +
                "OCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISADFOGMDFPGMFOINOIRENTOR" +
                "ENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGP" +
                "DFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCK" +
                "OFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORE" +
                "NTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGM" +
                "DFPGMFOINOIRENTORENTEAOTERADFGFGFDDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFD"));
        Assert.assertTrue(homePageLogin.checkWrongPasswordOrMailWarningIsShown());
    }

}
