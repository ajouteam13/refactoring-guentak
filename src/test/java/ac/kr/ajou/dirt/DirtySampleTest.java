package ac.kr.ajou.dirt;

import org.junit.Test;

import java.lang.reflect.Array;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DirtySampleTest {
    String aged = "Aged Brie";
    String back = "Backstage passes to a TAFKAL80ETC concert";
    String sulfras = "Sulfuras, Hand of Ragnaros";
    @Test
    public void aged_backstage_sulfras모두아닌경우_퀄리티양수이면_퀄리티값1감소_sellin은양수(){
        Item[] items = new Item[1];
        Item item = new Item("abc",10,10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(9));
    }
    @Test
    public void aged_backstage_sulfras모두아닌경우_퀄리티음수인경우_sellin은양수(){
        Item[] items = new Item[1];
        Item item = new Item("abc",10,-10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(-10));
    }
    @Test
    public void age일때_퀄리티가50미만일때_sellin은양수(){
        Item[] items = new Item[1];
        Item item = new Item(aged,10,10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(11));
    }
    @Test
    public void back일때_퀄리티50미만인경우_sellinq은범위별(){
        Item[] items = new Item[3];
        Item item = new Item(back,10,10); items[0] = item;
        Item item1 = new Item(back,14,10); items[1] = item1;
        Item item2 = new Item(back,1,10); items[2] = item2;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(12)); // 6 <sellin<11
        assertThat(items[1].quality,is(11)); // sellin>11
        assertThat(items[2].quality,is(13)); // sellin<6
    }
    @Test
    public void sulfras인데_sellin이_음수인경우(){
        Item[] items = new Item[1];
        Item item = new Item(sulfras,0,10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(10));
    }
    @Test
    public void 셋다아니고_sellin음수_퀄리티음수(){
        Item[] items = new Item[1];
        Item item = new Item("abc",0,-10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(-10));
    }
    @Test
    public void 셋다아니고_sellin음수_퀄리티양수(){
        Item[] items = new Item[1];
        Item item = new Item("abc",0,10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(8));
    }
    @Test
    public void aged이고_sellin음수_퀄리티50미만(){
        Item[] items = new Item[1];
        Item item = new Item(aged,0,10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(12));
    }
    @Test
    public void back이고_sellin음수이고이면퀄리티0(){
        Item[] items = new Item[1];
        Item item = new Item(back,0,10);
        items[0] = item;
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items[0].quality,is(0));
    }
}