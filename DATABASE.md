# Database Schema Documentation

## 📊 Overview

The Hotel Room Booking Application uses a relational database with three core tables:
- `rooms` - Room inventory management
- `guests` - Guest information
- `bookings` - Booking records with date-overlap prevention

**Supported Databases:**
- PostgreSQL (Primary)
- MySQL (Compatible)

---

## 🗂️ Table Schemas

### 1. ROOMS Table

**Purpose:** Store room information and availability status

```sql
CREATE TABLE rooms (
    id SERIAL PRIMARY KEY,
    number VARCHAR(50) NOT NULL UNIQUE,
    type VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    capacity INT NOT NULL CHECK (capacity > 0),
    status VARCHAR(50) NOT NULL DEFAULT 'Available' 
        CHECK (status IN ('Available', 'Booked', 'Maintenance')),
    description TEXT,
    amenities TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Columns:**
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | SERIAL | PRIMARY KEY | Unique room identifier |
| number | VARCHAR(50) | NOT NULL, UNIQUE | Room number (e.g., "101", "A-201") |
| type | VARCHAR(100) | NOT NULL | Room type (Standard, Deluxe, Suite) |
| price | DECIMAL(10,2) | NOT NULL, > 0 | Price per night in USD |
| capacity | INT | NOT NULL, > 0 | Maximum guest capacity |
| status | VARCHAR(50) | DEFAULT 'Available' | Available \| Booked \| Maintenance |
| description | TEXT | Optional | Room description and features |
| amenities | TEXT | Optional | JSON array of amenities (WiFi, Parking, etc.) |
| created_at | TIMESTAMP | Auto | Record creation timestamp |
| updated_at | TIMESTAMP | Auto | Record last update timestamp |

**Sample Data:**
```sql
INSERT INTO rooms (number, type, price, capacity, description, amenities)
VALUES 
    ('101', 'Standard', 99.00, 2, 'Cozy room with city view', '["WiFi", "TV", "Parking"]'),
    ('102', 'Deluxe', 199.00, 4, 'Spacious room with mini fridge', '["WiFi", "Parking", "Pool"]'),
    ('103', 'Suite', 299.00, 6, 'Premium suite with living area', '["WiFi", "Parking", "Gym", "Pool"]');
```

---

### 2. GUESTS Table

**Purpose:** Store guest contact and personal information

```sql
CREATE TABLE guests (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    country VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Columns:**
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | SERIAL | PRIMARY KEY | Unique guest identifier |
| name | VARCHAR(255) | NOT NULL | Guest's full name |
| email | VARCHAR(255) | NOT NULL, UNIQUE | Guest's email address |
| phone | VARCHAR(20) | Optional | Guest's phone number |
| country | VARCHAR(100) | Optional | Guest's country |
| created_at | TIMESTAMP | Auto | Record creation timestamp |
| updated_at | TIMESTAMP | Auto | Record last update timestamp |

**Sample Data:**
```sql
INSERT INTO guests (name, email, phone, country)
VALUES 
    ('John Doe', 'john@example.com', '+1-555-0101', 'USA'),
    ('Jane Smith', 'jane@example.com', '+1-555-0102', 'Canada'),
    ('Mike Johnson', 'mike@example.com', '+44-7911-123456', 'UK');
```

---

### 3. BOOKINGS Table

**Purpose:** Store booking records with date-overlap conflict detection

```sql
CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    room_id INT NOT NULL,
    guest_id INT NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL CHECK (total >= 0),
    status VARCHAR(50) NOT NULL DEFAULT 'Active' 
        CHECK (status IN ('Active', 'Cancelled', 'Completed')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign Key Constraints
    CONSTRAINT fk_bookings_room FOREIGN KEY (room_id) 
        REFERENCES rooms(id) ON DELETE RESTRICT,
    CONSTRAINT fk_bookings_guest FOREIGN KEY (guest_id) 
        REFERENCES guests(id) ON DELETE RESTRICT,
    
    -- Data Integrity Constraints
    CONSTRAINT check_in_before_checkout CHECK (check_in < check_out),
    
    -- Indexes for query optimization
    INDEX idx_bookings_room (room_id),
    INDEX idx_bookings_guest (guest_id),
    INDEX idx_bookings_check_in (check_in),
    INDEX idx_bookings_check_out (check_out),
    INDEX idx_bookings_status (status)
);
```

**Columns:**
| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | SERIAL | PRIMARY KEY | Unique booking identifier |
| room_id | INT | NOT NULL, FK | Reference to rooms table |
| guest_id | INT | NOT NULL, FK | Reference to guests table |
| check_in | DATE | NOT NULL | Check-in date (YYYY-MM-DD) |
| check_out | DATE | NOT NULL | Check-out date (YYYY-MM-DD) |
| total | DECIMAL(10,2) | NOT NULL, ≥ 0 | Total booking price (calculated) |
| status | VARCHAR(50) | DEFAULT 'Active' | Active \| Cancelled \| Completed |
| created_at | TIMESTAMP | Auto | Record creation timestamp |
| updated_at | TIMESTAMP | Auto | Record last update timestamp |

**Constraints:**
- `check_in < check_out`: Ensures check-out is always after check-in
- `total >= 0`: Prevents negative prices
- `status IN (...)`: Enforces valid status values

---

## 🔑 Relationships

```
┌─────────────────┐
│     ROOMS       │
├─────────────────┤
│ id (PK)         │
│ number          │
│ type            │
│ price           │
│ capacity        │
│ status          │
└─────────────────┘
        ↑
        │ (1:M)
        │ room_id
┌─────────────────┐
│    BOOKINGS     │
├─────────────────┤
│ id (PK)         │
│ room_id (FK)    │
│ guest_id (FK)   │
│ check_in        │
│ check_out       │
│ total           │
│ status          │
└─────────────────┘
        ↑
        │ (1:M)
        │ guest_id
┌─────────────────┐
│     GUESTS      │
├─────────────────┤
│ id (PK)         │
│ name            │
│ email           │
│ phone           │
│ country         │
└─────────────────┘
```

- **1:M (Rooms → Bookings):** One room can have many bookings
- **1:M (Guests → Bookings):** One guest can have many bookings

---

## 🚨 CRITICAL: Date-Overlap Conflict Check

### Problem
Prevent double-booking: A room cannot be booked by multiple guests for overlapping date ranges.

### Solution
Use a range overlap check in SQL:

```sql
-- Check if new booking overlaps with existing active bookings
SELECT COUNT(*) as conflict_count
FROM bookings
WHERE room_id = ? 
  AND status = 'Active'
  AND check_in < ?      -- new_check_out
  AND check_out > ?;    -- new_check_in

-- If conflict_count > 0, booking request is rejected
```

### Logic Explanation
Two date ranges overlap if:
- Existing check_in < New check_out  **AND**
- Existing check_out > New check_in

**Examples:**
```
✗ CONFLICT:
  Existing: [2026-05-20 → 2026-05-25]
  New:      [2026-05-23 → 2026-05-27]  (overlaps)

✓ NO CONFLICT:
  Existing: [2026-05-20 → 2026-05-25]
  New:      [2026-05-25 → 2026-05-30]  (back-to-back is ok)

✗ CONFLICT:
  Existing: [2026-05-20 → 2026-05-25]
  New:      [2026-05-15 → 2026-05-22]  (overlaps)
```

### TypeScript Implementation

```typescript
// In src/lib/db.ts
export const isRoomAvailable = async (
  roomId: number,
  checkIn: Date,
  checkOut: Date
): Promise<boolean> => {
  const result = await query(
    `SELECT COUNT(*) as conflict_count
     FROM bookings
     WHERE room_id = $1
       AND status = 'Active'
       AND check_in < $2
       AND check_out > $3`,
    [roomId, checkOut, checkIn]
  );
  
  return result.rows[0].conflict_count === 0;
};
```

### Usage in Booking Creation

```typescript
// Step 1: Check availability
const isAvailable = await isRoomAvailable(roomId, checkIn, checkOut);
if (!isAvailable) {
  throw new Error('Room is not available for selected dates');
}

// Step 2: Calculate total price
const nights = (checkOut - checkIn) / (1000 * 60 * 60 * 24);
const total = roomPrice * nights;

// Step 3: Create booking
INSERT INTO bookings (room_id, guest_id, check_in, check_out, total, status)
VALUES ($1, $2, $3, $4, $5, 'Active');
```

---

## 💰 Pricing Logic

### Formula
```
Total Cost = Number of Nights × Room Price per Night
```

### Calculation
```typescript
const checkInDate = new Date('2026-05-20');
const checkOutDate = new Date('2026-05-25');
const pricePerNight = 99.00;

const nights = (checkOutDate - checkInDate) / (1000 * 60 * 60 * 24);
// nights = 5

const totalCost = nights * pricePerNight;
// totalCost = 5 * 99 = $495.00
```

### Database Calculation
```sql
-- Calculate total on INSERT
INSERT INTO bookings (room_id, guest_id, check_in, check_out, total, status)
SELECT $1, $2, $3, $4, 
       (r.price * ($4::DATE - $3::DATE)) as total,
       'Active'
FROM rooms r
WHERE r.id = $1;
```

---

## 🔐 Indexes for Performance

```sql
-- Room lookups
CREATE INDEX idx_rooms_status ON rooms(status);
CREATE INDEX idx_rooms_type ON rooms(type);

-- Guest lookups
CREATE INDEX idx_guests_email ON guests(email);

-- Booking queries (most critical)
CREATE INDEX idx_bookings_room ON bookings(room_id);
CREATE INDEX idx_bookings_guest ON bookings(guest_id);
CREATE INDEX idx_bookings_check_in ON bookings(check_in);
CREATE INDEX idx_bookings_check_out ON bookings(check_out);
CREATE INDEX idx_bookings_status ON bookings(status);

-- Composite index for availability checks
CREATE INDEX idx_bookings_composite ON bookings(room_id, check_in, check_out, status);
```

---

## 📋 Views for Convenience

### 1. Active Bookings View
```sql
CREATE VIEW active_bookings_view AS
SELECT 
    b.id,
    b.room_id,
    r.number AS room_number,
    r.type AS room_type,
    b.guest_id,
    g.name AS guest_name,
    g.email AS guest_email,
    b.check_in,
    b.check_out,
    b.total
FROM bookings b
JOIN rooms r ON b.room_id = r.id
JOIN guests g ON b.guest_id = g.id
WHERE b.status = 'Active';
```

### 2. Room Availability View
```sql
CREATE VIEW room_availability_view AS
SELECT 
    r.id,
    r.number,
    r.type,
    r.price,
    r.capacity,
    r.status,
    COUNT(b.id) AS active_booking_count,
    COALESCE(MAX(b.check_out), CURRENT_DATE) AS last_checkout_date
FROM rooms r
LEFT JOIN bookings b ON r.id = b.room_id AND b.status = 'Active'
GROUP BY r.id;
```

---

## 🔧 Setup Instructions

### PostgreSQL

```bash
# 1. Create database
createdb hotel_booking

# 2. Connect to database
psql hotel_booking

# 3. Run schema
psql hotel_booking < src/db/schema.sql

# 4. Seed sample data
psql hotel_booking < src/db/seed.sql
```

### MySQL

```bash
# 1. Create database
mysql -u root -p -e "CREATE DATABASE hotel_booking;"

# 2. Run schema
mysql -u root -p hotel_booking < src/db/schema.sql

# 3. Seed sample data
mysql -u root -p hotel_booking < src/db/seed.sql
```

---

## 📈 Analytics Queries

### Room Occupancy Rate
```sql
SELECT 
    r.id,
    r.number,
    COUNT(b.id) as total_bookings,
    SUM(b.total) as total_revenue,
    ROUND(COUNT(b.id)::NUMERIC / 365 * 100, 2) as occupancy_rate
FROM rooms r
LEFT JOIN bookings b ON r.id = b.room_id AND b.status IN ('Active', 'Completed')
WHERE b.created_at >= CURRENT_DATE - INTERVAL '1 year'
GROUP BY r.id, r.number
ORDER BY total_revenue DESC;
```

### Upcoming Check-Ins (Next 7 Days)
```sql
SELECT 
    b.id,
    r.number,
    g.name,
    g.phone,
    b.check_in,
    DATEDIFF(b.check_in, CURRENT_DATE) as days_until_checkin
FROM bookings b
JOIN rooms r ON b.room_id = r.id
JOIN guests g ON b.guest_id = g.id
WHERE b.status = 'Active'
  AND b.check_in BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL 7 DAY
ORDER BY b.check_in ASC;
```

### Guest Booking History
```sql
SELECT 
    b.*,
    r.number,
    r.type,
    (b.check_out - b.check_in) as nights
FROM bookings b
JOIN rooms r ON b.room_id = r.id
WHERE b.guest_id = ?
ORDER BY b.check_in DESC;
```

---

## ✅ Validation Rules

| Rule | Table | Enforcement |
|------|-------|-------------|
| Check-in < Check-out | bookings | CHECK constraint |
| Price > 0 | rooms | CHECK constraint |
| Capacity > 0 | rooms | CHECK constraint |
| Status valid | rooms, bookings | CHECK constraint |
| No null room_id/guest_id | bookings | NOT NULL |
| No overlapping dates | bookings | Application logic |
| Unique email | guests | UNIQUE constraint |
| Unique room number | rooms | UNIQUE constraint |

---

**Last Updated:** May 2026  
**Version:** 1.0
