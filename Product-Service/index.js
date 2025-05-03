import express from 'express';
import 'dotenv/config'
import router from './src/routes/productRoutes.js';
import connectDb from './src/repository/dbConnection.js';
import cors from 'cors';

const app= express();
const { Eureka } = require('eureka-js-client');
connectDb();
app.use(express.json());
app.use(cors());
app.use("/api/v1/products", router);
const port= process.env.PORT || 6065;

const client = new Eureka({
    instance: {
      app: 'cart-service',
      instanceId: 'cart-service-1',
      hostName: 'localhost',
      ipAddr: '127.0.0.1',
      port: {
        '$': 6065,
        '@enabled': true,
      },
      vipAddress: 'cart-service',
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
    },
    eureka: {
      host: 'localhost',
      port: 8761,
      servicePath: '/eureka',
      maxRetries: 10,
      requestRetryDelay: 2000,
    },
  });
  
  client.start((error) => {
    if (error) {
      console.error('Eureka registration failed:', error);
    } else {
      console.log('Cart service registered with Eureka');
    }
  });

app.listen(port, ()=>{
    console.log(`server start at http://localhost:${port}`);
});