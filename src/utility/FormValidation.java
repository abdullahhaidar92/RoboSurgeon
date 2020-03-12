package utility;

public class FormValidation {
    public static boolean validate(String str,String regex){
        return str.matches(regex);
    }
    public static boolean validatePhoneNumber(String phone){
        return validate(phone,"[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*");
    }
}
