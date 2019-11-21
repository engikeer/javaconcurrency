@FunctionalInterface
public interface TestInterface<E extends Exception> {

    public void test() throws E;
}
