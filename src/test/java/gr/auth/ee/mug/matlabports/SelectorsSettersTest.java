package gr.auth.ee.mug.matlabports;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static gr.auth.ee.mug.matlabports.SelectorsSetters.END;


class SelectorsSettersTest {

    @Test
    void createSelector1() {
        final boolean[] b = SelectorsSetters.createSelector(0, 1, 5, 10);
        final boolean[] bb = new boolean[]{true, true, true, true, true, true, false, false, false, false};
        Assertions.assertNotNull(b);
        Assertions.assertArrayEquals(b, bb);
    }

    @Test
    void createSelector2() {
        final boolean[] b = SelectorsSetters.createSelector(1, 1, 6, 7);
        final boolean[] bb = new boolean[]{false, true, true, true, true, true, true};
        Assertions.assertNotNull(b);
        Assertions.assertArrayEquals(b, bb);
    }

    @Test
    void createSelector3() {
        final boolean[] b = SelectorsSetters.createSelector(5, -2, 3, 6);
        final boolean[] bb = new boolean[]{false, false, false, true, false, true};
        Assertions.assertNotNull(b);
        Assertions.assertArrayEquals(b, bb);
    }

    @Test
    void createSelector4() {
        final boolean[] b = SelectorsSetters.createSelector(1, 2, END,10);
        final boolean[] bb = new boolean[]{false, true, false, true, false, true, false, true, false, true};
        Assertions.assertNotNull(b);
        Assertions.assertArrayEquals(b, bb);
    }

    @Test
    void createSelector5() {
        final boolean[] b = SelectorsSetters.createSelector(0, 2, END - 1,4);
        final boolean[] bb = new boolean[]{true, false, true, false};
        Assertions.assertNotNull(b);
        Assertions.assertArrayEquals(b, bb);
    }
}