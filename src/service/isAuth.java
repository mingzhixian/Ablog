package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public class isAuth {

    //增加basic 认证
    private static final Base64.Decoder decoder = Base64.getDecoder();
    // private static final Base64.Encoder encoder = Base64.getEncoder();

    //通过http 请求头认证
    public static boolean isAuth(HttpServletRequest req, HttpServletResponse res) {
        String base6AuthStr = req.getHeader("Authorization");
        System.out.println("base6AuthStr=" + base6AuthStr); // base6AuthStr=Basic YWFhOmFhYQ==
        if (base6AuthStr == null) {
            res.setStatus(401);
            res.addHeader("WWW-Authenticate", "basic realm=\"no auth\"");
            return false;
        }

        String authStr = new String(decoder.decode(base6AuthStr.substring(6).getBytes()));
        System.out.println("authStr=" + authStr); // authStr=xxx:xxx

        String[] arr = authStr.split(":");
        if (arr != null && arr.length == 2) {
            String username = arr[0];
            String password = arr[1];
            // 校验用户名和密码
            if ("test".equals(username) && "123456".equals(password)) {
                return true;
            }
        }
        // 用户名 ID 和密码校验失败后，重新返回 401 和 WWW-Authenticate 响应头，从而可以重复质询
        res.setStatus(401);
        res.addHeader("WWW-Authenticate", "basic realm=\"no auth\"");
        return false;
    }
}
