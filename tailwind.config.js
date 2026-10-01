/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/pages/**/*.{js,ts,jsx,tsx,mdx}',
    './src/components/**/*.{js,ts,jsx,tsx,mdx}',
    './src/app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors: {
        // Primary Colors
        primary: {
          50: '#EFF6FF',
          100: '#DBEAFE',
          500: '#2563EB',
          600: '#1D4ED8',
        },
        // Secondary Colors
        secondary: {
          50: '#F5F3FF',
          500: '#7C3AED',
          600: '#6D28D9',
        },
        // Status Colors
        success: {
          50: '#ECFDF5',
          500: '#10B981',
          700: '#065F46',
        },
        warning: {
          50: '#FFFBEB',
          500: '#F59E0B',
        },
        error: {
          50: '#FEF2F2',
          500: '#EF4444',
          700: '#7F1D1D',
        },
        info: {
          50: '#F0F9FF',
          500: '#0EA5E9',
          700: '#0369A1',
        },
        // Neutral Grayscale
        neutral: {
          0: '#FFFFFF',
          50: '#F9FAFB',
          100: '#F3F4F6',
          200: '#E5E7EB',
          300: '#D1D5DB',
          400: '#9CA3AF',
          500: '#6B7280',
          600: '#4B5563',
          700: '#374151',
          800: '#1F2937',
          900: '#111827',
        },
      },
      spacing: {
        xs: '4px',
        sm: '8px',
        md: '12px',
        lg: '16px',
        xl: '24px',
        '2xl': '32px',
        '3xl': '48px',
        '4xl': '64px',
      },
      borderRadius: {
        sm: '4px',
        md: '8px',
        lg: '12px',
        xl: '16px',
      },
      fontSize: {
        h1: ['36px', { lineHeight: '44px', fontWeight: '700', letterSpacing: '-0.5px' }],
        h2: ['28px', { lineHeight: '36px', fontWeight: '700', letterSpacing: '-0.3px' }],
        h3: ['24px', { lineHeight: '32px', fontWeight: '600' }],
        h4: ['20px', { lineHeight: '28px', fontWeight: '600' }],
        'body-lg': ['16px', { lineHeight: '24px', fontWeight: '400' }],
        body: ['14px', { lineHeight: '20px', fontWeight: '400' }],
        'body-sm': ['12px', { lineHeight: '16px', fontWeight: '400' }],
        label: ['12px', { lineHeight: '16px', fontWeight: '500', letterSpacing: '0.5px' }],
        caption: ['11px', { lineHeight: '14px', fontWeight: '400' }],
      },
      fontFamily: {
        sans: ['Inter', 'Segoe UI', 'system-ui', 'sans-serif'],
        mono: ['Roboto Mono', 'Courier New', 'monospace'],
      },
      boxShadow: {
        'elevation-1': '0 1px 2px rgba(0, 0, 0, 0.05)',
        'elevation-2': '0 4px 6px rgba(0, 0, 0, 0.07)',
        'elevation-3': '0 10px 15px rgba(0, 0, 0, 0.1)',
        'elevation-4': '0 20px 25px rgba(0, 0, 0, 0.15)',
      },
      animation: {
        fadeIn: 'fadeIn 0.3s ease-in-out',
        spin: 'spin 1s linear infinite',
      },
      keyframes: {
        fadeIn: {
          '0%': { opacity: '0' },
          '100%': { opacity: '1' },
        },
      },
    },
  },
  plugins: [],
};
