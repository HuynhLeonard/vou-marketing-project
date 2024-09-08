package com.voufinal.gift_service.service;

import com.voufinal.gift_service.entity.request.GiftItemRequest;
import com.voufinal.gift_service.model.GiftLog;
import com.voufinal.gift_service.repository.GiftLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class GiftLogService {
    @Autowired
    private GiftLogRepository giftLogRepository;

    @Autowired
    private ItemRepoService itemRepoService;

    @Autowired
    private UserService userService;

    public void giftAnItemForUser(GiftItemRequest request) throws Exception {
        Object senderIdentifier = null;
        Object receiverIdentifier = null;
        String type = "";
        if (request.getReceiverEmail() != null && request.getSenderEmail() != null) {
            senderIdentifier = request.getSenderEmail();
            receiverIdentifier = request.getReceiverEmail();
            type = "email";
        } else if (request.getReceiverUsername() != null && request.getSenderUsername() != null){
            senderIdentifier = request.getSenderUsername();
            receiverIdentifier = request.getReceiverUsername();
            type = "username";
        } else if (request.getReceiverId() != null && request.getSenderId() != null){
            senderIdentifier = request.getSenderId();
            receiverIdentifier = request.getReceiverId();
            type = "id_user";
        }

        if (senderIdentifier == null)
            throw new Exception("Invalid request, id/username/email is required to send a gift");

        if (receiverIdentifier.equals(senderIdentifier))
            throw new Exception("Cannot send items to yourself");

        LinkedHashMap<String, Object> senderResponse = userService.findPlayerByIdentifier(senderIdentifier, type);

        // Check user có đủ item ko
        if (!itemRepoService.checkIfUserHaveAmountOfItemLargerThan(((Integer) senderResponse.get("idUser")).longValue(), request.getAmount(), request.getItemId())) {
            throw new Exception("User does not have enough items to send as a gift");
        }
        // Check receiver có tồn tại hay ko
        LinkedHashMap<String, Object> receiverResponse = userService.findPlayerByIdentifier(receiverIdentifier, type);
        // Tiến hành trừ item của sender
        itemRepoService.updateItemAmountOfUser(request.getItemId(), ((Integer) senderResponse.get("idUser")).longValue(), -request.getAmount());
        // Tiến hành cộng item của receiver
        itemRepoService.updateItemAmountOfUser(request.getItemId(), ((Integer) receiverResponse.get("idUser")).longValue(), request.getAmount());
        // Trừ thành công thì tạo gift log, nếu trừ khong thanh cong thi exception da duoc throw
        GiftLog newGiftLog = new GiftLog();
        newGiftLog.setIdItem(request.getItemId());
        newGiftLog.setAmount(request.getAmount());
        newGiftLog.setSenderName((String) senderResponse.get("fullName"));
        newGiftLog.setReceiverName((String) receiverResponse.get("fullName"));
        newGiftLog.setUidReceiver(((Integer) receiverResponse.get("idUser")).longValue());
        newGiftLog.setUidSender(((Integer) senderResponse.get("idUser")).longValue());
        giftLogRepository.save(newGiftLog);
    }

    public HashMap<String, Object> getGiftingHistoryByUserId(Long id_user) {
        List<GiftLog> giftLogsSent = giftLogRepository.findGiftLogsByUidSender(id_user);
        List<GiftLog> giftLogsReceive = giftLogRepository.findGiftLogsByUidReceiver(id_user);
        HashMap<String, Object> result = new HashMap<>();
        result.put("sendHistory", giftLogsSent);
        result.put("receiveHistory", giftLogsReceive);
        return result;
    }
}
