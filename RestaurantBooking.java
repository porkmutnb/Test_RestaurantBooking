import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantBooking {

    private static int TOTAL_TABLE = 4; // จํานวนโต๊ะทั้งหมดในร้าน
    private static int CUSTOMER_PER_TABLE = 4; // จํานวนลูกค้าต่อโต๊ะ
    private static String STORE_OPEN_DATE = "2024-03-28"; // วันที่ร้านเปิด

    public static void main(String[] args) {
        /* ตัวอย่างข้อมูลการจองโต๊ะอาหาร */
        List<HashMap<String, Object>> bookings = new ArrayList<>();
        HashMap<String, Object> booking1 = new HashMap<>();
        booking1.put("bookName", "John");
        booking1.put("phone", "123456789");
        booking1.put("serviceDate", "2024-03-28");
        booking1.put("timeIn", "18:00");
        booking1.put("timeOut", "20:00");
        booking1.put("customerQty", 4);
        bookings.add(booking1);
        HashMap<String, Object> booking2 = new HashMap<>();
        booking2.put("bookName", "Alice");
        booking2.put("phone", "987654321");
        booking2.put("serviceDate", "2024-03-28");
        booking2.put("timeIn", "18:00");
        booking2.put("timeOut", "20:00");
        booking2.put("customerQty", 3);
        bookings.add(booking2);
        HashMap<String, Object> booking3 = new HashMap<>();
        booking3.put("bookName", "Lipoh");
        booking3.put("phone", "888888888");
        booking3.put("serviceDate", "2024-03-28");
        booking3.put("timeIn", "18:00");
        booking3.put("timeOut", "20:00");
        booking3.put("customerQty", 7);
        bookings.add(booking3);
        HashMap<String, Object> booking4 = new HashMap<>();
        booking4.put("bookName", "Bob");
        booking4.put("phone", "999999999");
        booking4.put("serviceDate", "2024-03-28");
        booking4.put("timeIn", "18:00");
        booking4.put("timeOut", "20:00");
        booking4.put("customerQty", 7);
        bookings.add(booking4);

        /* เรียกใช้ฟังก์ชั่นเพื่อคำนวณโต๊ะที่ต้องใช้ */
        int tablesNeeded = calculateTablesNeeded(STORE_OPEN_DATE, bookings);
        System.out.println("Total Table in use: " + tablesNeeded + " Qty");
    }

    /* คํานวณจํานวนโต๊ะที่ต้องใช้ */
    public static int calculateTablesNeeded(String date, List<HashMap<String, Object>> bookings) {
        int tablesNeeded = 0;
        for (HashMap<String, Object> booking : bookings) {
            String bookingDate = (String) booking.get("serviceDate");
            if (bookingDate.equals(date)) {
                int numCustomers = (int) booking.get("customerQty");
                int numTables = (int) Math.ceil((double) numCustomers / CUSTOMER_PER_TABLE);
                tablesNeeded += numTables;
            }
        }
        return Math.min(tablesNeeded, TOTAL_TABLE);
    }
}
