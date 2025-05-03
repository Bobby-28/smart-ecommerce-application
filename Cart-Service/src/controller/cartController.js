import { service } from "../services/cartService.js";
import asyncHandler from "express-async-handler";

const fetchCart= asyncHandler(
    async (req, res) =>{
        const userId= req.header('x-user-id');
        if(!userId){
            res.status(400).json({
                message: 'Missing userId'
            });
        }
        const cart= await service.getCart(userId);
        res.status(200).json(cart);
    }
);

const addToCart= asyncHandler(
    async (req, res) =>{
        const userId= req.header('x-user-id');
        const {productId, quantity, price}= req.body;
        if(!userId || !productId || !quantity || !price){
            return res.status(400).json({message: 'Missing input'});
        }

        const cart= await service.getCart(userId);
        const exitsing= cart.items.find(i => i.productId === productId);
        
        if(exitsing){
            exitsing.quantity += quantity;
        }
        else{
            cart.items.push({productId, quantity, price});
        }
        await service.saveCart(userId, cart);
        res.status(201).json(cart);
    }
);

const deleteItem= asyncHandler(
    async(req, res)=>{
        const userId= req.header('x-user-id');
        const {productId} = req.params;
        const cart= await service.getCart(userId);
        cart.items= cart.items.filter(i=> i.productId !== productId);
        await saveCart(userId, cart);
        res.status(201).json(cart);
    }
);

const cleartUserCart= asyncHandler(
    async(req, res)=>{
        const userId= req.header('x-user-id');
        await service.clearCart(userId);
        res.status(200).json(
            {message: 'cart cleared'}
        );
    }
);

export const controller= {
    fetchCart,
    addToCart,
    deleteItem,
    cleartUserCart
}