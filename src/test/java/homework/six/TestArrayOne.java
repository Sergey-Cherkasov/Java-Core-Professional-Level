package homework.six;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//@RunWith(Parameterized.class)
public class TestArrayOne {

    private HWArrays hw;

    int[] arrExpTest1_2 = {5,6};
    int[] arrReqTest1 = {1,4,2,4,3,4,5,6};
    int[] arrReqTest2 = {1,2,3,4,5,6};
    int[] arrExpTest3 = new int[0];
    int[] arrReqTest3 = {1,4,2,4,3,4};
    int[] arrReqRTE = {1,2,3,5,6};

    @Before
    public void init(){
        hw = new HWArrays();
    }

    @Test
    public void testArray1() {
        Assert.assertArrayEquals(arrExpTest1_2, hw.getNewArray(arrReqTest1));
    }

    @Test
    public void testArray2() {
        Assert.assertArrayEquals(arrExpTest1_2, hw.getNewArray(arrReqTest2));
    }

    @Test
    public void testArray3() {
        Assert.assertArrayEquals(arrExpTest3, hw.getNewArray(arrReqTest3));
    }

    // Два варианта теста на RuntimeException
    @Test
    public void testArrayRuntimeException1() {
        Assert.assertThrows(RuntimeException.class, () -> hw.getNewArray(arrReqRTE));
    }

    @Test (expected = RuntimeException.class)
    public void testArrayRuntimeException2() {
        Assert.assertArrayEquals(arrExpTest3, hw.getNewArray(arrReqRTE));
        Assert.assertThrows(RuntimeException.class, () -> hw.getNewArray(arrReqRTE));
    }

//    Вариант с параметризации тестов
/*
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new int[][][] {
                {{1,4,2,4,3,4,5,6}, {5,6}},
                {{1,2,3,4,5,6}, {5,6}},
                {{1,4,2,4,3,4}, new int[0]}
        });
    }

    private final int[] arrExp;
    private final int[] arrReq

    public TestArrayOne(int[] arrReq, int[] arrExp) {
        this.arrExp = arrExp;
        this.arrReq = arrReq;
    }

    @Test
    public void testArrays() {
        Assert.assertArrayEquals(arrExp, hw.getNewArray(arrReq));
    }

*/

}
