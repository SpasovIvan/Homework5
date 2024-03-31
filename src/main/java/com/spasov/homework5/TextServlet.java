package com.spasov.homework5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "TextServlet", value = "/text-servlet")
public class TextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream textStream = TextServlet.class.getResourceAsStream("/text.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(textStream));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line);
        }
        req.setAttribute("line", text);
        String textString = text.toString();
        char[] textChars = textString.toCharArray();
        int counter = 0;
        int checker = 0;
        int cookieCounter = 0;
        text.delete(0, text.length());
        for (char item : textChars) {
            if (!Character.isLetter(item)) {
                counter++;
                if (item != '\n') {
                    text.append(item);
                }

            } else if (counter > 0){
                counter = 0;
                String encodedValue = URLEncoder.encode(text.toString(), "UTF-8");
                Cookie cookie = new Cookie("cookie" + cookieCounter, encodedValue);
                resp.addCookie(cookie);

                text.delete(0, text.length());
                cookieCounter++;
            }
            if (counter > checker) {
                checker = counter;
            }
        }

        req.setAttribute("count", checker);
        req.getRequestDispatcher("/text.jsp").forward(req, resp);
    }
}
