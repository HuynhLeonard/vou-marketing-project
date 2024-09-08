package com.voufinal.event_service.service;

import com.voufinal.event_service.dto.InventoryDetailDto;
import com.voufinal.event_service.dto.InventoryDto;
import com.voufinal.event_service.dto.InventoryImageUrlDto;
import com.voufinal.event_service.entity.MultipartInputStreamFileResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private RestTemplate restTemplate;

    private final String QUIZ_SERVICE_URL = "http://localhost:8087/api/v1/vouchers";

    public void createInventory(InventoryDto inventoryDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        // Wrap the List<QuizDTO> in an HttpEntity
        HttpEntity<InventoryDto> request = new HttpEntity<>(inventoryDTO, headers);

        // Make the POST request
        ResponseEntity<Void> response = restTemplate.exchange(
                QUIZ_SERVICE_URL,
                HttpMethod.POST,
                request,
                Void.class
        );

        // Optionally handle the response
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Quizzes created successfully.");
        } else {
            System.out.println("Failed to create quizzes: " + response.getStatusCode());
        }
    }
    public InventoryDetailDto getInventoryInfo(Long eventId) {
        String url = "http://localhost:8087/api/v1/vouchers/events/" + eventId;

        // Make the GET request
        ResponseEntity<InventoryDetailDto> response = restTemplate.getForEntity(url, InventoryDetailDto.class);

        // Return the body of the response
        return response.getBody();
    }

    public InventoryImageUrlDto uploadInventoryImages(Long eventId, MultipartFile qrImg, MultipartFile voucherImg) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("qrImg", new MultipartInputStreamFileResource(qrImg.getInputStream(), qrImg.getOriginalFilename()));
            body.add("voucherImg", new MultipartInputStreamFileResource(voucherImg.getInputStream(), voucherImg.getOriginalFilename()));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            String url = QUIZ_SERVICE_URL + "?id_event=" + eventId;

            ResponseEntity<InventoryImageUrlDto> response = restTemplate.exchange(
                    url
                    , HttpMethod.PUT,
                    requestEntity,
                    InventoryImageUrlDto.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public boolean checkVoucherExists(String voucherCode) {
        String url = "http://localhost:8087/api/v1/vouchers/exists/" + voucherCode;

        ResponseEntity<Boolean> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Boolean.class
        );

        return Boolean.TRUE.equals(response.getBody());
    }
    public InventoryDto updateInventory(InventoryDto inventoryDTO) {
        String url = "http://localhost:8087/api/v1/vouchers/info?code=" + inventoryDTO.getVoucher_code();

        // Make a PUT request to the updateGameInfo endpoint
        ResponseEntity<InventoryDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(inventoryDTO),
                InventoryDto.class
        );
        return response.getBody();
    }
}
