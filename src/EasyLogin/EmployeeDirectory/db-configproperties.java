@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private Connection getConnection() throws Exception {
        Properties props = new Properties();
        props.load(getServletContext().getResourceAsStream("/WEB-INF/db-config.properties"));
        return DriverManager.getConnection(props.getProperty("db.url"), 
                                           props.getProperty("db.user"), 
                                           props.getProperty("db.password"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        try (Connection conn = getConnection()) {
            PrintWriter out = response.getWriter();
            PreparedStatement stmt;
            if (idParam != null && !idParam.isEmpty()) {
                stmt = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
                stmt.setInt(1, Integer.parseInt(idParam));
            } else {
                stmt = conn.prepareStatement("SELECT * FROM employees");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                out.println("<p>ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Dept: " + rs.getString("department") +
                            ", Email: " + rs.getString("email") + "</p>");
            }
        } catch (Exception e) {
            throw new ServletException("DB error", e);
        }
    }
}
