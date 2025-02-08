package vn.edu.t3h.employeemananger.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // config kiểu dữ liiệu trả về trong response là html/text
        resp.setContentType("text/html");

        // ghi dữ liệu vào response
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1> hello servlet </h1>");
        out.println("</body></html>");
    }
}
