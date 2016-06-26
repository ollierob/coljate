package net.ollie.coljate.list;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class MutableListTest extends ListTest {

    @Override
    protected abstract MutableList<Object> createFrom(Object... objects);

    @Override
    public MutableList<Object> create() {
        return this.createFrom();
    }

    @Test
    public void testEmpty_SuffixRemove() {
        final MutableList<Object> list = this.create();
        assertTrue(list.isEmpty());
        final Object element = new Object();
        list.suffix(element);
        assertThat(list.count(), is(1));
        assertTrue(list.contains(element));
        list.removeOnce(element);
        assertTrue(list.isEmpty());
        assertFalse(list.contains(element));
    }

    @Test
    public void testSingleton_Prefix() {

        //Given
        final Object o1 = new Object(), o2 = new Object();
        final MutableList<Object> list = this.createFrom(o1);

        //When
        list.prefix(o2);

        //Then
        assertThat(list.count(), is(2));
        assertThat(list.get(0), is(o2));
        assertThat(list.get(1), is(o1));

    }

    @Test
    public void testSingleton_Suffix() {

        //Given
        final Object o1 = new Object(), o2 = new Object();
        final MutableList<Object> list = this.createFrom(o1);

        //When
        list.suffix(o2);

        //Then
        assertThat(list.count(), is(2));
        assertThat(list.get(0), is(o1));
        assertThat(list.get(1), is(o2));

    }

    @Test
    public void testDual_RemoveFirst() {

        final Object o1 = new Object(), o2 = new Object();
        final MutableList<Object> list = this.createFrom(o1, o2);

        list.removeOnce(o1);

        //Then
        assertThat(list.count(), is(1));
        assertFalse(list.contains(o1));
        assertTrue(list.contains(o2));

    }

    @Test
    public void testDual_RemoveLast() {

        final Object o1 = new Object(), o2 = new Object();
        final MutableList<Object> list = this.createFrom(o1, o2);
        assertTrue(list.contains(o2));

        //When
        list.removeOnce(o2);

        //Then
        assertThat(list.count(), is(1));
        assertTrue(list.contains(o1));
        assertFalse(list.contains(o2));

    }

    public void testImmutableCopy() {
        final Object o1 = new Object();
        final MutableList<Object> list = this.createFrom(o1);
        final ImmutableList<Object> immutable = list.immutableCopy();
        assertTrue(immutable.contains(o1));
    }

}
