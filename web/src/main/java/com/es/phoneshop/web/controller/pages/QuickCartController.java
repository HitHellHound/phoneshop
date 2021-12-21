package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.cart.PhoneNotFoundException;
import com.es.core.cart.QuickCartDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/quickCart")
public class QuickCartController {
    @Resource
    private CartService cartService;

    private final String quickCartAttributeName = "quickCart";

    @GetMapping
    public String openQuickAddToCart(Model model) {
        model.addAttribute(quickCartAttributeName, new QuickCartDto());
        return "quickCart";
    }

    @PostMapping
    public String addToCart(@ModelAttribute(quickCartAttributeName) QuickCartDto quickCart, BindingResult bindingResult,
                            Model model) {
        addItemsToCart(quickCart, bindingResult, model);
        return "quickCart";
    }

    private void addItemsToCart(QuickCartDto quickCart, BindingResult bindingResult, Model model) {
        String addedPhones = "";
        for (int i = 0; i < quickCart.getModels().size(); i++) {
            String phoneModel = quickCart.getModels().get(i);
            String quantity = quickCart.getQuantities().get(i);
            if (!phoneModel.equals("") || !quantity.equals("")) {
                try {
                    Long phoneQuantity = Long.parseLong(quickCart.getQuantities().get(i));
                    cartService.addPhoneByModel(phoneModel, phoneQuantity);
                    quickCart.getModels().remove(i);
                    quickCart.getModels().add(i, "");
                    quickCart.getQuantities().remove(i);
                    quickCart.getQuantities().add(i, "");
                    addedPhones += phoneModel + "; ";
                } catch (NumberFormatException e) {
                    FieldError error = new FieldError(quickCartAttributeName, "quantities[" + i + "]",
                            "Not a number");
                    bindingResult.addError(error);
                } catch (PhoneNotFoundException e) {
                    FieldError error = new FieldError(quickCartAttributeName, "models[" + i + "]",
                            "Not found this model");
                    bindingResult.addError(error);
                }
            }
        }
        if (!addedPhones.equals("")) {
            model.addAttribute("successMessage", "Phones (" + addedPhones + ") added to cart");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "There was some errors");
        }
    }
}
