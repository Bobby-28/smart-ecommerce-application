import express from 'express';
import router from './src/routes/cartRoutes.js';
import cors from 'cors';
import Eureka from 'eureka-js-client';

const app= express();

const { Eureka } = require('eureka-js-client');
//app.use(cors());
app.use(express.json());

app.use(cors());

app.use('/api/v1/cart', router);

const port = process.env.PORT || 6066;

const client = new Eureka({
    instance: {
      app: 'cart-service', // This is the service name registered with Eureka
      instanceId: 'cart-service-1',
      hostName: 'localhost',
      ipAddr: '127.0.0.1',
      port: {
        '$': 6066,
        '@enabled': true,
      },
      vipAddress: 'CART-SERVICE',
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
    console.log(`server run at http://localhost:${port}`);
})