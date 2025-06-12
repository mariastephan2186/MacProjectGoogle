import { test, expect } from '@playwright/test';

const API_BASE_URL = process.env.API_URL || 'http://localhost:3000/api';

test.describe('API Tests', () => {
  test.beforeEach(async ({ request }) => {
    // You can setup any test data or authentication here
  });

  test('GET /api/health - should return health status', async ({ request }) => {
    const response = await request.get(`${API_BASE_URL}/health`);
    expect(response.ok()).toBeTruthy();
    expect(await response.json()).toHaveProperty('status', 'ok');
  });

  test('POST /api/trade/buy - should create buy order', async ({ request }) => {
    const response = await request.post(`${API_BASE_URL}/trade/buy`, {
      data: {
        tokenSymbol: 'ETH',
        amount: '0.1',
        paymentMethod: 'card'
      }
    });

    expect(response.ok()).toBeTruthy();
    const data = await response.json();
    expect(data).toHaveProperty('orderId');
    expect(data).toHaveProperty('status');
  });

  test('POST /api/trade/sell - should create sell order', async ({ request }) => {
    const response = await request.post(`${API_BASE_URL}/trade/sell`, {
      data: {
        tokenSymbol: 'ETH',
        amount: '0.05',
        receiveMethod: 'bank'
      }
    });

    expect(response.ok()).toBeTruthy();
    const data = await response.json();
    expect(data).toHaveProperty('orderId');
    expect(data).toHaveProperty('status');
  });

  test('GET /api/trade/orders - should list orders', async ({ request }) => {
    const response = await request.get(`${API_BASE_URL}/trade/orders`);
    expect(response.ok()).toBeTruthy();
    const data = await response.json();
    expect(Array.isArray(data)).toBeTruthy();
  });
});