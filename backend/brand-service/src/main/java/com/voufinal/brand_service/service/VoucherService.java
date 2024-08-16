package com.voufinal.brand_service.service;

import com.voufinal.brand_service.dto.VoucherDTO;
import com.voufinal.brand_service.exception.NotFoundException;
import com.voufinal.brand_service.model.Brand;
import com.voufinal.brand_service.model.Voucher;
import com.voufinal.brand_service.repository.BrandRepository;
import com.voufinal.brand_service.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private final BrandRepository brandRepository;
    private final VoucherRepository voucherRepository;
    public static final String picturePath = "src/main/resources/image";   // get from computer

    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    // create random name base on date
    private String generateFileName(MultipartFile file){
        return new Date().getTime() + "-" + file.getOriginalFilename().replace(" ", "_");
    }

    private File converMultipart(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileURL = "";
        try {
            File file = converMultipart(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileURL = picturePath + fileName;
            saveFile(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileURL;
    }

    private void saveFile(String fileName, File file) throws IOException {
        File volumeFile = new File(picturePath + fileName);
        if (!volumeFile.getParentFile().exists()) {
            volumeFile.getParentFile().mkdirs();
        }
        if (!volumeFile.exists()) {
            volumeFile.createNewFile();
        }
        try (FileOutputStream fos = new FileOutputStream(volumeFile)) {
            fos.write(java.nio.file.Files.readAllBytes(file.toPath()));
        }
    }

    public void addVoucher(Long brandId, MultipartFile voucherQR, MultipartFile voucherImage, VoucherDTO voucherDTO){
        // find exist brand to add voucher belong to that brand
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand not found with id: " + brandId));

        Voucher voucher = new Voucher();

        String fileURLQR = uploadFile(voucherQR);
        String fileURL = uploadFile(voucherImage);

        voucher.setId(voucherDTO.getId());
        voucher.setImageQR(fileURLQR);
        voucher.setImageURL(fileURL);
        voucher.setValue(voucherDTO.getValue());
        voucher.setVoucherQuantities(voucherDTO.getQuantity());
        voucher.setDescription(voucherDTO.getDescription());
        voucher.setEndDate(voucherDTO.getEndDate());
        voucher.setBrand(brand);

        voucherRepository.save(voucher);
    }
}
