package com.plusplus.etherealevecreations.service.cartitem;


import com.plusplus.etherealevecreations.entity.Cart;
import com.plusplus.etherealevecreations.entity.CartItem;
import com.plusplus.etherealevecreations.entity.Product;
import com.plusplus.etherealevecreations.repository.CartItemRepository;
import com.plusplus.etherealevecreations.repository.CartRepository;
import com.plusplus.etherealevecreations.service.cart.CartService;
import com.plusplus.etherealevecreations.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService{

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    private final ProductService productService;
    private final CartService cartService;



    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart=cartService.getCart(cartId);
        Product product=productService.getProductById(productId);
        CartItem cartItem=cart.getItems().stream().filter
                (item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());

        if(cartItem.getId() ==null){
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
        else {

            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }

        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

        Cart cart=cartService.getCart(cartId);
        CartItem itemToRemove=cart.getItems()
                .stream()
                .filter((item -> item.getProduct().getId().equals(productId));
                cart.removeItems(itemToRemove);
                cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
    }

}
