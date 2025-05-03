import { service } from "../services/productService.js";
import asyncHandler from 'express-async-handler';

const addProduct= asyncHandler(
    async (req, res) =>{
        const {name, type, price, company, status}= req.body;
        if(!name || !type || !price || !company || !status){
            return res.status(400).json({message: 'Missing input'});
        }
        const product= await service.saveProduct(req.body);
        res.status(201).json(product);
    }
);

const getProduct= asyncHandler(
    async (req, res) =>{
        const product= await service.getSingleProduct(req.params);
        if(!product){
            return res.status(400).json({message: `Product with ${req.params} not found`})
        }
        return res.status(200).json(product);
    }
);

const deleteProducts= asyncHandler(
    async (req, res) =>{
        await service.deleteProduct(req.params);
        return res.status(200).json({message: `Product with ${req.params} is deleted Successfully`})
    }
);

export const controller= {
    addProduct,
    getProduct,
    deleteProducts
}