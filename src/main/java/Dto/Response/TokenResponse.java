package Dto.Response;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class TokenResponse {
    private Long idUser;
    private String token;
    private String refreshToken;
}