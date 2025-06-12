// apps/client/e2e/auth.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Authentication Flow', () => {
  test.beforeEach(async ({ page }) => {
    // Setup MetaMask and other prerequisites
    await page.goto('/');
  });

  test('complete authentication flow with MetaMask', async ({ page }) => {
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
});