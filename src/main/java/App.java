import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

// weblab.txt 为账号密码存储文件
// 存储格式为 “账号 + 空格 + 密码” ---- 一条账号密码记录一行

public class App {
    private static final String PATH = "weblab1.txt";

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Please provide an input!");
            System.exit(0);
        }
        String passwd = getPasswd(args[0]);
        if (passwd.equals("") || passwd == null) {
            System.out.println("用户不存在");
        } else {
            if (passwd.equals(args[1])) {
                System.out.println("登录成功，账号：" + args[0] + "，密码：" + sha256hex(passwd));
            } else {
                System.out.println("登录失败");
            }
        }
    }

    static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }

    public static String getPasswd(String name) throws IOException {
        FileReader fileReader = new FileReader(PATH);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            int index = str.indexOf(" ");
            String tempName = str.substring(0, index);
            String tempPasswd = str.substring(index + 1, str.length());
            if (name.equals(tempName)) {
                return tempPasswd;
            }
        }
        return null;
    }
}
