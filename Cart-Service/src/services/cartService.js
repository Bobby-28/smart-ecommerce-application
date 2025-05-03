import client from "../repository/dbConnection.js";

const getCartKey= (userId) => `cart:${userId}`;

const getCart= async (userId) => {
    const data= await client.get(getCartKey(userId));
    return data ? JSON.parse(data) : {items: []};
}

const saveCart= async (userId, cart) =>{
    await client.set(getCartKey(userId), JSON.stringify(cart));
};

const clearCart= async (userId) =>{
    await client.del(getCartKey(userId));
};

export const service= {
    getCart,
    saveCart,
    clearCart
}