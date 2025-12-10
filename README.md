# Secventory — Concurrency-Safe Inventory Management System

## Problem Statement

In real-world e-commerce, booking, and marketplace systems, **inventory corruption under concurrent access and partial system failure** is one of the most common and most expensive classes of backend bugs.

Typical failure modes include:

* Overselling due to race conditions
* Duplicate deductions from retried requests
* Negative stock caused by non-atomic updates
* Corrupted state after application or database crashes
* Inconsistent inventory due to orphaned reservations

Most student and demo systems avoid these problems by simplifying the domain. **Secventory does the opposite.**

---

## Objective

Design and implement a **transaction-safe, concurrency-correct inventory management backend** that guarantees:

* Zero overselling under high load
* Exactly-once stock deduction
* Safe behavior under duplicate API requests
* Correct recovery after crashes and database failures
* Full auditability of all inventory events

This is a **systems correctness project**, not a CRUD demo.

---

## Core Functional Requirements

### 1. Product Management

* Create a product
* Fetch product details
* Fetch real-time inventory status

### 2. Inventory Reservation

* Temporarily reserve stock for a product
* Reservation must:

  * Immediately reduce available stock
  * Expire after a defined TTL if not confirmed
  * Be idempotent (duplicate requests must not double reserve)

### 3. Purchase Confirmation

* Convert a valid reservation into a final purchase
* Must be:

  * Atomic
  * Exactly-once
  * Safe under retries

### 4. Reservation Cancellation

* Cancel active reservations
* Restore stock correctly and atomically

### 5. Transaction Audit

* Maintain an immutable transaction history for:

  * Reservations
  * Purchases
  * Cancellations
  * Expirations

---

## Hard System Guarantees (Non-Negotiable)

The system is considered **invalid** if **any** of the following ever occurs:

1. **No Overselling**

   ```
   total_purchased + total_reserved ≤ total_stock  (must always hold)
   ```

2. **No Double Deduction**

   * Duplicate API calls must NOT deduct stock twice.

3. **No Negative Inventory**

   * Available stock must never drop below zero.

4. **Atomic Operations**

   * Every inventory mutation must be all-or-nothing.

5. **Crash Safety**

   * Killing the application or database mid-transaction must NOT corrupt inventory state.

6. **Eventual Consistency After Failure**

   * Expired reservations must be reconciled safely after failures.

---

## Non-Functional Requirements

* Must support **1000+ concurrent reserve & purchase requests**
* Must support **idempotent writes**
* Must expose system metrics:

  * Average reservation latency
  * Failure rate
  * Active reservation count

No frontend is required.
No authentication is required.
No payment processing is required.

This project is intentionally **backend-only and correctness-focused**.

---

## Constraints (Phase 1 — Enforced)

To prevent premature complexity, the following are **explicitly disallowed** in Phase 1:

* Kafka or message brokers
* Distributed locks
* Microservices
* Kubernetes
* Event sourcing frameworks

Allowed stack for Phase 1:

* Java + Spring Boot
* PostgreSQL
* Single service deployment
* Database transactions
* Row-level locking

Distributed systems are earned only after transactional correctness is proven.

---

## Acceptance Criteria

Secventory is considered **complete and valid only if it can prove all of the following with automated tests and logs**:

1. **10,000 concurrent requests → zero oversells**
2. **Duplicate reservation requests → no double reservation**
3. **Duplicate purchase requests → no double purchase**
4. **Database killed mid-purchase → inventory remains consistent after restart**
5. **Expired reservations → stock is restored correctly**
6. **All failures are fully auditable via the transaction log**

Failure to prove any one of the above means the system has failed its core objective.

---

## Why This Project Exists

Secventory exists to demonstrate real, production-grade backend skills in:

* Concurrency control
* Transactional integrity
* Idempotent API design
* Failure recovery
* High-contention load behavior
* Correctness under stress

This project draws a hard line between:

> “I know Spring Boot”
> and
> “I can safely run inventory for a real business under failure and retries.”
