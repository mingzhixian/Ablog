package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

public class BasicAuth {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sessionAuth = (String) request.getSession().getAttribute("auth");

        if (sessionAuth != null) {
            System.out.println("this is next step");
            nextStep(request, response);

        } else {

            if (!checkHeaderAuth(request)) {
                response.setStatus(401);
                response.setHeader("Cache-Control", "no-store");
                response.setDateHeader("Expires", 0);
                response.setHeader("WWW-authenticate", "Basic Realm=\"auth\"");
            }

        }

    }

    private boolean checkHeaderAuth(HttpServletRequest request) {

        String auth = request.getHeader("Authorization");

        if ((auth != null) && (auth.length() > 6)) {
            auth = auth.substring(6);

            String decodedAuth = getFromBASE64(auth);

            request.getSession().setAttribute("auth", decodedAuth);
            return true;
        } else {
            return false;
        }

    }

    private String getFromBASE64(String s) {
        if (s == null)
            return null;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] b = decoder.decode(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    public void nextStep(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html> next step, authentication is : " + request.getSession().getAttribute("auth") + "<br>");
        pw.println("<br></html>");
    }
}