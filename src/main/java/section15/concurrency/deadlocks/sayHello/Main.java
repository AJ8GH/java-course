package section15.concurrency.deadlocks.sayHello;

public class Main {
    public static void main(String[] args) {
        final PolitePerson JANE = new PolitePerson("Jane");
        final PolitePerson JOHN = new PolitePerson("John");

        new Thread(() -> JANE.sayHello(JOHN)).start();
        new Thread(() -> JOHN.sayHello(JANE)).start();
    }

    static class PolitePerson {
        private final String name;

        public PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person) {
            System.out.format("%s: %s" + " has said hello to me!%n",
                    this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person) {
            System.out.format("%s: %s" + " has said hello back to me!%n",
                    this.name, person.getName());
        }
    }
}
