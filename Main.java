public class Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main loop_count");
			System.exit(1);
		}

		int loopCount = 0;
		try {
			loopCount = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println(args[0] +" is not an integer");
			System.exit(1);
		}

		Buffer buffer = new Buffer(3);
		Producer producer = new Producer(buffer, loopCount);
		Consumer consumer = new Consumer(buffer, loopCount);

		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t2.start();
		t1.start();

		try {
			t1.join();
			System.out.println("Producer finishes");
			t2.join();
			System.out.println("Consumer finishes");
		} catch (InterruptedException e) {

		}
	}

}