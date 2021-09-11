import java.util.Random;

public class Producer implements Runnable {

	private Buffer buffer;
	private int loopCount;

	public Producer(Buffer buffer, int loopCount) {
		this.buffer = buffer;
		this.loopCount = loopCount;
	}

	@Override
	public void run() {
		System.out.println("Producer runs....");
		for (int i = 0; i < loopCount; i++) {
			buffer.insert(i);
			try {
				Thread.sleep(new Random().nextInt(500));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
}
