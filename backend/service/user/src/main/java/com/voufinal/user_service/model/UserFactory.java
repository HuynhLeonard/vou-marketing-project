package com.voufinal.user_service.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UserFactory extends JsonDeserializer<User> {
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException{
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);

        String role = node.get("role").asText();

        User user;
        switch (User_Role.valueOf(role)) {
            case admin:
                user = new Admin();
                break;
            case player:
                user = new Player();
                break;
            case brand:
                user = new Brand();
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
        user.setUserName(node.get("userName").asText());
        user.setFullName(node.get("fullName").asText());
        user.setAccountId(node.get("accountId").asText());
        user.setEmail(node.get("email").asText());
        user.setPhoneNumber(node.get("phoneNumber").asText());
        user.setRole(User_Role.valueOf(role));
        user.setStatus(node.get("status").asBoolean());

        if(user instanceof Player) {
            Player player = (Player) user;
            player.setGender(node.get("gender").asText());
            player.setAccountFacebook(node.get("accountFacebook").asText());
            player.setDayofBirth(node.get("dayofBirth").asText());
            player.setAvatar(node.get("avatar").asText());
        }
        else if(user instanceof Brand) {
            Brand brand = (Brand) user;
            brand.setNameBrand(node.get("nameBrand").asText());
            brand.setField(node.get("field").asText());
            brand.setAddress(node.get("address").asText());
            brand.setLatitude(node.get("latitude").asDouble());
            brand.setLongitude(node.get("longitude").asDouble());
        }

        return user;
    }

}
