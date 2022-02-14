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
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private ListServiceImpl listService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.listService = applicationContext.getBean(ListServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SongsForm> songs = listService.getAllSongsNew();
        request.setAttribute("songs", songs);
        request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String email = (String) request.getSession().getAttribute("email");
        int id = 0;
        List<SongsForm> songs;
        ProfileSongsForm form;
        if (email == null) {
            response.sendError(403);
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
                        break;
                    case "new":
                        songs =  listService.getAllSongsNew();
                        request.setAttribute("songs", songs);
                        request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
                        break;
                    default:
                        return;
                }
            }
        }
    }
}
