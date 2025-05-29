import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: DingRD
 * @Date: 2025/5/21 11:00
 * @Description:
 */
public class BlockingQueue<T> {

    private T[] arr;

    private Integer count;

    private Integer putIndex;

    private Integer takeIndex;

    private ReentrantLock lock;

    private Condition notFull;

    private Condition notEmpty;

    public BlockingQueue(int capacity) {
        arr = (T[]) new Object[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void put(T item) {
        lock.lock();
        try {
            while (count == arr.length) {
                notFull.await();
            }
            arr[putIndex++] = item;
            if (putIndex == arr.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T t = arr[takeIndex];
            arr[takeIndex] = null;
            if (takeIndex == arr.length) {
                takeIndex = 0;
            }
            count--;
            notFull.signal();
            return t;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
