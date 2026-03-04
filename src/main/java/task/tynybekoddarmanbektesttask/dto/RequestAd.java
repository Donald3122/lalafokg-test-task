package task.tynybekoddarmanbektesttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestAd {
    private String image;
    private String title;
    private String price;
    private String city;
    private String date;
}
