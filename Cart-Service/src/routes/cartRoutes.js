import express from 'express';
import { controller } from "../controller/cartController.js";

const router= express.Router();

router.get('/', controller.fetchCart);
router.post('/', controller.addToCart);
router.delete('/:productId', controller.deleteItem);
router.delete('/', controller.cleartUserCart);

export default router;

