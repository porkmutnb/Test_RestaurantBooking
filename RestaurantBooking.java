import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantBooking {

    private static int TOTAL_TABLE = 4; // จํานวนโต๊ะทั้งหมดในร้าน
    private static int CUSTOMER_PER_TABLE = 4; // จํานวนลูกค้าต่อโต๊ะ
    private static String STORE_OPEN_DATE = "2024-03-28"; // วันที่ร้านเปิด
    private static String STORE_OPEN_TIME = "18:00"; // วันที่ร้านเปิด
    private static String STORE_CLOSE_TIME = "21:00"; // วันที่ร้านเปิด

    public static void main(String[] args) {
        /* ตัวอย่างข้อมูลการจองโต๊ะอาหาร */
        List<HashMap<String, Object>> bookings = new ArrayList<>();
        HashMap<String, Object> booking1 = new HashMap<>();
        booking1.put("bookName", "John");
        booking1.put("phone", "123456789");
        booking1.put("serviceDate", "2024-03-28");
        booking1.put("timeIn", "18:00");
        booking1.put("timeOut", "20:00");
        booking1.put("customerQty", 16);
        bookings.add(booking1);
        HashMap<String, Object> booking2 = new HashMap<>();
        booking2.put("bookName", "Alice");
        booking2.put("phone", "987654321");
        booking2.put("serviceDate", "2024-03-28");
        booking2.put("timeIn", "18:30");
        booking2.put("timeOut", "20:00");
        booking2.put("customerQty", 3);
        bookings.add(booking2);

        /* เรียกใช้ฟังก์ชั่นเพื่อคำนวณโต๊ะที่ต้องใช้ */
        int tablesNeeded = calculateTablesNeeded(bookings);
        System.out.println("Total Table in use: " + tablesNeeded + " Qty");
    }

    /* คํานวณจํานวนโต๊ะที่ต้องใช้ */
    public static int calculateTablesNeeded(List<HashMap<String, Object>> bookings) {
        int tablesNeeded = 0;
        /* เก็บข้อมูลการจองโต๊ะที่ต้องการใช้งาน */
        List<HashMap<String, Object>> selectedBookings = new ArrayList<>();
        for (HashMap<String, Object> booking : bookings) {
            String bookingDate = (String) booking.get("serviceDate");
            String bookingStartTime = (String) booking.get("timeIn");
            String bookingEndTime = (String) booking.get("timeOut");

            if (bookingDate.equals(STORE_OPEN_DATE)) {
                if (bookingStartTime.equals(STORE_CLOSE_TIME) || bookingEndTime.equals(STORE_OPEN_TIME)) {
                    continue; // ไม่มีการทับซ้อนกัน ไม่ต้องใช้โต๊ะซ้ำ
                } else if (bookingStartTime.compareTo(STORE_CLOSE_TIME) >= 0
                        || bookingEndTime.compareTo(STORE_OPEN_TIME) <= 0) {
                    // ไม่มีการทับซ้อนกัน ไม่ต้องใช้โต๊ะซ้ำ
                    continue;
                } else {
                    selectedBookings.add(booking);
                }
            }
        }
        /* ตรวจสอบโต๊ะที่ใช้งาน */
        List<Integer> usedTables = new ArrayList<>();
        for (HashMap<String, Object> booking : selectedBookings) {
            int numCustomers = (int) booking.get("customerQty");
            int numTables = (int) Math.ceil((double) numCustomers / CUSTOMER_PER_TABLE);
            boolean tableAssigned = false;
            int sumUseTable = usedTables.stream().mapToInt(a -> a).sum();
            if ((sumUseTable + numTables) > TOTAL_TABLE) {
                tableAssigned = true;
                break;
            }
            if (!tableAssigned) {
                tablesNeeded += numTables;
                usedTables.add(numTables);
            }
        }
        return Math.min(tablesNeeded, TOTAL_TABLE);
    }
}
