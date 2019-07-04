/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent;

/**
 * A {@link BlockingQueue} in which producers may wait for consumers
 * to receive elements.  A {@code TransferQueue} may be useful for
 * example in message passing applications in which producers
 * sometimes (using method {@link #transfer}) await receipt of
 * elements by consumers invoking {@code take} or {@code poll}, while
 * at other times enqueue elements (via method {@code put}) without
 * waiting for receipt.
 * 1、生产者线程等待一个消费者线程接收元素。
 * 2、TransferQueue 可以用于消息传递应用的场景，
 * 3、在某些场景下 生产者线程调用transfer方法，等待另外一个消费者线程调用 take | poll方法获取元素
 * 3、在另外一些场景下通过put方法添加元素，但是不用等待其他线程获取该元素。
 *
 * {@linkplain #tryTransfer(Object) Non-blocking} and
 * {@linkplain #tryTransfer(Object,long,TimeUnit) time-out} versions of
 * {@code tryTransfer} are also available.
 * A {@code TransferQueue} may also be queried, via {@link
 * #hasWaitingConsumer}, whether there are any threads waiting for
 * items, which is a converse analogy to a {@code peek} operation.
 * 1、tryTransfer(Object) 非阻塞
 * 2、tryTransfer(Object,long,TimeUnit) 超时
 * 3、TransferQueue 是可以查询的，通过 hasWaitingConsumer 方法，可以查询是否有消费者线程在等待消费数据，这与{@code peek}操作相反。
 *
 * <p>Like other blocking queues, a {@code TransferQueue} may be
 * capacity bounded.  If so, an attempted transfer operation may
 * initially block waiting for available space, and/or subsequently
 * block waiting for reception by a consumer.  Note that in a queue
 * with zero capacity, such as {@link SynchronousQueue}, {@code put}
 * and {@code transfer} are effectively synonymous.
 * 1、和其他的阻塞队列实现相比，TransferQueue 可能是容量有限的。
 * 2、如果容量有限，尝试调用transfer操作可能会阻塞等待有可用的空间，随后阻止等待消费者接收。
 * 3、如果是0容量的队列实现，例如 SynchronousQueue，put 和 transfer 操作的实现效果是一致的
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.7
 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 */
public interface TransferQueue<E> extends BlockingQueue<E> {
    /**
     * Transfers the element to a waiting consumer immediately, if possible.
     * 如果有另外一个消费者线程等待获取元素，立即将参数元素交换给消费者线程
     *
     * <p>More precisely, transfers the specified element immediately
     * if there exists a consumer already waiting to receive it (in
     * {@link #take} or timed {@link #poll(long,TimeUnit) poll}),
     * otherwise returning {@code false} without enqueuing the element.
     * 更确切的说，如果有一个消费者线程通过调用 take | poll(long,TimeUnit) 方法获取元素
     * 将这个元素交个这个消费者线程。
     * 如果没有消费者线程，直接返回false，这个元素并不会执行入队操作。
     *
     * @param e the element to transfer
     * @return {@code true} if the element was transferred, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this queue
     */
    boolean tryTransfer(E e);

    /**
     * Transfers the element to a consumer, waiting if necessary to do so.
     * 将参数元素传递给另外一个消费者线程，如果没有消费者线程，则等待。
     *
     * <p>More precisely, transfers the specified element immediately
     * if there exists a consumer already waiting to receive it (in
     * {@link #take} or timed {@link #poll(long,TimeUnit) poll}),
     * else waits until the element is received by a consumer.
     * 更确切的说，如果有一个消费者线程通过调用 take | poll(long,TimeUnit) 方法获取元素
     * 将这个元素交个这个消费者线程。
     * 如果没有这样的消费者线程，在进入阻塞，直到有消费者线程出现。
     *
     * @param e the element to transfer
     * @throws InterruptedException if interrupted while waiting,
     *         in which case the element is not left enqueued
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this queue
     */
    void transfer(E e) throws InterruptedException;

    /**
     * Transfers the element to a consumer if it is possible to do so
     * before the timeout elapses.
     * 将参数元素传递给另外一个消费者线程，如果没有消费者线程，则等待参数指定时间。
     *
     * <p>More precisely, transfers the specified element immediately
     * if there exists a consumer already waiting to receive it (in
     * {@link #take} or timed {@link #poll(long,TimeUnit) poll}),
     * else waits until the element is received by a consumer,
     * returning {@code false} if the specified wait time elapses
     * before the element can be transferred.
     * 更确且的说，如果有一个消费者线程通过调用 take | poll(long,TimeUnit) 方法获取元素
     * 将这个元素交个这个消费者线程。
     * 如果没有这样的消费者线程，在阻塞指定时间，如果在指定时间没有消费者线程出现，直接返回 false。
     *
     * @param e the element to transfer
     * @param timeout how long to wait before giving up, in units of
     *        {@code unit}
     * @param unit a {@code TimeUnit} determining how to interpret the
     *        {@code timeout} parameter
     * @return {@code true} if successful, or {@code false} if
     *         the specified waiting time elapses before completion,
     *         in which case the element is not left enqueued
     * @throws InterruptedException if interrupted while waiting,
     *         in which case the element is not left enqueued
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this queue
     */
    boolean tryTransfer(E e, long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * Returns {@code true} if there is at least one consumer waiting
     * to receive an element via {@link #take} or
     * timed {@link #poll(long,TimeUnit) poll}.
     * The return value represents a momentary state of affairs.
     * 如果有消费者线程通过调用 take | poll(long,TimeUnit) 方法等待获取元素，返回true.
     * 这个方法的返回值只是一个瞬时状态。
     *
     * @return {@code true} if there is at least one waiting consumer
     */
    boolean hasWaitingConsumer();

    /**
     * Returns an estimate of the number of consumers waiting to
     * receive elements via {@link #take} or timed
     * {@link #poll(long,TimeUnit) poll}.  The return value is an
     * approximation of a momentary state of affairs, that may be
     * inaccurate if consumers have completed or given up waiting.
     * The value may be useful for monitoring and heuristics, but
     * not for synchronization control.  Implementations of this
     * method are likely to be noticeably slower than those for
     * {@link #hasWaitingConsumer}.
     * 1、返回通过调用 take | poll(long,TimeUnit) 方法等待获取元素的消费者线程的估计值.
     * 2、这个方法的返回值只是一个瞬时状态，如果有消费者线程完成操作或者放弃等待元素，会导致这个方法不准确。
     * 3、这个方法返回值用于监控和提醒，但是不能用于同步控制。
     * 4、这个方法的返回值比 hasWaitingConsumer 方法更新要慢。
     * @return the number of consumers waiting to receive elements
     */
    int getWaitingConsumerCount();
}
