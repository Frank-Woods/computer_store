package ru.fwoods.computerstore.controller.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.domain.User;
import ru.fwoods.computerstore.model.CartProduct;
import ru.fwoods.computerstore.service.BasketService;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

@RestController
public class BasketRestController {

    @Autowired
    private BasketService basketService;

    @PostMapping(value = "/basket/add/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity addProduct(
            @AuthenticationPrincipal User user,
            @RequestPart(name = "product") CartProduct product
    ) {
        basketService.save(user, product);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/basket/get/product/count")
    public Integer getCountProduct(
            @AuthenticationPrincipal User user
    ) {
        return basketService.getCountProduct(user);
    }

    @GetMapping(value = "/basket/get/product")
    public ResponseEntity getBasket(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "products", required = false) String cart
    ) {
        if (user != null) {
            return ResponseEntity.ok().body(basketService.getProductsInBasket(user));
        }
        if (cart != null) {
            try {
                Gson gson = new Gson();
                Reader reader = new StringReader(cart);
                List<CartProduct> products = gson.fromJson(reader, new TypeToken<List<CartProduct>>() {}.getType());
                return ResponseEntity.ok().body(basketService.getProductsInBasket(products));
            } catch (JsonParseException exception) {
                exception.printStackTrace();
            }
        }
        return ResponseEntity.ok().body(null);
    }


    @PostMapping(value = "/basket/delete/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity deleteProduct(
            @AuthenticationPrincipal User user,
            @RequestPart(name = "product", required = false) CartProduct product
    ) {
        basketService.delete(user, product);
        return ResponseEntity.ok().body(null);
    }
}
