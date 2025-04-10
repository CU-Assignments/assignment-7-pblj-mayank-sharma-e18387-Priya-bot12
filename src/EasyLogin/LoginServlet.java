

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if("admin".equals(user) && "1234".equals(pass)) {
            request.setAttribute("username", user);
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        } else {
            response.getWriter().println("Invalid credentials. Try again.");
        }
    }
}
