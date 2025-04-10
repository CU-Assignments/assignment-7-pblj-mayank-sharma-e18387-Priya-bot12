@WebServlet("/submitAttendance")
public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentAttendance att = new StudentAttendance();
        att.setId(Integer.parseInt(request.getParameter("id")));
        att.setName(request.getParameter("name"));
        att.setDate(request.getParameter("date"));
        att.setStatus(request.getParameter("status"));

        try (Connection conn = // JDBC connection logic;
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO attendance (id, name, date, status) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, att.getId());
            stmt.setString(2, att.getName());
            stmt.setString(3, att.getDate());
            stmt.setString(4, att.getStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("DB insert error", e);
        }

        request.getRequestDispatcher("attendance-success.jsp").forward(request, response);
    }
}
