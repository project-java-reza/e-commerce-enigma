package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.ProductPrice;

public interface ProductPriceService  {

    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);
    ProductPrice findProductPriceActive(String productId, Boolean active); // disini artinya productPrice tidak akan pernah dihapus di aktifin atau nonaktifin

}
