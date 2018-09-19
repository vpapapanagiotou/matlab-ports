package gr.auth.ee.mug.matlabports;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SelectorsSettersTest {

    @Test
    void generateSelector1() {
        final boolean[] b = SelectorsSetters.createSelector(0, 1, 5, 10);
        final boolean[] bb = new boolean[]{true, true, true, true, true, true, false, false, false, false};
        Assertions.assertNotNull(b);
        Assertions.assertArrayEquals(b, bb);
    }
}