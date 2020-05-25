package homework.six;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestArrayTwo {

    private HWArrays hw;

    @Parameterized.Parameters
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][] {
                {1,1,1,1,1},
                {4,4,4,4,4},
                {1,4,5,4,1},
                {1,4,1,1,1}
        });
    }

    private final int[] arrReq;

    public TestArrayTwo(int[] arrReq) {
        this.arrReq = arrReq;
    }

    @Before
    public void init(){
        hw = new HWArrays();
    }

    @Test
    public void testArraysTrue() {
        Assert.assertTrue(hw.hasOneOrFourIntoArray(arrReq));
    }

    @Test
    public void testArraysFalse() {
        Assert.assertFalse(hw.hasOneOrFourIntoArray(arrReq));
    }

}
