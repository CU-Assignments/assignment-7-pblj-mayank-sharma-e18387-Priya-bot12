<form action="submitAttendance" method="post">
    Student ID: <input type="text" name="id" /><br/>
    Name: <input type="text" name="name" /><br/>
    Date: <input type="date" name="date" /><br/>
    Status: 
    <select name="status">
        <option value="Present">Present</option>
        <option value="Absent">Absent</option>
    </select><br/>
    <input type="submit" value="Submit Attendance" />
</form>
