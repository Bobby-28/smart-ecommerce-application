import mongoose from "mongoose";

const productSchema= new mongoose.Schema(
    {
        name: {
            type: String,
            required: [true, "Please add name"]
        },
        type: {
            type: String,
            required: [true, "Please add type"]
        },
        price: {
            type: String,
            required: [true, "Please add price of the product"]
        },
        company: {
            type: String,
            required: [true, "Please add company name"]
        },
        status: {
            type: String,
            required: [true, "Please add status of the product"]
        }
    },
    {
        timestamps: true
    }
);
export const Products= mongoose.model("Products", productSchema);