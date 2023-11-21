package hillel.lesson23_patterns_part2;


// Classical Java implementation of singleton
// design pattern
class Singleton {
    private static Singleton instance;

    // private constructor to force use of
    // getInstance() to create Singleton object
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }
}