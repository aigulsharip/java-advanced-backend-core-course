# Task 2: Thread-Safe Collection Operations

## The Problem

The task was to create three threads that work with a shared collection:
- One thread continuously writes random numbers
- Another calculates and prints the sum
- A third calculates the square root of the sum of squares

Everything needs to be thread-safe without causing deadlocks.

## How I Solved It
I implemented four different approaches to compare their trade-offs:

**1. Synchronized Block** - The simplest solution using Java's built-in synchronization. All threads lock on the same ArrayList, so only one can access it at a time. Easy to understand but threads block each other.

**2. ReentrantLock** - Similar to synchronized blocks but gives more control. You manually lock and unlock in try-finally blocks. More flexible if you need features like tryLock() or fairness policies.

**3. CopyOnWriteArrayList** - A concurrent collection that creates a new copy on every write. Readers never block, which is great for read-heavy scenarios. The downside is higher memory usage and expensive writes.

**4. LongAdder** - Instead of keeping all numbers in a collection, this approach just maintains running totals using atomic variables. Super fast and lock-free, perfect when you only need aggregated values. Can't retrieve individual numbers though.
All approaches avoid deadlocks by using a single lock or lock-free structures. Choose based on your needs: traditional locking for full control, CopyOnWriteArrayList for lots of reads, or LongAdder when you only care about totals.

---

# Task 3: Message Bus (Producer-Consumer Pattern)

## The Problem

Build an asynchronous message bus where:
- Producers generate random messages with topics and post them to a queue
- Consumers subscribe to specific topics and log messages they receive
- Multiple producers and consumers run in parallel
- Can't use `java.util.concurrent` queue implementations

## How I Solved It

I implemented a custom **MessageBusQueue** using a plain `LinkedList` with manual synchronization. The key challenge was making consumers topic-aware without using concurrent queues.

**MessageBusQueue** - Uses `synchronized` blocks with `wait()` and `notifyAll()` for thread coordination. When the queue is full, producers wait. When it's empty (or no matching topic), consumers wait. The `take()` method iterates through the queue to find messages matching the consumer's topic.

**Producer** - Generates messages with random topics every 300ms and puts them in the queue. If the queue is full, it blocks until space becomes available.

**Consumer** - Continuously tries to take messages for its specific topic. It waits if no matching messages are in the queue.

The solution demonstrates classic Producer-Consumer with a twist: topic-based filtering. Each consumer only processes messages for its subscribed topic, making it more like a simple pub-sub system than a basic queue.

