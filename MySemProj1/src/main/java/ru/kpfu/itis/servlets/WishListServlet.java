package ru.kpfu.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.form.ProfileSongsForm;
import ru.kpfu.itis.services.WishListServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wishList")
public class WishListServlet extends HttpServlet {

    private WishListServiceImpl wishListService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.wishListService = applicationContext.getBean(WishListServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email =(String) request.getSession().getAttribute("email");
        if (email == null) {
            response.sendRedirect("/signIn");
        } else {
            request.setAttribute("songs", wishListService.getAllSongs(email));
            request.getRequestDispatcher("/WEB-INF/views/wishList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String email = (String) request.getSession().getAttribute("email");
        int id = 0;
        ProfileSongsForm form;
        if (action != null) {
            switch (action) {
                case "dropSong":
                    id = Integer.parseInt(request.getParameter("id"));
                    form = ProfileSongsForm.builder()
                            .id(id)
                            .email(email)
                            .build();
                    wishListService.dropSong(form);
                    doGet(request, response);
                    break;
                default:
                    return;
            }
        }

    }
}
