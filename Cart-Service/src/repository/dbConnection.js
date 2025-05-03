import redis from 'redis';

const client= redis.createClient();
client.connect().catch(console.error);

export default client;