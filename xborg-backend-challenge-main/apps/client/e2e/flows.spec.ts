import { test, expect } from '@playwright/test';
import axios from 'axios';

// Define base URLs and constants
const APP_URL = process.env.APP_URL || 'http://localhost:3000';
const METAMASK_URL = 'chrome-extension://nkbihfbeogaeaoehlefnkodbefgpgknn/home.html';

// Helper function to check if server is running
async function isServerRunning() {
  try {
    await axios.get(APP_URL);
    return true;
  } catch (error) {
    return false;
  }
}

test.describe('Cryptocurrency Trading Operations', () => {
  test.beforeAll(async () => {
    // Check if server is running before starting tests
    const serverRunning = await isServerRunning();
    if (!serverRunning) {
      throw new Error(`
        Server is not running at ${APP_URL}
        Please ensure your development server is started before running tests.
        You can start it with:
        - yarn dev
        - npm run dev
        - or the appropriate command for your project
      `);
    }
  });

  test.beforeEach(async ({ page }) => {
    try {
      // Navigate to your application with retry logic
      await test.step('Navigate to application', async () => {
        let retries = 3;
        while (retries > 0) {
          try {
            await page.goto(APP_URL, {
              waitUntil: 'networkidle',
              timeout: 30000
            });
            break;
          } catch (error) {
            retries--;
            if (retries === 0) throw error;
            await new Promise(resolve => setTimeout(resolve, 1000));
          }
        }
      });

      // Connect wallet
      await test.step('Connect wallet', async () => {
        await page.getByTestId('connect-wallet-button').click();
        const metamaskPopup = await page.waitForEvent('popup');
        await metamaskPopup.getByRole('button', { name: 'Connect' }).click();
        await expect(page.getByTestId('wallet-address')).toBeVisible();
      });
    } catch (error) {
      console.error('Setup failed:', error);
      throw error;
    }
  });

  test('Buy Operation - Basic Flow', async ({ context, page }) => {
    await test.step('Navigate to buy section', async () => {
      await page.getByTestId('buy-crypto-button').click();
    });

    await test.step('Fill buy details', async () => {
      await page.getByTestId('token-amount-input').fill('0.1');
      await page.getByTestId('buy-with-selector').click();
      await page.getByTestId('payment-method-card').click();
    });

    await test.step('Confirm transaction', async () => {
      await page.getByTestId('confirm-buy-button').click();
    });

    await test.step('Handle MetaMask confirmation', async () => {
      const metamaskPage = await context.newPage();
      try {
        await metamaskPage.goto(METAMASK_URL);
        await metamaskPage.getByTestId('confirm-transaction-button').click();
      } finally {
        await metamaskPage.close();
      }
    });

    await test.step('Verify transaction success', async () => {
      await expect(page.getByTestId('transaction-success')).toBeVisible();
    });
  });

  test('Sell Operation - Basic Flow', async ({ context, page }) => {
    await test.step('Navigate to sell section', async () => {
      await page.getByTestId('sell-crypto-button').click();
    });

    await test.step('Fill sell details', async () => {
      await page.getByTestId('token-amount-input').fill('0.05');
      await page.getByTestId('receive-method-selector').click();
      await page.getByTestId('bank-account-option').click();
    });

    await test.step('Confirm sell', async () => {
      await page.getByTestId('confirm-sell-button').click();
    });

    await test.step('Handle MetaMask confirmation', async () => {
      const metamaskPage = await context.newPage();
      try {
        await metamaskPage.goto(METAMASK_URL);
        await metamaskPage.getByTestId('confirm-transaction-button').click();
      } finally {
        await metamaskPage.close();
      }
    });

    await test.step('Verify success', async () => {
      await expect(page.getByTestId('sell-success')).toBeVisible();
    });
  });

  test('Swap Operation - Basic Flow', async ({ context, page }) => {
    await test.step('Navigate to swap section', async () => {
      await page.getByTestId('swap-tokens-button').click();
    });

    await test.step('Configure swap', async () => {
      await page.getByTestId('from-token-selector').click();
      await page.getByTestId('token-ETH').click();
      await page.getByTestId('to-token-selector').click();
      await page.getByTestId('token-USDT').click();
      await page.getByTestId('swap-amount-input').fill('0.1');
    });

    await test.step('Verify and confirm swap', async () => {
      await expect(page.getByTestId('estimated-output')).toBeVisible();
      await page.getByTestId('confirm-swap-button').click();
    });

    await test.step('Handle MetaMask confirmation', async () => {
      const metamaskPage = await context.newPage();
      try {
        await metamaskPage.goto(METAMASK_URL);
        await metamaskPage.getByTestId('confirm-transaction-button').click();
      } finally {
        await metamaskPage.close();
      }
    });

    await test.step('Verify swap success', async () => {
      await expect(page.getByTestId('swap-success')).toBeVisible();
    });
  });
});