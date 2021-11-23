package com.es.phoneshop.web.controller;

import com.es.core.cart.CartAddMessageDto;
import com.es.core.cart.CartItemDto;
import com.es.core.cart.CartService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CartAddMessageDto addPhone(@RequestBody @Valid CartItemDto cartItem,
                                      BindingResult bindingResult) {
        CartAddMessageDto message = new CartAddMessageDto();
        if (!bindingResult.hasErrors()) {
            cartService.addPhone(cartItem.getPhoneId(), cartItem.getQuantity());
            message.setMessage("Successfully added to cart");
            message.setErrorStatus(false);
        } else {
            message.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            message.setErrorStatus(true);
        }
        message.setPhoneId(cartItem.getPhoneId());
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public CartAddMessageDto numberFormatException() {
        CartAddMessageDto message = new CartAddMessageDto();
        message.setMessage("Quantity must be number");
        message.setErrorStatus(true);
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }
}
