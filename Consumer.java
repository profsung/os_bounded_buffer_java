import java.util.Random;

public class Consumer implements Runnable {

	private Buffer buffer;
	private int loopCount;

	public Consumer(Buffer buffer, int loopCount) {
		this.buffer = buffer;
		this.loopCount = loopCount;
	}

	@Override
	public void run() {
		System.out.println("Consumer runs...");
		for (int i = 0; i < loopCount; i++) {
			int data = buffer.remove();
			try {
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
