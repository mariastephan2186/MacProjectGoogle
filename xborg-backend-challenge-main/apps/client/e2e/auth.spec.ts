// apps/client/e2e/auth.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Authentication Flow', () => {
  test.beforeEach(async ({ page }) => {
    // Setup MetaMask and other prerequisites
    await page.goto('/');
  });

  test(' complete authentication flow with MetaMask', async ({ page }) => {
    // Connect wallet
    await page.getByTestId('connect-wallet-button').click();

    // Handle MetaMask popup
    const metamaskPopup = await page.waitForEvent('popup');
    await metamaskPopup.getByRole('button', { name: 'Connect' }).click();

    // Verify successful connection
    await expect(page.getByTestId('wallet-address')).toBeVisible();

    // Test session persistence
    await page.reload();
    await expect(page.getByTestId('wallet-address')).toBeVisible();
  });

  test('handles wallet disconnection', async ({ page }) => {
    // Connect wallet first
    await page.getByTestId('connect-wallet-button').click();

    // Disconnect
    await page.getByTestId('disconnect-wallet-button').click();

    // Verify disconnection
    await expect(page.getByTestId('connect-wallet-button')).toBeVisible();
  });
});

/**
 * Following additional tests cover:
 * - Edge cases in authentication
 * - Form validation
 * - Error states and recovery
 * - Data synchronization
 * - Network issues
 * - Performance metrics
 * - Concurrent operations
 * - State management across sessions
 */
// apps/client/e2e/user-interaction.spec.ts
test.describe('User Interaction Flows', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/');
    // Set up authenticated state
  });

  test('complete main user journey', async ({ page }) => {
    // Navigate through main flow
    await page.getByTestId('start-action').click();

    // Fill form
    await page.getByTestId('input-field').fill('test data');
    await page.getByTestId('submit-button').click();

    // Verify success
    await expect(page.getByTestId('success-message')).toBeVisible();

    // Verify persistence
    await page.reload();
    await expect(page.getByTestId('saved-data')).toHaveText('test data');
  });
});

// apps/client/e2e/data-persistence.spec.ts
test.describe('Data Persistence', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/');
  });

  test('persists user preferences', async ({ page }) => {
    // Navigate to settings
    await page.goto('/settings');

    // Toggle preference
    await page.getByTestId('preference-toggle').click();

    // Verify persistence
    await page.reload();
    await expect(page.getByTestId('preference-toggle')).toBeChecked();
  });

  test('maintains authentication across pages', async ({ page }) => {
    // Set up authenticated state
    await page.evaluate(() => {
      localStorage.setItem('auth-token', 'test-token');
    });

    // Navigate to protected page
    await page.goto('/protected');

    // Verify still authenticated
    await expect(page.getByTestId('protected-content')).toBeVisible();
  });

  test('shows error message for rejected wallet connection', async ({ page }) => {
    await page.getByTestId('connect-wallet-button').click();
    const metamaskPopup = await page.waitForEvent('popup');
    await metamaskPopup.getByRole('button', { name: 'Reject' }).click();
    await expect(page.getByTestId('connection-error')).toBeVisible();
  });

  test('handles network switching', async ({ page }) => {
    // Test wallet connection on different networks
    await page.getByTestId('connect-wallet-button').click();
    await page.getByTestId('network-switch').click();
    await expect(page.getByTestId('network-name')).toHaveText('Expected Network');
  });


  test('validates form input requirements', async ({ page }) => {
    await page.getByTestId('start-action').click();
    await page.getByTestId('submit-button').click();
    await expect(page.getByTestId('validation-error')).toBeVisible();
  });

  test('handles concurrent user actions', async ({ page }) => {
    // Test multiple rapid interactions
    await Promise.all([
      page.getByTestId('action-1').click(),
      page.getByTestId('action-2').click()
    ]);
    await expect(page.getByTestId('action-result')).toBeVisible();
  });

  test('clears data on logout', async ({ page }) => {
    // Setup initial state
    await page.goto('/settings');
    await page.getByTestId('preference-toggle').click();

    // Perform logout
    await page.getByTestId('logout-button').click();

    // Verify data is cleared
    await page.reload();
    await expect(page.getByTestId('preference-toggle')).not.toBeChecked();
  });

  test('syncs data across tabs', async ({ browser }) => {
    const context = await browser.newContext();
    const page1 = await context.newPage();
    const page2 = await context.newPage();

    await page1.goto('/settings');
    await page1.getByTestId('preference-toggle').click();

    await page2.goto('/settings');
    await expect(page2.getByTestId('preference-toggle')).toBeChecked();
  });

  test('handles network disconnection gracefully', async ({ page }) => {
    await page.route('**/*', (route) => route.abort('internetdisconnected'));
    await page.getByTestId('start-action').click();
    await expect(page.getByTestId('network-error')).toBeVisible();
  });

  test('retries failed operations', async ({ page }) => {
    let attemptCount = 0;
    await page.route('**/api/action', (route) => {
      attemptCount++;
      if (attemptCount < 2) {
        route.abort();
      } else {
        route.fulfill({ status: 200, body: 'Success' });
      }
    });

    await page.getByTestId('action-button').click();
    await expect(page.getByTestId('success-message')).toBeVisible();
  });

  test('loads within performance budget', async ({ page }) => {
    const startTime = Date.now();
    await page.goto('/');
    const loadTime = Date.now() - startTime;
    expect(loadTime).toBeLessThan(3000); // 3 second budget
  });

  test('handles large data sets', async ({ page }) => {
    // Test with significant amount of data
    await page.route('**/api/data', (route) => {
      route.fulfill({
        status: 200,
        body: JSON.stringify(Array(1000).fill({ id: 1, data: 'test' }))
      });
    });

    await page.goto('/data-view');
    await expect(page.getByTestId('data-grid')).toBeVisible();
  });
});