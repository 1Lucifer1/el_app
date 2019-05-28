
import okhttp3.FormBody;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class test {
    static String url = "http://127.0.0.1:8000/el/";
    static String loginName = "";
    static String loginPassword = "";

    public static void main (String[] avgs) throws IOException, InterruptedException{
        Post p = new Post();
        FormBody formBody = new FormBody.Builder()
                .add("login_name",loginName)
                .add("login_password",loginPassword)
                .build();
        String room = p.post(url+"contest/", formBody);
        System.out.println(room);
    }
    private static void login()throws IOException, InterruptedException{
        Post p = new Post();
        FormBody formBody = new FormBody.Builder()
                .add("login_name",loginName)
                .add("login_password",loginPassword)
                .build();
        String loginCode = p.post(url+"", formBody);
        System.out.println(loginCode);
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

    }
    private static void findConnect() throws IOException , InterruptedException{
        String opName;
        FormBody formBody = new FormBody.Builder()
                .add("login_name",loginName)
                .add("login_password",loginPassword)
                .build();
        Post pp = new Post();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            String[] confirm_info = (pp.post(url + "game01/find_connent/", formBody)).split(" ");
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
                System.out.println("\n" + opName + " 加入了房间");
                TimeUnit.SECONDS.sleep(5);
                break;
            }
        }
    }
}
