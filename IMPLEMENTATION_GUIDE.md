# Hotel Booking App - Implementation & Setup Guide

## 🚀 Getting Started

This guide will help you set up and run the Hotel Room Booking Web Application.

---

## 📋 Prerequisites

- **Node.js** 18+ and npm 9+
- **PostgreSQL** 13+ or **MySQL** 8+
- **Git** for version control
- **VS Code** or any code editor

---

## 🔧 Initial Setup

### 1. Clone and Install Dependencies

```bash
cd hotel-booking-app

# Install dependencies
npm install

# Verify installation
npm --version
node --version
```

### 2. Database Setup

#### PostgreSQL Setup

```bash
# Create database
createdb hotel_booking

# Create admin user (optional)
psql -c "CREATE USER hotel_admin WITH PASSWORD 'secure_password';"
psql -c "ALTER ROLE hotel_admin WITH CREATEDB;"

# Grant privileges
psql -c "GRANT ALL PRIVILEGES ON DATABASE hotel_booking TO hotel_admin;"

# Load schema
psql hotel_booking < src/db/schema.sql

# Seed sample data (optional)
psql hotel_booking < src/db/seed.sql

# Verify tables
psql hotel_booking -c "\dt"
```

#### MySQL Setup

```bash
# Create database
mysql -u root -p -e "CREATE DATABASE hotel_booking CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# Create user (optional)
mysql -u root -p -e "CREATE USER 'hotel_admin'@'localhost' IDENTIFIED BY 'secure_password';"
mysql -u root -p -e "GRANT ALL PRIVILEGES ON hotel_booking.* TO 'hotel_admin'@'localhost';"

# Load schema
mysql -u root -p hotel_booking < src/db/schema.sql

# Seed sample data
mysql -u root -p hotel_booking < src/db/seed.sql

# Verify tables
mysql -u root -p hotel_booking -e "SHOW TABLES;"
```

### 3. Environment Configuration

```bash
# Copy environment template
cp .env.example .env.local

# Edit .env.local with your database credentials
```

**For PostgreSQL:**
```env
DATABASE_URL=postgresql://hotel_admin:secure_password@localhost:5432/hotel_booking
NODE_ENV=development
NEXT_PUBLIC_API_URL=http://localhost:3000
```

**For MySQL:**
```env
DATABASE_URL=mysql://hotel_admin:secure_password@localhost:3306/hotel_booking
NODE_ENV=development
NEXT_PUBLIC_API_URL=http://localhost:3000
```

---

## 🏃 Running the Application

### Development Mode

```bash
# Start development server
npm run dev

# App will be available at http://localhost:3000
```

### Production Build

```bash
# Build the project
npm run build

# Start production server
npm start
```

### Type Checking

```bash
# Check TypeScript types
npm run type-check

# Run linter
npm run lint
```

---

## 📁 Project Structure Quick Reference

```
hotel-booking-app/
├── src/
│   ├── app/                 # Next.js App Router
│   │   ├── (public)/        # Guest pages
│   │   ├── (admin)/         # Admin pages
│   │   └── api/             # Backend API routes
│   ├── components/
│   │   ├── public/          # Guest components
│   │   ├── admin/           # Admin components
│   │   └── common/          # Shared components
│   ├── lib/
│   │   ├── db.ts            # Database functions
│   │   ├── validation.ts    # Form validation
│   │   └── dates.ts         # Date utilities
│   ├── db/
│   │   ├── schema.sql       # Database DDL
│   │   └── queries.sql      # SQL queries
│   └── types/
│       └── index.ts         # TypeScript types
├── public/                  # Static assets
├── DATABASE.md              # Database documentation
├── DESIGN_SYSTEM.md         # UI/UX guidelines
├── package.json
├── tailwind.config.js
└── tsconfig.json
```

---

## 🎯 Key Features Implementation

### ✅ Public Guest Features

#### 1. Home Page (`src/app/(public)/page.tsx`)
```typescript
export default function Home() {
  return (
    <main>
      <HeroSection />
      <FeaturesShowcase />
      <CTASection />
    </main>
  );
}
```

#### 2. Room List Page (`src/app/(public)/rooms/page.tsx`)
```typescript
'use client';
import { RoomList } from '@/components/public/RoomList';
import { AvailabilityFilter } from '@/components/public/AvailabilityFilter';

export default function RoomsPage() {
  const [filters, setFilters] = useState({});
  
  return (
    <div className="container mx-auto p-6">
      <AvailabilityFilter onFilter={setFilters} />
      <RoomList filters={filters} />
    </div>
  );
}
```

#### 3. Room Booking (`src/app/(public)/rooms/[id]/booking/page.tsx`)
```typescript
'use client';
import { BookingForm } from '@/components/public/BookingForm';
import { useParams } from 'next/navigation';

export default function BookingPage() {
  const params = useParams();
  const roomId = parseInt(params.id as string);
  
  const handleBooking = async (data) => {
    const response = await fetch('/api/bookings/create', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    // Handle response
  };
  
  return <BookingForm roomId={roomId} onSubmit={handleBooking} />;
}
```

#### 4. My Bookings (`src/app/(public)/my-bookings/page.tsx`)
```typescript
'use client';
import { useEffect, useState } from 'react';
import { BookingCard } from '@/components/public/BookingCard';

export default function MyBookingsPage() {
  const [bookings, setBookings] = useState([]);
  
  useEffect(() => {
    // Fetch guest bookings
    fetch(`/api/bookings/guest/${guestId}`)
      .then(res => res.json())
      .then(data => setBookings(data));
  }, []);
  
  return (
    <div className="space-y-4">
      {bookings.map(booking => (
        <BookingCard key={booking.id} booking={booking} />
      ))}
    </div>
  );
}
```

### 🛠️ Admin Features

#### 1. Admin Dashboard (`src/app/(admin)/dashboard/page.tsx`)
```typescript
import { BookingsTable } from '@/components/admin/BookingsTable';
import { StatsCards } from '@/components/admin/StatsCards';
import { getAllBookings, getRoomAnalytics } from '@/lib/db';

export default async function AdminDashboard() {
  const bookings = await getAllBookings();
  const analytics = await getRoomAnalytics();
  
  return (
    <div className="space-y-6">
      <StatsCards analytics={analytics} />
      <BookingsTable bookings={bookings} />
    </div>
  );
}
```

#### 2. Room Management (`src/app/(admin)/rooms-management/page.tsx`)
```typescript
'use client';
import { RoomsManagement } from '@/components/admin/RoomsManagement';

export default function RoomsPage() {
  return <RoomsManagement />;
}
```

### 🔌 Backend API Routes

#### Availability Check (`src/app/api/availability/check/route.ts`)
```typescript
import { isRoomAvailable } from '@/lib/db';
import { ApiResponse } from '@/types';

export async function POST(req: Request) {
  const { roomId, checkIn, checkOut } = await req.json();
  
  try {
    const isAvailable = await isRoomAvailable(
      roomId,
      new Date(checkIn),
      new Date(checkOut)
    );
    
    return Response.json({
      success: true,
      data: { isAvailable },
    } as ApiResponse<{ isAvailable: boolean }>);
  } catch (error) {
    return Response.json(
      { success: false, error: error.message },
      { status: 500 }
    );
  }
}
```

#### Create Booking (`src/app/api/bookings/create/route.ts`)
```typescript
import { createBooking, createOrGetGuest } from '@/lib/db';

export async function POST(req: Request) {
  const { roomId, guestName, guestEmail, guestPhone, checkIn, checkOut } =
    await req.json();
  
  try {
    // Get or create guest
    const guest = await createOrGetGuest(
      guestName,
      guestEmail,
      guestPhone
    );
    
    // Create booking (with automatic conflict check and price calculation)
    const booking = await createBooking(
      roomId,
      guest.id,
      new Date(checkIn),
      new Date(checkOut)
    );
    
    return Response.json({
      success: true,
      data: booking,
    });
  } catch (error) {
    return Response.json(
      { success: false, error: error.message },
      { status: 400 }
    );
  }
}
```

---

## 🗄️ Database Functions Cheat Sheet

### Check Room Availability
```typescript
import { isRoomAvailable } from '@/lib/db';

const available = await isRoomAvailable(
  roomId,
  new Date('2026-05-20'),
  new Date('2026-05-25')
);
```

### Create Booking (with conflict check)
```typescript
import { createBooking } from '@/lib/db';

const booking = await createBooking(
  roomId,
  guestId,
  checkInDate,
  checkOutDate
);
```

### Get Available Rooms for Date Range
```typescript
import { getAvailableRooms } from '@/lib/db';

const rooms = await getAvailableRooms(
  new Date('2026-05-20'),
  new Date('2026-05-25'),
  'Deluxe' // optional room type filter
);
```

### Get Guest Bookings
```typescript
import { getGuestBookings } from '@/lib/db';

const bookings = await getGuestBookings(guestId);
const activeBookings = await getGuestBookings(guestId, 'Active');
```

### Cancel Booking
```typescript
import { cancelBooking } from '@/lib/db';

const cancelled = await cancelBooking(bookingId, guestId);
```

---

## 🎨 UI Component Usage

### RoomCard Component
```typescript
import { RoomCard } from '@/components/public/RoomCard';

<RoomCard
  room={roomData}
  onBook={(roomId) => console.log('Book room', roomId)}
  isAvailableForDates={true}
/>
```

### BookingForm Component
```typescript
import { BookingForm } from '@/components/public/BookingForm';

<BookingForm
  roomId={101}
  roomNumber="101"
  roomType="Deluxe"
  pricePerNight={99}
  onSubmit={handleBooking}
  onCancel={() => navigate('/rooms')}
/>
```

### BookingsTable Component (Admin)
```typescript
import { BookingsTable } from '@/components/admin/BookingsTable';

<BookingsTable
  bookings={bookingsList}
  onEdit={(id) => editBooking(id)}
  onCancel={(id) => cancelBooking(id)}
  onRenew={(id) => renewBooking(id)}
  isLoading={false}
/>
```

---

## 🧪 Testing

### Run Tests
```bash
npm run test

# Watch mode
npm run test:watch
```

### Example Test
```typescript
// src/lib/db.test.ts
import { isRoomAvailable } from './db';

describe('isRoomAvailable', () => {
  it('should return false for overlapping dates', async () => {
    const result = await isRoomAvailable(
      1,
      new Date('2026-05-20'),
      new Date('2026-05-25')
    );
    expect(result).toBe(false);
  });
});
```

---

## 📊 Database Queries Cheat Sheet

### Find All Active Bookings
```sql
SELECT * FROM bookings WHERE status = 'Active' ORDER BY check_in DESC;
```

### Check Date Overlap (CRITICAL)
```sql
SELECT COUNT(*) FROM bookings
WHERE room_id = 1
  AND status = 'Active'
  AND check_in < '2026-05-25'
  AND check_out > '2026-05-20';
```

### Get Room Revenue
```sql
SELECT r.number, SUM(b.total) as revenue
FROM rooms r
LEFT JOIN bookings b ON r.id = b.room_id AND b.status IN ('Active', 'Completed')
GROUP BY r.number
ORDER BY revenue DESC;
```

### Get Guest Booking History
```sql
SELECT b.*, r.number, r.type
FROM bookings b
JOIN rooms r ON b.room_id = r.id
WHERE b.guest_id = 1
ORDER BY b.check_in DESC;
```

---

## 🚨 Error Handling

### Common Error Scenarios

```typescript
// 1. Date Overlap Error
try {
  await createBooking(roomId, guestId, checkIn, checkOut);
} catch (error) {
  if (error.message.includes('not available')) {
    // Show: "Room not available for selected dates"
  }
}

// 2. Invalid Date Range
if (checkOut <= checkIn) {
  throw new Error('Check-out must be after check-in');
}

// 3. Missing Fields
if (!guestName || !guestEmail) {
  throw new ValidationError('Required fields missing');
}
```

---

## 📈 Performance Optimization

### Database Indexes
All critical indexes are already in `schema.sql`:
- Room status lookups
- Booking date range queries
- Guest email lookups
- Composite index for availability checks

### Query Optimization
```typescript
// ✅ GOOD: Uses index
const result = await query(
  'SELECT * FROM bookings WHERE room_id = $1 AND status = $2',
  [roomId, 'Active']
);

// ❌ BAD: Full table scan
const result = await query(
  'SELECT * FROM bookings WHERE LOWER(status) = $1',
  ['active']
);
```

---

## 🔒 Security Best Practices

1. **SQL Injection Prevention:** Always use parameterized queries
   ```typescript
   // ✅ GOOD
   await query('SELECT * FROM rooms WHERE id = $1', [roomId]);
   
   // ❌ BAD
   await query(`SELECT * FROM rooms WHERE id = ${roomId}`);
   ```

2. **Authentication (Future):** Implement JWT tokens
3. **Rate Limiting:** Add rate limiter to API endpoints
4. **Input Validation:** Use Zod for schema validation
5. **CORS:** Configure allowed origins

---

## 📚 Additional Resources

- [Next.js Documentation](https://nextjs.org/docs)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)

---

## 🤝 Deployment

### Environment-Specific Setup

**Staging:**
```env
DATABASE_URL=postgresql://user:pass@staging-db:5432/hotel_booking
NODE_ENV=staging
NEXT_PUBLIC_API_URL=https://staging-api.hotel-booking.com
```

**Production:**
```env
DATABASE_URL=postgresql://user:pass@prod-db:5432/hotel_booking
NODE_ENV=production
NEXT_PUBLIC_API_URL=https://api.hotel-booking.com
```

### Deploy to Vercel

```bash
# Install Vercel CLI
npm i -g vercel

# Deploy
vercel

# With environment variables
vercel --env DATABASE_URL=postgresql://...
```

---

## ✅ Checklist Before Launch

- [ ] Database configured and tested
- [ ] Environment variables set up
- [ ] All components rendering correctly
- [ ] Date-overlap logic verified
- [ ] Pricing calculation accurate
- [ ] Admin dashboard functional
- [ ] Forms validated
- [ ] API endpoints tested
- [ ] Error handling in place
- [ ] UI responsive on all devices

---

**Last Updated:** May 2026  
**Version:** 1.0
