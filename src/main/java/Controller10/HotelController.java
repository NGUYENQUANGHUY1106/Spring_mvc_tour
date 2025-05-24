package Controller10;

import Dto.Request.HotelRequest;
import Dto.Request.SearchRequest;
import Dto.Response.HotelCustomerGetResponse;
import Dto.Response.HotelResponse;
import Entity.BookHotel;
import Entity.Hotel;
import MapperData.HotelMapper;
import Repository.BookHotelRepository;
import Repository.FavoriteHotelRepository;
import Service.HotelService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = "*")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private BookHotelRepository bookHotelRepository;

    @Autowired
    private FavoriteHotelRepository favoriteHotelRepository;

    // ✅ API: Thêm mới khách sạn
    @PostMapping()
    public HotelResponse add(@RequestBody HotelRequest hotelRequest) {
        return hotelService.add(hotelRequest);
    }

    // ✅ API: Lấy tất cả khách sạn (trang chủ người dùng)
    @GetMapping()
    public List<HotelCustomerGetResponse> getAllHotels() {
        return hotelService.getAll();
    }

    // ✅ API: Tìm kiếm khách sạn theo tên, địa chỉ,...
    @PostMapping("/search")
    public List<HotelResponse> searchHotels(@RequestBody SearchRequest searchRequest) {
        return hotelService.search(searchRequest);
    }

    // ✅ API: Lấy tổng doanh thu của khách sạn theo id user
    @GetMapping("/totalMoney/{idUser}")
    public double getDoanhThu(@PathVariable Long idUser) {
        return hotelService.getDoanhThu(idUser);
    }

    // ✅ API: Thống kê số lượng booking theo từng trạng thái
    @GetMapping("/bookings/count/{userId}")
    public ResponseEntity<Map<String, Integer>> getBookingStatusCount(@PathVariable Long userId) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("WAIT", bookHotelRepository.countBookingsByUserAndStatus(userId, "WAIT"));
        counts.put("CONFIRMED", bookHotelRepository.countBookingsByUserAndStatus(userId, "CONFIRMED"));
        counts.put("CANCELLED", bookHotelRepository.countBookingsByUserAndStatus(userId, "CANCELLED"));
        counts.put("CHECKOUT", bookHotelRepository.countBookingsByUserAndStatus(userId, "CHECKOUT"));
        counts.put("RETURNED", bookHotelRepository.countBookingsByUserAndStatus(userId, "RETURNED"));
        return ResponseEntity.ok(counts);
    }

    // ✅ API: Lấy danh sách booking theo trạng thái
    @GetMapping("/bookings/status")
    public ResponseEntity<List<BookHotel>> getBookingsByStatus(
            @RequestParam Long userId,
            @RequestParam String status) {
        List<BookHotel> bookings = bookHotelRepository.findByHotel_User_IdAndStatusBook(userId, status);
        return ResponseEntity.ok(bookings);
    }

    // ✅ API: Lấy tổng số phòng trống của các khách sạn do user quản lý
    @GetMapping("/available-rooms/{userId}")
    public ResponseEntity<Integer> getAvailableRooms(@PathVariable Long userId) {
        int total = hotelService.getAvailableRoomByUserId(userId);
        return ResponseEntity.ok(total);
    }

    // ✅ API: Lấy chi tiết khách sạn theo ID
    @GetMapping("/{id}")
    public HotelResponse getHotelById(@PathVariable Long id) {
        return hotelService.getHotelResponseById(id);
    }

    // ✅ API: Upload ảnh lên imgbb và trả về URL
    @PostMapping(path = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = new HashMap<>();
        try {
            String apiKey = "4ddede4ada64aae1e0793f5c61eba4b4"; // API key imgbb
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.imgbb.com/1/upload?key=" + apiKey))
                    .POST(HttpRequest.BodyPublishers.ofString("image=" + URLEncoder.encode(base64, StandardCharsets.UTF_8)))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            String imageUrl = json.getJSONObject("data").getString("url");
            result.put("img", imageUrl);
            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            result.put("error", e.getMessage());
            return result;
        }
    }
}
