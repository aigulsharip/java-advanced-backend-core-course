# Task 1: HashMap Concurrency Experiment

One thread adds elements to a HashMap while another iterates and sums values. Plain `HashMap` throws `ConcurrentModificationException` due to fail-fast iterators.

**Solutions implemented:**
- **ConcurrentHashMap** - Best general solution, allows concurrent reads/writes
- **Collections.synchronizedMap()** - Works but needs external locking for iteration
- **Custom SynchronizedMap** - Single lock guards all access, simple but slower
- **Custom CopyOnWriteMap** - Volatile reference to immutable snapshots, great for read-heavy workloads but expensive writes

Tested across Java 8, 11, and 21. Behavior consistent, newer JVMs slightly faster. Use `ConcurrentHashMap` unless you have specific needs.

---

# Task 2: Thread-Safe Collection Operations

Three threads work with shared data: one writes random numbers, another calculates sum, third calculates square root of sum of squares. Must be thread-safe without deadlocks.

**Four approaches:**
- **Synchronized Block** - All threads lock on same ArrayList, simple but blocking
- **ReentrantLock** - Manual lock/unlock in try-finally, more flexible control
- **CopyOnWriteArrayList** - Copy on write, readers never block, expensive writes
- **LongAdder** - Atomic variables for running totals, lock-free and fast, can't retrieve individual values

All avoid deadlocks via single lock or lock-free structures.

---

# Task 3: Message Bus (Producer-Consumer Pattern)

Build an asynchronous message bus where producers post messages to topics and consumers subscribe to specific topics. Multiple producers/consumers run in parallel. Can't use `java.util.concurrent` queues.

**Implementation:**
- **MessageBusQueue** - Custom `LinkedList` with `synchronized` blocks, `wait()` and `notifyAll()`. Producers wait when full, consumers wait when empty or no matching topic.
- **Producer** - Generates random messages every 300ms
- **Consumer** - Takes messages matching subscribed topic only

Classic Producer-Consumer with topic-based filtering, like a simple pub-sub system.

