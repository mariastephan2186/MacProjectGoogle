import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import request from 'supertest';
// @ts-ignore
import { AppModule } from '../src/app.module';

describe('Cryptocurrency Trading API (e2e)', () => {
  let app: INestApplication;
  let authToken: string;

  beforeAll(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();

    // Get auth token for protected endpoints
    const loginResponse = await request(app.getHttpServer())
      .post('/auth/login')
      .send({
        address: '0x1234...', // Test wallet address
        signature: '0xabcd...', // Test signature
      });

    authToken = loginResponse.body.token;
  });

  afterAll(async () => {
    await app.close();
  });

  describe('Trading Operations', () => {
    describe('POST /api/trade/buy', () => {
      it('should create a buy order', async () => {
        return request(app.getHttpServer())
          .post('/api/trade/buy')
          .set('Authorization', `Bearer ${authToken}`)
          .send({
            tokenSymbol: 'ETH',
            amount: '0.1',
            paymentMethod: 'card'
          })
          .expect(201)
          .expect((res) => {
            expect(res.body).toHaveProperty('orderId');
            expect(res.body).toHaveProperty('status', 'pending');
            expect(res.body.type).toBe('buy');
          });
      });

      it('should validate minimum amount', async () => {
        return request(app.getHttpServer())
          .post('/api/trade/buy')
          .set('Authorization', `Bearer ${authToken}`)
          .send({
            tokenSymbol: 'ETH',
            amount: '0',
            paymentMethod: 'card'
          })
          .expect(400)
          .expect((res) => {
            expect(res.body.message).toContain('amount must be greater than 0');
          });
      });
    });

    describe('POST /api/trade/sell', () => {
      it('should create a sell order', async () => {
        return request(app.getHttpServer())
          .post('/api/trade/sell')
          .set('Authorization', `Bearer ${authToken}`)
          .send({
            tokenSymbol: 'ETH',
            amount: '0.05',
            receiveMethod: 'bank'
          })
          .expect(201)
          .expect((res) => {
            expect(res.body).toHaveProperty('orderId');
            expect(res.body).toHaveProperty('status', 'pending');
            expect(res.body.type).toBe('sell');
          });
      });
    });

    describe('POST /api/trade/swap', () => {
      it('should create a swap order', async () => {
        return request(app.getHttpServer())
          .post('/api/trade/swap')
          .set('Authorization', `Bearer ${authToken}`)
          .send({
            fromToken: 'ETH',
            toToken: 'USDT',
            amount: '0.1'
          })
          .expect(201)
          .expect((res) => {
            expect(res.body).toHaveProperty('orderId');
            expect(res.body).toHaveProperty('status', 'pending');
            expect(res.body.type).toBe('swap');
          });
      });

      it('should return current swap rate', async () => {
        return request(app.getHttpServer())
          .get('/api/trade/swap/rate')
          .query({
            fromToken: 'ETH',
            toToken: 'USDT',
            amount: '1.0'
          })
          .expect(200)
          .expect((res) => {
            expect(res.body).toHaveProperty('rate');
            expect(res.body).toHaveProperty('estimatedOutput');
          });
      });
    });

    describe('GET /api/trade/orders', () => {
      it('should return user orders', async () => {
        return request(app.getHttpServer())
          .get('/api/trade/orders')
          .set('Authorization', `Bearer ${authToken}`)
          .expect(200)
          .expect((res) => {
            expect(Array.isArray(res.body)).toBeTruthy();
            if (res.body.length > 0) {
              expect(res.body[0]).toHaveProperty('orderId');
              expect(res.body[0]).toHaveProperty('status');
              expect(res.body[0]).toHaveProperty('type');
            }
          });
      });

      it('should filter orders by status', async () => {
        return request(app.getHttpServer())
          .get('/api/trade/orders')
          .set('Authorization', `Bearer ${authToken}`)
          .query({ status: 'completed' })
          .expect(200)
          .expect((res) => {
            expect(Array.isArray(res.body)).toBeTruthy();
            // @ts-ignore
            res.body.forEach((order) => {
              expect(order.status).toBe('completed');
            });
          });
      });
    });
  });
});