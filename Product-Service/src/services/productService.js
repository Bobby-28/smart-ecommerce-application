import { Products } from '../models/product.js';

const saveProduct= async (product)=>{
    const data= await Products.create(product);
    return data;
}

const getSingleProduct= async (productId)=>{
    return await Products.findById(productId);
}

const deleteProduct= async (productId)=>{
    await Products.findByIdAndDelete(productId);
}

export const service= {
    saveProduct,
    getSingleProduct,
    deleteProduct
}