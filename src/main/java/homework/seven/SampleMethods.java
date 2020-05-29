package homework.seven;

public class SampleMethods {

    @BeforeSuite
    public void methodBeforeSuite() {
        System.out.println("Method before suite");
    }

    @Test(priority = 1)
    public void methodTest1() {
        System.out.println("Method 1");
    }

    @Test(priority = 2)
    public void methodTest2() {
        System.out.println("Method 2");
    }

    @Test(priority = 2)
    public void methodTest3() {
        System.out.println("Method 3");
    }

    @Test(priority = 4)
    public void methodTest4() {
        System.out.println("Method 4");
    }

    @Test
    public void methodTest5() {
        System.out.println("Method 5");
    }

    @Test
    public void methodTest6() {
        System.out.println("Method 6");
    }

    @Test(priority = 2)
    public void methodTest7() {
        System.out.println("Method 7");
    }

    @Test(priority = 8)
    public void methodTest8() {
        System.out.println("Method 8");
    }

    @Test(priority = 9)
    public void methodTest9() {
        System.out.println("Method 9");
    }

    @Test(priority = 10)
    public void methodTest10() {
        System.out.println("Method 10");
    }

    @AfterSuite
    public void methodAfterSuite() {
        System.out.println("Method after suite");
    }

}
