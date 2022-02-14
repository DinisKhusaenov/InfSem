package ru.kpfu.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.form.UserForm;
import ru.kpfu.itis.services.SignUpServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    SignUpServiceImpl signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.signUpService = applicationContext.getBean(SignUpServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =(String) request.getSession().getAttribute("email");
        if (email != null) {
            response.sendRedirect("/profile");
        } else {
            request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserForm form = UserForm.builder()
                .name(request.getParameter("name"))
                .lastName(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .passwordr(request.getParameter("passwordr"))
                .build();
        System.out.println(form);
        if (!form.getEmail().isEmpty() &&
                !signUpService.isNewUser(form.getEmail()) &&
                form.getPassword().equals(form.getPasswordr())) {
            signUpService.signUp(form);
            response.sendRedirect("/signIn");
        } else {
            response.sendRedirect("/signUp");
        }
    }
}
