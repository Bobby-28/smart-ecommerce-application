import express from 'express';
import { controller } from '../controller/productController.js';

const router= express.Router();

router.route("/").post(controller.addProduct);
router.route("/:id").get(controller.getProduct);
router.route("/:id").delete(controller.deleteProducts);
export default router;