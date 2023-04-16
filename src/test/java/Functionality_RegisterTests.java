import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Class Functionality_RegisterTests
 * Being used to execute tests based on registering to Trendyol
 */
public class Functionality_RegisterTests extends BaseClass {

    @Test(priority = 1, description = "Performs an end-to-end registration with correct e-mail and correct password" +
            "Verifies registration can be done successfully when username and password is in correct format and verification code is correct.")
    public static void Verify_Registering_ValidRegistration() {
        Assert.assertTrue(homePageRegister.performRegistrationAndReturnResult(getRegisterTestingUsername(), getTestingPassword(),
                getRegisterTestingMailPassword()));
    }

    @Test(priority = 2, description = "Performs an end-to-end registration with empty mail and valid password" +
            "Verifies enter e-mail or password warning after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_NoUsername() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("", getTestingPassword()));
        Assert.assertTrue(homePageRegister.checkEmptyEmailOrPasswordWarningIsShown());
    }

    @Test(priority = 3, description = "Performs an end-to-end registration with an e-mail without '@' sign." +
            "Verifies invalid e-mail format warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_InvalidEmailAttempt_WithoutAtSign() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti123213gmail", "Test1234."));
        Assert.assertTrue(homePageRegister.checkWrongEmailFormatWarningIsShown());
    }

    @Test(priority = 4, description = "Performs an end-to-end registration with an e-mail including no mail server on it. " +
            "Verifies invalid e-mail format warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_InvalidEmailAttempt_WithoutValidMailServer() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti123213@", "Test1234."));
        Assert.assertTrue(homePageRegister.checkWrongEmailFormatWarningIsShown());
    }

    @Test(priority = 5, description = "Performs an end-to-end registration with an e-mail including no username before '@' sign. " +
            "Verifies invalid e-mail format warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_InvalidEmailAttempt_WithoutValidMailUsername() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("@gmail.com", "Test1234."));
    }

    @Test(priority = 6, description = "Performs an end-to-end registration with a e-mail already registered to trendyol system." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_InvalidEmailAttempt_ExistingUser() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti1997@gmail.com", "Test1234."));
    }

    @Test(priority = 7, description = "Performs an end-to-end registration with a e-mail exceeding 255 characters" +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_InvalidEmailAttempt_ExceedingLongUsername() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("ASD123.ASDPZXCJPIJSADPIASDJPAJPIVMIXPDNFSADOIPFNIOSPWEPRKPWOERPXZCMKSDMFOIAMXVCDS" +
                "AOPIFMSDMFPOWEOPMVDKDFAFMASDPMVXPOASDIPOFNOXCVOSADFPOasdfadsfgdafgaVZCXVADFASDFDSAFSAFDSA@gmail.com", "Test1234."));
    }

    @Test(priority = 8, description = " Performs an end-to-end registration with correct e-mail and wrong password with incorrect format (lowercase letters only)." +
            "Verifies include number in password warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_OnlyLowercasePassword() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "onlylowercase"));
        Assert.assertTrue(homePageRegister.checkWrongPasswordIncludeNumberWarningIsShown());
    }

    @Test(priority = 9, description = "Performs an end-to-end registration with correct e-mail and wrong password with incorrect format (uppercase letters only)." +
            "Verifies include number in password warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_OnlyUppercasePassword() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "ONLYUPPERCASE"));
        Assert.assertTrue(homePageRegister.checkWrongPasswordIncludeNumberWarningIsShown());
    }

    @Test(priority = 10, description = "Performs an end-to-end registration with correct e-mail and wrong password with incorrect format (uppercase letters only)." +
            "Verifies include number in password warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_OnlyUpperLowerSignPassword() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "Onlyuplow."));
        Assert.assertTrue(homePageRegister.checkWrongPasswordIncludeNumberWarningIsShown());
    }

    @Test(priority = 11, description = "Performs an end-to-end registration with correct e-mail and wrong password with shorter than 7 characters." +
            "Verifies password length warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_ShortPassword() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "asdqwe"));
        Assert.assertTrue(homePageRegister.checkWrongPasswordLengthWarningIsShown());
    }

    @Test(priority = 12, description = "Performs an end-to-end registration with correct e-mail and no password." +
            "Verifies enter e-mail or password warning after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_NoPassword() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", ""));
        Assert.assertTrue(homePageRegister.checkEmptyEmailOrPasswordWarningIsShown());
    }

    @Test(priority = 13, description = "Performs an end-to-end registration with correct e-mail and wrong password with longer than 15 characters." +
            "Verifies password length warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_LongPassword() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "asdqwezxcqweasdc"));
        Assert.assertTrue(homePageRegister.checkWrongPasswordLengthWarningIsShown());
    }

    @Test(priority = 14, description = "Performs an end-to-end registration with correct e-mail and wrong password with longer than 255 characters." +
            "Verifies password length warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public static void Verify_Registering_ExceedingLongPassword() {
        Assert.assertTrue(homePageRegister.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "asdqweasdqweasdQWEQWAASZXCASSAD.1245ZXCASDGH" +
                "HCKXZMASDKĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXC" +
                "MKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQ" +
                "WEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXC" +
                "MPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZX" +
                "CÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAXCMKOPSADĞPDWQKPIZXCMPISADNJMPSADCVBSFDGDFGEARTzxcqweasdc"));
        Assert.assertTrue(homePageRegister.checkWrongPasswordLengthWarningIsShown());
    }

    @Test(priority = 15, description = "Performs an end-to-end registration with correct e-mail and wrong password with longer than 255 characters." +
            "Verifies e-mail verification input is not shown after registration attempt without checking personal data acceptance")
    public static void Verify_Registering_UncheckedPersonalDataAccept() {
        Assert.assertTrue(homePageRegister.performRegistrationWithoutCheckingPersonalDataAccept("yusufalti12312312@gmail.com", getTestingPassword()));
    }
}
