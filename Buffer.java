import java.util.Random;

public class Buffer {
	private final int BUFFER_SIZE;
	private int[] buffer;
	private int getIndex = 0;
	private int putIndex = 0;
	private int count = 0;

	public Buffer(int bufferSize) {
		BUFFER_SIZE = bufferSize;
		buffer = new int[BUFFER_SIZE];
	}

	public synchronized void insert(int data) {
		try {
			while (count == BUFFER_SIZE)
				wait(); // wait

			buffer[putIndex] = data;
			putIndex = ++putIndex % BUFFER_SIZE;
			++count;
			System.out.printf("P[%d]\n", data);
			notifyAll();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public synchronized int remove() {
		try {
			while (count == 0) {
				wait();
			}
			int value = buffer[getIndex];
			getIndex = ++getIndex % BUFFER_SIZE;
			--count;
			System.out.printf("Con[%d]\n", value);
			notifyAll();
			return value;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return -1;
	}
}