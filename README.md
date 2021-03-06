
# Multi-thread Design Patterns

Inspired by project [Design patterns implemented in Java](https://java-design-patterns.com/patterns/)

# Patterns

## Lifecycle

Apply callback to monitor a thread.
使用Callback机制来监控线程执行

## Mutex

Create a lock which only allows a single thread to access a resource at any one instant.
使用信号量或独占锁，以限制共享资源的访问

## Immutable
Immutable inner state. Apply `final` and return a `snapshot` of the object state.
对象内部状态不可变，`final`修饰，返回对象的`snapshot`