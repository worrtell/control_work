package handler;

import entity.Schedule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import java.io.*;
import java.util.List;

@WebServlet("*.ftl")
public class TemplateHandlerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();


        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        VelocityContext context = new VelocityContext();

        List<Schedule> schedule = (List<Schedule>) request.getAttribute("group");
        context.put("group", schedule);
        System.out.println(schedule);

        Template template = velocityEngine.getTemplate(servletPath);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        String result = writer.toString();
        response.setContentType("text/html");
        response.getWriter().write(result);
        response.flushBuffer();
    }
}