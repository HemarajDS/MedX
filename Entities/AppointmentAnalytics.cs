public class AppointmentAnalytics {
    public int Id { get; set; }
    public DateTime AppointmentDate { get; set; }
    public int TotalAppointments { get; set; }
    public int CanceledAppointments { get; set; }
    public int RescheduledAppointments { get; set; }
}