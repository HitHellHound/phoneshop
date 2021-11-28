package com.es.phoneshop.web.controller;

import com.es.core.cart.CartItemDto;
import com.es.core.cart.CartService;
import com.es.core.cart.CartStatusMessageDto;
import com.es.core.order.OutOfStockException;
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
    public CartStatusMessageDto addPhone(@RequestBody @Valid CartItemDto cartItem,
                                         BindingResult bindingResult) {
        CartStatusMessageDto message = new CartStatusMessageDto();
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

    @RequestMapping(path = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CartStatusMessageDto deletePhone(@RequestParam("phoneId") Long phoneId) {
        CartStatusMessageDto message = new CartStatusMessageDto();
        cartService.remove(phoneId);
        message.setErrorStatus(false);
        message.setMessage("Successfully deleted from cart");
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }


    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public CartStatusMessageDto numberFormatException() {
        CartStatusMessageDto message = new CartStatusMessageDto();
        message.setMessage("Quantity must be number");
        message.setErrorStatus(true);
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseBody
    public CartStatusMessageDto outOfStockException(OutOfStockException exception) {
        CartStatusMessageDto message = new CartStatusMessageDto();
        message.setMessage("Out of stock. " + exception.getMessage());
        message.setErrorStatus(true);
        message.setTotalCost(cartService.getTotalCost());
        message.setTotalQuantity(cartService.getTotalQuantity());
        return message;
    }
}
