package task.tynybekoddarmanbektesttask.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RequestAd {
    private String image;
    private String title;
    private double price;
    private String category;
    private String date;
}
