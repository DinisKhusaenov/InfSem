package ru.kpfu.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.kpfu.itis.form.ProfileSongsForm;
import ru.kpfu.itis.form.SongsForm;
import ru.kpfu.itis.services.ListServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/songs")
public class SongsServlet extends HttpServlet {

    private ListServiceImpl listService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.listService = applicationContext.getBean(ListServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SongsForm song = listService.getSong(id);
        request.setAttribute("song", song);
        request.getRequestDispatcher("/WEB-INF/views/song.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String email = (String) request.getSession().getAttribute("email");
        int id = 0;
        ProfileSongsForm form;
        if (email == null) {
            response.sendRedirect("/signIn");
        } else {
            if (action != null) {
                switch (action) {
                    case "addToWishList":
                        id = Integer.parseInt(request.getParameter("id"));
                        form = ProfileSongsForm.builder()
                                .id(id)
                                .email(email)
                                .build();
                        if (!listService.isNewSongInList(form)) {
                            listService.addSongToList(form);
                        }
                        doGet(request, response);
                        break;
                    default:
                        return;
                }
            }
        }
    }
}
