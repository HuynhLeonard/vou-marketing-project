package com.voufinal.event_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EventImageDto {
    private MultipartFile bannerFile;
    private MultipartFile qrImage;
    private MultipartFile voucherImg;

    public MultipartFile getBannerFile() {
        return bannerFile;
    }

    public void setBannerFile(MultipartFile bannerFile) {
        this.bannerFile = bannerFile;
    }

    public MultipartFile getQrImage() {
        return qrImage;
    }

    public void setQrImage(MultipartFile qrImage) {
        this.qrImage = qrImage;
    }

    public MultipartFile getVoucherImg() {
        return voucherImg;
    }

    public void setVoucherImg(MultipartFile voucherImg) {
        this.voucherImg = voucherImg;
    }
}
