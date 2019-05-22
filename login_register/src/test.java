import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class test {
    static String url = "http://127.0.0.1:8000/";
    static String loginName = "";
    static String loginPassword = "";
    static String loginJson = "";
    static HashMap<String,String> json = new HashMap<String,String> ();
    static String data;




    public static void main (String[] avgs) throws IOException, InterruptedException{
        System.out.println("你妈的 快点给老子登录！");
        Scanner scan = new Scanner(System.in);
        System.out.print("账号： ");
        loginName = scan.next();
        System.out.print("密码： ");
        loginPassword = scan.next();
        scan.close();
        json.put("login_name", loginName);
        json.put("login_password", loginPassword);
        Gson gson = new Gson();
        data = gson.toJson(json);
        login();

    }
    private static void login()throws IOException, InterruptedException{
        Post p = new Post();
        String loginCode = p.post(url+"game01/login/",data);
        System.out.print(data);
        if (loginCode.equals("0")) {
            System.out.println("登录成功！");
            System.out.println("你好  " + loginName + "！");
            findConnect();
        }
        else if (loginCode.equals("1")) {
            System.out.println("找不到你的用户名呢");
        }
        else if (loginCode.equals("2")) {
            System.out.println("密码错了！");
        }
        System.out.print(loginCode);
    }
    private static void findConnect() throws IOException , InterruptedException{
        String opName;
        Post pp = new Post();
        while (true) {
            String[] confirm_info = (pp.post(url + "game01/find_connent/", loginJson)).split(" ");
            if (confirm_info[0].equals("0")) {
                System.out.print(".");
                TimeUnit.SECONDS.sleep(1);
            }
            else if (confirm_info[0].equals("1")) {
                if (confirm_info[1].equals(loginName)) {
                    opName = confirm_info[2];
                }
                else {
                    opName = confirm_info[1];
                }
                System.out.println(opName + " 加入了房间");
                TimeUnit.SECONDS.sleep(5);
                break;
            }
        }
    }
}
