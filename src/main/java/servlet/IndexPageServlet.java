package servlet;

import entity.Schedule;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import service.ScheduleService;

@WebServlet(name = "index", value = "/index")
public class IndexPageServlet extends HttpServlet {
    private ScheduleService scheduleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        scheduleService = (ScheduleService) servletContext.getAttribute("userService");

        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Schedule> schedule = scheduleService.getAll(0,5);
        request.setAttribute("schedule", schedule);
        request.getRequestDispatcher("/template/index.ftl").forward(request, response);
        System.out.println("my index");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/index.ftl").forward(req, resp);
    }

}
