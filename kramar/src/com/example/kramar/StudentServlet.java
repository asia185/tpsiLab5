package com.example.kramar;
import com.example.kramar.Student;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("visitors") == null) {
            session.setAttribute("visitors", 0);
        }

        session.setAttribute("visitors", (int) session.getAttribute("visitors") + 1);

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        if (session.getAttribute("visitors") == null) {
            session.setAttribute("visitors", 0);
        }

        session.setAttribute("visitors", (int) session.getAttribute("visitors") + 1);


        if (session.getAttribute("students") == null) {
            session.setAttribute("students", new ArrayList<>());
        }

        List<Student> students = (List<Student>) session.getAttribute("students");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            session.setAttribute("email", email);
            session.setAttribute("error", "Wymagane są wszystkie pola");
        } else {
            students.add(new Student(firstName, lastName, email));
            session.setAttribute("students", students);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}

